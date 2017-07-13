package com.soupthatisthick.encounterbuilder.dao.lookup;

import android.content.ContentValues;
import android.database.Cursor;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.soupthatisthick.util.Logger;
import com.soupthatisthick.util.dao.SqlUtil;
import com.soupthatisthick.util.dao.DaoMaster;
import com.soupthatisthick.util.dao.WriteDao;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by Owner on 3/14/2017.
 * Copyright of Stuart Marr Erskine, all rights reserved.
 */

public class ContentValuesDao extends WriteDao<ContentValues> {

    public final String TABLE_NAME;
    private final String[] COLUMNS;
    public final String PRIMARY_KEY;

    public ContentValuesDao(
        final DaoMaster daoMaster,
        final @NonNull String tableName,
        final @NonNull String primaryKey
    ) throws IOException {
        super(daoMaster, tableName);

        this.PRIMARY_KEY = primaryKey;
        this.TABLE_NAME = tableName;
        this.COLUMNS = SqlUtil.getTableColumns(daoMaster.getReadableDatabase(), tableName);

        Logger.info("Created ContentValuesDao on table " + TABLE_NAME + " with the following columns.");
        for(int col=0; col<COLUMNS.length; col++)
        {
            Logger.info(" col[" + col + "] = " + COLUMNS[col]);
        }
    }

    @Override
    public ContentValues getContentValues(ContentValues contentValues) {
        return contentValues;
    }

    @Override
    protected ContentValues createNewModel() {
        return new ContentValues();
    }

    @Override
    public String getIdColumn() {
        return PRIMARY_KEY;
    }

    @Override
    public Long getId(@NonNull ContentValues contentValues) {
        return contentValues.getAsLong(getIdColumn());
    }

    @Override
    public void setId(@NonNull ContentValues contentValues, @Nullable Long id) {
        contentValues.put(getIdColumn(), id);
    }

    @Override
    public ContentValues readRecord(Cursor cursor) {
        ContentValues contentValues = createNewModel();
        for(int i=0; i<COLUMNS.length; i++)
        {
            contentValues.put(COLUMNS[i], cursor.getString(cursor.getColumnIndex(COLUMNS[i])));
        }
        Logger.info("Read record: " + contentValues.toString());
        return contentValues;
    }

    /**
     * We want to be able to search all columns except the id column
     * @return a {@link Set<String>}
     */
    @Override
    public Set<String> getSearchableColumns() {
        Set<String> searchableColumns = getAllColumns();
        searchableColumns.remove(getIdColumn());
        return searchableColumns;
    }

    /**
     * This is a list of columns that we will sort the results by when we return a dataset.
     *
     * @return a List of columns. Sorting is first done by the first column then by the next column etc until all columns have been ordered.
     */
    @Override
    public List<String> getColumnSortingOrder() {
        List<String> columns = new ArrayList<>();
        columns.add(getIdColumn());
        return columns;

    }

    /**
     * This will return a list of all columns in the database
     * @return a {@link Set<String>} that indicates all the columns in the database table
     */
    public final Set<String> getAllColumns() {
        HashSet<String> columns = new HashSet<>();
        Collections.addAll(columns, COLUMNS);
        return columns;
    }

}
