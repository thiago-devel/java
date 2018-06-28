package br.com.rubyit.resseler.core.commons.exceptions;

import br.com.rubyit.resseler.core.enums.ErrorType;

public class RetrieveComissionsException extends RawException {
    
    /**
     * Método de lançamento da exception RetrieveComissionsException
     * @param message
     * @param cause
     * @param code
     * @param errorType
     */
    public RetrieveComissionsException(final String message,
            final Throwable cause, final String code,
            final ErrorType errorType) {
        super(message, cause, code, errorType);
    }
}
