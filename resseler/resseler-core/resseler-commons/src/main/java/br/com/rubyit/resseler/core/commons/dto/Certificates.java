package br.com.rubyit.resseler.core.commons.dto;

import java.util.ArrayList;
import java.util.List;

/**
 * Class Certificates
 * Possui lista de certificados
 * @author b11527
 *
 */
public class Certificates {
    protected List<InsuranceCertificate> certificate;

    /**
     * the certificate
     * @return the certificate
     */
    public List<InsuranceCertificate> getCertificate() {
        if (certificate == null) {
            certificate = new ArrayList<>();
        }
        final List<InsuranceCertificate> newCertificates = certificate;
        return newCertificates;
    }

    /**
     * the certificate to set
     * @param certificate 
     */
    public void setCertificate(final List<InsuranceCertificate> certificate) {
        final List<InsuranceCertificate> newCertificates = new ArrayList<>(
                certificate);
        this.certificate = newCertificates;
    }
}
