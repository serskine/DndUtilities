package com.soupthatisthick.util.activity;

import android.app.ActionBar;
import android.app.Activity;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toolbar;

import com.soupthatisthick.util.Logger;
import com.soupthatisthick.encounterbuilder.util.adapter.CustomToggleAdapter;
import com.soupthatisthick.encounterbuilder.util.view.ViewUtil;
import com.soupthatisthick.util.dao.DaoMaster;

import java.util.ArrayList;
import java.util.List;

import soupthatisthick.encounterapp.R;

/**
 * Created by Owner on 4/12/2017.
 * Copyright of Stuart Marr Erskine, all rights reserved.
 */

public abstract class ViewToggleListActivity<DataType> extends AppActivity {

    protected ExpandableListView theExpandableListView;
    protected EditText theSearchEdit;
    protected Spinner theTableSpinner;
    protected CustomToggleAdapter<DataType> theListAdapter;
    private DaoMaster theDaoMaster;

    protected static final String KEY_SEARCH_TEXT = "KEY_SEARCH_TEXT";
    protected static final int SHARED_PREF_MODE = 0;
    protected static final int DEFAULT_MIN_SEARCH_TEXT_LENGTH = 0;

    protected abstract int getLayoutId();
    protected abstract EditText findSearchEditText();
    protected abstract ExpandableListView findExpandableListView();

    protected abstract CustomToggleAdapter<DataType> createToggleListAdapter(LayoutInflater layoutInflater);
    protected abstract List<DataType> searchForResults(String searchText) throws Exception;
    protected abstract List<DataType> getAllRecords() throws Exception;
    protected abstract void loadAllData();

    protected ActionBar theToolBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());

        theToolBar = getActionBar();


        // Initialize the edit text
        theSearchEdit = findSearchEditText();
        theSearchEdit.setOnEditorActionListener(
                new TextView.OnEditorActionListener() {
                    @Override
                    public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                        if  (   (actionId == EditorInfo.IME_ACTION_SEARCH)
                                ||  (   (event != null)
                                &&  (event.getKeyCode()==KeyEvent.KEYCODE_ENTER)
                        )
                                )
                        {
                            searchForText();
                            ViewUtil.hideInputWindow(ViewToggleListActivity.this);
                        }
                        return true;
                    }
                }
        );

        theSearchEdit.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                searchForText();
            }
        });

        theTableSpinner = (Spinner) findViewById(R.id.theTableSpinner);


        // Initialize the ListView and the Adapter
        theExpandableListView = findExpandableListView();
        theListAdapter = createToggleListAdapter(getLayoutInflater());
        theExpandableListView.setAdapter((ExpandableListAdapter) theListAdapter);
        // By default the list is empty

    }

    protected int getMinSearchTextLength() {
        return DEFAULT_MIN_SEARCH_TEXT_LENGTH;
    }

    @Override
    protected void onPause() {
        super.onPause();

        saveActivityState();
    }

    @Override
    protected void onResume() {
        super.onResume();

        loadAllData();
        loadSearchText();   // Load the last saved search in the shared preferences
        searchForText();    // Called to initialize the search results.

    }

    /**
     * This method will trigger a search based on the text contained within the search field.
     */
    protected final void searchForText() {
        final String searchText = theSearchEdit.getText().toString();
        final SearchTask task = new SearchTask();
        task.execute(searchText);
    }

    @SuppressWarnings("UnusedParameters")
    public void onClickSearchButton(View view)
    {
        searchForText();
    }

    public void onClickClearSearchTextButton(View view) {
        theSearchEdit.setText("");
    }

    /**
     * This will load data from the shared preferences
     */
    protected final void loadSearchText()
    {
        // Initialize the shared preferences;
        SharedPreferences sharedPref = getSharedPreferences(getClass().getSimpleName(), SHARED_PREF_MODE);
        theSearchEdit.setText(sharedPref.getString(KEY_SEARCH_TEXT, ""));
    }

    /**
     * Use this to save things into the shared preferences
     */
    protected final void saveActivityState()
    {
        SharedPreferences sharedPref = getSharedPreferences(getClass().getSimpleName(), SHARED_PREF_MODE);
        SharedPreferences.Editor sharedPrefEditor = sharedPref.edit();
        sharedPrefEditor.putString(KEY_SEARCH_TEXT, theSearchEdit.getText().toString());
        sharedPrefEditor.commit();
    }

    protected DaoMaster getDaoMaster() {
        return theDaoMaster;
    }

    protected void setDaoMaster(DaoMaster theDaoMaster) {
        this.theDaoMaster = theDaoMaster;
    }

    /**
     * This class will perform the tasks of searching the database for query results
     * and then updating the UI on completion.
     */
    private class SearchTask extends AsyncTask<String, Object, List<DataType>>
    {

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

        @Override
        protected void onPostExecute(List<DataType> results)
        {
            theExpandableListView.setAdapter((ExpandableListAdapter) theListAdapter);
            theListAdapter.setData(results);
        }
    }
}
