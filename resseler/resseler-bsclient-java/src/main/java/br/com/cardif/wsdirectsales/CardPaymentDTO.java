
package br.com.cardif.wsdirectsales;

import java.math.BigDecimal;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;

/**
 * <p>
 * Classe Java de cardPaymentDTO complex type.
 * 
 * <p>
 * O seguinte fragmento do esquema especifica o conte�do esperado contido dentro
 * desta classe.
 * 
 * <pre>
 * &lt;complexType name="cardPaymentDTO"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="cardDisplayName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="cardFlag" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="cardNumber" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="cardSecurityCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="cardValidity" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/&gt;
 *         &lt;element name="cardValue" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "cardPaymentDTO", propOrder = { "cardDisplayName", "cardFlag", "cardNumber", "cardSecurityCode",
        "cardValidity", "cardValue" })
public class CardPaymentDTO {

    protected String cardDisplayName;
    protected String cardFlag;
    protected String cardNumber;
    protected String cardSecurityCode;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar cardValidity;
    protected BigDecimal cardValue;

    /**
     * Obt�m o valor da propriedade cardDisplayName.
     * 
     * @return possible object is {@link String }
     * 
     */
    public String getCardDisplayName() {
        return cardDisplayName;
    }

    /**
     * Define o valor da propriedade cardDisplayName.
     * 
     * @param value
     *            allowed object is {@link String }
     * 
     */
    public void setCardDisplayName(String value) {
        this.cardDisplayName = value;
    }

    /**
     * Obt�m o valor da propriedade cardFlag.
     * 
     * @return possible object is {@link String }
     * 
     */
    public String getCardFlag() {
        return cardFlag;
    }

    /**
     * Define o valor da propriedade cardFlag.
     * 
     * @param value
     *            allowed object is {@link String }
     * 
     */
    public void setCardFlag(String value) {
        this.cardFlag = value;
    }

    /**
     * Obt�m o valor da propriedade cardNumber.
     * 
     * @return possible object is {@link String }
     * 
     */
    public String getCardNumber() {
        return cardNumber;
    }

    /**
     * Define o valor da propriedade cardNumber.
     * 
     * @param value
     *            allowed object is {@link String }
     * 
     */
    public void setCardNumber(String value) {
        this.cardNumber = value;
    }

    /**
     * Obt�m o valor da propriedade cardSecurityCode.
     * 
     * @return possible object is {@link String }
     * 
     */
    public String getCardSecurityCode() {
        return cardSecurityCode;
    }

    /**
     * Define o valor da propriedade cardSecurityCode.
     * 
     * @param value
     *            allowed object is {@link String }
     * 
     */
    public void setCardSecurityCode(String value) {
        this.cardSecurityCode = value;
    }

    /**
     * Obt�m o valor da propriedade cardValidity.
     * 
     * @return possible object is {@link XMLGregorianCalendar }
     * 
     */
    public XMLGregorianCalendar getCardValidity() {
        return cardValidity;
    }

    /**
     * Define o valor da propriedade cardValidity.
     * 
     * @param value
     *            allowed object is {@link XMLGregorianCalendar }
     * 
     */
    public void setCardValidity(XMLGregorianCalendar value) {
        this.cardValidity = value;
    }

    /**
     * Obt�m o valor da propriedade cardValue.
     * 
     * @return possible object is {@link BigDecimal }
     * 
     */
    public BigDecimal getCardValue() {
        return cardValue;
    }

    /**
     * Define o valor da propriedade cardValue.
     * 
     * @param value
     *            allowed object is {@link BigDecimal }
     * 
     */
    public void setCardValue(BigDecimal value) {
        this.cardValue = value;
    }

}
