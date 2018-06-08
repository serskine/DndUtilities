package com.soupthatisthick.encounterbuilder.activity.lookup;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ProgressBar;

import com.soupthatisthick.encounterbuilder.DndUtilApp;
import com.soupthatisthick.encounterbuilder.activity.MainActivity;
import com.soupthatisthick.encounterbuilder.dao.helper.CompendiumResource;
import com.soupthatisthick.encounterbuilder.util.progress.ProgressMonitor;
import com.soupthatisthick.encounterbuilder.util.sort.Category;
import com.soupthatisthick.util.Logger;
import com.soupthatisthick.util.activity.AppActivity;
import com.soupthatisthick.util.dao.DaoMaster;
import com.soupthatisthick.util.dao.ReadDao;

import soupthatisthick.encounterapp.R;

public class ViewSplashScreenActivity extends AppActivity implements CompendiumResource.Listener {

//    private final long DELAY = 1000;
    private final long DELAY = 100;

    private ProgressBar progressBar;

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        progressBar = findViewById(R.id.theSplashProgressBar);
    }

    @Override
    public void onResume()
    {
        super.onResume();
        DndUtilApp.getInstance().getCompendiumResource().addListener(this);
    }

    @Override
    public void onPause()
    {
        super.onPause();
        DndUtilApp.getInstance().getCompendiumResource().removeListener(this);
    }

    /**
     * These are triggered when the progress monitor updates
     * @param monitor
     * @param numSteps
     * @param numFailedSteps
     * @param numSuccessSteps
     * @param numPendingSteps
     */
    @Override
    public void update(ProgressMonitor monitor, int numSteps, int numFailedSteps, int numSuccessSteps, int numPendingSteps) {

        final int numCompletedSteps = numSteps - numPendingSteps;
        Logger.info("PROGRESS: " + numCompletedSteps + " / " + numSteps);

        progressBar.setMax(numSteps);
        progressBar.setProgress(numCompletedSteps);

        if (numPendingSteps<1) {
            Intent intent = new Intent(ViewSplashScreenActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        }
    }

    @Override
    public void loadDaoMasterSuccess(String daoMasterKey, DaoMaster daoMaster) {
        // do nothing. We don't care at this point.
    }

    @Override
    public void loadDaoMasterFailure(String daoMasterKey) {
        // do nothing. We don't care at this point.
    }

    @Override
    public void loadDaoSuccess(String daoKey, ReadDao readDao) {
        // do nothing. We don't care at this point.
    }

    @Override
    public void loadDaoFailure(String daoKey) {
        // do nothing. We don't care at this point.
    }

}
