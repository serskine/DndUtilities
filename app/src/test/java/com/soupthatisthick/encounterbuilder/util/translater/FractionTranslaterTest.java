package com.soupthatisthick.encounterbuilder.util.translater;

import android.support.test.runner.AndroidJUnit4;

import org.junit.Test;
import org.junit.runner.RunWith;

import static junit.framework.Assert.assertEquals;

/**
 * Created by Owner on 6/22/2017.
 * Copyright of Stuart Marr Erskine, all rights reserved.
 */
public class FractionTranslaterTest {
    @Test
    public void testFractionTranslater() {
        double[] inputs = {
            0d,
            1d,
            5d,
            5/2d,
            2/5d,
            2/4d,
            1/2d,
            1/4d,
            1/8d
        };
        String[] expected = {
            "0",
            "1",
            "5",
            "5/2",
            "2/5",
            "1/2",
            "1/2",
            "1/4",
            "1/8"
        };

        assertEquals("Input length should be the same as the expected outputs length.", inputs.length, expected.length);

        for(int i=0; i<inputs.length; i++)
        {
            String observed = FractionTranslater.asFraction(inputs[i]);
            assertEquals("Failed on index " + i + ".", expected[i], observed);
        }
    }
}
