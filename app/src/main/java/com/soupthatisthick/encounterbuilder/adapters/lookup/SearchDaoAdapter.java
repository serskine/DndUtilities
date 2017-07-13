package com.soupthatisthick.encounterbuilder.adapters.lookup;

import android.view.LayoutInflater;

import com.soupthatisthick.util.Logger;
import com.soupthatisthick.encounterbuilder.util.adapter.CustomListAdapter;
import com.soupthatisthick.util.dao.ReadDao;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Owner on 3/30/2017.
 * Copyright of Stuart Marr Erskine, all rights reserved.
 */

public abstract class SearchDaoAdapter<Record> extends CustomListAdapter<Record> {

    protected ReadDao<Record> readDao;
    private String searchText = "", lastSearch = null;

    public final ReadDao<Record> getReadDao()
    {
        return readDao;
    }

    /**
     * This will create a new adapter for viewing all the contents of a table given a search
     * query.
     * @param inflater
     * @param dao
     */
    public SearchDaoAdapter(LayoutInflater inflater, ReadDao<Record> dao) {
        super(inflater);
        this.readDao = dao;
        search();
    }

    /**
     * This method is used to search for the given search text
     * @return
     */
    protected final void search() {
        if (lastSearch==null || !lastSearch.equals(searchText)) {
            List<Record> results = new ArrayList<>();
            lastSearch = searchText;
            final String searchText = getSearchText();

            if (searchText.length() > 0) {
                try {
                    results = getReadDao().getRecordsLike(searchText);
                } catch (IOException e) {
                    Logger.error("Could not get records like '" + searchText + "'", e);
                    results = getResultsForErrorDuringSearch();
                }
            } else {
                try {
                    results = getReadDao().getAllRecords();
                } catch (IOException e) {
                    Logger.error("Could not get all records.", e);
                    results = getResultsForErrorDuringSearch();
                }
            }
            setData(results);
        }
    }

    /**
     * This method is called if there is an error during a search
     * @return
     */
    protected List<Record> getResultsForErrorDuringSearch()
    {
        return new ArrayList<>();
    }

    /**
     * This is used as the default list of items for search queries with no content in them.
     * @return an empty list by default
     * @throws IOException
     */
    protected List<Record> getResultsForEmptySearch() throws IOException
    {
        return new ArrayList<>();
    }


    /**
     * Use this method to return the text that the adapter last searched for
     * @return
     */
    public final String getSearchText() {
        return searchText;
    }

    /**
     * This will change the search text.
     *
     * @param searchText
     */
    public void setSearchText(String searchText) {
        searchText = (searchText==null) ? "" : searchText;
        if (!this.searchText.equals(searchText)) {
            this.lastSearch = null;
            this.searchText = searchText;
            search();
        }
    }
}
