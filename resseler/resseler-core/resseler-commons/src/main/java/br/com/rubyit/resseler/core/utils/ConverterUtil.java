package br.com.rubyit.resseler.core.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public final class ConverterUtil {

    public static final String FORMAT_MM_SLASH_YY = "MM/yy";
    private static Logger log = LogManager.getLogger(ConverterUtil.class);

    /**
     * Default Constructor for IOC
     */
    private ConverterUtil() {
        // do nothing. IOC Injection point
    }

    /**
     * Conversão de string para data
     * 
     * @param format
     * @param target
     * @return date
     */
    public static Date parseStringToDate(final String format, final String target) {
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
     * Conversão de data para string
     * 
     * @param format
     * @param target
     * @return string
     */
    public static String formaDateToString(final String format, final Date target) {
        if ((format == null) || (target == null)) { return null; }
        final DateFormat df = new SimpleDateFormat(format);
        final String result = df.format(target);

        return result;
    }
}
