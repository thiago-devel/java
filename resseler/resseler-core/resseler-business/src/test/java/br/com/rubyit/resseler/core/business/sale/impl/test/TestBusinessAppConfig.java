package br.com.rubyit.resseler.core.business.sale.impl.test;

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
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.jdbc.datasource.lookup.JndiDataSourceLookup;
import org.springframework.mock.jndi.SimpleNamingContextBuilder;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

import br.com.resseler.directsales.commons.persistence.dao.config.DAOAppConfig;
import br.com.resseler.directsales.commons.persistence.dao.impl.CapitalSeriesDAO;
import br.com.resseler.directsales.commons.persistence.dao.impl.ProductInsuranceDAO;
import br.com.resseler.directsales.commons.persistence.dao.impl.SalesmanDAO;
import br.com.resseler.directsales.commons.persistence.dao.impl.SystemPersonDAO;
import br.com.resseler.directsales.commons.persistence.dao.impl.UserDAO;
import br.com.resseler.directsales.sales.persistence.dao.impl.CardPaymentDAO;
import br.com.resseler.directsales.sales.persistence.dao.impl.InstallmentDAO;
import br.com.resseler.directsales.sales.persistence.dao.impl.InsuredObjectDAO;
import br.com.resseler.directsales.sales.persistence.dao.impl.SalesDAO;
import br.com.resseler.directsales.sales.persistence.dao.impl.SalesDBDAO;
import br.com.resseler.directsales.sales.repository.RepositorySales;
import br.com.rubyit.resseler.core.business.CertificateBusiness;
import br.com.rubyit.resseler.core.business.customer.impl.CustomerBusinessImpl;
import br.com.rubyit.resseler.core.business.sale.impl.SaleBusinessImpl;

@Configuration
@ComponentScan(basePackages = { "br.com.cardif.directsales.commons.dto" })
public class TestBusinessAppConfig extends DAOAppConfig {

    private InitialContext ic;
    private DriverManagerDataSource dataSource;
    private static final List<String> COMMONS_ENTITY_PKG_SCANNING = Arrays
            .asList("br.com.cardif.directsales.commons.dao.entity");

    @Bean
    public CustomerBusinessImpl retrieveCustomerBusinessImpl() {
        return new CustomerBusinessImpl();
    }

    @Bean
    public SaleBusinessImpl saleBusinessImpl() {
        return new SaleBusinessImpl();
    }

    @Bean
    public CertificateBusiness certificateBusiness() {
        return new CertificateBusiness();
    }

    @Bean
    public SystemPersonDAO systemPersonDAO() {
        return new SystemPersonDAO();
    }

    @Bean
    public ProductInsuranceDAO productInsuranceDAO() {
        return new ProductInsuranceDAO();
    }

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
    public SalesDAO salesDAO() {
        return new SalesDAO();
    }

    @Bean
    public SalesDBDAO salesDBDAO() {
        return new SalesDBDAO();
    }

    @Bean
    public SalesmanDAO salesmanDAO() {
        return new SalesmanDAO();
    }

    @Override
    public DataSource dataSourceDirectSales() {
        if (!mountDatasourceDBVendasDiretaJNDIDEV()) { return null; }

        final JndiDataSourceLookup dsLookup = new JndiDataSourceLookup();
        dsLookup.setResourceRef(true);

        return dsLookup.getDataSource(DIRECTSALES_JNDI_NAME);
    }

    @Override
    public LocalContainerEntityManagerFactoryBean entityManagerFactoryBeanDirectSales() {
        final HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        vendorAdapter.setGenerateDdl(true);

        final Properties properties = getAdditionalPropertiesDEV();

        final LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();

        factory.setJpaVendorAdapter(vendorAdapter);
        factory.setPersistenceUnitName(PERSISTENCE_UNIT_NAME_DIRECTSALES);
        factory.setPackagesToScan(COMMONS_ENTITY_PKG_SCANNING.toArray(new String[0]));
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
            /*dataSourceY.setUrl("jdbc:sqlserver://SAOC005DB04P;DatabaseName=dbVendasDireta");
            dataSourceY.setUsername("USERDES");
            dataSourceY.setPassword("USERDES");*/
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

    @Override
    @Bean
    public DataSource dataSourceContract() {
        if (!mountDatasourceDBContractJNDIDEV()) { return null; }

        final JndiDataSourceLookup dsLookup = new JndiDataSourceLookup();
        dsLookup.setResourceRef(true);

        return dsLookup.getDataSource(CONTRACT_JNDI_NAME);
    }

    @Override
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
