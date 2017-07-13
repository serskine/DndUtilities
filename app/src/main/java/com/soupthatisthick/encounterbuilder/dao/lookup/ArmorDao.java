package com.soupthatisthick.encounterbuilder.dao.lookup;

import android.content.ContentValues;
import android.database.Cursor;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.soupthatisthick.encounterbuilder.model.lookup.Armor;
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

public class ArmorDao extends WriteDao<Armor> {

    public static final String TBL_ARMOR = "ARMOR";
    public static final String COL_ID = "id";
    public static final String COL_NAME = "name";
    public static final String COL_COST = "cost";
    public static final String COL_AC = "ac";
    public static final String COL_STRENGTH = "strength";
    public static final String COL_STEALTH = "stealth";
    public static final String COL_WEIGHT = "weight";
    public static final String COL_TYPE = "type";
    public static final String COL_DESC = "description";

    public ArmorDao(DaoMaster daoMaster) throws IOException {
        super(daoMaster, TBL_ARMOR);
    }

    @Override
    public Armor readRecord(Cursor cursor) {
        Armor armor = new Armor();

        armor.setId(cursor.getLong(cursor.getColumnIndex(COL_ID)));
        armor.setName(cursor.getString(cursor.getColumnIndex(COL_NAME)));
        armor.setCost(cursor.getString(cursor.getColumnIndex(COL_COST)));
        armor.setAc(cursor.getString(cursor.getColumnIndex(COL_AC)));
        armor.setStrengthRequirement(cursor.getString(cursor.getColumnIndex(COL_STRENGTH)));
        armor.setStealthEffect(cursor.getString(cursor.getColumnIndex(COL_STEALTH)));
        armor.setWeight(cursor.getString(cursor.getColumnIndex(COL_WEIGHT)));
        armor.setType(cursor.getString(cursor.getColumnIndex(COL_TYPE)));
        armor.setDescription(cursor.getString(cursor.getColumnIndex(COL_DESC)));

        return armor;
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
        columns.add(COL_STEALTH);
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
     * @param armor
     */
    @Override
    public ContentValues getContentValues(Armor armor) {
        ContentValues content = new ContentValues();
        content.put(COL_ID, armor.getId());
        content.put(COL_AC, armor.getAc());
        content.put(COL_COST, armor.getCost());
        content.put(COL_DESC, armor.getDescription());
        content.put(COL_STEALTH, armor.getStealthEffect());
        content.put(COL_STRENGTH, armor.getStrengthRequirement());
        content.put(COL_TYPE, armor.getType());
        content.put(COL_WEIGHT, armor.getWeight());
        content.put(COL_NAME, armor.getName());
        return content;
    }

    /**
     * This will create a new model and return it
     *
     * @return a new Record to update
     */
    @Override
    protected Armor createNewModel() {
        return new Armor();
    }

    @Override
    public String getIdColumn() {
        return COL_ID;
    }

    @Override
    public Long getId(@NonNull Armor armor) {
        return armor.getId();
    }

    @Override
    public void setId(@NonNull Armor armor, @Nullable Long id) {
        armor.setId(id);
    }
}
