package com.soupthatisthick.encounterbuilder.logic;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;
import android.util.Log;

import com.soupthatisthick.encounterbuilder.dao.master.DndMaster;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static junit.framework.Assert.assertNotNull;
import static junit.framework.Assert.assertTrue;

/**
 * Created by Owner on 1/2/2017.
 * Copyright of Stuart Marr Erskine, all rights reserved.
 */

@RunWith(AndroidJUnit4.class)
public class DndMasterTest {

    public static final String TAG = DndMasterTest.class.getSimpleName();

    private DndMaster db = null;
    private Context ctx;
    private Logger logger = Logger.getLogger(TAG);

    @Before
    public void setUp()
    {
        InstrumentationRegistry.getTargetContext();
    }

    @Test
    public void testSetup()
    {

    }

    @Test
    public void testStuff()
    {

        String materials = "";
        String description = "(Component materials) other stuffxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx";


        String regex = "\\((.*|\n)\\)";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(description);
        boolean isFound = true;

        if (matcher.find()) {
            materials = matcher.group(1);
            description = matcher.replaceFirst("");
            isFound = true;
        } else {
            isFound = false;
        }

        Log.d(TAG, "---------------------------------------------");
        Log.d(TAG, "isFound   = " + isFound);
        Log.d(TAG, "materials = " + materials);
        Log.d(TAG, "desc      = " + description);


    }

}
