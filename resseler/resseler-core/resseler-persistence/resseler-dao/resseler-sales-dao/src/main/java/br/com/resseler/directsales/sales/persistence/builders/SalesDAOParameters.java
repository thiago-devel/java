package br.com.resseler.directsales.sales.persistence.builders;

import java.util.Map;

import br.com.rubyit.resseler.commons.kernel.dto.InsuranceDTO;
import br.com.rubyit.resseler.core.commons.dto.Customer;
import br.com.rubyit.resseler.core.commons.dto.PaymentMethod;
import br.com.rubyit.resseler.core.commons.dto.Salesman;

public class SalesDAOParameters {

    protected InsuranceDTO product;
    protected String certificateNumber;
    protected Long creditCardID;
    protected Map<String, Object> capitalSeriesData;
    protected Customer customer;
    protected Salesman salesman;
    protected PaymentMethod paymentMethod;
    protected Long workshopID;
    protected Long regionID;
    protected String workshopCode;
	/**
	 * @return the product
	 */
	public InsuranceDTO getProduct() {
		return product;
	}
	/**
	 * @return the certificateNumber
	 */
	public String getCertificateNumber() {
		return certificateNumber;
	}
	/**
	 * @return the creditCardID
	 */
	public Long getCreditCardID() {
		return creditCardID;
	}
	/**
	 * @return the capitalSeriesData
	 */
	public Map<String, Object> getCapitalSeriesData() {
		return capitalSeriesData;
	}
	/**
	 * @return the customer
	 */
	public Customer getCustomer() {
		return customer;
	}
	/**
	 * @return the salesman
	 */
	public Salesman getSalesman() {
		return salesman;
	}
	/**
	 * @return the paymentMethod
	 */
	public PaymentMethod getPaymentMethod() {
		return paymentMethod;
	}
	/**
	 * @return the workshopID
	 */
	public Long getWorkshopID() {
		return workshopID;
	}
	/**
	 * @return the regionID
	 */
	public Long getRegionID() {
		return regionID;
	}
	/**
	 * @return the workshopCode
	 */
	public String getWorkshopCode() {
		return workshopCode;
	}

}
