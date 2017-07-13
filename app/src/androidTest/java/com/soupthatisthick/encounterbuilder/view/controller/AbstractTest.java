package com.soupthatisthick.encounterbuilder.view.controller;

import android.util.Log;

/**
 * Created by Owner on 1/6/2017.
 * Copyright of Stuart Marr Erskine, all rights reserved.
 */

public class AbstractTest {

    private static String getTag(int depth)
    {
        try {
            throw new Exception("");
        } catch (Exception e)
        {
            StackTraceElement se = e.getStackTrace()[depth];
            return se.getMethodName() + ":" + se.getLineNumber();
        }
    }

    public void log(String message)
    {
        Log.d(getTag(2), message);
    }


}
