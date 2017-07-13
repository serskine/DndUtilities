package com.soupthatisthick.encounterbuilder.dao;

import com.soupthatisthick.encounterbuilder.InstrumentationTest;
import com.soupthatisthick.encounterbuilder.dao.lookup.TableDao;
import com.soupthatisthick.encounterbuilder.dao.master.LogsheetMaster;
import com.soupthatisthick.encounterbuilder.model.lookup.CustomMonster;
import com.soupthatisthick.util.dao.DaoMaster;
import com.soupthatisthick.util.differences.Differences;

import junit.framework.AssertionFailedError;

import org.junit.Test;

import java.io.IOException;
import java.util.List;

import static com.soupthatisthick.util.differences.Differences.logResults;
import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;
import static junit.framework.Assert.fail;

/**
 * Created by Owner on 2/18/2017.
 * Copyright of Stuart Marr Erskine, all rights reserved.
 */

public class ReadDaoTest extends InstrumentationTest {

    DaoMaster daoMaster;
    TableDao dao;

    @Override
    protected void onSetup() {
        try {
            daoMaster = new LogsheetMaster(context);
        } catch (Exception e) {
            fail(e.getMessage());
        }

        try {
            dao = new TableDao(daoMaster);
        } catch (IOException e) {
            fail(e.getMessage());
        }

        assertNotNull(dao);

    }

    @Test
    public void testLogContents()
    {
        dao.logContents();
    }

    @Test
    public void testListSearch()
    {
        String searchText = "autoindex";
        try {
            List<? extends Object> results = dao.getRecordsLike(searchText);
            logResults("like " + searchText, results);
        } catch (Exception e) {
            fail(e.getMessage());
        }
    }

    @Test
    public void testExclusionSearch()
    {
        String searchWord = "autoindex";

        try {
            List<? extends Object> allResults = dao.getAllRecords();
            List<? extends Object> includeResults = dao.getRecordsLike(searchWord);
            List<? extends Object> excludeResults = dao.getRecordsLike("-" + searchWord);

            Differences diff1 = new Differences(includeResults, excludeResults);
            diff1.log();

            try {
                assertEquals(0, diff1.expectedAndObserved.size());
                assertEquals(includeResults.size(), diff1.expectedButNotObserved.size());
                assertEquals(excludeResults.size(), diff1.observedButNotExpected.size());
            } catch (AssertionFailedError e) {
                fail(e.getMessage());
            }

            assertEquals(allResults.size(), includeResults.size() + excludeResults.size());

        } catch (Exception e) {
            fail(e.getMessage());
        }
    }

}
