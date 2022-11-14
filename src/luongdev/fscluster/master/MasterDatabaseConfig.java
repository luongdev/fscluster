package luongdev.fscluster.master;

import com.zaxxer.hikari.HikariDataSource;
import luongdev.fscluster.master.properties.MasterDbConfigProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
        basePackages = {"luongdev.fscluster.master"},
        entityManagerFactoryRef = "masterEntityManagerFactory",
        transactionManagerRef = "masterTransactionManager")
public class MasterDatabaseConfig {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private final MasterDbConfigProperties masterDbProperties;

    public MasterDatabaseConfig(MasterDbConfigProperties masterDbProperties) {
        this.masterDbProperties = masterDbProperties;
    }

    @Bean(name = "masterDataSource")
    public DataSource masterDataSource() {
        var hikariDataSource = new HikariDataSource();
        hikariDataSource.setUsername(masterDbProperties.getUser());
        hikariDataSource.setPassword(masterDbProperties.getPassword());

        var url = String.format(
                "jdbc:postgresql://%s:%s/%s",
                masterDbProperties.getHost(),
                masterDbProperties.getPort(),
                masterDbProperties.getDbname());

        hikariDataSource.setJdbcUrl(url);
        hikariDataSource.setDriverClassName("org.postgresql.Driver");
        hikariDataSource.setPoolName("FMASTER-POOL");

        logger.info("Setup of masterDataSource succeeded.");

        return hikariDataSource;
    }

    @Primary
    @Bean(name = "masterEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean masterEntityManagerFactory() {
        var em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(masterDataSource());
        em.setPackagesToScan(FSNode.class.getPackage().getName(), FSNodeRepository.class.getPackage().getName());
        em.setPersistenceUnitName("FMASTER-UNIT");

        var vendorAdapter = new HibernateJpaVendorAdapter();
        em.setJpaVendorAdapter(vendorAdapter);
        em.setJpaProperties(hibernateProperties());

        logger.info("Setup of masterEntityManagerFactory succeeded.");

        return em;
    }

    @Primary
    @Bean(name = "masterTransactionManager")
    public JpaTransactionManager masterTransactionManager(
            @Qualifier("masterEntityManagerFactory") EntityManagerFactory emf) {
        var transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(emf);

        return transactionManager;
    }

    @Bean
    public PersistenceExceptionTranslationPostProcessor exceptionTranslation() {
        return new PersistenceExceptionTranslationPostProcessor();
    }

    private Properties hibernateProperties() {
        Properties properties = new Properties();
        properties.put(org.hibernate.cfg.Environment.DIALECT, "org.hibernate.dialect.PostgreSQLDialect");
        properties.put(org.hibernate.cfg.Environment.SHOW_SQL, true);
        properties.put(org.hibernate.cfg.Environment.FORMAT_SQL, true);
        properties.put(org.hibernate.cfg.Environment.HBM2DDL_AUTO, "update");

        return properties;
    }

}
