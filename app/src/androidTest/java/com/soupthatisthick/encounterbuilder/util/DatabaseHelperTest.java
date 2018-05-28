package com.soupthatisthick.encounterbuilder.util;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import com.soupthatisthick.encounterbuilder.dao.master.DndMaster;
import com.soupthatisthick.util.Logger;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.IOException;

import static junit.framework.Assert.assertNotNull;
import static org.junit.Assert.assertEquals;

/**
 * Created by Owner on 2/18/2017.
 * Copyright of Stuart Marr Erskine, all rights reserved.
 */
@RunWith(AndroidJUnit4.class)
public class DatabaseHelperTest {

    private Context appContext;
    DatabaseHelper2 dbHelper;

    @Before
    public void setUp()
    {
        appContext = InstrumentationRegistry.getTargetContext();
        dbHelper = new DatabaseHelper2(
                appContext,
                "entity.db",
                null,
                1
        ) {
            /**
             * This will upgrade the database from the current version to the one version after it
             *
             * @param db
             * @param currentVersion
             * @throws Exception
             */
            @Override
            protected void upgrade(SQLiteDatabase db, int currentVersion) throws Exception {
                // Do nothing
            }

            /**
             * This will downgrade the database from the current version to one version later
             *
             * @param db
             * @param currentVersion
             * @throws Exception
             */
            @Override
            protected void downgrade(SQLiteDatabase db, int currentVersion) throws Exception {
                // Do nothing
            }
        };
        assertNotNull(dbHelper);

        Logger.info("name: " + dbHelper.getWritableDatabase().getPath());
        Logger.info("path: " + dbHelper.getWritableDatabase().getVersion());

        dbHelper.cleanAllQuietly();
    }

    @Test
    public void testSetup()
    {
        assertEquals("com.soupthatisthick.encounterbuilder", appContext.getPackageName());

    }

    @Test
    public void testDatabaseHelper2() throws IOException {

        dbHelper.updateDatabase();

    }
}
