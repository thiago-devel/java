package br.com.resseler.directsales.sales.persistence.dao.impl;

import static br.com.resseler.directsales.commons.persistence.dao.util.CustomerSalesConstants.CARD_DATA_ID;
import static br.com.resseler.directsales.commons.persistence.dao.util.CustomerSalesConstants.CARD_DATA_ID_FK;
import static br.com.resseler.directsales.commons.persistence.dao.util.CustomerSalesConstants.CARD_EXPIRATION_MONTH_YEAR;
import static br.com.resseler.directsales.commons.persistence.dao.util.CustomerSalesConstants.CARD_FLAG;
import static br.com.resseler.directsales.commons.persistence.dao.util.CustomerSalesConstants.CARD_NBR;
import static br.com.resseler.directsales.commons.persistence.dao.util.CustomerSalesConstants.CARD_NME;
import static br.com.resseler.directsales.commons.persistence.dao.util.CustomerSalesConstants.CARD_SECURITY;
import static br.com.resseler.directsales.commons.persistence.dao.util.CustomerSalesConstants.CARD_VALUE;
import static br.com.resseler.directsales.commons.persistence.dao.util.CustomerSalesConstants.CRD_AUTHORIZATION_NUMBER;
import static br.com.resseler.directsales.commons.persistence.dao.util.CustomerSalesConstants.CRD_CARD_FLAG;
import static br.com.resseler.directsales.commons.persistence.dao.util.CustomerSalesConstants.CRD_CARD_ID;
import static br.com.resseler.directsales.commons.persistence.dao.util.CustomerSalesConstants.CRD_CARD_MONTH_YEAR;
import static br.com.resseler.directsales.commons.persistence.dao.util.CustomerSalesConstants.CRD_CARD_NBR;
import static br.com.resseler.directsales.commons.persistence.dao.util.CustomerSalesConstants.CRD_CARD_NME;
import static br.com.resseler.directsales.commons.persistence.dao.util.CustomerSalesConstants.CRD_CARD_PRT;
import static br.com.resseler.directsales.commons.persistence.dao.util.CustomerSalesConstants.CRD_CARD_SECURITY;
import static br.com.resseler.directsales.commons.persistence.dao.util.CustomerSalesConstants.CRD_DATE_OF_TRANSACT;
import static br.com.resseler.directsales.commons.persistence.dao.util.CustomerSalesConstants.CRD_HOUR_OF_TRANSACT;
import static br.com.resseler.directsales.commons.persistence.dao.util.CustomerSalesConstants.CRD_NUMBER_OF_TRANSACT;
import static br.com.resseler.directsales.commons.persistence.dao.util.CustomerSalesConstants.CRD_TERMINAL_NUMBER;
import static br.com.resseler.directsales.commons.persistence.dao.util.CustomerSalesConstants.KEY_CARD_ID;
import static br.com.resseler.directsales.commons.persistence.dao.util.CustomerSalesConstants.K_ID;
import static br.com.resseler.directsales.commons.persistence.dao.util.CustomerSalesConstants.PERSISTENCE_UNIT_NAME_CONTRACT;
import static br.com.resseler.directsales.commons.persistence.dao.util.CustomerSalesConstants.PERSISTENCE_UNIT_NAME_DIRECTSALES;
import static br.com.resseler.directsales.commons.persistence.dao.util.CustomerSalesConstants.TABLE_CARD_DATA;
import static br.com.resseler.directsales.commons.persistence.dao.util.CustomerSalesConstants.TABLE_KEY_CARD;
import static br.com.resseler.directsales.commons.persistence.dao.util.CustomerSalesConstants.TABLE_PRD_CREDIT_CARD;
import static br.com.resseler.directsales.commons.persistence.dao.util.CustomerSalesConstants.queryRetrieveCardPayment;
import static br.com.rubyit.resseler.core.utils.ConverterUtil.FORMAT_MM_SLASH_YY;
import static br.com.rubyit.resseler.core.utils.ConverterUtil.formaDateToString;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.validation.ValidationException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;

