package br.com.resseler.directsales.commons.persistence.dao.rowmappers;

import static br.com.resseler.directsales.commons.persistence.dao.util.CustomerSalesConstants.PRD_CERT_PREFIX;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

/**
 * RowMapper ProductPrefixRM
 * @author b11527
 *
 */
public class ProductPrefixRM implements RowMapper<String> {

	/**
	 * @param rs
	 * @param rowNum
	 * @return
	 * 
	 */
    @Override
    public String mapRow(final ResultSet rs, final int rowNum) throws SQLException {

        final String prefix = rs.getString(PRD_CERT_PREFIX);

        return prefix;
    }
}
