package com.soupthatisthick.encounterbuilder.activity;

import android.os.Bundle;
import android.preference.PreferenceActivity;
import android.preference.PreferenceFragment;
import android.preference.PreferenceManager;

import soupthatisthick.encounterapp.R;

/**
 * Created by Owner on 5/28/2017.
 * Copyright of Stuart Marr Erskine, all rights reserved.
 */

public class SettingsActivity extends PreferenceActivity
{


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.preferences);
    }
}
