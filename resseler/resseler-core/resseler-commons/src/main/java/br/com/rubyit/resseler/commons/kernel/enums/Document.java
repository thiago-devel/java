package br.com.rubyit.resseler.commons.kernel.enums;

public enum Document {

    CPF("CPF"), CNPJ("CNPJ");

    private String value;

    private Document(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
