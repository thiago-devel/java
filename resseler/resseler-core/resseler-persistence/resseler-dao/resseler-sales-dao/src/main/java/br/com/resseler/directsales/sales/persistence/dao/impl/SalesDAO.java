package br.com.resseler.directsales.sales.persistence.dao.impl;

import static br.com.resseler.directsales.commons.persistence.dao.util.CustomerSalesConstants.ACCOUNT_ID;
import static br.com.resseler.directsales.commons.persistence.dao.util.CustomerSalesConstants.BCH_ID;
import static br.com.resseler.directsales.commons.persistence.dao.util.CustomerSalesConstants.CAPITAL_SERIES_ID;
import static br.com.resseler.directsales.commons.persistence.dao.util.CustomerSalesConstants.CAPITAL_SERIES_LTT;
import static br.com.resseler.directsales.commons.persistence.dao.util.CustomerSalesConstants.CAPITAL_SERIES_NBR;
import static br.com.resseler.directsales.commons.persistence.dao.util.CustomerSalesConstants.CAPITAL_SERIES_VLD;
import static br.com.resseler.directsales.commons.persistence.dao.util.CustomerSalesConstants.CERTIFICATE_BCH;
import static br.com.resseler.directsales.commons.persistence.dao.util.CustomerSalesConstants.CERTIFICATE_BCH_TMP;
import static br.com.resseler.directsales.commons.persistence.dao.util.CustomerSalesConstants.CERTIFICATE_CAPITAL_SERIES_LTT;
import static br.com.resseler.directsales.commons.persistence.dao.util.CustomerSalesConstants.CERTIFICATE_CAPITAL_SERIES_NBR;
import static br.com.resseler.directsales.commons.persistence.dao.util.CustomerSalesConstants.CERTIFICATE_CAPITAL_SERIES_VL;
import static br.com.resseler.directsales.commons.persistence.dao.util.CustomerSalesConstants.CERTIFICATE_CAPITAL_SERIES_VLD;
import static br.com.resseler.directsales.commons.persistence.dao.util.CustomerSalesConstants.CERTIFICATE_CMS_VL;
import static br.com.resseler.directsales.commons.persistence.dao.util.CustomerSalesConstants.CERTIFICATE_CNL_DT;
import static br.com.resseler.directsales.commons.persistence.dao.util.CustomerSalesConstants.CERTIFICATE_CTR_NBR;
import static br.com.resseler.directsales.commons.persistence.dao.util.CustomerSalesConstants.CERTIFICATE_EFF_DT;
import static br.com.resseler.directsales.commons.persistence.dao.util.CustomerSalesConstants.CERTIFICATE_EMAIL;
import static br.com.resseler.directsales.commons.persistence.dao.util.CustomerSalesConstants.CERTIFICATE_EMS_DT;
import static br.com.resseler.directsales.commons.persistence.dao.util.CustomerSalesConstants.CERTIFICATE_EXP_DT;
import static br.com.resseler.directsales.commons.persistence.dao.util.CustomerSalesConstants.CERTIFICATE_INI_VIG_DT;
import static br.com.resseler.directsales.commons.persistence.dao.util.CustomerSalesConstants.CERTIFICATE_IOF_VL;
import static br.com.resseler.directsales.commons.persistence.dao.util.CustomerSalesConstants.CERTIFICATE_NO;
import static br.com.resseler.directsales.commons.persistence.dao.util.CustomerSalesConstants.CERTIFICATE_PRM_TOT_VL;
import static br.com.resseler.directsales.commons.persistence.dao.util.CustomerSalesConstants.CERTIFICATE_PRM_VL;
import static br.com.resseler.directsales.commons.persistence.dao.util.CustomerSalesConstants.CERTIFICATE_SLM_PRT_VL;
import static br.com.resseler.directsales.commons.persistence.dao.util.CustomerSalesConstants.CERTIFICATE_STS;
import static br.com.resseler.directsales.commons.persistence.dao.util.CustomerSalesConstants.CERTIFICATE_TLMKT_CAMPAIGN;
import static br.com.resseler.directsales.commons.persistence.dao.util.CustomerSalesConstants.CERTIFICATE_TMP_STS;
import static br.com.resseler.directsales.commons.persistence.dao.util.CustomerSalesConstants.CERTIFICATE_TRF_DT;
import static br.com.resseler.directsales.commons.persistence.dao.util.CustomerSalesConstants.CERTIFICATE_TRF_ML_DT;
import static br.com.resseler.directsales.commons.persistence.dao.util.CustomerSalesConstants.CERTIFICATE_TRF_STS;
import static br.com.resseler.directsales.commons.persistence.dao.util.CustomerSalesConstants.CRD_CARD_ID;
import static br.com.resseler.directsales.commons.persistence.dao.util.CustomerSalesConstants.INSURED_ID;
import static br.com.resseler.directsales.commons.persistence.dao.util.CustomerSalesConstants.NATIONALITY_DEFAULT;
import static br.com.resseler.directsales.commons.persistence.dao.util.CustomerSalesConstants.PARCELAS_PADRAO;
import static br.com.resseler.directsales.commons.persistence.dao.util.CustomerSalesConstants.PAYMENTMETHOD_CREDITCARD_TEF_PTBR;
import static br.com.resseler.directsales.commons.persistence.dao.util.CustomerSalesConstants.PRDCONF_ID;
import static br.com.resseler.directsales.commons.persistence.dao.util.CustomerSalesConstants.PRD_FRM_ID;
import static br.com.resseler.directsales.commons.persistence.dao.util.CustomerSalesConstants.PRD_ID;
import static br.com.resseler.directsales.commons.persistence.dao.util.CustomerSalesConstants.REG_ID;
import static br.com.resseler.directsales.commons.persistence.dao.util.CustomerSalesConstants.SALESMAN_ID;
import static org.apache.commons.lang.math.NumberUtils.isNumber;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import br.com.resseler.directsales.sales.persistence.builders.CompleteCertificate;
import br.com.resseler.directsales.sales.persistence.builders.CompleteCertificateBuilder;
import br.com.resseler.directsales.sales.persistence.builders.SalesDAOParameters;
import br.com.resseler.directsales.sales.persistence.builders.SalesDAOParametersBuilder;
import br.com.rubyit.resseler.commons.kernel.dto.CapitalSeries;
import br.com.rubyit.resseler.commons.kernel.dto.IdentityDTO;
import br.com.rubyit.resseler.commons.kernel.dto.InsuranceDTO;
import br.com.rubyit.resseler.commons.kernel.dto.ProductDTO;
import br.com.rubyit.resseler.commons.kernel.enums.InsuranceStatus;
import br.com.rubyit.resseler.core.commons.dto.CardPaymentDTO;
import br.com.rubyit.resseler.core.commons.dto.Coverage;
import br.com.rubyit.resseler.core.commons.dto.Coverages;
import br.com.rubyit.resseler.core.commons.dto.Customer;
import br.com.rubyit.resseler.core.commons.dto.InsuranceCertificate;
import br.com.rubyit.resseler.core.commons.dto.PaymentMethod;
import br.com.rubyit.resseler.core.commons.dto.Regional;
import br.com.rubyit.resseler.core.commons.dto.Sale;
import br.com.rubyit.resseler.core.commons.dto.Salesman;
import br.com.rubyit.resseler.core.commons.dto.SystemPerson;
import br.com.rubyit.resseler.core.commons.dto.Tax;
import br.com.rubyit.resseler.core.commons.dto.Taxes;
import br.com.rubyit.resseler.core.commons.dto.Workshop;
import br.com.rubyit.resseler.core.commons.exceptions.BusinessException;
import br.com.rubyit.resseler.core.commons.exceptions.ExceptionsConstants;
import br.com.rubyit.resseler.core.commons.exceptions.ValidationException;
import br.com.rubyit.resseler.core.enums.ErrorType;
import br.com.rubyit.resseler.core.enums.PaymentMethodType;
import br.com.rubyit.resseler.core.utils.ProductData;

