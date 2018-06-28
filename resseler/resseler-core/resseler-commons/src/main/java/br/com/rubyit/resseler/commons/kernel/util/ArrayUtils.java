package br.com.rubyit.resseler.commons.kernel.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * Utilitário para manipulação de arrays
 * 
 * @author Nalbatech
 */
public abstract class ArrayUtils {

    /** Definição de vírgulas */
    private static final String COMMAS = ", ";

    /**
     * Junta em uma única string os valores de um array separados por vírgula
     * 
     * @param vetor
     *            Array
     * 
     * @return String com os valores unidos
     */
    public static String join(Object vetor[]) {
        return join(vetor, ",");
    }

    /**
     * Junta em uma única string os valores de um array separados por vírgula
     * 
     * @param vetor
     *            Array
     * 
     * @return String com os valores unidos
     */
    public static String join(boolean vetor[]) {
        return join(vetor, ",");
    }

    /**
     * Junta em uma única string os valores de um array
     * 
     * @param vetor
     *            Array
     * 
     * @param separador
     *            Caracter delimitador de valores
     * 
     * @return String com os valores unidos
     */
    public static String join(boolean[] vetor, String separador) {
        StringBuilder resultado = new StringBuilder();
        for (int i = 0; i < vetor.length; i++) {
            if (resultado.length() > 0) {
                resultado.append(separador);
            }
            resultado.append(vetor[i]);
        }
        return resultado.toString();
    }

    /**
     * Junta em uma única string os valores de um array
     * 
     * @param vetor
     *            Array
     * 
     * @param separador
     *            Caracter delimitador de valores
     * 
     * @return String com os valores unidos
     */
    public static String join(Object[] vetor, String separador) {
        StringBuilder resultado = new StringBuilder();
        for (int i = 0; i < vetor.length; i++) {
            if (resultado.length() > 0) {
                resultado.append(separador);
            }
            if (vetor[i] instanceof Long) {
                resultado.append(String.valueOf((Long) vetor[i]));
            } else {
                resultado.append(vetor[i].toString());
            }
        }
        return resultado.toString();
    }

    /**
     * Transforma uma série de valores em uma lista
     * 
     * @param values
     *            Série
     * 
     * @return Lista com os valores da série
     */
    public static List<String> asList(String... values) {
        List<String> list = new ArrayList<>();
        for (String value : values) {
            list.add(value);
        }
        return list;
    }

    /**
     * Junta em uma única string os valores de um array separados por vírgula
     * 
     * @param values
     *            Array
     * 
     * @return String com os valores unidos
     */
    public static String joinWithCommas(Object[] values) {
        return join(values, COMMAS);
    }

    /**
     * Faz o "boxing" de cada um dos elementos de um array
     * 
     * @param itens
     *            Array de instâncias
     * 
     * @param <T>
     *            Tipo do objeto
     * 
     * @return Array com os itens convertidos para objetos
     */
    public static <T> Object[] toObject(T[] itens) {
        Object[] objs = new Object[itens.length];

        for (int i = 0; i < objs.length; i++) {
            objs[i] = itens[i];
        }

        return objs;
    }

	public static List<String> convertToArrayListString(final ArrayList<Object> list) {
		
		// usando fluent interface para reduzir dependencias de import
        final List<String> categoriesStringList = list.stream()
        		   // Passando o LAMBDA J8 "object -> Objects.toString(object, null)" para a chamada de map
        		   .map(object -> Objects.toString(object, null))
        		   .collect(Collectors.toList());
		
        return categoriesStringList;
	}
}
