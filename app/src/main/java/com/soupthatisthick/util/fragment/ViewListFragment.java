package com.soupthatisthick.util.fragment;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.CallSuper;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.soupthatisthick.encounterbuilder.util.adapter.CustomListAdapter;
import com.soupthatisthick.encounterbuilder.util.view.ViewUtil;
import com.soupthatisthick.util.Logger;
import com.soupthatisthick.util.activity.ViewListActivity;
import com.soupthatisthick.util.dao.DaoMaster;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Owner on 5/31/2017.
 * Copyright of Stuart Marr Erskine, all rights reserved.
 */

public abstract class ViewListFragment<DataType> extends AppFragment
{

    public static final String TAG = ViewListActivity.class.getSimpleName();

    protected ListView theListView;
    protected EditText theSearchEdit;
    protected CustomListAdapter<DataType> theListAdapter;
    protected DaoMaster db;

    protected static final String KEY_SEARCH_TEXT = "KEY_SEARCH_TEXT";
    protected static final int SHARED_PREF_MODE = 0;
    protected static final int DEFAULT_MIN_SEARCH_TEXT_LENGTH = 0;

    protected abstract int getLayoutId();
    protected abstract EditText findSearchEditText(View root);
    protected abstract ListView findListView(View root);

    @Override
    @CallSuper
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(getLayoutId(), container);

        // Initialize the edit text
        theSearchEdit = findSearchEditText(view);
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
                            hideSoftKeyboard();
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

        // Initialize the ListView and the Adapter
        theListView = findListView(view);
        theListAdapter = createListAdapter(inflater);
        theListView.setAdapter(theListAdapter);
        // By default the list is empty


        return view;
    }

    protected abstract CustomListAdapter<DataType> createListAdapter(LayoutInflater layoutInflater);
    protected abstract List<DataType> searchForResults(String searchText) throws Exception;
    protected abstract List<DataType> getAllRecords() throws Exception;
    protected int getMinSearchTextLength() {
        return DEFAULT_MIN_SEARCH_TEXT_LENGTH;
    }

    @Override
    public void onPause() {
        super.onPause();

        saveActivityState();
    }

    @Override
    public void onResume() {
        super.onResume();

        loadAllData();
        loadSearchText();   // Load the last saved search in the shared preferences
        searchForText();    // Called to initialize the search results.

    }

    /**
     * This is called before we set the initial search text, triggering a search for data.
     */
    protected abstract void loadAllData();

    /**
     * This method will trigger a search based on the text contained within the search field.
     */
    protected final void searchForText() {
        String searchText = theSearchEdit.getText().toString();
        List<DataType> results;

        if (searchText.length() >= getMinSearchTextLength()) {
            try {
                results = searchForResults(searchText);
            } catch (Exception e) {
                Logger.error("Could not get results for search text '" + searchText + "'", e);
                results = new ArrayList<>();
            }
        } else {
            results = new ArrayList<>();
            Logger.warning("Search text must be at least " + getMinSearchTextLength() + " characters.");
        }

        theListView.setAdapter(theListAdapter);
        theListAdapter.setData(results);
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
    @CallSuper
    protected void loadSearchText()
    {
        // Initialize the shared preferences;
        SharedPreferences sharedPref = getActivity().getSharedPreferences(getClass().getSimpleName(), SHARED_PREF_MODE);
        theSearchEdit.setText(sharedPref.getString(KEY_SEARCH_TEXT, ""));
    }

    /**
     * Use this to save things into the shared preferences
     */
    @CallSuper
    protected void saveActivityState()
    {
        SharedPreferences sharedPref = getActivity().getSharedPreferences(getClass().getSimpleName(), SHARED_PREF_MODE);
        SharedPreferences.Editor sharedPrefEditor = sharedPref.edit();
        sharedPrefEditor.putString(KEY_SEARCH_TEXT, theSearchEdit.getText().toString());
        sharedPrefEditor.commit();
    }

}
