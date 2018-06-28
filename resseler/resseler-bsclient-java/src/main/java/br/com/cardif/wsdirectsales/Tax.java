
package br.com.cardif.wsdirectsales;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>
 * Classe Java de tax complex type.
 * 
 * <p>
 * O seguinte fragmento do esquema especifica o conte�do esperado contido dentro
 * desta classe.
 * 
 * <pre>
 * &lt;complexType name="tax"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="taxType" type="{http://wsdirectsales.cardif.com.br/}taxType" minOccurs="0"/&gt;
 *         &lt;element name="taxValue" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "tax", propOrder = { "taxType", "taxValue" })
public class Tax {

    @XmlSchemaType(name = "string")
    protected TaxType taxType;
    protected String taxValue;

    /**
     * Obt�m o valor da propriedade taxType.
     * 
     * @return possible object is {@link TaxType }
     * 
     */
    public TaxType getTaxType() {
        return taxType;
    }

    /**
     * Define o valor da propriedade taxType.
     * 
     * @param value
     *            allowed object is {@link TaxType }
     * 
     */
    public void setTaxType(TaxType value) {
        this.taxType = value;
    }

    /**
     * Obt�m o valor da propriedade taxValue.
     * 
     * @return possible object is {@link String }
     * 
     */
    public String getTaxValue() {
        return taxValue;
    }

    /**
     * Define o valor da propriedade taxValue.
     * 
     * @param value
     *            allowed object is {@link String }
     * 
     */
    public void setTaxValue(String value) {
        this.taxValue = value;
    }

}
