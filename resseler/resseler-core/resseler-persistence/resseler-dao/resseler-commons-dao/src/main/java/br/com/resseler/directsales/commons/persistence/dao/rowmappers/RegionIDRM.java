package br.com.resseler.directsales.commons.persistence.dao.rowmappers;

import static br.com.resseler.directsales.commons.persistence.dao.util.CustomerSalesConstants.REG_ID;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

/**
 * RowMapper Class for type RegionId
 *
 * @author a42239
 *
 */
public class RegionIDRM implements RowMapper<Long> {

    @Override
    /**
     * Method to handle row mapper for RegionID in Integer type.
     * @param resultset
     * @param rownum
     * @return int
     */
    public Long mapRow(final ResultSet rs, final int rowNum) throws SQLException {
        final Long value = rs.getLong(REG_ID);

        return value;
    }
}
