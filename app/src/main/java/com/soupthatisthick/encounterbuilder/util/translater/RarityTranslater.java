package com.soupthatisthick.encounterbuilder.util.translater;

import android.support.annotation.Nullable;

import com.soupthatisthick.encounterbuilder.model.lookup.Faction;
import com.soupthatisthick.encounterbuilder.model.lookup.Rarity;

/**
 * Created by Owner on 2/26/2017.
 * Copyright of Stuart Marr Erskine, all rights reserved.
 */

public class RarityTranslater {


    public static final RarityTranslater INSTANCE = new RarityTranslater();

    private RarityTranslater() {}

    /**
     * This will determine what faction the text represents
     * @param rarityText
     * @return {@link Faction}
     */
    public Rarity toRarity(@Nullable String rarityText)
    {
        if (rarityText==null)
        {
            return Rarity.UNIQUE;
        }

        rarityText = rarityText.toLowerCase().trim();
        for(Rarity faction : Rarity.values())
        {
            String pattern = faction.toString().toLowerCase().trim();
            if (rarityText.matches(pattern))
            {
                return faction;
            }
        }
        return Rarity.UNIQUE;
    }

    /**
     * This will take a rarity and convert it into text that can
     * be converted back into the same rarity
     * @param rarity
     * @return
     */
    public String toText(@Nullable Rarity rarity)
    {
        return (rarity==null) ? Rarity.UNIQUE.toString() : rarity.toString();
    }
}
