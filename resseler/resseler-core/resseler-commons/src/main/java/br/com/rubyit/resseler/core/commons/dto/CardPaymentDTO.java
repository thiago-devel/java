package br.com.rubyit.resseler.core.commons.dto;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Objects;

import br.com.rubyit.resseler.commons.kernel.core.Identification;

/**
 * Class CardPaymentDTO
 * @author b11527
 *
 */
public class CardPaymentDTO extends Identification {

    private String cardNumber = null;
    private String cardDisplayName = null;
    private String cardSecurityCode = null;
    private Calendar cardValidity = null;
    private String cardFlag = null;
    private BigDecimal cardValue = null;

    /**
     * the cardNumber
     * @return the cardNumber
     */
    public String getCardNumber() {
        return cardNumber;
    }

    /**
     * the cardNumber to set
     * @param cardNumber 
     */
    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    /**
     * the cardDisplayName
     * @return the cardDisplayName
     */
    public String getCardDisplayName() {
        return cardDisplayName;
    }

    /**
     * the cardDisplayName to set
     * @param cardDisplayName 
     */
    public void setCardDisplayName(String cardDisplayName) {
        this.cardDisplayName = cardDisplayName;
    }

    /**
     * the cardSecurityCode
     * @return the cardSecurityCode
     */
    public String getCardSecurityCode() {
        return cardSecurityCode;
    }

    /**
     * the cardSecurityCode to set
     * @param cardSecurityCode 
     */
    public void setCardSecurityCode(String cardSecurityCode) {
        this.cardSecurityCode = cardSecurityCode;
    }

    /**
     * the cardValidity
     * @return the cardValidity
     */
    public Calendar getCardValidity() {
        return cardValidity;
    }

    /**
     * the cardValidity to set
     * @param cardValidity 
     */
    public void setCardValidity(Calendar cardValidity) {
        this.cardValidity = cardValidity;
    }

    /**
     * the cardFlag
     * @return the cardFlag
     */
    public String getCardFlag() {
        return cardFlag;
    }

    /**
     * the cardFlag to set
     * @param cardFlag 
     */
    public void setCardFlag(String cardFlag) {
        this.cardFlag = cardFlag;
    }

    /**
     * the cardValue
     * @return the cardValue
     */
    public BigDecimal getCardValue() {
        return cardValue;
    }

    /**
     * the cardValue to set
     * @param cardValue 
     */
    public void setCardValue(BigDecimal cardValue) {
        this.cardValue = cardValue;
    }
    
    @Override
    public String toString() {
        return "CardPaymentDTO [cardNumber=" + cardNumber + ", cardDisplayName=" + cardDisplayName
                + ", cardSecurityCode=" + cardSecurityCode + ", cardValidity=" + cardValidity + ", cardFlag=" + cardFlag
                + ", cardValue=" + cardValue + ", getClass()=" + getClass() + ", hashCode()=" + hashCode()
                + ", toString()=" + super.toString() + "]";
    }

    @Override
    public boolean equals(final Object obj) {

        if (obj == null) { return false; }

        if (getClass() != obj.getClass()) { return false; }

        final CardPaymentDTO other = (CardPaymentDTO) obj;

        boolean result = Objects.equals(cardDisplayName, other.cardDisplayName)
                && Objects.equals(cardFlag, other.cardFlag) && Objects.equals(cardNumber, other.cardNumber);
        result = result && Objects.equals(cardSecurityCode, other.cardSecurityCode)
                && Objects.equals(cardValidity, other.cardValidity);
        result = result && Objects.equals(cardValue, other.cardValue);

        return result;
    }

    @Override

    public int hashCode() {

        return Objects.hash(cardDisplayName, cardFlag, cardNumber, cardSecurityCode, cardValidity, cardValidity);
    }

}
