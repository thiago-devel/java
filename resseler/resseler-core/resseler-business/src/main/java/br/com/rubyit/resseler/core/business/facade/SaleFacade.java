package br.com.rubyit.resseler.core.business.facade;

import java.util.Calendar;
import java.util.HashSet;
import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import br.com.rubyit.resseler.commons.kernel.dto.ProductDTO;
import br.com.rubyit.resseler.core.business.sale.impl.SaleBusinessImpl;
import br.com.rubyit.resseler.core.commons.dto.CommissionsPagingDTO;
import br.com.rubyit.resseler.core.commons.dto.InsuranceCertificate;
import br.com.rubyit.resseler.core.commons.dto.Regional;
import br.com.rubyit.resseler.core.commons.dto.Sale;
import br.com.rubyit.resseler.core.commons.dto.Salesman;
import br.com.rubyit.resseler.core.commons.dto.ScorePagingDTO;
import br.com.rubyit.resseler.core.commons.dto.Workshop;
import br.com.rubyit.resseler.core.commons.exceptions.CheckPaymentMethodException;
import br.com.rubyit.resseler.core.commons.exceptions.DoSaleException;
import br.com.rubyit.resseler.core.commons.exceptions.ExceptionsConstants;
import br.com.rubyit.resseler.core.commons.exceptions.RawException;
import br.com.rubyit.resseler.core.commons.exceptions.RetrieveComissionsException;
import br.com.rubyit.resseler.core.commons.exceptions.RetrieveSalesException;
import br.com.rubyit.resseler.core.commons.exceptions.SalesmanException;
import br.com.rubyit.resseler.core.enums.ErrorType;

public class SaleFacade extends BaseFacade {

    private static Set<String> servicosSalesEmExecucao = new HashSet<>();
    private static final Logger LOG = LogManager.getLogger(SaleFacade.class);

    @Autowired
    private SaleBusinessImpl salesBusiness;

    /**
     * Default Constructor used by IOC
     */
    public SaleFacade() {
        // do nothing. IOC Injection point
    }

    public SaleFacade(final SaleBusinessImpl salesBusiness) {
        this.salesBusiness = salesBusiness;
    }

    /**
     * Método que realiza a venda de um determinado produto
     *
     * @param sale
     * @return
     */
    public InsuranceCertificate doSale(final Sale sale) {
        InsuranceCertificate insuranceCertificate = new InsuranceCertificate();

        final String[] hashID = prepareHashID();

        if ((sale.getCustomer() != null)
                && (sale.getCustomer().getIdentity() != null)) {
            hashID[0] = sale.getCustomer().getIdentity().getDocumentValue();
        }

        // Controle de resubmit
        try {
            // Tratamento para não ocorrer transações duplicadas
            iniciarExecucao("doSale", hashID, servicosSalesEmExecucao);

            insuranceCertificate = salesBusiness.doSale(sale);

            return insuranceCertificate;

        } catch (final RawException ex) {
            final String msg = "ERROR: business process doSale fail!" + ex;
            LOG.error(msg);
            throw new DoSaleException(msg, ex, ex.getCode(), ex.getErrorType());

        } catch (final Exception ex) {
            final String msg = "ERROR: business process doSale fail!" + ex;
            LOG.error(msg);
            throw new DoSaleException(msg, ex, ExceptionsConstants.CODE_GENERIC,
                    ErrorType.SYSTEM);

        } finally {
            // Controle de resubmit
            finalizarExecucao("doSale", hashID, servicosSalesEmExecucao);
        }

    }

    /**
     * Histórico de vendas e comissões de um determinado vendedor
     *
     * @param salesman
     * @param pageNumber
     * @return
     */
    public CommissionsPagingDTO retrieveComissions(final Salesman salesman,
            final Integer pageNumber) {

        CommissionsPagingDTO insuranceCertificates = null;

        final String[] hashID = prepareHashID();

        if ((salesman != null) && (salesman.getIdentity() != null)) {
            hashID[0] = salesman.getIdentity().getDocumentValue();
        }

        // Controle de resubmit
        try {
            // Tratamento para não ocorrer transações duplicadas
            iniciarExecucao("retrieveComissions", hashID,
                    servicosSalesEmExecucao);

            insuranceCertificates = salesBusiness.retrieveComissions(salesman,
                    pageNumber);

            return insuranceCertificates;

        } catch (final RawException ex) {
            final String msg = "ERROR: business process retrieveComissions fail!"
                    + ex;
            LOG.error(msg);
            throw new RetrieveComissionsException(msg, ex, ex.getCode(),
                    ex.getErrorType());
        } catch (final Exception ex) {
            final String msg = "ERROR: business process retrieveComissions fail!"
                    + ex;
            LOG.error(msg);
            throw new RetrieveComissionsException(msg, ex,
                    ExceptionsConstants.CODE_GENERIC, ErrorType.SYSTEM);
        } finally {
            // Controle de resubmit
            finalizarExecucao("retrieveComissions", hashID,
                    servicosSalesEmExecucao);
        }

    }

