package com.soupthatisthick.encounterbuilder.model.lookup;

/**
 * Created by Owner on 2/28/2017.
 * Copyright of Stuart Marr Erskine, all rights reserved.
 */

public enum SpellSchool {
    ABJURATION("Abjuration"),
    CONJURATION("Conjuration"),
    DIVINATION("Divination"),
    ENCHANTMENT("Enchantment"),
    EVOCATION("Evocation"),
    ILLUSION("Illusion"),
    NECROMANCY("Necromancy"),
    TRANSMUTATION("Transmutation"),
    OTHER("Other");

    public final String displayString;

    private SpellSchool(String displayString)
    {
        this.displayString = displayString;
    }

    @Override
    public String toString()
    {
        return displayString;
    }
}
