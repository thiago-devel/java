package br.com.rubyit.resseler.core.commons.dto;

import java.math.BigDecimal;

import br.com.rubyit.resseler.commons.kernel.core.Identification;
import br.com.rubyit.resseler.commons.kernel.dto.GenericDTO;

/**
 * Class Coverage
 * @author b11527
 *
 */
public class Coverage extends Identification implements GenericDTO {

    protected BigDecimal coveragePremium;
    protected BigDecimal coverageValueLimit;
    protected String description;
    protected Integer installmentLimit;

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public void setDescription(final String description) {
        this.description = description;
    }

    /**
     * @return the coveragePremium
     */
    public BigDecimal getCoveragePremium() {
        return coveragePremium;
    }

    /**
     * the coveragePremium to set
     * @param coveragePremium 
     */
    public void setCoveragePremium(BigDecimal coveragePremium) {
        this.coveragePremium = coveragePremium;
    }

    /**
     * the coverageValueLimit
     * @return the coverageValueLimit
     */
    public BigDecimal getCoverageValueLimit() {
        return coverageValueLimit;
    }

    /**
     * the coverageValueLimit to set
     * @param coverageValueLimit 
     */
    public void setCoverageValueLimit(BigDecimal coverageValueLimit) {
        this.coverageValueLimit = coverageValueLimit;
    }

    /**
     * the installmentLimit
     * @return the installmentLimit
     */
    public Integer getInstallmentLimit() {
        return installmentLimit;
    }

    /**
     * the installmentLimit to set
     * @param installmentLimit 
     */
    public void setInstallmentLimit(Integer installmentLimit) {
        this.installmentLimit = installmentLimit;
    }

}
