package com.soupthatisthick.encounterbuilder.dao.lookup;

import android.content.ContentValues;
import android.database.Cursor;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.soupthatisthick.encounterbuilder.model.lookup.Equipment;
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

public class EquipmentDao extends WriteDao<Equipment> {

    public static final String TBL_EQUIPMNT = "EQUIPMENT";
    public static final String COL_ID = "id";
    public static final String COL_COST = "cost";
    public static final String COL_NAME = "name";
    public static final String COL_WEIGHT = "weight";
    public static final String COL_DESCRIPTION = "description";
    public static final String COL_TYPE = "type";

    public EquipmentDao(DaoMaster daoMaster) throws IOException {
        super(daoMaster, TBL_EQUIPMNT);
    }

    @Override
    public Equipment readRecord(Cursor cursor) {
        Equipment equipment = new Equipment();
        equipment.setId(cursor.getLong(cursor.getColumnIndex(COL_ID)));
        equipment.setCost(cursor.getString(cursor.getColumnIndex(COL_COST)));
        equipment.setDescription(cursor.getString(cursor.getColumnIndex(COL_DESCRIPTION)));
        equipment.setName(cursor.getString(cursor.getColumnIndex(COL_NAME)));
        equipment.setType(cursor.getString(cursor.getColumnIndex(COL_TYPE)));
        equipment.setWeight(cursor.getString(cursor.getColumnIndex(COL_WEIGHT)));
        return equipment;
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
        columns.add(COL_TYPE);
        return columns;
    }

    /**
     * This is a list of columns that we will sort the results by when we return a dataset.
     *
     * @return a List of columns. Sorting is first done by the first column then by the next column etc until all columns have been ordered.
     */
    @Override
    public List<String> getColumnSortingOrder() {
        ArrayList<String> columns = new ArrayList<>();
        columns.add(COL_NAME);
        columns.add(COL_TYPE);
        return columns;
    }

    /**
     * This will build a mapping of column names to values from the specified record
     * so that they may be inserted.
     *
     * @param equipment
     */
    @Override
    public ContentValues getContentValues(Equipment equipment) {
        ContentValues contentValues = new ContentValues();

        contentValues.put(COL_ID, equipment.getId());
        contentValues.put(COL_NAME, equipment.getName());
        contentValues.put(COL_COST, equipment.getCost());
        contentValues.put(COL_TYPE, equipment.getType());
        contentValues.put(COL_WEIGHT, equipment.getWeight());
        contentValues.put(COL_DESCRIPTION, equipment.getDescription());

        return contentValues;
    }

    /**
     * This will create a new model and return it
     *
     * @return a new Record to update
     */
    @Override
    protected Equipment createNewModel() {
        return new Equipment();
    }

    @Override
    public String getIdColumn() {
        return COL_ID;
    }

    @Override
    public Long getId(@NonNull Equipment equipment) {
        return equipment.getId();
    }

    @Override
    public void setId(@NonNull Equipment equipment, @Nullable Long id) {
        equipment.setId(id);
    }
}
