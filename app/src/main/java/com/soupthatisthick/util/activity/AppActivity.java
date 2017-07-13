package com.soupthatisthick.util.activity;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.CallSuper;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import com.soupthatisthick.util.Logger;

import java.util.Set;

/**
 * Created by Owner on 5/28/2017.
 * Copyright of Stuart Marr Erskine, all rights reserved.
 */

public class AppActivity extends Activity {

    protected SharedPreferences globalPreferences;

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
}
