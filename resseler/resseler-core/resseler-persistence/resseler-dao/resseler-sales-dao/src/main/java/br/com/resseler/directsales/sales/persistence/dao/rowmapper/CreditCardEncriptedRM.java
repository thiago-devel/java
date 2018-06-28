package br.com.resseler.directsales.sales.persistence.dao.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

/**
 * RowMapper Class for type CreditCardEncripted
 *
 * @author a42239
 *
 */
public class CreditCardEncriptedRM implements RowMapper<String> {

    @Override
    /**
     * Method to handle row mapper for Encripted CreditCard in String type.
     * @param rs
     * @param rowNum
     * @return String
     * 
     */
    public String mapRow(final ResultSet rs, final int rowNum) throws SQLException {
        return rs.getString("CARD_NBR_CRIPT");
    }
}
