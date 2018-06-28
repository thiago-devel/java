package br.com.rubyit.resseler.commons.core.infra;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Images implements Serializable {

	private List<Image> images = null;
	
	public List<Image> retrieveImages() {
		if (images == null) {
			images = new ArrayList<>();
		}
		
		return images;
	}
}
