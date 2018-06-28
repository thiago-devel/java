package br.com.resseler.directsales.sales.repository;

import java.math.BigInteger;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import br.com.rubyit.resseler.commons.core.ProductOfTheCompany;
import br.com.rubyit.resseler.commons.kernel.dto.ProductDTO;
import br.com.rubyit.resseler.persistence.dao.ProductDAO;
import br.com.rubyit.resseler.persistence.dao.ProductOfTheCompanyDAO;

@Repository
public class ProductOfTheCompanyRepository {
	
	@Autowired
	private ProductOfTheCompanyDAO productOfTheCompanyDAO = null;
	
	//Colocar contrustores. um que recebe a dependencia e outro padrao.
	
	public Boolean save(final List<ProductOfTheCompany> products) {
		return productOfTheCompanyDAO.save(products);
	}
	
	public List<ProductOfTheCompany> retrieveListProductOfTheCompany() {
		//final Long ID = (productID == null) ? null : productID.longValue();
		//return productOfTheCompanyDAO.retrieveBy(ID);
		
		return null;
	}
}
