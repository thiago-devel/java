package br.com.rubyit.resseler.core.business.sale.impl;

import static br.com.resseler.directsales.commons.persistence.dao.util.CustomerSalesConstants.PRODUCT_CARTAO_SUPERPROTEGIDO;
import static br.com.resseler.directsales.commons.persistence.dao.util.CustomerSalesConstants.PRODUCT_PROTECAO_PREMIADA;
import static br.com.rubyit.resseler.core.business.validators.Validations.isEmpty;
import static br.com.rubyit.resseler.core.business.validators.ValidatorBusiness.creditCardPaymentEncriptedValidation;
import static br.com.rubyit.resseler.core.business.validators.ValidatorBusiness.creditCardPaymentValidation;
import static br.com.rubyit.resseler.core.commons.exceptions.ExceptionsConstants.MSG_1_CERTIFICATE_ALREADY_EXISTS_FOR_CPF;
import static br.com.rubyit.resseler.core.commons.exceptions.ExceptionsConstants.MSG_3_CERTIFICATE_ALREADY_EXISTS_FOR_CPF;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;

import br.com.resseler.directsales.sales.repository.RepositorySales;
import br.com.rubyit.resseler.commons.kernel.dto.ProductDTO;
import br.com.rubyit.resseler.core.business.CertificateBusiness;
import br.com.rubyit.resseler.core.business.ConverterUtil;
import br.com.rubyit.resseler.core.business.sale.SaleBusiness;
import br.com.rubyit.resseler.core.business.validators.ValidCNP;
import br.com.rubyit.resseler.core.business.validators.ValidatorBusiness;
import br.com.rubyit.resseler.core.commons.dto.CommissionsPagingDTO;
import br.com.rubyit.resseler.core.commons.dto.InsuranceCertificate;
import br.com.rubyit.resseler.core.commons.dto.Regional;
import br.com.rubyit.resseler.core.commons.dto.Sale;
import br.com.rubyit.resseler.core.commons.dto.Salesman;
import br.com.rubyit.resseler.core.commons.dto.SalesmanScore;
import br.com.rubyit.resseler.core.commons.dto.ScorePagingDTO;
import br.com.rubyit.resseler.core.commons.dto.SystemPerson;
import br.com.rubyit.resseler.core.commons.dto.Workshop;
import br.com.rubyit.resseler.core.commons.exceptions.BusinessException;
import br.com.rubyit.resseler.core.commons.exceptions.CheckPaymentMethodException;
import br.com.rubyit.resseler.core.commons.exceptions.ExceptionsConstants;
import br.com.rubyit.resseler.core.commons.exceptions.InsuranceCertificateException;
import br.com.rubyit.resseler.core.commons.exceptions.RawException;
import br.com.rubyit.resseler.core.commons.exceptions.UpdatePaymentMethodException;
import br.com.rubyit.resseler.core.commons.exceptions.ValidationException;
import br.com.rubyit.resseler.core.enums.ErrorType;

public class SaleBusinessImpl implements SaleBusiness {

    private static final int PAGE_SIZE_COMISSIONS = 10;
    private static final int PAGE_SIZE_PERFORMANCE = 1000;
    private static final Logger LOG = LogManager.getLogger(SaleBusinessImpl.class);
    private static final int PROTECAO_PREMIADA_CERTIFICATES_LIMIT = 3;

    @Autowired
    private RepositorySales repositorySales;

    @Autowired
    private CertificateBusiness certificateBusiness;

    /**
     * Default Constructor for DI
     */
    public SaleBusinessImpl() {
        // do nothing. DI Injection point
    }

    public SaleBusinessImpl(final RepositorySales repositorySales) {
        this.repositorySales = repositorySales;
    }

    @Override
    public InsuranceCertificate doSale(final Sale sale) {
        // Inicia os procedimentos de validação
        try {
            validateMandatoryFields(sale);
        } catch (final RawException ex) {
            LOG.error(ex);
            throw new ValidationException(ex.getMessage(), ex, ex.getCode(),
                    ex.getErrorType());
        }
        final InsuranceCertificate insuranceCertificate = repositorySales
                .saveSale(sale);

        return insuranceCertificate;
    }

