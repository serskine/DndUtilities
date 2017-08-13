package com.soupthatisthick.encounterbuilder.util;

/**
 * Created by Owner on 2/16/2017.
 * Copyright of Stuart Marr Erskine, all rights reserved.
 */

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.CallSuper;
import android.support.annotation.NonNull;

import com.soupthatisthick.util.FileHelper;
import com.soupthatisthick.util.Logger;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * @author Danny Remington - MacroSolve
 *
 *         Helper class for sqlite database.
 */
public abstract class DatabaseHelper extends SQLiteOpenHelper
{

    /*
     * The Android's default system path of the application database in internal
     * storage. The package of the application is part of the path of the
     * directory.
     */
    protected final String DB_DIR;
    protected final String DB_NAME;
    protected final String DB_PATH;
    protected final String OLD_DB_PATH;

    protected final Context myContext;

    private boolean createDatabase = false;
    private boolean upgradeDatabase = false;
    private boolean copyToExternalStorage = false;


    /**
     * Constructor Takes and keeps a reference of the passed context in order to
     * access to the application assets and resources.
     *
     * @param context
     */
    public DatabaseHelper(Context context, String dbDirectory, String dbName, int dbVersion) {
        super(context, dbName, null, dbVersion);

        myContext = context;


        DB_NAME = dbName;

        // Get the path of the database that is based on the context.
        DB_PATH = myContext.getDatabasePath(DB_NAME).getAbsolutePath();

        DB_DIR = dbDirectory;

        // This is where we will put a copy of the old database file.
        OLD_DB_PATH = myContext.getExternalFilesDir(null) + "/old_" + DB_NAME;

        initializeDataBase();

    }

    /**
     * This is used to retrieve all of the database sql statements required to migrate from
     * oldVersion to newVersion
     * @param prevVersion is the version of the database before migration
     * @param targetVersion is the version of the database we want to be at.
     */
    protected final void migrateDatabase(SQLiteDatabase db, int prevVersion, int targetVersion) throws Exception
    {
        if (targetVersion > prevVersion)
        {
            for(int version = prevVersion; version < targetVersion; version++)
            {
                upgrade(db, version);

            }
        } else if (targetVersion < prevVersion) {
            for(int version = prevVersion; version > targetVersion; version--)
            {
                downgrade(db, version);
            }
        }
    }



    /**
     * This will upgrade the database from the current version to the one version after it
     * @param db
     * @param currentVersion
     * @throws Exception
     */
    protected abstract void upgrade(SQLiteDatabase db, int currentVersion) throws Exception;

    /**
     * This will downgrade the database from the current version to one version later
     * @param db
     * @param currentVersion
     * @throws Exception
     */
    protected abstract void downgrade(SQLiteDatabase db, int currentVersion) throws Exception;


    /**
     * Upgrade the database in internal storage if it exists but is not current.
     * Create a new empty database in internal storage if it does not exist.
     */
    public void initializeDataBase() {
        /*
         * Creates or updates the database in internal storage if it is needed
         * before opening the database. In all cases opening the database copies
         * the database in internal storage to the cache.
         */
        getWritableDatabase();

        if (createDatabase) {
            copyToExternalStorage = true;
            /*
             * If the database is created by the copy method, then the creation
             * code needs to go here. This method consists of copying the new
             * database from assets into internal storage and then caching it.
             */
            try {
                /*
                 * Write over the empty data that was created in internal
                 * storage with the one in assets and then cache it.
                 */
                copyDatabaseFromAssetsFolderToSystemFolder();
            } catch (IOException e) {
                Logger.error(e.getMessage(), e);
            }
        } else if (upgradeDatabase) {
            copyToExternalStorage = true;
            /*
             * If the database is upgraded by the copy and reload method, then
             * the upgrade code needs to go here. This method consists of
             * renaming the old database in internal storage, create an empty
             * new database in internal storage, copying the database from
             * assets to the new database in internal storage, caching the new
             * database from internal storage, loading the data from the old
             * database into the new database in the cache and then deleting the
             * old database from internal storage.
             */
            try {
                Logger.info("Copying of the database file to a new location");
                Logger.info(" - " + DB_PATH + " => " + OLD_DB_PATH);
                FileHelper.copyFile(DB_PATH, OLD_DB_PATH);  // Remember the old copy of the database

                copyDatabaseFromAssetsFolderToSystemFolder();

                /*
                 * Add code to load data into the new database from the old
                 * database and then delete the old database from internal
                 * storage after all data has been transferred.
                 */

            } catch (IOException e) {
                throw new Error("Error copying database", e);
            }
        }

        // Copy from the system folder into the shared external storage folder
        if (copyToExternalStorage) {
            try {
                copyDatabaseFromSystemFolderToSharedExternalStorage();
                copyToExternalStorage = false;
                Logger.info("Finished copying the used database into the shared external storage folder.");
            } catch (Exception e) {
                copyToExternalStorage = true;   // Attempt again next time.
                Logger.error("Failed to copy used system database into shared external storage.", e);
            }
        }

    }

