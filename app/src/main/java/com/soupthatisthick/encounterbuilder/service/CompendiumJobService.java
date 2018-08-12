package com.soupthatisthick.encounterbuilder.service;

import android.app.job.JobInfo;
import android.app.job.JobParameters;
import android.app.job.JobScheduler;
import android.app.job.JobService;
import android.content.ComponentName;
import android.content.Context;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.PersistableBundle;

import com.loopj.android.http.FileAsyncHttpResponseHandler;
import com.soupthatisthick.util.Logger;
import com.soupthatisthick.util.json.JsonUtil;

import static java.lang.Thread.*;

public class CompendiumJobService extends JobService {

    private static final String PREFIX = CompendiumJobService.class.getCanonicalName() + ".";

    public static final int JOB_ID_SIMULATE = 1;
    public static final int JOB_ID_TRANSFER = 2;

    public static final String EXTRA_SIMULATE_NUM_STEPS = PREFIX + "EXTRA_SIMULATE_NUM_STEPS";
    public static final String EXTRA_SIMULATE_STEP_DELAY = PREFIX + "EXTRA_SIMULATE_STEP_DELAY";
    public static final String EXTRA_SIMULATE_JOB_TITLE = PREFIX + "EXTRA_SIMULATE_JOB_TITLE";

    public static final String EXTRA_TRANSFER_SOURCE = PREFIX + "EXTRA_TRANSFER_SOURCE";
    public static final String EXTRA_TRANSFER_DESTINATION = PREFIX + "EXTRA_TRANSFER_DESTINATION";

    @Override
    public boolean onStartJob(JobParameters jobParameters) {
        switch(jobParameters.getJobId()) {
            case JOB_ID_SIMULATE:
                return simulateJob(jobParameters);
            case JOB_ID_TRANSFER:
                return transferJob(jobParameters);
            default:
                Logger.warning("We don't know how to handle job with id " + jobParameters.getJobId() + ".\n" + JsonUtil.toJson(jobParameters, true, true));
                return true;
        }

    }

    @Override
    public boolean onStopJob(JobParameters jobParameters) {
        return false;
    }


    /**
     * This method is used to simulate a job that runs in the background
     * @param jobParameters contains the job parameters
     * @return true if the job is finished, false if it is not.
     */
    private boolean simulateJob(JobParameters jobParameters) {
        AsyncTask<JobParameters, Void, Void> task = new AsyncTask<JobParameters, Void, Void>() {
            @Override
            protected Void doInBackground(JobParameters... backgroundParams) {
                final JobParameters jobParams = backgroundParams[0];
                final PersistableBundle extras = jobParameters.getExtras();

                if (extras!=null) {
                    final int numSteps = extras.getInt(EXTRA_SIMULATE_NUM_STEPS, 0);
                    final long stepDelay = extras.getLong(EXTRA_SIMULATE_STEP_DELAY, 0L);
                    final String jobTitle = extras.getString(EXTRA_SIMULATE_JOB_TITLE, "Unknown Job");

                    Logger.info("\n___ Executing " + jobTitle + " ___\n" + JsonUtil.toJson(jobParameters, true, true));
                    for (int step = 1; step <= numSteps; step++) {
                        final String line = String.format(" - %s: Step %d/%d", jobTitle, step, numSteps);
                        Logger.info(line);
                        pause(stepDelay);
                    }
                }

                jobFinished(jobParams, false);  // Call this when the job is complete

                return null;
            }
        };

        return true;
    }

    private static void pause(long milliseconds) {
        try {
            sleep(milliseconds);
        } catch (InterruptedException e) {
            Logger.warning(e.getMessage());
        }
    }

    /**
     * This method is used to start an async task that will transfer data from one uri and send it to another uri
     * @param jobParameters
     * @return
     */
    private boolean transferJob(JobParameters jobParameters) {

        AsyncTask<JobParameters, Void, Void> task = new AsyncTask<JobParameters, Void, Void>() {
            @Override
            protected Void doInBackground(JobParameters... backgroundParams) {
                final JobParameters jobParams = backgroundParams[0];
                final PersistableBundle extras = jobParameters.getExtras();

                if (extras!=null) {
                    final String sourceAsString = extras.getString(EXTRA_TRANSFER_SOURCE, null);
                    final String destinationAsString = extras.getString(EXTRA_TRANSFER_DESTINATION, null);

                    final Uri source = Uri.parse(sourceAsString);
                    final Uri destination = Uri.parse(destinationAsString);

                }

                jobFinished(jobParams, false);  // Call this when the job is complete

                return null;
            }
        };
        task.execute(jobParameters);

        return true;

    }

    // ----------------------- methods used to start the jobs ----------------------/

    /**
     * Simulates a job that occurs over a network connection
     * @param context where we called it from
     * @param title the title of the job
     * @param numSteps the number of steps involved in the job
     * @param stepDelay the amount of delay in milliseconds between steps
     */
    public static void scheduleSimulateJob(Context context, final String title, int numSteps, int stepDelay) {
        PersistableBundle extras = new PersistableBundle();
        extras.putInt(EXTRA_SIMULATE_NUM_STEPS, numSteps);
        extras.putLong(EXTRA_SIMULATE_STEP_DELAY, stepDelay);
        extras.putString(EXTRA_SIMULATE_JOB_TITLE, title);

        ComponentName componentName = new ComponentName(context, CompendiumJobService.class);
        JobInfo jobInfo = new JobInfo.Builder(JOB_ID_SIMULATE, componentName)
                .setRequiredNetworkType(JobInfo.NETWORK_TYPE_ANY)
                .setExtras(extras)
                .build();
        JobScheduler jobScheduler = (JobScheduler) context.getSystemService(JOB_SCHEDULER_SERVICE);
        jobScheduler.schedule(jobInfo);
    }

    public static void scheduleTransferTask(Context context, final Uri source, final Uri destination) {
        PersistableBundle extras = new PersistableBundle();
        extras.putString(EXTRA_TRANSFER_SOURCE, source.toString());
        extras.putString(EXTRA_TRANSFER_DESTINATION, destination.toString());

        ComponentName componentName = new ComponentName(context, CompendiumJobService.class);
        JobInfo jobInfo = new JobInfo.Builder(JOB_ID_SIMULATE, componentName)
                .setRequiredNetworkType(JobInfo.NETWORK_TYPE_ANY)
                .setExtras(extras)
                .build();
        JobScheduler jobScheduler = (JobScheduler) context.getSystemService(JOB_SCHEDULER_SERVICE);
        jobScheduler.schedule(jobInfo);
    }

}
