package br.com.rubyit.resseler.core.business;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import br.com.rubyit.resseler.commons.kernel.dto.IdentityDTO;
import br.com.rubyit.resseler.core.commons.dto.Partner;
import br.com.rubyit.resseler.core.commons.dto.Salesman;
import br.com.rubyit.resseler.core.commons.dto.SystemPerson;

/**
 * Util class to parse values
 *
 * @author a42239
 */
public final class ConverterUtil {

    public static final String FORMAT_MM_SLASH_YY = "MM/yy";
    private static Logger log = LogManager.getLogger(ConverterUtil.class);

    private ConverterUtil() {
    }

    /**
     * Método utilitário responsável pela
     * conversão de string para data atráves
     * de um formato. exemplo:
     * dd/MM/yyyy HH:mm:ss
     * dd/MM/yyyy
     *
     * @return date
     * @param format
     * @param target
     *
     */
    public static Date parseStringToDate(final String format,
            final String target) {
        if ((format == null) || (target == null)) { return null; }
        Date result = null;
        try {
            final DateFormat df = new SimpleDateFormat(format);
            result = df.parse(target);
        } catch (final ParseException ex) {
            log.error("ERROR: fail to parse String to Date", ex);
        }

        return result;
    }

    /**
     * Método utilitário responsável pela
     * conversão de uma data para um string 
     * atraves de um formato definido.
     * exemplo:
     * dd/MM/yyyy HH:mm:ss
     *
     * @return string
     * @param format
     * @param target
     *
     */
    public static String formaDateToString(final String format,
            final Date target) {
        if ((format == null) || (target == null)) { return null; }
        final DateFormat df = new SimpleDateFormat(format);
        final String result = df.format(target);

        return result;
    }

    /**
     * Método responsável por converter 
     * o objeto recebido da requisita para um
     * dto Person
     *
     * @param salesman
     * @return
     */
    public static SystemPerson converterPerson(final Salesman salesman) {
        final SystemPerson systemPerson = new SystemPerson();
        systemPerson.setPartner(new Partner());
        if (salesman.getPartner() != null) {
            systemPerson.getPartner().setID(salesman.getPartner().getID());
        }
        systemPerson.setIdentity(new IdentityDTO());
        systemPerson.getIdentity()
                .setDocumentValue(salesman.getIdentity().getDocumentValue());
        systemPerson.setBirthDate(salesman.getBirthDate());
        systemPerson.setFullName(salesman.getFullName());
        systemPerson.setRole(salesman.getRole());
        return systemPerson;
    }
}
