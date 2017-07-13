package com.soupthatisthick.encounterbuilder.activity.lookup;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.soupthatisthick.encounterbuilder.activity.MainActivity;
import com.soupthatisthick.util.activity.AppActivity;

import soupthatisthick.encounterapp.R;

public class ViewSplashScreenActivity extends AppActivity {

    private final long DELAY = 1000;

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);


    }

    @Override
    public void onResume()
    {
        super.onResume();
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(ViewSplashScreenActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        }, DELAY);

    }

}
