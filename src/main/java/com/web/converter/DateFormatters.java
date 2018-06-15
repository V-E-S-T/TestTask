package com.web.converter;

import org.springframework.format.Formatter;
import org.springframework.util.StringUtils;

import java.text.ParseException;
import java.util.Locale;
import java.sql.Date;

public class DateFormatters implements Formatter<Date> {

    @Override
    public Date parse(String text, Locale locale) throws ParseException {
        return StringUtils.isEmpty(text) ? null : Date.valueOf(text);
    }

    @Override
    public String print(Date ld, Locale locale) {
        return ld.toString();
    }

}
