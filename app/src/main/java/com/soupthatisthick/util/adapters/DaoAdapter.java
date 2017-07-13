package com.soupthatisthick.util.adapters;

import android.support.annotation.NonNull;
import android.support.test.internal.runner.junit4.AndroidAnnotatedBuilder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ListAdapter;
import android.widget.SpinnerAdapter;

import com.soupthatisthick.encounterbuilder.util.adapter.CustomListAdapter;
import com.soupthatisthick.util.Logger;
import com.soupthatisthick.util.dao.ReadDao;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Owner on 6/21/2017.
 * Copyright of Stuart Marr Erskine, all rights reserved.
 */

public abstract class DaoAdapter<Record> implements Filterable
{
    private ReadDao<Record> readDao;
    private int minSearchTextLength = 0;
    private String searchText = "";
    private final Filter filter = new DaoFilter();

    /**
     * This method must be overwritten to change the data in the adapter.
     * Once called it must notify all the observers that the dataset has changed/
     * @param records is the new dataset in the adapter
     */
    public abstract void setData(List<Record> records);

    /**
     * This will set the query used to determine the data set
     * @param searchText
     */
    public final void setQuery(String searchText)
    {
        this.searchText = searchText;
        searchForText();
    }

    /**
     * This method causes us to update our results
     */
    protected final void searchForText()
    {
        List<Record> results;

        try {
            if (readDao==null) {
                throw new RuntimeException("The DAO to query results from is null. Returning an empty result set.");
            }
            if (searchText==null || searchText.length() < getMinSearchTextLength()) {
                throw new RuntimeException("Search text must be at least " + getMinSearchTextLength() + " characters.");
            }
            results = searchForResults(searchText);

        } catch (Exception e) {
            results = new ArrayList<>();
            Logger.warning("Could not get results for search text '" + searchText + "'");
        }

        setData(results);   // Set the data. This method once called should notify observers as well.
    }

    /**
     * This method will determine what the minimum number of characters are required to return any results in the search
     * @return
     */
    public final int getMinSearchTextLength() {
        return minSearchTextLength;
    }

    /**
     * This will set the minimum number of characters required to return further results in the search text
     * @param length
     */
    public final void setMinSearchTextLength(int length) {
        this.minSearchTextLength = length;
    }


    /**
     * This method is what performs the search on the dao
     * @param searchText
     * @return
     * @throws IOException
     */
    private final List<Record> searchForResults(@NonNull String searchText) throws IOException {
        return readDao.getRecordsLike(searchText);
    }

    /**
     * This will change the read dao the we will query for results. Changing this will NOT change the existing dataset.
     * @param readDao
     */
    public final void setReadDao(ReadDao<Record> readDao) {
        this.readDao = readDao;
    }

    /**
     * <p>Returns a filter that can be used to constrain data with a filtering
     * pattern.</p>
     * <p>
     * <p>This method is usually implemented by {@link Adapter}
     * classes.</p>
     *
     * @return a filter used to constrain data
     */
    @Override
    public Filter getFilter() {
        return filter;
    }

    /**
     * This class will apply the filter to the data set
     */
    private class DaoFilter extends Filter
    {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            FilterResults results = new FilterResults();
            List<Record> items = new ArrayList<>();
            if (constraint!=null) {
                try {
                    items = readDao.getRecordsLike(constraint.toString());
                } catch (Exception e) {
                    Logger.error("Failed to filter results", e);
                    items = new ArrayList<>();
                }
            }
            results.values = items;
            results.count = items.size();
            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            List<Record> data = (List<Record>) results.values;
            setData(data);
        }
    }
}


