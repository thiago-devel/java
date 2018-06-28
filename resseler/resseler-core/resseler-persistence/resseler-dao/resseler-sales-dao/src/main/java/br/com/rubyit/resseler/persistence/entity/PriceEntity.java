package br.com.rubyit.resseler.persistence.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import javax.persistence.Convert;
import javax.persistence.Entity;

import br.com.rubyit.resseler.commons.kernel.core.Identificable;
import br.com.rubyit.resseler.persistence.dao.entity.BaseEntity;
import br.com.rubyit.resseler.persistence.entity.converter.ProductPriceMapConverter;

@Entity
public class PriceEntity extends BaseEntity implements Serializable, Identificable {
	
	@Convert(converter = ProductPriceMapConverter.class)
	private Map<String, BigDecimal> prices = null;
	private Long ID;

	public Map<String, BigDecimal> getPrices() {
		if (prices == null) {
			prices = new HashMap<>();
		}
		
		return prices;
	}

	public void setPrices(Map<String, BigDecimal> productPrices) {
		getPrices().clear();
		getPrices().putAll(productPrices);
	}
	
	@Override
	public Long getID() {
		return ID;
	}
	
	@Override
	public void setID(Long ID) {
		this.ID = ID;
	}
}
