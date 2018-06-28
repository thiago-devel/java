package br.com.rubyit.resseler.commons.core;

import java.util.HashMap;
import java.util.Map;

import br.com.rubyit.resseler.commons.kernel.core.Identification;

public class ProductAttributes extends Identification {
	private Map<String, Object> attributes = null;
	
	public void putAttribute(final String attributeName, final Object attribute) {
		retrieveAttributes().put(attributeName, attribute);
	}
	
	public Map<String, Object> retrieveAttributes() {
		if (attributes == null) {
			attributes = new HashMap<>();
		}
		return attributes;
	}
	
	public Object retrieveAttribute(final String attributeName) {
		return retrieveAttributes().get(attributeName);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((attributes == null) ? 0 : attributes.hashCode());
		
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		ProductAttributes other = (ProductAttributes) obj;
		if (attributes == null) {
			if (other.attributes != null)
				return false;
		} else if (!attributes.equals(other.attributes))
			return false;
		
		return true;
	}

	@Override
	public String toString() {
		return "ProductAttributes [attributes=" + attributes + "]";
	}
	
}
