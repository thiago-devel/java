
package br.com.cardif.wsdirectsales;

import java.math.BigDecimal;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>
 * Classe Java de insuranceCertificate complex type.
 *
 * <p>
 * O seguinte fragmento do esquema especifica o conte�do esperado contido dentro
 * desta classe.
 *
 * <pre>
 * &lt;complexType name="insuranceCertificate"&gt;
 *   &lt;complexContent&gt;
 *     &lt;extension base="{http://wsdirectsales.cardif.com.br/}certificateDTO"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="coverages" type="{http://wsdirectsales.cardif.com.br/}coverages" minOccurs="0"/&gt;
 *         &lt;element name="customer" type="{http://wsdirectsales.cardif.com.br/}customer" minOccurs="0"/&gt;
 *         &lt;element name="paymentMethod" type="{http://wsdirectsales.cardif.com.br/}paymentMethod" minOccurs="0"/&gt;
 *         &lt;element name="product" type="{http://wsdirectsales.cardif.com.br/}insuranceDTO" minOccurs="0"/&gt;
 *         &lt;element name="saleCommissionValue" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/&gt;
 *         &lt;element name="salesman" type="{http://wsdirectsales.cardif.com.br/}salesman" minOccurs="0"/&gt;
 *         &lt;element name="taxes" type="{http://wsdirectsales.cardif.com.br/}taxes" minOccurs="0"/&gt;
 *         &lt;element name="workshop" type="{http://wsdirectsales.cardif.com.br/}workshop" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/extension&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "insuranceCertificate", propOrder = { "coverages", "customer", "paymentMethod", "product",
        "saleCommissionValue", "salesman", "taxes", "workshop" })
public class InsuranceCertificate extends CertificateDTO {

    protected Coverages coverages;
    protected Customer customer;
    protected PaymentMethod paymentMethod;
    protected InsuranceDTO product;
    protected BigDecimal saleCommissionValue;
    protected Salesman salesman;
    protected Taxes taxes;
    protected Workshop workshop;

    /**
     * Obt�m o valor da propriedade coverages.
     *
     * @return possible object is {@link Coverages }
     * 
     */
    public Coverages getCoverages() {
        return coverages;
    }

    /**
     * Define o valor da propriedade coverages.
     *
     * @param value
     *            allowed object is {@link Coverages }
     * 
     */
    public void setCoverages(final Coverages value) {
        coverages = value;
    }

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
    public void setCustomer(final Customer value) {
        customer = value;
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
    public void setPaymentMethod(final PaymentMethod value) {
        paymentMethod = value;
    }

    /**
     * Obt�m o valor da propriedade product.
     *
     * @return possible object is {@link InsuranceDTO }
     * 
     */
    public InsuranceDTO getProduct() {
        return product;
    }

    /**
     * Define o valor da propriedade product.
     *
     * @param value
     *            allowed object is {@link InsuranceDTO }
     * 
     */
    public void setProduct(final InsuranceDTO value) {
        product = value;
    }

    /**
     * Obt�m o valor da propriedade saleCommissionValue.
     *
     * @return possible object is {@link BigDecimal }
     * 
     */
    public BigDecimal getSaleCommissionValue() {
        return saleCommissionValue;
    }

    /**
     * Define o valor da propriedade saleCommissionValue.
     *
     * @param value
     *            allowed object is {@link BigDecimal }
     * 
     */
    public void setSaleCommissionValue(final BigDecimal value) {
        saleCommissionValue = value;
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
    public void setSalesman(final Salesman value) {
        salesman = value;
    }

    /**
     * Obt�m o valor da propriedade taxes.
     *
     * @return possible object is {@link Taxes }
     * 
     */
    public Taxes getTaxes() {
        return taxes;
    }

    /**
     * Define o valor da propriedade taxes.
     *
     * @param value
     *            allowed object is {@link Taxes }
     * 
     */
    public void setTaxes(final Taxes value) {
        taxes = value;
    }

    /**
     * Obt�m o valor da propriedade workshop.
     *
     * @return possible object is {@link Workshop }
     * 
     */
    public Workshop getWorkshop() {
        return workshop;
    }

    /**
     * Define o valor da propriedade workshop.
     *
     * @param value
     *            allowed object is {@link Workshop }
     * 
     */
    public void setWorkshop(final Workshop value) {
        workshop = value;
    }

    @Override
    public String toString() {
        return "InsuranceCertificate [coverages=" + coverages + ", customer=" + customer + ", paymentMethod="
                + paymentMethod + ", product=" + product + ", saleCommissionValue=" + saleCommissionValue
                + ", salesman=" + salesman + ", taxes=" + taxes + ", workshop=" + workshop + ", capitalSeries="
                + capitalSeries + ", certificateCreationDate=" + certificateCreationDate
                + ", certificateValidityEndDate=" + certificateValidityEndDate + ", certificateValidityInitDate="
                + certificateValidityInitDate + ", contractNumber=" + contractNumber + ", policeNumber=" + policeNumber
                + ", description=" + description + ", id=" + id + "]";
    }

}
