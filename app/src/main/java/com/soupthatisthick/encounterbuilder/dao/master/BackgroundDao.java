package com.soupthatisthick.encounterbuilder.dao.master;

import android.content.ContentValues;
import android.database.Cursor;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.soupthatisthick.encounterbuilder.model.lookup.Background;
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

public class BackgroundDao extends WriteDao<Background> {

    public static final String TBL_BACKGROUND = "BACKGROUND";
    public static final String COL_ID = "id";
    public static final String COL_NAME = "name";
    public static final String COL_SKILLS = "skills";
    public static final String COL_TOOLS = "tools";
    public static final String COL_LANG = "languages";
    public static final String COL_EQUIP = "equipment";
    public static final String COL_DESC = "description";
    public static final String COL_FEATURES = "features";


    public BackgroundDao(DaoMaster daoMaster) throws IOException {
        super(daoMaster, TBL_BACKGROUND);
    }

    @Override
    public Background readRecord(Cursor cursor) {
        Background background = new Background();
        background.setId(cursor.getLong(cursor.getColumnIndex(COL_ID)));
        background.setName(cursor.getString(cursor.getColumnIndex(COL_NAME)));
        background.setSkills(cursor.getString(cursor.getColumnIndex(COL_SKILLS)));
        background.setTools(cursor.getString(cursor.getColumnIndex(COL_TOOLS)));
        background.setLanguages(cursor.getString(cursor.getColumnIndex(COL_LANG)));
        background.setEquipment(cursor.getString(cursor.getColumnIndex(COL_EQUIP)));
        background.setDescription(cursor.getString(cursor.getColumnIndex(COL_DESC)));
        background.setFeatures(cursor.getString(cursor.getColumnIndex(COL_FEATURES)));
        return background;
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
        columns.add(COL_SKILLS);
        columns.add(COL_LANG);
        columns.add(COL_TOOLS);
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
     * @param background
     */
    @Override
    public ContentValues getContentValues(Background background) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_ID, background.getId());
        contentValues.put(COL_NAME, background.getName());
        contentValues.put(COL_SKILLS, background.getSkills());
        contentValues.put(COL_TOOLS, background.getTools());
        contentValues.put(COL_EQUIP, background.getEquipment());
        contentValues.put(COL_LANG, background.getLanguages());
        contentValues.put(COL_FEATURES, background.getFeatures());
        contentValues.put(COL_DESC, background.getDescription());
        return contentValues;
    }

    /**
     * This will create a new model and return it
     *
     * @return a new Record to update
     */
    @Override
    protected Background createNewModel() {
        return new Background();
    }

    @Override
    public String getIdColumn() {
        return COL_ID;
    }

    @Override
    public Long getId(@NonNull Background background) {
        return background.getId();
    }

    @Override
    public void setId(@NonNull Background background, @Nullable Long id) {
        background.setId(id);
    }
}
