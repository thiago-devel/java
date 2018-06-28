package br.com.rubyit.resseler.core.commons.dto;

import java.util.List;

/**
 * Class InsuredObject
 * @author b11527
 *
 */
public class InsuredObject {

    protected Integer ID;

    protected List<Beneficiary> beneficiaries;

    /**
     * the iD
     * @return the iD
     */
    public Integer getID() {
        return ID;
    }

    /**
     * the iD to set
     * @param iD 
     */
    public void setID(Integer iD) {
        ID = iD;
    }

    /**
     * the beneficiaries
     * @return the beneficiaries
     */
    public List<Beneficiary> getBeneficiaries() {
        return beneficiaries;
    }

    /**
     * the beneficiaries to set
     * @param beneficiaries 
     */
    public void setBeneficiaries(List<Beneficiary> beneficiaries) {
        this.beneficiaries = beneficiaries;
    }

}
