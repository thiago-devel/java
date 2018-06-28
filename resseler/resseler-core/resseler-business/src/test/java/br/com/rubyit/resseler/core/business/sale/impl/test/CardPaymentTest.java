package br.com.rubyit.resseler.core.business.sale.impl.test;

import static br.com.rubyit.resseler.core.utils.ConverterUtil.FORMAT_MM_SLASH_YY;
import static br.com.rubyit.resseler.core.utils.ConverterUtil.parseStringToDate;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;

import br.com.rubyit.resseler.core.commons.dto.CardPaymentDTO;

public abstract class CardPaymentTest {
    public static final String CPF_EXEMPLO = "61330505000";
    public static final String VENCIMENTO_CARTAO_EXEMPLO = "03/21";
    public static final String CARTAO_NUMERO_EXEMPLO = "5305993016355848";
    public static final String LUIZA_BANDEIRA_CARTAO_EXEMPLO = "luiza";
    public static final String CODIGO_SEGURANCA_EXEMPLO = "344";
    public static final String NOME_CARTAO_EXEMPLO = "angelina maria de araujo";
    public static final String CARTAO_NUMERO_EXEMPLO_ENCRIPTED = "F010C12E90A3E096";
    public static final BigDecimal CARTAO_VALOR_EXEMPLO = BigDecimal.valueOf(11.89);

    public static CardPaymentDTO montaCartaoParaTestes() {
        final CardPaymentDTO cardPayment = new CardPaymentDTO();
        cardPayment.setCardDisplayName(NOME_CARTAO_EXEMPLO);
        cardPayment.setCardSecurityCode(CODIGO_SEGURANCA_EXEMPLO);

        final Date date = parseStringToDate(FORMAT_MM_SLASH_YY, VENCIMENTO_CARTAO_EXEMPLO);
        final Calendar cardValidity = Calendar.getInstance();
        if (date != null) {
            cardValidity.setTime(date);
        }
        cardPayment.setCardValidity(cardValidity);
        cardPayment.setCardFlag(LUIZA_BANDEIRA_CARTAO_EXEMPLO);
        cardPayment.setCardNumber(CARTAO_NUMERO_EXEMPLO);
        cardPayment.setCardValue(CARTAO_VALOR_EXEMPLO);

        return cardPayment;
    }
}
