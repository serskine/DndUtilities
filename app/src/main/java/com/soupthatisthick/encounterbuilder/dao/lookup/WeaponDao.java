package com.soupthatisthick.encounterbuilder.dao.lookup;

import android.content.ContentValues;
import android.database.Cursor;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.soupthatisthick.encounterbuilder.model.lookup.Weapon;
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

public class WeaponDao extends WriteDao<Weapon> {

    public static final String TBL_WEAPONS = "WEAPONS";
    public static final String COL_ID = "id";
    public static final String COL_NAME = "name";
    public static final String COL_COST = "cost";
    public static final String COL_DAMAGE = "damage";
    public static final String COL_WEIGHT = "weight";
    public static final String COL_PROPERTIES = "properties";
    public static final String COL_TYPE = "type";
    public static final String COL_DESCRIPTION = "description";


    public WeaponDao(DaoMaster daoMaster) throws IOException {
        super(daoMaster, TBL_WEAPONS);
    }

    @Override
    public Weapon readRecord(Cursor cursor) {
        Weapon weapon = new Weapon();

        weapon.setId(cursor.getLong(cursor.getColumnIndex(COL_ID)));
        weapon.setName(cursor.getString(cursor.getColumnIndex(COL_NAME)));
        weapon.setCost(cursor.getString(cursor.getColumnIndex(COL_COST)));
        weapon.setDamage(cursor.getString(cursor.getColumnIndex(COL_DAMAGE)));
        weapon.setWeight(cursor.getString(cursor.getColumnIndex(COL_WEIGHT)));
        weapon.setProperties(cursor.getString(cursor.getColumnIndex(COL_PROPERTIES)));
        weapon.setType(cursor.getString(cursor.getColumnIndex(COL_TYPE)));
        weapon.setDescription(cursor.getString(cursor.getColumnIndex(COL_DESCRIPTION)));

        return weapon;
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
        columns.add(COL_PROPERTIES);
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
        ArrayList<String> order = new ArrayList<>();
        order.add(COL_NAME);
        order.add(COL_TYPE);
        return order;
    }

    /**
     * This will build a mapping of column names to values from the specified record
     * so that they may be inserted.
     *
     * @param weapon
     */
    @Override
    public ContentValues getContentValues(Weapon weapon) {
        ContentValues content = new ContentValues();

        content.put(COL_ID, weapon.getId());
        content.put(COL_NAME, weapon.getName());
        content.put(COL_COST, weapon.getCost());
        content.put(COL_DAMAGE, weapon.getDamage());
        content.put(COL_DESCRIPTION, weapon.getDescription());
        content.put(COL_PROPERTIES, weapon.getProperties());
        content.put(COL_TYPE, weapon.getType());
        content.put(COL_WEIGHT, weapon.getWeight());

        return content;
    }

    /**
     * This will create a new model and return it
     *
     * @return a new Record to update
     */
    @Override
    protected Weapon createNewModel() {
        return new Weapon();
    }

    @Override
    public String getIdColumn() {
        return COL_ID;
    }

    @Override
    public Long getId(@NonNull Weapon weapon) {
        return weapon.getId();
    }

    @Override
    public void setId(@NonNull Weapon weapon, @Nullable Long id) {
        weapon.setId(id);
    }
}
