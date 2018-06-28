package br.com.rubyit.resseler.commons.kernel.dto;

import br.com.rubyit.resseler.commons.kernel.enums.PhoneType;

public class PhoneDTO implements GenericDTO {

    private String phoneNumber;
    private PhoneType type;

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(final String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public PhoneType getType() {
        return type;
    }

    public void setType(final PhoneType type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "PhoneDTO [phoneNumber=" + phoneNumber + ", type=" + type + "]";
    }
}
