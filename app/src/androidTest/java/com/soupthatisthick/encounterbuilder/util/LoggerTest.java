package com.soupthatisthick.encounterbuilder.util;

import android.util.Log;

import com.soupthatisthick.encounterbuilder.InstrumentationTest;
import com.soupthatisthick.util.Logger;

import org.junit.Test;

import static junit.framework.Assert.assertEquals;

/**
 * Created by Owner on 1/29/2017.
 * Copyright of Stuart Marr Erskine, all rights reserved.
 */
public class LoggerTest extends InstrumentationTest {

    public static final String TAG = LoggerTest.class.getSimpleName();

    @Test
    public void testGetCodeSpot()
    {
        Logger.CodeSpot element = Logger.getCodeSpot(0);

        Log.d(TAG,"*************************");
        Log.d(TAG, "TESTING GETTING THE CODE SPOT");
        Log.d(TAG, element.toString());

        assertEquals("File name should match.", "LoggerTest.java", element.stackTraceElement.getFileName());
        assertEquals("Line numbers should match.", 23, element.stackTraceElement.getLineNumber());

    }
    @Test
    public void testDebug()
    {
        Log.d(TAG,"*************************");
        Logger.debug("TESTING DEBUG");
    }

    @Test
    public void testInfo()
    {
        Log.i(TAG,"*************************");
        Logger.info("TESTING INFO");
    }

    @Test
    public void testWarning()
    {
        Log.w(TAG,"*************************");
        Logger.warning("TESTING WARNING");
    }

    @Test
    public void testError()
    {
        Log.e(TAG,"*************************");
        Logger.error("TESTING ERROR", null);
    }

    @Test
    public void testTitle() {
        Log.e(TAG,"*************************");
        Logger.title("TESTING TITLE");
    }

    @Override
    protected void onSetup() {
        // Do nothing. It's not required
    }

    @Override
    protected void onTeardown() {

    }
}
