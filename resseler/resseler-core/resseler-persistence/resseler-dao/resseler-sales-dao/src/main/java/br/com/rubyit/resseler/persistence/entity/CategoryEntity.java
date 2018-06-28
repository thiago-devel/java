package br.com.rubyit.resseler.persistence.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import br.com.rubyit.resseler.commons.kernel.core.Describable;
import br.com.rubyit.resseler.commons.kernel.core.Identificable;
import br.com.rubyit.resseler.persistence.dao.entity.BaseEntity;

@Entity
@Table(uniqueConstraints = {@UniqueConstraint(columnNames = "ID", name = "CNT_CATEGORY_ID_UNIQUE")})
public class CategoryEntity extends BaseEntity implements Identificable, Describable {

	private String name = null;
	private String description = null;
	private Long ID = null;
	private Long rootCategoryID = null;
	private Long parentCategoryID = null;
	private Boolean isRootCategory = false;

	public CategoryEntity() {
	}

	public Long getRootCategoryID() {
		return rootCategoryID;
	}

	public void setRootCategoryID(final Long rootCategoryID) {
		this.rootCategoryID = rootCategoryID;
	}

	public Long getParentCategoryID() {
		return parentCategoryID;
	}

	public void setParentCategoryID(final Long parentCategoryID) {
		this.parentCategoryID = parentCategoryID;
	}

	public Boolean getIsRootCategory() {
		return isRootCategory;
	}

	public void setIsRootCategory(final Boolean isRootCategory) {
		this.isRootCategory = isRootCategory;
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