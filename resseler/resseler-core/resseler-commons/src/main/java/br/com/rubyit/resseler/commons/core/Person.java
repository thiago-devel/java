package br.com.rubyit.resseler.commons.core;

import java.time.LocalDate;

import br.com.rubyit.resseler.commons.core.AddressBuilder.Address;

public class Person extends Entity {

	private String nickname = null;
	private Email email = null;
	private Gender gender = null;
	private LocalDate birthdate = null;
	private Address address = null;

	public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public Email getEmail() {
		return email;
	}

	public void setEmail(Email email) {
		this.email = email;
	}

	public LocalDate getBirthdate() {
		return birthdate;
	}

	public void setBirthdate(LocalDate birthdate) {
		this.birthdate = birthdate;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}
}
