package br.com.rubyit.resseler.soa.bs.sales;

import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

import br.com.rubyit.resseler.commons.kernel.dto.ProductDTO;


@WebService
public class ProductsAndCategoriesBS {

	@WebMethod
	public void loadCategories(@WebParam(name="products") List<ProductDTO> products) {
		System.out.println(products);;
	}
}
