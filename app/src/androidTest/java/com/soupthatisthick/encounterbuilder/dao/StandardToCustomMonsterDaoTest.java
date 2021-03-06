package com.soupthatisthick.encounterbuilder.dao;

import android.text.style.TabStopSpan;

import com.soupthatisthick.encounterbuilder.DndUtilApp;
import com.soupthatisthick.encounterbuilder.InstrumentationTest;
import com.soupthatisthick.encounterbuilder.dao.helper.CompendiumResource;
import com.soupthatisthick.encounterbuilder.dao.lookup.CustomMonsterDao;
import com.soupthatisthick.encounterbuilder.dao.lookup.StandardMonsterDao;
import com.soupthatisthick.encounterbuilder.dao.master.DndMaster;
import com.soupthatisthick.encounterbuilder.model.lookup.CustomMonster;
import com.soupthatisthick.encounterbuilder.model.lookup.StandardMonster;
import com.soupthatisthick.encounterbuilder.util.DatabaseHelper2;
import com.soupthatisthick.encounterbuilder.util.sort.Category;
import com.soupthatisthick.util.Logger;
import com.soupthatisthick.util.dao.Dao;
import com.soupthatisthick.util.dao.DaoMaster;
import com.soupthatisthick.util.dao.WriteDao;
import com.soupthatisthick.util.json.JsonUtil;

import org.junit.Test;

import java.io.IOException;
import java.util.List;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.fail;

public class StandardToCustomMonsterDaoTest extends InstrumentationTest {


    private CompendiumResource compendiumResource;
    private DndMaster dndMaster;
    private WriteDao<StandardMonster> standardMonsterDao;
    private WriteDao<CustomMonster> customMonsterDao;


    @Override
    protected void onSetup() {
        try {
            compendiumResource = ((DndUtilApp) context.getApplicationContext()).getCompendiumResource();
            dndMaster = compendiumResource.getDndMaster();
            standardMonsterDao = loadDao(StandardMonster.class, 1000, 100);
            customMonsterDao = loadDao(CustomMonster.class, 1000, 100);
        } catch (Exception e) {
            fail(e.getMessage());
        }
    }

    @Override
    protected void onTeardown() {
        try {
            dndMaster.copy(DatabaseHelper2.Location.WORKING_DATABASE_DIR, DatabaseHelper2.Location.EXTERNAL_FILES_DIR, true);
        } catch (Exception e) {
            Logger.error(e.getMessage(), e);
        }
    }

    @Test
    public void doNothing() {

    }

    @Test
    public void convertToCustomMonsters() throws Exception {
        List<StandardMonster> standardMonsterList = standardMonsterDao.getAllRecords();
        for(StandardMonster standardMonster : standardMonsterList) {
            logStandardMonster(standardMonster);

            CustomMonster customMonster = new CustomMonster(standardMonster);
            logCustomMonster(customMonster);

        }
    }

    @Test
    public void convertToCustomMonsters_Griffon() throws Exception {
        final Long griffonId = 157L;
        StandardMonster griffonStandard = standardMonsterDao.load(griffonId);
        logStandardMonster(griffonStandard);

        CustomMonster griffonCustom = new CustomMonster(griffonStandard);
        logCustomMonster(griffonCustom);

    }

    @Test
    public void addStandardMonstersToCustomMonsters() throws Exception {
        List<StandardMonster> standardMonsterList = standardMonsterDao.getAllRecords();
        for(StandardMonster standardMonster : standardMonsterList) {
            CustomMonster customMonster = customMonsterDao.create();
            customMonster.updateFrom(standardMonster);
            boolean isUpdated = customMonsterDao.update(customMonster);
            assertEquals("Custom monster saved: ", true, customMonsterDao.update(customMonster));
        }


    }

    private void logStandardMonster(StandardMonster standardMonster) {
        Logger.info("\n___ standard monster ___\n" + JsonUtil.toJson(standardMonster, true, true));
    }

    private void logCustomMonster(CustomMonster customMonster) {
        Logger.info("\n___ custom monster (" + customMonster.getName() + ") ___\n" + JsonUtil.toJson(customMonster, true, true));
    }

    private <T> WriteDao<T> loadDao(Class<T> tClass, long sleepInterval, int maxErrors) {
        WriteDao<T> dao = null;
        int numErrors = 0;
        while(dao==null) {
            try {
                Thread.sleep(sleepInterval);
                Logger.info("Loading dao for " + tClass.getCanonicalName() + "....");
                dao = compendiumResource.getDaoForClass(tClass);
            } catch (Exception e) {
                numErrors++;
                Logger.warning("Error[" + numErrors + "]: " + e.getMessage());
                if (maxErrors>=0 && numErrors >= maxErrors) {
                    fail("Max number or errors reached!. Final error: " + e.getMessage());
                }
            }
        }
        return dao;
    }
}
