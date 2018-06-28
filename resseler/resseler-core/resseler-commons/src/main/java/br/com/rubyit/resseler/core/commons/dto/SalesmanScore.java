package br.com.rubyit.resseler.core.commons.dto;

import br.com.rubyit.resseler.commons.kernel.dto.ProductDTO;

/**
 * Class SalesmanScore
 * @author b11527
 *
 */
public class SalesmanScore {
    private Salesman salesman;
    private ProductDTO product;
    private Regional regional;
    private Workshop workshop;
    private Coverages coverages;
    private Taxes taxes;

    private Long activeCertificatesQty = 0l;
    private Double activeCertificatesValue = 0d;
    private Double totalCommissionValue = 0d;

    /**
     * Constructor
     */
    public SalesmanScore() {
        super();
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
     * the product
     * @return the product
     */
    public ProductDTO getProduct() {
        return product;
    }

    /**
     * the product to set
     * @param product 
     */
    public void setProduct(ProductDTO product) {
        this.product = product;
    }

    /**
     * the regional
     * @return the regional
     */
    public Regional getRegional() {
        return regional;
    }

    /**
     * the regional to set
     * @param regional 
     */
    public void setRegional(Regional regional) {
        this.regional = regional;
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
     * the coverages
     * @return the coverages
     */
    public Coverages getCoverages() {
        return coverages;
    }

    /**
     * the coverages to set
     * @param coverages 
     */
    public void setCoverages(Coverages coverages) {
        this.coverages = coverages;
    }

    /**
     * the taxes
     * @return the taxes
     */
    public Taxes getTaxes() {
        return taxes;
    }

    /**
     * the taxes to set
     * @param taxes 
     */
    public void setTaxes(Taxes taxes) {
        this.taxes = taxes;
    }

    /**
     * the activeCertificatesQty
     * @return the activeCertificatesQty
     */
    public Long getActiveCertificatesQty() {
        return activeCertificatesQty;
    }

    /**
     * the activeCertificatesQty to set
     * @param activeCertificatesQty 
     */
    public void setActiveCertificatesQty(Long activeCertificatesQty) {
        this.activeCertificatesQty = activeCertificatesQty;
    }

    /**
     * the activeCertificatesValue
     * @return the activeCertificatesValue
     */
    public Double getActiveCertificatesValue() {
        return activeCertificatesValue;
    }

    /**
     * the activeCertificatesValue to set
     * @param activeCertificatesValue 
     */
    public void setActiveCertificatesValue(Double activeCertificatesValue) {
        this.activeCertificatesValue = activeCertificatesValue;
    }

    /**
     * the totalCommissionValue
     * @return the totalCommissionValue
     */
    public Double getTotalCommissionValue() {
        return totalCommissionValue;
    }

    /**
     * the totalCommissionValue to set
     * @param totalCommissionValue 
     */
    public void setTotalCommissionValue(Double totalCommissionValue) {
        this.totalCommissionValue = totalCommissionValue;
    }

}
