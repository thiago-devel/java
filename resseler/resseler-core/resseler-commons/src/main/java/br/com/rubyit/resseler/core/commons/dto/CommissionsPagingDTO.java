package br.com.rubyit.resseler.core.commons.dto;

import java.util.List;

/**
 * Class CommissionsPagingDTO
 * @author b11527
 *
 */
public class CommissionsPagingDTO {
    protected final List<InsuranceCertificate> certificates;
    protected final Integer pageCount;

    /**
     * Constructor with params
     * @param certificates
     * @param pageCount
     */
    public CommissionsPagingDTO(final List<InsuranceCertificate> certificates,
            final int pageCount) {
        this.certificates = certificates;
        this.pageCount = pageCount;
    }

    /**
     * the certificates
     * @return the certificates
     */
    public List<InsuranceCertificate> getCertificates() {
        return certificates;
    }

    /**
     * the pageCount
     * @return the pageCount
     */
    public Integer getPageCount() {
        return pageCount;
    }

}
