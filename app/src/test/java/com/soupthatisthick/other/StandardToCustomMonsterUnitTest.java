package com.soupthatisthick.other;

import android.content.Context;
import android.test.mock.MockContext;

import com.soupthatisthick.encounterbuilder.ExampleUnitTest;
import com.soupthatisthick.encounterbuilder.dao.lookup.CustomMonsterDao;
import com.soupthatisthick.encounterbuilder.dao.lookup.StandardMonsterDao;
import com.soupthatisthick.encounterbuilder.dao.master.DndMaster;
import com.soupthatisthick.encounterbuilder.model.lookup.CustomMonster;
import com.soupthatisthick.encounterbuilder.model.lookup.StandardMonster;
import com.soupthatisthick.util.Logger;
import com.soupthatisthick.util.dao.WriteDao;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.List;

public class StandardToCustomMonsterUnitTest {

    private static final String ASSET_DIR = "C:\\Users\\stuart.erskine\\IdeaProjects\\DndUtilities\\app\\src\\main\\assets";

    private Context context;
    private DndMaster dndMaster;
    private StandardMonsterDao standardMonsterDao;
    private CustomMonsterDao customMonsterDao;

    @Before
    public void onSetup() throws IOException {
        context = new com.soupthatisthick.util.MockContext() {
        };
        dndMaster = new DndMaster(
            context,
            ASSET_DIR,
            "dnd.db",
            15
        );
        standardMonsterDao = new StandardMonsterDao(dndMaster);
        customMonsterDao = new CustomMonsterDao(dndMaster);
    }

    @After
    public void onTearDown() {

    }

    @Test
    public void addStandardMonstersToCustomMonsters() throws IOException {
        List<StandardMonster> standardMonsterList = standardMonsterDao.getAllRecords();
        for(StandardMonster standardMonster : standardMonsterList) {
            CustomMonster customMonster = customMonsterDao.create();
//
//            customMonster.updateFrom(standardMonster);
//            final boolean isSaved = customMonsterDao.update(customMonster);
//
//            if (isSaved) {
//                Logger.info("Save SUCCESS: " + customMonster.getName());
//            } else {
//                Logger.warning("Save FAILURE: " + customMonster.getName()));
//            }
        }
    }
}