/**
 * Principal DAO class to handle Sales persistences.
 *
 * @author a42239
 */
@edu.umd.cs.findbugs.annotations.SuppressFBWarnings(value = {
        "squid:S1200" }, justification = "Nivel minimo de acoplamento ja foi atingido. Mantendo a logica planejada e com os requisitos de negocios "
                + "existentes, nao existem outras refatoracoes possiveis nesse momento.")
public class SalesDAO {

    private static final String ICD_CERTIFICATE = "certificate";
    private static final String ICD_PRODUCT = "product";
    private static final String ICD_CERTIFICATE_NUMBER = "certificateNumber";
    private static final String ICD_SALE = "sale";
    private static final int DEFAULT_OBJECT_CODE = 173;
    private static final int DEFAULT_INSTALLMENT_QUANT = 1;
    private static final Logger LOG = LogManager.getLogger(SalesDAO.class);
    private static final String ERROR_CERTIFICATE_PERSISTENCE_PROCESS_FAILURE = "ERROR: "
            + "Certificate persistence process failure.";
    @Autowired
    private SalesDBDAO salesDBDAO;

    /**
     * Contructor used by DI
     */
    public SalesDAO() {
        // do nothing. DI Injection point
    }

    public SalesDAO(final SalesDBDAO salesDBDAO) {
        this.salesDBDAO = salesDBDAO;
    }

    public InsuranceCertificate process(final Sale sale) {
        LOG.debug("Init method process(final Sale [" + sale + "])");

        final InsuranceCertificate certificateActivated = insuranceCertificateProcess(
                sale);

        LOG.debug("End method process(final Sale [" + sale
                + "]) return with certificateActivated=[" + certificateActivated
                + "]");
        return certificateActivated;
    }

    private InsuranceCertificate insuranceCertificateProcess(final Sale ssale) {
        LOG.debug("Init method insuranceCertificateProcess(final Sale [" + ssale
                + "])");
        final Map<String, Object> insuranceCertificateData = insuranceCertificatePendingProcess(
                ssale);
        final Sale sale = (Sale) insuranceCertificateData.get(ICD_SALE);
        final String certificateNumber = (String) insuranceCertificateData
                .get(ICD_CERTIFICATE_NUMBER);
        final InsuranceDTO product = (InsuranceDTO) insuranceCertificateData
                .get(ICD_PRODUCT);
        final InsuranceCertificate pendingCertificate = (InsuranceCertificate) insuranceCertificateData
                .get(ICD_CERTIFICATE);

        final InsuranceCertificate certificateActivated = paymentProcess(sale,
                certificateNumber, product, pendingCertificate);

        LOG.debug("End method insuranceCertificateProcess(final Sale [" + ssale
                + "]) return with certificateActivated=[" + certificateActivated
                + "]");
        return certificateActivated;
    }

