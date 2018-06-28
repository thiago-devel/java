package br.com.rubyit.resseler.commons.core;

public class User extends Person {
	
	private Boolean online = false;

	public Boolean isOnline() {
		return online;
	}

	public void setOnline(Boolean isOneline) {
		this.online = isOneline;
	}

	@Override
	public String toString() {
		return "User [online=" + online + ", getGender()=" + getGender() + ", getNickname()=" + getNickname()
				+ ", getEmail()=" + getEmail() + ", getBirthdate()=" + getBirthdate() + ", getAddress()=" + getAddress()
				+ ", getID()=" + getID() + ", getDescription()=" + getDescription() + "]";
	}
}
