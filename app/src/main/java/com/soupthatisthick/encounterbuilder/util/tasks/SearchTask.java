package com.soupthatisthick.encounterbuilder.util.tasks;

/**
 * Created by Owner on 6/22/2017.
 * Copyright of Stuart Marr Erskine, all rights reserved.
 */


import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.widget.ExpandableListAdapter;

import com.soupthatisthick.util.Logger;

import java.util.ArrayList;
import java.util.List;

/**
 * This class will perform the tasks of searching the database for query results
 * and then updating the UI on completion.
 */
public abstract class SearchTask<DataType> extends AsyncTask<String, Object, List<DataType>>
{
    protected abstract int getMinSearchTextLength();
    protected abstract List<DataType> searchForResults(String searchText) throws Exception;
    protected abstract void onSearchCompleted(List<DataType> results);

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
    protected List<DataType> doInBackground(String... params) {
        if (params.length<1) {
            return new ArrayList<>();
        } else {
            String searchText = (String) params[0];
            List<DataType> results;

            Logger.info("SEARCHING FOR '" + searchText + "'");

            if (searchText.length() >= getMinSearchTextLength()) {
                try {
                    results = searchForResults(searchText);
                } catch (Exception e) {
                    Logger.error("Could not get results for search text '" + searchText + "'", e);
                    results = new ArrayList<>();
                }
            } else {
                results = new ArrayList<>();
                Logger.info("Search text must be at least " + getMinSearchTextLength() + " characters.");
            }
            return results;
        }
    }

    @Override
    protected final void onPostExecute(List<DataType> results)
    {
        onSearchCompleted(results);
    }
}
