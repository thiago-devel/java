package br.com.resseler.directsales.sales.repository.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * Configuration class to replace the Spring application-context.xml file
 * SalesRepositoryConfig
 * 
 * @author a42239
 *
 */
@Configuration
@ComponentScan(basePackages = { "br.com.cardif.directsales.sales.repository",
        "br.com.cardif.exemple.core.persistence.repository.sales", "br.com.cardif.exemple.core.persistence.dao.sales",
        "br.com.cardif.exemple.core.persistence.dao.sales.impl" })
public class SalesRepositoryConfig {
}
