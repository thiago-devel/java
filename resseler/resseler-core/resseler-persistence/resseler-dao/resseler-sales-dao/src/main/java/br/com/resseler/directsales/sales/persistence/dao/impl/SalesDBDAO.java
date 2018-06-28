package br.com.resseler.directsales.sales.persistence.dao.impl;

import static br.com.resseler.directsales.commons.persistence.dao.util.CustomerSalesConstants.CARD_DATA_ID_FK;
import static br.com.resseler.directsales.commons.persistence.dao.util.CustomerSalesConstants.CONTRACT_INSERT_DATE;
import static br.com.resseler.directsales.commons.persistence.dao.util.CustomerSalesConstants.CONTRACT_NUMBER;
import static br.com.resseler.directsales.commons.persistence.dao.util.CustomerSalesConstants.CONTRACT_UPDATE_DATE;
import static br.com.resseler.directsales.commons.persistence.dao.util.CustomerSalesConstants.UPDATE_USER_DOC;
import static br.com.resseler.directsales.commons.persistence.dao.util.CustomerSalesConstants.UPDATE_USER_DOC_TYPE;
import static br.com.resseler.directsales.commons.persistence.dao.util.CustomerSalesConstants.UPDATE_USER_ID;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.transaction.annotation.Transactional;

import br.com.resseler.directsales.commons.persistence.dao.impl.CapitalSeriesDAO;
import br.com.resseler.directsales.commons.persistence.dao.impl.InsuredDAO;
import br.com.resseler.directsales.commons.persistence.dao.impl.ProductInsuranceDAO;
import br.com.resseler.directsales.commons.persistence.dao.impl.SalesmanDAO;
import br.com.resseler.directsales.commons.persistence.dao.impl.SystemPersonDAO;
import br.com.resseler.directsales.commons.persistence.dao.impl.UserDAO;
import br.com.rubyit.resseler.commons.kernel.dto.IdentityDTO;
import br.com.rubyit.resseler.commons.kernel.dto.InsuranceDTO;
import br.com.rubyit.resseler.commons.kernel.dto.LoginDTO;
import br.com.rubyit.resseler.commons.kernel.enums.InsuranceStatus;
import br.com.rubyit.resseler.commons.kernel.exception.SystemException;
import br.com.rubyit.resseler.core.commons.dto.CardPaymentDTO;
import br.com.rubyit.resseler.core.commons.dto.Customer;
import br.com.rubyit.resseler.core.commons.dto.InsuranceCertificate;
import br.com.rubyit.resseler.core.commons.dto.Salesman;
import br.com.rubyit.resseler.core.commons.dto.SystemPerson;
import br.com.rubyit.resseler.core.commons.exceptions.BusinessException;
import br.com.rubyit.resseler.core.commons.exceptions.ExceptionsConstants;
import br.com.rubyit.resseler.core.commons.exceptions.ValidationException;
import br.com.rubyit.resseler.core.enums.ErrorType;

/**
 * Sales Data Base DAO class
 *
 */
@edu.umd.cs.findbugs.annotations.SuppressFBWarnings(value = {
        "squid:S1200" }, justification = "Nivel minimo de acoplamento ja "
                + "foi atingido. Mantendo a logica planejada e com os "
                + "requisitos de negocios existentes, nao existem outras "
                + "refatoracoes possiveis.")
public class SalesDBDAO {

    private static final Logger LOG = LogManager.getLogger(SalesDBDAO.class);
    @Autowired
    protected ProductInsuranceDAO daoProduct;
    @Autowired
    protected CapitalSeriesDAO daoCapitalSeries;
    @Autowired
    protected UserDAO daoUser;
    @Autowired
    protected InsuredDAO daoInsured;
    @Autowired
    protected SystemPersonDAO daoSPerson;
    @Autowired
    protected CardPaymentDAO daoCardPayment;
    @Autowired
    protected SalesmanDAO salesmanDAO;
    @Autowired
    protected InstallmentDAO installmentDAO;
    @Autowired
    protected InsuredObjectDAO insuredObjectDAO;

    /**
     * Contructor used by IOC
     */
    public SalesDBDAO() {
        // do nothing. IOC Injection point
    }

    public SystemPerson retrieveBy(final String cpfCNPJ) {
        final List<SystemPerson> systemPersonL = daoSPerson.retrieveBy(cpfCNPJ);

        return (systemPersonL == null) ? null : systemPersonL.get(0);
    }

