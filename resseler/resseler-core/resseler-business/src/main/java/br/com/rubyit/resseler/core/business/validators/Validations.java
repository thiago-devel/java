package br.com.rubyit.resseler.core.business.validators;

/**
 * 
 * Classe utilitária responsável por algumas validações
 *
 */
public final class Validations {

    private Validations() {
        // COMMENT
    }

    /**
     * Método utilitário responsável pela
     * validação se um obj é vazio/nulo
     *
     * @return boolean
     *
     */
    public static boolean isEmpty(final Object obj) {
        if (obj == null) { return true; }

        if (obj.getClass().equals(String.class)) {
            final String str = (String) obj;

            if ("".equals(str.trim())) { return true; }
        }

        return false;
    }

    /**
     * Método utilitário responsável pela
     * validação se uma string é feita de número
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
}
