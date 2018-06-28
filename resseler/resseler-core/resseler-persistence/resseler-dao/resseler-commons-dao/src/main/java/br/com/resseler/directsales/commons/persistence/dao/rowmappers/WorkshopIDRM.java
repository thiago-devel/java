package br.com.resseler.directsales.commons.persistence.dao.rowmappers;

import static br.com.resseler.directsales.commons.persistence.dao.util.CustomerSalesConstants.BCH_ID;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

/**
 * RowMapper Class for type WorkshopID
 *
 * @author a42239
 *
 */
public class WorkshopIDRM implements RowMapper<Long> {

    @Override
    /**
     * Method to handle row mapper for WorkshopID in Integer type.
     * @param rs
     * @param rowNum
     */
    public Long mapRow(final ResultSet rs, final int rowNum) throws SQLException {
        final Long value = rs.getLong(BCH_ID);

        return value;
    }
}