    public Boolean isValidUser(final String user, final String passwd) {
        return daoUser.isValidUser(user, passwd);
    }

    public Long retrieveSalesmanID(final Long personID) {
        LOG.debug("Init method retrieveSalesmanID(final Integer [" + personID
                + "])");

        Long retrievedSalesmanID = null;
        final Salesman retrievedSalesman = salesmanDAO.retrieveBy(personID);
        LOG.debug("RetrievedSalesman=[' + retrievedSalesman + ']");
        if (retrievedSalesman != null) {
            retrievedSalesmanID = retrievedSalesman.getID();
            LOG.debug(
                    "RetrievedSalesman have ID=[' + retrievedSalesmanID + ']");
        }

        LOG.debug("End method retrieveSalesmanID(final Integer [" + personID
                + "])");

        return retrievedSalesmanID;
    }

    public Long persistCustomer(final Customer customer) {
        return daoInsured.persist(customer);
    }

    public String generateContractNumberBy(final Long productID) {
        final String prefixoProduto = daoProduct
                .retrieveProductPrefix(productID);

        return null;//daoCertificate.generateContractNumberFrom(prefixoProduto);
    }

    public Map<String, Object> retrieveCapitalSeriesData(
            final String certificateNumber) {
        final String capitalSeriesID = daoCapitalSeries
                .retrieveCapitalSeriesIDFrom(certificateNumber);

        return daoCapitalSeries.retrieveCapitalSeriesData(capitalSeriesID);
    }

    public Map<String, Object> retrieveCapitalSeriesDataByCertificate(
            final String certificateNumber) {
        return daoCapitalSeries
                .retrieveCapitalSeriesDataByCertificate(certificateNumber);
    }

    public Map<String, Object> cleanOldAndRetrieveNewCapitalSeriesData(
            final String certificateNumber) {
        return daoCapitalSeries
                .cleanOldAndRetrieveNewCapitalSeriesData(certificateNumber);
    }

    public InsuranceDTO retrieveProductBy(final Long productID) {
        return daoProduct.retrieveProduct(productID);
    }

    public Integer persistCardPayment(final CardPaymentDTO cardPayment) {
        return daoCardPayment.persist(cardPayment);
    }

    public void updateCertificateStatus(final Long insuranceCertificateID,
            final InsuranceStatus status) {
        //daoCertificate.updateCertificateStatus(insuranceCertificateID, status);
    }

    public void updateCertificateStatusAndTmpSts(
            final Long insuranceCertificateID,
            final InsuranceStatus status) {
        /*daoCertificate.updateCertificateStatusAndTmpSts(insuranceCertificateID,
                status);*/
    }

    public Integer persistInsuranceCertificate(
            final InsuranceCertificate certificate,
            final Map<String, Object> parans) {
        return null;//daoCertificate.persist(certificate, parans);
    }

    public Integer updateInsuranceCertificate(
            final InsuranceCertificate certificate,
            final Map<String, Object> parans) {
        return null;//daoCertificate.update(certificate, parans);
    }

    public Integer persistInsuredObject(final InsuranceCertificate certificate,
            final Integer insuredObjectCode) {
        return insuredObjectDAO.persist(certificate, insuredObjectCode);
    }

    public Integer persistInstallment(final InsuranceCertificate certificate,
            final Integer installmentOrderNumber,
            final Map<String, Object> parans) {
        return installmentDAO.persist(certificate, installmentOrderNumber,
                parans);
    }

    public BigDecimal retrieveProductComissionValue(final Long productID) {
        return daoProduct.retrieveProductComissionValue(productID);
    }

    public Long retrieveWorkshopIDBySalesman(final String cpfCNPJ) {
        final Salesman salesman = retrieveSalesmanBy(cpfCNPJ);
        final Long salesmanID = salesman.getID();

        try {
            return salesmanDAO.retrieveWorkshopIDBySalesmanID(salesmanID);
        } catch (final DataAccessException ex) {
            final String msg = "ERROR: cannot find a workshopID for Salesman with salesmanID="
                    + "[" + salesmanID + "] and cpfCNPJ=[" + cpfCNPJ + "]!";
            LOG.error(msg, ex);
            throw new SystemException(msg, ex);
        }
    }

    public Long retrieveRegionIDByWorkshopID(final Long workshopID) {
        try {
            return salesmanDAO.retrieveRegionIDByWorkshopID(workshopID);
        } catch (final DataAccessException ex) {
            final String msg = "ERROR: cannot find a RegionalID for WorkshopID=["
                    + workshopID + "]!";
            LOG.error(msg, ex);
            throw new SystemException(msg, ex);
        }

    }

