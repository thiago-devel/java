package br.com.resseler.directsales.sales.persistence.dao.rowmapper;

import static br.com.resseler.directsales.commons.persistence.dao.util.CustomerSalesConstants.PRD_FRM_DSC;
import static br.com.resseler.directsales.commons.persistence.dao.util.CustomerSalesConstants.PRD_FRM_ID;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import br.com.resseler.directsales.sales.persistence.dao.PaymentForm;

/**
 * RowMapper Class for type PaymentForm
 * 
 * @author a42239
 *
 */
public class PaymentFormRM implements RowMapper<PaymentForm> {

    @Override
    /**
     * Method to handle row mapper for type PaymentForm
     * @param rs
     * @param rowNum
     * @return PaymentForm
     * 
     */
    public PaymentForm mapRow(final ResultSet rs, final int rowNum) throws SQLException {
        final PaymentForm paymentForm = new PaymentForm();

        paymentForm.setID(rs.getLong(PRD_FRM_ID));
        paymentForm.setDescription(rs.getString(PRD_FRM_DSC));

        return paymentForm;
    }
}
