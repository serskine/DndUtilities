package com.soupthatisthick.util.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.CallSuper;
import android.support.annotation.Nullable;

import com.soupthatisthick.encounterbuilder.util.DatabaseHelper;
import com.soupthatisthick.encounterbuilder.util.DatabaseHelper2;
import com.soupthatisthick.encounterbuilder.util.Text;
import com.soupthatisthick.util.Logger;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import static com.soupthatisthick.util.dao.SqlUtil.getTableColumnsSet;

/**
 * Created by Owner on 1/26/2017.
 * Copyright of Stuart Marr Erskine, all rights reserved.
 */

public class DaoMaster extends DatabaseHelper2
{

    public static final String DATABASE_FILE_PATH = "/databases/";

    private final int codeVersion;
    private final int realVersion;

    public DaoMaster(
        Context context,
        String dbDirectory,
        String dbName,
        int version
    ) throws IOException {
        super(context,dbName, null, version);
        // Cause us to open the database
        this.codeVersion = version;
        SQLiteDatabase currentDb = getReadableDatabase();
        realVersion = currentDb.getVersion();

        Logger.debug("Created DaoMaster for database " + getDatabaseName() + " codeVersion: " + codeVersion + " readVersion: " + realVersion);
        logSchema();

    }

    public final void logSchema()
    {
        Logger.title("DaoMaster " + getDatabaseName() + " version " + getReadableDatabase().getVersion(), 1);


        Map<String, Set<String>> schema = getSchema();

        Map<String, String> tableMap = new HashMap<>();

        for(String table : schema.keySet())
        {
            Set<String> columns = schema.get(table);
            String line = "";
            for(String col : columns)
            {
                line += "[" + col + "]";
            }
            tableMap.put(table, line);
        }

        Logger.info(Text.mapOutput(tableMap));
    }

    public final void logTableSchema(String table)
    {
        SQLiteDatabase db = getReadableDatabase();
        SqlUtil.logSchema(db, table);
    }

    public static final void close(@Nullable DaoMaster daoMaster)
    {
        if (daoMaster!=null)
        {
            daoMaster.close();
        }
    }

    /**
     * This will return a list of all tables in the database including the master table
     * @return a {@link Set<String>}
     */
    public Set<String> getTables()
    {
        return SqlUtil.getTables(getReadableDatabase());
    }

    /**
     * This will return a map containing all the tables in the database as well as their columns
     * @return a {@link Map<String, Set<String>>}
     */
    public Map<String, Set<String>> getSchema()
    {
        Set<String> tables = getTables();
        Map<String, Set<String>> schema = new TreeMap<>();

        for(String table : tables)
        {
            Set<String> columns = getColumns(table);
            schema.put(table, columns);
        }
        return schema;
    }

    /**
     * This will return all the columns expected to be found in the specified table assuming it exists
     * @param table is the name of the table
     * @return a {@link Set<String>}
     */
    public Set<String> getColumns(String table)
    {
        return getTableColumnsSet(getReadableDatabase(), table);
    }

    /**
     * This will take the current version of the database and attempt to migrate it to the next version.
     *
     * @param currentVersion is the current version of the database.
     */
    @Override
    @CallSuper
    protected void upgrade(SQLiteDatabase db, int currentVersion) throws Exception {
        Logger.info(" - Daomaster database upgrade from version " + currentVersion + " to " + (currentVersion + 1) + ".");
    }

    /**
     * This will take the current version of the database and try to downgrade it to one step older.
     *
     * @param currentVersion is the current version of the database.
     */
    @Override
    @CallSuper
    protected void downgrade(SQLiteDatabase db, int currentVersion) throws Exception {
        Logger.info(" - Daomaster database downgrade from version " + currentVersion + " to " + (currentVersion + 1) + ".");
    }

    /**
     * Used to check if the database file we wish to use exists or not in internal storage.
     * @return true if the database file exists
     */
    public boolean exists() {
        File dbFile = getLocationFile(Location.WORKING_DATABASE_DIR, false);
        return (dbFile != null && dbFile.exists());
    }


    /**
     * This will delete the database file if it exists
     * @return true if the file was present before and deleted
     */
    public boolean delete() {
        boolean internalDeleted = true;
        try {
            getWritableDatabase().close();
            File dbFile = getLocationFile(Location.WORKING_DATABASE_DIR, false);
            if (dbFile != null) {
                internalDeleted = dbFile.delete();
            }
        } catch (Exception e) {
            Logger.warning(e.getMessage());
        }
        try {
            getContext().deleteDatabase(getDatabaseName());
        } catch (Exception e) {
            Logger.warning(e.getMessage());
            internalDeleted = false;
        }
        return internalDeleted;
    }


}