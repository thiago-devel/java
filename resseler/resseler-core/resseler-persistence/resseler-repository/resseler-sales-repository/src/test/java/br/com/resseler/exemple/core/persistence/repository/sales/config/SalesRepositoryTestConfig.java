package br.com.resseler.exemple.core.persistence.repository.sales.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import br.com.resseler.directsales.sales.persistence.dao.impl.SalesDAO;
import br.com.resseler.directsales.sales.repository.RepositorySales;

@Configuration
@ComponentScan(basePackages = { "br.com.cardif.directsales.sales.persistence.dao.test.config", })
public class SalesRepositoryTestConfig {

    @Bean
    public RepositorySales retrieveRepositorySales() {
        return new RepositorySales();
    }

    @Bean
    @Primary
    public SalesDAO repositoryTestSalesDAO() {
        return new SalesDAO();
    }
}
