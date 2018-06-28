package br.com.rubyit.resseler.commons.kernel.util;

import java.util.Random;

/**
 * Utilitário para manipulação de strings
 * 
 * @author Nalbatech
 */
public abstract class StringUtils {

    /**
     * Obtém valores a partir de uma máscara
     * 
     * @param pMask
     *            Máscara
     * 
     * @param pValue
     *            Valor
     * 
     * @param pReturnValueEmpty
     *            Indica se deve ou não retornar uma string em branco no caso do
     *            valor estar nulo ou vazio
     * 
     * @return Retorna a string da máscara
     */
    private static String getValueMaskFormat(String pMask, String pValue, boolean pReturnValueEmpty) {

        if (pReturnValueEmpty && (pValue == null || pValue.trim().isEmpty())) { return ""; }

        /*  
         * Substituir as mascaras passadas como  9, X, * por # para efetuar a formatcao  
         */
        String mask = pMask;

        mask = mask.replaceAll("[*]", "#");
        mask = mask.replaceAll("[9]", "#");
        mask = mask.toUpperCase().replaceAll("[X]", "#");

        /*  
         * Formata valor com a mascara passada    
         */
        int i;
        int ultimaPosicaoSubstituida = -1;
        for (i = 0; i < pValue.length(); i++) {
            ultimaPosicaoSubstituida = mask.indexOf('#');
            mask = mask.replaceFirst("#", pValue.substring(i, i + 1));
        }

        /*  
         * Subistitui por string vazia os digitos restantes da mascara  
         * quando o valor passado e menor que a mascara    
         */
        return mask.substring(0, ultimaPosicaoSubstituida + 1);
    }

    /**
     * Formata uma string com uma máscara
     * 
     * @param texto
     *            String
     * 
     * @param mascara
     *            Máscara
     * 
     * @return String formatada
     */
    public static String mask(String texto, String mascara) {
        return getValueMaskFormat(mascara, texto, false);
    }

    /**
     * Remove separadores numéricos
     * 
     * @param text
     *            Texto
     * 
     * @return Texto sem os separadores
     */
    public static String removeSeparators(String text) {
        if (text == null) { return null; }

        return text.replaceAll("[)(.,/,-]", "");
    }

    /**
     * Retira acentos de uma string Exemplo: 'Açúcar' retorna 'Acucar'
     * 
     * @param str
     *            Texto com acentos
     * 
     * @return Texto sem acentos
     */
    public static String removeAccents(String str) {
        String temp = null;
        temp = java.text.Normalizer.normalize(str, java.text.Normalizer.Form.NFD);
        temp = temp.replaceAll("[^\\p{ASCII}]", "");
        return temp;
    }

    /**
     * Obtém a primeira palavra de uma string
     * 
     * @param texto
     *            String
     * 
     * @return Primeira palavra da string
     */
    public static String firstWord(String texto) {
        if (texto.trim().equals("")) { return ""; }
        int pos = find(texto, " ");
        if (pos <= 0) {
            return texto;
        } else {
            return mid(texto, 1, pos - 1);
        }
    }

    /**
     * Obtém a última palavra de uma string
     * 
     * @param texto
     *            String
     * 
     * @return Última palavra da string
     */
    public static String lastWord(String texto) {
        int ultimoEspaco = texto.lastIndexOf(' ');

        if (ultimoEspaco < 0) { return texto; }
        return texto.substring(ultimoEspaco + 1);
    }

    /**
     * Converte a primeira letra de uma string para minúsculo
     * 
     * @param texto
     *            Texto que será convertido
     * 
     * @return Texto com a primeira letra convertida
     */
    public static String firstLower(String texto) {
        return StringUtils.lower(StringUtils.left(texto, 1)) + StringUtils.mid(texto, 2);
    }

    /**
     * Converte para maiúsculo a primeira letra de todas as palavras de uma
     * string
     * 
     * @param text
     *            Texto que será convertido
     * 
     * @return Texto com a primeira letra convertida em todas as palavras
     */
    public static String firstUpperAllWords(String text) {

        String[] parts = text.split("[ ]");

        for (int i = 0; i < parts.length; i++) {
            if (parts[i].length() > 1) {
                parts[i] = firstUpper(parts[i].toLowerCase());
            }
        }

        return ArrayUtils.join(parts, " ");
    }

    /**
     * Converte a primeira letra de uma string para maiúsculo
     * 
     * @param texto
     *            Texto que será convertido
     * 
     * @return Texto com a primeira letra convertida
     */
    public static String firstUpper(String texto) {
        return StringUtils.upper(StringUtils.left(texto, 1)) + StringUtils.mid(texto, 2);
    }

