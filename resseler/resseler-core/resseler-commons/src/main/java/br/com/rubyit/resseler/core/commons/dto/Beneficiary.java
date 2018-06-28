package br.com.rubyit.resseler.core.commons.dto;

import java.util.Calendar;

/**
 * Class Beneficiary
 * @author b11527
 *
 */
public class Beneficiary {

    private Integer ID;
    private String fullName;
    private Calendar birthDate;
    private Double percentValue;

    /**
     * Constructor default
     */
    public Beneficiary() {
        super();
    }

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
     * the fullName
     * @return the fullName
     */
    public String getFullName() {
        return fullName;
    }

    /**
     * the fullName to set
     * @param fullName 
     */
    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    /**
     * the birthDate
     * @return the birthDate
     */
    public Calendar getBirthDate() {
        return birthDate;
    }

    /**
     * the birthDate to set
     * @param birthDate 
     */
    public void setBirthDate(Calendar birthDate) {
        this.birthDate = birthDate;
    }

    /**
     * the percentValue
     * @return the percentValue
     */
    public Double getPercentValue() {
        return percentValue;
    }

    /**
     * the percentValue to set
     * @param percentValue 
     */
    public void setPercentValue(Double percentValue) {
        this.percentValue = percentValue;
    }

}
