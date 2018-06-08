package com.soupthatisthick.encounterbuilder.util.progress;

import com.soupthatisthick.encounterbuilder.util.announcer.Announcer;
import com.soupthatisthick.util.Logger;

import java.util.EventListener;

public class ProgressMonitor {

    /**
     * This is the interface that listeners must implement to receive updates from the ProgressMonitor
     * These listeners must remember to add and remove themselves
     */
    public interface Listener extends EventListener {
        void update(
                final ProgressMonitor monitor,
                final int numSteps,
                final int numFailedSteps,
                final int numSuccessSteps,
                final int numPendingSteps
        );
    }
    
    private final Announcer<ProgressMonitor.Listener> listeners = Announcer.to(ProgressMonitor.Listener.class);
    
    private int numSteps;
    private int numFailedSteps;
    private int numSuccessSteps;
    private int numPendingSteps;
    private String name;

    public ProgressMonitor(final int numSteps) {
        this(Logger.getCodeSpot(1).toString(), numSteps);
    }

    public ProgressMonitor(final String name, final int numSteps) {
        this.name = (name==null) ? getClass().getSimpleName() : name;
        init(numSteps);
    }
    
    public final synchronized ProgressMonitor init(final int numSteps) {
        this.numSteps = Math.max(0, numSteps);
        this.numPendingSteps = this.numSteps;
        this.numFailedSteps = 0;
        this.numSuccessSteps = 0;
        announceUpdate();
        return this;
    }
    
    public final synchronized ProgressMonitor reset() {
        return init(numSteps);
    }
    
    public final synchronized ProgressMonitor recordSuccess() {
        if (this.numPendingSteps>0) {
            numPendingSteps--;
            numSuccessSteps++;
            announceUpdate();
        }
        return this;
    }
    
    public final synchronized ProgressMonitor recordFailure() {
        if (this.numPendingSteps>0) {
            numPendingSteps--;
            numFailedSteps++;
            announceUpdate();
        }
        return this;
    }
    
    public final synchronized ProgressMonitor recordRemainderAsSuccess() {
        if (this.numPendingSteps>0) {
            numSuccessSteps += this.numPendingSteps;
            numPendingSteps = 0;
            announceUpdate();
        }
        return this;
    }
    
    public final synchronized ProgressMonitor recordRemainderAsFailure() {
        if (this.numPendingSteps>0) {
            numFailedSteps += this.numPendingSteps;
            numPendingSteps = 0;
            announceUpdate();
        }
        return this;
    }


    public final synchronized ProgressMonitor announceUpdate() {
        listeners.announce().update(this, numSteps, numSuccessSteps, numFailedSteps, numPendingSteps);
        Logger.info(String.format(
                "PROGRESS %s: numSteps %d, numSuccess %d, numFailed %d, numPending %d",
                name, numSteps, numSuccessSteps, numFailedSteps, numPendingSteps
        ));
        return this;
    }

    public final synchronized ProgressMonitor addListener(ProgressMonitor.Listener listener) {
        listeners.addListener(listener);
        listener.update(this, numSteps, numSuccessSteps, numFailedSteps, numPendingSteps);
        return this;
    }

    public final synchronized ProgressMonitor removeListener(ProgressMonitor.Listener listener) {
        listeners.removeListener(listener);
        return this;
    }
}