    // #TRATAR NA FASE 3
    @edu.umd.cs.findbugs.annotations.SuppressFBWarnings(value = {
            "squid:MethodCyclomaticComplexity" }, justification = "A complexiddade deste metodo se deve "
                    + "ao processo de validacao envolvido. As possiveis refatoracoes ja foram "
                    + "implementadadas e portanto o codigo sera mantido.")
    protected void validateMandatoryFields(final Sale sale) {

        ValidatorBusiness.validatePersonDocuments(sale.getCustomer());
        ValidatorBusiness.validatePersonFullName(sale.getCustomer());

        Long productID = null;
        if (sale.getProduct() != null) {
            productID = sale.getProduct().getID();
        }
        if ((productID == null)
                || ((!PRODUCT_CARTAO_SUPERPROTEGIDO.equals(productID))
                        && (!PRODUCT_PROTECAO_PREMIADA
                                .equals(productID)))) { throw new ValidationException(
                                        "O código de produto informado não é valido! productID=["
                                                + productID + "]",
                                        ExceptionsConstants.CODE_PRODUTO_INVALIDO,
                                        ErrorType.VALIDATION); }

        final String cpf = sale.getCustomer().getIdentity().getDocumentValue();

        try {

            final Integer quantityCertificates = repositorySales
                    .retrieveInsuranceCertificatesByCustomerIdentificationAndProduct(
                            cpf, productID);

            Boolean exceededLimitByCpfProtecaoPremiada;


                exceededLimitByCpfProtecaoPremiada = PRODUCT_PROTECAO_PREMIADA
                        .equals(productID)
                        && (quantityCertificates >= PROTECAO_PREMIADA_CERTIFICATES_LIMIT);

                if (quantityCertificates > 0 && !PRODUCT_PROTECAO_PREMIADA
                        .equals(productID)) { throw new InsuranceCertificateException(
                                MSG_1_CERTIFICATE_ALREADY_EXISTS_FOR_CPF,
                                ExceptionsConstants.CODE_1_CERTIFICATES_CPF,
                                ErrorType.BUSINESS); }
                if (exceededLimitByCpfProtecaoPremiada) { throw new InsuranceCertificateException(
                        MSG_3_CERTIFICATE_ALREADY_EXISTS_FOR_CPF,
                        ExceptionsConstants.CODE_3_CERTIFICATES_CPF,
                        ErrorType.BUSINESS); }
        } catch (final EmptyResultDataAccessException ex) {
            LOG.error("Falha ao tentar obter os dados de cliente com CPF=["
                    + cpf + "]!", ex);
        }

        ValidatorBusiness.validatePersonMoreData(sale.getCustomer(),productID);
        
        final Integer maxInsuredAge = repositorySales.retrieveMaxInsuredAge(productID);
        
        ValidatorBusiness.validatePersonAge(sale.getCustomer(),maxInsuredAge);
        ValidatorBusiness.validateContacts(sale.getCustomer());
        ValidatorBusiness.validatePaymentMethod(sale.getPaymentMethod(),
                productID);

    }

    @Override
    public ScorePagingDTO retrievePerformance(final ProductDTO product,
            final Regional regional, final Workshop workshop,
            final Salesman salesman, final Calendar startDate,
            final Calendar endDate, final Integer pageNumber) {

        validateMandatoryFieldsForRetriveSales(startDate, endDate);

        final List<SalesmanScore> salesmanScores = repositorySales
                .retrievePerformance(product, regional, workshop, salesman,
                        startDate, endDate);

        final List<SalesmanScore> finalListsalesmanScores = new ArrayList<>();

        Integer pageCount = 0;

        if (salesmanScores != null) {

            int totalPagina = 0;

            pageCount = salesmanScores.size() / PAGE_SIZE_PERFORMANCE;

            if ((pageCount % PAGE_SIZE_PERFORMANCE) > 0) {
                pageCount++;
            }

            for (int index = (pageNumber * PAGE_SIZE_PERFORMANCE)
                    - PAGE_SIZE_PERFORMANCE; (index < salesmanScores.size())
                            && (totalPagina < PAGE_SIZE_PERFORMANCE); index++) {

                final SalesmanScore score = salesmanScores.get(index);

                finalListsalesmanScores.add(score);
                totalPagina++;
            }

        }

        return new ScorePagingDTO(finalListsalesmanScores, pageCount);
    }

    @Override
    public CommissionsPagingDTO retrieveComissions(final Salesman salesman,
            final Integer pageNumber) {
        // Inicia os procedimentos de validação
        validateEmptySalesman(salesman);
        validateMandatoryFieldsForComissions(salesman);

        final String identificationCPF = salesman.getIdentity()
                .getDocumentValue();

        final List<InsuranceCertificate> insuranceCertificates = repositorySales
                .retrieveInsuranceCertificatesBySalesmanIdentification(
                        identificationCPF);

        final List<InsuranceCertificate> finalCertificates = new ArrayList<>();

        Integer pageCount = 0;

        if (insuranceCertificates != null) {

            int totalPagina = 0;

            pageCount = insuranceCertificates.size() / PAGE_SIZE_COMISSIONS;

            if ((pageCount % PAGE_SIZE_COMISSIONS) > 0) {
                pageCount++;
            }

            for (int index = (pageNumber * PAGE_SIZE_COMISSIONS)
                    - 10; (index < insuranceCertificates.size())
                            && (totalPagina < PAGE_SIZE_COMISSIONS); index++) {

                final InsuranceCertificate certificate = insuranceCertificates
                        .get(index);

                finalCertificates.add(certificateBusiness
                        .attributeWorkshopsToCustomerCertificate(certificate));
                totalPagina++;
            }
        }

        return new CommissionsPagingDTO(finalCertificates, pageCount);

    }

