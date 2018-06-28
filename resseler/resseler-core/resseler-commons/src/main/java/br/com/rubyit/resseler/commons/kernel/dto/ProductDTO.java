package br.com.rubyit.resseler.commons.kernel.dto;

import br.com.rubyit.resseler.commons.core.ModelBrand;
import br.com.rubyit.resseler.commons.core.Price;
import br.com.rubyit.resseler.commons.core.ProductAttributes;
import br.com.rubyit.resseler.commons.kernel.core.Identification;

public class ProductDTO extends Identification {

	private ModelBrand modelBrand = null;
	private StringBuilder sbProductDetailDescription = new StringBuilder();
	private String productDetailDescription = null;
	private ProductAttributes productAttributes = null;
	private Price productPrice = null;

	public ModelBrand getModelBrand() {
		return modelBrand;
	}

	public void setModelBrand(ModelBrand modelBrand) {
		this.modelBrand = modelBrand;
	}

	public String getProductDetailDescription() {
		productDetailDescription = getSbProductDetailDescription().toString();
		
		return productDetailDescription;
	}
	
	private StringBuilder getSbProductDetailDescription() {
		if (sbProductDetailDescription == null) {
			sbProductDetailDescription = new StringBuilder();
		}
		
		return sbProductDetailDescription;
	}

	public void appendProductDetailDescription(final String description) {
		getSbProductDetailDescription().append(description);
		getProductDetailDescription();
	}

	public ProductAttributes getProductAttributes() {
		return productAttributes;
	}

	public void setProductAttributes(ProductAttributes productAttributes) {
		this.productAttributes = productAttributes;
	}

	public Price getProductPrice() {
		return productPrice;
	}

	public void setProductPrice(final Price productPrice) {
		this.productPrice = productPrice;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((modelBrand == null) ? 0 : modelBrand.hashCode());
		result = prime * result + ((productAttributes == null) ? 0 : productAttributes.hashCode());
		result = prime * result + ((productDetailDescription == null) ? 0 : productDetailDescription.hashCode());
		result = prime * result + ((productPrice == null) ? 0 : productPrice.hashCode());
		
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
		ProductDTO other = (ProductDTO) obj;
		if (modelBrand == null) {
			if (other.modelBrand != null)
				return false;
		} else if (!modelBrand.equals(other.modelBrand))
			return false;
		if (productAttributes == null) {
			if (other.productAttributes != null)
				return false;
		} else if (!productAttributes.equals(other.productAttributes))
			return false;
		if (productDetailDescription == null) {
			if (other.productDetailDescription != null)
				return false;
		} else if (!productDetailDescription.equals(other.productDetailDescription))
			return false;
		if (productPrice == null) {
			if (other.productPrice != null)
				return false;
		} else if (!productPrice.equals(other.productPrice))
			return false;
		
		return true;
	}

	@Override
	public String toString() {
		return "ProductDTO [modelBrand=" + modelBrand + ", productDetailDescription=" + productDetailDescription
				+ ", productAttributes=" + productAttributes + ", productPrice=" + productPrice + "]";
	}

}
