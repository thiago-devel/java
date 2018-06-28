
package br.com.cardif.wsdirectsales;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>
 * Classe Java de paymentMethod complex type.
 * 
 * <p>
 * O seguinte fragmento do esquema especifica o conte�do esperado contido dentro
 * desta classe.
 * 
 * <pre>
 * &lt;complexType name="paymentMethod"&gt;
 *   &lt;complexContent&gt;
 *     &lt;extension base="{http://wsdirectsales.cardif.com.br/}identification"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="cardPayment" type="{http://wsdirectsales.cardif.com.br/}cardPaymentDTO" minOccurs="0"/&gt;
 *         &lt;element name="paymentType" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/extension&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "paymentMethod", propOrder = { "cardPayment", "paymentType" })
public class PaymentMethod extends Identification {

    protected CardPaymentDTO cardPayment;
    protected Integer paymentType;

    /**
     * Obt�m o valor da propriedade cardPayment.
     * 
     * @return possible object is {@link CardPaymentDTO }
     * 
     */
    public CardPaymentDTO getCardPayment() {
        return cardPayment;
    }

    /**
     * Define o valor da propriedade cardPayment.
     * 
     * @param value
     *            allowed object is {@link CardPaymentDTO }
     * 
     */
    public void setCardPayment(CardPaymentDTO value) {
        this.cardPayment = value;
    }

    /**
     * Obt�m o valor da propriedade paymentType.
     * 
     * @return possible object is {@link Integer }
     * 
     */
    public Integer getPaymentType() {
        return paymentType;
    }

    /**
     * Define o valor da propriedade paymentType.
     * 
     * @param value
     *            allowed object is {@link Integer }
     * 
     */
    public void setPaymentType(Integer value) {
        this.paymentType = value;
    }

}
