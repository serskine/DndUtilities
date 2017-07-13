package com.soupthatisthick.encounterbuilder.util.translater;

import com.soupthatisthick.util.model.Rational;

/**
 * Created by Owner on 6/22/2017.
 * Copyright of Stuart Marr Erskine, all rights reserved.
 */

public class FractionTranslater {




    /**
     * This will return a double value as a simplified fraction string
     * @param value
     * @return
     */
    public static final String asFraction(double value) {
        Rational r = new Rational(value);
        return r.toString();
    }

}
