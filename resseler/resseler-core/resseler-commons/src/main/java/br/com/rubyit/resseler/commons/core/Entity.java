package br.com.rubyit.resseler.commons.core;

public abstract class Entity {

	private Long ID = null;
	private String description = null;

	public Long getID() {
		return ID;
	}

	public void setID(Long iD) {
		ID = iD;
	}

	public String getDescription() {
		return description ;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
