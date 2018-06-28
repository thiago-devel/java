package br.com.resseler.directsales.commons.persistence.dao.rowmappers;

import static br.com.resseler.directsales.commons.persistence.dao.util.CustomerSalesConstants.PERSON_ID;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

/**
 * RowMapper Class for ID of SystemPerson of the Salesman
 *
 * @author a42239
 *
 */
public class SalesmanSPIDRM implements RowMapper<Long> {

    @Override
    /**
     * Method to handle row mapper for ID of System Person of the Salesman.
     * @param resultset
     * @param rownum
     * @return int
     */
    public Long mapRow(final ResultSet rs, final int rowNum) throws SQLException {
        final Long value = rs.getLong(PERSON_ID);

        return value;
    }
}
