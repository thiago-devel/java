package br.com.rubyit.resseler.core.persistence.config.dao.test;

import java.util.Properties;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.jdbc.datasource.lookup.JndiDataSourceLookup;
import org.springframework.mock.jndi.SimpleNamingContextBuilder;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

import br.com.rubyit.resseler.persistence.config1.dao.SalesDAOAppConfig;

@Configuration
@ComponentScan(basePackages = {
        "br.com.rubyit.resseler.core.persistence.dao" })
public class TestDDBDAOAppConfig extends SalesDAOAppConfig {
    private InitialContext ic;
    private DriverManagerDataSource dataSource;

    @Override
    public DataSource dataSourceResseler() {
        if (!mountDatasourceJNDI()) { return null; }

        final JndiDataSourceLookup dsLookup = new JndiDataSourceLookup();
        dsLookup.setResourceRef(true);

        return dsLookup.getDataSource(RESSELER_JNDI_NAME);
    }

    @Override
    public LocalContainerEntityManagerFactoryBean entityManagerFactoryBeanResseler() {
        final HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        vendorAdapter.setGenerateDdl(true);

        final Properties properties = getAdditionalProperties();

        final LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
        factory.setJpaVendorAdapter(vendorAdapter);
        factory.setPersistenceUnitName(PERSISTENCE_UNIT_NAME_RESSELER);
        factory.setPackagesToScan(ENTITY_PKG_SCANNING);
        factory.setDataSource(dataSourceResseler());
        factory.setJpaProperties(properties);

        factory.afterPropertiesSet();

        return factory;
    }

    @Override
    protected Properties getAdditionalProperties() {
        final Properties properties = new Properties();

        properties.put("hibernate.dialect",
                "org.hibernate.dialect.SQLServerDialect");

        properties.put("hibernate.hbm2ddl.auto", "validate");
        properties.put("show_sql", "true");
        properties.put("format_sql", "true");
        properties.put("use_sql_comments", "true");
        return properties;
    }

    private boolean mountDatasourceJNDI() {
        if ((ic != null) && (dataSource != null)) { return true; }
        try {
            SimpleNamingContextBuilder.emptyActivatedContextBuilder();
            ic = new InitialContext();
            ic.createSubcontext("java:");
            ic.createSubcontext("java:jboss/");
            ic.createSubcontext("java:jboss/datasources/");

            final DriverManagerDataSource dataSource = new DriverManagerDataSource();
            dataSource.setDriverClassName(
                    "com.microsoft.sqlserver.jdbc.SQLServerDataSource");

            dataSource.setUrl(
                    "jdbc:sqlserver://SAOS005DB09D;DatabaseName=Resseler");

            dataSource.setUsername("userdes");
            dataSource.setPassword("userdes");

            ic.bind("java:jboss/datasources/Resseler", dataSource);
        } catch (final NamingException ex) {
            System.err.println(
                    "ERROR: fail to prepare the persistence context! - " + ex);

            return false;
        }

        return true;
    }
}