    @Transactional(value = "transactionManagerDirectSales")
    public Map<String, Object> insuranceCertificatePendingProcess(
            final Sale ssale) {
        LOG.debug("Init method insuranceCertificatePendingProcess(final Sale ["
                + ssale + "])");

        validateSalesman(ssale);

        final Sale preSale = fillSalesman(ssale);

        final Long productID = (ssale.getProduct() == null) ? null
                : ssale.getProduct().getID();

        final Sale sale = fillCustomer(preSale);

        // RETORNA CERTIFICADO CASO TENHA OCORRIDO ALGUM TIMEOUT NO PROCESSO DA
        // VENDA
        final List<InsuranceCertificate> certificatesRefusedByCustomer = salesDBDAO
                .retrieveCertificateListByPersonCpfAndPrdIdAndSts(
                        sale.getCustomer().getIdentity().getDocumentValue(),
                        productID, InsuranceStatus.REFUSED);

        Boolean isResubmit = Boolean.FALSE;
        Long certificateID = null;
        String certificateNumber;
        Map<String, Object> capitalSeriesData;

        if ((certificatesRefusedByCustomer != null)
                && !certificatesRefusedByCustomer.isEmpty()) {
            final InsuranceCertificate certificateResubmit = certificatesRefusedByCustomer
                    .get(0);
            capitalSeriesData = validateCertificateResubmit(
                    certificatesRefusedByCustomer);
            certificateID = certificateResubmit.getID();
            certificateNumber = certificateResubmit.getContractNumber();
            isResubmit = Boolean.TRUE;
        } else {
            certificateNumber = salesDBDAO.generateContractNumberBy(productID);
            capitalSeriesData = salesDBDAO
                    .retrieveCapitalSeriesData(certificateNumber);
        }

        final InsuranceDTO product = salesDBDAO.retrieveProductBy(productID);
        product.setStatus(InsuranceStatus.PENDING);

        final Long creditCardID = retrieveCreditCardID(sale);

        InsuranceCertificate certificate = processInsuranceCertificate(sale,
                certificateNumber, product, creditCardID, capitalSeriesData,
                isResubmit, certificateID);

        certificate = attributeCoveragesToCertificate(certificate, product);

        final Map<String, Object> insuranceCertificateData = new HashMap<>();
        insuranceCertificateData.put(ICD_SALE, sale);
        insuranceCertificateData.put(ICD_CERTIFICATE_NUMBER, certificateNumber);
        insuranceCertificateData.put(ICD_PRODUCT, product);
        insuranceCertificateData.put(ICD_CERTIFICATE, certificate);

        LOG.debug("End method insuranceCertificateProcess(final Sale [" + ssale
                + "]) return with insuranceCertificateData=["
                + insuranceCertificateData + "]");
        return insuranceCertificateData;
    }

    private Map<String, Object> validateCertificateResubmit(
            final List<InsuranceCertificate> certificatesRefusedByCustomer) {
        final InsuranceCertificate certificateReSubmit = certificatesRefusedByCustomer
                .get(0);
        Map<String, Object> capitalSeriesData = salesDBDAO
                .retrieveCapitalSeriesDataByCertificate(
                        certificateReSubmit.getContractNumber());

        if ((capitalSeriesData == null) || capitalSeriesData.isEmpty()) {
            capitalSeriesData = salesDBDAO
                    .cleanOldAndRetrieveNewCapitalSeriesData(
                            certificateReSubmit.getContractNumber());
        }

        validateCapitalSeriesData(capitalSeriesData);

        return capitalSeriesData;
    }

    private void validateCapitalSeriesData(
            final Map<String, Object> capitalSeriesData) {
        if ((capitalSeriesData == null) || capitalSeriesData.isEmpty()) {
            final String msg = "ERROR: Não foi possível completar a venda, por favor entre em contato com o administrador do sistema. ";
            LOG.error(msg);
            throw new ValidationException(msg,
                    ExceptionsConstants.CODE_NOT_SALE, ErrorType.VALIDATION);
        }
    }

    @Transactional(value = "transactionManagerDirectSales")
    public InsuranceCertificate paymentProcess(final Sale sale,
            final String certificateNumber, final InsuranceDTO product,
            final InsuranceCertificate certificate) {
        LOG.debug("Init method paymentProcess(final Sale [" + sale
                + "], final String [" + certificateNumber + "],"
                + "final InsuranceDTO [" + product + "], InsuranceCertificate ["
                + certificate + "])");

        InsuranceCertificate newCertificate = certificate;

        try {
            newCertificate = processCreditCardPayment(sale, certificateNumber,
                    product, newCertificate);
        } catch (final Exception ex) {
            final String msg = "ERROR: business process doSale fail!" + ex;
            LOG.error(msg);
            throw new BusinessException(msg, ex,
                    ExceptionsConstants.CODE_PAYMENTINTEGRATION, ErrorType.BUSINESS);
        }

        final PaymentMethod paymentMethod = fillPaymentMethod(sale);
        newCertificate.setPaymentMethod(paymentMethod);

        LOG.debug("End method paymentProcess(final Sale [" + sale
                + "], final String [" + certificateNumber + "],"
                + "final InsuranceDTO [" + product + "], InsuranceCertificate ["
                + newCertificate + "]) return with certificate=["
                + newCertificate + "]");
        return newCertificate;
    }

    private Long retrieveCreditCardID(final Sale sale) {
        final PaymentMethod paymentMethod = sale.getPaymentMethod();
        if (paymentMethod == null) {
            final String msg = "ERROR: cannot retrieve Credit Card ID to null paymentMethod!";
            LOG.error(msg);
            throw new ValidationException(msg,
                    ExceptionsConstants.CODE_PAYMENT_NULL,
                    ErrorType.VALIDATION);
        }
        final CardPaymentDTO cardPayment = paymentMethod.getCardPayment();
        final Number key = salesDBDAO.persistCardPayment(cardPayment);
        final Long creditCardID = key.longValue();

        return creditCardID;
    }

