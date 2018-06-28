package br.com.rubyit.resseler.persistence.entity;

import javax.persistence.Entity;

import br.com.rubyit.resseler.commons.kernel.core.Describable;
import br.com.rubyit.resseler.commons.kernel.core.Identificable;
import br.com.rubyit.resseler.persistence.dao.entity.BaseEntity;

@Entity
public class ModelBrandEntity extends BaseEntity implements Identificable, Describable {
	
	private String name = null;
	private String description = null;
	private String code = null;
	private Long ID = null;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
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