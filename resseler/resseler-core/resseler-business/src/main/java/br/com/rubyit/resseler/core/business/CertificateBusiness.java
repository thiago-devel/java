package br.com.rubyit.resseler.core.business;

import static br.com.resseler.directsales.commons.persistence.dao.util.CustomerSalesConstants.CAPITAL_SERIES_NBR;
import static br.com.resseler.directsales.commons.persistence.dao.util.CustomerSalesConstants.NATIONALITY_DEFAULT;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import br.com.resseler.directsales.sales.persistence.builders.CompleteCertificate;
import br.com.resseler.directsales.sales.persistence.builders.CompleteCertificateBuilder;
import br.com.resseler.directsales.sales.repository.RepositorySales;
import br.com.rubyit.resseler.core.commons.dto.Customer;
import br.com.rubyit.resseler.core.commons.dto.InsuranceCertificate;
import br.com.rubyit.resseler.core.commons.dto.Salesman;

/**
 * 
 * CertificateBusiness
 *
 */
public class CertificateBusiness {

    //@Autowired
    private /*RepositoryCustomer*/Object repositoryCustomer;
    @Autowired
    private RepositorySales repositorySales;

    /**
     * Default Constructor for DI
     */
    public CertificateBusiness() {
        // do nothing. DI Injection point
    }

    /**
     * Método responsável por complementar/preencher
     * o objeto InsuranceCertificate com informações da venda
     * @param certificate
     * @return
     */
    public InsuranceCertificate attributeWorkshopsToCustomerCertificate(
            final InsuranceCertificate certificate) {
        final InsuranceCertificate newCertificate = certificate;

        final Salesman salesman = newCertificate.getSalesman()
                .getIdentity() != null ? newCertificate.getSalesman()
                        : repositorySales.retrieveSalesmanBy(
                                newCertificate.getSalesman().getID());
        final Customer customer = null;/*repositoryCustomer
                .retrieveCustomerBy(newCertificate.getCustomer());*/
        
        final Long workshopID = salesman.getBchId();

        final Long regionID = repositorySales
                .retrieveRegionIDByWorkshopID(workshopID);

        CompleteCertificateBuilder ccBuilder = new CompleteCertificateBuilder();
        ccBuilder.setProduct(newCertificate.getProduct());
        customer.setNationality(NATIONALITY_DEFAULT);
        ccBuilder.setCustomer(customer);
        ccBuilder.setSalesman(salesman);
        ccBuilder.setWorkshopID(workshopID);
        ccBuilder.setRegionID(regionID);
        ccBuilder.setParans(null);
        ccBuilder.setCertificate(certificate);
        ccBuilder = fillCapitalSeriesData(certificate, ccBuilder);
        final CompleteCertificate ccertificate = ccBuilder.build();

        return repositorySales.completeCertificateFill(ccertificate);
    }

    /**
     * Método que complementa o attributeWorkshopsToCustomerCertificate
     * utilizado para popular/preencher os dados de capital series
     * de um InsuranceCertificate
     * @param certificate
     * @param ccBuilder
     * @return
     */
    private CompleteCertificateBuilder fillCapitalSeriesData(
            final InsuranceCertificate certificate,
            final CompleteCertificateBuilder ccBuilder) {
        final CompleteCertificateBuilder builder = ccBuilder;
        final Map<String, Object> capitalSeriesData = new HashMap<>();
        final String capitalSeries = (certificate.getCapitalSeries()
                .getCapitalNumber() == null) ? null
                        : certificate.getCapitalSeries().getCapitalNumber()
                                .toString();
        capitalSeriesData.put(CAPITAL_SERIES_NBR, capitalSeries);
        builder.setCapitalSeriesData(capitalSeriesData);

        return builder;
    }
}