    protected void validateMandatoryFieldsForRetriveSales(
            final Calendar startDate, final Calendar endDate) {

        if (isEmpty(startDate)) { throw new ValidationException(
                "É necessário informar a data inicial.",
                ExceptionsConstants.CODE_DATAINICIAL_EMPTY,
                ErrorType.VALIDATION); }
        if (isEmpty(endDate)) { throw new ValidationException(
                "É necessário informar a data final.",
                ExceptionsConstants.CODE_DATAFIM_EMPTY, ErrorType.VALIDATION); }
    }

    protected void validateMandatoryFieldsForComissions(
            final Salesman salesman) {

        if (isEmpty(salesman.getIdentity())) { throw new ValidationException(
                "O campo identidade é obrigatório.",
                ExceptionsConstants.CODE_IDENTITY_EMPTY,
                ErrorType.VALIDATION); }

        if (isEmpty(salesman.getIdentity()
                .getDocumentValue())) { throw new ValidationException(
                        "O campo CPF é obrigatório.",
                        ExceptionsConstants.CODE_CPF_EMPTY,
                        ErrorType.VALIDATION); }

        if (isEmpty(salesman.getIdentity()
                .getDocumentType())) { throw new ValidationException(
                        "O campo tipo de documento é obrigatório.",
                        ExceptionsConstants.CODE_DOCUMENTTYPE_EMPTY,
                        ErrorType.VALIDATION); }

        if (!ValidCNP.isValidCPF(salesman.getIdentity()
                .getDocumentValue())) { throw new ValidationException(
                        "O campo CPF não é válido.",
                        ExceptionsConstants.CODE_CPF_INVALIDO,
                        ErrorType.VALIDATION); }
    }

    protected void validateEmptySalesman(final Salesman salesman) {
        if (isEmpty(salesman)) { throw new ValidationException(
                "É necessário informar os dados do vendendor.",
                ExceptionsConstants.CODE_SALESMAN_EMPTY,
                ErrorType.VALIDATION); }
    }

    @Override
    public Boolean checkUpdateMethod(final InsuranceCertificate certificate) {

        try {
            validateCertificateForCheckPaymentMethod(certificate);
        } catch (final RawException ex) {
            LOG.error(ex);
            throw new ValidationException(ex.getMessage(), ex, ex.getCode(),
                    ex.getErrorType());
        }

        final List<InsuranceCertificate> certificates = repositorySales
                .retrieveCertificateFor(certificate);
        final String contractNumber = certificate.getContractNumber();
        if ((certificates != null) && (certificates.size() == 1) && certificates
                .get(0).getContractNumber().equals(contractNumber)) {

            final Salesman salesman = repositorySales.retrieveSalesmanBy(
                    certificate.getSalesman().getIdentity().getDocumentValue());
            if ((salesman != null) && (salesman.getID() != null)) {

                try {
                    creditCardPaymentValidation(
                            certificate.getPaymentMethod().getCardPayment(),
                            certificates.get(0).getProduct().getID());
                } catch (final RawException ex) {
                    LOG.error(ex);
                    throw new ValidationException(ex.getMessage(), ex,
                            ex.getCode(), ex.getErrorType());
                }

                return true;

            }
        }

        return false;
    }

    @Override
    public void updatePaymentMethodContract(
            final InsuranceCertificate certificate,
            final String securityKeyID) {
        try {
            validateUpdatePaymentMethodContract(certificate, securityKeyID);
        } catch (final RawException ex) {
            LOG.error(ex);
            throw new ValidationException(ex.getMessage(), ex, ex.getCode(),
                    ex.getErrorType());
        }

        repositorySales.updatePaymentMethodContract(certificate, securityKeyID);
    }

    private void validateUpdatePaymentMethodContract(
            final InsuranceCertificate certificate,
            final String securityKeyID) {
        if (isEmpty(securityKeyID)) { throw new UpdatePaymentMethodException(
                "securityKeyID=[" + securityKeyID
                        + "] não pode ser nulo ou vazio.",
                ExceptionsConstants.CODE_SECURITKEY_EMPTY,
                ErrorType.VALIDATION); }

        validateCertificateForUpdatePaymentMethod(certificate);
    }