    private InsuranceCertificate activeCertificate(
            final InsuranceCertificate certificate) {
        LOG.debug("Init method activeCertificate(final InsuranceCertificate ["
                + certificate + "])");

        final InsuranceCertificate newCertificate = changeCertificateStatus(
                certificate, InsuranceStatus.ACTIVE, Boolean.FALSE);

        LOG.debug("End method activeCertificate(final InsuranceCertificate ["
                + certificate + "]) return with newCertificate=["
                + newCertificate + "])");
        return newCertificate;
    }

    private InsuranceCertificate refuseCertificate(
            final InsuranceCertificate certificate, final Boolean withTmpSts) {
        LOG.debug("Init method refuseCertificate(final InsuranceCertificate ["
                + certificate + "])");

        final InsuranceCertificate newCertificate = changeCertificateStatus(
                certificate, InsuranceStatus.REFUSED, withTmpSts);

        LOG.debug("End method refuseCertificate(final InsuranceCertificate ["
                + certificate + "]) return with newCertificate=["
                + newCertificate + "])");
        return newCertificate;
    }

    private InsuranceCertificate changeCertificateStatus(
            final InsuranceCertificate certificate,
            final InsuranceStatus newStatus, final Boolean withTmpSts) {
        LOG.debug(
                "Init method changeCertificateStatus(final InsuranceCertificate ["
                        + certificate + "], final InsuranceStatus " + newStatus
                        + ")");
        final InsuranceCertificate newCertificate = certificate;
        if (certificate == null) {
            final String msg = " Invalid insuranceCertificate is null. Updated insurance certificate abort!";
            LOG.error(msg);
        } else {
            if (withTmpSts) {
                salesDBDAO.updateCertificateStatusAndTmpSts(certificate.getID(),
                        newStatus);
            } else {
                salesDBDAO.updateCertificateStatus(certificate.getID(),
                        newStatus);
            }
            setCertificateStatus(newCertificate, newStatus);
        }

        LOG.debug(
                "End method changeCertificateStatus(final InsuranceCertificate ["
                        + certificate + "]) return with newCertificate=["
                        + newCertificate + "], final InsuranceStatus "
                        + newStatus + ")");
        return newCertificate;
    }

    private void setCertificateStatus(final InsuranceCertificate newCertificate,
            final InsuranceStatus status) {
        final InsuranceDTO pr = newCertificate.getProduct();
        if (pr != null) {
            pr.setStatus(status);
        }
        newCertificate.setProduct(pr);
    }

    private InsuranceCertificate attributeCoveragesToCertificate(
            final InsuranceCertificate certificate, final ProductDTO product) {

        final InsuranceCertificate newCertificate = certificate;
        final Coverages coverages = new Coverages();
        final ProductData pd = ProductData.getInstance();

        final List<Coverage> listCoverages = pd.getListCoverageByProduct(product);
        if (listCoverages != null) {
            coverages.setCoverage(listCoverages);
        }

        final Taxes taxes = new Taxes();
        final List<Tax> listTax = pd.getListTaxByProduct(product);
        if (listTax != null) {
            taxes.setTax(listTax);
        }

        newCertificate.setTaxes(taxes);
        newCertificate.setCoverages(coverages);

        return newCertificate;
    }

    private Sale fillCustomer(final Sale sale) {
        final Sale newSale = sale;
        final Customer customer = sale.getCustomer();
        final Number key = salesDBDAO.persistCustomer(customer);
        customer.setID((key == null) ? null : key.longValue());
        newSale.setCustomer(customer);

        return newSale;
    }

    private Sale fillSalesman(final Sale sale) {
        LOG.debug("Init method setSalesmanID(final Sale [" + sale + "])");

        final Sale newSale = sale;
        final SystemPerson sPerson = sale.getSalesman();
        final IdentityDTO identSalesman = sPerson.getIdentity();
        final String cpfCnpj = (identSalesman == null) ? null
                : identSalesman.getDocumentValue();
        final Salesman salesman = salesDBDAO.retrieveSalesmanBy(cpfCnpj);
        newSale.setSalesman(salesman);

        LOG.debug("End method setSalesmanID(final Sale [" + newSale + "])");
        return newSale;
    }

    protected void validateSalesman(final Sale sale) {
        final Salesman salesman = sale.getSalesman();
        validateSalesman(salesman);

        final String cpfCNPJ = salesman.getIdentity().getDocumentValue();

        final Object person = salesDBDAO.retrieveBy(cpfCNPJ);

        if (person == null) {
            final String msg = "ERROR: Salesman inválido. Vendedor não encontrado!";
            LOG.error(msg);
            throw new BusinessException(msg,
                    ExceptionsConstants.CODE_SALESMAN_NOTFOUND,
                    ErrorType.BUSINESS);
        }
    }

    private void validateSalesman(final Salesman salesman) {
        final String VALIDATION_ERROR = "ERROR: received invalid Salesman data.";
        if (salesman == null) {
            final String msg = VALIDATION_ERROR
                    + " Process aborted! Salesman Object is null!";
            LOG.error(msg);
            throw new ValidationException(msg,
                    ExceptionsConstants.CODE_SALESMAN_NULL,
                    ErrorType.VALIDATION);
        }
        if (salesman.getOperatorName() == null) {
            final String msg = VALIDATION_ERROR
                    + " Process aborted! Salesman.operatorName is null!";
            LOG.error(msg);
            throw new ValidationException(msg,
                    ExceptionsConstants.CODE_OPERATOR_NULL,
                    ErrorType.VALIDATION);
        }
        if ("".equals(salesman.getOperatorName().trim())) {
            final String msg = VALIDATION_ERROR
                    + " Process aborted!  Salesman.operatorName is invalid!";
            LOG.error(msg);
            throw new ValidationException(msg,
                    ExceptionsConstants.CODE_OPERATORNAME_NULL,
                    ErrorType.VALIDATION);
        }
        validateSalesmanPartner(salesman, VALIDATION_ERROR);
        validateSalesmanIdentity(salesman, VALIDATION_ERROR);
    }

