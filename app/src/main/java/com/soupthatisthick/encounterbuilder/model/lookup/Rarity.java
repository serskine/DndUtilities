package com.soupthatisthick.encounterbuilder.model.lookup;

import com.soupthatisthick.encounterbuilder.model.DaoModel;

/**
 * Created by Owner on 2/26/2017.
 * Copyright of Stuart Marr Erskine, all rights reserved.
 */

public enum Rarity {
    UNCOMMON("uncommon"),
    RARE("rare"),
    VERY_RARE("very rare"),
    LEGENDARY("legendary"),
    UNIQUE("unique");

    public final String displayString;

    private Rarity(String displayString)
    {
        this.displayString = displayString;
    }

    @Override
    public String toString()
    {
        return displayString;
    }
}
