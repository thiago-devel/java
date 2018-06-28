package br.com.rubyit.resseler.soa.bs.sales.jaxb.adapters;

import java.util.Calendar;

import javax.xml.bind.annotation.adapters.XmlAdapter;

public class DateTypeCalendarAdapter extends XmlAdapter<String, Calendar> {

	/**
     * unmarshal
     * @param String
     * @return calendar
     * 
     */
    @Override
    public Calendar unmarshal(final String value) {
        if ((value != null)
                && !"".equals(value.trim())) { return javax.xml.bind.DatatypeConverter.parseDateTime(value); }

        return null;
    }

    /**
     * marshal
     * @param Calendar
     * @return string
     * 
     */
    @Override
    public String marshal(final Calendar value) {
        if (value == null) { return null; }
        return javax.xml.bind.DatatypeConverter.printDateTime(value);
    }
}