    /**
     * Obtém string de quebra de linha
     * 
     * @return String de quebra de linha
     */
    public static String vbCrLf() {
        return "\r\n";
    }

    /**
     * Substitui um texto por outro em uma string
     * 
     * @param texto
     *            String que será manipulada
     * 
     * @param textoLocalizar
     *            Texto pesquisado
     * 
     * @param textoSubstituir
     *            Texto de substituição
     * 
     * @return String com as substituições realizadas
     */
    public static String replace(String texto, String textoLocalizar, String textoSubstituir) {
        return texto.replace(textoLocalizar, textoSubstituir);
    }

    /**
     * Elimina os espaços no início e no fim de uma string
     * 
     * @param texto
     *            Texto
     * 
     * @return Texto sem os espaços no início e no fim da string
     */
    public static String trim(String texto) {
        return texto == null ? null : texto.trim();
    }

    /**
     * Retorna a posição da primeira ocorrência de um determinado critério em um
     * texto
     * 
     * @param texto
     *            Texto que será pesquisado
     * 
     * @param localiza
     *            Critério de pesquisa
     * 
     * @return Retorna a posição da primeira ocorrência; Se não encontrar o
     *         critério de pesquisa retorna zero
     */
    public static int find(String texto, String localiza) {
        return find(1, texto, localiza);
    }

    /**
     * Retorna a posição da primeira ocorrência de um determinado critério em um
     * texto
     * 
     * @param start
     *            Posição inicial de busca
     * 
     * @param text
     *            Texto que será pesquisado
     * 
     * @param word
     *            Critério de pesquisa
     * 
     * @return Retorna a posição da primeira ocorrência; Se não encontrar o
     *         critério de pesquisa retorna zero
     */
    public static int find(int start, String text, String word) {
        int result = -1;
        int newStart = start;
        if (newStart == 0) {
            newStart = 1;
        }

        result = text.indexOf(word, newStart - 1);

        return result + 1;
    }

    /**
     * Converte um texto para minúsculo
     * 
     * @param texto
     *            Texto que será convertido
     * 
     * @return Texto convertido
     */
    public static String lower(String texto) {
        return texto.toLowerCase();
    }

    /**
     * Converte um texto para maiúsculo
     * 
     * @param texto
     *            Texto que será convertido
     * 
     * @return Texto convertido
     */
    public static String upper(String texto) {
        return texto.toUpperCase();
    }

    /**
     * Obtém o número de caracteres em um texto
     * 
     * @param texto
     *            Texto analisado
     * 
     * @return Tamanho da string
     */

    public static int length(String texto) {
        return texto.length();
    }

    /**
     * Obtém uma quantidade específica de caracteres à esquerda de uma
     * determinada string
     * 
     * @param string
     *            Texto
     * 
     * @param length
     *            Quantidade de caracteres
     * 
     * @return Resultado
     */
    public static String left(String string, int length) {
        if (string == null) { return null; }
        return string.substring(0, Math.min(length, string.length()));
    }

    /**
     * Obtém uma quantidade específica de caracteres à direita de uma
     * determinada string
     * 
     * @param texto
     *            Texto
     * 
     * @param tamanho
     *            Quantidade de caracteres
     * 
     * @return Resultado
     */
    public static String right(String texto, int tamanho) {
        return texto.substring(texto.length() - tamanho);
    }

    /**
     * Obtém uma substring a partir da posição inicial e o tamanho
     * 
     * @param string
     *            Texto
     * 
     * @param start
     *            Posição inicial
     * 
     * @param length
     *            Quantidade de caracteres
     * 
     * @return Resultado
     */
    public static String mid(String string, int start, int length) {
        int newStart = start - 1;
        if (length + newStart > string.length()) {
            return string.substring(newStart);
        } else {
            return string.substring(newStart).substring(0, length);
        }
    }

    /**
     * Obtém uma substring a partir da posição inicial
     * 
     * @param string
     *            Texto
     * 
     * @param start
     *            Posição inicial
     * 
     * @return Resultado
     */
    public static String mid(String string, int start) {
        int newStart = start - 1;
        if (newStart < 0) {
            newStart = 0;
        }
        return string.substring(newStart);
    }

    /**
     * Repete o comportamento do ISNULL() do SQLSERVER, decidindo entre dois
     * textos a partir da nulidade do primeiro.
     * 
     * @param valor
     *            Objeto que será analisado
     * 
     * @param retorno
     *            Valor retornado no caso do primeiro parâmetro ser nulo
     * 
     * @return Se o valor for nulo, retorna o parâmetro "retorno"; Caso
     *         contrário retorna o próprio valor
     */
    public static String isNull(Object valor, String retorno) {
        if (valor == null) {
            return retorno;
        } else {
            return valor.toString();
        }
    }

