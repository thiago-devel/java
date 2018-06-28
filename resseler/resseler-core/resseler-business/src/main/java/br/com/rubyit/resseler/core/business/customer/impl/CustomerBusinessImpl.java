package br.com.rubyit.resseler.core.business.customer.impl;

import static br.com.resseler.directsales.commons.persistence.dao.util.CustomerSalesConstants.CREDIT_CARD_PAYMENT_TYPE;
import static br.com.resseler.directsales.commons.persistence.dao.util.CustomerSalesConstants.PAYMENTMETHOD_CREDITCARD_TEF_PTBR;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import br.com.resseler.directsales.sales.repository.RepositorySales;
import br.com.rubyit.resseler.core.business.CertificateBusiness;
import br.com.rubyit.resseler.core.business.customer.CustomerBusiness;
import br.com.rubyit.resseler.core.business.validators.ValidatorBusiness;
import br.com.rubyit.resseler.core.commons.dto.Beneficiary;
import br.com.rubyit.resseler.core.commons.dto.CardPaymentDTO;
import br.com.rubyit.resseler.core.commons.dto.Customer;
import br.com.rubyit.resseler.core.commons.dto.InsuranceCertificate;
import br.com.rubyit.resseler.core.commons.dto.PaymentMethod;
import br.com.rubyit.resseler.core.commons.exceptions.ExceptionsConstants;
import br.com.rubyit.resseler.core.commons.exceptions.InsuranceCertificateException;
import br.com.rubyit.resseler.core.enums.ErrorType;
import br.com.rubyit.resseler.core.utils.ProductPlan;

/**
 * Class CustomerBusinessImpl
 * @author b11527
 *
 */
public class CustomerBusinessImpl implements CustomerBusiness {

    private static Logger LOG = LogManager.getLogger(CustomerBusinessImpl.class);
    //@Autowired
    private /*RepositoryCustomer*/Object repositoryCustomer = null;
    @Autowired
    private CertificateBusiness certificateBusiness = null;
    @Autowired
    private RepositorySales repositorySales = null;

    /**
     * Default Constructor for DI
     */
    public CustomerBusinessImpl() {
        // do nothing. DI Injection point
    }

    /**
     * Constructor with params
     * @param repositoryCustomer
     * @param certificateBusiness
     * @param repositorySales
     */
    public CustomerBusinessImpl(final /*RepositoryCustomer*/Object repositoryCustomer,
            final CertificateBusiness certificateBusiness,
            final RepositorySales repositorySales) {
        this.repositoryCustomer = repositoryCustomer;
        this.certificateBusiness = certificateBusiness;
        this.repositorySales = repositorySales;
    }

    /**
     * Método responsável pelo retorno
     * de uma lista de certificados 
     * vendidos a um determinado cliente
     * (Segunda via) 
     * @param customer
     * @return
     */
    @Override
    public List<InsuranceCertificate> retrieveCustomerSales(
            final Customer customer) {
        // Inicia os procedimentos de validação
        ValidatorBusiness.validatePersonDocuments(customer);

        final List<InsuranceCertificate> insuranceCertificates = retrieveCustomerCertificates(
                customer);

        final List<InsuranceCertificate> customerCertificates = attibuteAtributesToCustomerCertificates(
                insuranceCertificates);

        return customerCertificates;
    }

    /**
     * Método responsável pelo
     * retorno de uma lista de certificados
     * a partir de um cliente (cpf)
     * @param customer
     * @return
     */
    private List<InsuranceCertificate> retrieveCustomerCertificates(
            final Customer customer) {
        final String identificationCPF = customer.getIdentity()
                .getDocumentValue();

        final List<InsuranceCertificate> insuranceCertificates = null;/*repositoryCustomer
                .retrieveInsuranceCertificatesByCustomerIdentification(
                        identificationCPF);*/
        return insuranceCertificates;
    }

