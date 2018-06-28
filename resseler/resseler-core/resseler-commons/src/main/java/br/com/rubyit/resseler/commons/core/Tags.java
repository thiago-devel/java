package br.com.rubyit.resseler.commons.core;

import java.util.HashSet;
import java.util.Set;

public class Tags {

	private Set<String> tags = null;
	
	public Set<String> getTags() {
		if (tags == null) {
			tags = new HashSet<>();
		}
		
		return tags;
	}
}
