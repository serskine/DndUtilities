package com.soupthatisthick.util.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.soupthatisthick.util.Logger;

import java.io.IOException;

public class MockDaoMaster extends DaoMaster {

    // Don't assign 0 to these values. They get used later.
    private int createCalls;
    private int upgradeCalls;
    private int downgradeCalls;

    public MockDaoMaster(Context context, String dbDirectory, String dbName, int version) throws IOException {
        super(context, dbDirectory, dbName, version);
        Logger.debug("Finished constructor for " + this.getClass().getSimpleName() + ".");
        Logger.debug("createCalls = " + createCalls + ", upgradeCalls = " + upgradeCalls + ", downgradeCalls = " + downgradeCalls + ".");
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        super.onCreate(db);
        this.createCalls++;
        Logger.debug("Finished onCreate()");
    }

    @Override
    protected void upgrade(SQLiteDatabase db, int currentVersion) throws Exception {
        super.upgrade(db, currentVersion);
        this.upgradeCalls++;
        db.setVersion(currentVersion+1);
        Logger.debug("Finished upgrade()");
    }

    @Override
    protected void downgrade(SQLiteDatabase db, int currentVersion) throws Exception {
        super.downgrade(db, currentVersion);
        this.downgradeCalls++;
        db.setVersion(currentVersion-1);
        Logger.debug("Finished downgrade()");
    }

    public int getUpgradeCalls() {
        return this.upgradeCalls;
    }

    public int getDowngradeCalls() {
        return this.downgradeCalls;
    }

    public int getCreateCalls() {
        return this.createCalls;
    }

    public String description() {
        StringBuilder sb = new StringBuilder();

        sb.append("\n");
        sb.append("\nDatabase Name: " + getDatabaseName());
        sb.append("\nInternal file path: " + getLocationPath(Location.INTERNAL_FILES_DIR, false));
        sb.append("\nExternal file path: " + getLocationPath(Location.EXTERNAL_FILES_DIR, false));
        sb.append("\nWorking file path: " + getLocationPath(Location.WORKING_DATABASE_DIR, false));
        sb.append("\n");
        sb.append("\n");
        sb.append("\nBackup file name: " + getFileName(false));
        sb.append("\nInternal file path for backup: " + getLocationPath(Location.INTERNAL_FILES_DIR, true));
        sb.append("\nExternal file path for backup: " + getLocationPath(Location.EXTERNAL_FILES_DIR, true));
        sb.append("\nWorking file path for backup: " + getLocationPath(Location.WORKING_DATABASE_DIR, true));
        sb.append("\n");
        sb.append("\nCreate calls:      " + getCreateCalls());
        sb.append("\nUpgrade calls:     " + getUpgradeCalls());
        sb.append("\nDowngrade calls:   " + getDowngradeCalls());
        sb.append("\n");

        return sb.toString();
    }

}