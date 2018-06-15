package com.soupthatisthick.encounterbuilder.dao.lookup;

import android.content.ContentValues;
import android.database.Cursor;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.soupthatisthick.encounterbuilder.model.lookup.Adventure;
import com.soupthatisthick.util.dao.DaoMaster;
import com.soupthatisthick.util.dao.WriteDao;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class AdventureDao extends WriteDao<Adventure> {
    public static final String TBL_ADVENTURE = "ADVENTURE";
    public static final String COL_ID = "id";
    public static final String COL_CODE = "code";
    public static final String COL_LEVEL_BAND = "levelBand";
    public static final String COL_RUNTIME_HOURS = "runtimeHours";
    public static final String COL_TITLE = "title";
    public static final String COL_NOTES = "notes";
    public static final String COL_SEASON_ID = "seasonId";
    public static final String COL_DESCRIPTION = "description";

    public AdventureDao(DaoMaster daoMaster, @NonNull String tableName) throws IOException {
        super(daoMaster, tableName);
    }

    @Override
    public ContentValues getContentValues(Adventure adventure) {
        ContentValues cv = new ContentValues();

        cv.put(COL_ID, adventure.getId());
        cv.put(COL_CODE, adventure.getCode());
        cv.put(COL_LEVEL_BAND, adventure.getLevelBand());
        cv.put(COL_RUNTIME_HOURS, adventure.getLevelBand());
        cv.put(COL_TITLE, adventure.getTitle());
        cv.put(COL_NOTES, adventure.getNotes());
        cv.put(COL_SEASON_ID, adventure.getSeasonId());
        cv.put(COL_DESCRIPTION, adventure.getDescription());

        return cv;
    }

    @Override
    protected Adventure createNewModel() {
        return new Adventure();
    }

    @Override
    public String getIdColumn() {
        return COL_ID;
    }

    @Override
    public Long getId(@NonNull Adventure adventure) {
        return adventure.getId();
    }

    @Override
    public void setId(@NonNull Adventure adventure, @Nullable Long id) {
        adventure.setId(id);
    }

    @Override
    public Adventure readRecord(Cursor cursor) {
        Adventure adventure = new Adventure();

        adventure.setId(cursor.getLong(cursor.getColumnIndex(COL_ID)));
        adventure.setCode(cursor.getString(cursor.getColumnIndex(COL_CODE)));
        adventure.setDescription(cursor.getString(cursor.getColumnIndex(COL_DESCRIPTION)));
        adventure.setLevelBand(cursor.getString(cursor.getColumnIndex(COL_LEVEL_BAND)));
        adventure.setNotes(cursor.getString(cursor.getColumnIndex(COL_NOTES)));
        adventure.setRuntimeHours(cursor.getString(cursor.getColumnIndex(COL_RUNTIME_HOURS)));
        adventure.setSeasonId(cursor.getLong(cursor.getColumnIndex(COL_SEASON_ID)));

        return adventure;
    }

    @Override
    public Set<String> getSearchableColumns() {
        Set<String> columns = new HashSet<>();
        columns.add(COL_CODE);
        columns.add(COL_TITLE);
        columns.add(COL_SEASON_ID);
        columns.add(COL_LEVEL_BAND);
        return columns;
    }

    @Override
    public List<String> getColumnSortingOrder() {
        return Arrays.asList(COL_SEASON_ID, COL_CODE, COL_TITLE);
    }
}
