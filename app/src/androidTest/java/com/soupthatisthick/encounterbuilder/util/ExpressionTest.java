package com.soupthatisthick.encounterbuilder.util;

import com.soupthatisthick.encounterbuilder.InstrumentationTest;
import com.soupthatisthick.util.Expression;

import org.junit.Test;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.fail;

/**
 * Created by Owner on 5/29/2017.
 * Copyright of Stuart Marr Erskine, all rights reserved.
 */

public class ExpressionTest
{

    @Test
    public void testExpectedSuccess()
    {

        String[] inputs = new String[] {
                "0", "-0", "+0", "1/2", "2/1", "(2/2)"
        };
        Double[] expected = new Double[] {
                0d, -0d, +0d, 0.5d, 2d, 1d
        };

        try {
            assertEquals("Inputs and outputs should have the same size", inputs.length, expected.length);

            for(int i=0; i<inputs.length; i++)
            {
                Double value = Expression.eval(inputs[i]);
                assertEquals(String.format("assert same at input[%d]", i), expected[i], value);

            }
        } catch (Exception e) {
            fail(e.getMessage());
        }
    }
}