import br.com.resseler.directsales.sales.persistence.dao.rowmapper.CardPaymentDTORM;
import br.com.resseler.directsales.sales.persistence.dao.rowmapper.CreditCardEncriptedRM;
import br.com.rubyit.resseler.core.commons.dto.CardPaymentDTO;

public class CardPaymentDAO {

    private static final Logger LOG = LogManager.getLogger(CardPaymentDAO.class);

    @Autowired(required = true)
    @Qualifier("dataSourceDirectSales")
    private javax.sql.DataSource dataSourceDirectSales;

    @Autowired(required = true)
    @Qualifier("dataSourceContract")
    protected javax.sql.DataSource dataSourceContract;
    private JdbcTemplate jdbcTemplateD = null;
    private JdbcTemplate jdbcTemplateC = null;

    @PostConstruct
    public void init() {

        jdbcTemplateD = new JdbcTemplate(dataSourceDirectSales);
        jdbcTemplateC = new JdbcTemplate(dataSourceContract);
    }

    /**
     * Contructor used by DI
     */
    public CardPaymentDAO() {
        // do nothing. DI Injection point
    }

    public CardPaymentDAO(final javax.sql.DataSource dataSource) {
        dataSourceDirectSales = dataSource;
    }

    public Integer persist(final CardPaymentDTO cardPayment) {

        validateCardPayment(cardPayment);

        final SimpleJdbcInsert insert = new SimpleJdbcInsert(jdbcTemplateD).withTableName(TABLE_PRD_CREDIT_CARD)
                .usingGeneratedKeyColumns(CRD_CARD_ID);
        final MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource.addValue(CRD_DATE_OF_TRANSACT, null);
        parameterSource.addValue(CRD_NUMBER_OF_TRANSACT, null);
        parameterSource.addValue(CRD_AUTHORIZATION_NUMBER, null);
        parameterSource.addValue(CRD_TERMINAL_NUMBER, null);
        parameterSource.addValue(CRD_CARD_SECURITY, cardPayment.getCardSecurityCode());
        final String cardValidity = (cardPayment.getCardValidity() == null) ? null
                : formaDateToString(FORMAT_MM_SLASH_YY, cardPayment.getCardValidity().getTime());
        parameterSource.addValue(CRD_CARD_MONTH_YEAR, cardValidity);
        parameterSource.addValue(CRD_CARD_FLAG, cardPayment.getCardFlag());
        parameterSource.addValue(CRD_CARD_NBR, cardPayment.getCardNumber());
        parameterSource.addValue(CRD_CARD_NME, cardPayment.getCardDisplayName());
        parameterSource.addValue(CRD_HOUR_OF_TRANSACT, null);
        parameterSource.addValue(CRD_CARD_PRT, null);
        final SqlParameterSource parameters = parameterSource;
        final Number key = insert.executeAndReturnKey(parameters);

        if (key == null) {
            LOG.error("Fail to persist CardPayment[" + cardPayment + "] to " + PERSISTENCE_UNIT_NAME_DIRECTSALES);
        }

        LOG.debug("CardPayment[" + cardPayment + "] saved to " + PERSISTENCE_UNIT_NAME_DIRECTSALES + " with ID=[" + key
                + "]");

        return (key == null) ? null : key.intValue();
    }

    public Long persistUpdateCardPayment(final CardPaymentDTO cardPayment) {

        validateCardPayment(cardPayment);

        final SimpleJdbcInsert insert = new SimpleJdbcInsert(jdbcTemplateC).withTableName(TABLE_CARD_DATA)
                .usingGeneratedKeyColumns(CARD_DATA_ID);
        final MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource.addValue(CARD_NBR, cardPayment.getCardNumber());
        parameterSource.addValue(CARD_NME, cardPayment.getCardDisplayName());
        parameterSource.addValue(CARD_EXPIRATION_MONTH_YEAR, cardPayment.getCardValidity());
        parameterSource.addValue(CARD_SECURITY, cardPayment.getCardSecurityCode());
        parameterSource.addValue(CARD_VALUE, cardPayment.getCardValue());
        parameterSource.addValue(CARD_FLAG, cardPayment.getCardFlag());
        final SqlParameterSource parameters = parameterSource;
        final Number key = insert.executeAndReturnKey(parameters);

        if (key == null) {
            LOG.error("Fail to persist the update of CardPayment[" + cardPayment + "] data to "
                    + PERSISTENCE_UNIT_NAME_CONTRACT);
        }

        LOG.debug("CardPayment[" + cardPayment + "] data update saved to " + PERSISTENCE_UNIT_NAME_CONTRACT
                + " with ID=[" + key + "]");

        return (key == null) ? null : key.longValue();
    }