    /**
     * Se o valor especificado for nulo, retorna uma string vazia
     * 
     * @param valor
     *            Objeto que será analisado
     * 
     * @return Se o valor for nulo, retorna uma string vazia; Caso contrário
     *         retorna o próprio valor
     */
    public static String isNull(String valor) {
        return isNull(valor, "");
    }

    /**
     * Repete o comportamento do ISNULL() do SQLSERVER, decidindo entre dois
     * textos a partir do critério do primeiro estar vazio.
     * 
     * @param valor
     *            Objeto que será analisado
     * 
     * @param retorno
     *            Valor retornado no caso do primeiro parâmetro estar vazio
     * 
     * @return Se o valor estiver vazio, retorna o parâmetro "retorno"; Caso
     *         contrário retorna o próprio valor
     */
    public static String isEmpty(String valor, String retorno) {
        if (valor.equals("")) {
            return retorno;
        } else {
            return valor;
        }
    }

    /**
     * Repete o comportamento do ISNULL() do SQLSERVER, decidindo entre dois
     * textos a partir do critério do primeiro estar nulo ou vazio.
     * 
     * @param valor
     *            Objeto que será analisado
     * 
     * @param retorno
     *            Valor retornado no caso do primeiro parâmetro estar nulo ou
     *            vazio
     * 
     * @return Se o valor estiver nulo ou vazio, retorna o parâmetro "retorno";
     *         Caso contrário retorna o próprio valor
     */
    public static String isNullOrEmpty(String valor, String retorno) {
        if (valor == null || valor.trim().isEmpty()) {
            return retorno;
        } else {
            return valor;
        }
    }

    /**
     * Se o valor especificado estiver nulo, retorna uma string vazia
     * 
     * @param valor
     *            Valor analisado
     * 
     * @return Se o valor for nulo, retorna uma string vazia; Caso contrário
     *         retorna o próprio valor
     */
    public static String isNullThenEmpty(Object valor) {
        return isNull(valor, "");
    }

    /**
     * Se o valor especificado estiver nulo, retorna zero
     * 
     * @param valor
     *            Valor analisado
     * 
     * @return Se o valor for nulo, retorna zero; Caso contrário retorna o
     *         próprio valor
     */
    public static String isNullThenZero(Object valor) {
        return isNull(valor, "0");
    }

    /**
     * Gera uma chave aleatória
     * 
     * @param tamanho
     *            Quantidade de caracteres
     * 
     * @return Chave gerada
     */
    public static String generate(int tamanho) {
        Random r = new Random();
        long num = r.nextLong();

        return left(Long.toHexString(num), tamanho);
    }

    /**
     * Remove um pedaço de uma string
     * 
     * @param texto
     *            Texto que será alterado
     * 
     * @param inicio
     *            Posição inicial do texto que será removido
     * 
     * @param fim
     *            Posição final do texto que será removido
     * 
     * @return String alterada
     */
    public static String remove(String texto, int inicio, int fim) {
        return left(texto, inicio - 1) + mid(texto, fim + 1);
    }

    /**
     * Converte para maiúscula a primeira letra de todos as palavras de uma
     * string, separadas por um delimitador
     * 
     * @param text
     *            Texto que será manipulado
     * 
     * @param separator
     *            Separador de palavras
     * 
     * @return String alterada
     */
    public static String allFirstUpper(String text, String separator) {

        String[] parts = text.split("[" + separator + "]");

        for (int i = 0; i < parts.length; i++) {
            parts[i] = firstUpper(parts[i].toLowerCase());
        }

        return ArrayUtils.join(parts, "");
    }

    /**
     * Transforma um texto de CamelCase (todas as primeiras letras maiúscula, e
     * os espaços substituídos por "_") para uma string comum. Exemplo:
     * LINE_OF_BUSINESS = Line Of Business
     * 
     * @param text
     *            Texto
     * 
     * @return Texto convertido
     */
    public static String camelCase(String text) {
        String result = allFirstUpper(text, "_");

        return firstLower(result);
    }

    /**
     * Repete um caracter ou string por uma determinada quantidade de vezes
     * 
     * @param string
     *            String que será repetida
     * 
     * @param count
     *            Quantidade de vezes
     * 
     * @return Resultado
     */
    public static String repeat(String string, int count) {
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < count; i++) {
            sb.append(string);
        }

