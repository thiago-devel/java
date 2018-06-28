package br.com.rubyit.resseler.core.commons.exceptions;

import br.com.rubyit.resseler.core.enums.ErrorType;

/**
 * Classe de exceção referente ao certificado
 * @author b11527
 *
 */
public class InsuranceCertificateException extends RawException {

    /**
     * Método de lançamento da exception InsuranceCertificateException
     * @param message
     * @param cause
     */
    public InsuranceCertificateException(final String message,
            final Throwable cause, final String code,
            final ErrorType errorType) {
        super(message, cause, code, errorType);
    }

    /**
     * Método de lançamento da exception InsuranceCertificateException
     * @param message
     * @param cause
     */
    public InsuranceCertificateException(final String message,
            final String code, final ErrorType errorType) {
        super(message, code, errorType);
    }
}
