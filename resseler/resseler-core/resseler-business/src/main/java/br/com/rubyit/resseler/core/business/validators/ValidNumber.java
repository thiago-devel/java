package br.com.rubyit.resseler.core.business.validators;

/**
 * 
 * Classe utilitária para validação de númeração
 *
 */
public final class ValidNumber {
    private ValidNumber() {
    }

    /**
     * Método utilitário responsável pela
     * validação se uma string é feita de números
     *
     * @return boolean
     *
     */
    public static boolean isValidNumber(final String value) {

        for (int i = 0; i < value.length(); i++) {
            if (!Character.isDigit(value.charAt(i))) { return false; }
        }
        return true;
    }

    /**
     * Método utilitário responsável pela
     * validação se uma string contém número
     *
     * @return boolean
     *
     */
    public static boolean containsNumber(final String value) {

        for (int i = 0; i < value.length(); i++) {
            if (Character.isDigit(value.charAt(i))) { return true; }
        }
        return false;
    }
}
