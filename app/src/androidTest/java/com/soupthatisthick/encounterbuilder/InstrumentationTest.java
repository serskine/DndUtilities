package com.soupthatisthick.encounterbuilder;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import com.soupthatisthick.util.Logger;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static junit.framework.Assert.assertNotNull;

/**
 * Created by Owner on 2/18/2017.
 * Copyright of Stuart Marr Erskine, all rights reserved.
 */
@RunWith(AndroidJUnit4.class)
public abstract class InstrumentationTest {

    protected Context context;


    @Before
    public final void setUp()
    {
        Logger.title("Begin Setup");
        context = InstrumentationRegistry.getTargetContext();
        assertNotNull(context);
        onSetup();
        Logger.title("End Setup");
    }

    protected abstract void onSetup();

    @Test
    public final void testSetup()
    {
        // This will test that the setup worked properly.
    }


    @After
    public void tearDown()
    {
        context = null;
    }

}
