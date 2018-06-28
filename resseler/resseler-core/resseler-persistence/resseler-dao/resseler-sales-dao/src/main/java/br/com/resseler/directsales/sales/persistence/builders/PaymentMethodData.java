package br.com.resseler.directsales.sales.persistence.builders;

import java.math.BigDecimal;
import java.util.Calendar;

/**
 * Class PaymentMethodData
 * @author b11527
 *
 */
public class PaymentMethodData {
    protected String cartaoNumero;
    protected String cartaoNome;
    protected Calendar cartaoValidade;
    protected String cartaoCodigoBandeira;
    protected BigDecimal premio;
    protected /*PaymentMethodType*/Object paymentMethodType;
    protected /*CardPaymentType*/Object cardData;
    protected /*CardsPaymentType*/Object cardsData;

    /**
     * Constructor default
     */
    protected PaymentMethodData() {
    }

    /**
     * the cartaoNumero
     * @return the cartaoNumero
     */
    public String getCartaoNumero() {
        return cartaoNumero;
    }

    /**
     * the cartaoNome
     * @return the cartaoNome
     */
    public String getCartaoNome() {
        return cartaoNome;
    }

    /**
     * the cartaoValidade
     * @return the cartaoValidade
     */
    public Calendar getCartaoValidade() {
        return cartaoValidade;
    }

    /**
     * the cartaoCodigoBandeira
     * @return the cartaoCodigoBandeira
     */
    public String getCartaoCodigoBandeira() {
        return cartaoCodigoBandeira;
    }

    /**
     * the premio
     * @return the premio
     */
    public BigDecimal getPremio() {
        return premio;
    }

    /**
     * the paymentMethodType
     * @return the paymentMethodType
     */
    public /*PaymentMethodType*/Object getPaymentMethodType() {
        return paymentMethodType;
    }

    /**
     * the cardData
     * @return the cardData
     */
    public /*CardPaymentType*/Object getCardData() {
        return cardData;
    }

    /**
     * the cardsData
     * @return the cardsData
     */
    public /*CardsPaymentType*/Object getCardsData() {
        return cardsData;
    }

}
