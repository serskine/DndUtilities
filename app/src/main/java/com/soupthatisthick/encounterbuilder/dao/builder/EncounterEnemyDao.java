package com.soupthatisthick.encounterbuilder.dao.builder;

import android.content.ContentValues;
import android.database.Cursor;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.soupthatisthick.encounterbuilder.model.builder.EncounterEnemy;
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

public class EncounterEnemyDao extends WriteDao<EncounterEnemy> {

    private static final String TBL_ENCOUNTER_ENEMIES = "ENCOUNTER_CHALLENGES";
    private static final String COL_ID = "id";
    private static final String COL_ENCOUNTER_ID = "encounterId";
    private static final String COL_CHALLENGE_ID = "challengeId";

    public EncounterEnemyDao(DaoMaster daoMaster) throws IOException {
        super(daoMaster, TBL_ENCOUNTER_ENEMIES);
    }

    @Override
    public EncounterEnemy readRecord(Cursor cursor) {
        EncounterEnemy enemy = new EncounterEnemy();
        enemy.setId(cursor.getLong(cursor.getColumnIndex(COL_ID)));
        enemy.setEncounterId(cursor.getLong(cursor.getColumnIndex(COL_ENCOUNTER_ID)));
        enemy.setChallengeId(cursor.getLong(cursor.getColumnIndex(COL_CHALLENGE_ID)));
        return enemy;
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
        order.add(COL_CHALLENGE_ID);
        return order;
    }

    /**
     * This will build a mapping of column names to values from the specified record
     * so that they may be inserted.
     *
     * @param encounterEnemy
     */
    @Override
    public ContentValues getContentValues(EncounterEnemy encounterEnemy) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_ID, encounterEnemy.getId());
        contentValues.put(COL_ENCOUNTER_ID, encounterEnemy.getEncounterId());
        contentValues.put(COL_CHALLENGE_ID, encounterEnemy.getChallengeId());
        return contentValues;
    }

    /**
     * This will create a new model and return it
     *
     * @return a new Record to update
     */
    @Override
    protected EncounterEnemy createNewModel() {
        return new EncounterEnemy();
    }

    @Override
    public String getIdColumn() {
        return COL_ID;
    }

    @Override
    public Long getId(@NonNull EncounterEnemy encounterEnemy) {
        return encounterEnemy.getId();
    }

    @Override
    public void setId(@NonNull EncounterEnemy encounterEnemy, @Nullable Long id) {
        encounterEnemy.setId(id);
    }
}
