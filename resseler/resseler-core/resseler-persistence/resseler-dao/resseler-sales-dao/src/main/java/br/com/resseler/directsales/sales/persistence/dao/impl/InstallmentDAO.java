package br.com.resseler.directsales.sales.persistence.dao.impl;

import static br.com.resseler.directsales.commons.persistence.dao.util.CustomerSalesConstants.CERTIFICATE_EFF_DT;
import static br.com.resseler.directsales.commons.persistence.dao.util.CustomerSalesConstants.CERTIFICATE_ID;
import static br.com.resseler.directsales.commons.persistence.dao.util.CustomerSalesConstants.CERTIFICATE_PRM_VL;
import static br.com.resseler.directsales.commons.persistence.dao.util.CustomerSalesConstants.INST_DUE_DT;
import static br.com.resseler.directsales.commons.persistence.dao.util.CustomerSalesConstants.INST_ID;
import static br.com.resseler.directsales.commons.persistence.dao.util.CustomerSalesConstants.INST_INS_DTM;
import static br.com.resseler.directsales.commons.persistence.dao.util.CustomerSalesConstants.INST_NUM_DOC;
import static br.com.resseler.directsales.commons.persistence.dao.util.CustomerSalesConstants.INST_ORD_NBR;
import static br.com.resseler.directsales.commons.persistence.dao.util.CustomerSalesConstants.INST_PRM_VL;
import static br.com.resseler.directsales.commons.persistence.dao.util.CustomerSalesConstants.PERSISTENCE_UNIT_NAME_DIRECTSALES;
import static br.com.resseler.directsales.commons.persistence.dao.util.CustomerSalesConstants.TABLE_INSTALLMENT;

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

public class InstallmentDAO {

    private static Logger log = LogManager.getLogger(InstallmentDAO.class);

    @Autowired(required = true)
    protected javax.sql.DataSource dataSourceDirectSales;
    private JdbcTemplate jdbcTemplate = null;

    @PostConstruct
    public void init() {

        jdbcTemplate = new JdbcTemplate(dataSourceDirectSales);
    }

    /**
     * 
     * @param certificate
     * @param installmentOrderNumber
     * @param parans
     * @return
     */
    public Integer persist(final InsuranceCertificate certificate, final Integer installmentOrderNumber,
            final Map<String, Object> parans) {

        if ((certificate == null) || (installmentOrderNumber == null) || (parans == null)) {
            final String msg = "ERROR: can not persist a null Installment Data! Data{certificate=[" + certificate
                    + "],installmentOrderNumber=[" + installmentOrderNumber + "],parans=[" + parans + "]}";
            log.error(msg);
            throw new ValidationException(msg,ExceptionsConstants.CODE_INS_CERTIFICATE_NULL,ErrorType.VALIDATION);
        }

        final Map<String, Object> args = new HashMap<>();
        args.put(CERTIFICATE_ID, certificate.getID());
        final String installmentDocNumber = generateInstallmentDocNumber(certificate);
        args.put(INST_NUM_DOC, installmentDocNumber);
        args.put(INST_DUE_DT, parans.get(CERTIFICATE_EFF_DT));
        args.put(INST_PRM_VL, parans.get(CERTIFICATE_PRM_VL));
        args.put(INST_ORD_NBR, installmentOrderNumber);
        args.put(INST_INS_DTM, parans.get(CERTIFICATE_EFF_DT));

        final SimpleJdbcInsert insert = new SimpleJdbcInsert(jdbcTemplate).withTableName(TABLE_INSTALLMENT)
                .usingGeneratedKeyColumns(INST_ID);
        final Number key = insert.executeAndReturnKey(args);

        if (key == null) {
            log.error("Fail to persist Installment to " + PERSISTENCE_UNIT_NAME_DIRECTSALES);
        }

        log.debug("Installment saved to " + PERSISTENCE_UNIT_NAME_DIRECTSALES + " with ID=[" + key + "]");

        return (key == null) ? null : key.intValue();
    }

    /**
     * 
     * @param certificate
     * @return
     */
    private String generateInstallmentDocNumber(final InsuranceCertificate certificate) {
        if ((certificate.getContractNumber() == null) || "".equals(certificate.getContractNumber().trim())) {
            final String msg = "ERROR: can not persist Installment Data without contract contractNumber! Data{certificate=["
                    + certificate + "]}";
            log.error(msg);
            throw new ValidationException(msg,ExceptionsConstants.CODE_INSCERT_CONTRACT_NULL,ErrorType.VALIDATION);
        }

        final String installmentDocNumber = certificate.getContractNumber() + "01";
        return installmentDocNumber;
    }

}
