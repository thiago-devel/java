package br.com.resseler.directsales.sales.persistence.builders;

import java.util.Map;

import br.com.rubyit.resseler.commons.kernel.dto.InsuranceDTO;
import br.com.rubyit.resseler.core.commons.dto.Customer;
import br.com.rubyit.resseler.core.commons.dto.InsuranceCertificate;
import br.com.rubyit.resseler.core.commons.dto.Salesman;

public class CompleteCertificateBuilder {

    private final CompleteCertificate object = new CompleteCertificate();

    /**
     * 
     * @param product
     * @return
     */
    public CompleteCertificateBuilder setProduct(final InsuranceDTO product) {
        object.product = product;
        return this;
    }

    /**
     * 
     * @param customer
     * @return
     */
    public CompleteCertificateBuilder setCustomer(final Customer customer) {
        object.customer = customer;
        return this;
    }

    /**
     * 
     * @param salesman
     * @return
     */
    public CompleteCertificateBuilder setSalesman(final Salesman salesman) {
        object.salesman = salesman;
        return this;
    }

    /**
     * 
     * @param workshopID
     * @return
     */
    public CompleteCertificateBuilder setWorkshopID(final Long workshopID) {
        object.workshopID = workshopID;
        return this;
    }

    /**
     * 
     * @param regionID
     * @return
     */
    public CompleteCertificateBuilder setRegionID(final Long regionID) {
        object.regionID = regionID;
        return this;
    }

    /**
     * 
     * @param parans
     * @return
     */
    public CompleteCertificateBuilder setParans(final Map<String, Object> parans) {
        object.parans = parans;
        return this;
    }

    /**
     * 
     * @param oldCertificate
     * @return
     */
    public CompleteCertificateBuilder setCertificate(final InsuranceCertificate oldCertificate) {
        object.certificate = oldCertificate;
        return this;
    }

    /**
     * 
     * @param capitalSeriesData
     * @return
     */
    public CompleteCertificateBuilder setCapitalSeriesData(final Map<String, Object> capitalSeriesData) {
        object.capitalSeriesData = capitalSeriesData;
        return this;
    }

    /**
     * 
     * @return CompleteCertificate
     */
    public CompleteCertificate build() {
        return object;
    }

}
