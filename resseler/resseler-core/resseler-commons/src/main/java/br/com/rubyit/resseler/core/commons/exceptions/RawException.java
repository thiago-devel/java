package br.com.rubyit.resseler.core.commons.exceptions;

import br.com.rubyit.resseler.core.enums.ErrorType;

/**
 * Classe de exceção referente a lógicas de validações
 * @author b11527
 *
 */
public class RawException extends RuntimeException {
    
    private final String code;
    private final ErrorType errorType; 
    
    /**
     * Método de lançamento da exception RawException
     * @param message
     * @param cause
     */
    public RawException(final String message, final Throwable cause, final String code, final ErrorType errorType) {
        super(message, cause);
        this.code = code;
        this.errorType = errorType;
    }
    
    /**
     * Método de lançamento da exception RawException
     * @param message
     * @param code
     */
    public RawException(final String message, final String code, final ErrorType errorType) {
        super(message);
        this.code = code;
        this.errorType = errorType;
    }
    
    /**
     * the code
     * @return the code
     */
    public String getCode() {
        return code;
    }

    /**
     * @return the errorType
     */
    public ErrorType getErrorType() {
        return errorType;
    }

}
