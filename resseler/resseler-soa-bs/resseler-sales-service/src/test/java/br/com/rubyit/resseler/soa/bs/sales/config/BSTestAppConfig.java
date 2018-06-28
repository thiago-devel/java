package br.com.rubyit.resseler.soa.bs.sales.config;

import static br.com.resseler.directsales.commons.persistence.dao.util.CustomerSalesConstants.CONTRACT_JNDI_NAME;
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
import org.springframework.context.annotation.ImportResource;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.jdbc.datasource.lookup.JndiDataSourceLookup;
import org.springframework.mock.jndi.SimpleNamingContextBuilder;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import br.com.resseler.directsales.commons.persistence.dao.impl.CapitalSeriesDAO;
import br.com.resseler.directsales.commons.persistence.dao.impl.ProductInsuranceDAO;
import br.com.resseler.directsales.commons.persistence.dao.impl.SalesmanDAO;
import br.com.resseler.directsales.commons.persistence.dao.impl.UserDAO;
import br.com.resseler.directsales.sales.persistence.dao.impl.CardPaymentDAO;
import br.com.resseler.directsales.sales.persistence.dao.impl.InstallmentDAO;
import br.com.resseler.directsales.sales.persistence.dao.impl.InsuredObjectDAO;
import br.com.resseler.directsales.sales.persistence.dao.impl.SalesDBDAO;
import br.com.resseler.directsales.sales.repository.RepositorySales;
import br.com.rubyit.resseler.soa.bs.sales.ProductsAndCategoriesBS;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = { "br.com.cardif.wsdirectsales.soa.bs.sales" })
@ImportResource({ "classpath:META-INF/cxf/cxf.xml" })
public class BSTestAppConfig extends WebMvcConfigurerAdapter {

    private static final String DIRECTSALES_JNDI_NAME = "java:jboss/datasources/directSales";
    private static final String[] ENTITY_PKG_SCANNING = new String[] { "br.com.cardif.directsales.commons.dao.entity" };
    private static final List<String> COMMONS_ENTITY_PKG_SCANNING = Arrays
            .asList("br.com.cardif.directsales.commons.dao.entity");
    private InitialContext ic;
    private DriverManagerDataSource dataSource;

    @Bean
    public ProductsAndCategoriesBS getSalesBS() {
        return new ProductsAndCategoriesBS();
    }

    @Bean
    public ProductInsuranceDAO productInsuranceDAO() {
        return new ProductInsuranceDAO();
    }

    /*
    @Bean
    public CertificateDAO certificateDAO() {
        return new CertificateDAO();
    }*/

    @Bean
    public CapitalSeriesDAO capitalSeriesDAO() {
        return new CapitalSeriesDAO();
    }

    @Bean
    public RepositorySales repositorySales() {
        return new RepositorySales();
    }

    @Bean
    public UserDAO userDAO() {
        return new UserDAO();
    }

    @Bean
    public SalesDBDAO salesDBDAO() {
        return new SalesDBDAO();
    }

    @Bean
    public CardPaymentDAO cardPaymentDAO() {
        return new CardPaymentDAO();
    }

    @Bean
    public InstallmentDAO installmentDAO() {
        return new InstallmentDAO();
    }

    @Bean
    public InsuredObjectDAO insuredObjectDAO() {
        return new InsuredObjectDAO();
    }

    @Bean
    public SalesmanDAO salesmanDAO() {
        return new SalesmanDAO();
    }

    @Bean
    public DataSource dataSourceDirectSales() {
        if (!mountDatasourceDBVendasDiretaJNDIDEV()) { return null; }

        final JndiDataSourceLookup dsLookup = new JndiDataSourceLookup();
        dsLookup.setResourceRef(true);

        return dsLookup.getDataSource(DIRECTSALES_JNDI_NAME);
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactoryBeanDirectSales() {
        final HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        vendorAdapter.setGenerateDdl(true);

        final Properties properties = getAdditionalPropertiesDEV();

        final LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
        factory.setJpaVendorAdapter(vendorAdapter);
        factory.setPersistenceUnitName(PERSISTENCE_UNIT_NAME_DIRECTSALES);
        factory.setPackagesToScan(ENTITY_PKG_SCANNING);
        factory.setDataSource(dataSourceDirectSales());
        factory.setJpaProperties(properties);

        factory.afterPropertiesSet();

        return factory;
    }

    protected Properties getAdditionalPropertiesDEV() {
        final Properties properties = new Properties();

        properties.put("hibernate.dialect", "org.hibernate.dialect.SQLServerDialect");
        properties.put("show_sql", "true");

        return properties;
    }

    private boolean mountDatasourceDBVendasDiretaJNDIDEV() {
        if ((ic != null) && (dataSource != null)) { return true; }

        try {
            SimpleNamingContextBuilder.emptyActivatedContextBuilder();
            ic = new InitialContext();
            ic.createSubcontext("java:");
            ic.createSubcontext("java:jboss/");
            ic.createSubcontext("java:jboss/datasources/");

            final DriverManagerDataSource dataSourceY = new DriverManagerDataSource();
            dataSourceY.setDriverClassName("com.microsoft.sqlserver.jdbc.SQLServerDataSource");
            dataSourceY.setUrl("jdbc:sqlserver://SAOS005DB09D;DatabaseName=dbVendasDireta");
            dataSourceY.setUsername("userdes");
            dataSourceY.setPassword("userdes");

            ic.bind(DIRECTSALES_JNDI_NAME, dataSourceY);

        } catch (final NamingException ex) {
            System.err.println("ERROR: fail to prepare the persistence context! - " + ex);
            return false;
        }

        return true;
    }

    @Bean
    public DataSource dataSourceContract() {
        if (!mountDatasourceDBContractJNDIDEV()) { return null; }

        final JndiDataSourceLookup dsLookup = new JndiDataSourceLookup();
        dsLookup.setResourceRef(true);

        return dsLookup.getDataSource(CONTRACT_JNDI_NAME);
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactoryBeanContract() {
        final HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        vendorAdapter.setGenerateDdl(true);

        final Properties properties = getAdditionalPropertiesDEV();

        final LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
        factory.setJpaVendorAdapter(vendorAdapter);
        factory.setPersistenceUnitName(PERSISTENCE_UNIT_NAME_CONTRACT);
        factory.setPackagesToScan(COMMONS_ENTITY_PKG_SCANNING.toArray(new String[0]));
        factory.setDataSource(dataSourceContract());
        factory.setJpaProperties(properties);

        factory.afterPropertiesSet();

        return factory;
    }

    private boolean mountDatasourceDBContractJNDIDEV() {
        if ((ic != null) && (dataSource != null)) { return true; }

        try {
            SimpleNamingContextBuilder.emptyActivatedContextBuilder();
            ic = new InitialContext();
            ic.createSubcontext("java:");
            ic.createSubcontext("java:jboss/");
            ic.createSubcontext("java:jboss/datasources/");

            final DriverManagerDataSource dataSourceY = new DriverManagerDataSource();
            dataSourceY.setDriverClassName("com.microsoft.sqlserver.jdbc.SQLServerDataSource");
            dataSourceY.setUrl("jdbc:sqlserver://SAOS005DB09D;DatabaseName=dbContract");
            dataSourceY.setUsername("userdes");
            dataSourceY.setPassword("userdes");

            ic.bind(CONTRACT_JNDI_NAME, dataSourceY);

        } catch (final NamingException ex) {
            System.err.println("ERROR: fail to prepare the persistence context! - " + ex);
            return false;
        }

        return true;
    }
}
