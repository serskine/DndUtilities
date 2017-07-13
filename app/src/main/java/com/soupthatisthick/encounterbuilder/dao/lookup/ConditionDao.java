package com.soupthatisthick.encounterbuilder.dao.lookup;

import android.content.ContentValues;
import android.database.Cursor;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.soupthatisthick.encounterbuilder.model.lookup.Condition;
import com.soupthatisthick.util.dao.DaoMaster;
import com.soupthatisthick.util.dao.WriteDao;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by Owner on 5/8/2017.
 * Copyright of Stuart Marr Erskine, all rights reserved.
 */

public class ConditionDao extends WriteDao<Condition> {

    public static final String TBL_CONDITION = "CONDITIONS";
    public static final String COL_ID = "id";
    public static final String COL_NAME = "name";
    public static final String COL_DESC = "description";

    public ConditionDao(DaoMaster daoMaster) throws IOException {
        super(daoMaster, TBL_CONDITION);
    }

    @Override
    public Condition readRecord(Cursor cursor) {
        Condition condition = new Condition();
        condition.setId(cursor.getLong(cursor.getColumnIndex(COL_ID)));
        condition.setName(cursor.getString(cursor.getColumnIndex(COL_NAME)));
        condition.setDescription(cursor.getString(cursor.getColumnIndex(COL_DESC)));
        return condition;
    }

    /**
     * This method will return all the columns that may be searched for matches in a text search.
     *
     * @return a Set of columns elidgible for searching with a like query
     */
    @Override
    public Set<String> getSearchableColumns() {
        Set<String> columns = new HashSet<>();
        columns.add(COL_NAME);
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
     * @param condition
     */
    @Override
    public ContentValues getContentValues(Condition condition) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_ID, condition.getId());
        contentValues.put(COL_NAME, condition.getName());
        contentValues.put(COL_DESC, condition.getDescription());
        return contentValues;
    }

    /**
     * This will create a new model and return it
     *
     * @return a new Record to update
     */
    @Override
    protected Condition createNewModel() {
        return new Condition();
    }

    @Override
    public String getIdColumn() {
        return COL_ID;
    }

    @Override
    public Long getId(@NonNull Condition condition) {
        return condition.getId();
    }

    @Override
    public void setId(@NonNull Condition condition, @Nullable Long id) {
        condition.setId(id);
    }
}
