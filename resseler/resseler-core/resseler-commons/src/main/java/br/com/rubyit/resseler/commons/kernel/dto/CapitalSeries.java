package br.com.rubyit.resseler.commons.kernel.dto;

import java.math.BigInteger;

import br.com.rubyit.resseler.commons.kernel.core.Identification;

public class CapitalSeries extends Identification implements GenericDTO {

    private BigInteger capitalNumber;

    public BigInteger getCapitalNumber() {
        return capitalNumber;
    }

    public void setCapitalNumber(BigInteger capitalNumber) {
        this.capitalNumber = capitalNumber;
    }
}