    private void validateSalesmanPartner(final Salesman salesman,
            final String VALIDATION_ERROR) {
        if (salesman.getPartner() == null) {
            final String msg = VALIDATION_ERROR
                    + " Process aborted! Salesman.partner is null!";
            LOG.error(msg);
            throw new ValidationException(msg,
                    ExceptionsConstants.CODE_PARTNER_NULL,
                    ErrorType.VALIDATION);
        }
        if (salesman.getPartner().getID() == null) {
            final String msg = VALIDATION_ERROR
                    + " Process aborted! Salesman.partner.ID is null!";
            LOG.error(msg);
            throw new ValidationException(msg,
                    ExceptionsConstants.CODE_PARTNERID_NULL,
                    ErrorType.VALIDATION);
        }
    }

    private void validateSalesmanIdentity(final Salesman salesman,
            final String VALIDATION_ERROR) {
        if (salesman.getIdentity() == null) {
            final String msg = VALIDATION_ERROR
                    + " Process aborted! Salesman.identity is null!";
            LOG.error(msg);
            throw new ValidationException(msg,
                    ExceptionsConstants.CODE_IDENTITY_NULL,
                    ErrorType.VALIDATION);
        }
        if ((salesman.getIdentity().getDocumentValue() == null) || ""
                .equals(salesman.getIdentity().getDocumentValue().trim())) {
            final String msg = VALIDATION_ERROR
                    + " Process aborted! Salesman.identity is invalid!";
            LOG.error(msg);
            throw new ValidationException(msg,
                    ExceptionsConstants.CODE_DOCUMENT_NULL,
                    ErrorType.VALIDATION);
        }
    }

    protected InsuranceCertificate processInsuranceCertificate(final Sale sale,
            final String certificateNumber, final InsuranceDTO product,
            final Long creditCardID,
            final Map<String, Object> capitalSeriesData,
            final Boolean isResubmit, final Long certificateID) {
        final InsuranceCertificate certificate = persistCertificate(product,
                certificateNumber, sale, creditCardID, capitalSeriesData,
                isResubmit, certificateID);

        return certificate;
    }

    protected InsuranceCertificate processCreditCardPayment(final Sale sale,
            final String certificateNumber, final InsuranceDTO product,
            final InsuranceCertificate certificate) {
        LOG.debug("Init method processCreditCardPayment(final Sale [" + sale
                + "], final String [" + certificateNumber + "],"
                + "final InsuranceDTO [" + product
                + "], final InsuranceCertificate [" + certificate + "])");
        final BigDecimal premiumValue = (product == null) ? null
                : product.getPremiumValue();
        final BigInteger numeroParcelas = PARCELAS_PADRAO;
        InsuranceCertificate newCertificate = null;
/*
        try {
            PaymentIntegration.processCreditCardPayment(sale, certificateNumber,
                    premiumValue, numeroParcelas);
            newCertificate = activeCertificate(certificate);
        } catch (final Exception ex) {
            String msg = "ERROR: method processCreditCardPayment fail!["
                    + ex.getMessage()
                    + "] \n the parameters was [certificateNumber="
                    + certificateNumber + ", product=" + product
                    + ", premiumValue=" + premiumValue + ", numeroParcelas="
                    + numeroParcelas + "]";
            if (certificate == null) {
                msg = msg.concat(
                        "[InsuranceCertificate is null. Updated insurance certificate abort!]");
                LOG.error(msg);
            } else {
                if (ex.getCause() instanceof FuncFaultMessage) {
                    LOG.error(
                            "Initiate process to refuse the certificateNumber=["
                                    + certificateNumber + "]");
                    refuseCertificate(certificate, Boolean.FALSE);
                    LOG.error(
                            "Finalize process to refuse the certificateNumber=["
                                    + certificateNumber + "]");
                } else {
                    LOG.error(
                            "Initiate process to refuse the certificateNumber=["
                                    + certificateNumber
                                    + "] and CERTIFICATE_TMP_STS=["
                                    + CODE_TIMEOUT + "]");
                    refuseCertificate(certificate, Boolean.TRUE);
                    LOG.error(
                            "Finalize process to refuse the certificateNumber=["
                                    + certificateNumber
                                    + "] and CERTIFICATE_TMP_STS=["
                                    + CODE_TIMEOUT + "]");
                }
            }

            LOG.error(msg, ex);
            throw new PaymentIntegrationException(msg, ex,
                    ExceptionsConstants.CODE_PAYMENTINTEGRATION, ErrorType.BUSINESS);
        }*/

        LOG.debug("END method processCreditCardPayment(final Sale [" + sale
                + "], final String [" + certificateNumber + "],"
                + "final InsuranceDTO [" + product
                + "], final InsuranceCertificate [" + certificate
                + "]) return with newCertificate=[" + newCertificate + "]");
        return newCertificate;
    }

