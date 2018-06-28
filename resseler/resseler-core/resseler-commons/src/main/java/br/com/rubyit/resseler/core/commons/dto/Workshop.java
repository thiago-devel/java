package br.com.rubyit.resseler.core.commons.dto;

import java.util.ArrayList;
import java.util.List;

import br.com.rubyit.resseler.commons.kernel.core.Identification;
import br.com.rubyit.resseler.commons.kernel.dto.GenericDTO;

/**
 * Class Workshop
 * @author b11527
 *
 */
public class Workshop extends Identification implements GenericDTO {

    protected Long regionalID;
    protected String branchCode;

    protected List<Salesman> sallers;

    /**
     * the sallers
     * @return the sallers
     */
    public List<Salesman> getSallers() {
        if (sallers == null) {
            sallers = new ArrayList<>();
        }

        final List<Salesman> newSallers = sallers;

        return newSallers;
    }

    /**
     * the sallers
     * @param sallers
     */
    public void setSallers(final List<Salesman> sallers) {
        final List<Salesman> newSallers = new ArrayList<>(sallers);

        this.sallers = newSallers;
    }

    /**
     * the regionalID
     * @return the regionalID
     */
    public Long getRegionalID() {
        return regionalID;
    }

    /**
     * the regionalID to set
     * @param regionalID 
     */
    public void setRegionalID(Long regionalID) {
        this.regionalID = regionalID;
    }

    /**
     * the branchCode
     * @return the branchCode
     */
    public String getBranchCode() {
        return branchCode;
    }

    /**
     * the branchCode to set
     * @param branchCode 
     */
    public void setBranchCode(String branchCode) {
        this.branchCode = branchCode;
    }

}
