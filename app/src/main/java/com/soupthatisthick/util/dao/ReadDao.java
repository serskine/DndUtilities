package com.soupthatisthick.util.dao;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.soupthatisthick.util.Logger;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Created by Owner on 1/28/2017.
 * Copyright of Stuart Marr Erskine, all rights reserved.
 */

public abstract class ReadDao<Record> implements Dao<Record> {

    protected final DaoMaster daoMaster;

    public static final String COL_THE_ROWID = "OID";
    protected static final String TBL_DAO_SEARCHABLE = "DAO_SEARCHABLE";

    private Set<String> searchableColumns = null;
    private final String tableName;

    public ReadDao(DaoMaster daoMaster, @NonNull String tableName) throws IOException {
        this.daoMaster = daoMaster;
        this.tableName = tableName;
        if (!daoMaster.getTables().contains(getTable()))
        {
            daoMaster.logSchema();
            throw new IOException("Dao master (" + daoMaster.getDatabaseName() + ") does not contain table " + getTable());
        }

    }

    @Override
    public final String getTable()
    {
        return this.tableName;
    }

    @Override
    public final List<Record> getAllRecords() throws IOException {
        return getRecords(null, null);
    }

    protected final void logQuery(@Nullable String selection, @Nullable String[] args)
    {
        Logger.debug("QUERY = " + selection);

        if (args!=null) {
            for (int i = 0; i < args.length; i++) {
                Logger.debug("   arg[" + i + "] = " + args[i]);
            }
        } else {
            Logger.debug("  args = null");
        }

    }

    public final List<Record> getRecords(@Nullable SqlUtil.Query query) throws IOException {
        if (query==null)
        {
            return getRecords(null, null);
        } else {
            return getRecords(query.sql, query.args, query.groupBy, query.having, query.orderBy);
        }
    }


    @Override
    public final List<Record> getRecords(
            @Nullable String selection,
            @Nullable String[] args
    ) throws IOException {
        return getRecords(selection, args, null, null, null);
    }

    @Override
    public final List<Record> getRecords(
        @Nullable String selection,
        @Nullable String[] args,
        @Nullable String groupBy,
        @Nullable String having,
        @Nullable String orderBy
    ) throws IOException {

        ArrayList<Record> items = new ArrayList<>();
        SQLiteDatabase db = daoMaster.getReadableDatabase();
        Cursor cursor = db.query(
                getTable(),      // table
                null,            // desired columns
                selection,       // selection
                args,            // selection args
                groupBy,         // group by
                having,          // having
                orderBy          // order by
        );

        if (cursor.moveToFirst()) {
            while (!cursor.isAfterLast()) {
                items.add(readRecord(cursor));
                cursor.moveToNext();
            }
        }
        return items;

    }

    /**
     * This is the default method to use for search queries. It will first split the search text up into
     * words and then for each word attempt to see if it is similar to any of the available database columns
     * provided. This will only provide all records where there every word matches some column using a
     * case insensitive like query.
     * @param searchText that will be parsed for words
     * @return a list of records where every word matches some column.
     * @throws IOException is thrown when there is an error
     */
    public List<Record> getRecordsLike(String searchText) throws IOException
    {
        return getRecordsLike(
            searchText,
            getSearchableColumns(),
            getColumnSortingOrder()
        );
    }


    /**
     * This sorts all the words into two groups. Those we want to include and those we want to exclude
     * @param includeList
     * @param excludeList
     * @param words
     */
    private static final void groupWords(@NonNull List<String> includeList, @NonNull List<String> excludeList, @NonNull String words[])
    {

        for(int i=0; i<words.length; i++)
        {
            if (words[i].startsWith("-") && words[i].length()>1)
            {
                excludeList.add(words[i].substring(1));
            } else {
                includeList.add(words[i]);
            }
        }
    }

    /**
     * This is the more detailed method for getting a list of records that is similar to the search text, only here
     * we can specify the columns that we search as well as the sorting order
     * @param searchText
     * @param searchableColumns
     * @param columnOrdering
     * @return
     * @throws IOException
     */
   public List<Record> getRecordsLike(
            String searchText,
            @NonNull Set<String> searchableColumns,
            @NonNull List<String> columnOrdering
    ) throws IOException
    {
        searchText = searchText.toUpperCase().trim();   // Convert to upper case to make case insensitive

        String[] columnOrder = new String[columnOrdering.size()];
        columnOrder = columnOrdering.toArray(columnOrder);

        String words[] = searchText.split("\\s+");  // Split on white spaces to get all words that must match something
        ArrayList<String> includeList = new ArrayList<>();
        ArrayList<String> excludeList = new ArrayList<>();

        groupWords(includeList, excludeList, words);

        String[] includeWords = new String[includeList.size()];
        String[] excludeWords = new String[excludeList.size()];

        for(int i=0; i<includeWords.length; i++)
        {
            includeWords[i] = "%" + includeList.get(i) + "%";
        }
        for(int i=0; i<excludeWords.length; i++)
        {
            excludeWords[i] = "%" + excludeList.get(i) + "%";
        }

        String[] selectionArgs;
        String selection = "";

        // Build the query if we have words to match
        if (words.length>0) {

            // Build the array of columns to match against
            Set<String> columnSet = searchableColumns;
            String[] columns = new String[columnSet.size()];
            int idx = 0;
            for(String column : columnSet)
            {
                columns[idx] = column;
                idx++;
            }

            SqlUtil.Query query = SqlUtil.anyIncludedWordAndNoExcludedWords(
                includeWords,
                excludeWords,
                columns,
                columnOrder
            );

            Logger.info(query.toString());
            return getRecords(query);
        } else {
            // Get all records
            return getRecords(null);
        }
    }

    public void logContents()
    {
        Logger.info("TODO: Implement the logging of all contents in a ReadDao.");
    }

    public void logSchema()
    {
        SqlUtil.logSchema(daoMaster.getReadableDatabase(), getTable());
    }

    @Override
    public final int countRecords() throws IOException
    {
        return countRecords(null, null);
    }

    @Override
    public final int countRecords(
            @Nullable String selection,
            @Nullable String[] args
    ) throws IOException {

        SQLiteDatabase db = daoMaster.getReadableDatabase();
        Cursor cursor = db.query(
                getTable(), // table
                null,   // desired columns
                selection,   // selection
                args,   // selection args
                null,   // group by
                null,   // having
                null    // order by
        );
        return cursor.getCount();
    }


}
