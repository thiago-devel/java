package br.com.rubyit.resseler.core.commons.dto;

import br.com.rubyit.resseler.commons.kernel.dto.Stock;

public class Salesman extends Operator {

    private Long personId = null;
    private Integer salesmanBch = null;
    private Integer salesmanAtuBch = null;
    private Long bchId = null;
    private String salesmanCod = null;
    private String regionName = null;
    private Stock stock = null;

    /**
     * Constructor default
     */
    public Salesman() {
        super();
    }

    /**
     * the personId
     * 
     * @return the personId
     */
    public Long getPersonId() {
        return personId;
    }

    /**
     * the personId to set
     * 
     * @param personId
     */
    public void setPersonId(final Long personId) {
        this.personId = personId;
    }

    /**
     * the salesmanBch
     * 
     * @return the salesmanBch
     */
    public Integer getSalesmanBch() {
        return salesmanBch;
    }

    /**
     * the salesmanBch to set
     * 
     * @param salesmanBch
     */
    public void setSalesmanBch(final Integer salesmanBch) {
        this.salesmanBch = salesmanBch;
    }

    /**
     * the salesmanAtuBch
     * 
     * @return the salesmanAtuBch
     */
    public Integer getSalesmanAtuBch() {
        return salesmanAtuBch;
    }

    /**
     * the salesmanAtuBch to set
     * 
     * @param salesmanAtuBch
     */
    public void setSalesmanAtuBch(final Integer salesmanAtuBch) {
        this.salesmanAtuBch = salesmanAtuBch;
    }

    /**
     * the bchId
     * 
     * @return the bchId
     */
    public Long getBchId() {
        return bchId;
    }

    /**
     * the bchId to set
     * 
     * @param bchId
     */
    public void setBchId(final Long bchId) {
        this.bchId = bchId;
    }

    /**
     * the salesmanCod
     * 
     * @return the salesmanCod
     */
    public String getSalesmanCod() {
        return salesmanCod;
    }

    /**
     * the salesmanCod to set
     * 
     * @param salesmanCod
     */
    public void setSalesmanCod(final String salesmanCod) {
        this.salesmanCod = salesmanCod;
    }

    /**
     * @return the regionName
     */
    public String getRegionName() {
        return regionName;
    }

    /**
     * @param regionName
     *            the regionName to set
     */
    public void setRegionName(final String regionName) {
        this.regionName = regionName;
    }

	public Stock getStock() {
		return stock;
	}

	public void setStock(final Stock stock) {
		this.stock = stock;
	}
}
