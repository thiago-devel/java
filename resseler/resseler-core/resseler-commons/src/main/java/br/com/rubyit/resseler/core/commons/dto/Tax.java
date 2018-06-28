package br.com.rubyit.resseler.core.commons.dto;

import br.com.rubyit.resseler.core.enums.TaxType;

/**
 * Class Tax
 * @author b11527
 *
 */
public class Tax {

    protected TaxType taxType;
    protected String taxValue;

    /**
     * Constructor default
     */
    public Tax() {
        super();
    }

    /**
     * Constructor with params
     * @param taxType
     * @param taxValue
     */
    public Tax(TaxType taxType, String taxValue) {
        this.taxType = taxType;
        this.taxValue = taxValue;
    }

    /**
     * the taxType
     * @return the taxType
     */
    public TaxType getTaxType() {
        return taxType;
    }

    /**
     * the taxType to set
     * @param taxType 
     */
    public void setTaxType(TaxType taxType) {
        this.taxType = taxType;
    }

    /**
     * the taxValue
     * @return the taxValue
     */
    public String getTaxValue() {
        return taxValue;
    }

    /**
     * the taxValue to set
     * @param taxValue 
     */
    public void setTaxValue(String taxValue) {
        this.taxValue = taxValue;
    }

}
