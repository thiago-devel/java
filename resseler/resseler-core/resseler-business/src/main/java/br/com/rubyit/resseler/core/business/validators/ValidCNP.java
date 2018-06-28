package br.com.rubyit.resseler.core.business.validators;

/**
 * 
 * Classe utilitária de validação de CPF e CNPJ
 *
 */
public final class ValidCNP {

    private static final int[] pesoCPF = { 11, 10, 9, 8, 7, 6, 5, 4, 3, 2 };
    private static final int[] pesoCNPJ = { 6, 5, 4, 3, 2, 9, 8, 7, 6, 5, 4, 3, 2 };

    private ValidCNP() {
    }

    /**
     * Método utilitário responsável pela
     * Validação de CPF/CNPJ
     * @return int
     *
     */
    private static int calcularDigito(final String str, final int[] peso) {
        int sum = 0;
        for (int index = str.length() - 1; index >= 0; index--) {
            final int digit = Integer.parseInt(str.substring(index, index + 1));
            sum += digit * peso[(peso.length - str.length()) + index];
        }
        sum = 11 - (sum % 11);

        return sum > 9 ? 0 : sum;
    }

    /**
     * Método utilitário responsável pela
     * Validação de CPF
     * @return boolean
     *
     */
    public static boolean isValidCPF(final String cpf) {
        if ((cpf == null) || (cpf.length() != 11)) { return false; }

        final Integer digito1 = calcularDigito(cpf.substring(0, 9), pesoCPF);
        final Integer digito2 = calcularDigito(cpf.substring(0, 9) + digito1,
                pesoCPF);
        return cpf.equals(
                cpf.substring(0, 9) + digito1.toString() + digito2.toString());
    }

    /**
     * Método utilitário responsável pela
     * validação do tamanho de um CPF
     * @param cpf
     * @return
     */
    public static boolean isValidCPFLength(final String cpf) {
        if ((cpf != null) && (cpf.length() != 11)) { return false; }
        return true;
    }

    /**
     * Método utilitário responsável pela
     * Validação de CNPJ
     * @return boolean
     */
    public static boolean isValidCNPJ(final String cnpj) {
        if ((cnpj == null) || (cnpj.length() != 14)) { return false; }

        final Integer digito1 = calcularDigito(cnpj.substring(0, 12), pesoCNPJ);
        final Integer digito2 = calcularDigito(cnpj.substring(0, 12) + digito1,
                pesoCNPJ);
        return cnpj.equals(cnpj.substring(0, 12) + digito1.toString()
                + digito2.toString());
    }
}
