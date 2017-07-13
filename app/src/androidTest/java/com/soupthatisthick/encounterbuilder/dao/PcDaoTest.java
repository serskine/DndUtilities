package com.soupthatisthick.encounterbuilder.dao;

import com.soupthatisthick.encounterbuilder.InstrumentationTest;
import com.soupthatisthick.encounterbuilder.dao.lookup.PcDao;
import com.soupthatisthick.encounterbuilder.dao.master.LogsheetMaster;
import com.soupthatisthick.encounterbuilder.model.lookup.Pc;
import com.soupthatisthick.util.Logger;

import junit.framework.Assert;

import org.junit.Test;

import java.util.Collection;
import java.util.List;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;
import static junit.framework.Assert.fail;

/**
 * Created by Owner on 2/17/2017.
 * Copyright of Stuart Marr Erskine, all rights reserved.
 */
public class PcDaoTest extends InstrumentationTest {

    private LogsheetMaster db;
    private PcDao dao;

    public static final int NUM_RECORDS = 4;

    @Test
    public void testReadDao()
    {
        dao.logContents();

        try {
            List<Pc> pcList = dao.getAllRecords();
            logList(pcList);
            Assert.assertEquals("Number of pc's should match.", NUM_RECORDS, pcList.size());
        } catch (Exception e) {
            fail(e.getMessage());
        }
    }

    @Override
    protected void onSetup() {
        try {
            db = new LogsheetMaster(context);
            dao = new PcDao(db);
        } catch (Exception e) {
            fail(e.getMessage());
        }

    }

    private static final void logList(Collection<Pc> pcList)
    {
        int count = 0;
        for(Pc pc : pcList)
        {
            Logger.info("pc[" + count + "] = " + pc.toString(), 1);
        }
    }
}
