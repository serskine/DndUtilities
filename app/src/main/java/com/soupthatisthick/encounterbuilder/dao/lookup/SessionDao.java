package com.soupthatisthick.encounterbuilder.dao.lookup;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.soupthatisthick.encounterbuilder.model.lookup.LogsheetSummary;
import com.soupthatisthick.encounterbuilder.model.lookup.Session;
import com.soupthatisthick.encounterbuilder.util.translater.DateTranslater;
import com.soupthatisthick.util.Logger;
import com.soupthatisthick.util.dao.SqlUtil;
import com.soupthatisthick.util.dao.DaoMaster;
import com.soupthatisthick.util.dao.WriteDao;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by Owner on 2/5/2017.
 * Copyright of Stuart Marr Erskine, all rights reserved.
 */

public class SessionDao extends WriteDao<Session> {

    public static final String TBL_SESSIONS = "gameSessions";
    public static final String COL_SESSION_ID = "sessionId";
    public static final String COL_CHARACTER_ID = "characterId";
    public static final String COL_ADVENTURE = "adventure";
    public static final String COL_DM_NAME = "dmName";
    public static final String COL_DM_DCI = "dmDci";
    public static final String COL_DATE = "date";
    public static final String COL_XP = "xpEarned";
    public static final String COL_GOLD = "goldEarned";
    public static final String COL_DOWNTIME = "downtimeEarned";
    public static final String COL_RENOWN = "renownEarned";
    public static final String COL_MAGIC_ITEMS = "magicItemsEarned";
    public static final String COL_NOTES = "notes";

    public SessionDao(DaoMaster daoMaster) throws IOException {
        super(daoMaster, TBL_SESSIONS);
    }

    @Override
    public ContentValues getContentValues(Session session) {
        ContentValues content = new ContentValues();

        content.put(COL_SESSION_ID, session.getSessionId());

        content.put(COL_CHARACTER_ID, session.getCharacterId());
        content.put(COL_ADVENTURE, session.getAdventure());
        content.put(COL_DM_NAME, session.getDmName());
        content.put(COL_DM_DCI, session.getDmDci());


        Date date = session.getDate();
        if (date==null)
        {
            date = new Date();
        }

        String dateColValue;
        try {
            dateColValue = DateTranslater.translate(date);
        } catch (Exception e) {
            dateColValue = null;
        }

        content.put(COL_DATE, dateColValue);

        content.put(COL_XP, session.getXp());
        content.put(COL_GOLD, session.getGold());
        content.put(COL_DOWNTIME, session.getDowntime());
        content.put(COL_RENOWN, session.getRenown());
        content.put(COL_MAGIC_ITEMS, session.getMagicItems());
        content.put(COL_NOTES, session.getNotes());

        return content;
    }

    @Override
    protected Session createNewModel() {
        Session session = new Session();
        session.setDate(new Date());
        return session;
    }

    @Override
    public String getIdColumn() {
        return COL_SESSION_ID;
    }

    @Override
    public Long getId(@NonNull Session session) {
        return session.getSessionId();
    }

    @Override
    public void setId(@NonNull Session session, @Nullable Long id) {
        session.setSessionId(id);
    }


