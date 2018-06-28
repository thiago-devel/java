package br.com.rubyit.resseler.core.business.product;

import java.math.BigInteger;
import java.util.List;

import org.apache.commons.lang.NotImplementedException;
import org.springframework.beans.factory.annotation.Autowired;

import br.com.resseler.directsales.sales.repository.ProductRepository;
import br.com.rubyit.resseler.commons.core.Company;
import br.com.rubyit.resseler.commons.core.ProductOfTheCompany;
import br.com.rubyit.resseler.commons.kernel.dto.ProductDTO;

public class ProductBusiness {
	
	@Autowired
	private ProductRepository productRepository = null;

	//Colocar contrustores. um que recebe a dependencia e outro padrao.
	public void save(ProductDTO product) {
		productRepository.save(product);
	}
	
	public ProductDTO retrieveBy(BigInteger productID) {
		return productRepository.retrieveBy(productID);
	}

	public void saveProductsCatalogBy(Company company) {
		// TODO Auto-generated method stub
		
	}

	public List<ProductDTO> retrieveProductsCatalogBy(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	public Boolean save(List<ProductOfTheCompany> products) {
		// TODO Auto-generated method stub
		return null;
	}

}
