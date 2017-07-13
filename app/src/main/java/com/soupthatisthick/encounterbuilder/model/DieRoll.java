package com.soupthatisthick.encounterbuilder.model;

/**
 * Created by Owner on 5/11/2017.
 * Copyright of Stuart Marr Erskine, all rights reserved.
 */

public class DieRoll {
    public final DieSize size;
    public final int roll;

    public DieRoll(DieSize size, int roll)
    {
        this.roll = roll;   // We are allowed to display any number we want on the die
        this.size = size;   // This the background for the die
    }
}
