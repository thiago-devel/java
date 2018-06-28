package br.com.resseler.directsales.commons.persistence.dao.rowmappers;

import static br.com.resseler.directsales.commons.persistence.dao.util.CustomerSalesConstants.BCH_COD;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

/**
 * RowMapper Class for type Workshop Code
 *
 * @author a42239
 *
 */
public class WorkshopCodeRM implements RowMapper<String> {

    @Override
    /**
     * Method to handle row mapper for Workshop Code in String type.
     * @param resultset
     * @param rownum
     * @return int
     */
    public String mapRow(final ResultSet rs, final int rowNum) throws SQLException {
        final String value = rs.getString(BCH_COD);

        return value;
    }
}
