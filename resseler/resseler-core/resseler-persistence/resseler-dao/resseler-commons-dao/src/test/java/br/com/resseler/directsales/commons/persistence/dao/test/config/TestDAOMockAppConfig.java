package br.com.resseler.directsales.commons.persistence.dao.test.config;

import static br.com.resseler.directsales.commons.persistence.dao.util.CustomerSalesConstants.CONTRACT_JNDI_NAME;
import static br.com.resseler.directsales.commons.persistence.dao.util.CustomerSalesConstants.DIRECTSALES_JNDI_NAME;
import static br.com.resseler.directsales.commons.persistence.dao.util.CustomerSalesConstants.PERSISTENCE_UNIT_NAME_CONTRACT;
import static br.com.resseler.directsales.commons.persistence.dao.util.CustomerSalesConstants.PERSISTENCE_UNIT_NAME_DIRECTSALES;

import java.util.Arrays;
import java.util.List;
import java.util.Properties;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.jdbc.datasource.init.DatabasePopulatorUtils;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import org.springframework.jdbc.datasource.lookup.JndiDataSourceLookup;
import org.springframework.mock.jndi.SimpleNamingContextBuilder;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

import br.com.resseler.directsales.commons.persistence.dao.config.DAOAppConfig;
import br.com.resseler.directsales.commons.persistence.dao.impl.InsuredDAO;
import br.com.resseler.directsales.commons.persistence.dao.impl.ProductInsuranceDAO;
import br.com.resseler.directsales.commons.persistence.dao.impl.SystemPersonDAO;

@Configuration
@ComponentScan(basePackages = { "br.com.cardif.directsales.commons.dto" })
public class TestDAOMockAppConfig extends DAOAppConfig {

    private InitialContext ic;
    private DriverManagerDataSource dataSource;
    private static final List<String> COMMONS_ENTITY_PKG_SCANNING = Arrays
            .asList("br.com.cardif.directsales.commons.dao.entity");

    @Bean
    public SystemPersonDAO systemPersonDAO() {
        return new SystemPersonDAO();
    }

    @Bean
    public ProductInsuranceDAO productInsuranceDAO() {
        return new ProductInsuranceDAO();
    }

    @Bean
    public InsuredDAO insuredDAO() {
        return new InsuredDAO();
    }

    @Override
    public DataSource dataSourceDirectSales() {
        if (!mountDatasourceDBVendasDiretaJNDI()) { return null; }

        final JndiDataSourceLookup dsLookup = new JndiDataSourceLookup();
        dsLookup.setResourceRef(true);

        return dsLookup.getDataSource(DIRECTSALES_JNDI_NAME);
    }

    @Override
    public DataSource dataSourceContract() {
        if (!mountDatasourceDBContractJNDI()) { return null; }

        final JndiDataSourceLookup dsLookup = new JndiDataSourceLookup();
        dsLookup.setResourceRef(true);

        return dsLookup.getDataSource(CONTRACT_JNDI_NAME);
    }

    @Override
    public LocalContainerEntityManagerFactoryBean entityManagerFactoryBeanDirectSales() {
        final HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        vendorAdapter.setGenerateDdl(true);

        final Properties properties = getAdditionalProperties();

        final LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
        factory.setJpaVendorAdapter(vendorAdapter);
        factory.setPersistenceUnitName(PERSISTENCE_UNIT_NAME_DIRECTSALES);
        factory.setPackagesToScan(COMMONS_ENTITY_PKG_SCANNING.toArray(new String[0]));
        factory.setDataSource(dataSourceDirectSales());
        factory.setJpaProperties(properties);

        factory.afterPropertiesSet();

        return factory;
    }

    @Override
    public LocalContainerEntityManagerFactoryBean entityManagerFactoryBeanContract() {
        final HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        vendorAdapter.setGenerateDdl(true);

        final Properties properties = getAdditionalProperties();

        final LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
        factory.setJpaVendorAdapter(vendorAdapter);
        factory.setPersistenceUnitName(PERSISTENCE_UNIT_NAME_CONTRACT);
        factory.setPackagesToScan(COMMONS_ENTITY_PKG_SCANNING.toArray(new String[0]));
        factory.setDataSource(dataSourceDirectSales());
        factory.setJpaProperties(properties);

        factory.afterPropertiesSet();

        return factory;
    }

    @Override
    protected Properties getAdditionalProperties() {
        final Properties properties = new Properties();

        properties.put("hibernate.dialect", "org.hibernate.dialect.HSQLDialect");
        properties.put("hibernate.hbm2ddl.auto", "validate");
        properties.put("show_sql", "true");

        return properties;
    }

    private boolean mountDatasourceDBVendasDiretaJNDI() {
        if ((ic != null) && (dataSource != null)) { return true; }

        try {
            SimpleNamingContextBuilder.emptyActivatedContextBuilder();
            ic = new InitialContext();
            ic.createSubcontext("java:");
            ic.createSubcontext("java:jboss/");
            ic.createSubcontext("java:jboss/datasources/");

            final DriverManagerDataSource dataSource = new DriverManagerDataSource();
            dataSource.setDriverClassName("org.hsqldb.jdbcDriver");
            dataSource.setUrl("jdbc:hsqldb:mem:aname;sql.syntax_mss=true");
            dataSource.setUrl("jdbc:hsqldb:mem:aname");
            dataSource.setUsername("sa");
            dataSource.setPassword("");

            final ResourceDatabasePopulator databasePopulator = new ResourceDatabasePopulator();
            databasePopulator.setContinueOnError(true);
            databasePopulator.addScript(new ClassPathResource("create_person_schema.sql"));
            databasePopulator.addScript(new ClassPathResource("create_product_schema.sql"));
            databasePopulator.addScript(new ClassPathResource("create_certificate_schema.sql"));
            databasePopulator.addScript(new ClassPathResource("create_insured_schema.sql"));
            databasePopulator.addScript(new ClassPathResource("create_address_schema.sql"));

            DatabasePopulatorUtils.execute(databasePopulator, dataSource);

            ic.bind(DIRECTSALES_JNDI_NAME, dataSource);

        } catch (final NamingException ex) {
            System.err.println("ERROR: fail to prepare the persistence context! - " + ex);
            return false;
        }

        return true;
    }

    private boolean mountDatasourceDBContractJNDI() {
        if ((ic != null) && (dataSource != null)) { return true; }

        try {
            SimpleNamingContextBuilder.emptyActivatedContextBuilder();
            ic = new InitialContext();
            ic.createSubcontext("java:");
            ic.createSubcontext("java:jboss/");
            ic.createSubcontext("java:jboss/datasources/");

            final DriverManagerDataSource dataSource = new DriverManagerDataSource();
            dataSource.setDriverClassName("org.hsqldb.jdbcDriver");
            dataSource.setUrl("jdbc:hsqldb:mem:aname;sql.syntax_mss=true");
            dataSource.setUrl("jdbc:hsqldb:mem:aname");
            dataSource.setUsername("sa");
            dataSource.setPassword("");

            final ResourceDatabasePopulator databasePopulator = new ResourceDatabasePopulator();
            databasePopulator.setContinueOnError(true);
            databasePopulator.addScript(new ClassPathResource("create_dbcontract_schema.sql"));

            DatabasePopulatorUtils.execute(databasePopulator, dataSource);

            ic.bind(CONTRACT_JNDI_NAME, dataSource);

        } catch (final NamingException ex) {
            System.err.println("ERROR: fail to prepare the persistence context! - " + ex);
            return false;
        }

        return true;
    }
}