    /**
     * Método responsável por popular
     * o restante das informações do
     * certificado, como filial
     * beneficiarios, dados de pagamento,
     * cartão, plano...
     * @param insuranceCertificates
     * @return
     */
    private List<InsuranceCertificate> attibuteAtributesToCustomerCertificates(
            final List<InsuranceCertificate> insuranceCertificates) {
        List<InsuranceCertificate> certificates = null;
        if (insuranceCertificates != null) {
            certificates = new ArrayList<>(insuranceCertificates.size());
            for (final InsuranceCertificate ic : insuranceCertificates) {
                InsuranceCertificate cert = attributeWorkshopsToCustomerCertificate(ic);
                cert = attributeBeneficiariesToCertificate(cert);
                cert = attributePaymentToCustomerCertificate(cert);
                cert = attributeCardPaymentToCustomerCertificate(cert);
                cert.setPlan(ProductPlan.getInstance()
                        .getPlanByProduct(cert.getProduct()));

                certificates.add(cert);
            }
        }
        return certificates;
    }

    /**
     * Método responsável
     * em popular dados de pagamento
     * de um determinado certificado
     * @param certificate
     * @return
     */
    private InsuranceCertificate attributePaymentToCustomerCertificate(
            final InsuranceCertificate certificate) {
        final InsuranceCertificate newCertificate = certificate;
        if (CREDIT_CARD_PAYMENT_TYPE == certificate.getPaymentMethod()
                .getPaymentType()) {
            newCertificate.getPaymentMethod()
                    .setDescription(PAYMENTMETHOD_CREDITCARD_TEF_PTBR);
        }
        return newCertificate;
    }

    /**
     * Método responsável
     * em popular dados do cartão de crédito
     * de um determinado certificado
     * @param certificate
     * @return
     */
    private InsuranceCertificate attributeCardPaymentToCustomerCertificate(
            final InsuranceCertificate certificate) {
        final InsuranceCertificate newCertificate = certificate;
        final Long creditCardID = extractCreditCardID(newCertificate);
        final CardPaymentDTO cardPayment = repositorySales
                .retrieveCertificateCardPaymentData(creditCardID);
        newCertificate.getPaymentMethod().setCardPayment(cardPayment);

        return newCertificate;
    }

    /**
     * Método responsável
     * em popular dados de beneficiários
     * de um determinado certificado
     * @param certificate
     * @return
     */
    private InsuranceCertificate attributeBeneficiariesToCertificate(
            final InsuranceCertificate certificate) {
        final InsuranceCertificate newCertificate = certificate;
        if (newCertificate.getInsuredObject() != null
                && newCertificate.getInsuredObject().getID() != null) {
            final List<Beneficiary> beneficiaries = null;/*repositoryCustomer
                    .retrieveBeneficiariesBy(
                            newCertificate.getInsuredObject().getID());*/
            newCertificate.getInsuredObject().setBeneficiaries(beneficiaries);
        }
        return newCertificate;
    }

    /**
     * Método responsável
     * em extrair o ID do cartão de crédito
     * de um determinado certificado
     * @param newCertificate
     * @return
     */
    private Long extractCreditCardID(
            final InsuranceCertificate newCertificate) {
        final PaymentMethod paymentMethod = newCertificate.getPaymentMethod();
        if ((paymentMethod == null)
                || (paymentMethod.getCardPayment() == null)) {
            final String msg = "ERROR: can not attibuteAtributesToCustomerCertificates. Invalid paymentMethod to contractNumber=["
                    + newCertificate.getContractNumber() + "]";
            LOG.error(msg);
            throw new InsuranceCertificateException(msg,
                    ExceptionsConstants.CODE_PAYMENTMETHOD_NULL,
                    ErrorType.VALIDATION);
        }
        final Long creditCardID = paymentMethod.getCardPayment().getID();
        return creditCardID;
    }

    /**
     * Método responsável
     * em popular dados de região/filial
     * de um determinado certificado
     * @param cert
     * @return
     */
    private InsuranceCertificate attributeWorkshopsToCustomerCertificate(
            InsuranceCertificate cert) {
        return certificateBusiness
                .attributeWorkshopsToCustomerCertificate(cert);
    }

}
