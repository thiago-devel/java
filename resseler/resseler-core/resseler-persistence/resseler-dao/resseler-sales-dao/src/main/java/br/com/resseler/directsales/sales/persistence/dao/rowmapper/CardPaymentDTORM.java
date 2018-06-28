package br.com.resseler.directsales.sales.persistence.dao.rowmapper;

import static br.com.resseler.directsales.commons.persistence.dao.util.CustomerSalesConstants.CRD_CARD_FLAG;
import static br.com.resseler.directsales.commons.persistence.dao.util.CustomerSalesConstants.CRD_CARD_MONTH_YEAR;
import static br.com.resseler.directsales.commons.persistence.dao.util.CustomerSalesConstants.CRD_CARD_NBR;
import static br.com.resseler.directsales.commons.persistence.dao.util.CustomerSalesConstants.CRD_CARD_NME;
import static br.com.resseler.directsales.commons.persistence.dao.util.CustomerSalesConstants.CRD_CARD_SECURITY;
import static br.com.rubyit.resseler.core.utils.ConverterUtil.FORMAT_MM_SLASH_YY;
import static br.com.rubyit.resseler.core.utils.ConverterUtil.parseStringToDate;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.Date;

import org.springframework.jdbc.core.RowMapper;

import br.com.rubyit.resseler.core.commons.dto.CardPaymentDTO;

/**
 * RowMapper Class for type CardPaymentDTO
 *
 * @author a42239
 *
 */
public class CardPaymentDTORM implements RowMapper<CardPaymentDTO> {

    /**
     * Method to handle row mapper for type Salesman.
     * @param rs
     * @param rowNum
     * @return CardPaymentDTO
     */
    @Override
    public CardPaymentDTO mapRow(final ResultSet rs, final int rowNum)
            throws SQLException {
        final CardPaymentDTO card = new CardPaymentDTO();

        card.setCardDisplayName(rs.getString(CRD_CARD_NME));
        card.setCardSecurityCode(rs.getString(CRD_CARD_SECURITY));

        final Date date = parseStringToDate(FORMAT_MM_SLASH_YY,
                rs.getString(CRD_CARD_MONTH_YEAR));
        final Calendar cardValidity = Calendar.getInstance();
        if (date != null) {
            cardValidity.setTime(date);
        }
        card.setCardValidity(cardValidity);
        card.setCardFlag(rs.getString(CRD_CARD_FLAG));
        card.setCardNumber(rs.getString(CRD_CARD_NBR));

        return card;
    }
}
