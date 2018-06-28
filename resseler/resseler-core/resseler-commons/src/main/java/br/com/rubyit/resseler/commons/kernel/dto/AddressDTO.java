package br.com.rubyit.resseler.commons.kernel.dto;

import br.com.rubyit.resseler.commons.kernel.enums.AddressType;

public class AddressDTO {
    private String addressDetail;
    private String addressNumber;
    private String addressReference;
    private String neighborhood;
    private String city;
    private String state;
    private String addressPostalCode;
    private AddressType type;

    public String getAddressDetail() {
        return addressDetail;
    }

    public void setAddressDetail(final String addressDetail) {
        this.addressDetail = addressDetail;
    }

    public String getAddressNumber() {
        return addressNumber;
    }

    public void setAddressNumber(final String addressNumber) {
        this.addressNumber = addressNumber;
    }

    public String getAddressReference() {
        return addressReference;
    }

    public void setAddressReference(final String addressReference) {
        this.addressReference = addressReference;
    }

    public String getNeighborhood() {
        return neighborhood;
    }

    public void setNeighborhood(final String neighborhood) {
        this.neighborhood = neighborhood;
    }

    public String getCity() {
        return city;
    }

    public void setCity(final String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(final String state) {
        this.state = state;
    }

    public String getAddressPostalCode() {
        return addressPostalCode;
    }

    public void setAddressPostalCode(final String addressPostalCode) {
        this.addressPostalCode = addressPostalCode;
    }

    public AddressType getType() {
        return type;
    }

    public void setType(final AddressType type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "AddressDTO [addressDetail=" + addressDetail + ", addressNumber=" + addressNumber + ", addressReference="
                + addressReference + ", neighborhood=" + neighborhood + ", city=" + city + ", state=" + state
                + ", addressPostalCode=" + addressPostalCode + ", type=" + type + "]";
    }
}
