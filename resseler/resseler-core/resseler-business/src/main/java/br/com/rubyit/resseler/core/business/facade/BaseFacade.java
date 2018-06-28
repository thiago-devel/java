package br.com.rubyit.resseler.core.business.facade;

import java.util.Set;

import javax.xml.ws.WebServiceException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public abstract class BaseFacade {

    private static final Logger LOG = LogManager.getLogger(BaseFacade.class);

    protected String[] prepareHashID() {
        final String[] hashID = { "12345" };
        return hashID;
    }

    /**
     * methods para evitar transações/registros duplicados
     *
     * @param method
     *            O método em execução.
     * @param fields
     *            Os parâmetros únicos que irão compor o hash
     * @param servicosEmExecucao
     *            TODO
     */
    protected void iniciarExecucao(final String method, final String[] fields, final Set<String> servicosEmExecucao)
            throws WebServiceException {
        synchronized (servicosEmExecucao) {
            if (servicosEmExecucao.contains(buildHash(method, fields))) {
                final String message = "O método=[" + method + "] já está em execução [" + fields + "]";
                LOG.error(message);
                throw new WebServiceException(message);
            }

            servicosEmExecucao.add(buildHash(method, fields));
        }
    }

    /**
     * Assinala como finalizada a execução de um método específico.
     *
     * @param metodo
     *            O método em execução.
     * @param campos
     *            Os parâmetros únicos que irão compor o hash
     * @param servicosEmExecucao
     *            TODO
     */
    protected void finalizarExecucao(final String metodo, final String[] campos, final Set<String> servicosEmExecucao) {
        synchronized (servicosEmExecucao) {
            servicosEmExecucao.remove(buildHash(metodo, campos));
        }
    }

    /**
     * Constrói o Hash que será utilizado para detectar operações duplicadas.
     *
     *
     * @param metodo
     *            O método em execução
     * @param args
     *            Os parâmetros únicos que irão compor o hash
     * @return {@link String}
     */
    protected String buildHash(final String metodo, final String[] args) {
        final StringBuilder hash = new StringBuilder(metodo).append(".");
        for (final String arg : args) {
            hash.append(arg).append(".");
        }
        return hash.toString();
    }
}
