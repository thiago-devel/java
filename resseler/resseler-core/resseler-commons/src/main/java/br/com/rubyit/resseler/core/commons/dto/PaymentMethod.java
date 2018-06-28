package br.com.rubyit.resseler.core.commons.dto;

import br.com.rubyit.resseler.commons.kernel.core.Identification;
import br.com.rubyit.resseler.commons.kernel.dto.GenericDTO;

/**
 * Class PaymentMethod
 * @author b11527
 *
 */
public class PaymentMethod extends Identification implements GenericDTO {

    private Integer paymentType = null;
    private CardPaymentDTO cardPayment = null;
    
    /**
     * the paymentType
     * @return the paymentType
     */
    public Integer getPaymentType() {
        return paymentType;
    }
    /**
     * the paymentType to set
     * @param paymentType 
     */
    public void setPaymentType(Integer paymentType) {
        this.paymentType = paymentType;
    }
    /**
     * the cardPayment
     * @return the cardPayment
     */
    public CardPaymentDTO getCardPayment() {
        return cardPayment;
    }
    /**
     * the cardPayment to set
     * @param cardPayment 
     */
    public void setCardPayment(CardPaymentDTO cardPayment) {
        this.cardPayment = cardPayment;
    }

}
