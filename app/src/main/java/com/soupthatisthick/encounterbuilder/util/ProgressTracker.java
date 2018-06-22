package com.soupthatisthick.encounterbuilder.util;

import com.soupthatisthick.encounterbuilder.util.announcer.Announcer;

import java.util.EventListener;

/**
 * Created by Owner on 6/7/2018.
 * Copyright of Stuart Marr Erskine, all rights reserved.
 */
public class ProgressTracker {

    public static interface Listener extends EventListener {
        void update(ProgressTracker progressTracker);
    }

    private int numSteps;
    private int numSuccess;
    private int numFail;
    private int numPending;

    public final Announcer<ProgressTracker.Listener> listeners = Announcer.to(ProgressTracker.Listener.class);

    public ProgressTracker(int numSteps) {
        reset(numSteps);
    }

    public void reset(int numSteps) {
        if (numSteps<1) {
            numSteps = 0;
        }
        this.numSteps = numSteps;
        this.numSuccess = 0;
        this.numFail = 0;
        this.numPending = numSteps;
        listeners.announce().update(this);
    }

    public void recordSuccess() {
        numSuccess++;
        numPending--;
        listeners.announce().update(this);
    }

    public void recordFailure() {
        numFail++;
        numPending--;
        listeners.announce().update(this);
    }

    public void failRemainder() {
        numFail += numPending;
        numPending = 0;
        listeners.announce().update(this);
    }

    public void succeedRemainder() {
        numSuccess += numPending;
        numPending = 0;
        listeners.announce().update(this);
    }

    public boolean isCompleted() {
        return (numPending<1);
    }

    public int getNumSteps() {
        return numSteps;
    }

    public int getNumSuccess() {
        return numSuccess;
    }

    public int getNumFail() {
        return numFail;
    }

}
