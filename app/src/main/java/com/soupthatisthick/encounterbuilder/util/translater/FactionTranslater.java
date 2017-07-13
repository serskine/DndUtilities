package com.soupthatisthick.encounterbuilder.util.translater;

import android.support.annotation.Nullable;

import com.soupthatisthick.encounterbuilder.model.lookup.Faction;

/**
 * Created by Owner on 2/8/2017.
 * Copyright of Stuart Marr Erskine, all rights reserved.
 */

public class FactionTranslater {


    public static final FactionTranslater INSTANCE = new FactionTranslater();

    private FactionTranslater() {}

     /**
     * This will determine what faction the text represents
     * @param factionText
     * @return {@link Faction}
     */
    public Faction toFaction(@Nullable String factionText)
    {
        if (factionText==null)
        {
            return Faction.NONE;
        }

        factionText = factionText.toLowerCase().trim();
        for(Faction faction : Faction.values())
        {
            String pattern = faction.toString().toLowerCase().trim();
            if (factionText.matches(pattern))
            {
                return faction;
            }
        }
        return Faction.NONE;
    }

    /**
     * This will take a faction and convert it into text that can
     * be converted back into the same faction
     * @param faction
     * @return
     */
    public String toText(@Nullable Faction faction)
    {
        return (faction==null) ? Faction.NONE.toString() : faction.toString();
    }

}
