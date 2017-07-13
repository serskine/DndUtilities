package com.soupthatisthick.encounterbuilder.view;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static junit.framework.Assert.assertEquals;

/**
 * Created by Owner on 1/30/2017.
 * Copyright of Stuart Marr Erskine, all rights reserved.
 */

@RunWith(AndroidJUnit4.class)
public class TitleTextViewTest {

    private Context context;

    @Before
    public void setUp()
    {
        // Context of the app under test.
        context = InstrumentationRegistry.getTargetContext();
    }

    @Test
    public void testGetSetText() throws Exception
    {

        TitleTextView tv = new TitleTextView(context);
        String title = "TITLE";
        String text = "TEXT";

        tv.setTitle(title);
        tv.setText(text);

        String output = tv.getText().toString();
        assertEquals(title+text, output);

    }
}
