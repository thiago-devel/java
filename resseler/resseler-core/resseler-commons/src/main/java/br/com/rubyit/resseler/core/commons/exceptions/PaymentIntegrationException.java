package br.com.rubyit.resseler.core.commons.exceptions;

import br.com.rubyit.resseler.core.enums.ErrorType;

/**
 * Classe de exceção referente a integração de pagamento
 * @author b11527
 *
 */
public class PaymentIntegrationException extends RawException {

    /**
     * Método de lançamento da exception PaymentIntegrationException
     * @param message
     * @param cause
     * @param code
     * @param errorType
     */
    public PaymentIntegrationException(final String message,
            final Throwable cause, final String code,
            final ErrorType errorType) {
        super(message, cause, code, errorType);
    }
}
