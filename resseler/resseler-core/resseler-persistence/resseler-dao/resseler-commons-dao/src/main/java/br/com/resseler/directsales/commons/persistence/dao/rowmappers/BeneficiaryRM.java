package br.com.resseler.directsales.commons.persistence.dao.rowmappers;

import static br.com.resseler.directsales.commons.persistence.dao.util.CustomerSalesConstants.BENEFICIARY_DT_BTH;
import static br.com.resseler.directsales.commons.persistence.dao.util.CustomerSalesConstants.BENEFICIARY_ID;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.Date;

import org.springframework.jdbc.core.RowMapper;

import br.com.rubyit.resseler.core.commons.dto.Beneficiary;

/**
 * RowMapper Class 
 * for type RegionId
 *
 * @author b11527
 *
 */
public class BeneficiaryRM implements RowMapper<Beneficiary> {

    @Override
    /**
     * Method to handle row 
     * mapper for RegionID in 
     * Integer type.
     * Preenche uma lista de beneficiarios
     * de um determinado INSURED_OBJECT_ID
     * que faz LEFT JOIN com a tabela certificate (pois se existem
     * beneficiarios a alguns produtos)
     */
    public Beneficiary mapRow(final ResultSet rs, final int rowNum) throws SQLException {
    	
    	if ((rs == null) || (rs.getInt(BENEFICIARY_ID) == 0)) { return null; }
    	
    	Beneficiary beneficiary = new Beneficiary();
    	beneficiary.setID(rs.getInt("BENEFICIARY_ID"));
    	beneficiary.setFullName(rs.getString("BENEFICIARY_NME"));
    	beneficiary.setPercentValue(rs.getDouble("BENEFICIARY_PCT"));

    	final Calendar creationDate = Calendar.getInstance();
        final Date creation = rs.getDate(BENEFICIARY_DT_BTH);
        if (creation != null) {
            creationDate.setTime(creation);
        }
    	beneficiary.setBirthDate(creationDate);
    	
        return beneficiary;
    }
}