    /**
     * Histórico de vendas de um determinado período, por vendedor/regional/vendedor/filial
     *
     * @param certificate
     * @param regional
     * @param workshop
     * @param salesman
     * @param startDate
     * @param endDate
     * @param pageNumber
     * @return
     */
    public ScorePagingDTO retrievePerformance(final ProductDTO product,
            final Regional regional, final Workshop workshop,
            final Salesman salesman, final Calendar startDate,
            final Calendar endDate, final Integer pageNumber) {

        ScorePagingDTO scorePagingDTO = null;

        final String[] hashID = prepareHashID();

        if ((startDate != null) && (endDate != null)) {
            hashID[0] = startDate.toString() + endDate.toString();
        }

        // Controle de resubmit
        try {
            // Tratamento para não ocorrer transações duplicadas
            iniciarExecucao("retrievePerformance", hashID,
                    servicosSalesEmExecucao);

            scorePagingDTO = salesBusiness.retrievePerformance(product,
                    regional, workshop, salesman, startDate, endDate,
                    pageNumber);

            return scorePagingDTO;

        } catch (final RawException ex) {
            final String msg = "ERROR: business process retrieveSales fail!"
                    + ex;
            LOG.error(msg);
            throw new RetrieveSalesException(msg, ex, ex.getCode(),
                    ex.getErrorType());
        } catch (final Exception ex) {
            final String msg = "ERROR: business process retrieveSales fail!"
                    + ex;
            LOG.error(msg);
            throw new RetrieveSalesException(msg, ex,
                    ExceptionsConstants.CODE_GENERIC, ErrorType.SYSTEM);
        } finally {
            // Controle de resubmit
            finalizarExecucao("retrievePerformance", hashID,
                    servicosSalesEmExecucao);
        }

    }

    public Boolean checkUpdateMethod(final InsuranceCertificate certificate) {
        if (certificate == null) { return false; }

        final String[] hashID = prepareHashID();

        // Controle de resubmit
        try {
            // Tratamento para não ocorrer transações duplicadas
            hashID[0] = certificate.toString();
            iniciarExecucao("checkUpdateMethod", hashID,
                    servicosSalesEmExecucao);

            return salesBusiness.checkUpdateMethod(certificate);
        } catch (final RawException ex) {
            final String msg = "ERROR: business process checkUpdateMethod fail!"
                    + ex;
            LOG.error(msg);
            throw new CheckPaymentMethodException(msg, ex, ex.getCode(),
                    ex.getErrorType());
        } catch (final Exception ex) {
            final String msg = "ERROR: business process checkUpdateMethod fail!"
                    + ex;
            LOG.error(msg);
            throw new CheckPaymentMethodException(msg, ex,
                    ExceptionsConstants.CODE_GENERIC, ErrorType.SYSTEM);
        } finally {
            // Controle de resubmit
            finalizarExecucao("checkUpdateMethod", hashID,
                    servicosSalesEmExecucao);
        }
    }

    /**
     * Atualiza meio de pagamento de um determinado contrato
     *
     * @param certificate
     * @param securityKeyID
     * @return
     */
    public Boolean updatePaymentMethodContract(
            final InsuranceCertificate certificate,
            final String securityKeyID) {
        if (certificate == null) { return false; }

        final String[] hashID = prepareHashID();

        // Controle de resubmit
        try {
            // Tratamento para não ocorrer transações duplicadas
            hashID[0] = certificate.toString() + securityKeyID;
            iniciarExecucao("updatePaymentMethodContract", hashID,
                    servicosSalesEmExecucao);

            salesBusiness.updatePaymentMethodContract(certificate,
                    securityKeyID);

            return true;
        } catch (final RawException ex) {
            final String msg = "ERROR: business process updatePaymentMethodContract fail!"
                    + ex;
            LOG.error(msg);
            throw new CheckPaymentMethodException(msg, ex, ex.getCode(),
                    ex.getErrorType());
        } catch (final Exception ex) {
            final String msg = "ERROR: business process updatePaymentMethodContract fail!"
                    + ex;
            LOG.error(msg);
            throw new CheckPaymentMethodException(msg, ex,
                    ExceptionsConstants.CODE_GENERIC, ErrorType.SYSTEM);
        } finally {
            // Controle de resubmit
            finalizarExecucao("updatePaymentMethodContract", hashID,
                    servicosSalesEmExecucao);
        }
    }

