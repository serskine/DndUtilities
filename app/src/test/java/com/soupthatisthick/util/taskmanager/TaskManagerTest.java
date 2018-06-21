package com.soupthatisthick.util.taskmanager;

import android.os.AsyncTask;
import android.support.test.runner.AndroidJUnit4;

import com.soupthatisthick.encounterbuilder.util.progress.TaskManager;
import com.soupthatisthick.util.Logger;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.Date;

import static junit.framework.Assert.assertTrue;

/**
 * Created by Owner on 6/17/2018.
 * Copyright of Stuart Marr Erskine, all rights reserved.
 */
public class TaskManagerTest {

    @Before
    public void setup() {

    }

    @After
    public void tearDown() {

    }

    private class TimedTask extends AsyncTask<Void, Void, Void> {
        public final long interval, numInterval;
        public final Date started;
        public final String tag;

        public TimedTask(String tag, long numInterval, long interval) {
            this.tag = tag;
            this.interval = interval;
            this.numInterval = numInterval;
            this.started = new Date();
            Logger.info("Timed task " + tag + " created at " + started.toString() +".");
        }

        /**
         * Override this method to perform a computation on a background thread. The
         * specified parameters are the parameters passed to {@link #execute}
         * by the caller of this task.
         * <p>
         * This method can call {@link #publishProgress} to publish updates
         * on the UI thread.
         *
         * @param voids The parameters of the task.
         * @return A result, defined by the subclass of this task.
         * @see #onPreExecute()
         * @see #onPostExecute
         * @see #publishProgress
         */
        @Override
        protected Void doInBackground(Void... voids) {
            for(int i=0; i<numInterval; i++) {
                try {
                    final Date now = new Date();
                    Logger.info(" - " + tag + ": interval " + i + " at " + now.toString() + ".");
                    Thread.sleep(interval);
                } catch (InterruptedException e) {
                    Logger.warning(" - " + tag + ": Interrupted! " + e.getMessage());
                }
            }
            final Date now = new Date();
            Logger.info(" - " + tag + ": completed at " + now.toString());
            return null;
        }
    }

    @Test
    public void taskManagerTest() {
        Logger.info("taskManagerTest()");
        TaskManager<Void, Void, Void> taskManager = new TaskManager<>();

        for(int i=0; i<10; i++) {
            final TimedTask timedTask = new TimedTask("Task[" + i + "]", 10, 1000);
            taskManager.add(timedTask);
        }
        taskManager.startAllPendingTasks();
        taskManager.waitForAllTasksToFinish();
        Logger.info("All Timed tasks completed!");
        assertTrue(true);
    }



}
