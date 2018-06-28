package br.com.rubyit.resseler.persistence.entity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToOne;

import br.com.rubyit.resseler.persistence.dao.entity.BaseEntity;

@Entity
public class ProductOfTheCompanyEntity extends BaseEntity {

	@OneToOne(cascade = CascadeType.ALL)
	private ProductEntity product = null;
	@OneToOne(cascade = CascadeType.ALL)
	private CategoryEntity category = null;
	@OneToOne(cascade = CascadeType.ALL)
	private BrandEntity brand = null;
	@OneToOne(cascade = CascadeType.ALL)
	private CompanyEntity company = null;

	public ProductOfTheCompanyEntity() {

	}

	public ProductEntity getProduct() {
		return product;
	}

	public void setProduct(ProductEntity product) {
		this.product = product;
	}

	public CategoryEntity getCategory() {
		return category;
	}

	public void setCategory(CategoryEntity category) {
		this.category = category;
	}

	public BrandEntity getBrand() {
		return brand;
	}

	public void setBrand(BrandEntity brand) {
		this.brand = brand;
	}

	public CompanyEntity getCompany() {
		return company;
	}

	public void setCompany(CompanyEntity company) {
		this.company = company;
	}
}
