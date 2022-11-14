package luongdev.fscluster.master.dynamic;

import org.hibernate.context.spi.CurrentTenantIdentifierResolver;

public class NodeResolver implements CurrentTenantIdentifierResolver {

    private static final String DEFAULT_NODE = "6950945f-9c9a-4ec4-8663-8aba2888055f";

    @Override
    public String resolveCurrentTenantIdentifier() {
        var nodeId = NodeHolder.getNode();
        return nodeId == null ? DEFAULT_NODE : nodeId;
    }

    @Override
    public boolean validateExistingCurrentSessions() {
        return true;
    }
}
