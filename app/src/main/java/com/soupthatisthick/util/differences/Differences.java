package com.soupthatisthick.util.differences;

/**
 * Created by Owner on 6/30/2017.
 * Copyright of Stuart Marr Erskine, all rights reserved.
 */

import android.support.annotation.NonNull;

import com.soupthatisthick.util.Logger;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import static junit.framework.Assert.assertEquals;

/**
 * Internal class to determine results
 */
public class Differences {
    public final List<Object> expected, observed;
    public final List<Object> expectedButNotObserved, observedButNotExpected, expectedAndObserved;

    public Differences(Collection<? extends Object> expected, Collection<? extends Object> observed) {

        Logger.info("Expected " + expected.size() + " results and found " + observed.size() + ".");

        this.expected = new LinkedList<>();
        this.observed = new LinkedList<>();
        this.expected.addAll(expected);
        this.observed.addAll(observed);

        LinkedList<Object> expectedCopy = new LinkedList<Object>();
        LinkedList<Object> observedCopy = new LinkedList<Object>();

        expectedCopy.addAll(expected);
        observedCopy.addAll(observed);

        expectedButNotObserved = new ArrayList<Object>();
        observedButNotExpected = new ArrayList<Object>();
        expectedAndObserved = new ArrayList<Object>();

        //
        // Determine the two output lists
        //
        while (!expectedCopy.isEmpty()) {
            Object first = expectedCopy.removeFirst();
            if (observedCopy.remove(first)) {
                expectedAndObserved.add(first);
            } else {
                expectedButNotObserved.add(first);
            }
        }
        observedButNotExpected.addAll(observedCopy);
    }

    public final boolean isSame() {
        return (expectedButNotObserved.size()==0) && (observedButNotExpected.size()==0);
    }

    public final void assertSame()
    {
        assertEquals("Assert all expected items are observed.", 0, expectedButNotObserved.size());
        assertEquals("Assert all observed items are expected.", 0, observedButNotExpected.size());
    }

    public final void log()
    {
        logResults("Expected list", expected);
        logResults("Observed list", observed);
        logResults("Expected and observed", expectedAndObserved);
        logResults("Observed but not expected", observedButNotExpected);
        logResults("Expected but not observed", expectedButNotObserved);
    }



    /**
     * Logs out the list contents with the appropriate title
     *
     * @param title
     * @param results
     */
    public static final void logResults(@NonNull String title, @NonNull List<?> results) {
        Logger.title(title + String.format(" (%d results)", results.size()), 1);
        for (Object item : results) {
            Logger.info(item.toString(), 1);
        }
    }
}


