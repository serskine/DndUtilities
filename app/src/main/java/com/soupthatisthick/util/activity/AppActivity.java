package com.soupthatisthick.util.activity;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.CallSuper;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import com.soupthatisthick.encounterbuilder.DndUtilApp;
import com.soupthatisthick.encounterbuilder.dao.helper.CompendiumResource;
import com.soupthatisthick.encounterbuilder.util.progress.ProgressMonitor;
import com.soupthatisthick.util.Logger;
import com.soupthatisthick.util.dao.DaoMaster;
import com.soupthatisthick.util.dao.ReadDao;

import java.util.Set;

/**
 * Created by Owner on 5/28/2017.
 * Copyright of Stuart Marr Erskine, all rights reserved.
 */

public class AppActivity extends Activity implements CompendiumResource.Listener {

    protected SharedPreferences globalPreferences;
    private CompendiumResource compendiumResource;

    @CallSuper
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        globalPreferences = PreferenceManager.getDefaultSharedPreferences(this);
    }

    protected final void hideSoftKeyboard()
    {
        // Check if no view has focus:
        View view = this.getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    protected void logGlobalPreferences()
    {
        Set<String> keys = globalPreferences.getAll().keySet();
        Logger.info("Keys for global preferences (x" + keys.size() + ")");
        for(String setting : keys)
        {
            Logger.info(" - " + setting);
        }
    }

    @Override
    @CallSuper
    public void loadDaoMasterSuccess(String daoMasterKey, DaoMaster daoMaster) {
        Logger.info("DaoMaster " + daoMasterKey + ": LOADED");
    }

    @Override
    @CallSuper
    public void loadDaoMasterFailure(String daoMasterKey) {
        Logger.warning("DaoMaster " + daoMasterKey + ": NOT LOADED");
    }

    @Override
    @CallSuper
    public void loadDaoSuccess(String daoKey, ReadDao readDao) {
        Logger.info("ReadDao " + daoKey + ": LOADED");
    }

    @Override
    @CallSuper
    public void loadDaoFailure(String daoKey) {
        Logger.warning("ReadDao " + daoKey + ": NOT LOADED");
    }

    @Override
    @CallSuper
    public void update(ProgressMonitor monitor, int numSteps, int numFailedSteps, int numSuccessSteps, int numPendingSteps) {
        Logger.info(String.format(
                "PROGRESS : numSteps %d, numSuccess %d, numFailed %d, numPending %d",
                numSteps, numSuccessSteps, numFailedSteps, numPendingSteps
        ));
    }

    @Override
    @CallSuper
    protected void onResume() {
        super.onResume();
        compendiumResource = DndUtilApp.getInstance().getCompendiumResource();
        compendiumResource.addListener(this);
    }

    @Override
    @CallSuper
    protected void onPause() {
        super.onPause();
        compendiumResource = DndUtilApp.getInstance().getCompendiumResource();
        compendiumResource.removeListener(this);
    }


    protected final CompendiumResource getCompendiumResource() {
        return this.compendiumResource;
    }
}
