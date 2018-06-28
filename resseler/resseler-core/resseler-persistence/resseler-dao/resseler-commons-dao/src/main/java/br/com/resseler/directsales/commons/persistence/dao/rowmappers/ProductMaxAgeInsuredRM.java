package br.com.resseler.directsales.commons.persistence.dao.rowmappers;

import static br.com.resseler.directsales.commons.persistence.dao.util.CustomerSalesConstants.PRD_MAX_INSURED_AGE;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

/**
 * 
 * @author b24997
 *
 */
public class ProductMaxAgeInsuredRM implements RowMapper<Integer> {
    /**
     * Método mapRow ResultSet - Integer
     * 
     * @param rs
     * @param rowNum
     * @return
     * 
     */
    @Override
    public Integer mapRow(final ResultSet rs, final int rowNum) throws SQLException {
        return rs.getInt(PRD_MAX_INSURED_AGE);
    }
}