        return sb.toString();
    }

    /**
     * Complementa uma string com uma determinada quantidade de caracteres à
     * esquerda
     * 
     * @param string
     *            Texto que será complementado
     * 
     * @param length
     *            Tamanho do campo
     * 
     * @param pad
     *            Caracter que será utilizado para complementação
     * 
     * @return Texto complementado
     */
    public static String leftPad(String string, int length, String pad) {
        if (string == null) { return null; }
        String result;
        if (string.length() < length) {
            result = repeat(pad, length - string.length()) + string;
        } else {
            result = string;
        }
        return result;
    }

    /**
     * Complementa uma string com uma determinada quantidade de caracteres à
     * direita
     * 
     * @param string
     *            Texto que será complementado
     * 
     * @param length
     *            Tamanho do campo
     * 
     * @param pad
     *            Caracter que será utilizado para complementação
     * 
     * @return Texto complementado
     */
    public static String rightPad(String string, int length, String pad) {
        if (string == null) { return null; }
        String result;
        if (string.length() < length) {
            result = string + repeat(pad, length - string.length());
        } else {
            result = string;
        }
        return result;
    }

    /**
     * Retorna uma string com uma quantidade definida de espaços
     * 
     * @param quant
     *            Quantidade de espaços
     * 
     * @return Resultado
     */
    public static String space(int quant) {
        return repeat(" ", quant);
    }

    /**
     * Obtém a diferença da quantidade de caracteres de uma expressão em um
     * texto
     * 
     * @param text
     *            Texto analisado
     * 
     * @param expression
     *            Expressão
     * 
     * @return Número de caracteres do texto sem contar o tamanho da expressão
     */
    public static int count(String text, String expression) {
        return text.length() - text.replace(expression, "").length();
    }

    /**
     * Obtém um determinado elemento de uma lista de informações delimitadas por
     * um separador
     * 
     * @param text
     *            Texto analisado
     * 
     * @param separator
     *            Separador
     * 
     * @param position
     *            Índice do elemento
     * 
     * @return Elemento desejado
     */
    public static String getDelimitedValue(String text, String separator, int position) {
        String[] values = text.split(separator);
        if (values.length < position) {
            return null;
        } else {
            return values[position];
        }
    }

    /**
     * Obtém o último elemento de uma lista de informações delimitadas por um
     * separador
     * 
     * @param text
     *            Texto analisado
     * 
     * @param separator
     *            Separador
     * 
     * @return Último elemento
     */
    public static String lastPart(String text, String separator) {
        String[] parts = text.split("[" + separator + "]");
        if (parts == null || parts.length == 0) { return null; }
        return parts[parts.length - 1];
    }

    /**
     * Retorna uma string vazia quando o objeto for nulo
     * 
     * @param value
     *            Objeto que será analisado
     * 
     * @return Retorna uma string vazia se o objeto for nulo; Caso contrário
     *         retorna o próprio objeto
     */
    public static String emptyWhenNull(Object value) {
        if (value == null) {
            return "";
        } else {
            return value.toString();
        }
    }

    /**
     * Remove caracteres especiais de uma string
     * 
     * @param text
     *            Texto que será substituído
     * 
     * @return Texto substituído
     */
    public static String removeSpecialCharacters(String text) {
        if (text == null || text.isEmpty()) { return text; }

        String res = text;
        res = res.replace("&", "");
        res = res.replace("<", "");
        res = res.replace(">", "");
        res = res.replace("~", "");
        res = res.replace("", "");
        res = res.replace("Þ", "");

        return res;
    }

    /**
     * Concatena uma série de textos
     * 
     * @param values
     *            Série de valores para concatenar
     * 
     * @return String com os valores concatenados
     */
    public static String concat(Object... values) {
        StringBuilder sb = new StringBuilder();
        for (Object value : values) {
            if (value != null) {
                sb.append(value);
            }
        }
        return sb.toString();
    }

    /**
     * Formata um texto à direita
     * 
     * @param text
     *            Texto
     * 
     * @param format
     *            Máscara do formato
     * 
     * @return Texto alinhado à direita
     */
    public static String rightFormat(String text, String format) {
        if (text == null) { return null; }
        if (text.length() >= format.length()) { return text.substring(text.length() - format.length()); }
        return format.substring(0, format.length() - text.length()) + text;
    }

    /**
     * Formata um texto à esquerda
     * 
     * @param text
     *            Texto
     * 
     * @param format
     *            Máscara do formato
     * 
     * @return Texto alinhado à esquerda
     */
    public static String leftFormat(String text, String format) {
        if (text.length() >= format.length()) { return text.substring(0, format.length()); }
        return text + format.substring(text.length());
    }

}