    protected InsuranceCertificate persistCertificate(
            final InsuranceDTO product, final String certificateNumber,
            final Sale sale, final Long creditCardID,
            final Map<String, Object> capitalSeriesData,
            final Boolean isResubmit, final Long certificateID) {
        LOG.debug("Init method persistCertificate(final InsuranceDTO ["
                + product + "], final String [" + certificateNumber
                + "], final Sale [" + sale + "], final Integer [" + creditCardID
                + "], final Map<String, Object> [" + capitalSeriesData + "])");

        if (sale == null) {
            final String msg = ERROR_CERTIFICATE_PERSISTENCE_PROCESS_FAILURE
                    + " Sale object is null!";
            LOG.error(msg);
            throw new ValidationException(msg,
                    ExceptionsConstants.CODE_SALE_NULL, ErrorType.VALIDATION);
        }

        final Customer customer = sale.getCustomer();
        final Salesman salesman = sale.getSalesman();

        validateCertificateContent(product, sale, customer, salesman,
                capitalSeriesData);

        final PaymentMethod paymentMethod = sale.getPaymentMethod();

        final String salesmanCpfCnpj = sale.getSalesman().getIdentity()
                .getDocumentValue();
        final Long workshopID = salesDBDAO
                .retrieveWorkshopIDBySalesman(salesmanCpfCnpj);
        final Long regionID = salesDBDAO
                .retrieveRegionIDByWorkshopID(workshopID);

        InsuranceCertificate certificate = new InsuranceCertificate();
        certificate.setProduct(product);
        certificate.setContractNumber(certificateNumber);

        final String workshopCode = salesDBDAO
                .retrieveWorkshopCodeBy(workshopID);

        final SalesDAOParametersBuilder sdpBuilder = new SalesDAOParametersBuilder();
        sdpBuilder.setProduct(product);
        sdpBuilder.setCertificateNumber(certificateNumber);
        sdpBuilder.setCreditCardID(creditCardID);
        sdpBuilder.setCapitalSeriesData(capitalSeriesData);
        customer.setNationality(NATIONALITY_DEFAULT);
        sdpBuilder.setCustomer(customer);
        sdpBuilder.setSalesman(salesman);
        sdpBuilder.setPaymentMethod(paymentMethod);
        sdpBuilder.setWorkshopID(workshopID);
        sdpBuilder.setRegionID(regionID);
        sdpBuilder.setWorkshopCode(workshopCode);
        final SalesDAOParameters salesDAOParameters = sdpBuilder.build();

        final Map<String, Object> parans = prepareParameters(
                salesDAOParameters);

        if (isResubmit) {
            certificate.setID(certificateID);
            salesDBDAO.updateInsuranceCertificate(certificate, parans);
        } else {
            final Number result = salesDBDAO
                    .persistInsuranceCertificate(certificate, parans);
            final Long newCertificateID = (result == null) ? null
                    : result.longValue();
            certificate.setID(newCertificateID);
        }

        salesDBDAO.persistInstallment(certificate, DEFAULT_INSTALLMENT_QUANT,
                parans);
        salesDBDAO.persistInsuredObject(certificate, DEFAULT_OBJECT_CODE);

        final CompleteCertificateBuilder ccBuilder = new CompleteCertificateBuilder();
        ccBuilder.setProduct(product);
        ccBuilder.setCustomer(customer);
        ccBuilder.setSalesman(salesman);
        ccBuilder.setWorkshopID(workshopID);
        ccBuilder.setRegionID(regionID);
        ccBuilder.setParans(parans);
        ccBuilder.setCertificate(certificate);
        ccBuilder.setCapitalSeriesData(capitalSeriesData);
        final CompleteCertificate ccertificate = ccBuilder.build();
        certificate = completeCertificateFill(ccertificate);

        LOG.debug("End method persistCertificate(final InsuranceDTO [" + product
                + "], final String [" + certificateNumber + "], final Sale ["
                + sale + "], final Integer [" + creditCardID
                + "], final Map<String, Object> [" + capitalSeriesData
                + "]) return with certificate=[" + certificate + "]");
        return certificate;
    }

    private PaymentMethod fillPaymentMethod(final Sale sale) {
        final PaymentMethod paymentMethod = sale.getPaymentMethod();
        paymentMethod.setDescription(PAYMENTMETHOD_CREDITCARD_TEF_PTBR);
        paymentMethod.setID(PaymentMethodType.CREDITCARD.getCode().longValue());
        final CardPaymentDTO creditCard = sale.getPaymentMethod()
                .getCardPayment();
        final String creditCardMasked = maskCreditCardNumber(creditCard);
        creditCard.setCardNumber(creditCardMasked);
        creditCard.setCardSecurityCode(null);
        paymentMethod.setCardPayment(creditCard);

        return paymentMethod;
    }

    private InsuranceCertificate fillCapitalSeries(
            final Map<String, Object> capitalSeriesData,
            final InsuranceCertificate certificate) {
        final Long capitalSeriesID = (Long) capitalSeriesData
                .get(CAPITAL_SERIES_ID);
        final String capSeriesNumber = (String) capitalSeriesData
                .get(CAPITAL_SERIES_NBR);
        final String capitalSeriesNumber = (capSeriesNumber == null) ? null
                : capSeriesNumber;
        final BigInteger capSeriesNumberInt = ((capitalSeriesNumber != null)
                && (isNumber(capSeriesNumber)))
                        ? BigInteger.valueOf(Long.parseLong(capSeriesNumber))
                        : null;
        final CapitalSeries cap = new CapitalSeries();
        cap.setCapitalNumber(capSeriesNumberInt);
        cap.setDescription(capitalSeriesNumber);
        cap.setID(capitalSeriesID);
        final InsuranceCertificate newCertificate = certificate;
        newCertificate.setCapitalSeries(cap);

        return newCertificate;
    }

