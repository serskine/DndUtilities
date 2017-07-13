package com.soupthatisthick.encounterbuilder.util.translater;

import com.soupthatisthick.util.Logger;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Owner on 2/10/2017.
 * Copyright of Stuart Marr Erskine, all rights reserved.
 */

public class DateTranslater {

    /**
     * use this regular expression to see if a string matches the pattern
     */
    public static final String REGEX = "\\d\\d\\d\\d/\\d\\d/\\d\\d";

    /**
     * Used by the formatter yyyy/mm/dd
     */
    public static final String PATTERN = "yyyy/MM/dd";
    public static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat(PATTERN);

    /**
     * Translates input text into a date.
     * @param text
     * @return
     */
    public static final Date translate(String text)
    {
        try {
            return DATE_FORMAT.parse(text);
        } catch (Exception e)
        {
            Logger.error("Failed to parse date properly. Assuming null", e);
            return null;
        }
    }

    public static final String translate(Date date)
    {
        return DATE_FORMAT.format(date);
    }
}
