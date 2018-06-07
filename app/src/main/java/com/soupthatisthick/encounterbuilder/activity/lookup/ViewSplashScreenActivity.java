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
import com.soupthatisthick.util.Logger;
import com.soupthatisthick.util.activity.AppActivity;

import soupthatisthick.encounterapp.R;

public class ViewSplashScreenActivity extends AppActivity {

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
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {

                while (!updateProgressBar()) {
                    try {
                        Thread.sleep(DELAY);
                    } catch (InterruptedException e) {
                        Logger.error(e.getMessage(), e);
                    }
                }

                Intent intent = new Intent(ViewSplashScreenActivity.this, MainActivity.class);
                startActivity(intent);
                finish();

            }
        }, DELAY);

    }

    /**
     * Will update the progress bar and return true if the progress is completed.
     * @return true if we have completed our progress
     */
    private boolean updateProgressBar() {
        CompendiumResource compendiumResource = DndUtilApp.getInstance().getCompendiumResource();
        boolean isCompleted = compendiumResource.isCompleted();

        int step = compendiumResource.getStep();
        int maxStep = compendiumResource.getNumberOfSteps();

        Logger.info("PROGRESS: " + step + " / " + maxStep);

        progressBar.setIndeterminate(false);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            progressBar.setMin(0);
        }

        progressBar.setMax(maxStep);
        progressBar.setProgress(step);


        return isCompleted;
    }

}
