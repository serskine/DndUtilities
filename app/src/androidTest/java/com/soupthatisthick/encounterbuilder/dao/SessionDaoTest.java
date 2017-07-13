package com.soupthatisthick.encounterbuilder.dao;

import com.soupthatisthick.encounterbuilder.InstrumentationTest;
import com.soupthatisthick.encounterbuilder.dao.lookup.SessionDao;
import com.soupthatisthick.encounterbuilder.dao.master.LogsheetMaster;
import com.soupthatisthick.encounterbuilder.model.lookup.Session;
import com.soupthatisthick.util.Logger;

import org.junit.Test;

import java.util.Collection;
import java.util.List;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.fail;

/**
 * Created by Owner on 2/18/2017.
 * Copyright of Stuart Marr Erskine, all rights reserved.
 */

public class SessionDaoTest extends InstrumentationTest {

    private LogsheetMaster db;
    private SessionDao dao;

    @Test
    public void testReadDao()
    {
        dao.logContents();

        try {
            List<Session> list = dao.getAllRecords();
            logList(list);
            assertEquals("Number of sessions should match.", 7, list.size());
        } catch (Exception e) {
            fail(e.getMessage());
        }
    }

    @Test
    public void testCreateUpdateAndDeleteSessionsFor()
    {
        try {
            int count = dao.countRecords();
            Session newSession;
            Long characterId = new Long(999);

            int numNewSessions = 10;
            for(int i=0; i<numNewSessions; i++) {
                newSession = dao.create();
                newSession.setCharacterId(characterId);
                dao.update(newSession.getSessionId(), newSession);
            }

            int newCount = dao.countRecords();

            assertEquals("Number of records should have increased", count+numNewSessions, newCount);

            dao.deleteSessionsFor(characterId);

            int thirdCount = dao.countRecords();

            assertEquals("Number of records should revert to normal", count, thirdCount);



        } catch (Exception e) {
            fail(e.getMessage());
        }

    }

    @Override
    protected void onSetup() {
        try {
            db = new LogsheetMaster(context);
            db.logSchema();
            dao = new SessionDao(db);
        } catch (Exception e) {
            fail(e.getMessage());
        }

    }

    private static final void logList(Collection<Session> list)
    {
        int count = 0;
        for(Session session : list)
        {
            Logger.info("session[" + count + "] = " + session.toString(), 1);
        }
    }
}
