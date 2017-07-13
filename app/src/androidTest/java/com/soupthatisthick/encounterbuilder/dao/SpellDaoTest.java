package com.soupthatisthick.encounterbuilder.dao;

import com.soupthatisthick.encounterbuilder.InstrumentationTest;
import com.soupthatisthick.encounterbuilder.dao.lookup.SpellDao;
import com.soupthatisthick.encounterbuilder.dao.master.DndMaster;
import com.soupthatisthick.encounterbuilder.model.lookup.Spell;

import junit.framework.Assert;

import org.junit.Test;

import java.util.List;

import static junit.framework.Assert.fail;
import static org.junit.Assert.assertEquals;

/**
 * Created by Owner on 1/29/2017.
 * Copyright of Stuart Marr Erskine, all rights reserved.
 */

public class SpellDaoTest extends InstrumentationTest {

    private DndMaster db;
    private SpellDao dao;

    @Test
    public void testReadDao()
    {
        dao.logContents();

        try {
            List<Spell> spells = dao.getAllRecords();
            Assert.assertEquals("Number of spells should match.", 408, spells.size());
        } catch (Exception e) {
            fail(e.getMessage());
        }
    }

    @Override
    protected void onSetup() {
        try {
            db = new DndMaster(context);
            dao = new SpellDao(db);
        } catch (Exception e) {
            fail(e.getMessage());
        }
    }
}
