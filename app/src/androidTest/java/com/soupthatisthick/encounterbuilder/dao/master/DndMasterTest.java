package com.soupthatisthick.encounterbuilder.dao.master;

import com.soupthatisthick.encounterbuilder.InstrumentationTest;
import com.soupthatisthick.util.Logger;

import org.junit.Test;

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


    @Test
    public void testCreateDelete()
    {
        assertTrue(master.exists());
        boolean deleteResult = master.delete();

        Logger.info("deleteResult = " + deleteResult);

        assertFalse(master.exists());
    }

}
