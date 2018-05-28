package com.soupthatisthick.encounterbuilder.dao.master;

import android.content.Context;
import android.content.res.Resources;
import android.database.sqlite.SQLiteDatabase;

import android.os.Build;
import android.support.annotation.RequiresApi;
import com.soupthatisthick.util.FileHelper;
import com.soupthatisthick.util.dao.DaoMaster;
import com.soupthatisthick.util.Logger;

import java.io.IOException;
import java.io.InputStream;

import soupthatisthick.encounterapp.R;

/**
 * Created by Owner on 2/5/2017.
 * Copyright of Stuart Marr Erskine, all rights reserved.
 */

public class LogsheetMaster extends DaoMaster {

    public static final int VERSION = 3;

    public static final String RAW_PATH = "android.resource://com.soupthatisthick.encounterbuilder//raw//";
    public static final String ASSET_PATH = "//data//data//com.soupthatisthick.encounterbuilder//databases//";
    public static final String DB_FILE = "logsheets.db";
    public static final String SQL_FILE = ASSET_PATH + "sql//logsheet_db.sql";

    //private String ASSET_PATH = mycontext.getApplicationContext().getPackageName()+"/databases/";

    @RequiresApi(api = Build.VERSION_CODES.ICE_CREAM_SANDWICH)
    public LogsheetMaster(Context context) throws IOException {

        super(
                context,
//                RAW_PATH,
                context.getApplicationContext().getExternalFilesDir(null) + DATABASE_FILE_PATH,
                DB_FILE,
                VERSION
        );
        Logger.debug("Created database " + getDatabaseName());
        Logger.debug(" - " + getReadableDatabase().getPath());
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        super.onCreate(db);

        Resources res = this.getContext().getResources();
        InputStream is = res.openRawResource(R.raw.logsheet_db);

        try {
            if (is == null) {
                Logger.info(" - Failed to create a new database (" + DB_FILE + ")");
            } else {
                String sql = FileHelper.convertStreamToString(is);
                Logger.info("--- creation sql ---");

                String[] statements = FileHelper.parseSqlStatements(sql);
                for (String statement : statements) {
                    executeSQL("logsheet_db", db, statement);  // Always completes. Ignore the return value
                }
            }
        } catch (Exception e) {
            Logger.error("Failed to create database " + DB_FILE + ". " + e.getMessage(), e);
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
        InputStream is;
        String tag;
        switch(currentVersion) {
            case 1:
                is = res.openRawResource(R.raw.logsheet_migrate_1_to_2);
                tag = "logsheet_migrate_1_to_2";
                break;
            case 2:
                is = res.openRawResource(R.raw.logsheet_migrate_2_to_3);
                tag = "logsheet_migrate_1_to_2";
                break;
            case 3:
                is = res.openRawResource(R.raw.logsheet_migrate_3_to_4);
                tag = "logsheet_migrate_2_to_3";
                break;
            default:
                is = null;
                tag = "unknown";
                break;
        }


        if (is==null)
        {
            Logger.info(" - no data to upgrade from version " + currentVersion);
        } else {
            String sql = FileHelper.convertStreamToString(is);
            Logger.info("--- migration sql ---");

            String[] statements = FileHelper.parseSqlStatements(sql);

            for (String statement : statements) {
                executeSQL(tag, db, statement);  // Will always complete. Ignore the return value
            }
        }

    }



}
