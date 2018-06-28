package br.com.rubyit.resseler.core.commons.exceptions;

import br.com.rubyit.resseler.core.enums.ErrorType;

/**
 * Classe de exceção referente ao método de pagamento
 * @author b11527
 *
 */
public class InvalidCardException extends RawException {

    /**
     * 
     * @param message
     * @param cause
     */
    public InvalidCardException(final String message, final Throwable cause, final String code, final ErrorType errorType) {
        super(message, cause, code, errorType);
    }
    
    /**
     * 
     * @param message
     * @param cause
     */
    public InvalidCardException(final String message, final String code, final ErrorType errorType) {
        super(message,code,errorType);
    }

}
