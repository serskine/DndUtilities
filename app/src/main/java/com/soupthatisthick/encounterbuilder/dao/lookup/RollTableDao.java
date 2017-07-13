package com.soupthatisthick.encounterbuilder.dao.lookup;

import android.content.ContentValues;
import android.database.Cursor;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.soupthatisthick.encounterbuilder.model.lookup.RollTable;
import com.soupthatisthick.util.dao.DaoMaster;
import com.soupthatisthick.util.dao.WriteDao;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by Owner on 5/9/2017.
 * Copyright of Stuart Marr Erskine, all rights reserved.
 */

public class RollTableDao extends WriteDao<RollTable> {

    public static final String TBL_ROLL_TABLE = "ROLL_TABLE";
    public static final String COL_ID = "id";
    public static final String COL_NAME = "name";
    public static final String COL_DIE_QTY = "dieQty";
    public static final String COL_DIE_SIZE = "dieSize";

    public RollTableDao(DaoMaster daoMaster) throws IOException {
        super(daoMaster, TBL_ROLL_TABLE);
    }

    @Override
    public RollTable readRecord(Cursor cursor) {
        RollTable rollTable = new RollTable();
        rollTable.setId(cursor.getLong(cursor.getColumnIndex(COL_ID)));
        rollTable.setName(cursor.getString(cursor.getColumnIndex(COL_NAME)));
        rollTable.setDieCount(cursor.getInt(cursor.getColumnIndex(COL_DIE_QTY)));
        rollTable.setDieSize(cursor.getInt(cursor.getColumnIndex(COL_DIE_SIZE)));
        return rollTable;
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
        List<String> order = new ArrayList<>();
        order.add(COL_NAME);
        return order;
    }

    /**
     * This will build a mapping of column names to values from the specified record
     * so that they may be inserted.
     *
     * @param rollTable
     */
    @Override
    public ContentValues getContentValues(RollTable rollTable) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_ID, rollTable.getId());
        contentValues.put(COL_NAME, rollTable.getName());
        contentValues.put(COL_DIE_QTY, rollTable.getDieCount());
        contentValues.put(COL_DIE_SIZE, rollTable.getDieSize());
        return contentValues;
    }

    /**
     * This will create a new model and return it
     *
     * @return a new Record to update
     */
    @Override
    protected RollTable createNewModel() {
        return new RollTable();
    }

    @Override
    public String getIdColumn() {
        return COL_ID;
    }

    @Override
    public Long getId(@NonNull RollTable rollTable) {
        return rollTable.getId();
    }

    @Override
    public void setId(@NonNull RollTable rollTable, @Nullable Long id) {
        rollTable.setId(id);
    }
}