    @Override
    public Session readRecord(Cursor cursor) {
        Session session = new Session();

        session.setSessionId(cursor.getLong(cursor.getColumnIndex(COL_SESSION_ID)));

        String charIdTxt = cursor.getString(cursor.getColumnIndex(COL_CHARACTER_ID));

        Long charId;
        try {
            charId = Long.parseLong(charIdTxt);
        } catch (Exception e)
        {
            charId = null;
        }
        session.setCharacterId(charId);

        session.setAdventure(cursor.getString(cursor.getColumnIndex(COL_ADVENTURE)));
        session.setDmName(cursor.getString(cursor.getColumnIndex(COL_DM_NAME)));
        session.setDmDci(cursor.getString(cursor.getColumnIndex(COL_DM_DCI)));
        session.setNotes(cursor.getString(cursor.getColumnIndex(COL_NOTES)));

        String dateColumn = cursor.getString(cursor.getColumnIndex(COL_DATE));
        Date date = DateTranslater.translate(dateColumn);
        date = (date==null) ? new Date() : date;
        session.setDate(date);

        session.setXp(cursor.getInt(cursor.getColumnIndex(COL_XP)));
        session.setGold(cursor.getInt(cursor.getColumnIndex(COL_GOLD)));
        session.setDowntime(cursor.getInt(cursor.getColumnIndex(COL_DOWNTIME)));
        session.setRenown(cursor.getInt(cursor.getColumnIndex(COL_RENOWN)));
        session.setMagicItems(cursor.getInt(cursor.getColumnIndex(COL_MAGIC_ITEMS)));

        return session;
    }


    @Override
    public Set<String> getSearchableColumns() {
        HashSet<String> columns = new HashSet<>();

        columns.add(COL_ADVENTURE);
        columns.add(COL_DATE);

        columns.add(COL_DM_DCI);
        columns.add(COL_DM_NAME);

        columns.add(COL_NOTES);

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
        columns.add(COL_DATE);
        columns.add(COL_ADVENTURE);
        return columns;

    }

    /**
     * This is used to get all the logsheets for a specific character
     * @param characterId identifies the owner of the sessisons
     * @return
     * @throws IOException
     */
    public List<Session> getSessionsFor(@NonNull Long characterId) throws IOException
    {
        String selection = "(" + COL_CHARACTER_ID + "=?)";

        return getRecords(
            selection,                              // selection where clause
            new String[] {characterId.toString()},  // selection arguments
            null,                                   // groupby
            null,                                   // having
            COL_DATE + " DESC"                      // orderby
        );
    }

    /**
     * This method will get the summary for all of the key columns
     * @param characterId
     * @return
     * @throws IOException
     */
    public LogsheetSummary getSummaryFor(@NonNull Long characterId) throws IOException
    {
        Cursor cursor = null;
        LogsheetSummary summary = new LogsheetSummary();

        try {
            SQLiteDatabase db = daoMaster.getWritableDatabase();
            String whereClause = "(" + COL_CHARACTER_ID + "=?)";
            String[] columns = new String[] {
                "SUM(" + COL_XP + ")",
                "SUM(" + COL_GOLD + ")",
                "SUM(" + COL_DOWNTIME + ")",
                "SUM(" + COL_RENOWN + ")",
                "SUM(" + COL_MAGIC_ITEMS + ")"
            };

            cursor = db.query(
                getTable(),
                columns,
                whereClause,
                new String[] { Long.toString(characterId) },
                null,
                null,
                null
            );

            if (cursor.moveToFirst() && !cursor.isAfterLast()) {
                summary.setTotalXp(cursor.getInt(cursor.getColumnIndex(columns[0])));
                summary.setTotalGold(cursor.getInt(cursor.getColumnIndex(columns[1])));
                summary.setTotalDowntime(cursor.getInt(cursor.getColumnIndex(columns[2])));
                summary.setTotalRenown(cursor.getInt(cursor.getColumnIndex(columns[3])));
                summary.setTotalMagicItems(cursor.getInt(cursor.getColumnIndex(columns[4])));
            }

        } catch (Exception e) {
            Logger.error(e.getMessage(), e);

        } finally {
            SqlUtil.closeCursor(cursor);
        }

        return summary;
    }

    /**
     * This will delete all the sessions for the given character
     * @param characterId whose sessions we want to delete
     * @throws IOException thrown when an error occurs
     */
    public void deleteSessionsFor(@NonNull Long characterId) throws IOException
    {
        SQLiteDatabase db = daoMaster.getWritableDatabase();
        String whereClause = "(" + COL_CHARACTER_ID + "=?)";

        db.delete(
            getTable(),
            whereClause,
            new String[] { Long.toString(characterId) }
        );
    }


}
