package br.com.rubyit.resseler.core.persistence.config.dao.test;

import java.util.Properties;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PropertiesLoaderUtils;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.jdbc.datasource.lookup.JndiDataSourceLookup;
import org.springframework.mock.jndi.SimpleNamingContextBuilder;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

import br.com.rubyit.resseler.persistence.config2.dao.DAOResselerAppConfig;

@Configuration
@ComponentScan(basePackages = {
		"br.com.rubyit.resseler.persistence.dao"})
public class TestMockDAOAppConfig extends DAOResselerAppConfig {
    private InitialContext ic;
    private DriverManagerDataSource dataSource;
    private String jndi = null;

    public TestMockDAOAppConfig() {

        try {
            final Resource resource = new ClassPathResource("jndi.properties");
            final Properties props = PropertiesLoaderUtils
                    .loadProperties(resource);
            jndi = props.getProperty("jndi.datasource");

        } catch (final Exception e) {
            jndi = RESSELER_JNDI_NAME;
        }
    }

    @Override
    public DataSource dataSourceResseler() {
        if (!mountDatasourceJNDI()) { return null; }

        final JndiDataSourceLookup dsLookup = new JndiDataSourceLookup();
        dsLookup.setResourceRef(true);

        return dsLookup.getDataSource(jndi);
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
                "org.hibernate.dialect.HSQLDialect");

        //properties.put("hibernate.hbm2ddl.auto", "validate");
        properties.put("hibernate.hbm2ddl.auto", "update");
        properties.put("show_sql", "true");
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
            ic.createSubcontext("jdbc");

            final DriverManagerDataSource dataSource = new DriverManagerDataSource();
            dataSource.setDriverClassName("org.hsqldb.jdbcDriver");
            dataSource.setUrl("jdbc:hsqldb:mem:aname;sql.syntax_mss=true");
            /*dataSource.setUrl(
                    "jdbc:hsqldb:file:target/data/db/db;shutdown=true;hsqldb.write_delay‌​_millis=0;hsqldb.tx_level=serializable");*/
            dataSource.setUsername("sa");
            dataSource.setPassword("");

            /*final ResourceDatabasePopulator databasePopulator = new ResourceDatabasePopulator();
            databasePopulator.setContinueOnError(true);
            databasePopulator.addScript(
                    new ClassPathResource("create_schema_AuditTrail.sql"));

            DatabasePopulatorUtils.execute(databasePopulator, dataSource);*/

            ic.bind("java:jboss/datasources/Resseler", dataSource);
            ic.bind("jdbc/ResselerDS", dataSource);
        } catch (final NamingException ex) {
            System.err.println(
                    "ERROR: fail to prepare the persistence context! - " + ex);

            return false;
        }

        return true;
    }
}
