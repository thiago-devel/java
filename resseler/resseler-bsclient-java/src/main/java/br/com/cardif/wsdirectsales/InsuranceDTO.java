
package br.com.cardif.wsdirectsales;

import java.math.BigDecimal;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>
 * Classe Java de insuranceDTO complex type.
 * 
 * <p>
 * O seguinte fragmento do esquema especifica o conte�do esperado contido dentro
 * desta classe.
 * 
 * <pre>
 * &lt;complexType name="insuranceDTO"&gt;
 *   &lt;complexContent&gt;
 *     &lt;extension base="{http://wsdirectsales.cardif.com.br/}productDTO"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="premiumValue" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/&gt;
 *         &lt;element name="status" type="{http://wsdirectsales.cardif.com.br/}insuranceStatus" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/extension&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "insuranceDTO", propOrder = { "premiumValue", "status" })
public class InsuranceDTO extends ProductDTO {

    protected BigDecimal premiumValue;
    @XmlSchemaType(name = "string")
    protected InsuranceStatus status;

    /**
     * Obt�m o valor da propriedade premiumValue.
     * 
     * @return possible object is {@link BigDecimal }
     * 
     */
    public BigDecimal getPremiumValue() {
        return premiumValue;
    }

    /**
     * Define o valor da propriedade premiumValue.
     * 
     * @param value
     *            allowed object is {@link BigDecimal }
     * 
     */
    public void setPremiumValue(BigDecimal value) {
        this.premiumValue = value;
    }

    /**
     * Obt�m o valor da propriedade status.
     * 
     * @return possible object is {@link InsuranceStatus }
     * 
     */
    public InsuranceStatus getStatus() {
        return status;
    }

    /**
     * Define o valor da propriedade status.
     * 
     * @param value
     *            allowed object is {@link InsuranceStatus }
     * 
     */
    public void setStatus(InsuranceStatus value) {
        this.status = value;
    }

}
