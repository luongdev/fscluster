package luongdev.fscluster.fnode;

import luongdev.fscluster.master.FSNodeRepository;
import luongdev.fscluster.master.dynamic.NodeProvider;
import luongdev.fscluster.master.dynamic.NodeResolver;
import luongdev.fscluster.master.properties.MasterDbConfigProperties;
import org.hibernate.MultiTenancyStrategy;
import org.hibernate.cfg.Environment;
import org.hibernate.context.spi.CurrentTenantIdentifierResolver;
import org.hibernate.engine.jdbc.connections.spi.MultiTenantConnectionProvider;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import java.util.HashMap;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
        basePackageClasses = TestRepository.class,
        entityManagerFactoryRef = "nodeEntityManagerFactory",
        transactionManagerRef = "nodeTransactionManager")
public class NodeDatabaseConfig {

    private final MasterDbConfigProperties masterDbConfigProperties;
    private final FSNodeRepository fsNodeRepository;

    public NodeDatabaseConfig(
            MasterDbConfigProperties masterDbConfigProperties, FSNodeRepository fsNodeRepository) {
        this.masterDbConfigProperties = masterDbConfigProperties;
        this.fsNodeRepository = fsNodeRepository;
    }

    @Bean(name = "nodeJpaVendorAdapter")
    public JpaVendorAdapter nodeJpaVendorAdapter() {
        return new HibernateJpaVendorAdapter();
    }

    @Bean(name = "nodeTransactionManager")
    public JpaTransactionManager transactionManager(@Qualifier("nodeEntityManagerFactory") EntityManagerFactory emf) {
        var transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(emf);

        return transactionManager;
    }

    @Bean(name = "nodeProvider")
    @ConditionalOnBean(name = "masterEntityManagerFactory")
    public MultiTenantConnectionProvider nodeProvider() {
        return new NodeProvider(masterDbConfigProperties, fsNodeRepository);
    }

    @Bean(name = "nodeResolver")
    public CurrentTenantIdentifierResolver nodeResolver() {
        return new NodeResolver();
    }

    @Bean(name = "nodeEntityManagerFactory")
    @ConditionalOnBean(name = "nodeProvider")
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(
            @Qualifier("nodeProvider") MultiTenantConnectionProvider nodeProvider,
            @Qualifier("nodeResolver") CurrentTenantIdentifierResolver nodeResolver) {
        var em = new LocalContainerEntityManagerFactoryBean();

        em.setPackagesToScan("luongdev.fscluster.fnode");
        em.setJpaVendorAdapter(nodeJpaVendorAdapter());
        em.setPersistenceUnitName("NODE-UNIT");

        var properties = new HashMap<String, Object>();
        properties.put(Environment.MULTI_TENANT, MultiTenancyStrategy.DATABASE);
        properties.put(Environment.MULTI_TENANT_CONNECTION_PROVIDER, nodeProvider);
        properties.put(Environment.MULTI_TENANT_IDENTIFIER_RESOLVER, nodeResolver);
        properties.put(Environment.DIALECT, "org.hibernate.dialect.PostgreSQLDialect");
        properties.put(Environment.SHOW_SQL, true);
        properties.put(Environment.FORMAT_SQL, true);
        properties.put(Environment.HBM2DDL_AUTO, "update");

        em.setJpaPropertyMap(properties);

        return em;
    }

}
