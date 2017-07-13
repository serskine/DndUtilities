package com.soupthatisthick.encounterbuilder.printing.model;

import android.support.annotation.NonNull;

import com.soupthatisthick.encounterbuilder.model.lookup.StandardMonster;
import com.soupthatisthick.encounterbuilder.util.Text;
import com.soupthatisthick.util.Logger;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

/**
 * Created by Owner on 4/27/2017.
 * Copyright of Stuart Marr Erskine, all rights reserved.
 */

public class EncounterPage {
    public static final int MONSTER_TYPES_PER_PAGE = 3;
    public final StandardMonster[] monsters = new StandardMonster[MONSTER_TYPES_PER_PAGE];
    public final int sheetNumber, xpValue;
    public final String name, difficulty, location, notes;
    private final Map<Integer, Set<String>> initMap = new HashMap<>();

    public Set<String> getMonstersAtInit(int initValue)
    {
        Set<String> monsters = initMap.get(initValue);
        if (monsters==null)
        {
            monsters = new TreeSet<>();
            initMap.put(initValue, monsters);
        }
        return monsters;
    }

    public EncounterPage(
            String name,
            String difficulty,
            String location,
            int sheetNumber,
            int xpValue,
            String notes,
            StandardMonster monster1,
            StandardMonster monster2,
            StandardMonster monster3
    )
    {
        this.name = name;
        this.difficulty = difficulty;
        this.location = location;
        this.sheetNumber = sheetNumber;
        this.xpValue = xpValue;
        this.notes = notes;


        this.monsters[0] = monster1;
        this.monsters[1] = monster2;
        this.monsters[2] = monster3;
    }

    @Override
    public String toString()
    {
        String output = name.toString();
        for(int i=0; i<monsters.length; i++)
        {
            output += "[" + Text.toString(monsters[i].toString()) + "]";
        }
        return output;
    }


    public static final void log(@NonNull EncounterPage page)
    {
        Logger.title(page.name);
        Logger.info(" EncounterMap Name      = " + page.name);
        Logger.info(" Difficulty          = " + page.difficulty);
        Logger.info(" Sheet #             = " + page.sheetNumber);
        Logger.info(" Calculated XP Value = " + page.xpValue);

        for(int i=0; i<page.monsters.length; i++) {
            StandardMonster monster = page.monsters[i];
            Logger.info(" - Session " + i + 1);
            if (monster != null) {
                Logger.info("   - " + monster.getName() + "(" + monster.getId() + ")");
                Logger.info("   - " + monster.getType());
            } else {
                Logger.info("   - EMPTY");
            }
        }
    }


}
