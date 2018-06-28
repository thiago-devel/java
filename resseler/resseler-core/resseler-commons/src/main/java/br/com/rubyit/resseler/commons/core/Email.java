package br.com.rubyit.resseler.commons.core;

public class Email {

	private boolean valid = false;
	private String email = null;
	
	public Email(final String email) {
		this.email = email;
	}
	public boolean isValid() {
		return valid;
	}
	public String getEmail() {
		return email;
	}
	@Override
	public String toString() {
		return "Email [valid=" + valid + ", email=" + email + "]";
	}
}
