package br.com.rubyit.resseler.commons.core;

import java.util.HashSet;
import java.util.Set;

import br.com.rubyit.resseler.commons.core.DesireBuilder.Desire;

public class Desirous extends User {

	private Set<Desire> desire = null;
	
	public Set<Desire> getDesires() {
		if (desire == null) {
			desire = new HashSet<>();
		}
		
		return desire;
	}

	@Override
	public String toString() {
		return "Desirous [desire=" + desire + ", isOnline()=" + isOnline() + ", getGender()=" + getGender()
				+ ", getNickname()=" + getNickname() + ", getEmail()=" + getEmail() + ", getBirthdate()="
				+ getBirthdate() + "]";
	}
}
