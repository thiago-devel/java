package br.com.resseler.directsales.sales.persistence.builders;

import java.math.BigDecimal;
import java.util.Calendar;

public class PaymentMethodDataBuilder {

    private final PaymentMethodData object = new PaymentMethodData();

    /**
     * 
     * @param cartaoNumero
     * @return
     */
    public PaymentMethodDataBuilder setCartaoNumero(final String cartaoNumero) {
        object.cartaoNumero = cartaoNumero;
        return this;
    }

    /**
     * 
     * @param cartaoNome
     * @return
     */
    public PaymentMethodDataBuilder setCartaoNome(final String cartaoNome) {
        object.cartaoNome = cartaoNome;
        return this;
    }

    /**
     * 
     * @param cartaoValidade
     * @return
     */
    public PaymentMethodDataBuilder setCartaoValidade(final Calendar cartaoValidade) {
        object.cartaoValidade = cartaoValidade;
        return this;
    }

    /**
     * 
     * @param cartaoCodigoBandeira
     * @return
     */
    public PaymentMethodDataBuilder setCartaoCodigoBandeira(final String cartaoCodigoBandeira) {
        object.cartaoCodigoBandeira = cartaoCodigoBandeira;
        return this;
    }

    /**
     * 
     * @param premio
     * @return
     */
    public PaymentMethodDataBuilder setPremio(final BigDecimal premio) {
        object.premio = premio;
        return this;
    }

    /**
     * 
     * @param paymentMethodType
     * @return
     */
    public PaymentMethodDataBuilder setPaymentMethodType(/*final PaymentMethodType*/final Object paymentMethodType) {
        //object.paymentMethodType = paymentMethodType;
        return this;
    }

    /**
     * 
     * @param cardData
     * @return
     */
    public PaymentMethodDataBuilder setCardData(final /*CardPaymentType*/Object cardData) {
        //object.cardData = cardData;
        return this;
    }

    /**
     * 
     * @param cardsData
     * @return
     */
    public PaymentMethodDataBuilder setCardsData(final /*CardsPaymentType*/Object cardsData) {
        //object.cardsData = cardsData;
        return this;
    }

    /**
     * 
     * @return PaymentMethodData
     */
    public PaymentMethodData build() {
        return object;
    }

}
