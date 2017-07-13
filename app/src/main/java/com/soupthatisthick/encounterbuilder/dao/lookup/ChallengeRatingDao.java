package com.soupthatisthick.encounterbuilder.dao.lookup;

import android.content.ContentValues;
import android.database.Cursor;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.soupthatisthick.encounterbuilder.model.lookup.ChallengeRating;
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

public class ChallengeRatingDao extends WriteDao<ChallengeRating> {

    public static final String TBL_CR_DETAILS = "CR_DETAILS";
    public static final String COL_ID = "id";
    public static final String COL_NAME = "name";
    public static final String COL_VALUE = "value";
    public static final String COL_PROF = "prof";
    public static final String COL_MAX_AC = "maxAc";
    public static final String COL_MIN_HP = "minHp";
    public static final String COL_MAX_HP = "maxHp";
    public static final String COL_MIN_DMG = "minDmg";
    public static final String COL_MAX_DMG = "maxDmg";
    public static final String COL_SAVE_DC = "saveDc";
    public static final String COL_XP = "xp";

    public ChallengeRatingDao(DaoMaster daoMaster) throws IOException {
        super(daoMaster, TBL_CR_DETAILS);
    }


    @Override
    public ChallengeRating readRecord(Cursor cursor) {
        ChallengeRating cr = new ChallengeRating();
        cr.setId(cursor.getLong(cursor.getColumnIndex(COL_ID)));
        cr.setValue(cursor.getDouble(cursor.getColumnIndex(COL_VALUE)));
        cr.setName(cursor.getString(cursor.getColumnIndex(COL_NAME)));
        cr.setMaxAc(cursor.getInt(cursor.getColumnIndex(COL_MAX_AC)));
        cr.setProficiency(cursor.getInt(cursor.getColumnIndex(COL_PROF)));
        cr.setSaveDc(cursor.getInt(cursor.getColumnIndex(COL_SAVE_DC)));
        cr.setMinHp(cursor.getInt(cursor.getColumnIndex(COL_MIN_HP)));
        cr.setMaxHp(cursor.getInt(cursor.getColumnIndex(COL_MAX_HP)));
        cr.setMinDmg(cursor.getInt(cursor.getColumnIndex(COL_MIN_DMG)));
        cr.setMaxDmg(cursor.getInt(cursor.getColumnIndex(COL_MAX_DMG)));
        cr.setXp(cursor.getInt(cursor.getColumnIndex(COL_XP)));
        return cr;
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
        ArrayList<String> order = new ArrayList<>();
        order.add(COL_VALUE);
        return order;
    }

    /**
     * This will build a mapping of column names to values from the specified record
     * so that they may be inserted.
     *
     * @param cr
     */
    @Override
    public ContentValues getContentValues(ChallengeRating cr) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_ID, cr.getId());
        contentValues.put(COL_NAME, cr.getName());
        contentValues.put(COL_MAX_AC, cr.getMaxAc());
        contentValues.put(COL_VALUE, cr.getValue());
        contentValues.put(COL_SAVE_DC, cr.getSaveDc());
        contentValues.put(COL_PROF, cr.getProficiency());
        contentValues.put(COL_MIN_HP, cr.getMinHp());
        contentValues.put(COL_MAX_HP, cr.getMaxHp());
        contentValues.put(COL_MIN_DMG, cr.getMinDmg());
        contentValues.put(COL_MAX_DMG, cr.getMaxDmg());
        contentValues.put(COL_XP, cr.getXp());
        return contentValues;
    }

    /**
     * This will create a new model and return it
     *
     * @return a new Record to update
     */
    @Override
    protected ChallengeRating createNewModel() {
        return new ChallengeRating();
    }

    @Override
    public String getIdColumn() {
        return COL_ID;
    }

    @Override
    public Long getId(@NonNull ChallengeRating challengeRating) {
        return challengeRating.getId();
    }

    @Override
    public void setId(@NonNull ChallengeRating challengeRating, @Nullable Long id) {
        challengeRating.setId(id);
    }

    /**
     * This will attempt to find the challenge rating with the specified value
     * @param value
     * @return
     * @throws Exception
     */
    public ChallengeRating findWithValue(@NonNull Double value) throws IOException
    {
        List<ChallengeRating> results = getRecords(
                COL_VALUE + "=?",
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
