package com.soupthatisthick.encounterbuilder.dao.lookup;

import android.content.ContentValues;
import android.database.Cursor;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.soupthatisthick.encounterbuilder.model.lookup.LifeStyle;
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

public class LifeStyleDao extends WriteDao<LifeStyle> {

    public static final String TBL_LIFESTYLE = "LIFESTYLE";
    public static final String COL_ID = "id";
    public static final String COL_NAME = "name";
    public static final String COL_PRICE_PER_DAY = "pricePerDay";
    public static final String COL_DESC = "description";

    public LifeStyleDao(DaoMaster daoMaster) throws IOException {
        super(daoMaster, TBL_LIFESTYLE);
    }

    @Override
    public LifeStyle readRecord(Cursor cursor) {
        LifeStyle lifeStyle = new LifeStyle();

        lifeStyle.setId(cursor.getLong(cursor.getColumnIndex(COL_ID)));
        lifeStyle.setDescription(cursor.getString(cursor.getColumnIndex(COL_DESC)));
        lifeStyle.setName(cursor.getString(cursor.getColumnIndex(COL_NAME)));
        lifeStyle.setPricePerDay(cursor.getString(cursor.getColumnIndex(COL_PRICE_PER_DAY)));

        return lifeStyle;
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
     * @param lifeStyle
     */
    @Override
    public ContentValues getContentValues(LifeStyle lifeStyle) {
        ContentValues content = new ContentValues();
        content.put(COL_ID, lifeStyle.getId());
        content.put(COL_DESC, lifeStyle.getDescription());
        content.put(COL_NAME, lifeStyle.getName());
        content.put(COL_PRICE_PER_DAY, lifeStyle.getPricePerDay());
        return content;
    }

    /**
     * This will create a new model and return it
     *
     * @return a new Record to update
     */
    @Override
    protected LifeStyle createNewModel() {
        return new LifeStyle();
    }

    @Override
    public String getIdColumn() {
        return COL_ID;
    }

    @Override
    public Long getId(@NonNull LifeStyle lifeStyle) {
        return lifeStyle.getId();
    }

    @Override
    public void setId(@NonNull LifeStyle lifeStyle, @Nullable Long id) {
        lifeStyle.setId(id);
    }
}
