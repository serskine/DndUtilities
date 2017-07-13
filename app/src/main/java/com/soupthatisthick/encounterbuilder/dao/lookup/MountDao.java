package com.soupthatisthick.encounterbuilder.dao.lookup;

import android.content.ContentValues;
import android.database.Cursor;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.soupthatisthick.encounterbuilder.model.lookup.Mount;
import com.soupthatisthick.util.dao.DaoMaster;
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

public class MountDao extends WriteDao<Mount> {

    public static final String TBL_MOUNTS = "MOUNTS";
    public static final String COL_ID = "id";
    public static final String COL_NAME = "name";
    public static final String COL_COST = "cost";
    public static final String COL_SPEED = "speed";
    public static final String COL_CARRY = "carry";
    public static final String COL_DESC = "description";

    public MountDao(DaoMaster daoMaster) throws IOException {
        super(daoMaster, TBL_MOUNTS);
    }

    @Override
    public Mount readRecord(Cursor cursor) {
        Mount mount = new Mount();
        mount.setId(cursor.getLong(cursor.getColumnIndex(COL_ID)));
        mount.setName(cursor.getString(cursor.getColumnIndex(COL_NAME)));
        mount.setCost(cursor.getString(cursor.getColumnIndex(COL_COST)));
        mount.setSpeed(cursor.getString(cursor.getColumnIndex(COL_SPEED)));
        mount.setCarry(cursor.getString(cursor.getColumnIndex(COL_CARRY)));
        mount.setDescription(cursor.getString(cursor.getColumnIndex(COL_DESC)));
        return mount;
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
     * @param mount
     */
    @Override
    public ContentValues getContentValues(Mount mount) {
        ContentValues content = new ContentValues();
        content.put(COL_ID, mount.getId());
        content.put(COL_CARRY, mount.getCarry());
        content.put(COL_DESC, mount.getDescription());
        content.put(COL_NAME, mount.getName());
        content.put(COL_SPEED, mount.getSpeed());
        content.put(COL_COST, mount.getCost());
        return content;
    }

    /**
     * This will create a new model and return it
     *
     * @return a new Record to update
     */
    @Override
    protected Mount createNewModel() {
        return new Mount();
    }

    @Override
    public String getIdColumn() {
        return COL_ID;
    }

    @Override
    public Long getId(@NonNull Mount mount) {
        return mount.getId();
    }

    @Override
    public void setId(@NonNull Mount mount, @Nullable Long id) {
        mount.setId(id);
    }
}
