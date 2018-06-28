package br.com.rubyit.resseler.core.enums;

/**
 * Enum PaymentMethodType
 * @author b11527
 *
 */
public enum PaymentMethodType {

    CREDITCARD(8, "Cartão de Crédito TEF");

    private Integer code;
    private String description;

    /**
     * Constructor with params
     * @param code
     * @param description
     */
    private PaymentMethodType(final Integer code, final String description) {
        this.code = code;
        this.description = description;
    }

    /**
     * the code
     * @return the code
     */
    public Integer getCode() {
        return code;
    }

    /**
     * the description
     * @return the description
     */
    public String getDescription() {
        return description;
    }

}
