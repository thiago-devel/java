package br.com.resseler.directsales.commons.persistence.dao.rowmappers;

import static br.com.resseler.directsales.commons.persistence.dao.util.CustomerSalesConstants.CAPITAL_SERIES_DEL_FG;
import static br.com.resseler.directsales.commons.persistence.dao.util.CustomerSalesConstants.CAPITAL_SERIES_EFF_DTM;
import static br.com.resseler.directsales.commons.persistence.dao.util.CustomerSalesConstants.CAPITAL_SERIES_ID;
import static br.com.resseler.directsales.commons.persistence.dao.util.CustomerSalesConstants.CAPITAL_SERIES_INS_DTM;
import static br.com.resseler.directsales.commons.persistence.dao.util.CustomerSalesConstants.CAPITAL_SERIES_INS_USER_ID;
import static br.com.resseler.directsales.commons.persistence.dao.util.CustomerSalesConstants.CAPITAL_SERIES_LTT;
import static br.com.resseler.directsales.commons.persistence.dao.util.CustomerSalesConstants.CAPITAL_SERIES_NBR;
import static br.com.resseler.directsales.commons.persistence.dao.util.CustomerSalesConstants.CAPITAL_SERIES_POLCRT_EFF_DTM;
import static br.com.resseler.directsales.commons.persistence.dao.util.CustomerSalesConstants.CAPITAL_SERIES_UPD_DTM;
import static br.com.resseler.directsales.commons.persistence.dao.util.CustomerSalesConstants.CAPITAL_SERIES_UPD_USER_ID;
import static br.com.resseler.directsales.commons.persistence.dao.util.CustomerSalesConstants.CAPITAL_SERIES_VLD;
import static br.com.resseler.directsales.commons.persistence.dao.util.CustomerSalesConstants.CAPITAL_SERIES_VLD_DTM;
import static br.com.resseler.directsales.commons.persistence.dao.util.CustomerSalesConstants.CAP_FILE_ID;
import static br.com.resseler.directsales.commons.persistence.dao.util.CustomerSalesConstants.CAP_PRD_ID;
import static br.com.resseler.directsales.commons.persistence.dao.util.CustomerSalesConstants.OWNER_PARTNER_ID;
import static br.com.resseler.directsales.commons.persistence.dao.util.CustomerSalesConstants.PARTNER_ID;
import static br.com.resseler.directsales.commons.persistence.dao.util.CustomerSalesConstants.POLCRT_ENDORS_NO;
import static br.com.resseler.directsales.commons.persistence.dao.util.CustomerSalesConstants.POLICY_NO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.jdbc.core.RowMapper;

public class CapitalSeriesDataRM implements RowMapper<Map<String, Object>> {

	/**
	 * @param rs
	 * @param rowNum
	 */
    @Override
    public Map<String, Object> mapRow(final ResultSet rs, final int rowNum) throws SQLException {
        final Map<String, Object> capitalSeriesData = new HashMap<>();

        capitalSeriesData.put(CAPITAL_SERIES_ID, rs.getInt(CAPITAL_SERIES_ID));
        capitalSeriesData.put(OWNER_PARTNER_ID, rs.getInt(OWNER_PARTNER_ID));
        capitalSeriesData.put(PARTNER_ID, rs.getInt(PARTNER_ID));
        capitalSeriesData.put(CAPITAL_SERIES_LTT, rs.getString(CAPITAL_SERIES_LTT));
        capitalSeriesData.put(CAPITAL_SERIES_NBR, rs.getString(CAPITAL_SERIES_NBR));
        capitalSeriesData.put(CAPITAL_SERIES_VLD, rs.getLong(CAPITAL_SERIES_VLD));
        capitalSeriesData.put(CAPITAL_SERIES_EFF_DTM, rs.getDate(CAPITAL_SERIES_EFF_DTM));
        capitalSeriesData.put(CAPITAL_SERIES_VLD_DTM, rs.getDate(CAPITAL_SERIES_VLD_DTM));
        capitalSeriesData.put(POLICY_NO, rs.getLong(POLICY_NO));
        capitalSeriesData.put(POLCRT_ENDORS_NO, rs.getInt(POLCRT_ENDORS_NO));
        capitalSeriesData.put(CAPITAL_SERIES_POLCRT_EFF_DTM, rs.getDate(CAPITAL_SERIES_POLCRT_EFF_DTM));
        capitalSeriesData.put(CAPITAL_SERIES_INS_DTM, rs.getDate(CAPITAL_SERIES_INS_DTM));
        capitalSeriesData.put(CAPITAL_SERIES_INS_USER_ID, rs.getString(CAPITAL_SERIES_INS_USER_ID));
        capitalSeriesData.put(CAPITAL_SERIES_UPD_DTM, rs.getDate(CAPITAL_SERIES_UPD_DTM));
        capitalSeriesData.put(CAPITAL_SERIES_UPD_USER_ID, rs.getString(CAPITAL_SERIES_UPD_USER_ID));
        capitalSeriesData.put(CAPITAL_SERIES_DEL_FG, rs.getString(CAPITAL_SERIES_DEL_FG));
        capitalSeriesData.put(CAP_FILE_ID, rs.getInt(CAP_FILE_ID));
        capitalSeriesData.put(CAP_PRD_ID, rs.getInt(CAP_PRD_ID));

        return capitalSeriesData;
    }
}
