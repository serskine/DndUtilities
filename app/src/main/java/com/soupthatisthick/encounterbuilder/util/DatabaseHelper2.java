package com.soupthatisthick.encounterbuilder.util;

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
 * Created by Owner on 5/27/2018.
 * Copyright of Stuart Marr Erskine, all rights reserved.
 */
public abstract class DatabaseHelper2 extends SQLiteOpenHelper {

    private final Context context;
    private final SQLiteDatabase.CursorFactory factory;
    private final String name;
    private final int version;

    private final File externalDir;
    private final File internalDir;

    private boolean createdDatabase = false;
    private boolean upgradeDatabase = false;


    /**
     * Create a helper object to create, open, and/or manage a database.
     * This method always returns very quickly.  The database is not actually
     * created or opened until one of {@link #getWritableDatabase} or
     * {@link #getReadableDatabase} is called.
     *
     * @param context to use to open or create the database
     * @param name    of the database file, or null for an in-memory database
     * @param factory to use for creating cursor objects, or null for the default
     * @param version number of the database (starting at 1); if the database is older,
     *                {@link #onUpgrade} will be used to upgrade the database; if the database is
     *                newer, {@link #onDowngrade} will be used to downgrade the database
     */
    public DatabaseHelper2(
            Context context,
            String name,
            SQLiteDatabase.CursorFactory factory,
            int version
    ) {
        super(context, name, factory, version);
        this.context = context;
        this.factory = factory;
        this.name = name;
        this.version = version;
        externalDir = context.getExternalFilesDir(null);
        internalDir = context.getFilesDir();

        updateDatabase();
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
        createdDatabase = true;
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

    public String getFileName(boolean isBackup) {
        return (isBackup) ? backupName(name) : name;
    }

    public String getInternalFilePath(boolean isBackup) {
        return internalDir.getAbsolutePath() + "/" + getFileName(isBackup);
    }

    public String getExternalFilePath(boolean isBackup) {
        return externalDir.getAbsolutePath() + "/" + getFileName(isBackup);
    }

    public void copyFromAssetsToExternalStorage(boolean isBackup) throws IOException {
        final String targetFile = getExternalFilePath(isBackup);
        close();

        OutputStream myOutput = new FileOutputStream(targetFile);
        InputStream myInput = context.getAssets().open(name);

        FileHelper.copyFile(myInput, myOutput);
        Logger.info("Copied assets/" + name + " => " + targetFile );

        getWritableDatabase().close();
    }

    public void copyFromAssetsToInternalStorage(boolean isBackup) throws IOException {
        final String targetFile = getInternalFilePath(isBackup);
        close();

        OutputStream myOutput = new FileOutputStream(targetFile);
        InputStream myInput = context.getAssets().open(name);

        FileHelper.copyFile(myInput, myOutput);
        Logger.info("Copied assets/" + name + " => " + targetFile );

        getWritableDatabase().close();
    }

    public void copyFromInternalToExternalStorage(boolean isBackup) throws IOException {
        final String outputFile = getExternalFilePath(isBackup);
        final String inputFile = getInternalFilePath(false);
        close();

        OutputStream myOutput = new FileOutputStream(outputFile);
        InputStream myInput = new FileInputStream(inputFile);

        FileHelper.copyFile(myInput, myOutput);
        Logger.info("Copied " + inputFile + " => " + outputFile );

        getWritableDatabase().close();
    }


    public void copyFromExternalToInternalStorage(boolean isBackup) throws IOException {
        final String outputFile = getInternalFilePath(isBackup);
        final String inputFile = getExternalFilePath(false);
        close();

        OutputStream myOutput = new FileOutputStream(outputFile);
        InputStream myInput = new FileInputStream(inputFile);

        FileHelper.copyFile(myInput, myOutput);
        Logger.info("Copied " + inputFile + " => " + inputFile );

        getWritableDatabase().close();
    }

    public static final String backupName(final String originalName) {
        return "backup_of_" + originalName;
    }

    /**
     * This method will do the following.
     * - make a backup of the current working directory in external storage logging errors as they occur
     * - attempt to copy from external storage to internal working storage
     * - if we were unable to update the internal working storage from an external source, use the one found in the assets directory instead
     */
    public void updateDatabase() {
        boolean copiedFromExternal = false;
        try {
            Logger.info("Creating backup of the working database.");
            copyFromInternalToExternalStorage(true);    // Create the backup
        } catch (Exception e) {
            Logger.warning(e.getMessage());
        }
        try {
            Logger.info("Updating the working database from the external source.");
            copyFromExternalToInternalStorage(false);
            copiedFromExternal = true;
        } catch (Exception e) {
            Logger.warning(e.getMessage());
        }

        // We only want to update from the assets directory if we failed to copy from the external directory
        if  (   !copiedFromExternal
            &&  (   isUpgradeDatabase()
                ||  isCreatedDatabase()
                )
            ) {
            Logger.info("There was no external database to use. Updating the working database from the assets database instead.");
            try {
                copyFromAssetsToInternalStorage(false); // Overwrite the original
            } catch (IOException e) {
                Logger.warning(e.getMessage());
            }
        }

    }

    /**
     * This will create a file object to the internal storage file we us when running the app
     * @param isBackup determines the name of the file
     * @return a {@link File}
     */
    public File getInternalStorageFile(boolean isBackup) {
        return new File(getInternalFilePath(isBackup));
    }

    /**
     * This will create a file object to the external storage file we us when running the app
     * @param isBackup determines the name of the file
     * @return a {@link File}
     */
    public File getExternalStorageFile(boolean isBackup) {
        return new File(getExternalFilePath(isBackup));
    }

    /**
     * This will delete all originals and backup files in the internal and external storage directories
     */
    public void cleanAllQuietly() {
        try {
            getInternalStorageFile(false).delete();
        } catch (Exception e) {
            Logger.warning(e.getMessage());
        }
        try {
            getExternalStorageFile(false).delete();
        } catch (Exception e) {
            Logger.warning(e.getMessage());
        }
        try {
            getInternalStorageFile(true).delete();
        } catch (Exception e) {
            Logger.warning(e.getMessage());
        }
        try {
            getExternalStorageFile(true).delete();
        } catch (Exception e) {
            Logger.warning(e.getMessage());
        }
    }

    /**
     * This is used to retrieve all of the database sql statements required to migrate from
     * oldVersion to newVersion
     * @param prevVersion is the version of the database before migration
     * @param targetVersion is the version of the database we want to be at.
     */
    protected final void migrateDatabase(SQLiteDatabase db, int prevVersion, int targetVersion) throws Exception
    {
        Logger.info("Migrating database " + db.getPath() + " from version " + prevVersion + " to version " + targetVersion);
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
     * @param db is the database to upgrade
     * @param currentVersion is the current version of the database
     * @throws Exception if we can't upgrade the database
     */
    protected void upgrade(SQLiteDatabase db, int currentVersion) throws Exception {
        throw new RuntimeException("We can't upgrade this database from version " + currentVersion +".");
    }

    /**
     * This will downgrade the database from the current version to one version later
     * @param db is the database we wish to downgrade
     * @param currentVersion is the current version of the database
     * @throws Exception if we can't downgrade the database
     */
    protected void downgrade(SQLiteDatabase db, int currentVersion) throws Exception {
        throw new RuntimeException("We can't downgrade this database from version " + currentVersion + ".");
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

    protected final Context getContext() {
        return this.context;
    }

    /**
     * This will determine if the database was created for the first time.
     * @return true is the database is new
     */
    public boolean isCreatedDatabase() {
        return createdDatabase;
    }

    /**
     * This will determine if the database was upgraded
     * @return true if it was upgraded (or downgraded) false otherwise
     */
    public boolean isUpgradeDatabase() {
        return upgradeDatabase;
    }
}
