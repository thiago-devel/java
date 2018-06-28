package br.com.resseler.directsales.commons.persistence.dao.rowmappers;

import static br.com.resseler.directsales.commons.persistence.dao.util.CustomerSalesConstants.PRD_DSC;
import static br.com.resseler.directsales.commons.persistence.dao.util.CustomerSalesConstants.PRD_ID;
import static br.com.resseler.directsales.commons.persistence.dao.util.CustomerSalesConstants.PRD_SLM_PRT_VL;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import br.com.rubyit.resseler.commons.kernel.dto.InsuranceDTO;

/**
 * Class InsuranceDTORM
 * @author b11527
 *
 */
public class InsuranceDTORM implements RowMapper<InsuranceDTO> {
    /**
     * Method to handle row mapper 
     * for type InsuranceDTO.
     * @param rs
     * @param rowNum
     * @return
     * 
     */
    @Override
    public InsuranceDTO mapRow(final ResultSet rs, final int rowNum)
            throws SQLException {
        final InsuranceDTO product = new InsuranceDTO();
        product.setDescription(rs.getString(PRD_DSC));
        product.setID(rs.getLong(PRD_ID));
        final Double premiumValeu = rs.getDouble(PRD_SLM_PRT_VL);
        product.setPremiumValue(BigDecimal.valueOf(premiumValeu));

        return product;
    }
}
