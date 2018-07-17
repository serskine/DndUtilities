package com.soupthatisthick.encounterbuilder.dao;

import com.soupthatisthick.encounterbuilder.DndUtilApp;
import com.soupthatisthick.encounterbuilder.InstrumentationTest;
import com.soupthatisthick.encounterbuilder.dao.helper.CompendiumResource;
import com.soupthatisthick.encounterbuilder.dao.lookup.CustomMonsterDao;
import com.soupthatisthick.encounterbuilder.dao.lookup.StandardMonsterDao;
import com.soupthatisthick.encounterbuilder.dao.master.DndMaster;
import com.soupthatisthick.encounterbuilder.model.lookup.CustomMonster;
import com.soupthatisthick.encounterbuilder.model.lookup.StandardMonster;
import com.soupthatisthick.encounterbuilder.util.sort.Category;
import com.soupthatisthick.util.Logger;
import com.soupthatisthick.util.dao.DaoMaster;
import com.soupthatisthick.util.json.JsonUtil;

import org.junit.Test;

import java.io.IOException;
import java.util.List;

import static junit.framework.Assert.fail;

public class StandardToCustomMonsterDaoTest extends InstrumentationTest {

    CompendiumResource compendiumResource;

    @Override
    protected void onSetup() {
        try {
            compendiumResource = ((DndUtilApp) context.getApplicationContext()).getCompendiumResource();
        } catch (Exception e) {
            fail(e.getMessage());
        }
    }

    @Override
    protected void onTeardown() {

    }

    @Test
    public void doNothing() {

    }

    @Test
    public void convertToCustomMonsters() throws Exception {
        List<StandardMonster> standardMonsterList = (List<StandardMonster>) compendiumResource.getDaoForCategory(Category.STANDARD_MONSTER).getAllRecords();
        for(StandardMonster standardMonster : standardMonsterList) {
            CustomMonster customMonster = new CustomMonster(standardMonster);

            Logger.info("___ custom monster (" + customMonster.getName() + ") ___\n" + JsonUtil.toJson(customMonster, true, true));
        }
    }
}
