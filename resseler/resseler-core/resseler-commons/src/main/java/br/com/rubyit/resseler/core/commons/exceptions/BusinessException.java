package br.com.rubyit.resseler.core.commons.exceptions;

import br.com.rubyit.resseler.core.enums.ErrorType;

/**
 * Classe de exceção referente a lógicas de negócio
 * @author b11527
 *
 */
public class BusinessException extends RawException {

    /**
     * Método de lançamento da exception BusinessException
     * @param message
     * @param code
     */
    public BusinessException(final String message, final String code,
            final ErrorType errorType) {
        super(message, code, errorType);
    }

    /**
     * Método de lançamento da exception BusinessException
     * @param message
     * @param code
     */
    public BusinessException(final String message, final Throwable cause,
            final String code, final ErrorType errorType) {
        super(message, cause, code, errorType);
    }
}
