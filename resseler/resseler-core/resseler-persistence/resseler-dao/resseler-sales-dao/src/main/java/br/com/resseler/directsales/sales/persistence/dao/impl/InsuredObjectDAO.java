package br.com.resseler.directsales.sales.persistence.dao.impl;

import static br.com.resseler.directsales.commons.persistence.dao.util.CustomerSalesConstants.CERTIFICATE_ID;
import static br.com.resseler.directsales.commons.persistence.dao.util.CustomerSalesConstants.INSURED_OBJECT_COD;
import static br.com.resseler.directsales.commons.persistence.dao.util.CustomerSalesConstants.INSURED_OBJECT_ID;
import static br.com.resseler.directsales.commons.persistence.dao.util.CustomerSalesConstants.PERSISTENCE_UNIT_NAME_DIRECTSALES;
import static br.com.resseler.directsales.commons.persistence.dao.util.CustomerSalesConstants.TABLE_INSURED_OBJECT;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;

import br.com.rubyit.resseler.core.commons.dto.InsuranceCertificate;
import br.com.rubyit.resseler.core.commons.exceptions.ExceptionsConstants;
import br.com.rubyit.resseler.core.commons.exceptions.ValidationException;
import br.com.rubyit.resseler.core.enums.ErrorType;

/**
 * Data Access Object do objecto Insured
 * @author b11527
 *
 */
public class InsuredObjectDAO {

    private static Logger log = LogManager.getLogger(InsuredObjectDAO.class);

    @Autowired(required = true)
    protected javax.sql.DataSource dataSourceDirectSales;
    private JdbcTemplate jdbcTemplate = null;

    /**
     * PostConstruct
     * initialize jdbcTemplate
     */
    @PostConstruct
    public void init() {
        jdbcTemplate = new JdbcTemplate(dataSourceDirectSales);
    }

    /**
     * Persiste INSURED OBJECT
     * @param certificate
     * @param insuredObjectCode
     * @return Integer
     */
    public Integer persist(final InsuranceCertificate certificate,
            final Integer insuredObjectCode) {

        if ((certificate == null) || (insuredObjectCode == null)) {
            final String msg = "ERROR: can not persist a null Insured Object Data! Data{certificate=["
                    + certificate + "],insuredObjectCode=[" + insuredObjectCode
                    + "]}";
            log.error(msg);
            throw new ValidationException(msg,ExceptionsConstants.CODE_INS_CERTIFICATE_NULL,ErrorType.VALIDATION);
        }

        final Map<String, Object> args = new HashMap<>();
        args.put(CERTIFICATE_ID, certificate.getID());
        args.put(INSURED_OBJECT_COD, insuredObjectCode);

        final SimpleJdbcInsert insert = new SimpleJdbcInsert(jdbcTemplate)
                .withTableName(TABLE_INSURED_OBJECT)
                .usingGeneratedKeyColumns(INSURED_OBJECT_ID);
        final Number key = insert.executeAndReturnKey(args);

        if (key == null) {
            log.error("Fail to persist Insured Object to "
                    + PERSISTENCE_UNIT_NAME_DIRECTSALES);
        }

        log.debug("Insured Object saved to " + PERSISTENCE_UNIT_NAME_DIRECTSALES
                + " with ID=[" + key + "]");

        return (key == null) ? null : key.intValue();
    }

}
