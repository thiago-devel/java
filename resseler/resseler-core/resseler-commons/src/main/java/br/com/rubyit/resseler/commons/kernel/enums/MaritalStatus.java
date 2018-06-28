package br.com.rubyit.resseler.commons.kernel.enums;

public enum MaritalStatus {

    MARRIED("Married"), SINGLE("Single");

    private String value;

    private MaritalStatus(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
