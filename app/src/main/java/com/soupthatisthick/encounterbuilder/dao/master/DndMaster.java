package com.soupthatisthick.encounterbuilder.dao.master;

import android.content.Context;
import android.content.res.Resources;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.NonNull;
import android.util.Log;

import com.soupthatisthick.util.FileHelper;
import com.soupthatisthick.util.Logger;
import com.soupthatisthick.util.dao.DaoMaster;

import java.io.IOException;
import java.io.InputStream;

import soupthatisthick.encounterapp.R;

/**
 * Created by Owner on 1/2/2017.
 * Copyright of Stuart Marr Erskine, all rights reserved.
 */

public class DndMaster extends DaoMaster {

    public static final int VERSION = 13;
    private static final String DB_FILE = "dnd.db";

    // This represents the number of sql errors we will allow before throwing a fatal error.
    public static final int ALLOWABLE_SQL_STATEMENT_ERROR_COUNT = 10;

    public DndMaster(Context context, String filePath, String fileName, int version) throws IOException {

        super(  context,
                context.getApplicationContext().getPackageName() + filePath,
                fileName,
                version
        );
        Logger.debug("Created database " + getDatabaseName());
        Logger.debug(" - " + getReadableDatabase().getPath());

    }

    public DndMaster(Context context) throws IOException {
        this(context, DATABASE_FILE_PATH, DB_FILE, VERSION);
    }

    /**
     * This method is used to process an sql file.
     * @param db is the database we will perform the operations on
     *           @param tag is used to identify the spurce in the logs
     * @param is is the input stream we retrieve the sql from
     * @param allowableErrorCount is the number of sql statement errors we will allow before aborting
     * @returnthe number of errors encountered while executing the sql statements
     */
    protected static final int processSql(
        @NonNull String tag,
        @NonNull SQLiteDatabase db,
        @NonNull InputStream is,
        int allowableErrorCount
    )
    {
        int errorCount = 0;
        try {
            String sql = FileHelper.convertStreamToString(is);
            String[] statements = FileHelper.parseSqlStatements(sql);
            String errorStatements = "";

            for (String statement : statements) {
                boolean result = executeSQL(tag, db, statement);  // Always completes. Ignore the return value
                errorCount += (result) ? 0 : 1;
                errorStatements += "error[" + errorCount + "] = " + statement + "\n";
                if (errorCount > allowableErrorCount)
                {
                    String errorMessage = "Error while processing sql statement from file with tag " + tag + ".\n";
                    errorMessage += " - We have reached the maximum number of sql statement errors (" + allowableErrorCount + ")\n";
                    errorMessage += errorStatements;
                    throw new RuntimeException(errorMessage);
                }
            }
        } catch (Exception e) {
            Logger.error("Failed to parse sql statements from input stream", e);
        }
        return errorCount;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        super.onCreate(db);

        Logger.info("Created database " + db.getPath() + " version " + db.getVersion() + ".");
        Resources res = this.getContext().getResources();
        InputStream schemaIs = res.openRawResource(R.raw.dnd_db_schema);

        try {
            processSql("dnd_db_schema", db, schemaIs, ALLOWABLE_SQL_STATEMENT_ERROR_COUNT);
        } catch (Exception e) {
            Logger.error("Failed to create database " + DB_FILE + ".", e);
        }
    }

    /**
     * This will take the current version of the database and attempt to migrate it to the next version.
     *
     * @param currentVersion is the current version of the database.
     */
    @Override
    protected void upgrade(SQLiteDatabase db, int currentVersion) throws Exception {
        super.upgrade(db, currentVersion);
        Resources res = this.getContext().getResources();
        Logger.info("Upgrading database to version " + currentVersion + ".");
        InputStream is;
        String tag;
        switch(currentVersion) {
            case 1:
                is = res.openRawResource(R.raw.dnd_migrate_1_to_2);
                tag = "dnd_migrate_1_to_2";
                break;
            case 2:
                is = res.openRawResource(R.raw.dnd_migrate_2_to_3);
                tag = "dnd_migrate_2_to_3";
                break;
            case 3:
                is = res.openRawResource(R.raw.dnd_migrate_3_to_4);
                tag = "dnd_migrate_3_to_4";
                break;
            case 4:
                is = res.openRawResource(R.raw.dnd_migrate_4_to_5);
                tag = "dnd_migrate_4_to_5";
                break;
            case 5:
                is = res.openRawResource(R.raw.dnd_migrate_5_to_6);
                tag = "dnd_migrate_5_to_6";
                break;
            case 6:
                is = res.openRawResource(R.raw.dnd_migrate_6_to_7);
                tag = "dnd_migrate_6_to_7";
                break;
            case 7:
                is = res.openRawResource(R.raw.dnd_migrate_7_to_8);
                tag = "dnd_migrate_7_to_8";
                break;
            case 8:
                is = res.openRawResource(R.raw.dnd_migrate_8_to_9);
                tag = "dnd_migrate_8_to_9";
                break;
            case 9:
                is = res.openRawResource(R.raw.dnd_migrate_9_to_10);
                tag = "dnd_migrate_9_to_10";
                break;
            case 10:
                is = res.openRawResource(R.raw.dnd_migrate_10_to_11);
                tag = "dnd_migrate_10_to_11";
                break;
            case 11:
                is = res.openRawResource(R.raw.dnd_migrate_11_to_12);
                tag = "dnd_migrate_11_to_12";
                break;
            case 12:
                is = res.openRawResource(R.raw.dnd_migrate_12_to_13);
                tag = "dnd_migrate_12_to_13";
                break;
            default:
                is = null;
                tag = "dnd_db";
                break;
        }


        if (is==null)
        {
            Logger.info(" - no data to upgrade from version " + currentVersion);
        } else {
            String sql = FileHelper.convertStreamToString(is);
            String[] statements = FileHelper.parseSqlStatements(sql);

            for (String statement : statements) {
                Logger.info(" - executing " + statement);
                executeSQL(tag, db, statement);  // Will always complete. Ignore the return value
            }
        }

    }

}
