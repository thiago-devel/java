package br.com.rubyit.resseler.core.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.xml.bind.annotation.adapters.XmlAdapter;

/**
 * Class CalendarYearDateAdapter
 * Calendar Adapter
 * @author b11527
 *
 */
public final class CalendarYearDateAdapter extends XmlAdapter<String, Calendar> {

    public static final String YEARMONTHDAY = "yyyy-MM-dd";
    public static final String YEAR4DIGIT = "yyyy";
    private final String format;

    /**
     * Default contructor
     */
    public CalendarYearDateAdapter() {
        format = YEARMONTHDAY;

    }

    /**
     * Contructor
     * with param format
     * @param format
     */
    public CalendarYearDateAdapter(final String format) {
        this.format = format;
    }

    /**
     * unmarshal
     * String to Calendar
     * @return Calendar
     */
    @Override
    public Calendar unmarshal(final String value) {
        try {
            final SimpleDateFormat sdf = new SimpleDateFormat(format);
            final Date date = sdf.parse(value);
            final Calendar result = Calendar.getInstance();
            if (date != null) {
                result.setTime(date);
            }

            return result;
        } catch (final ParseException e) {
            throw new IllegalArgumentException(e);
        }
    }

    /**
     * marshal
     * Calendar to String
     * @return String
     */
    @Override
    public String marshal(final Calendar value) {
        if (value == null) { return null; }

        final SimpleDateFormat sdf = new SimpleDateFormat(format);
        final Date date = value.getTime();
        final String result = sdf.format(date);

        return result;
    }

}
