package br.com.resseler.directsales.commons.persistence.dao.rowmappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.RowMapper;

public class QuantityCertificateCustomerRM implements RowMapper<Integer> {

    @Override
    public Integer mapRow(ResultSet rs, final int rowNum)
            throws SQLException, DataAccessException {
        Integer quantity = rs.getInt("QUANTITY"); 
        return quantity;
    }
}
