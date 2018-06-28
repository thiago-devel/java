package br.com.rubyit.resseler.commons.kernel.dto;

import java.util.Calendar;

import br.com.rubyit.resseler.commons.kernel.core.Identification;

public class CertificateDTO extends Identification implements GenericDTO {
    private String policeNumber;
    private String contractNumber;
    private Calendar certificateValidityInitDate;
    private Calendar certificateValidityEndDate;
    private Calendar certificateCreationDate;
    private CapitalSeries capitalSeries;

    public String getPoliceNumber() {
        return policeNumber;
    }

    public void setPoliceNumber(String policeNumber) {
        this.policeNumber = policeNumber;
    }

    public String getContractNumber() {
        return contractNumber;
    }

    public void setContractNumber(String contractNumber) {
        this.contractNumber = contractNumber;
    }

    public Calendar getCertificateValidityInitDate() {
        return certificateValidityInitDate;
    }

    public void setCertificateValidityInitDate(Calendar certificateValidityInitDate) {
        this.certificateValidityInitDate = certificateValidityInitDate;
    }

    public Calendar getCertificateValidityEndDate() {
        return certificateValidityEndDate;
    }

    public void setCertificateValidityEndDate(Calendar certificateValidityEndDate) {
        this.certificateValidityEndDate = certificateValidityEndDate;
    }

    public Calendar getCertificateCreationDate() {
        return certificateCreationDate;
    }

    public void setCertificateCreationDate(Calendar certificateCreationDate) {
        this.certificateCreationDate = certificateCreationDate;
    }

    public CapitalSeries getCapitalSeries() {
        return capitalSeries;
    }

    public void setCapitalSeries(CapitalSeries capitalSeries) {
        this.capitalSeries = capitalSeries;
    }
}
