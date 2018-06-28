package br.com.rubyit.resseler.core.enums;

/**
 * Enum TaxType
 * @author b11527
 *
 */
public enum TaxType {
    IOF("IOF"), PROLABORE("PROLABORE");

    private String value;

    /**
     * set value
     * @param value
     */
    private TaxType(final String value) {
        this.value = value;
    }

    /**
     * the value
     * @return the value
     */
    public String getValue() {
        return value;
    }

}
