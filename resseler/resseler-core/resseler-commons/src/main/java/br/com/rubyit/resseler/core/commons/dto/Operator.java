package br.com.rubyit.resseler.core.commons.dto;

/**
 * Class Operator
 * @author b11527
 *
 */
public class Operator extends SystemPerson {

    protected String operatorName;

    /**
     * the operatorName
     * @return the operatorName
     */
    public String getOperatorName() {
        return operatorName;
    }

    /**
     * the operatorName to set
     * @param operatorName 
     */
    public void setOperatorName(String operatorName) {
        this.operatorName = operatorName;
    }

}
