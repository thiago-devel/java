package br.com.resseler.directsales.sales.repository;

import java.math.BigInteger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import br.com.rubyit.resseler.commons.kernel.dto.ProductDTO;
import br.com.rubyit.resseler.persistence.dao.ProductDAO;

@Repository
public class ProductRepository {
	
	@Autowired
	private ProductDAO productDAO = null;
	//Colocar contrustores. um que recebe a dependencia e outro padrao.
	public Boolean save(ProductDTO product) {
		return productDAO.saveProduct(product);
	}
	
	public ProductDTO retrieveBy(BigInteger productID) {
		final Long ID = (productID == null) ? null : productID.longValue();
		return productDAO.retrieveBy(ID);
	}
}
