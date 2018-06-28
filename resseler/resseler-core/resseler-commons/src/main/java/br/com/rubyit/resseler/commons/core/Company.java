package br.com.rubyit.resseler.commons.core;

import br.com.rubyit.resseler.commons.kernel.core.Identification;

public class Company extends Identification {

	@Override
	public String toString() {
		return "Company [getID()=" + getID() + ", getName()=" + getName() + ", getDescription()=" + getDescription()
				+ "]";
	}
	
}
