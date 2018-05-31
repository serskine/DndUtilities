package com.soupthatisthick.encounterbuilder.dao;

import android.annotation.SuppressLint;

import com.soupthatisthick.encounterbuilder.InstrumentationTest;
import com.soupthatisthick.encounterbuilder.dao.lookup.CustomMonsterDao;
import com.soupthatisthick.encounterbuilder.dao.lookup.StandardMonsterDao;
import com.soupthatisthick.encounterbuilder.dao.master.DndMaster;
import com.soupthatisthick.encounterbuilder.model.lookup.CustomMonster;
import com.soupthatisthick.encounterbuilder.model.lookup.StandardMonster;
import com.soupthatisthick.util.Logger;

import org.junit.Test;

import java.util.List;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.fail;

/**
 * Created by Owner on 7/12/2017.
 * Copyright of Stuart Marr Erskine, all rights reserved.
 */

public class StandardMonsterDaoTest extends InstrumentationTest {


    private DndMaster db;
    private StandardMonsterDao dao;

    @Override
    protected void onSetup() {
        try {
            db = new DndMaster(context);
            db.logSchema();
            dao = new StandardMonsterDao(db);
        } catch (Exception e) {
            fail(e.getMessage());
        }
    }

    @Override
    protected void onTeardown() {

    }

    @Test
    public void testReadDao()
    {
        dao.logContents();
        StandardMonster monster = dao.load(1L);
        Logger.info(monster.toString());
        logMonster(monster);
    }

    @SuppressLint("DefaultLocale")
    private static void logMonster(StandardMonster monster)
    {
        Logger.title(monster.getName());

        Logger.info("Abilities");
        Logger.info(String.format(" - Strength:     %d (%d)" , monster.getStrength(), monster.getStrengthMod()));
        Logger.info(String.format(" - Dexterity:    %d (%d)" , monster.getDexterity(), monster.getDexterityMod()));
        Logger.info(String.format(" - Constitution: %d (%d)" , monster.getConstitution(), monster.getConstitutionMod()));
        Logger.info(String.format(" - Intelligence: %d (%d)" , monster.getIntelligence(), monster.getIntelligenceMod()));
        Logger.info(String.format(" - Wisdom:       %d (%d)" , monster.getWisdom(), monster.getWisdomMod()));
        Logger.info(String.format(" - Charisma:     %d (%d)" , monster.getCharisma(), monster.getCharismaMod()));

        Logger.info("Saves");
        Logger.info(String.format(" - Str Save: %d", monster.getStrength(), monster.getStrengthSave()));
        Logger.info(String.format(" - Dex Save: %d", monster.getDexterity(), monster.getDexteritySave()));
        Logger.info(String.format(" - Con Save: %d", monster.getConstitution(), monster.getConstitutionSave()));
        Logger.info(String.format(" - Int Save: %d", monster.getIntelligence(), monster.getIntelligenceSave()));
        Logger.info(String.format(" - Wis Save: %d", monster.getWisdom(), monster.getWisdomSave()));
        Logger.info(String.format(" - Cha Save: %d", monster.getCharisma(), monster.getCharismaSave()));

        Logger.info("Skills");
        Logger.info(String.format(" - Acrobatics:    %d", monster.getAcrobatics()));
        Logger.info(String.format(" - Arcana:        %d", monster.getArcana()));
        Logger.info(String.format(" - Athletics:     %d", monster.getAthletics()));
        Logger.info(String.format(" - Deception:     %d", monster.getDeception()));
        Logger.info(String.format(" - History:       %d", monster.getHistory()));
        Logger.info(String.format(" - Insight:       %d", monster.getInsight()));
        Logger.info(String.format(" - Intimidation:  %d", monster.getIntimidation()));
        Logger.info(String.format(" - Investigation: %d", monster.getInvestigation()));
        Logger.info(String.format(" - Medicine:      %d", monster.getMedicine()));
        Logger.info(String.format(" - Nature:        %d", monster.getNature()));
        Logger.info(String.format(" - Perception:    %d", monster.getPerception()));
        Logger.info(String.format(" - Performance:   %d", monster.getPerformance()));
        Logger.info(String.format(" - Persuasion:    %d", monster.getPersuasion()));
        Logger.info(String.format(" - Religion:      %d", monster.getReligion()));
        Logger.info(String.format(" - Stealth:       %d", monster.getStealth()));
        Logger.info(String.format(" - Survival:      %d", monster.getSurvival()));

    }


}
