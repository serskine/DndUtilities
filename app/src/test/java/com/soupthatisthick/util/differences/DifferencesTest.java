package com.soupthatisthick.util.differences;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static junit.framework.Assert.assertEquals;

/**
 * Created by Owner on 6/30/2017.
 * Copyright of Stuart Marr Erskine, all rights reserved.
 */

public class DifferencesTest {

    @Test
    public void testWithIntersection()
    {
        List<Integer> range1 = createRange(0, 10);
        List<Integer> range2 = createRange(7, 15);

        assertEquals(10, range1.size());
        assertEquals(8, range2.size());

        Differences diff = new Differences(range1, range2);

        diff.log();

        assertEquals(7, diff.expectedButNotObserved.size());
        assertEquals(3, diff.expectedAndObserved.size());
        assertEquals(5, diff.observedButNotExpected.size());

    }

    @Test
    public void testSame()
    {
        List<Integer> range = createRange(0, 10);
        assertEquals(10, range.size());

        Differences diff = new Differences(range, range);

        diff.log();

        assertEquals(0, diff.observedButNotExpected.size());
        assertEquals(0, diff.expectedButNotObserved.size());
        assertEquals(range.size(), diff.expectedAndObserved.size());
    }

    @Test
    public void testSameWithDupes()
    {
        List<Integer> range = createRange(0, 10);
        range.addAll(createRange(0, 10));
        assertEquals(20, range.size());

        Differences diff = new Differences(range, range);

        diff.log();

        assertEquals(0, diff.observedButNotExpected.size());
        assertEquals(0, diff.expectedButNotObserved.size());
        assertEquals(range.size(), diff.expectedAndObserved.size());

    }

    @Test
    public void testNoIntersection()
    {
        List<Integer> range1 = createRange(0, 10);
        List<Integer> range2 = createRange(10, 21);

        assertEquals(10, range1.size());
        assertEquals(11, range2.size());

        Differences diff = new Differences(range1, range2);

        diff.log();

        assertEquals(10, diff.expectedButNotObserved.size());
        assertEquals(0, diff.expectedAndObserved.size());
        assertEquals(11, diff.observedButNotExpected.size());

    }

    @Test
    public void testSomeDupesNotExpected()
    {
        List<Integer> range1 = createRange(0, 10);
        List<Integer> overlap = createRange(5,10);
        range1.addAll(overlap);

        List<Integer> range2 = createRange(0, 10);
        assertEquals(15, range1.size());
        assertEquals(10, range2.size());

        Differences diff = new Differences(range1, range2);

        diff.log();

        assertEquals(5, diff.expectedButNotObserved.size());
        assertEquals(10, diff.expectedAndObserved.size());
        assertEquals(0, diff.observedButNotExpected.size());


    }

    public static final ArrayList<Integer> createRange(int min, int max)
    {
        if (min>max)
        {
            return createRange(max, min);
        } else {
            ArrayList<Integer> range = new ArrayList<>();
            for (int i = min; i < max; i++) {
                range.add(new Integer(i));
            }
            return range;
        }
    }
}
