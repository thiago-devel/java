package br.com.resseler.directsales.commons.persistence.dao.rowmappers;

import static br.com.resseler.directsales.commons.persistence.dao.util.CustomerSalesConstants.PHONE_NUM;
import static br.com.resseler.directsales.commons.persistence.dao.util.CustomerSalesConstants.PHONE_TYP;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import br.com.rubyit.resseler.commons.kernel.dto.PhoneDTO;
import br.com.rubyit.resseler.commons.kernel.enums.PhoneType;

/**
 * RowMapper Class 
 * for type PhoneRM
 * @author b11527
 *
 */
public class PhoneRM implements RowMapper<PhoneDTO> {

    /**
     * Method to handle row mapper 
     * for type PhoneDTO.
     * Preenche dados do telefone de um 
     * determinado contato
     * @param rs
     * @param rowNum
     * @return
     * 
     */
    @Override
    public PhoneDTO mapRow(final ResultSet rs, final int rowNum)
            throws SQLException {
        final PhoneDTO phone = new PhoneDTO();

        phone.setPhoneNumber(rs.getString(PHONE_NUM));

        switch (rs.getString(PHONE_TYP)) {
        case "RESIDENTIAL":
            phone.setType(PhoneType.RESIDENTIAL);
            break;
        case "MOBILE":
            phone.setType(PhoneType.MOBILE);
            break;
        default:
        }

        return phone;
    }
}
