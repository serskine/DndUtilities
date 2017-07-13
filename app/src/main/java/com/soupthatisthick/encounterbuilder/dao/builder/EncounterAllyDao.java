package com.soupthatisthick.encounterbuilder.dao.builder;

import android.content.ContentValues;
import android.database.Cursor;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.soupthatisthick.encounterbuilder.model.builder.EncounterAlly;
import com.soupthatisthick.util.dao.DaoMaster;
import com.soupthatisthick.util.dao.WriteDao;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by Owner on 6/20/2017.
 * Copyright of Stuart Marr Erskine, all rights reserved.
 */

public class EncounterAllyDao extends WriteDao<EncounterAlly> {

    private static final String TBL_ENCOUNTER_ALLIES = "ENCOUNTER_PLAYERS";
    private static final String COL_ID = "id";
    private static final String COL_ENCOUNTER_ID = "encounterId";
    private static final String COL_PLAYER_ID = "playerId";

    public EncounterAllyDao(DaoMaster daoMaster) throws IOException {
        super(daoMaster, TBL_ENCOUNTER_ALLIES);
    }

    @Override
    public EncounterAlly readRecord(Cursor cursor) {
        EncounterAlly ally = new EncounterAlly();
        ally.setId(cursor.getLong(cursor.getColumnIndex(COL_ID)));
        ally.setEncounterId(cursor.getLong(cursor.getColumnIndex(COL_ENCOUNTER_ID)));
        ally.setEncounterId(cursor.getLong(cursor.getColumnIndex(COL_PLAYER_ID)));
        return ally;
    }

    /**
     * This method will return all the columns that may be searched for matches in a text search.
     *
     * @return a Set of columns elidgible for searching with a like query
     */
    @Override
    public Set<String> getSearchableColumns() {
        Set<String> cols = new HashSet<>();
        cols.add(COL_ID);
        return cols;
    }

    /**
     * This is a list of columns that we will sort the results by when we return a dataset.
     *
     * @return a List of columns. Sorting is first done by the first column then by the next column etc until all columns have been ordered.
     */
    @Override
    public List<String> getColumnSortingOrder() {
        List<String> order = new ArrayList<>();
        order.add(COL_ENCOUNTER_ID);
        order.add(COL_PLAYER_ID);
        return order;
    }

    /**
     * This will build a mapping of column names to values from the specified record
     * so that they may be inserted.
     *
     * @param encounterAlly
     */
    @Override
    public ContentValues getContentValues(EncounterAlly encounterAlly) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_ID, encounterAlly.getId());
        contentValues.put(COL_ENCOUNTER_ID, encounterAlly.getId());
        contentValues.put(COL_PLAYER_ID, encounterAlly.getId());
        return contentValues;
    }

    /**
     * This will create a new model and return it
     *
     * @return a new Record to update
     */
    @Override
    protected EncounterAlly createNewModel() {
        return new EncounterAlly();
    }

    @Override
    public String getIdColumn() {
        return COL_ID;
    }

    @Override
    public Long getId(@NonNull EncounterAlly encounterAlly) {
        return encounterAlly.getId();
    }

    @Override
    public void setId(@NonNull EncounterAlly encounterAlly, @Nullable Long id) {
        encounterAlly.setId(id);
    }
}
