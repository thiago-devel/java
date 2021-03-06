package br.com.rubyit.resseler.commons.kernel.enums;

public enum PhoneType {
    RESIDENTIAL(1, "RESIDENTIAL"), MOBILE(2, "MOBILE"), BUSINESS(3, "BUSINESS");
    private Integer ID;
    private String description;

    public Integer getID() {
        return ID;
    }

    public void setID(final Integer iD) {
        ID = iD;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(final String description) {
        this.description = description;
    }

    private PhoneType(final Integer ID, final String description) {
        this.ID = ID;
        this.description = description;
    }
}
