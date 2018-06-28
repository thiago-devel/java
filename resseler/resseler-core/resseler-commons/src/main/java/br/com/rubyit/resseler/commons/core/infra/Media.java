package br.com.rubyit.resseler.commons.core.infra;

import java.awt.Dimension;

public class Media {

	private Long id = null;
	private String path = null;
	private Dimension dimension = null;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public Dimension getDimension() {
		return dimension;
	}

	public void setDimension(Dimension dimension) {
		this.dimension = dimension;
	}
}
