package br.com.rubyit.resseler.core.business.validators;

import static br.com.rubyit.resseler.core.business.validators.Validations.isEmpty;
import static br.com.rubyit.resseler.core.business.validators.Validations.isValidNumber;

import java.math.BigDecimal;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import br.com.rubyit.resseler.core.commons.exceptions.ExceptionsConstants;
import br.com.rubyit.resseler.core.commons.exceptions.InvalidCardException;
import br.com.rubyit.resseler.core.enums.ErrorType;

/**
 * 
 * Classe de validação
 * de cartão de crédito
 *
 */
public final class ValidCard {
    private static final int CREDITCARD_NAME_MAX_LENGTH = 30;
    private static final int CREDITCARD_DIGITS = 16;
    private static final Logger LOG = LogManager.getLogger(ValidCard.class);

    private ValidCard() {
    }

    /**
     * Validação de Cartão de Crédito
     * Verifica o tamanho e se os dados passados
     * são feitos somente de números
     *
     * @return boolean
     *
     */
    static boolean isValidCardNumber(final String cardNumber) {

        final String msgInvalidCard = "ERROR = O campo Número do Cartão ["
                + cardNumber + "] não é válido.";

        final String binCardNumber = cardNumber.trim().substring(0, 6);
        
        if (!isValidNumber(cardNumber.replace(" ", "")) || "000000".equals(binCardNumber)) {
            LOG.error(msgInvalidCard);
            throw new InvalidCardException(msgInvalidCard,
                    ExceptionsConstants.CODE_NUMCARTAO_INVALIDO,
                    ErrorType.VALIDATION);
        }

        if (cardNumber.trim().length() != CREDITCARD_DIGITS) {
            LOG.error(msgInvalidCard);
            throw new InvalidCardException(msgInvalidCard,
                    ExceptionsConstants.CODE_NUMCARTAO_TAMANHO_INVALIDO,
                    ErrorType.VALIDATION);
        }
        
        return true;
    }

    /**
     * Validação de Cartão de Crédito
     * Verifica se os números do cartão enviado
     * são do tipo MAGAZINE LUIZA
     *
     * @return boolean
     *
     */
    static boolean isMagazineValid(final String cardNumber) {

        final String binCardNumber = cardNumber.trim().substring(0, 6);

        if (!"530780".equals(binCardNumber) && !"530994".equals(binCardNumber)
                && !"517967".equals(binCardNumber)
                && !"530599".equals(binCardNumber)) {
            final String msgNotExpectedCard = "O número de cartão informado ["
                    + cardNumber + "] não é do tipo Magazine Luiza.";
            throw new InvalidCardException(msgNotExpectedCard,
                    ExceptionsConstants.CODE_CARTAO_NOT_MAGAZINELUIZA,
                    ErrorType.VALIDATION);
        }

        return true;
    }

    /**
     * Validação de Cartão de Crédito
     * Verifica se o nome do cartão é válido
     *
     * @return boolean
     *
     */
    static boolean isValidCardName(final String cardName) {

        final String msg = "ERROR = ";

        if (isEmpty(cardName)) {
            final String msgEmpty = msg
                    + "O campo Nome do Cartão de Crédito é obrigatório.";
            LOG.error(msgEmpty);
            throw new InvalidCardException(msgEmpty,
                    ExceptionsConstants.CODE_CREDITCARDNAME_EMPTY,
                    ErrorType.VALIDATION);
        }
        if (cardName.trim().length() > CREDITCARD_NAME_MAX_LENGTH) {
            final String msgInvalidCard = msg + "O campo Nome do Cartão ["
                    + cardName + "], não pode " + "conter mais de "
                    + CREDITCARD_NAME_MAX_LENGTH + " de caracteres.";
            LOG.error(msgInvalidCard);
            throw new InvalidCardException(msgInvalidCard,
                    ExceptionsConstants.CODE_CREDITCARDNAME_LENGTH,
                    ErrorType.VALIDATION);
        }
        if (cardName.matches(".*\\d.*")) {
            final String msgInvalidCard = msg + "O campo Nome do Cartão ["
                    + cardName + "], não pode "
                    + "conter dígitos de caracteres.";
            LOG.error(msgInvalidCard);
            throw new InvalidCardException(msgInvalidCard,
                    ExceptionsConstants.CODE_NOMECARTAO_DIGITOS,
                    ErrorType.VALIDATION);
        }

        return true;
    }

    /**
     * Validação de Cartão de Crédito
     * Verifica se a numeração do cartão
     * não é feita somente de números 0
     *
     * @return boolean
     *
     */
    public static boolean isInvalidCardValue(final BigDecimal cardValue) {
        if ((cardValue != null)
                && (cardValue.compareTo(BigDecimal.ZERO) > 0)) { return false; }

        return true;
    }
}
