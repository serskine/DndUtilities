package com.soupthatisthick.encounterbuilder.dao.lookup;

import android.content.ContentValues;
import android.database.Cursor;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.soupthatisthick.encounterbuilder.model.lookup.Pc;
import com.soupthatisthick.util.Logger;
import com.soupthatisthick.util.dao.SqlUtil;
import com.soupthatisthick.util.dao.DaoMaster;
import com.soupthatisthick.util.dao.WriteDao;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by Owner on 2/5/2017.
 * Copyright of Stuart Marr Erskine, all rights reserved.
 */

public class PcDao extends WriteDao<Pc> {

    public static final String TBL_LOGSHEETS = "logsheets";
    public static final String COL_ID = "id";
    public static final String COL_PLAYER_NAME = "playerName";
    public static final String COL_PLAYER_DCI = "playerDci";
    public static final String COL_CHARACTER = "characterName";
    public static final String COL_FACTION = "faction";
    public static final String COL_CLASS_LEVELS = "classLevels";

    public PcDao(DaoMaster daoMaster) throws IOException {
        super(daoMaster, TBL_LOGSHEETS);
    }

    @Override
    public ContentValues getContentValues(Pc pc) {
        ContentValues content = new ContentValues();

        content.put(COL_ID, pc.getId());
        content.put(COL_PLAYER_NAME, pc.getPlayerName());
        content.put(COL_PLAYER_DCI, pc.getPlayerDci());
        content.put(COL_CHARACTER, pc.getCharacterName());
        content.put(COL_CLASS_LEVELS, pc.getClassAndLevels());
        content.put(COL_FACTION, pc.getFaction());

        return content;
    }

    @Override
    public Pc createNewModel() {
        return new Pc();
    }

    @Override
    public String getIdColumn() {
        return COL_ID;
    }

    @Override
    public Long getId(@NonNull Pc pc) {
        return pc.getId();
    }

    @Override
    public void setId(@NonNull Pc pc, @Nullable Long id) {
        pc.setId(id);
    }

    @Override
    public Pc readRecord(Cursor cursor) {
        Pc pc = new Pc();

        Logger.info(SqlUtil.cursorHeadersString(cursor));

        pc.setId(cursor.getLong(cursor.getColumnIndex(COL_ID)));
        pc.setPlayerName(cursor.getString(cursor.getColumnIndex(COL_PLAYER_NAME)));
        pc.setPlayerDci(cursor.getString(cursor.getColumnIndex(COL_PLAYER_DCI)));
        pc.setCharacterName(cursor.getString(cursor.getColumnIndex(COL_CHARACTER)));
        pc.setClassAndLevels(cursor.getString(cursor.getColumnIndex(COL_CLASS_LEVELS)));
        pc.setFaction(cursor.getString(cursor.getColumnIndex(COL_FACTION)));

        return pc;
    }

    @Override
    public Set<String> getSearchableColumns() {
        HashSet<String> columns = new HashSet<>();
        columns.add(COL_PLAYER_NAME);
        columns.add(COL_PLAYER_DCI);
        columns.add(COL_CHARACTER);
        columns.add(COL_FACTION);
        columns.add(COL_CLASS_LEVELS);
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
        columns.add(COL_PLAYER_DCI);
        columns.add(COL_CHARACTER);
        return columns;

    }
}
