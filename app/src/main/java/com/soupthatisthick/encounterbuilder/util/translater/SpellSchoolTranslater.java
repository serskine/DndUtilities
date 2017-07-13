package com.soupthatisthick.encounterbuilder.util.translater;

import com.soupthatisthick.encounterbuilder.model.lookup.SpellSchool;

/**
 * Created by Owner on 2/28/2017.
 * Copyright of Stuart Marr Erskine, all rights reserved.
 */

public class SpellSchoolTranslater {
    public static final SpellSchoolTranslater INSTANCE = new SpellSchoolTranslater();

    private SpellSchoolTranslater() {}

    public SpellSchool toSpellSchool(String text)
    {
        if (text==null)
        {
            return SpellSchool.OTHER;
        }
        String lookThrough = text.toLowerCase();
        for(SpellSchool school : SpellSchool.values())
        {
            String toFind = school.displayString.toLowerCase();
            if (lookThrough.contains(toFind)) {
                return school;
            }
        }
        return SpellSchool.OTHER;
    }

    public String toText(SpellSchool spellSchool)
    {
        return spellSchool.displayString;
    }
}
