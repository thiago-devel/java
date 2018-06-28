package br.com.rubyit.resseler.core.commons.dto;

import java.math.BigDecimal;

import br.com.rubyit.resseler.commons.kernel.dto.CertificateDTO;
import br.com.rubyit.resseler.commons.kernel.dto.InsuranceDTO;

/**
 * Class InsuranceCertificate
 * @author b11527
 *
 */
public class InsuranceCertificate extends CertificateDTO {

    protected InsuranceDTO product;
    protected Customer customer;
    protected PaymentMethod paymentMethod;
    protected Salesman salesman;
    protected Workshop workshop;
    protected BigDecimal saleCommissionValue;
    protected Coverages coverages;
    protected Taxes taxes;
    protected InsuredObject insuredObject;
    protected String plan;

    /**
     * the coverages
     * @return the coverages
     */
    public Coverages getCoverages() {
        if (coverages == null) {
            coverages = new Coverages();
        }

        return coverages;
    }

    /**
     * the taxes
     * @return the taxes
     */
    public Taxes getTaxes() {
        if (taxes == null) {
            taxes = new Taxes();
        }

        return taxes;
    }

    /**
     * the product
     * @return the product
     */
    public InsuranceDTO getProduct() {
        return product;
    }

    /**
     * the product to set
     * @param product 
     */
    public void setProduct(InsuranceDTO product) {
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
     */
    public void setCustomer(Customer customer) {
        this.customer = customer;
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
     */
    public void setPaymentMethod(PaymentMethod paymentMethod) {
        this.paymentMethod = paymentMethod;
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
     */
    public void setSalesman(Salesman salesman) {
        this.salesman = salesman;
    }

    /**
     * the workshop
     * @return the workshop
     */
    public Workshop getWorkshop() {
        return workshop;
    }

    /**
     * the workshop to set
     * @param workshop 
     */
    public void setWorkshop(Workshop workshop) {
        this.workshop = workshop;
    }

    /**
     * the saleCommissionValue
     * @return the saleCommissionValue
     */
    public BigDecimal getSaleCommissionValue() {
        return saleCommissionValue;
    }

    /**
     * the saleCommissionValue to set
     * @param saleCommissionValue 
     */
    public void setSaleCommissionValue(BigDecimal saleCommissionValue) {
        this.saleCommissionValue = saleCommissionValue;
    }

    /**
     * the insuredObject
     * @return the insuredObject
     */
    public InsuredObject getInsuredObject() {
        return insuredObject;
    }

    /**
     * the insuredObject to set
     * @param insuredObject 
     */
    public void setInsuredObject(InsuredObject insuredObject) {
        this.insuredObject = insuredObject;
    }

    /**
     * the plan
     * @return the plan
     */
    public String getPlan() {
        return plan;
    }

    /**
     * the plan to set
     * @param plan 
     */
    public void setPlan(String plan) {
        this.plan = plan;
    }

    /**
     * the coverages to set
     * @param coverages 
     */
    public void setCoverages(Coverages coverages) {
        this.coverages = coverages;
    }

    /**
     * the taxes to set
     * @param taxes 
     */
    public void setTaxes(Taxes taxes) {
        this.taxes = taxes;
    }

    /**
     * toString - InsuranceCertificate.class
     */
    @Override
    public String toString() {
        return "InsuranceCertificate [product=" + product + ", customer="
                + customer + ", paymentMethod=" + paymentMethod + ", salesman="
                + salesman + ", workshop=" + workshop + ", saleCommissionValue="
                + saleCommissionValue + ", coverages=" + coverages + ", taxes="
                + taxes + "insuredObject=" + insuredObject
                + ", getPoliceNumber()=" + getPoliceNumber()
                + ", getContractNumber()=" + getContractNumber()
                + ", getCertificateValidityInitDate()="
                + getCertificateValidityInitDate()
                + ", getCertificateValidityEndDate()="
                + getCertificateValidityEndDate()
                + ", getCertificateCreationDate()="
                + getCertificateCreationDate() + ", getCapitalSeries()="
                + getCapitalSeries() + ", getID()=" + getID()
                + ", getDescription()=" + getDescription() + ", getClass()="
                + getClass() + ", hashCode()=" + hashCode() + ", toString()="
                + super.toString() + "]";
    }

}
