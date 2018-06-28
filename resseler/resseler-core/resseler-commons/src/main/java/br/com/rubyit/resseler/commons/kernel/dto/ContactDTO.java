package br.com.rubyit.resseler.commons.kernel.dto;

import br.com.rubyit.resseler.commons.core.Email;

public class ContactDTO {

    private AddressDTO address = null;
    private PhoneDTO phone = null;
    private Email email = null;

    public AddressDTO getAddress() {
        return address;
    }

    public void setAddress(final AddressDTO address) {
        this.address = address;
    }

    public PhoneDTO getPhone() {
        return phone;
    }

    public void setPhone(final PhoneDTO phone) {
        this.phone = phone;
    }

	public Email getEmail() {
		return email;
	}

	public void setEmail(final Email email) {
		this.email = email;
	}
}
