package br.com.rubyit.resseler.core.commons.exceptions;

import br.com.rubyit.resseler.core.enums.ErrorType;

/**
 * Classe de exceção referente ao Vendedor (Salesman)
 * @author b11527
 *
 */
public class SalesmanException extends RawException {

    /**
     * Método de lançamento da exception SalesmanException
     * @param message
     * @param cause
     * @param code
     * @param errorType
     */
    public SalesmanException(final String message, final Throwable cause,
            final String code, final ErrorType errorType) {
        super(message, cause, code, errorType);
    }
}
