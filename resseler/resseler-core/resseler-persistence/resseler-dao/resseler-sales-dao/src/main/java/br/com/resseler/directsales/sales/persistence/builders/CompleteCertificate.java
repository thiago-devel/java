package br.com.resseler.directsales.sales.persistence.builders;

import java.util.Map;

import br.com.rubyit.resseler.commons.kernel.dto.InsuranceDTO;
import br.com.rubyit.resseler.core.commons.dto.Customer;
import br.com.rubyit.resseler.core.commons.dto.InsuranceCertificate;
import br.com.rubyit.resseler.core.commons.dto.Salesman;

/**
 * Class CompleteCertificate
 * @author b11527
 *
 */
public class CompleteCertificate {
    protected InsuranceDTO product;
    protected Customer customer;
    protected Salesman salesman;
    protected Long workshopID;
    protected Long regionID;
    protected Map<String, Object> parans;
    protected InsuranceCertificate certificate;
    protected Map<String, Object> capitalSeriesData;

    /**
     * Constructor default
     */
    protected CompleteCertificate() {
    }

    /**
     * the product
     * @return the product
     */
    public InsuranceDTO getProduct() {
        return product;
    }

    /**
     * the customer
     * @return the customer
     */
    public Customer getCustomer() {
        return customer;
    }

    /**
     * the salesman
     * @return the salesman
     */
    public Salesman getSalesman() {
        return salesman;
    }

    /**
     * the workshopID
     * @return the workshopID
     */
    public Long getWorkshopID() {
        return workshopID;
    }

    /**
     * the regionID
     * @return the regionID
     */
    public Long getRegionID() {
        return regionID;
    }

    /**
     * the parans
     * @return the parans
     */
    public Map<String, Object> getParans() {
        return parans;
    }

    /**
     * the certificate
     * @return the certificate
     */
    public InsuranceCertificate getCertificate() {
        return certificate;
    }

    /**
     * the capitalSeriesData
     * @return the capitalSeriesData
     */
    public Map<String, Object> getCapitalSeriesData() {
        return capitalSeriesData;
    }
}
