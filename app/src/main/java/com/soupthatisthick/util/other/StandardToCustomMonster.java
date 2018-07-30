package com.soupthatisthick.util.other;

import android.app.Instrumentation;
import android.content.Context;
import android.test.mock.MockContext;

import com.soupthatisthick.encounterbuilder.dao.lookup.CustomMonsterDao;
import com.soupthatisthick.encounterbuilder.dao.lookup.StandardMonsterDao;
import com.soupthatisthick.encounterbuilder.dao.master.DndMaster;
import com.soupthatisthick.encounterbuilder.model.lookup.CustomMonster;
import com.soupthatisthick.encounterbuilder.model.lookup.StandardMonster;
import com.soupthatisthick.util.Logger;
import com.soupthatisthick.util.dao.WriteDao;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.IOException;
import java.util.List;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.fail;

public class StandardToCustomMonster {
    private Context context;
    private DndMaster dndMaster;
    private WriteDao<StandardMonster> standardMonsterDao;
    private WriteDao<CustomMonster> customMonsterDao;

    private Instrumentation instrumentation;

    @Before
    public void onSetup() {

        
        try {
            dndMaster = new DndMaster(context);
            standardMonsterDao = new StandardMonsterDao(dndMaster);
            customMonsterDao = new CustomMonsterDao(dndMaster);
        } catch (IOException e) {
            fail(e.getMessage());
        }
    }

    @Test
    public void transferMonsters() throws IOException {
        List<StandardMonster> standardMonsterList = standardMonsterDao.getAllRecords();
        for(StandardMonster standardMonster : standardMonsterList) {
            final CustomMonster customMonster = customMonsterDao.create();
            customMonster.updateFrom(standardMonster);
            final boolean isSaved = customMonsterDao.update(customMonster);

            if (isSaved) {
                Logger.info("SAVE SUCCESS: " + customMonster.getName());
            } else {
                Logger.warning("SAVE FAILURE: " + customMonster.getName());
            }
        }
    }
}
