package br.com.rubyit.resseler.persistence.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import br.com.rubyit.resseler.commons.kernel.core.Describable;
import br.com.rubyit.resseler.commons.kernel.core.Identificable;
import br.com.rubyit.resseler.persistence.dao.entity.BaseEntity;

@Entity
@Table(uniqueConstraints = {@UniqueConstraint(columnNames = "ID", name = "CNT_COMPANY_ID_UNIQUE")})
public class CompanyEntity extends BaseEntity implements Identificable, Describable {

	private String name = null;
	private String description = null;
	@Column(unique = true)
	private Long ID = null;
	
	public CompanyEntity() {
	}
	
	@Override
	public Long getID() {
		return ID;
	}
	
	@Override
	public void setID(Long ID) {
		this.ID = ID;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public void setName(String name) {
		this.name = name;
	}
	
	@Override
	public String getDescription() {
		return description;
	}

	@Override
	public void setDescription(String description) {
		this.description = description;
	}
}
