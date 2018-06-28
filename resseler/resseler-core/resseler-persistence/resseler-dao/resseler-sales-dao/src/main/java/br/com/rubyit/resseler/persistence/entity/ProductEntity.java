package br.com.rubyit.resseler.persistence.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import br.com.rubyit.resseler.commons.kernel.core.Describable;
import br.com.rubyit.resseler.commons.kernel.core.Identificable;
import br.com.rubyit.resseler.persistence.dao.entity.BaseEntity;

@Entity
@Table(uniqueConstraints = {@UniqueConstraint(columnNames = "ID", name = "CNT_PRODUCT_ID_UNIQUE")})
public class ProductEntity extends BaseEntity implements Identificable, Describable {

	@OneToOne
	private ModelBrandEntity modelBrand = null;
	@Lob
	@Column(length = 10000)
	private String productDetailDescription = new String();
	@Lob
	private ProductAttributesEntity productAttributes = null;
	@Lob
	@Column(length = 1000)
	private PriceEntity productPrice = null;
	private String name = null;
	private String description = null;
	private Long ID = null;

	public ProductEntity() {
	}

	public ModelBrandEntity getModelBrand() {
		return modelBrand;
	}

	public void setModelBrand(ModelBrandEntity modelBrand) {
		this.modelBrand = modelBrand;
	}

	public String getProductDetailDescription() {
		return productDetailDescription;
	}


	public void setProductDetailDescription(final String productDetailDescription) {
		this.productDetailDescription = productDetailDescription;
	}


	public ProductAttributesEntity getProductAttributes() {
		return productAttributes;
	}


	public void setProductAttributes(ProductAttributesEntity productAttributes) {
		this.productAttributes = productAttributes;
	}

	public PriceEntity getProductPrice() {
		return productPrice;
	}


	public void setProductPrice(final PriceEntity productPrice) {
		this.productPrice = productPrice;
	}

	@Override
	public Long getID() {
		return ID;
	}
	
	@Override
	public void setID(final Long ID) {
		this.ID = ID;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public void setName(final String name) {
		this.name = name;
	}

	@Override
	public String getDescription() {
		return description;
	}

	@Override
	public void setDescription(final String description) {
		this.description = description;
	}

}