    protected void validateCertificateForCheckPaymentMethod(
            final InsuranceCertificate certificate) {
        validateCertificateContent(certificate);
    }

    protected void validateCertificateForUpdatePaymentMethod(
            final InsuranceCertificate certificate) {
        validateCertificateContent(certificate);

        creditCardPaymentEncriptedValidation(
                certificate.getPaymentMethod().getCardPayment());
    }

    private void validateCertificateContent(
            final InsuranceCertificate certificate) {
        validateCertificate(certificate);

        if (isEmpty(certificate.getPaymentMethod()
                .getCardPayment())) { throw new CheckPaymentMethodException(
                        "Dados de cartão=["
                                + certificate.getPaymentMethod()
                                        .getCardPayment()
                                + "] não podem ser nulos ou vazio.",ExceptionsConstants.CODE_CC_CREDITCARD_EMPTY,ErrorType.VALIDATION); }

        if (isEmpty(certificate.getSalesman()) || isEmpty(certificate
                .getSalesman()
                .getIdentity())) { throw new CheckPaymentMethodException(
                        "Dados de vendedor=[" + certificate.getSalesman()
                                + "] não podem ser nulos ou vazio.",ExceptionsConstants.CODE_CC_SALESMAN_EMPTY,ErrorType.VALIDATION); }
    }

    private void validateCertificate(final InsuranceCertificate certificate) {
        if (isEmpty(certificate)) { throw new CheckPaymentMethodException(
                "Certificate=[" + certificate
                        + "] não pode ser nulo ou vazio.",ExceptionsConstants.CODE_CC_CERTIFICATE_EMPTY,ErrorType.VALIDATION); }

        if (isEmpty(certificate
                .getContractNumber())) { throw new CheckPaymentMethodException(
                        "Numero de certificado=["
                                + certificate.getContractNumber()
                                + "] não pode ser nulo ou vazio.",ExceptionsConstants.CODE_CC_CONTRACT_EMPTY,ErrorType.VALIDATION); }

        if (isEmpty(certificate
                .getPaymentMethod())) { throw new CheckPaymentMethodException(
                        "Dados de forma de pagamento=["
                                + certificate.getPaymentMethod()
                                + "] não podem ser nulos ou vazio.",
                        ExceptionsConstants.CODE_CC_PAYMENT_EMPTY,ErrorType.VALIDATION); }
    }

    /**
     * Método responsavel por cadastrar um vendedor
     *
     * @param salesman
     *            Possui as informações da entidade Usuário, Pessoa e Vendedor
     */
    public void saveSalesman(final Salesman salesman) {
        ValidatorBusiness.validateSaveSalesman(salesman);
        final SystemPerson person = ConverterUtil.converterPerson(salesman);
        if (repositorySales
                .retrieveBy(person.getIdentity().getDocumentValue()) == null) {
            //salesman.getUser().setPartnerId(salesman.getPartner().getID());
            //salesman.getUser().setUserAct("A");
            repositorySales.saveSalesman(salesman, person);
        } else {
            throw new BusinessException(
                    "Não foi possível cadastrar o vendedor, já existe CPF cadastrado para: ["
                            + person.getFullName() + "].",
                    ExceptionsConstants.CODE_SALESMAN_IN_BD,
                    ErrorType.BUSINESS);
        }
    }

    /**
     * Atualizar Ramo e Filial do vendedor
     *
     * @param salesman
     */
    public void updateSalesmanBranch(final Salesman salesman) {
        ValidatorBusiness.validateUpdateSalesmanBch(salesman);
        final SystemPerson person = ConverterUtil.converterPerson(salesman);
        if (repositorySales
                .retrieveBy(person.getIdentity().getDocumentValue()) != null) {
            repositorySales.updateSalesmanBranch(salesman);
        } else {
            throw new BusinessException(
                    "CPF não encontrado =["
                            + person.getIdentity().getDocumentValue() + "].",
                    ExceptionsConstants.CODE_UP_SALESMAN_NOTFOUND,
                    ErrorType.BUSINESS);
        }
    }

    /**
     * Verificar se existe a filial para essa região
     *
     * @param salesman
     * @return código da filial
     */
    public Salesman findSalesmanBch(final Salesman salesman) {
        ValidatorBusiness.validateFindSalesmanBch(salesman);
        salesman.setBchId(repositorySales.findCodBranchForRegion(salesman));
        salesman.setSalesmanAtuBch(salesman.getSalesmanBch());
        return salesman;
    }
}
