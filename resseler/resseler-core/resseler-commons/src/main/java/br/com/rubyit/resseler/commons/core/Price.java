package br.com.rubyit.resseler.commons.core;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public class Price {
	
	private Map<String, BigDecimal> prices = null;
	
	public enum PriceTypes {
		BASE("BASE"), RETAIL("RETAIL"), WHOLESALE("WHOLESALE"), ARBITRARY("ARBITRARY");
		
		private String priceType = null;
		
		PriceTypes(final String priceType) {
			this.priceType = priceType;
		}
		
		public String priceType() {
			return priceType;
		}
	}
	
	public Map<String, BigDecimal> getPrices() {
		return retrievePrices();
	}

	public void setPrices(Map<String, BigDecimal> productPrices) {
		retrievePrices().clear();
		retrievePrices().putAll(productPrices);
	}

	public Map<String, BigDecimal> retrievePrices() {
		if (prices == null) {
			prices = new HashMap<>();
			prices.put(PriceTypes.BASE.priceType(), BigDecimal.ZERO);
			prices.put(PriceTypes.RETAIL.priceType(), BigDecimal.ZERO);
			prices.put(PriceTypes.WHOLESALE.priceType(), BigDecimal.ZERO);
			prices.put(PriceTypes.ARBITRARY.priceType(), BigDecimal.ZERO);
		}

		return prices;
	}
	
	public void putArbitraryPrice(final BigDecimal arbitaryPrice) {
		retrievePrices().put(PriceTypes.ARBITRARY.priceType(), arbitaryPrice);
	}
	
	/**
	 * retorna o valor definido pelo vendedor (Salesman)
	 * 
	 * @return
	 */
	public BigDecimal retrieveArbitraryPrice() {
		return prices.get(PriceTypes.ARBITRARY.priceType());
	}
	
	/**
	 * retorna o valor de varejo
	 * 
	 * @return
	 */
	public BigDecimal retrieveRetailPrice() {
		return prices.get(PriceTypes.RETAIL.priceType());
	}

	/**
	 * retorna o valor de atacado
	 * 
	 * @return
	 */
	public BigDecimal retrieveWholesalePrice() {
		return prices.get(PriceTypes.WHOLESALE.priceType());
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((prices == null) ? 0 : prices.hashCode());
		
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Price other = (Price) obj;
		if (prices == null) {
			if (other.prices != null)
				return false;
		} else if (!prices.equals(other.prices))
			return false;
		
		return true;
	}

	@Override
	public String toString() {
		return "Price [prices=" + prices + "]";
	}

}
