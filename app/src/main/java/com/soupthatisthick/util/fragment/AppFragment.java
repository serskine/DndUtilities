package com.soupthatisthick.util.fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.CallSuper;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;

import com.soupthatisthick.util.Logger;

import java.util.Set;

/**
 * Created by Owner on 5/30/2017.
 * Copyright of Stuart Marr Erskine, all rights reserved.
 */

public abstract class AppFragment extends Fragment {

    protected SharedPreferences globalPreferences;
    protected LayoutInflater layoutInflater;

    @Override
    @CallSuper
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        this.globalPreferences = PreferenceManager.getDefaultSharedPreferences(getActivity());
        this.layoutInflater = getActivity().getLayoutInflater();
    }

    @Override
    public abstract View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState);

    protected final void hideSoftKeyboard()
    {
        // Check if no view has focus:
        View view = getActivity().getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager)getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
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