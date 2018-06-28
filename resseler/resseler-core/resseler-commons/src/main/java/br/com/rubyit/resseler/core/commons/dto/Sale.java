package br.com.rubyit.resseler.core.commons.dto;

import br.com.rubyit.resseler.commons.kernel.dto.CertificateDTO;
import br.com.rubyit.resseler.commons.kernel.dto.ProductDTO;

/**
 * Class Sale
 * @author b11527
 *
 */
public class Sale {

    protected ProductDTO product;
    protected Customer customer;
    protected Salesman salesman;
    protected PaymentMethod paymentMethod;

    /**
     * Constructor default
     * @return
     */
    public CertificateDTO doSale() {
        // TODO Auto-generated method stub
        return null;
    }

    /**
     * the product
     * @return the product
     */
    public ProductDTO getProduct() {
        return product;
    }

    /**
     * the product to set
     * @param product
     *            
     */
    public void setProduct(ProductDTO product) {
        this.product = product;
    }

    /**
     * the customer
     * @return the customer
     */
    public Customer getCustomer() {
        return customer;
    }

    /**
     * the customer to set
     * @param customer
     *            
     */
    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    /**
     * the salesman
     * @return the salesman
     */
    public Salesman getSalesman() {
        return salesman;
    }

    /**
     * the salesman to set
     * @param salesman
     *            
     */
    public void setSalesman(Salesman salesman) {
        this.salesman = salesman;
    }

    /**
     * the paymentMethod
     * @return the paymentMethod
     */
    public PaymentMethod getPaymentMethod() {
        return paymentMethod;
    }

    /**
     * the paymentMethod to set
     * @param paymentMethod
     *            
     */
    public void setPaymentMethod(PaymentMethod paymentMethod) {
        this.paymentMethod = paymentMethod;
    }
}
