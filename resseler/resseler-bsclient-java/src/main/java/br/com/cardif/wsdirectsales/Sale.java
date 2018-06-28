
package br.com.cardif.wsdirectsales;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>
 * Classe Java de sale complex type.
 * 
 * <p>
 * O seguinte fragmento do esquema especifica o conte�do esperado contido dentro
 * desta classe.
 * 
 * <pre>
 * &lt;complexType name="sale"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="customer" type="{http://wsdirectsales.cardif.com.br/}customer" minOccurs="0"/&gt;
 *         &lt;element name="paymentMethod" type="{http://wsdirectsales.cardif.com.br/}paymentMethod" minOccurs="0"/&gt;
 *         &lt;element name="product" type="{http://wsdirectsales.cardif.com.br/}productDTO" minOccurs="0"/&gt;
 *         &lt;element name="salesman" type="{http://wsdirectsales.cardif.com.br/}salesman" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "sale", propOrder = { "customer", "paymentMethod", "product", "salesman" })
public class Sale {

    protected Customer customer;
    protected PaymentMethod paymentMethod;
    protected ProductDTO product;
    protected Salesman salesman;

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
     * Obt�m o valor da propriedade paymentMethod.
     * 
     * @return possible object is {@link PaymentMethod }
     * 
     */
    public PaymentMethod getPaymentMethod() {
        return paymentMethod;
    }

    /**
     * Define o valor da propriedade paymentMethod.
     * 
     * @param value
     *            allowed object is {@link PaymentMethod }
     * 
     */
    public void setPaymentMethod(PaymentMethod value) {
        this.paymentMethod = value;
    }

    /**
     * Obt�m o valor da propriedade product.
     * 
     * @return possible object is {@link ProductDTO }
     * 
     */
    public ProductDTO getProduct() {
        return product;
    }

    /**
     * Define o valor da propriedade product.
     * 
     * @param value
     *            allowed object is {@link ProductDTO }
     * 
     */
    public void setProduct(ProductDTO value) {
        this.product = value;
    }

    /**
     * Obt�m o valor da propriedade salesman.
     * 
     * @return possible object is {@link Salesman }
     * 
     */
    public Salesman getSalesman() {
        return salesman;
    }

    /**
     * Define o valor da propriedade salesman.
     * 
     * @param value
     *            allowed object is {@link Salesman }
     * 
     */
    public void setSalesman(Salesman value) {
        this.salesman = value;
    }

}
