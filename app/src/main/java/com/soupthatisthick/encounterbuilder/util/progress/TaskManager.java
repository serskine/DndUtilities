package com.soupthatisthick.encounterbuilder.util.progress;

import android.os.AsyncTask;

import com.soupthatisthick.util.Logger;

import java.util.ArrayList;
import java.util.Arrays;

public class TaskManager<Params, Progress, Result>{

    public static class Status {
        public final int numPending;
        public final int numRunning;
        public final int numFinished;
        public final int numCancelled;
        public final int numTasks;

        public Status(int numTasks, int numPending, int numRunning, int numFinished, int numCancelled) {
            this.numCancelled = numCancelled;
            this.numFinished = numFinished;
            this.numRunning = numRunning;
            this.numPending = numPending;
            this.numTasks = numTasks;
        }

        @Override
        public String toString() {
            return String.format("%d tasks, %d pending, %d running, %d finished, %d cancelled",
                    numTasks, numPending, numRunning, numFinished, numCancelled);
        }
    }

    private final ArrayList<AsyncTask<Params, Progress, Result>> tasks = new ArrayList<>();

    public TaskManager clear(boolean mayInterruptIfRunning) {
        synchronized (tasks) {
            cancelAllTasks(mayInterruptIfRunning).waitForAllTasksToFinish();
            tasks.clear();
        }
        return this;
    }

    public TaskManager add(AsyncTask<Params, Progress, Result>... asyncTasks) {
        synchronized(tasks) {
            this.tasks.addAll(Arrays.asList(asyncTasks));
        }
        return this;
    }

    public TaskManager startAllPendingTasks(Params... params) {
        synchronized (tasks) {
            for (AsyncTask<Params, Progress, Result> task : tasks) {
                if (task.getStatus() == AsyncTask.Status.PENDING) {
                    task.execute(params);
                }
            }
        }
        return this;
    }

    public TaskManager waitForAllTasksToFinish() {
        boolean done;
        do {
            synchronized (tasks) {
                done = true;
                for (AsyncTask<Params, Progress, Result> task : tasks) {
                    if (task.getStatus() != AsyncTask.Status.FINISHED) {
                        done = false;
                        break;
                    }
                }
                Status status = getStatus();
                Logger.debug("Status: " + status);
            }
        } while(!done);
        return this;
    }

    public TaskManager cancelAllTasks(boolean mayInterruptIfRunning) {
        synchronized (tasks) {
            for (AsyncTask<Params, Progress, Result> task : tasks) {
                if (!task.isCancelled()) {
                    task.cancel(mayInterruptIfRunning);
                }
            }
        }
        return this;
    }

    public synchronized Status getStatus() {
        int numPending = 0;
        int numCancelled = 0;
        int numRunning = 0;
        int numFinished = 0;
        int numTasks;

        synchronized (tasks) {
            numTasks = tasks.size();
            for(AsyncTask task : tasks) {
                switch(task.getStatus()) {
                    case FINISHED:
                        numFinished++;
                        break;
                    case PENDING:
                        numPending++;
                        break;
                    case RUNNING:
                        numRunning++;
                        break;
                    default:
                        break;
                }
            }
        }

        return new Status(numTasks, numPending, numRunning, numFinished, numCancelled);
    }
}
