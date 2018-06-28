package br.com.rubyit.resseler.commons.core.categories;

import br.com.rubyit.resseler.commons.kernel.core.Identification;

public class Category extends Identification {

	private Long rootCategoryID = null;
	private Long parentCategoryID = null;
	private Boolean isRootCategory = false;
	private Boolean hasSubCategories = false;

	public Category() {
		this.setID(0L);
	}
	
	public Category(final Long categoryID) {
		this.setID(categoryID);
	}

	public void setRootCategoryID(final Long rootCategoryId) {
		this.rootCategoryID = rootCategoryId;		
	}
	
	public Long getRootCategoryID() {
		return rootCategoryID;		
	}

	public void setParentCategoryID(final Long parentCategoryId) {
		this.parentCategoryID  = parentCategoryId;
	}
	
	public Long getParentCategoryID() {
		return parentCategoryID;
	}

	public void setRootCategory(final Boolean isRootCategory) {
		this.isRootCategory = isRootCategory;
	}
	
	public Boolean isRootCategory() {
		return isRootCategory;
	}

	public void setHasSubCategories(final Boolean hasSubCategories) {
		this.hasSubCategories  = hasSubCategories;
	}
	
	public Boolean hasSubCategories() {
		return hasSubCategories;
	}

}
