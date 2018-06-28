package br.com.resseler.directsales.commons.persistence.dao.impl;

import static br.com.resseler.directsales.commons.persistence.dao.util.CustomerSalesConstants.CAPITAL_SERIES_ID;
import static br.com.resseler.directsales.commons.persistence.dao.util.CustomerSalesConstants.POLICY_NO;
import static br.com.resseler.directsales.commons.persistence.dao.util.CustomerSalesConstants.PRD_ID;
import static br.com.resseler.directsales.commons.persistence.dao.util.CustomerSalesConstants.PRODUCT_CARTAO_SUPERPROTEGIDO;
import static br.com.resseler.directsales.commons.persistence.dao.util.CustomerSalesConstants.PRODUCT_PROTECAO_PREMIADA;
import static br.com.resseler.directsales.commons.persistence.dao.util.CustomerSalesConstants.RESULTSET_CAPITAL_SERIES;
import static br.com.resseler.directsales.commons.persistence.dao.util.CustomerSalesConstants.SP_GET_NEXT_CAPITALSERIES_ID;
import static br.com.resseler.directsales.commons.persistence.dao.util.CustomerSalesConstants.SP_GET_NEXT_CAPITALSERIES_ID_GARANTIA;
import static br.com.resseler.directsales.commons.persistence.dao.util.CustomerSalesConstants.queryRetrieveCapitalSeries;
import static br.com.resseler.directsales.commons.persistence.dao.util.CustomerSalesConstants.queryRetrieveCapitalSeriesByCertficate;
import static br.com.resseler.directsales.commons.persistence.dao.util.CustomerSalesConstants.updateCleanCertificateInCapitalSeries;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;

import br.com.resseler.directsales.commons.persistence.dao.rowmappers.CapitalSeriesDataRM;
import br.com.rubyit.resseler.core.commons.exceptions.BusinessException;
import br.com.rubyit.resseler.core.commons.exceptions.ExceptionsConstants;
import br.com.rubyit.resseler.core.commons.exceptions.ValidationException;
import br.com.rubyit.resseler.core.enums.ErrorType;

public class CapitalSeriesDAO {

    private static Logger log = LogManager.getLogger(CapitalSeriesDAO.class);
    private SimpleJdbcCall simpleJdbcCall = null;

    @Autowired(required = true)
    protected javax.sql.DataSource dataSourceDirectSales;

    public String retrieveCapitalSeriesIDFrom(final String certificateNumber) {

        if ((certificateNumber == null)
                || "".equals(certificateNumber.trim())) {
            final String msg = "ERROR: can't retrieve capitalSeriesID with certificateNumber["
                    + certificateNumber + "] NULL or empty";
            log.error(msg);
            throw new ValidationException(msg,
                    ExceptionsConstants.CODE_CAPITALSERIESID_NULL,
                    ErrorType.VALIDATION);
        }

        final Map<String, Object> result = callSPGetNextCapitalSeriesID(
                certificateNumber);
        log.debug("callSPGetNextCapitalSeriesID[result=" + result + "]");
        log.debug("callSPGetNextCapitalSeriesID[result.keySet()="
                + result.keySet() + "]");

        final List<Object> object = (List<Object>) result
                .get(RESULTSET_CAPITAL_SERIES);
        log.debug("result.get(RESULTSET_CAPITAL_SERIES)[" + object + "]");

        if ((object == null) || (object.isEmpty())) {
            final String msg = "ERROR: Sem Capital Series disponível para uso!";
            log.error(msg);
            throw new BusinessException(msg,
                    ExceptionsConstants.CODE_NO_CAPITALSERIES_USE,
                    ErrorType.BUSINESS);
        }

        String capitalSeriesID = null;
        final Map<String, Object> rsmap = (Map<String, Object>) object.get(0);
        log.debug("(Map<String, Object>)[object.get(0)=" + object.get(0) + "]");
        if (rsmap != null) {
            final Integer cap = (rsmap.get(CAPITAL_SERIES_ID) == null) ? null
                    : (Integer) rsmap.get(CAPITAL_SERIES_ID);
            capitalSeriesID = (cap == null) ? null : Integer.toString(cap);
        }

        log.debug("Generated capitalSeriesID=[" + capitalSeriesID + "]");

        return capitalSeriesID;
    }

