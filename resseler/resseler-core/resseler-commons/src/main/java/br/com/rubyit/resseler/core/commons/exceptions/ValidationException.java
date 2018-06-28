package br.com.rubyit.resseler.core.commons.exceptions;

import br.com.rubyit.resseler.core.enums.ErrorType;

/**
 * Classe de exceção referente a lógicas de validações
 * @author b11527
 *
 */
public class ValidationException extends RawException {
    
    
    /**
     * Método de lançamento da exception ValidationException
     * @param message
     * @param cause
     */
    public ValidationException(final String message, final Throwable cause, final String code, final ErrorType errorType) {
        super(message, cause, code, errorType);
    }
    
    /**
     * Método de lançamento da exception ValidationException
     * @param message
     * @param code
     */
    public ValidationException(final String message, final String code, final ErrorType errorType) {
        super(message,code,errorType);
    }

}
