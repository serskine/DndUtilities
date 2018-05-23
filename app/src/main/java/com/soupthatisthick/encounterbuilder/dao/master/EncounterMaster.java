package com.soupthatisthick.encounterbuilder.dao.master;

import android.content.Context;
import android.content.res.Resources;
import android.database.sqlite.SQLiteDatabase;

import com.soupthatisthick.util.FileHelper;
import com.soupthatisthick.util.Logger;
import com.soupthatisthick.util.dao.DaoMaster;

import java.io.IOException;
import java.io.InputStream;

import soupthatisthick.encounterapp.R;

/**
 * Created by Owner on 5/8/2017.
 * Copyright of Stuart Marr Erskine, all rights reserved.
 */

public class EncounterMaster extends DaoMaster {
        public static final int VERSION = 10;
        public static final String DB_FILE = "encounters.db";

        public EncounterMaster(Context context, String filePath, String fileName, int version) throws IOException {

            super(  context,
                    context.getApplicationContext().getPackageName() + filePath,
                    fileName,
                    version
            );
            Logger.debug("Created database " + getDatabaseName());
            Logger.debug(" - " + getReadableDatabase().getPath());

        }

        public EncounterMaster(Context context) throws IOException {
            this(context, DATABASE_FILE_PATH, DB_FILE, VERSION);
        }


        @Override
        public void onCreate(SQLiteDatabase db) {
            super.onCreate(db);

            Resources res = this.myContext.getResources();
            InputStream is = res.openRawResource(R.raw.encounters_db);
            String tag = "encounters_db";

            try {
                if (is == null) {
                    Logger.info(" - Failed to create a new database (" + DB_FILE + ")");
                } else {
                    String sql = FileHelper.convertStreamToString(is);
                    Logger.info("--- creation sql ---");

                    String[] statements = FileHelper.parseSqlStatements(sql);
                    for (String statement : statements) {
                        executeSQL(tag, db, statement);  // Always completes. Ignore the return value
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
            Resources res = this.myContext.getResources();
            InputStream is;
            String tag;

            switch(currentVersion) {
                case 1:
                    is = res.openRawResource(R.raw.encounters_migrate_1_to_2);
                    tag = "encounters_migrate_1_to_2";
                    break;
                default:
                    is = null;
                    tag = "encounters_db";
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
