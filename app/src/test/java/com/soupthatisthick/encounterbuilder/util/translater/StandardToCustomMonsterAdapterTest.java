package com.soupthatisthick.encounterbuilder.util.translater;

import com.soupthatisthick.encounterbuilder.model.lookup.CustomMonster;
import com.soupthatisthick.encounterbuilder.model.lookup.StandardMonster;
import com.soupthatisthick.util.Logger;
import com.soupthatisthick.util.json.JsonUtil;
import com.soupthatisthick.util.podam.PodamUtil;

import org.junit.Test;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;
import static junit.framework.Assert.assertTrue;

public class StandardToCustomMonsterAdapterTest {
    @Test
    public void apply() {
        StandardMonster standardMonster = randomStandardMonster();
        assertNotNull(standardMonster);

        Logger.info("\n___ Standard Monster ___\n" + JsonUtil.toJson(standardMonster, true, true));

        CustomMonster customMonster = new CustomMonster(standardMonster);
        Logger.info("\n___ Custom Monster ___\n" + JsonUtil.toJson(customMonster, true, true));
        assertNotNull(customMonster);
    }
    
    @Test
    public void apply_saves() {
        StandardMonster standardMonster = randomStandardMonster();
        
        // We should only see the strength, dexterity and constitution mods
        standardMonster.setStrengthSave(standardMonster.getStrengthMod() + 2);
        standardMonster.setDexteritySave(standardMonster.getDexterityMod() + 3);
        standardMonster.setConstitutionSave(standardMonster.getConstitutionMod() + 4);
        standardMonster.setIntelligenceSave(standardMonster.getIntelligenceMod() + 0);
        standardMonster.setWisdomSave(standardMonster.getWisdomMod() + 0);
        standardMonster.setCharismaSave(standardMonster.getCharismaMod() + 0);
        
        assertNotNull(standardMonster);

        Logger.info("\n___ Standard Monster ___\n" + JsonUtil.toJson(standardMonster, true, true));

        CustomMonster customMonster = new CustomMonster(standardMonster);
        Logger.info("\n___ Custom Monster ___\n" + JsonUtil.toJson(customMonster, true, true));
        assertNotNull(customMonster);
        
        final String saves = customMonster.getSaves();
        
        assertEquals("Contains Strength :", true, saves.contains("Strength"));
        assertEquals("Contains Dexterity :", true, saves.contains("Dexterity"));
        assertEquals("Contains Constitution :", true, saves.contains("Constitution"));
        assertEquals("Contains Intelligence :", false, saves.contains("Intelligence"));
        assertEquals("Contains Wisdom :", false, saves.contains("Wisdom"));
        assertEquals("Contains Charisma :", false, saves.contains("Charisma"));
        
    }

    StandardMonster randomStandardMonster() {
        StandardMonster monster = PodamUtil.manufacture(StandardMonster.class);
        return monster;
    }

}
