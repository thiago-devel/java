package br.com.resseler.directsales.commons.persistence.dao.rowmappers;

import static br.com.resseler.directsales.commons.persistence.dao.util.CustomerSalesConstants.PRD_AGR_VL_COMIS;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

/**
 * Class ProductComissionValueRM
 * @author b11527
 *
 */
public class ProductComissionValueRM implements RowMapper<BigDecimal> {
    /**
     * Método mapRow
     * ResultSet - BigDecimal
     * @param rs
     * @param rowNum
     * @return 
     * 
     */
	@Override
    public BigDecimal mapRow(final ResultSet rs, final int rowNum) throws SQLException {
        final Double value = rs.getDouble(PRD_AGR_VL_COMIS);
        final BigDecimal productComissionValue = new BigDecimal(value);

        return productComissionValue;
    }
}
