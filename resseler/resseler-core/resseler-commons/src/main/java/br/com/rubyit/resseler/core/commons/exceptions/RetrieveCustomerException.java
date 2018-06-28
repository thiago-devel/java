package br.com.rubyit.resseler.core.commons.exceptions;

import br.com.rubyit.resseler.core.enums.ErrorType;

public class RetrieveCustomerException extends RawException {

    /**
     * Método de lançamento da exception RetrieveCustomerException
     * @param message
     * @param cause
     * @param code
     * @param errorType
     */
    public RetrieveCustomerException(final String message,
            final Throwable cause, final String code,
            final ErrorType errorType) {
        super(message, cause, code, errorType);
    }
}
