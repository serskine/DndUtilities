package com.soupthatisthick.encounterbuilder;

import android.app.Application;
import android.support.annotation.CallSuper;

import com.soupthatisthick.encounterbuilder.dao.helper.CompendiumResource;

/**
 * Created by Owner on 6/30/2017.
 * Copyright of Stuart Marr Erskine, all rights reserved.
 */

public class DndUtilApp extends Application {

    private static DndUtilApp INSTANCE;


    /**
     * @return the instance of the application which is a singleton.
     */
    public static final DndUtilApp getInstance()
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
