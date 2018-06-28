
package br.com.cardif.wsdirectsales;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>
 * Classe Java de retrieveCustomerRequest complex type.
 * 
 * <p>
 * O seguinte fragmento do esquema especifica o conte�do esperado contido dentro
 * desta classe.
 * 
 * <pre>
 * &lt;complexType name="retrieveCustomerRequest"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="customer" type="{http://wsdirectsales.cardif.com.br/}customer" minOccurs="0"/&gt;
 *         &lt;element name="operator" type="{http://wsdirectsales.cardif.com.br/}operator" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "retrieveCustomerRequest", propOrder = { "customer", "operator" })
public class RetrieveCustomerRequest {

    protected Customer customer;
    protected Operator operator;

    /**
     * Obt�m o valor da propriedade customer.
     * 
     * @return possible object is {@link Customer }
     * 
     */
    public Customer getCustomer() {
        return customer;
    }

    /**
     * Define o valor da propriedade customer.
     * 
     * @param value
     *            allowed object is {@link Customer }
     * 
     */
    public void setCustomer(Customer value) {
        this.customer = value;
    }

    /**
     * Obt�m o valor da propriedade operator.
     * 
     * @return possible object is {@link Operator }
     * 
     */
    public Operator getOperator() {
        return operator;
    }

    /**
     * Define o valor da propriedade operator.
     * 
     * @param value
     *            allowed object is {@link Operator }
     * 
     */
    public void setOperator(Operator value) {
        this.operator = value;
    }

}
