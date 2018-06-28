package br.com.rubyit.resseler.core.commons.exceptions;

import br.com.rubyit.resseler.core.enums.ErrorType;

/**
 * Classe de exceção referente ao método de pagamento
 * @author b11527
 *
 */
public class CheckPaymentMethodException extends RawException {
    
    /**
     * Método de lançamento da exception CheckPaymentMethodException
     * @param message
     * @param cause
     * @param code
     * @param errorType
     */
    public CheckPaymentMethodException(final String message, final Throwable cause, final String code, final ErrorType errorType) {
        super(message, cause, code, errorType);
    }
    
    /**
     * Método de lançamento da exception CheckPaymentMethodException
     * @param message
     * @param cause
     * @param errorType
     */
    public CheckPaymentMethodException(final String message, final String code,
            final ErrorType errorType) {
        super(message, code, errorType);
    }
}
