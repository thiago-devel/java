package br.com.resseler.directsales.commons.persistence.dao.rowmappers;

import static br.com.resseler.directsales.commons.persistence.dao.util.CustomerSalesConstants.ADDRESS_CMP;
import static br.com.resseler.directsales.commons.persistence.dao.util.CustomerSalesConstants.ADDRESS_CTY;
import static br.com.resseler.directsales.commons.persistence.dao.util.CustomerSalesConstants.ADDRESS_NBH;
import static br.com.resseler.directsales.commons.persistence.dao.util.CustomerSalesConstants.ADDRESS_NME;
import static br.com.resseler.directsales.commons.persistence.dao.util.CustomerSalesConstants.ADDRESS_NUM;
import static br.com.resseler.directsales.commons.persistence.dao.util.CustomerSalesConstants.ADDRESS_STE;
import static br.com.resseler.directsales.commons.persistence.dao.util.CustomerSalesConstants.ADDRESS_ZIP;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import br.com.rubyit.resseler.commons.kernel.dto.AddressDTO;

/**
 * RowMapper Class 
 * for type AddressDTO
 * @author b11527
 *
 */
public class AddressRM implements RowMapper<AddressDTO> {
    /**
     * Method to handle row mapper 
     * for type AddressDTO.
     * Preenche dados do endereco de um 
     * determinado contato
     * @param rs
     * @param rowNum
     */
    @Override
    public AddressDTO mapRow(final ResultSet rs, final int rowNum)
            throws SQLException {
        final AddressDTO address = new AddressDTO();
        address.setAddressDetail(rs.getString(ADDRESS_NME));
        address.setAddressNumber(rs.getString(ADDRESS_NUM));
        address.setAddressReference(rs.getString(ADDRESS_CMP));
        address.setNeighborhood(rs.getString(ADDRESS_NBH));
        address.setCity(rs.getString(ADDRESS_CTY));
        address.setState(rs.getString(ADDRESS_STE));
        address.setAddressPostalCode(rs.getString(ADDRESS_ZIP));

        return address;
    }
}
