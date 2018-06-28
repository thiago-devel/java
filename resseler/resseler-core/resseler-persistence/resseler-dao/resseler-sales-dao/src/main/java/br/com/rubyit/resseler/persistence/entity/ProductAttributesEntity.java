package br.com.rubyit.resseler.persistence.entity;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import javax.persistence.Convert;
import javax.persistence.Entity;

import br.com.rubyit.resseler.commons.core.ProductAttributes;
import br.com.rubyit.resseler.commons.kernel.core.Identificable;
import br.com.rubyit.resseler.persistence.dao.entity.BaseEntity;
import br.com.rubyit.resseler.persistence.entity.converter.ProductAttributesMapConverter;

@Entity
public class ProductAttributesEntity extends BaseEntity implements Serializable, Identificable {

	@Convert(converter = ProductAttributesMapConverter.class)
	private Map<String, Object> attributes;
	private Long ID;

	public ProductAttributesEntity() {
	}
	
	public ProductAttributesEntity(final ProductAttributes productAtributes) {
		if (productAtributes != null) {
			setID(productAtributes.getID());
			setAttributes(productAtributes.retrieveAttributes());
		}
	}

	public Map<String, Object> getAttributes() {
		if (attributes == null) {
			attributes = new HashMap<>();
		}

		return attributes;
	}
	
	public void setAttributes(final Map<String, Object> attributes) {
		getAttributes().clear();
		getAttributes().putAll(attributes);
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
