package br.com.resseler.directsales.sales.persistence.builders;

import java.util.Map;

import br.com.rubyit.resseler.commons.kernel.dto.InsuranceDTO;
import br.com.rubyit.resseler.core.commons.dto.Customer;
import br.com.rubyit.resseler.core.commons.dto.PaymentMethod;
import br.com.rubyit.resseler.core.commons.dto.Salesman;

public class SalesDAOParametersBuilder {

    private final SalesDAOParameters object = new SalesDAOParameters();

    /**
     * 
     * @param product
     * @return
     */
    public SalesDAOParametersBuilder setProduct(final InsuranceDTO product) {
        object.product = product;
        return this;
    }

    /**
     * 
     * @param certificateNumber
     * @return
     */
    public SalesDAOParametersBuilder setCertificateNumber(final String certificateNumber) {
        object.certificateNumber = certificateNumber;
        return this;
    }

    /**
     * 
     * @param creditCardID
     * @return
     */
    public SalesDAOParametersBuilder setCreditCardID(final Long creditCardID) {
        object.creditCardID = creditCardID;
        return this;
    }

    /**
     * 
     * @param capitalSeriesData
     * @return
     */
    public SalesDAOParametersBuilder setCapitalSeriesData(final Map<String, Object> capitalSeriesData) {
        object.capitalSeriesData = capitalSeriesData;
        return this;
    }

    /**
     * 
     * @param customer
     * @return
     */
    public SalesDAOParametersBuilder setCustomer(final Customer customer) {
        object.customer = customer;
        return this;
    }

    /**
     * 
     * @param salesman
     * @return
     */
    public SalesDAOParametersBuilder setSalesman(final Salesman salesman) {
        object.salesman = salesman;
        return this;
    }

    /**
     * 
     * @param paymentMethod
     * @return
     */
    public SalesDAOParametersBuilder setPaymentMethod(final PaymentMethod paymentMethod) {
        object.paymentMethod = paymentMethod;
        return this;
    }

    /**
     * 
     * @param workshopID
     * @return
     */
    public SalesDAOParametersBuilder setWorkshopID(final Long workshopID) {
        object.workshopID = workshopID;
        return this;
    }

    /**
     * 
     * @param regionID
     * @return
     */
    public SalesDAOParametersBuilder setRegionID(final Long regionID) {
        object.regionID = regionID;
        return this;
    }

    /**
     * 
     * @param workshopCode
     * @return
     */
    public SalesDAOParametersBuilder setWorkshopCode(final String workshopCode) {
        object.workshopCode = workshopCode;
        return this;
    }

    /**
     * 
     * @return SalesDAOParameters
     */
    public SalesDAOParameters build() {
        return object;
    }
}
