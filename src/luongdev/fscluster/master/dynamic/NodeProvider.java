package luongdev.fscluster.master.dynamic;

import luongdev.fscluster.master.FSNodeRepository;
import luongdev.fscluster.master.NodeDbSourceAdapter;
import luongdev.fscluster.master.properties.MasterDbConfigProperties;
import org.hibernate.engine.jdbc.connections.spi.AbstractDataSourceBasedMultiTenantConnectionProviderImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.util.Map;
import java.util.TreeMap;

@Configuration
public class NodeProvider extends AbstractDataSourceBasedMultiTenantConnectionProviderImpl {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private final Map<String, DataSource> dataSources = new TreeMap<>();

    private final MasterDbConfigProperties masterDbConfigProperties;
    private final FSNodeRepository fsNodeRepository;

    public NodeProvider(
            MasterDbConfigProperties masterDbConfigProperties, FSNodeRepository fsNodeRepository) {
        this.masterDbConfigProperties = masterDbConfigProperties;
        this.fsNodeRepository = fsNodeRepository;
    }

    @Override
    protected DataSource selectAnyDataSource() {
        if (dataSources.isEmpty()) {
            var fsNodes = fsNodeRepository.findAll();
            logger.info("selectAnyDataSource() method call...Total fsNodes:" + fsNodes.size());
            for (var node : fsNodes) {
                dataSources.put(
                        node.getId().toString(),
                        new NodeDbSourceAdapter(node).getDataSource(masterDbConfigProperties.getDbname())
                );
            }
        }
        return this.dataSources.values().iterator().next();
    }

    @Override
    protected DataSource selectDataSource(String nodeIdentifier) {
        var nodeId = initializeTenantIfLost(nodeIdentifier);
        if (!this.dataSources.containsKey(nodeId)) {
            var fsNodes = fsNodeRepository.findAll();
            for (var node : fsNodes) {
                dataSources.put(
                        node.getId().toString(),
                        new NodeDbSourceAdapter(node).getDataSource(masterDbConfigProperties.getDbname())
                );
            }
        }

        if (!this.dataSources.containsKey(nodeId)) {
            logger.warn("Trying to get tenant:" + nodeIdentifier + " which was not found in master db after rescan");
        }

        return this.dataSources.get(nodeId);
    }

    private String initializeTenantIfLost(String tenantIdentifier) {
        if (!tenantIdentifier.equals(NodeHolder.getNode())) {
            tenantIdentifier = NodeHolder.getNode();
        }

        return tenantIdentifier;
    }
}