    /**
     * This is used to copy the internal storage database out to the external file location
     * @throws IOException
     */
    private void copyDatabaseFromSystemFolderToSharedExternalStorage() throws IOException {
        String inputPath = DB_PATH;
        String outputPath = myContext.getExternalFilesDir(null) + "/" + DB_NAME;
        Logger.info("Copying the system database to the external files folder for sharing.");
        Logger.info("- From " + inputPath + " => " + outputPath);
        /*
         * Close SQLiteOpenHelper so it will commit the created empty database
         * to internal storage.
         */
        close();

        /*
         * Open the database in the assets folder as the input stream.
         */
        InputStream myInput =  new FileInputStream(inputPath);

        /*
         * Open the empty theDaoMaster in interal storage as the output stream.
         */
        OutputStream myOutput = new FileOutputStream(outputPath);

        /*
         * Copy over the empty theDaoMaster in internal storage with the database in the
         * assets folder.
         */
        FileHelper.copyFile(myInput, myOutput);

        /*
         * Access the copied database so SQLiteHelper will cache it and mark it
         * as created.
         */
        getWritableDatabase().close();
    }

    private void copyDatabaseFromAssetsFolderToSharedExternalStorage() throws IOException {
        Logger.info("Copying assets => external storage");

        String outputPath = myContext.getExternalFilesDir(null) + "/" + DB_NAME;

        // Close SQLiteOpenHelper so it will commit the created empty database
        // to internal storage.
        close();

        // Open the database in the assets folder as the input stream.
        InputStream myInput = myContext.getAssets().open(DB_NAME);

        // Open the empty theDaoMaster in external shared storage as the output stream.
        OutputStream myOutput = new FileOutputStream(outputPath);

        // Copy over the empty theDaoMaster shared external storage with the database in the assets folder.
        FileHelper.copyFile(myInput, myOutput);

        // Access the copied database so SQLiteHelper will cache it and mark it as created.
        getWritableDatabase().close();
    }

    /**
     * Copies your database from your local assets-folder to the just created
     * empty database in the system folder, from where it can be accessed and
     * handled. This is done by transfering bytestream.
     * */
    private void copyDatabaseFromAssetsFolderToSystemFolder() throws IOException {
        /*
         * Close SQLiteOpenHelper so it will commit the created empty database
         * to internal storage.
         */
        close();

        /*
         * Open the empty theDaoMaster in interal storage as the output stream.
         */
        Logger.info(" - opening target file " + DB_PATH);
        OutputStream myOutput = new FileOutputStream(DB_PATH);

        /*
         * Open the database in the assets folder as the input stream.
         */
        Logger.info(" - opening source file " + DB_NAME);
        InputStream myInput = myContext.getAssets().open(DB_NAME);

        /*
         * Copy over the empty theDaoMaster in internal storage with the database in the
         * assets folder.
         */
        Logger.info(" - copying from " + DB_NAME + " => " + DB_PATH);
        FileHelper.copyFile(myInput, myOutput);

        /*
         * Access the copied database so SQLiteHelper will cache it and mark it
         * as created.
         */
        Logger.info(" - closing writable database to it will be marked as cached and created.");
        getWritableDatabase().close();
    }

