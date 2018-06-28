package br.com.rubyit.resseler.core.business.product.test;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import br.com.resseler.directsales.sales.repository.ProductRepository;
import br.com.rubyit.resseler.core.business.product.ProductBusiness;
import br.com.rubyit.resseler.persistence.dao.ProductDAO;

@Configuration
@ComponentScan(basePackages = { "br.com.resseler.core.business" })
public class TestBusinessProductAppConfig {

	@Bean
	public ProductDAO getProductDAO() {
		return new ProductDAO();
	}

	@Bean
	public ProductRepository getProductRepository() {
		return new ProductRepository();
	}

	@Bean
	public ProductBusiness getProductBusiness() {
		return new ProductBusiness();
	}
}
