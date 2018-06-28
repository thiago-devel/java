package br.com.rubyit.resseler.commons.kernel.util;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Facilitadores de formatação de dados
 * 
 * @author Nalbatech
 */
public abstract class FormatUtils {

    /** Número de dígitos no CNPJ */
    public static final int CNPJ_DIGITS = 14;

    /** Número de dígitos no CPF */
    public static final int CPF_DIGITS = 11;

    /** Número de dígitos de um período */
    public static final int PERIOD_DIGITS = 11;

    /** Região para formatação */
    public static final Locale BRAZIL = new Locale("pt", "BR");

    /**
     * Formata um CEP numérico
     * 
     * @param cep
     *            CEP numérico
     * 
     * @return CEP formatado
     */
    public static String formatCep(Integer cep) {
        if (cep == null) {
            return null;
        } else {
            return formatCep(format(cep, "00000000"));
        }
    }

    /**
     * Formata um CEP que está como string
     * 
     * @param cep
     *            CEP não formatado
     * 
     * @return CEP formatado
     */
    public static String formatCep(String cep) {
        if (cep == null) { return null; }
        String textoCep = StringUtils.removeSeparators(cep);
        textoCep = StringUtils.leftPad(textoCep, 8, "0");
        return StringUtils.mask(textoCep, "#####-###");
    }

    /**
     * Formata período (mm/aaaa)
     * 
     * @param periodo
     *            Período em formato texto (mmaaaa)
     * 
     * @return Período formatado
     */
    public static String formatPeriod(String periodo) {
        String periodoFormatado = String.valueOf(periodo);

        periodoFormatado = periodo.substring(0, 2) + "/" + periodo.substring(2, 6);

        return periodoFormatado;
    }

    /**
     * Formata uma hora para dd/mm/aaaa
     * 
     * @param dataHora
     *            Data de referência
     * 
     * @return Data formatada (dd/mm/aaaa)
     */
    public static String format(Date dataHora) {
        return format(dataHora, "dd/MM/yyyy");
    }

    /**
     * Formata uma data para um formato específico
     * 
     * @param dataHora
     *            Data de referência
     * 
     * @param formato
     *            Formato
     * 
     * @return Data formatada
     */
    public static String format(Date dataHora, String formato) {
        if (dataHora == null) {
            return null;
        } else {
            SimpleDateFormat formatador = new SimpleDateFormat(formato);
            return formatador.format(dataHora);
        }
    }

    /**
     * Formata um número para um formato específico
     * 
     * @param numero
     *            Número
     * 
     * @param formato
     *            Formato
     * 
     * @return Número formatado
     */
    public static String format(Number numero, String formato) {

        if (numero == null) {
            return null;
        } else {
            if (formato.contains("@")
                    || formato.equals("&")) { return formatDelimitedText(numero.toString(), formato); }

            NumberFormat formatador = new DecimalFormat(formato);

            return formatador.format(numero);

        }
    }

    /**
     * Formata um número como moeda
     * 
     * @param numero
     *            Número
     * 
     * @return Valor formatado
     */
    public static String money(Number numero) {
        if (numero == null) {
            return null;
        } else {
            NumberFormat formatador = NumberFormat.getCurrencyInstance(Locale.getDefault());
            return formatador.format(numero);
        }
    }

    /**
     * Formata um número como moeda para um país específico
     * 
     * @param numero
     *            Número
     * 
     * @param locale
     *            Região (país)
     * 
     * @return Valor formatado
     */
    public static String money(Number numero, Locale locale) {
        if (numero == null) {
            return null;
        } else {
            NumberFormat formatador = NumberFormat.getCurrencyInstance(locale);
            return formatador.format(numero);
        }
    }

    /**
     * Formata um texto
     * 
     * @param texto
     *            Texto
     * 
     * @param formato
     *            Formato: <code>
     * &gt; : formata o texto como uppercase
     * &lt; : formata o texto como lowercase
     * &amp; : formata como texto delimitado, de acordo com a quantidade de espaços
     * 0 : remove os separadores "[)(.,/,-]"
     * </code>
     * 
     * @return Valor formatado
     */
    public static String format(String texto, String formato) {
        if (texto == null) { return null; }
        if (formato.equals(">")) {
            return texto.toUpperCase();
        } else if (formato.equals("<")) {
            return texto.toLowerCase();
        } else if (formato.contains("@") || formato.contains("&")) {
            return formatDelimitedText(texto, formato);
        } else if (formato.contains("0")) {
            String plainFormat = StringUtils.removeSeparators(formato);
            if (plainFormat.length() > texto.length()) {
                texto = StringUtils.leftPad(texto, plainFormat.length(), "0");
            }
            String newFormat = formato.replace("0", "@");
            return formatDelimitedText(texto, newFormat);
        } else {
            return StringUtils.mask(texto, formato);
        }
    }