    /*
     * This is where the creation of tables and the initial population of the
     * tables should happen, if a database is being created from scratch instead
     * of being copied from the application package assets. Copying a database
     * from the application package assets to internal storage inside this
     * method will result in a corrupted database.
     * <P>
     * NOTE: This method is normally only called when a database has not already
     * been created. When the database has been copied, then this method is
     * called the first time a reference to the database is retrieved after the
     * database is copied since the database last cached by SQLiteOpenHelper is
     * different than the database in internal storage.
     */
    @Override
    @CallSuper
    public void onCreate(SQLiteDatabase db) {
        /*
         * Signal that a new database needs to be copied. The copy process must
         * be performed after the database in the cache has been closed causing
         * it to be committed to internal storage. Otherwise the database in
         * internal storage will not have the same creation timestamp as the one
         * in the cache causing the database in internal storage to be marked as
         * corrupted.
         */
        createDatabase = true;
    }

    /**
     * Called only if version number was changed and the database has already
     * been created. Copying a database from the application package assets to
     * the internal data system inside this method will result in a corrupted
     * database in the internal data system.
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        /*
         * Signal that the database needs to be upgraded for the copy method of
         * creation. The copy process must be performed after the database has
         * been opened or the database will be corrupted.
         */
        upgradeDatabase = true;

        /*
         * This will upgrade by reading a sql file and executing the commands in
         * it.
         */
        try {
            migrateDatabase(db, oldVersion, newVersion);
        } catch (Exception ex) {
            Logger.error("Failed to migrate the database from version " + oldVersion + " => version " + newVersion, ex);
        }
    }

    /**
     * Called everytime the database is opened by getReadableDatabase or
     * getWritableDatabase. This is called after onCreate or onUpgrade is
     * called.
     */
    @Override
    public void onOpen(SQLiteDatabase db) {
        super.onOpen(db);
    }

    /**
     * This is a shorthand way of saying executing all the sql statements located within the specified file
     * on the specified database
     * @param db to have the sql statements executed on
     * @param fileName is the name of the file that contains all of the sql statements
     * @throws IOException
     */
    protected void execSqlFile(SQLiteDatabase db, String fileName) throws IOException
    {
        Logger.info("Parsing database file from " + Text.quote(fileName));
        InputStream is = myContext.getResources().getAssets().open(
                fileName
        );

        String[] statements = FileHelper.parseSqlFile(is);

        for (String statement : statements) {
            db.execSQL(statement);
        }
    }


    /**
     * This will attempt to delete the underlying database file.
     * @return true if the file was deleted successfully.
     */
    public boolean delete()
    {
        Logger.info("Deleting the database file " + DB_PATH);
        File file = new File(DB_PATH);
        return file.delete();
    }

    /**
     * This will determine if the database file exists or not.
     * @return
     */
    public boolean exists()
    {
        File file = new File(DB_PATH);
        return file.exists();
    }


    /**
     * This will attempt to execute the specified SQL statement on the provided database.
     * If an exception occurs it will simply catch the exception and log it's details. This
     * allows us to continue on executing as if nothing happened.

     * @param db
     * @param statement
     * @return true if the statement successfully executed and false if it did not.
     */
    protected static final boolean executeSQL(@NonNull String tag, SQLiteDatabase db, String statement)
    {
        try {
            db.execSQL(statement);
            Logger.info(String.format("[%s] SUCCESS! %s", tag, statement));
            return true;
        } catch (SQLiteException e) {
            Logger.error(String.format("[%s] FAILED!! %s", tag, statement), e);
            return false;
        }
    }
}