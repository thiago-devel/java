package br.com.resseler.directsales.sales.persistence.dao.impl;

import static br.com.resseler.directsales.commons.persistence.dao.impl.SystemPersonDAO.retriveSystemPersonBy;
import static br.com.resseler.directsales.commons.persistence.dao.util.CustomerSalesConstants.PERSISTENCE_UNIT_NAME_DIRECTSALES;
import static br.com.resseler.directsales.commons.persistence.dao.util.CustomerSalesConstants.insertPaymentForm;
import static br.com.resseler.directsales.commons.persistence.dao.util.CustomerSalesConstants.queryRetrievePaymentForm;
import static br.com.resseler.directsales.commons.persistence.dao.util.CustomerSalesConstants.queryRetriveByID;

import java.util.List;

import javax.annotation.PostConstruct;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import br.com.resseler.directsales.sales.persistence.dao.PaymentForm;
import br.com.resseler.directsales.sales.persistence.dao.rowmapper.PaymentFormRM;
import br.com.rubyit.resseler.core.commons.dto.Salesman;
import br.com.rubyit.resseler.core.commons.dto.SystemPerson;
import br.com.rubyit.resseler.core.commons.exceptions.ExceptionsConstants;
import br.com.rubyit.resseler.core.commons.exceptions.ValidationException;
import br.com.rubyit.resseler.core.enums.ErrorType;

public class PaymentDAO {

    private static final Logger LOG = LogManager.getLogger(PaymentDAO.class);

    @Autowired(required = true)
    private javax.sql.DataSource dataSourceDirectSales;
    private JdbcTemplate jdbcTemplate = null;

    @PostConstruct
    public void init() {

        jdbcTemplate = new JdbcTemplate(dataSourceDirectSales);
    }

    /**
     * Contructor used by DI
     */
    public PaymentDAO() {
        // do nothing. DI Injection point
    }

    public PaymentDAO(final javax.sql.DataSource dataSource) {
        dataSourceDirectSales = dataSource;
    }

    public void persist(final PaymentForm parans) {

        if (parans == null) {
            final String msg = "ERROR: can not persist a null PaymentForm!";
            LOG.error(msg);
            throw new ValidationException(msg,
                    ExceptionsConstants.CODE_PAYMENTFORM_NULL,
                    ErrorType.VALIDATION);
        }
        if ((parans.getID() == null) || (parans.getDescription() == null)) {
            final String msg = "ERROR: can not persist with invalid PaymentForm=["
                    + parans + "]!";
            LOG.error(msg);
            throw new ValidationException(msg,
                    ExceptionsConstants.CODE_PAYMENTFORMID_NULL,
                    ErrorType.VALIDATION);
        }

        final Object[] args = new Object[] { parans.getID(),
                parans.getDescription() };

        final int out = jdbcTemplate.update(insertPaymentForm, args);

        if (out != 0) {
            LOG.debug("PaymentForm=[" + parans + "] saved to "
                    + PERSISTENCE_UNIT_NAME_DIRECTSALES);
        } else {
            LOG.error("Fail to persist PaymentForm=[" + parans + "] to "
                    + PERSISTENCE_UNIT_NAME_DIRECTSALES);
        }

    }

    public PaymentForm retrievePaymentForm(final Long argument) {

        // using RowMapper anonymous class, we can create a separate RowMapper
        // for reuse
        final PaymentForm result = jdbcTemplate.queryForObject(
                queryRetrievePaymentForm, new Object[] { argument },
                new PaymentFormRM());

        return result;
    }

    public Salesman retrieveBy(final Integer systemPersonID) {
        Salesman salesman = null;

        final List<SystemPerson> systemPersonL = retriveSystemPersonBy(
                jdbcTemplate, queryRetriveByID, systemPersonID);

        if ((systemPersonL != null) && (!systemPersonL.isEmpty())) {
            final SystemPerson sp = systemPersonL.get(0);
            salesman = mountSalesman(sp);
        }

        return salesman;
    }

    private Salesman mountSalesman(final SystemPerson sp) {
        final Salesman salesman = new Salesman();
        salesman.setBirthDate(sp.getBirthDate());
        salesman.retrieveContacts().addAll(sp.retrieveContacts());
        salesman.setDescription(sp.getDescription());
        salesman.setDrtID(sp.getDrtID());
        salesman.setFullName(sp.getFullName());
        salesman.setGender(sp.getGender());
        salesman.setID(sp.getID());
        salesman.setIdentity(sp.getIdentity());
        salesman.setLogin(sp.getLogin());
        salesman.setMaritalStatus(sp.getMaritalStatus());
        salesman.setMlID(sp.getMlID());
        salesman.setPartner(sp.getPartner());
        salesman.setRole(sp.getRole());
        salesman.setSUP(sp.getSUP());
        salesman.setType(sp.getType());

        return salesman;
    }
}
