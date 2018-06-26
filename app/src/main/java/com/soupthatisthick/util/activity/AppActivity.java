package com.soupthatisthick.util.activity;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.CallSuper;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.ProgressBar;

import com.soupthatisthick.encounterbuilder.DndUtilApp;
import com.soupthatisthick.encounterbuilder.dao.helper.CompendiumResource;
import com.soupthatisthick.encounterbuilder.util.progress.ProgressMonitor;
import com.soupthatisthick.util.Logger;
import com.soupthatisthick.util.dao.DaoMaster;
import com.soupthatisthick.util.dao.ReadDao;

import java.util.Set;

import soupthatisthick.encounterapp.R;

/**
 * Created by Owner on 5/28/2017.
 * Copyright of Stuart Marr Erskine, all rights reserved.
 */

public class AppActivity extends Activity implements CompendiumResource.Listener {

    protected SharedPreferences globalPreferences;
    private CompendiumResource compendiumResource;

    private Button backButton;
    private Button forwardButton;
    private ProgressBar progressBar;

    @CallSuper
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        globalPreferences = PreferenceManager.getDefaultSharedPreferences(this);

        getActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getActionBar().setDisplayShowCustomEnabled(true);
        getActionBar().setCustomView(R.layout.action_bar_custom);

        final View actionbarView = getActionBar().getCustomView();
        backButton = actionbarView.findViewById(R.id.action_bar_back);
        forwardButton = actionbarView.findViewById(R.id.action_bar_forward);
        progressBar = actionbarView.findViewById(R.id.action_bar_progress);

        backButton.setOnClickListener(this::onBackButtonClicked);
        forwardButton.setOnClickListener(this::onForwardButtonClicked);
        progressBar.setMax(100);
        progressBar.setProgress(100);

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

    public Button getBackButton() {
        return backButton;
    }

    public Button getForwardButton() {
        return forwardButton;
    }

    public void setForwardButton(Button forwardButton) {
        this.forwardButton = forwardButton;
    }

    public ProgressBar getProgressBar() {
        return progressBar;
    }

    public void setProgressBar(ProgressBar progressBar) {
        this.progressBar = progressBar;
    }

    @CallSuper
    public void onBackButtonClicked(View view) {
        Logger.info("Actionbar back button clicked");
    }

    public void onForwardButtonClicked(View view) {
        Logger.info("Actionbar forward button clicked");
    }
}
