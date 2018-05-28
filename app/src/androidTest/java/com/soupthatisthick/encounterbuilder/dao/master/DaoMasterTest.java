package com.soupthatisthick.encounterbuilder.dao.master;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.soupthatisthick.encounterbuilder.InstrumentationTest;
import com.soupthatisthick.encounterbuilder.util.DatabaseHelper2;
import com.soupthatisthick.util.Logger;
import com.soupthatisthick.util.dao.DaoMaster;
import com.soupthatisthick.util.dao.MockDaoMaster;

import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import static com.soupthatisthick.encounterbuilder.util.DatabaseHelper2.Location.ASSETS_DIRECTORY;
import static com.soupthatisthick.encounterbuilder.util.DatabaseHelper2.Location.EXTERNAL_FILES_DIR;
import static com.soupthatisthick.encounterbuilder.util.DatabaseHelper2.Location.INTERNAL_FILES_DIR;
import static com.soupthatisthick.encounterbuilder.util.DatabaseHelper2.Location.WORKING_DATABASE_DIR;
import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertNotNull;
import static junit.framework.Assert.assertTrue;
import static junit.framework.Assert.fail;

public class DaoMasterTest extends InstrumentationTest {

    private static final String DIRECTORY = "./";
    private static final String NAME = "testdb.db";
    private MockDaoMaster master;

    @Override
    protected void onSetup() {
    }

    @Override
    protected void onTeardown() {
    }


    private void createMaster(int targetVersion) throws IOException {
        master = new MockDaoMaster(context, DIRECTORY, NAME, targetVersion);
        assertNotNull(master);
    }

    private boolean doesDatabaseExist() {
        return doesFileExist(context.getDatabasePath(NAME).getAbsolutePath());
    }

    private boolean doesFileExist(String path) {
        File theFile = new File(path);
        return theFile.exists();
    }

    private void deleteMasterQuietly() {
        deleteFileQuietly(context.getDatabasePath(NAME).getAbsolutePath());
        master = null;
    }

    private void deleteFileQuietly(String path) {
        File theFile = new File(path);
        if (theFile.exists()) {
            theFile.delete();
        }
    }

    private void assertMasterStatus(
            int expectedCreateCalls,
            int expectedUpgradeCalls,
            int expectedDowngradeCalls
    ) {

        StringBuilder sb = new StringBuilder();

        sb.append(" \n");
        sb.append("Create calls    : " + master.getCreateCalls());
        sb.append("Upgrade calls   : " + master.getUpgradeCalls());
        sb.append("Downgrade calls : " + master.getDowngradeCalls());

        assertEquals(master.description() + "\nCreate calls: ", expectedCreateCalls, master.getCreateCalls());
        assertEquals(master.description() + "\nUpgrade calls: ", expectedUpgradeCalls, master.getUpgradeCalls());
        assertEquals(master.description() + "\nDowngrade calls: ", expectedDowngradeCalls, master.getDowngradeCalls());
    }

    private void assertMasterDatabaseFilesExist(boolean expected) {
        for(DatabaseHelper2.Location location : DatabaseHelper2.Location.values()) {
            if (location != ASSETS_DIRECTORY) {
                assertEquals(location.toString(), expected, doesFileExist(master.getLocationPath(location, expected)));
            }
        }
    }



    @Test
    public void testCreation() throws IOException {

        deleteMasterQuietly();
        assertEquals("Database " + NAME + " should not exist: ", false, doesDatabaseExist());

        createMaster(2);
        assertEquals("Database " + NAME + " should exist: ", true, doesDatabaseExist());

        Logger.debug(master.description());
        assertMasterStatus(1, 0, 0);
    }
    
    @Test
    public void testUpgradeDowngrade() throws IOException {

        deleteMasterQuietly();

        createMaster(2);
        Logger.debug(master.description());
        assertMasterStatus(1, 0, 0);

        createMaster(3);
        assertMasterStatus(0, 1, 0);

        createMaster(2);
        assertMasterStatus(0, 0, 1);

        createMaster(1);
        assertMasterStatus(0, 0, 1);

        createMaster(5);
        assertMasterStatus(0, 4, 0);

        deleteMasterQuietly();

        createMaster(5);
        assertMasterStatus(1, 0, 0);
    }

    @Test
    public void testCopyFromAssetsLocations() throws IOException {

        deleteMasterQuietly();

        createMaster(1);

        master.copy(ASSETS_DIRECTORY, INTERNAL_FILES_DIR, false);
        master.copy(ASSETS_DIRECTORY, INTERNAL_FILES_DIR, true);
        master.copy(ASSETS_DIRECTORY, EXTERNAL_FILES_DIR, false);
        master.copy(ASSETS_DIRECTORY, EXTERNAL_FILES_DIR, true);
        master.copy(ASSETS_DIRECTORY, WORKING_DATABASE_DIR, true);
        master.copy(ASSETS_DIRECTORY, WORKING_DATABASE_DIR, false);

        assertMasterDatabaseFilesExist(true);

        master.cleanAllQuietly();
        assertMasterDatabaseFilesExist(false);
    }



}
