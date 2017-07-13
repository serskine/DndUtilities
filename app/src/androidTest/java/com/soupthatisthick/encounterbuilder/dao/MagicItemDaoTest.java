package com.soupthatisthick.encounterbuilder.dao;

import com.soupthatisthick.encounterbuilder.InstrumentationTest;
import com.soupthatisthick.encounterbuilder.dao.lookup.MagicItemDao;
import com.soupthatisthick.encounterbuilder.dao.master.DndMaster;
import com.soupthatisthick.encounterbuilder.model.lookup.MagicItem;

import junit.framework.Assert;

import org.junit.Test;

import java.util.List;

import static junit.framework.Assert.fail;

/**
 * Created by Owner on 2/18/2017.
 * Copyright of Stuart Marr Erskine, all rights reserved.
 */

public class MagicItemDaoTest extends InstrumentationTest {


    private DndMaster db;
    private MagicItemDao dao;

    private static final int NUM_RECORDS = 389;


    @Test
    public void testReadDao()
    {
        dao.logContents();

        try {
            List<MagicItem> list = dao.getAllRecords();
            Assert.assertEquals("Number of magic items should match.", NUM_RECORDS, list.size());
        } catch (Exception e) {
            fail(e.getMessage());
        }
    }


    @Override
    protected void onSetup() {
        try {
            db = new DndMaster(context);
            dao = new MagicItemDao(db);
        } catch (Exception e) {
            fail(e.getMessage());
        }
    }
}