    public String retrieveWorkshopCodeBy(final Long workshopID) {
        try {
            return salesmanDAO.retrieveWorkshopCodeBy(workshopID);
        } catch (final DataAccessException ex) {
            final String msg = "ERROR: cannot find a WorkshopCode for WorkshopID=["
                    + workshopID + "]!";
            LOG.error(msg, ex);
            throw new SystemException(msg, ex);
        }
    }

    public Salesman retrieveSalesmanBy(final String cpfCnpj) {
        try {
            return salesmanDAO.retrieveSalesmanBy(cpfCnpj);
        } catch (final DataAccessException ex) {
            final String msg = "ERROR: cannot find a Salesman for cpfCnpj=["
                    + cpfCnpj + "]!";
            LOG.error(msg, ex);
            throw new SystemException(msg, ex);
        }
    }

    public List<InsuranceCertificate> retrieveCertificateFor(
            final String contractNumber) {
        return null;//daoCertificate.retrieveCertificateFor(contractNumber);
    }

    public List<InsuranceCertificate> retrieveCertificateListByPersonCpfAndPrdIdAndSts(
            final String personCpf, final Long productId,
            final InsuranceStatus status) {
        return null;/*daoCertificate.retrieveCertificateListByPersonCpfAndPrdIdAndSts(
                personCpf, productId, status);*/
    }

    @Transactional(value = "transactionManagerContract")
    public Long updateCardPaymentContract(final CardPaymentDTO cardPayment,
            final String securityKeyID) {
        final Long cardPaymentID = daoCardPayment
                .persistUpdateCardPayment(cardPayment);
        daoCardPayment.persistUpdateCardPaymentSecurityKey(cardPayment,
                securityKeyID, cardPaymentID);

        return cardPaymentID;
    }

    public void updatePaymentMethodContract(
            final InsuranceCertificate certificate,
            final String securityKeyID) {
        validateUpdatePaymentMethodContract(certificate);

        final String contractNumber = certificate.getContractNumber();
        final List<InsuranceCertificate> certificates = null;/*daoCertificate
                .retrieveCertificateFor(contractNumber);*/
        if ((certificates == null) || certificates.isEmpty() || !certificates
                .get(0).getContractNumber()
                .equals(contractNumber)) { throw new BusinessException(
                        "ERROR: No valid insuranceCertificate found ["
                                + certificates + "] for contractNumber=["
                                + contractNumber + "]",
                        ExceptionsConstants.CODE_CERTIFICATES_EMPTY,
                        ErrorType.BUSINESS); }

        final InsuranceCertificate cert = certificates.get(0);
        final Map<String, Object> parans = new HashMap<>();
        parans.put(CONTRACT_NUMBER, cert.getContractNumber());
        parans.put(CONTRACT_INSERT_DATE,
                cert.getCertificateCreationDate().getTime());
        parans.put(CONTRACT_UPDATE_DATE, Calendar.getInstance().getTime());
        final Long cardPaymentID = retrieveCardPaymentIDForContract(certificate,
                securityKeyID);
        parans.put(CARD_DATA_ID_FK, cardPaymentID);
        final Salesman salesman = retrieveSalesmanForContract(certificate);
        parans.put(UPDATE_USER_ID, salesman.getID());
        final IdentityDTO ident = salesman.getIdentity();
        parans.put(UPDATE_USER_DOC, ident.getDocumentValue());
        parans.put(UPDATE_USER_DOC_TYPE, ident.getDocumentType().getValue());

        //daoCertificate.persistUpdateCertificate(parans);
    }

    private Salesman retrieveSalesmanForContract(
            final InsuranceCertificate certificate) {
        if ((certificate == null) || (certificate.getSalesman() == null)
                || (certificate.getSalesman()
                        .getIdentity() == null)) { return null; }
        final String salesmanCpfCnpJ = certificate.getSalesman().getIdentity()
                .getDocumentValue();
        final Salesman salesman = salesmanDAO
                .retrieveSalesmanBy(salesmanCpfCnpJ);
        return salesman;
    }

