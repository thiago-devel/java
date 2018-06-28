package br.com.resseler.directsales.commons.persistence.dao.config;

import static br.com.resseler.directsales.commons.persistence.dao.util.CustomerSalesConstants.CONTRACT_JNDI_NAME;
import static br.com.resseler.directsales.commons.persistence.dao.util.CustomerSalesConstants.DIRECTSALES_JNDI_NAME;
import static br.com.resseler.directsales.commons.persistence.dao.util.CustomerSalesConstants.PERSISTENCE_UNIT_NAME_DIRECTSALES;

import java.util.Arrays;
import java.util.List;
import java.util.Properties;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.lookup.JndiDataSourceLookup;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@ComponentScan(basePackages = { "br.com.cardif.directsales.commons" })
@EnableTransactionManagement
public class DAOAppConfig {

    private static final List<String> COMMONS_ENTITY_PKG_SCANNING = Arrays
            .asList("br.com.cardif.directsales.commons.dao.entity");

    /**
     * 
     * @return dataSourceDirectSales
     */
    @Bean(name = "dataSourceDirectSales")
    public DataSource dataSourceDirectSales() {
        final JndiDataSourceLookup dsLookup = new JndiDataSourceLookup();
        dsLookup.setResourceRef(true);

        return dsLookup.getDataSource(DIRECTSALES_JNDI_NAME);
    }

    /**
     * 
     * @return dataSourceContract
     */
    @Bean(name = "dataSourceContract")
    public DataSource dataSourceContract() {
        final JndiDataSourceLookup dsLookup = new JndiDataSourceLookup();
        dsLookup.setResourceRef(true);

        return dsLookup.getDataSource(CONTRACT_JNDI_NAME);
    }

    /**
     * 
     * @return PlatformTransactionManager
     */
    @Bean(name = "transactionManagerDirectSales")
    public PlatformTransactionManager transactionManagerSEGP() {
        final JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(entityManagerFactoryBeanDirectSales().getObject());

        return transactionManager;
    }

    /**
     * 
     * @return PlatformTransactionManager
     */
    @Bean(name = "transactionManagerContract")
    public PlatformTransactionManager transactionManagerContract() {
        final JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(entityManagerFactoryBeanContract().getObject());

        return transactionManager;
    }

    /**
     * 
     * @return LocalContainerEntityManagerFactoryBean
     */
    @Bean(name = "entityManagerFactoryBeanDirectSales")
    public LocalContainerEntityManagerFactoryBean entityManagerFactoryBeanDirectSales() {
        final LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(dataSourceDirectSales());
        em.setPackagesToScan(COMMONS_ENTITY_PKG_SCANNING.toArray(new String[0]));
        em.setPersistenceUnitName(PERSISTENCE_UNIT_NAME_DIRECTSALES);

        final JpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        em.setJpaVendorAdapter(vendorAdapter);
        em.setJpaProperties(getAdditionalProperties());

        return em;
    }

    /**
     * 
     * @return LocalContainerEntityManagerFactoryBean
     */
    @Bean(name = "entityManagerFactoryBeanContract")
    public LocalContainerEntityManagerFactoryBean entityManagerFactoryBeanContract() {
        final LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(dataSourceContract());
        em.setPackagesToScan(COMMONS_ENTITY_PKG_SCANNING.toArray(new String[0]));
        em.setPersistenceUnitName(PERSISTENCE_UNIT_NAME_DIRECTSALES);

        final JpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        em.setJpaVendorAdapter(vendorAdapter);
        em.setJpaProperties(getAdditionalProperties());

        return em;
    }

    /**
     * 
     * @return Properties
     */
    protected Properties getAdditionalProperties() {
        final Properties properties = new Properties();

        properties.put("hibernate.transaction.manager_lookup_class",
                "org.hibernate.transaction.JBossTransactionManagerLookup");
        properties.put("hibernate.transaction.factory_class", "org.hibernate.transaction.JTATransactionFactory");

        properties.put("hibernate.dialect", "org.hibernate.dialect.SQLServerDialect");
        properties.put("show_sql", "true");

        return properties;
    }
}
