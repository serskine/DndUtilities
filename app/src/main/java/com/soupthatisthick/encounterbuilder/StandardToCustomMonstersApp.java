package com.soupthatisthick.encounterbuilder;

import android.support.annotation.CallSuper;
import android.support.multidex.MultiDexApplication;

import com.soupthatisthick.encounterbuilder.dao.helper.CompendiumResource;
import com.soupthatisthick.encounterbuilder.dao.lookup.StandardMonsterDao;
import com.soupthatisthick.encounterbuilder.model.lookup.CustomMonster;
import com.soupthatisthick.encounterbuilder.model.lookup.StandardMonster;
import com.soupthatisthick.encounterbuilder.util.sort.Category;
import com.soupthatisthick.util.Logger;
import com.soupthatisthick.util.json.JsonUtil;

import java.util.List;

public class StandardToCustomMonstersApp extends MultiDexApplication {

    private static StandardToCustomMonstersApp INSTANCE;


    /**
     * @return the instance of the application which is a singleton.
     */
    public static final StandardToCustomMonstersApp getInstance()
    {
        return INSTANCE;
    }


    private CompendiumResource compendiumResource = null;

    @CallSuper
    @Override
    public void onCreate()
    {
        super.onCreate();
        INSTANCE = this;
        compendiumResource = new CompendiumResource(getApplicationContext());
        compendiumResource.loadAllData();

        try {
            StandardMonsterDao standardMonsterDao = (StandardMonsterDao) compendiumResource.getDaoForCategory(Category.STANDARD_MONSTER);
            List<StandardMonster> monsters = standardMonsterDao.getAllRecords();
            for(StandardMonster monster : monsters) {
                CustomMonster customMonster = new CustomMonster(monster);
                Logger.info("\n___ converted to CustomMonster ___\n" + JsonUtil.toJson(customMonster, true, true));
            }
        } catch (Exception e) {
            Logger.error(e.getMessage(), e);
        }
    }

    /**
     * This method is for use in emulated process environments.  It will
     * never be called on a production Android device, where processes are
     * removed by simply killing them; no user code (including this callback)
     * is executed when doing so.
     */
    @CallSuper
    public void onTerminate() {
        super.onTerminate();
        compendiumResource = null;
        INSTANCE = null;
    }

    /**
     * This will return the resource for the compendium
     * @return
     */
    public CompendiumResource getCompendiumResource() {
        return compendiumResource;
    }
}

