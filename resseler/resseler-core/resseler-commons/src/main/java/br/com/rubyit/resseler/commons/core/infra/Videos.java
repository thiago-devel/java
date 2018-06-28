package br.com.rubyit.resseler.commons.core.infra;

import java.util.ArrayList;
import java.util.List;

public class Videos {
	
private List<Video> videos = null;
	
	public List<Video> retrieveVideos() {
		if (videos == null) {
			videos = new ArrayList<>();
		}
		
		return videos;
	}

}
