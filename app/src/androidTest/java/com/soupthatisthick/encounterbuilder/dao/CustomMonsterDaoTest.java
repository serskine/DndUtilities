package com.soupthatisthick.encounterbuilder.dao;

import com.soupthatisthick.util.differences.Differences;
import com.soupthatisthick.encounterbuilder.InstrumentationTest;
import com.soupthatisthick.encounterbuilder.dao.lookup.CustomMonsterDao;
import com.soupthatisthick.encounterbuilder.dao.master.DndMaster;
import com.soupthatisthick.encounterbuilder.model.lookup.CustomMonster;
import com.soupthatisthick.util.Logger;

import junit.framework.AssertionFailedError;

import org.junit.Test;

import java.util.Collection;
import java.util.List;

import static com.soupthatisthick.util.differences.Differences.logResults;
import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.fail;

/**
 * Created by Owner on 2/19/2017.
 * Copyright of Stuart Marr Erskine, all rights reserved.
 */

public class CustomMonsterDaoTest extends InstrumentationTest {

    private DndMaster db;
    private CustomMonsterDao dao;

    public static final int NUM_RECORDS = 138;

    @Test
    public void testReadDao()
    {
        dao.logContents();

        try {
            List<CustomMonster> list = dao.getAllRecords();
            logList(list);
            assertEquals("Number of sessions should match.", NUM_RECORDS, list.size());
        } catch (Exception e) {
            fail(e.getMessage());
        }
    }




    @Override
    protected void onSetup() {
        try {
            db = new DndMaster(context);
            db.logSchema();
            dao = new CustomMonsterDao(db);
        } catch (Exception e) {
            fail(e.getMessage());
        }

    }

    @Override
    protected void onTeardown() {

    }

    private static final void logList(Collection<CustomMonster> list)
    {
        int count = 0;
        for(CustomMonster customMonster : list)
        {
            Logger.info("session[" + count + "] = " + customMonster.toString(), 1);
        }
    }
}
