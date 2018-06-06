package com.soupthatisthick.encounterbuilder.dao.lookup;

import android.content.ContentValues;
import android.database.Cursor;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.soupthatisthick.encounterbuilder.model.lookup.EntityList;
import com.soupthatisthick.util.dao.DaoMaster;
import com.soupthatisthick.util.dao.WriteDao;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by Owner on 4/18/2017.
 * Copyright of Stuart Marr Erskine, all rights reserved.
 */

public class EntityListDao extends WriteDao<EntityList> {

    public static final String TBL_LISTS = "LISTS";
    public static final String COL_ID = "id";
    public static final String COL_NAME = "name";

    public EntityListDao(DaoMaster daoMaster) throws IOException {
        super(daoMaster, TBL_LISTS);
    }

    @Override
    public EntityList readRecord(Cursor cursor) {
        EntityList entityList = new EntityList();
        entityList.setId(cursor.getLong(cursor.getColumnIndex(COL_ID)));
        entityList.setName(cursor.getString(cursor.getColumnIndex(COL_NAME)));
        return entityList;
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
        ArrayList<String> sortOrder = new ArrayList<>();
        sortOrder.add(COL_NAME);
        return sortOrder;
    }

    /**
     * This will build a mapping of column names to values from the specified record
     * so that they may be inserted.
     *
     * @param entityList
     */
    @Override
    public ContentValues getContentValues(EntityList entityList) {
        ContentValues content = new ContentValues();
        content.put(COL_ID, entityList.getId());
        content.put(COL_NAME, entityList.getName());
        return content;
    }

    /**
     * This will create a new model and return it
     *
     * @return a new Record to update
     */
    @Override
    protected EntityList createNewModel() {
        return new EntityList();
    }

    @Override
    public String getIdColumn() {
        return COL_ID;
    }

    @Override
    public Long getId(@NonNull EntityList entityList) {
        return entityList.getId();
    }

    @Override
    public void setId(@NonNull EntityList entityList, @Nullable Long id) {
        entityList.setId(id);
    }
}
