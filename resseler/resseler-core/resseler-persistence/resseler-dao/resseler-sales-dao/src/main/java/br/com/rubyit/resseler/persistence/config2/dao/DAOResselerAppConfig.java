package br.com.rubyit.resseler.persistence.config2.dao;

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
import org.springframework.transaction.support.TransactionTemplate;

@Configuration
@ComponentScan(basePackages = { 
		"br.com.rubyit.resseler.persistence.dao"
		,"br.com.rubyit.resseler.persistence.entity.converter"
})
@EnableTransactionManagement
public class DAOResselerAppConfig {
    public static final String PERSISTENCE_UNIT_NAME_RESSELER = "PU_RESSELER";
    public static final String RESSELER_JNDI_NAME = "java:jboss/datasources/Resseler";
    public final String[] ENTITY_PKG_SCANNING = {
            "br.com.rubyit.resseler.persistence.entity" };

    @Bean
    public TransactionTemplate transactionTemplate() {
        final TransactionTemplate template = new TransactionTemplate();
        template.setTransactionManager(transactionManagerResseler());

        return template;
    }

    @Bean(name = { "dataSourceResseler" })
    public DataSource dataSourceResseler() {
        final JndiDataSourceLookup dsLookup = new JndiDataSourceLookup();
        dsLookup.setResourceRef(true);

        return dsLookup.getDataSource(RESSELER_JNDI_NAME);
    }

    @Bean(name = { "transactionManagerResseler" })
    public PlatformTransactionManager transactionManagerResseler() {
        final JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(
                entityManagerFactoryBeanResseler().getObject());

        return transactionManager;
    }

    @Bean(name = { "entityManagerFactoryBeanResseler" })
    public LocalContainerEntityManagerFactoryBean entityManagerFactoryBeanResseler() {
        final LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(dataSourceResseler());
        em.setPackagesToScan(ENTITY_PKG_SCANNING);
        em.setPersistenceUnitName(PERSISTENCE_UNIT_NAME_RESSELER);

        final JpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        em.setJpaVendorAdapter(vendorAdapter);
        em.setJpaProperties(getAdditionalProperties());

        return em;
    }

    protected Properties getAdditionalProperties() {
        final Properties properties = new Properties();

        properties.put("hibernate.transaction.manager_lookup_class",
                "org.hibernate.transaction.JBossTransactionManagerLookup");

        properties.put("hibernate.transaction.factory_class",
                "org.hibernate.transaction.JTATransactionFactory");

        properties.put("hibernate.dialect",
                "org.hibernate.dialect.SQLServerDialect");

        properties.put("show_sql", "false");

        return properties;
    }
}