    private Map<String, Object> callSPGetNextCapitalSeriesID(
            final String certificateNumber) {
        final JdbcTemplate jdbcTemplate = new JdbcTemplate(
                dataSourceDirectSales);
        final Map<String, Object> inParamMap = new HashMap<>();

        if (certificateNumber.startsWith("1096")) {
            inParamMap.put(PRD_ID, PRODUCT_PROTECAO_PREMIADA);
            simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate)
                    .withProcedureName(SP_GET_NEXT_CAPITALSERIES_ID);
        } else {
            inParamMap.put(PRD_ID, PRODUCT_CARTAO_SUPERPROTEGIDO);
            simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate)
                    .withProcedureName(SP_GET_NEXT_CAPITALSERIES_ID_GARANTIA);
        }

        inParamMap.put(POLICY_NO, certificateNumber);

        final SqlParameterSource in = new MapSqlParameterSource(inParamMap);

        final Map<String, Object> simpleJdbcCallResult = simpleJdbcCall
                .execute(in);

        return simpleJdbcCallResult;
    }

    public Map<String, Object> retrieveCapitalSeriesData(
            final String capitalSeriesID) {

        if (capitalSeriesID == null) {
            final String msg = "ERROR: capitalSeriesID can not be null!";
            log.error(msg);
            throw new ValidationException(msg,
                    ExceptionsConstants.CODE_CAPITALSERIESID_NULL,
                    ErrorType.VALIDATION);
        }

        final JdbcTemplate jdbcTemplate = new JdbcTemplate(
                dataSourceDirectSales);
        final Map<String, Object> capitalSeriesData = jdbcTemplate
                .queryForObject(queryRetrieveCapitalSeries,
                        new Object[] { capitalSeriesID },
                        new CapitalSeriesDataRM());

        return capitalSeriesData;
    }

    public Map<String, Object> retrieveCapitalSeriesDataByCertificate(
            final String certificateNumber) {
        if (certificateNumber == null) {
            final String msg = "ERROR: certificateNumber can not be null!";
            log.error(msg);
            throw new ValidationException(msg,
                    ExceptionsConstants.CODE_CERTIFICATENUMBER_NULL,
                    ErrorType.VALIDATION);
        }

        final JdbcTemplate jdbcTemplate = new JdbcTemplate(
                dataSourceDirectSales);
        final List<Map<String, Object>> capitalSeriesDataList = jdbcTemplate
                .query(queryRetrieveCapitalSeriesByCertficate,
                        new CapitalSeriesDataRM(), certificateNumber);

        final Map<String, Object> result = ((capitalSeriesDataList == null)
                || (capitalSeriesDataList.isEmpty())) ? null
                        : capitalSeriesDataList.get(0);

        return result;
    }

    public Map<String, Object> cleanOldAndRetrieveNewCapitalSeriesData(
            final String certificateNumber) {
        final JdbcTemplate jdbcTemplateDirectSales = new JdbcTemplate(
                dataSourceDirectSales);

        final int updateResult = jdbcTemplateDirectSales.update(
                updateCleanCertificateInCapitalSeries,
                new Object[] { certificateNumber });
        if (updateResult == 0) {
            final String message = "Capital Series status update fail! Clean certificateNumber=["
                    + certificateNumber + "]";
            log.error(message);
            throw new BusinessException(message,
                    ExceptionsConstants.CODE_NOT_UPDATED_CAPOLD,
                    ErrorType.BUSINESS);
        }
        log.debug("Capital Series: Certificate cleaned!");

        return callSPGetNextCapitalSeriesID(certificateNumber);
    }

}
