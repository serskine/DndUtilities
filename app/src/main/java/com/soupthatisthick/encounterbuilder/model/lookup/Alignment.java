package com.soupthatisthick.encounterbuilder.model.lookup;

/**
 * Created by Owner on 3/2/2017.
 * Copyright of Stuart Marr Erskine, all rights reserved.
 */

public enum Alignment {
    LAWFUL_GOOD("Lawful Good"),
    NEUTRAL_GOOD("Neutral Good"),
    CHAOTIC_GOOD("Chaotic Good"),
    LAWFUL_NEUTRAL("Lawful Neutral"),
    UNALINED("Unaligned"),
    CHAOTIC_NEUTRAL("Chaotic Neutral"),
    LAWFUL_EVIL("Lawful Evil"),
    NEUTRAL_EVIL("Neutral Evil"),
    CHAOTIC_EVIL("Chaotic Evil");

    public final String displayString;

    Alignment(String displayString)
    {
        this.displayString = displayString;
    }

    @Override
    public String toString()
    {
        return displayString;
    }
}
