package com.soupthatisthick.encounterbuilder.activity;

import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.DataSetObserver;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.method.ScrollingMovementMethod;
import android.text.style.ForegroundColorSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.soupthatisthick.encounterbuilder.util.Text;
import com.soupthatisthick.encounterbuilder.util.adapter.CustomListAdapter;
import com.soupthatisthick.encounterbuilder.util.view.ViewUtil;
import com.soupthatisthick.util.Logger;
import com.soupthatisthick.util.activity.ViewListActivity;
import com.soupthatisthick.util.adapters.LogEntryAdapter;
import com.soupthatisthick.util.model.LogEntry;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

import soupthatisthick.encounterapp.R;

import static android.os.AsyncTask.Status.RUNNING;

/**
 * Created by Owner on 5/11/2017.
 * Copyright of Stuart Marr Erskine, all rights reserved.
 */

public class ViewLogsActivity extends ViewListActivity<LogEntry> {

    public static final String KEY_UPDATING_INTERVAL = "KEY_UPDATING_INTERVAL";
    public static final String KEY_IS_UPDATING = "KEY_IS_UPDATING";

    ImageView updateButton;
    TextView console;

    private long updateIntervalMs = 1000;   // update every second

    private final Object LOCK = new Object();
    private boolean isUpdating = true;

