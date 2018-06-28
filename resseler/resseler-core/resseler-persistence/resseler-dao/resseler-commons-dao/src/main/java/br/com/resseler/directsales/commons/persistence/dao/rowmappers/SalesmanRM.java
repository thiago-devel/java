package br.com.resseler.directsales.commons.persistence.dao.rowmappers;

import static br.com.resseler.directsales.commons.persistence.dao.util.CustomerSalesConstants.SALESMAN_ATU_BCH;
import static br.com.resseler.directsales.commons.persistence.dao.util.CustomerSalesConstants.SALESMAN_BCH;
import static br.com.resseler.directsales.commons.persistence.dao.util.CustomerSalesConstants.SALESMAN_ID;
import static br.com.resseler.directsales.commons.persistence.dao.util.CustomerSalesConstants.SALESMAN__BCH_ID;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import br.com.rubyit.resseler.core.commons.dto.Salesman;
import br.com.rubyit.resseler.core.commons.dto.SystemPerson;

/**
 * RowMapper Class 
 * for type Salesman
 * @author a42239
 *
 */
public class SalesmanRM implements RowMapper<Salesman> {
    private final SystemPerson sp;

    /**
     * Constructor to initialize inner 
     * SystemPerson attribute
     * @author a42239
     *
     */
    public SalesmanRM(final SystemPerson sp) {
        this.sp = sp;
    }

    @Override
    /**
     * Method to handle row 
     * mapper for type Salesman.
     * Preenche informacoes do vendedor
     * @param rs
     * @param rowNum
     * @return
     * 
     */
    public Salesman mapRow(final ResultSet rs, final int rowNum)
            throws SQLException {

        final Salesman salesman = new Salesman();
        salesman.setID(rs.getLong(SALESMAN_ID));
        salesman.setBirthDate(sp.getBirthDate());
        salesman.retrieveContacts().addAll(sp.retrieveContacts());
        salesman.setDescription(sp.getDescription());
        salesman.setDrtID(sp.getDrtID());
        salesman.setFullName(sp.getFullName());
        salesman.setGender(sp.getGender());
        salesman.setIdentity(sp.getIdentity());
        salesman.setLogin(sp.getLogin());
        salesman.setMaritalStatus(sp.getMaritalStatus());
        salesman.setMlID(sp.getMlID());
        salesman.setPartner(sp.getPartner());
        salesman.setRole(sp.getRole());
        salesman.setSUP(sp.getSUP());
        salesman.setType(sp.getType());
        salesman.setSalesmanBch(rs.getInt(SALESMAN_BCH));
        salesman.setSalesmanAtuBch(rs.getInt(SALESMAN_ATU_BCH));
        salesman.setBchId(rs.getLong(SALESMAN__BCH_ID));

        return salesman;
    }
}
