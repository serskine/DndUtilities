package com.soupthatisthick.encounterbuilder.dao.lookup;

import android.content.ContentValues;
import android.database.Cursor;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.soupthatisthick.encounterbuilder.model.lookup.God;
import com.soupthatisthick.util.dao.DaoMaster;
import com.soupthatisthick.util.dao.ReadDao;
import com.soupthatisthick.util.dao.WriteDao;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by Owner on 5/25/2017.
 * Copyright of Stuart Marr Erskine, all rights reserved.
 */

public class GodsDao extends WriteDao<God> {

    public static final String TBL_GODS = "GODS";
    public static final String COL_ID = "id";
    public static final String COL_NAME = "name";
    public static final String COL_ALIGNMENT = "alignment";
    public static final String COL_DOMAINS = "domains";
    public static final String COL_SYMBOL = "symbol";
    public static final String COL_SOURCE = "source";
    public static final String COL_NOTES = "notes";

    public GodsDao(DaoMaster daoMaster) throws IOException {
        super(daoMaster, TBL_GODS);
    }

    @Override
    public God readRecord(Cursor cursor) {
        God god = new God();
        god.setId(cursor.getLong(cursor.getColumnIndex(COL_ID)));
        god.setAlignment(cursor.getString(cursor.getColumnIndex(COL_ALIGNMENT)));
        god.setDomains(cursor.getString(cursor.getColumnIndex(COL_DOMAINS)));
        god.setName(cursor.getString(cursor.getColumnIndex(COL_NAME)));
        god.setNotes(cursor.getString(cursor.getColumnIndex(COL_NOTES)));
        god.setSource(cursor.getString(cursor.getColumnIndex(COL_SOURCE)));
        god.setSymbol(cursor.getString(cursor.getColumnIndex(COL_SYMBOL)));
        return god;
    }

    /**
     * This method will return all the columns that may be searched for matches in a text search.
     *
     * @return a Set of columns elidgible for searching with a like query
     */
    @Override
    public Set<String> getSearchableColumns() {
        HashSet<String> columns = new HashSet<>();
        columns.add(COL_NAME);
        columns.add(COL_ALIGNMENT);
        columns.add(COL_DOMAINS);
        columns.add(COL_SOURCE);
        columns.add(COL_NOTES);
        return columns;
    }

    /**
     * This is a list of columns that we will sort the results by when we return a dataset.
     *
     * @return a List of columns. Sorting is first done by the first column then by the next column etc until all columns have been ordered.
     */
    @Override
    public List<String> getColumnSortingOrder() {
        ArrayList<String> order = new ArrayList<>();
        order.add(COL_NAME);
        return order;
    }

    /**
     * This will build a mapping of column names to values from the specified record
     * so that they may be inserted.
     *
     * @param god
     */
    @Override
    public ContentValues getContentValues(God god) {
        ContentValues content = new ContentValues();
        content.put(COL_ID, god.getId());
        content.put(COL_NAME, god.getName());
        content.put(COL_ALIGNMENT, god.getAlignment());
        content.put(COL_DOMAINS, god.getDomains());
        content.put(COL_NOTES, god.getNotes());
        content.put(COL_SOURCE, god.getSource());
        content.put(COL_SYMBOL, god.getSymbol());
        return content;
    }

    /**
     * This will create a new model and return it
     *
     * @return a new Record to update
     */
    @Override
    protected God createNewModel() {
        return new God();
    }

    @Override
    public String getIdColumn() {
        return COL_ID;
    }

    @Override
    public Long getId(@NonNull God god) {
        return god.getId();
    }

    @Override
    public void setId(@NonNull God god, @Nullable Long id) {
        god.setId(id);
    }
}
