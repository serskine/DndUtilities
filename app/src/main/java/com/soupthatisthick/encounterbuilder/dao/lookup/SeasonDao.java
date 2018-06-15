package com.soupthatisthick.encounterbuilder.dao.lookup;

import android.content.ContentValues;
import android.database.Cursor;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.soupthatisthick.encounterbuilder.model.lookup.Season;
import com.soupthatisthick.util.dao.DaoMaster;
import com.soupthatisthick.util.dao.WriteDao;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class SeasonDao extends WriteDao<Season> {

    public static final String TBL_SEASON = "SEASON";
    public static final String COL_ID = "id";
    public static final String COL_TITLE = "title";

    public SeasonDao(DaoMaster daoMaster) throws IOException {
        super(daoMaster, TBL_SEASON);
    }

    @Override
    public ContentValues getContentValues(Season season) {
        ContentValues cv = new ContentValues();
        cv.put(COL_ID, season.getId());
        cv.put(COL_TITLE, season.getTitle());
        return cv;
    }

    @Override
    protected Season createNewModel() {
        return new Season();
    }

    @Override
    public String getIdColumn() {
        return COL_ID;
    }

    @Override
    public Long getId(@NonNull Season season) {
        return season.getId();
    }

    @Override
    public void setId(@NonNull Season season, @Nullable Long id) {
        season.setId(id);
    }

    @Override
    public Season readRecord(Cursor cursor) {
        Season season = new Season();
        season.setId(cursor.getLong(cursor.getColumnIndex(COL_ID)));
        season.setTitle(cursor.getString(cursor.getColumnIndex(COL_TITLE)));
        return season;
    }

    @Override
    public Set<String> getSearchableColumns() {
        Set<String> columns = new HashSet<>();
        columns.add(COL_TITLE);
        return columns;
    }

    @Override
    public List<String> getColumnSortingOrder() {
        return Arrays.asList(COL_TITLE);
    }
}
