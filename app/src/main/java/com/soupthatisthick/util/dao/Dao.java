package com.soupthatisthick.util.dao;

import android.database.Cursor;
import android.support.annotation.Nullable;

import java.io.IOException;
import java.util.List;
import java.util.Set;

/**
 * Created by Owner on 1/28/2017.
 * Copyright of Stuart Marr Erskine, all rights reserved.
 */

public interface Dao<Record> {
    Record readRecord(Cursor cursor);

    int countRecords() throws IOException;
    int countRecords(@Nullable String selection, @Nullable String[] args) throws IOException;

    List<Record> getAllRecords() throws IOException;
    List<Record> getRecords(@Nullable String selection, @Nullable String[] args) throws IOException;
    List<Record> getRecords(@Nullable String selection, @Nullable String[] args, @Nullable String groupBy, @Nullable String having, @Nullable String orderBy) throws Exception;

    String getTable();

    /**
     * This method will return all the columns that may be searched for matches in a text search.
     * @return a Set of columns elidgible for searching with a like query
     */
    Set<String> getSearchableColumns();

    /**
     * This is a list of columns that we will sort the results by when we return a dataset.
     * @return a List of columns. Sorting is first done by the first column then by the next column etc until all columns have been ordered.
     */
    List<String> getColumnSortingOrder();

    void logContents();
    void logSchema();
}
