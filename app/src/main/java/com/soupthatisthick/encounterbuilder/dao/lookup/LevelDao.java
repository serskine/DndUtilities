package com.soupthatisthick.encounterbuilder.dao.lookup;

import android.content.ContentValues;
import android.database.Cursor;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.soupthatisthick.encounterbuilder.model.lookup.ChallengeRating;
import com.soupthatisthick.encounterbuilder.model.lookup.Level;
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

public class LevelDao extends WriteDao<Level> {

    public static final String TBL_LEVEL_DETAILS = "LEVEL_DETAILS";
    public static final String COL_ID = "id";
    public static final String COL_NAME = "name";
    public static final String COL_LEVEL = "level";
    public static final String COL_EASY = "easy";
    public static final String COL_NORMAL = "normal";
    public static final String COL_HARD = "hard";
    public static final String COL_DEADLY = "deadly";
    public static final String COL_PROF = "proficiency";
    public static final String COL_TIER = "tier";
    public static final String COL_XP = "xp";


    public LevelDao(DaoMaster daoMaster) throws IOException {
        super(daoMaster, TBL_LEVEL_DETAILS);
    }

    @Override
    public Level readRecord(Cursor cursor) {
        Level level = new Level();
        level.setId(cursor.getLong(cursor.getColumnIndex(COL_ID)));
        level.setName(cursor.getString(cursor.getColumnIndex(COL_NAME)));
        level.setLevel(cursor.getInt(cursor.getColumnIndex(COL_LEVEL)));
        level.setEasy(cursor.getInt(cursor.getColumnIndex(COL_EASY)));
        level.setNormal(cursor.getInt(cursor.getColumnIndex(COL_NORMAL)));
        level.setHard(cursor.getInt(cursor.getColumnIndex(COL_HARD)));
        level.setDeadly(cursor.getInt(cursor.getColumnIndex(COL_DEADLY)));
        level.setProficiency(cursor.getInt(cursor.getColumnIndex(COL_PROF)));
        level.setTier(cursor.getInt(cursor.getColumnIndex(COL_TIER)));
        level.setXp(cursor.getInt(cursor.getColumnIndex(COL_XP)));
        return level;
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
        return columns;
    }

    /**
     * This is a list of columns that we will sort the results by when we return a dataset.
     *
     * @return a List of columns. Sorting is first done by the first column then by the next column etc until all columns have been ordered.
     */
    @Override
    public List<String> getColumnSortingOrder() {
        List<String> columns = new ArrayList<>();
        columns.add(COL_LEVEL);
        return columns;
    }

    /**
     * This will build a mapping of column names to values from the specified record
     * so that they may be inserted.
     *
     * @param level
     */
    @Override
    public ContentValues getContentValues(Level level) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_ID, level.getId());
        contentValues.put(COL_NAME, level.getName());
        contentValues.put(COL_LEVEL, level.getLevel());
        contentValues.put(COL_EASY, level.getEasy());
        contentValues.put(COL_NORMAL, level.getNormal());
        contentValues.put(COL_HARD, level.getHard());
        contentValues.put(COL_DEADLY, level.getDeadly());
        contentValues.put(COL_PROF, level.getProficiency());
        contentValues.put(COL_TIER, level.getTier());
        contentValues.put(COL_XP, level.getXp());
        return contentValues;
    }

    /**
     * This will create a new model and return it
     *
     * @return a new Record to update
     */
    @Override
    protected Level createNewModel() {
        return new Level();
    }

    @Override
    public String getIdColumn() {
        return COL_ID;
    }

    @Override
    public Long getId(@NonNull Level level) {
        return level.getId();
    }

    @Override
    public void setId(@NonNull Level level, @Nullable Long id) {
        level.setId(id);
    }

    /**
     * This will attempt to find the challenge rating with the specified value
     * @param value
     * @return
     * @throws Exception
     */
    public Level findWithValue(@NonNull Double value) throws IOException
    {
        List<Level> results = getRecords(
            COL_LEVEL + "=?",
            new String[]
                {
                        value.toString()
                }
        );
        if (results.isEmpty()) {
            throw new RuntimeException("Failed to find a challenge rating with value " + value);
        }
        if (results.size() > 1) {
            throw new RuntimeException("Malformed database! There are " + results.size() + " resords with value=" + value);
        }
        return results.get(0);
    }
}
