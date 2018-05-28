package com.soupthatisthick.encounterbuilder.dao.master;

import com.soupthatisthick.encounterbuilder.InstrumentationTest;
import com.soupthatisthick.encounterbuilder.util.Text;
import com.soupthatisthick.util.Logger;

import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertTrue;
import static junit.framework.Assert.fail;

/**
 * Created by Owner on 5/7/2017.
 * Copyright of Stuart Marr Erskine, all rights reserved.
 */

public class DndMasterTest extends InstrumentationTest {

    private DndMaster master;
    @Override
    protected void onSetup() {
        try {
            master = new DndMaster(context);
        } catch (Exception e) {
            fail(e.getMessage());
        }
    }

    @Override
    protected void onTeardown() {
        try {
            master.delete();
        } catch (Exception e) {
            // Do nothing
        }
        assertFalse(master.exists());
    }


    @Test
    public void testCreateDelete()
    {
        assertTrue(master.exists());
        boolean deleteResult = master.delete();

        Logger.info("deleteResult = " + deleteResult);

        assertFalse(master.exists());
    }

    @Test
    public void testTables() {

        master.logSchema();

        Set<String> observedTables = master.getTables();
        Set<String> extraTables = new HashSet<>(observedTables);
        String[] expectedTables = new String[] {
            "ARMOR", "BACKGROUND", "CHARACTER_ADVANCEMENT", "CONDITIONS", "CONTAINERS", "CUSTOM_MONSTERS",
            "DAO_SEARCHABLE", "EDITABLE_SPELLS", "ENTITY", "EQUIPMENT", "FEATS", "GODS", "HEIGHT_WEIGHT",
            "LANGUAGES", "LIFESTYLE", "LISTS", "LIST_ITEMS", "MAGIC_ITEMS", "MC", "MONSTERS", "MOUNTS", "MS",
            "MULTICLASSING", "NOTES", "ROLL_TABLE", "ROLL_TABLE_ENTRY", "SERVICE", "SPELL_SLOTS_MULTICLASS",
            "STANDARD_MONSTERS", "TMP", "TRADE_GOODS", "WATERBORNE_VECHICLES", "WEAPONS",
            "android_metadata", "sqlite_sequence", "sqlite_master"
        };

        Set<String> missingTables = new HashSet<>();
        for(String expectedTable : expectedTables) {

            if (!observedTables.contains(expectedTable)) {
                missingTables.add(expectedTable);
            }
            extraTables.remove(expectedTable);
        }

        if (!missingTables.isEmpty() || !extraTables.isEmpty()) {
            StringBuilder message = new StringBuilder();

            message.append("\n");
            message.append("The schema was not as expected!\n");

            for (String missingTable : missingTables) {
                message.append("Missing expected table " + missingTable + ".\n");
            }

            for (String extraTable : extraTables) {
                message.append("Extra table " + extraTable + " found.\n");
            }

            fail(message.toString());
        }
    }

}
