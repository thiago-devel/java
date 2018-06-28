package br.com.rubyit.resseler.commons.kernel.dto;

import java.util.ArrayList;
import java.util.List;

public final class Stock {

	private List<ProductDTO> productsStock = null;
	
	public List<ProductDTO> retrieveProductsStock() {
		if (productsStock == null) {
			productsStock = new ArrayList<>();
		}
		
		return productsStock;
	}
}
