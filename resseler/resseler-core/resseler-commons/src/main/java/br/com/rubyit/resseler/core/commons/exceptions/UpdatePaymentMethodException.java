package br.com.rubyit.resseler.core.commons.exceptions;

import br.com.rubyit.resseler.core.enums.ErrorType;

public class UpdatePaymentMethodException extends RawException {

    /**
     * 
     * @param message
     * @param cause
     * @param code
     * @param errorType
     * 
     */
    public UpdatePaymentMethodException(final String message,
            final Throwable cause, final String code,
            final ErrorType errorType) {
        super(message, cause, code, errorType);
    }

    /**
     * 
     * @param message
     * @param code
     * @param errorType
     */
    public UpdatePaymentMethodException(final String message, final String code,
            final ErrorType errorType) {
        super(message, code, errorType);
    }
}
