package br.com.rubyit.resseler.core.business.facade;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import br.com.rubyit.resseler.core.business.customer.impl.CustomerBusinessImpl;
import br.com.rubyit.resseler.core.commons.dto.Customer;
import br.com.rubyit.resseler.core.commons.dto.InsuranceCertificate;
import br.com.rubyit.resseler.core.commons.exceptions.ExceptionsConstants;
import br.com.rubyit.resseler.core.commons.exceptions.RawException;
import br.com.rubyit.resseler.core.commons.exceptions.RetrieveCustomerException;
import br.com.rubyit.resseler.core.enums.ErrorType;

/**
 * 
 * CustomerFacade
 *
 */
public class CustomerFacade extends BaseFacade {
    private static Set<String> servicosCustomerEmExecucao = new HashSet<>();
    private static final Logger LOG = LogManager.getLogger(CustomerFacade.class);

    @Autowired
    private CustomerBusinessImpl customerBusiness;

    /**
     * Default Constructor used by IOC
     */
    public CustomerFacade() {
        // do nothing. IOC Injection point
    }

    /**
     * Constructor CustomerFacade with param
     * @param customerBusiness
     */
    public CustomerFacade(final CustomerBusinessImpl customerBusiness) {
        this.customerBusiness = customerBusiness;
    }

    /**
     * Retorna segunda via do cliente
     * @param customer
     * @return lista de certificados do cliente
     *
     */
    public List<InsuranceCertificate> retrieveCustomerSales(
            final Customer customer) {

        List<InsuranceCertificate> insuranceCertificates = new ArrayList<>();
        final String[] hashID = prepareHashID();

        if (customer != null && customer.getIdentity() != null) {
            hashID[0] = customer.getIdentity().getDocumentValue();
        }
        // Controle de resubmit
        try {
            // Tratamento para não ocorrer transações duplicadas
            iniciarExecucao("retrieveCustomerSales", hashID,
                    servicosCustomerEmExecucao);
            insuranceCertificates = customerBusiness
                    .retrieveCustomerSales(customer);
            return insuranceCertificates;

        } catch (final RawException ex) {
            final String msg = "ERROR: business process retrieveCustomerSales fail!"
                    + ex;
            LOG.error(msg);
            throw new RetrieveCustomerException(msg, ex, ex.getCode(),
                    ex.getErrorType());
        } catch (final Exception ex) {
            final String msg = "ERROR: business process retrieveCustomerSales fail!"
                    + ex;
            LOG.error(msg);
            throw new RetrieveCustomerException(msg, ex,
                    ExceptionsConstants.CODE_GENERIC, ErrorType.SYSTEM);
        } finally {
            // Controle de resubmit
            finalizarExecucao("retrieveCustomerSales", hashID,
                    servicosCustomerEmExecucao);
        }
    }
}
