package com.soupthatisthick.encounterbuilder.dao;

import com.soupthatisthick.encounterbuilder.InstrumentationTest;
import com.soupthatisthick.encounterbuilder.dao.lookup.CustomMonsterDao;
import com.soupthatisthick.encounterbuilder.dao.lookup.EntityDao;
import com.soupthatisthick.encounterbuilder.dao.master.DndMaster;
import com.soupthatisthick.encounterbuilder.model.lookup.Entity;
import com.soupthatisthick.encounterbuilder.util.sort.Category;
import com.soupthatisthick.util.Logger;
import com.soupthatisthick.util.json.JsonUtil;

import org.junit.Test;

import java.io.IOException;
import java.util.List;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.fail;

public class EntityDaoTest extends InstrumentationTest {
    private DndMaster db;
    private EntityDao dao;

    @Override
    protected void onSetup() {
        try {
            db = new DndMaster(context);
            db.logSchema();
            dao = new EntityDao(db);
        } catch (Exception e) {
            fail(e.getMessage());
        }
    }

    @Override
    protected void onTeardown() {

    }

    @Test
    public void testEntityDao() throws IOException {
        List<Entity> entityList = dao.getAllRecords();
        Logger.debug(JsonUtil.toJson(entityList, true));

        assertEquals("Size: ", 1, entityList.size());

        Entity entity = entityList.get(0);
        Logger.debug(entity.json());

        assertEquals("child entity category: ", Category.ARMOR, entity.getChildCategory());
    }
}