    private Long retrieveCardPaymentIDForContract(
            final InsuranceCertificate certificate,
            final String securityKeyID) {
        if ((certificate == null) || (certificate.getPaymentMethod() == null)
                || (certificate.getPaymentMethod()
                        .getCardPayment() == null)) { return null; }
        final CardPaymentDTO cardPayment = certificate.getPaymentMethod()
                .getCardPayment();
        final Long cardPaymentID = updateCardPaymentContract(cardPayment,
                securityKeyID);
        return cardPaymentID;
    }

    private void validateUpdatePaymentMethodContract(
            final InsuranceCertificate certificate) {
        final String msgBase = "ERROR: can not update payment method contract with a null";
        if (certificate == null) {
            final String msg = msgBase + " certificate!";
            LOG.error(msg);
            throw new ValidationException(msg,
                    ExceptionsConstants.CODE_UP_CERTIFICATE_NULL,
                    ErrorType.VALIDATION);
        }

        if (certificate.getPaymentMethod() == null) {
            final String msg = msgBase + " PaymentMethod!";
            LOG.error(msg);
            throw new ValidationException(msg,
                    ExceptionsConstants.CODE_UP_PAYMENT_NULL,
                    ErrorType.VALIDATION);
        }

        if (certificate.getSalesman() == null) {
            final String msg = msgBase + " Salesman!";
            LOG.error(msg);
            throw new ValidationException(msg,
                    ExceptionsConstants.CODE_UP_SALESMAN_NULL,
                    ErrorType.VALIDATION);
        }

        if (certificate.getSalesman().getIdentity() == null) {
            final String msg = msgBase + " Salesman with Identity!";
            LOG.error(msg);
            throw new ValidationException(msg,
                    ExceptionsConstants.CODE_UP_IDENTITY_NULL,
                    ErrorType.VALIDATION);
        }
    }

    /**
     * Método responsavel por cadastrar um vendedor
     *
     * @param salesman
     *            Possui as informações da entidade Usuário e Vendedor
     * @param systemPerson
     *            Possui as informações da entidade pessoa
     */
    public void saveSalesman(final Salesman salesman, final SystemPerson systemPerson) {
        try {
            final Long userId = daoSPerson.persist(salesman);
            systemPerson.setLogin(new LoginDTO());
            systemPerson.getLogin().setID(userId);
            final Long personId = daoSPerson.persist(systemPerson);
            salesman.setPersonId(personId);
            salesmanDAO.persist(salesman);
        } catch (final DataAccessException ex) {
            final String msg = "ERROR: cannot save the salesman cpf=[" + salesman.getIdentity().getDocumentValue() + "]!";
            LOG.error(msg, ex);
            throw new SystemException(msg, ex);
        }
    }

    /**
     * Método responsável pela alteração do ramo e da filial do vendedor, é
     * realizado o código da pessoa pelo cpf, depois é realizado a alteração do
     * vendedor com base no id da pessoa
     *
     * @param salesman
     */
    public void updateSalesmanBranch(final Salesman salesman) {
        try {
            final List<SystemPerson> person = daoSPerson.retrieveBy(salesman.getIdentity().getDocumentValue());
            if ((person != null) && !person.isEmpty()) {
                salesman.setPersonId(person.get(0).getID());
                salesmanDAO.updateSalesmanBranch(salesman);
            } else {
                final String msg = "ERROR: cannot find person for cpf=[" + salesman.getIdentity().getDocumentValue() + "]!";
                LOG.debug(msg);
            }
        } catch (final DataAccessException ex) {
            final String msg = "ERROR: cannot update the salesman cpf=[" + salesman.getIdentity().getDocumentValue() + "]!";
            LOG.error(msg, ex);
            throw new SystemException(msg, ex);
        }
    }

    /**
     * Verificar se existe a filial para essa região 
     *
     * @param salesman
     * @return código da filial
     */
    public Long findCodBranchForRegion(final Salesman salesman) {
        try {
            final Long regionID = salesmanDAO.retrieveCodRegionForName(salesman.getRegionName());
            return salesmanDAO.findCodBranchForRegion(salesman, regionID);
        } catch (final DataAccessException ex) {
            final String msg = "ERROR: cannot find a branch for region e branch =[" + salesman.getRegionName() + "/" + salesman.getSalesmanBch() + "]!";
            LOG.error(msg, ex);
            throw new SystemException(msg, ex);
        }
    }

    /**
     * Retorna a idade maxima permitida para contratar o seguro
     * @param productID código do produto de venda
     * @return
     */
    public Integer retrieveMaxInsuredAge(final Long productID) {
        return daoProduct.retrieveMaxInsuredAge(productID);
    }
}