    private void validateCardPayment(final CardPaymentDTO cardPayment) {
        if (cardPayment == null) {
            final String msg = "ERROR: can not persist a null CardPayment!";
            LOG.error(msg);
            throw new ValidationException(msg);
        }
    }

    public Integer persistUpdateCardPaymentSecurityKey(final CardPaymentDTO cardPayment, final String securityKeyID,
            final Long cardPaymentID) {

        validadeCardPaymentSecurity(cardPayment, securityKeyID, cardPaymentID);

        final SimpleJdbcInsert insert = new SimpleJdbcInsert(jdbcTemplateC).withTableName(TABLE_KEY_CARD)
                .usingGeneratedKeyColumns(KEY_CARD_ID);
        final MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource.addValue(K_ID, securityKeyID);
        parameterSource.addValue(CARD_DATA_ID_FK, cardPaymentID);
        final SqlParameterSource parameters = parameterSource;
        final Number key = insert.executeAndReturnKey(parameters);

        if (key == null) {
            LOG.error("Fail to persist the update of CardPayment[" + cardPayment + "], securityKeyID=[" + securityKeyID
                    + "], cardPaymentID=[" + cardPaymentID + "] security data to " + PERSISTENCE_UNIT_NAME_CONTRACT);
        }

        LOG.debug("CardPayment[" + cardPayment + "], securityKeyID=[" + securityKeyID + "], cardPaymentID=["
                + cardPaymentID + "] security data update saved to " + PERSISTENCE_UNIT_NAME_CONTRACT + " with ID=["
                + key + "]");

        return (key == null) ? null : key.intValue();
    }

    private void validadeCardPaymentSecurity(final CardPaymentDTO cardPayment, final String keyID,
            final Long cardPaymentID) {
        validateCardPayment(cardPayment);

        if (keyID == null) {
            final String msg = "ERROR: can not persist a CardPayment with a null keyID!";
            LOG.error(msg);
            throw new ValidationException(msg);
        }

        if (cardPaymentID == null) {
            final String msg = "ERROR: can not persist a CardPayment with a null cardPaymentID!";
            LOG.error(msg);
            throw new ValidationException(msg);
        }
    }

    public CardPaymentDTO retrieveBy(final Long cardPaymentID) {

        final List<CardPaymentDTO> productPrefixL = jdbcTemplateD.query(queryRetrieveCardPayment,
                new CardPaymentDTORM(), new Object[] { cardPaymentID });

        final CardPaymentDTO productPrefix = ((productPrefixL == null)
                || (productPrefixL.isEmpty() || (productPrefixL.contains(null)))) ? null : productPrefixL.get(0);

        return productPrefix;
    }

    public static String creditCardEncript(final String creditCardNumber, final javax.sql.DataSource dataSource) {
        if (dataSource == null) {
            final String msg = "ERROR: can not encript CreditCardNumber with a null dataSource!";
            LOG.error(msg);
            throw new ValidationException(msg);
        }
        if ((creditCardNumber == null) || "".equals(creditCardNumber.trim())) {
            final String msg = "ERROR: can not encript a invalid CreditCardNumber=[" + creditCardNumber + "]!";
            LOG.error(msg);
            throw new ValidationException(msg);
        }
        final JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

        final String creditCardEncripted = jdbcTemplate.queryForObject(
                "select dbo.udf_EncryptCreditCard('" + creditCardNumber + "', 16) as " + "CARD_NBR_CRIPT", null,
                new CreditCardEncriptedRM());

        return creditCardEncripted;
    }
}