    /**
     * Formata um texto delimitado
     * 
     * @param text
     *            Texto
     * 
     * @param format
     *            Se tiver a string "!" o valor será alinhado à direita; A
     *            quantidade de caracteres delimitados será de acordo com o
     *            tamanho dessa string
     * 
     * @return Valor formatado
     */
    private static String formatDelimitedText(String text, String format) {
        String charMask;

        if (format.contains("&")) {
            charMask = "&";
        } else if (format.contains("@")) {
            charMask = "@";
        } else {
            return text;
        }

        int length = StringUtils.count(format, charMask);

        String result = text;
        int diff = length - text.length();
        if (diff > 0) {
            if (format.contains("!")) {
                result = StringUtils.rightPad(result, length, " ");
            } else {
                result = StringUtils.leftPad(result, length, " ");
            }
        }
        if (diff < 0) {
            if (format.contains("!")) {
                result = StringUtils.right(result, length);
            } else {
                format = format + StringUtils.repeat(charMask, Math.abs(diff));
            }
        }
        String mask = format.replace(charMask, "#").replace("!", "");

        result = StringUtils.mask(result, mask);

        if (charMask.equals("&") && diff > 0) {
            if (format.contains("!")) {
                result = result.substring(0, result.length() - diff);
            } else {
                result = result.substring(diff);
            }
        }

        return result;
    }

    /**
     * Formata um long em uma string sem casas decimais
     * 
     * @param numero
     *            Número
     * 
     * @return Formata o número sem casas decimais (#,##0)
     */
    public static String format(long numero) {
        return format(numero, "#,##0");
    }

    /**
     * Formata um double em uma string com duas casas decimais
     * 
     * @param numero
     *            Número
     * 
     * @return Formata o número com duas casas decimais (#,##0.00)
     */
    public static String format(double numero) {
        return format(numero, "#,##0.00");
    }

    /**
     * Formata um BigDecimal em uma string com duas casas decimais
     * 
     * @param numero
     *            Número
     * 
     * @return Formata o número com duas casas decimais (#,##0.00)
     */
    public static String format(BigDecimal numero) {
        return format(numero, "#,##0.00");
    }

    /**
     * Formata um CPF
     * 
     * @param cpf
     *            String com o CPF sem formatação
     * 
     * @return CPF formatado
     */
    public static String formatCpf(String cpf) {
        if (cpf == null) { return null; }
        return StringUtils.mask(cpf.trim(), "###.###.###-##");
    }

    /**
     * Formata um CNPJ
     * 
     * @param cnpj
     *            String com CNPJ sem formatação
     * 
     * @return CNPJ formatado
     */
    public static String formatCnpj(String cnpj) {
        if (cnpj == null) { return null; }
        String result = StringUtils.removeSeparators(cnpj.trim());

        if (result.length() < CNPJ_DIGITS) {
            result = StringUtils.leftPad(result, CNPJ_DIGITS, "0");
        }

        return StringUtils.mask(result, "##.###.###/####-##");
    }

    /**
     * Formata um CPF ou CNPJ
     * 
     * @param cpfCnpj
     *            CPF ou CNPJ
     * 
     * @return Valor formatado
     */
    public static String formatCpfCnpj(String cpfCnpj) {
        if (cpfCnpj.length() >= CNPJ_DIGITS) {
            return formatCnpj(cpfCnpj);
        } else {
            return formatCpf(cpfCnpj);
        }
    }

    /**
     * Formata um telefone
     * 
     * @param telefone
     *            String com telefone sem formatação
     * 
     * @return Telefone formatado
     */
    public static String formatarTelefone(String telefone) {
        if (telefone == null) { return null; }
        return StringUtils.mask(telefone, "(##)####-####");
    }

    /**
     * Formata o número da master policy
     * 
     * @param masterPolicyId
     *            Número da master policy
     * 
     * @param endorsement
     *            Número do endosso
     * 
     * @return Master policy formatada
     */
    public static String formatMasterPolicyId(Long masterPolicyId, Integer endorsement) {
        final String ID_FORMAT = "000000000";
        final String ENDORSEMENT_FORMAT = "00";

        String formattedId = FormatUtils.format(masterPolicyId, ID_FORMAT);
        formattedId = formattedId.substring(0, 8) + "-" + formattedId.substring(8);

        String formattedEndorsId = FormatUtils.format(endorsement, ENDORSEMENT_FORMAT);

        return formattedId + "/" + formattedEndorsId;
    }
}