    private UpdateTask task = new UpdateTask();

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        updateButton = (ImageView) findViewById(R.id.vl_update_button);
        console = (TextView) findViewById(R.id.vl_console_view);
        console.setMovementMethod(new ScrollingMovementMethod());
    }

    @Override
    protected void onResume()
    {
        super.onResume();
    }

    @Override
    protected void onPause()
    {
        super.onPause();
    }


    @Override
    protected int getLayoutId() {
        return R.layout.activity_view_logs;
    }

    @Override
    protected EditText findSearchEditText() {
        return (EditText) findViewById(R.id.vl_search_edit);
    }

    @Override
    protected ListView findListView() {
        return (ListView) findViewById(R.id.vl_list_view);
    }

    @Override
    protected CustomListAdapter<LogEntry> createListAdapter(LayoutInflater layoutInflater) {
        LogEntryAdapter adapter = new LogEntryAdapter(layoutInflater);
        adapter.registerDataSetObserver(new DataSetObserver() {
            /**
             * This method is called when the entire data set has changed,
             * most likely through a call to on a {@link Cursor}.
             */
            @Override
            public void onChanged() {
                super.onChanged();
                scrollToBottom();
            }
        });
        return adapter;
    }

    protected void scrollToBottom()
    {
        int count = theListView.getAdapter().getCount();
        theListView.setSelection(count-1);
    }

    @Override
    protected List<LogEntry> searchForResults(String searchText) throws Exception {

        List<LogEntry> entryList = new ArrayList<>();
        String regex = ".*(" + searchText + ").*";
        SpannableStringBuilder stringBuilder = new SpannableStringBuilder();

        try {
            Pattern.compile(regex);
            ViewUtil.markAsValid(theSearchEdit);
        } catch (PatternSyntaxException exception) {
            ViewUtil.markAsInvalid(theSearchEdit);
        }

        try {
            Process process = Runtime.getRuntime().exec("logcat -d");
            BufferedReader bufferedReader = new BufferedReader(
                    new InputStreamReader(process.getInputStream()));

            String line = "";
            while ((line = bufferedReader.readLine()) != null) {

                if (Text.isBlank(searchText) || line.matches(regex)) {
                    SpannableString content = massageLineContent(line);
                    stringBuilder.append(content);
                    stringBuilder.append("\n");
                }
            }

            console.setText(stringBuilder);
        } catch (IOException e) {
            Logger.error(e.getMessage(),e);
        }


        return entryList;
    }

    private static final String SPACE = "\\s+";
    private static final String TEXT = "([^\\s]+)";
    private static final String REGEX =
            TEXT + SPACE
        +   TEXT + SPACE
        +   TEXT + SPACE
        +   TEXT + SPACE
        +   TEXT + SPACE
        +   TEXT + "\\s*:" + SPACE
        +   "(.*)";

    private static final String VERBOSE = "V";
    private static final String DEBUG = "D";
    private static final String INFO = "I";
    private static final String WARNING = "W";
    private static final String ERROR = "E";
    private static final String ASSERT = "A";


    private static SpannableString massageLineContent(@NonNull String content)
    {
        return massageLineContent(content, false, false, false, false, false, true, true);
    }

    private static SpannableString massageLineContent(
        @NonNull String content,
        boolean showDate,
        boolean showTime,
        boolean showProcessId,
        boolean showThreadId,
        boolean showPackageName,
        boolean showLogLevel,
        boolean showTag
        )
    {
        SpannableStringBuilder newContent = new SpannableStringBuilder();

        Pattern pattern = Pattern.compile(REGEX);
        Matcher matcher = pattern.matcher(content);
        if (matcher.matches())
        {
            newContent.append((showDate)                ? matcher.group(0) + " "        : "");
            newContent.append((showTime)                ? matcher.group(1) + " "        : "");
            newContent.append((showProcessId)           ? matcher.group(2) + " "        : "");
            newContent.append((showThreadId)            ? matcher.group(3) + " "        : "");
            newContent.append((showPackageName)         ? matcher.group(4) + " "        : "");
            newContent.append((showLogLevel)            ? matcher.group(5) + " "        : "");
            newContent.append((showTag)                 ? matcher.group(6) + " "        : "");
            newContent.append(matcher.group(7));


            SpannableString output = ViewUtil.getColoredSpanned(newContent.toString(), ViewUtil.ERROR_TEXT_COLOR);
            switch(matcher.group(5))
            {
                case WARNING:
                    output = ViewUtil.getColoredSpanned(newContent.toString(), ViewUtil.WARNING_TEXT_COLOR);
                    break;
                case ERROR:
                case ASSERT:
                    output = ViewUtil.getColoredSpanned(newContent.toString(), ViewUtil.ERROR_TEXT_COLOR);
                    break;
                case INFO:
                    output = ViewUtil.getColoredSpanned(newContent.toString(), ViewUtil.INFO_TEXT_COLOR);
                    break;
                case VERBOSE:
                case DEBUG:
                default:
                    output = ViewUtil.getColoredSpanned(newContent.toString(), ViewUtil.NORMAL_TEXT_COLOR);
                    break;
            }
            return output;
        } else {
            return ViewUtil.getColoredSpanned(content.toString(), ViewUtil.INFO_TEXT_COLOR);
        }
    }

    private static final SpannableString toColor(int color, String text)
    {
        SpannableString str = new SpannableString(text);
        str.setSpan(new ForegroundColorSpan(color), 0, text.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        return str;
    }

    @Override
    protected List<LogEntry> getAllRecords() throws Exception {
        return searchForResults("");
    }


    /**
     * This is called before we set the initial search text, triggering a search for data.
     */
    @Override
    protected void loadAllData() {
        SharedPreferences sharedPref = getSharedPreferences(getClass().getSimpleName(), SHARED_PREF_MODE);
        boolean isUpdatingWanted = sharedPref.getBoolean(KEY_IS_UPDATING, false);
        long interval = sharedPref.getLong(KEY_UPDATING_INTERVAL, 1000l);

        synchronized (LOCK)
        {
            updateIntervalMs = interval;
            setUpdating(isUpdatingWanted);
        }
    }

    @Override
    protected void saveActivityState()
    {
        super.saveActivityState();
        synchronized (LOCK) {
            SharedPreferences sharedPref = getSharedPreferences(getClass().getSimpleName(), SHARED_PREF_MODE);
            SharedPreferences.Editor sharedPrefEditor = sharedPref.edit();
            sharedPrefEditor.putBoolean(KEY_IS_UPDATING, isUpdating);
            sharedPrefEditor.putLong(KEY_UPDATING_INTERVAL, updateIntervalMs);
            sharedPrefEditor.commit();
        }

    }

    private void setUpdating(boolean isUpdatingWanted)
    {
        synchronized (LOCK)
        {
            try {
                isUpdating = isUpdatingWanted;

                if (isUpdating) {
                    updateButton.setImageDrawable(getResources().getDrawable(R.drawable.ic_control_pause));
                    if (task.getStatus() != RUNNING)
                    {
                        task = new UpdateTask();
                        task.execute();
                    }
                } else {
                    updateButton.setImageDrawable(getResources().getDrawable(R.drawable.ic_control_play));
                    if (task.getStatus()==RUNNING)
                    {
                        task.cancel(true);
                    }
                }
            } catch (Exception e) {
                // Do nothing
            }
        }
    }

    private void toggleUpdateButton()
    {
        synchronized (LOCK)
        {
            setUpdating(!isUpdating);
        }
    }

    protected void onClickUpdateButton(View view)
    {
        toggleUpdateButton();
    }

    /**
     * Update Task used to periodically update the logs
     */
    private class UpdateTask extends AsyncTask {
        /**
         * Override this method to perform a computation on a background thread. The
         * specified parameters are the parameters passed to {@link #execute}
         * by the caller of this task.
         * <p>
         * This method can call {@link #publishProgress} to publish updates
         * on the UI thread.
         *
         * @param params The parameters of the task.
         * @return A result, defined by the subclass of this task.
         * @see #onPreExecute()
         * @see #onPostExecute
         * @see #publishProgress
         */
        @Override
        protected Object doInBackground(Object[] params) {
            try {
                while(true)
                {
                    publishProgress();
                    Thread.currentThread().sleep(updateIntervalMs);
                }
            } catch (Exception e) {
                return null;
            }
        }

        @Override
        protected void onProgressUpdate(Object... progress)
        {
            synchronized (LOCK) {
                if (isUpdating)
                {
                    searchForText();
                }
            }
        }
    };

    private class UpdateUiTask extends AsyncTask<String, SpannableStringBuilder, Boolean> {

        private SpannableStringBuilder stringBuilder = new SpannableStringBuilder();
        private final long publishDelay;

        public UpdateUiTask(long publishDelay)
        {
            this.publishDelay = publishDelay;
        }

        /**
         * Used to update the ui
         * @param values
         */
        protected void onProgressUpdate(SpannableStringBuilder... values) {
            if (values!=null) {
                console.setText(values[0]);
            }
        }

        /**
         * Override this method to perform a computation on a background thread. The
         * specified parameters are the parameters passed to {@link #execute}
         * by the caller of this task.
         * <p>
         * This method can call {@link #publishProgress} to publish updates
         * on the UI thread.
         *
         * @param params The parameters of the task.
         * @return A result, defined by the subclass of this task.
         * @see #onPreExecute()
         * @see #onPostExecute
         * @see #publishProgress
         */
        @Override
        protected Boolean doInBackground(String... params) {

            String searchText = params[0];
            String regex = ".*(" + searchText + ").*";

            try {
                Pattern.compile(regex);
                ViewUtil.markAsValid(theSearchEdit);
            } catch (PatternSyntaxException exception) {
                ViewUtil.markAsInvalid(theSearchEdit);
            }

            try {
                Process process = Runtime.getRuntime().exec("logcat");
                BufferedReader bufferedReader = new BufferedReader(
                        new InputStreamReader(process.getInputStream()));

                String line = "";
                Date lastEvent = new Date();
                while(true) {
                    if ((line = bufferedReader.readLine()) != null) {

                        if (Text.isBlank(searchText) || line.matches(regex)) {
                            SpannableString content = massageLineContent(line);

                            stringBuilder.append(content);
                            stringBuilder.append("\n");
                        }
                        Date newEvent = new Date();
                        if (newEvent.getTime() - lastEvent.getTime() >= publishDelay)
                        {
                            lastEvent = newEvent;
                            publishProgress(stringBuilder);
                        }
                    }
                }
            } catch (IOException e) {
                Logger.error(e.getMessage(),e);
                return Boolean.FALSE;
            }
        }
    }
}