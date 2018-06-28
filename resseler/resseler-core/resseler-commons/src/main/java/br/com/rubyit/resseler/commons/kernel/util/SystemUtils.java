package br.com.rubyit.resseler.commons.kernel.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Utilit�rio de intera��o com o sistema operacional
 *
 * @author Nalbatech
 */
public abstract class SystemUtils {

    /**
     * Obt�m o usu�rio logado do sistema operacional
     *
     * @return Nome do usu�rio
     */
    public static String userName() {
        return System.getProperty("user.name");
    }

    /**
     * Obt�m o nome do sistema operacional
     *
     * @return Nome do sistema operacional
     */
    public static String soName() {
        return System.getProperty("os.name");
    }

    /**
     * Copia os dados de um inputStream para um arquivo
     *
     * @param is
     *            InputStream com os dados
     *
     * @param outFile
     *            Arquivo de sa�da
     *
     * @throws IOException
     *             Ocorre se houver algum erro se houver alguma
     *             indisponibilidade nos arquivos
     */
    public static void copyFile(final InputStream is, final File outFile) throws IOException {
        final OutputStream os = new FileOutputStream(outFile);

        byte[] buffer;
        try {
            buffer = new byte[is.available()];
            is.read(buffer);
            os.write(buffer);
        } finally {
            os.close();
        }
    }

    /**
     * Remove as barras dos paths
     *
     * @param text
     *            Path
     *
     * @return Valor convertido, sem as barras
     */
    public static String removeFileSeparators(final String text) {
        if (text == null) { return null; }
        String res = text.replace("\\", "");
        res = res.replace("/", "");

        return res;
    }

    /**
     * Converte o separador de arquivos de uma string para aquele do sistema
     * operacional em uso
     *
     * @param path
     *            String com o path
     *
     * @return Path com as barras convertidas
     */
    public static String updateFileSeparators(final String path) {
        String res = "";

        res = StringUtils.replace(path, "\\", File.separator);
        res = StringUtils.replace(res, "/", File.separator);

        return res;
    }

    /**
     * Cria uma URL a partir de uma string
     *
     * @param url
     *            String com a URL
     *
     * @return Inst�ncia da URL
     */
    public static URL createURL(final String url) {
        try {
            return new URL(url);
        } catch (final MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Verifica se o obj é null ou vazio
     * 
     * @param obj
     * @return
     */
    public static boolean isNullOrEmpty(final Object obj) {
        return ((obj == null) || obj.toString().trim().equals("")
                || ((obj instanceof Object[]) && (((Object[]) obj).length == 0))) ? Boolean.TRUE : Boolean.FALSE;
    }

}
