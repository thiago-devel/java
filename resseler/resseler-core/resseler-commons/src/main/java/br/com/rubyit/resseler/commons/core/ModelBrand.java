package br.com.rubyit.resseler.commons.core;

import br.com.rubyit.resseler.commons.kernel.core.Identification;

public class ModelBrand extends Identification {

	public static final String UNDEFINED_MODEL = "undefined";
	private String code = null;
	
	public ModelBrand() {
		this.setName(UNDEFINED_MODEL);
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((code == null) ? 0 : code.hashCode());
		
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
		ModelBrand other = (ModelBrand) obj;
		if (code == null) {
			if (other.code != null)
				return false;
		} else if (!code.equals(other.code))
			return false;
		
		return true;
	}

	@Override
	public String toString() {
		return "ModelBrand [code=" + code + "]";
	}
	
}
