package com.soupthatisthick.encounterbuilder.dao.lookup;

import android.content.ContentValues;
import android.database.Cursor;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.soupthatisthick.encounterbuilder.model.lookup.SearchFilter;
import com.soupthatisthick.util.dao.DaoMaster;
import com.soupthatisthick.util.dao.WriteDao;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

/**
 * Created by Owner on 5/4/2017.
 * Copyright of Stuart Marr Erskine, all rights reserved.
 */

public class SearchFilterDao extends WriteDao<SearchFilter> {
    public static final String TBL_MY_TABLE = TBL_DAO_SEARCHABLE;
    public static final String COL_ID = "id";
    public static final String COL_TABLE = "table";
    public static final String COL_COLUMN = "column";

    public SearchFilterDao(DaoMaster daoMaster) throws IOException {
        super(daoMaster, TBL_MY_TABLE);
    }

    @Override
    public SearchFilter readRecord(Cursor cursor) {
        SearchFilter searchFilter = new SearchFilter();
        searchFilter.setId(cursor.getLong(cursor.getColumnIndex(COL_ID)));
        searchFilter.setTable(cursor.getString(cursor.getColumnIndex(COL_TABLE)));
        searchFilter.setColumn(cursor.getString(cursor.getColumnIndex(COL_COLUMN)));
        return searchFilter;
    }

    /**
     * This method will return all the columns that may be searched for matches in a text search.
     *
     * @return a Set of columns elidgible for searching with a like query
     */
    @Override
    public Set<String> getSearchableColumns() {
        Set<String> columns = new TreeSet<>();
        columns.add(COL_TABLE);
        columns.add(COL_COLUMN);
        return columns;
    }

    /**
     * This is a list of columns that we will sort the results by when we return a dataset.
     *
     * @return a List of columns. Sorting is first done by the first column then by the next column etc until all columns have been ordered.
     */
    @Override
    public List<String> getColumnSortingOrder() {
        List<String> order = new ArrayList<>();
        order.add(COL_TABLE);
        order.add(COL_COLUMN);
        return order;
    }

    /**
     * This will build a mapping of column names to values from the specified record
     * so that they may be inserted.
     *
     * @param searchFilter
     */
    @Override
    public ContentValues getContentValues(SearchFilter searchFilter) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_ID, searchFilter.getId());
        contentValues.put(COL_TABLE, searchFilter.getTable());
        contentValues.put(COL_COLUMN, searchFilter.getColumn());
        return contentValues;
    }

    /**
     * This will create a new model and return it
     *
     * @return a new Record to update
     */
    @Override
    protected SearchFilter createNewModel() {
        return new SearchFilter();
    }

    @Override
    public String getIdColumn() {
        return COL_ID;
    }

    @Override
    public Long getId(@NonNull SearchFilter searchFilter) {
        return searchFilter.getId();
    }

    @Override
    public void setId(@NonNull SearchFilter searchFilter, @Nullable Long id) {
        searchFilter.setId(id);
    }
}
