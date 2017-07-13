package com.soupthatisthick.encounterbuilder.dao.builder;

import android.content.ContentValues;
import android.database.Cursor;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.soupthatisthick.encounterbuilder.model.builder.EncounterMast;
import com.soupthatisthick.util.dao.DaoMaster;
import com.soupthatisthick.util.dao.WriteDao;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by Owner on 6/20/2017.
 * Copyright of Stuart Marr Erskine, all rights reserved.
 */

public class EncounterMastDao extends WriteDao<EncounterMast> {

    private static final String TBL_ENCOUNTERS = "ENCOUNTERS";
    private static final String COL_ID = "id";
    private static final String COL_NAME = "name";

    public EncounterMastDao(DaoMaster daoMaster) throws IOException {
        super(daoMaster, TBL_ENCOUNTERS);
    }

    @Override
    public EncounterMast readRecord(Cursor cursor) {
        EncounterMast mast = new EncounterMast();
        mast.setId(cursor.getLong(cursor.getColumnIndex(COL_ID)));
        mast.setName(cursor.getString(cursor.getColumnIndex(COL_NAME)));
        return mast;
    }

    /**
     * This method will return all the columns that may be searched for matches in a text search.
     *
     * @return a Set of columns elidgible for searching with a like query
     */
    @Override
    public Set<String> getSearchableColumns() {
        Set<String> cols = new HashSet<>();
        cols.add(COL_NAME);
        return cols;
    }

    /**
     * This is a list of columns that we will sort the results by when we return a dataset.
     *
     * @return a List of columns. Sorting is first done by the first column then by the next column etc until all columns have been ordered.
     */
    @Override
    public List<String> getColumnSortingOrder() {
        List<String> order = new ArrayList<>();
        order.add(COL_NAME);
        return order;
    }

    /**
     * This will build a mapping of column names to values from the specified record
     * so that they may be inserted.
     *
     * @param encounterMast
     */
    @Override
    public ContentValues getContentValues(EncounterMast encounterMast) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_ID, encounterMast.getId());
        contentValues.put(COL_NAME, encounterMast.getName());
        return contentValues;
    }

    /**
     * This will create a new model and return it
     *
     * @return a new Record to update
     */
    @Override
    protected EncounterMast createNewModel() {
        return new EncounterMast();
    }

    @Override
    public String getIdColumn() {
        return COL_ID;
    }

    @Override
    public Long getId(@NonNull EncounterMast encounterMast) {
        return encounterMast.getId();
    }

    @Override
    public void setId(@NonNull EncounterMast encounterMast, @Nullable Long id) {
        encounterMast.setId(id);
    }
}