    /**
     * Método responsavel por cadastrar um vendedor
     *
     * @param salesman
     *            Possui as informações da entidade Usuário, Pessoa e Vendedor
     * @return true caso o registro foi salvo com sucesso
     */
    public boolean saveSalesMan(final Salesman salesman) {

        final String[] hashID = prepareHashID();

        if ((salesman != null) && (salesman.getIdentity() != null)) {
            hashID[0] = salesman.getIdentity().getDocumentValue();
        }

        // Controle de resubmit
        try {
            // Tratamento para não ocorrer transações duplicadas
            iniciarExecucao("saveSalesMan", hashID, servicosSalesEmExecucao);
            salesBusiness.saveSalesman(salesman);

            return true;

        } catch (final RawException ex) {
            final String msg = "ERROR: business process saveSalesMan fail!"
                    + ex;
            LOG.error(msg);
            throw new SalesmanException(msg, ex, ex.getCode(),
                    ex.getErrorType());
        } catch (final Exception ex) {
            final String msg = "ERROR: business process saveSalesMan fail!"
                    + ex;
            LOG.error(msg);
            throw new SalesmanException(msg, ex,
                    ExceptionsConstants.CODE_GENERIC, ErrorType.SYSTEM);
        } finally {
            // Controle de resubmit
            finalizarExecucao("saveSalesMan", hashID, servicosSalesEmExecucao);
        }

    }

    /**
     * Atualizar ramo e filial do vendedor por cpf
     *
     * @param salesman
     * @return
     */
    public boolean updateSalesmanBranch(final Salesman salesman) {

        final String[] hashID = prepareHashID();

        if ((salesman != null) && (salesman.getIdentity() != null)) {
            hashID[0] = salesman.getIdentity().getDocumentValue();
        }

        // Controle de resubmit
        try {
            // Tratamento para não ocorrer transações duplicadas
            iniciarExecucao("updateBranchSalesman", hashID,
                    servicosSalesEmExecucao);
            salesBusiness.updateSalesmanBranch(salesman);

            return true;

        } catch (final RawException ex) {
            final String msg = "ERROR: business process updateBranchSalesman fail!"
                    + ex;
            LOG.error(msg);
            throw new SalesmanException(msg, ex, ex.getCode(),
                    ex.getErrorType());
        } catch (final Exception ex) {
            final String msg = "ERROR: business process updateBranchSalesman fail!"
                    + ex;
            LOG.error(msg);
            throw new SalesmanException(msg, ex,
                    ExceptionsConstants.CODE_GENERIC, ErrorType.SYSTEM);
        } finally {
            // Controle de resubmit
            finalizarExecucao("updateBranchSalesman", hashID,
                    servicosSalesEmExecucao);
        }
    }

    /**
     * Selecionar o ramo e a filial do último vendedor adicionado
     *
     * @param salesman
     * @return
     */
    public Salesman findSalesmanBch(final Salesman salesman) {
        final String[] hashID = prepareHashID();

        if ((salesman != null) && (salesman.getSalesmanBch() != null)) {
            hashID[0] = salesman.getSalesmanBch().toString() + salesman.getRegionName();
        }

        try {
            // Tratamento para não ocorrer transações duplicadas
            iniciarExecucao("findSalesmanBch", hashID,
                    servicosSalesEmExecucao);
            
            return salesBusiness.findSalesmanBch(salesman);

        } catch (final RawException ex) {
            final String msg = "ERROR: business process findSalesmanBch fail!"
                    + ex;
            LOG.error(msg);
            throw new SalesmanException(msg, ex, ex.getCode(),
                    ex.getErrorType());
        } catch (final Exception ex) {
            final String msg = "ERROR: business process findSalesmanBch fail!"
                    + ex;
            LOG.error(msg);
            throw new SalesmanException(msg, ex,
                    ExceptionsConstants.CODE_GENERIC, ErrorType.SYSTEM);
        } finally {
            // Controle de resubmit
            finalizarExecucao("findSalesmanBch", hashID,
                    servicosSalesEmExecucao);
        }
    }

}