    public InsuranceCertificate completeCertificateFill(
            final CompleteCertificate completeCertificate) {
        if ((completeCertificate == null) || (completeCertificate
                .getCertificate() == null)) { return null; }
        InsuranceCertificate certificate = completeCertificate.getCertificate();
        if (completeCertificate.getParans() != null) {
            final Map<String, Object> parans = completeCertificate.getParans();

            certificate.setCertificateCreationDate(
                    (Calendar) parans.get(CERTIFICATE_EFF_DT));
            certificate.setCertificateValidityInitDate(
                    (Calendar) parans.get(CERTIFICATE_INI_VIG_DT));
            certificate.setCertificateValidityEndDate(
                    (Calendar) parans.get(CERTIFICATE_EXP_DT));
            certificate.setContractNumber((String) parans.get(CERTIFICATE_NO));
        }

        final Salesman salesman = completeCertificate.getSalesman();
        certificate.setSalesman(salesman);

        certificate.setCustomer(completeCertificate.getCustomer());

        final Regional regionalSul = new Regional();
        regionalSul.setID(completeCertificate.getRegionID());

        final Workshop workshop = new Workshop();
        workshop.getSallers().add(salesman);
        workshop.setID(completeCertificate.getWorkshopID());
        workshop.setRegionalID(regionalSul.getID());
        regionalSul.getWorkshops().add(workshop);
        certificate.setWorkshop(workshop);

        if (completeCertificate.getProduct() != null) {
            final InsuranceDTO product = completeCertificate.getProduct();
            final BigDecimal productComissionValue = salesDBDAO
                    .retrieveProductComissionValue(
                            completeCertificate.getProduct().getID());
            certificate.setSaleCommissionValue(productComissionValue);
            certificate = attributeCoveragesToCertificate(certificate, product);
        }

        certificate = fillCapitalSeries(
                completeCertificate.getCapitalSeriesData(), certificate);
        if (certificate.getCertificateCreationDate() == null) {
            certificate.setCertificateCreationDate(Calendar.getInstance());
        }

        return certificate;
    }

    private Map<String, Object> prepareParameters(
            final SalesDAOParameters salesDAOParameters) {
        final Map<String, Object> parans = new HashMap<>();
        parans.put(INSURED_ID, (salesDAOParameters.getCustomer() == null) ? null
                : salesDAOParameters.getCustomer().getID());
        parans.put(SALESMAN_ID, (salesDAOParameters.getSalesman() == null)
                ? null : salesDAOParameters.getSalesman().getID());
        parans.put(PRD_ID, (salesDAOParameters.getProduct() == null) ? null
                : salesDAOParameters.getProduct().getID());
        parans.put(CERTIFICATE_NO, salesDAOParameters.getCertificateNumber());
        parans.put(CERTIFICATE_PRM_VL, (salesDAOParameters.getProduct() == null)
                ? null : salesDAOParameters.getProduct().getPremiumValue());
        final Calendar initDT = Calendar.getInstance();
        final Calendar expDT = Calendar.getInstance();
        expDT.set(Calendar.DAY_OF_MONTH, initDT.get(Calendar.DAY_OF_MONTH) + 1);
        expDT.set(Calendar.YEAR, initDT.get(Calendar.YEAR) + 5);
        final Calendar d1 = Calendar.getInstance();
        d1.set(Calendar.DAY_OF_MONTH, initDT.get(Calendar.DAY_OF_MONTH) + 1);
        parans.put(CERTIFICATE_EFF_DT, initDT);
        parans.put(CERTIFICATE_EXP_DT, expDT);
        parans.put(CERTIFICATE_STS, (salesDAOParameters.getProduct() == null)
                ? InsuranceStatus.REFUSED.getCode()
                : salesDAOParameters.getProduct().getStatus().getCode());
        parans.put(CERTIFICATE_CMS_VL, null);
        parans.put(CERTIFICATE_IOF_VL, null);
        parans.put(CERTIFICATE_PRM_TOT_VL, null);
        parans.put(CERTIFICATE_SLM_PRT_VL, null);
        parans.put(CERTIFICATE_EMS_DT, initDT);
        parans.put(CERTIFICATE_CNL_DT, null);
        parans.put(CERTIFICATE_TRF_DT, null);
        parans.put(CERTIFICATE_TRF_ML_DT, null);
        parans.put(CERTIFICATE_TRF_STS, null);
        parans.put(CERTIFICATE_CAPITAL_SERIES_LTT,
                (salesDAOParameters.getCapitalSeriesData() == null) ? null
                        : salesDAOParameters.getCapitalSeriesData()
                                .get(CAPITAL_SERIES_LTT));
        parans.put(CERTIFICATE_CAPITAL_SERIES_NBR,
                (salesDAOParameters.getCapitalSeriesData() == null) ? null
                        : salesDAOParameters.getCapitalSeriesData()
                                .get(CAPITAL_SERIES_NBR));
        parans.put(CERTIFICATE_CAPITAL_SERIES_VLD,
                (salesDAOParameters.getCapitalSeriesData() == null) ? null
                        : salesDAOParameters.getCapitalSeriesData()
                                .get(CAPITAL_SERIES_VLD));
        parans.put(CERTIFICATE_BCH, salesDAOParameters.getWorkshopCode());
        parans.put(CERTIFICATE_TMP_STS, null);
        parans.put(BCH_ID, salesDAOParameters.getWorkshopID());
        parans.put(REG_ID, salesDAOParameters.getRegionID());
        parans.put(CERTIFICATE_BCH_TMP, null);
        parans.put(PRD_FRM_ID, (salesDAOParameters.getPaymentMethod() == null)
                ? null
                : salesDAOParameters.getPaymentMethod().getPaymentType());
        parans.put(CRD_CARD_ID, salesDAOParameters.getCreditCardID());
        parans.put(CERTIFICATE_CAPITAL_SERIES_VL, null);
        parans.put(CERTIFICATE_CTR_NBR, null);
        parans.put(CERTIFICATE_EMAIL, null);
        parans.put(ACCOUNT_ID, null);
        parans.put(CERTIFICATE_INI_VIG_DT, d1);
        parans.put(CERTIFICATE_TLMKT_CAMPAIGN, null);
        parans.put(PRDCONF_ID, null);

        return parans;
    }

