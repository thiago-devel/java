package br.com.rubyit.resseler.commons.core.infra;

import java.io.Serializable;

public class Image extends Media implements Serializable {

	private ImageType imageType = null;

	public ImageType getImageType() {
		return imageType;
	}

	public void setImageType(ImageType imageType) {
		this.imageType = imageType;
	}
	
	public enum ImageType {
		MAJOR("MAJOR"), MEDIUM_HIGH("MEDIUM HIGH"), MEDIUM("MEDIUM"), MEDIUM_LOW("MEDIUM LOW"), MINOR("MINOR");
		private String type = null;
		
		ImageType(final String type) {
			this.type = type;
		}
		
		public String type() {
			return type;
		}
	}
}
