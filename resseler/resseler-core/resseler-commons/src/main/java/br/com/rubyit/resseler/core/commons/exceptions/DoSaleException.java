package br.com.rubyit.resseler.core.commons.exceptions;

import br.com.rubyit.resseler.core.enums.ErrorType;

/**
 * Classe de exceção referente ao processo de venda
 * @author b11527
 *
 */
public class DoSaleException extends RawException {
    
    /**
     * Método de lançamento da exception DoSaleException
     * @param message
     * @param cause
     * @param code
     * @param errorType
     */
    public DoSaleException(final String message, final Throwable cause,
            final String code, final ErrorType errorType) {
        super(message, cause, code, errorType);
    }
}
