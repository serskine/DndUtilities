package com.soupthatisthick.encounterbuilder.dao.lookup;

import android.content.ContentValues;
import android.database.Cursor;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.soupthatisthick.encounterbuilder.model.lookup.Feat;
import com.soupthatisthick.util.dao.DaoMaster;
import com.soupthatisthick.util.dao.WriteDao;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by Owner on 5/5/2017.
 * Copyright of Stuart Marr Erskine, all rights reserved.
 */

public class FeatDao extends WriteDao<Feat> {
    public static final String TBL_FEATS = "FEATS";
    public static final String COL_ID = "id";
    public static final String COL_NAME = "name";
    public static final String COL_PREREQ = "prerequisite";
    public static final String COL_DESC = "description";

    public FeatDao(DaoMaster daoMaster) throws IOException {
        super(daoMaster, TBL_FEATS);
    }

    @Override
    public Feat readRecord(Cursor cursor) {
        Feat feat = new Feat();
        feat.setId(cursor.getLong(cursor.getColumnIndex(COL_ID)));
        feat.setName(cursor.getString(cursor.getColumnIndex(COL_NAME)));
        feat.setPrerequisite(cursor.getString(cursor.getColumnIndex(COL_PREREQ)));
        feat.setDescription(cursor.getString(cursor.getColumnIndex(COL_DESC)));
        return feat;
    }

    /**
     * This method will return all the columns that may be searched for matches in a text search.
     *
     * @return a Set of columns eligible for searching with a like query
     */
    @Override
    public Set<String> getSearchableColumns() {
        Set<String> columns = new HashSet<>();
        columns.add(COL_NAME);
        columns.add(COL_PREREQ);
        return columns;
    }

    /**
     * This is a list of columns that we will sort the results by when we return a dataset.
     *
     * @return a List of columns. Sorting is first done by the first column then by the next column etc until all columns have been ordered.
     */
    @Override
    public List<String> getColumnSortingOrder() {
        ArrayList<String> order = new ArrayList<String>();
        order.add(COL_NAME);
        return order;
    }

    /**
     * This will build a mapping of column names to values from the specified record
     * so that they may be inserted.
     *
     * @param feat
     */
    @Override
    public ContentValues getContentValues(Feat feat) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_ID, feat.getId());
        contentValues.put(COL_NAME, feat.getName());
        contentValues.put(COL_PREREQ, feat.getPrerequisite());
        contentValues.put(COL_DESC, feat.getDescription());
        return contentValues;
    }

    /**
     * This will create a new model and return it
     *
     * @return a new Record to update
     */
    @Override
    protected Feat createNewModel() {
        return new Feat();
    }

    @Override
    public String getIdColumn() {
        return COL_ID;
    }

    @Override
    public Long getId(@NonNull Feat feat) {
        return feat.getId();
    }

    @Override
    public void setId(@NonNull Feat feat, @Nullable Long id) {
        feat.setId(id);
    }
}