    private PaymentMethod validateCertificateContent(final InsuranceDTO product,
            final Sale sale, final Customer customer, final Salesman salesman,
            final Map<String, Object> capitalSeriesData) {
        if (customer == null) {
            final String msg = ERROR_CERTIFICATE_PERSISTENCE_PROCESS_FAILURE
                    + " Customer object is null!";
            LOG.error(msg);
            throw new ValidationException(msg,
                    ExceptionsConstants.CODE_CUSTOMER_NULL,
                    ErrorType.VALIDATION);
        }
        if (salesman == null) {
            final String msg = ERROR_CERTIFICATE_PERSISTENCE_PROCESS_FAILURE
                    + " Salesman object is null!";
            LOG.error(msg);
            throw new ValidationException(msg,
                    ExceptionsConstants.CODE_SALESMAN_NULL,
                    ErrorType.VALIDATION);
        }
        if (product == null) {
            final String msg = ERROR_CERTIFICATE_PERSISTENCE_PROCESS_FAILURE
                    + " Product object is null!";
            LOG.error(msg);
            throw new ValidationException(msg,
                    ExceptionsConstants.CODE_PRODUCT_NULL,
                    ErrorType.VALIDATION);
        }
        if (capitalSeriesData == null) {
            final String msg = ERROR_CERTIFICATE_PERSISTENCE_PROCESS_FAILURE
                    + " CapitalSeriesData object is null!";
            LOG.error(msg);
            throw new ValidationException(msg,
                    ExceptionsConstants.CODE_CAPITALSERIES_NULL,
                    ErrorType.VALIDATION);
        }

        return validatePaymentMethod(product, sale);
    }

    private PaymentMethod validatePaymentMethod(final InsuranceDTO product,
            final Sale sale) {
        final PaymentMethod paymentMethod = sale.getPaymentMethod();
        if (paymentMethod == null) {
            final String msg = ERROR_CERTIFICATE_PERSISTENCE_PROCESS_FAILURE
                    + " PaymentMethod object is null!";
            LOG.error(msg);
            throw new ValidationException(msg,
                    ExceptionsConstants.CODE_PAYMENT_NULL,
                    ErrorType.VALIDATION);
        }
        final CardPaymentDTO creditCardPayment = paymentMethod.getCardPayment();
        if (creditCardPayment == null) {
            final String msg = ERROR_CERTIFICATE_PERSISTENCE_PROCESS_FAILURE
                    + " CardPaymentDTO object is null!";
            LOG.error(msg);
            throw new ValidationException(msg,
                    ExceptionsConstants.CODE_CARD_NULL, ErrorType.VALIDATION);
        }
        final BigDecimal creditCardPaymentValue = creditCardPayment
                .getCardValue();

        if (creditCardPaymentValue == null) {
            final String msg = ERROR_CERTIFICATE_PERSISTENCE_PROCESS_FAILURE
                    + " Credit Card Payment Value object is null!";
            LOG.error(msg);
            throw new ValidationException(msg,
                    ExceptionsConstants.CODE_PAYMENTMETHOD_NULL,
                    ErrorType.VALIDATION);
        }
        final BigDecimal productPremium = product.getPremiumValue();
        if (creditCardPaymentValue.compareTo(productPremium) != 0) {
            final String msg = ERROR_CERTIFICATE_PERSISTENCE_PROCESS_FAILURE
                    + " Credit Card Payment Value[" + creditCardPaymentValue
                    + "] is diferent then product premium value["
                    + productPremium + "].";
            LOG.error(msg);
            throw new BusinessException(msg, ExceptionsConstants.CODE_PREMIUM,
                    ErrorType.BUSINESS);
        }
        return paymentMethod;
    }

    private static String maskCreditCardNumber(
            final CardPaymentDTO creditCard) {
        final String creditCardNoMask = creditCard.getCardNumber();
        final String creditCardMasked = maskCreditCardNumber(creditCardNoMask);

        return creditCardMasked;
    }

    public static String maskCreditCardNumber(final String creditCardNoMask) {
        final int total = creditCardNoMask.length();
        final int startlen = 0;
        final int endlen = 4;
        final int masklen = total - (startlen + endlen);
        final String start = creditCardNoMask.substring(0, startlen);
        final String end = creditCardNoMask.substring(startlen + masklen,
                total);
        final String padded = StringUtils.rightPad(start, startlen + masklen,
                '*');
        final String creditCardMasked = padded.concat(end);
        return creditCardMasked;
    }

    public List<InsuranceCertificate> retrieveCertificateFor(
            final String contractNumber) {
        return salesDBDAO.retrieveCertificateFor(contractNumber);
    }

    public void updatePaymentMethodContract(
            final InsuranceCertificate certificate,
            final String securityKeyID) {
        salesDBDAO.updatePaymentMethodContract(certificate, securityKeyID);
    }
}
