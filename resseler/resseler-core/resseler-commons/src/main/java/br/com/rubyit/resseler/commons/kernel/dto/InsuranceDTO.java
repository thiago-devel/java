package br.com.rubyit.resseler.commons.kernel.dto;

import java.math.BigDecimal;

import br.com.rubyit.resseler.commons.kernel.enums.InsuranceStatus;

public class InsuranceDTO extends ProductDTO {

    private BigDecimal premiumValue;
    private InsuranceStatus status;

    public BigDecimal getPremiumValue() {
        return premiumValue;
    }

    public void setPremiumValue(BigDecimal premiumValue) {
        this.premiumValue = premiumValue;
    }

    public InsuranceStatus getStatus() {
        return status;
    }

    public void setStatus(InsuranceStatus status) {
        this.status = status;
    }
}
