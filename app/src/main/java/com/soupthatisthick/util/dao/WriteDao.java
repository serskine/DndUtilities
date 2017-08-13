package com.soupthatisthick.util.dao;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.soupthatisthick.encounterbuilder.ifaces.Crud;
import com.soupthatisthick.util.Logger;

import java.io.IOException;

/**
 * Created by Owner on 2/10/2017.
 * Copyright of Stuart Marr Erskine, all rights reserved.
 */

public abstract class WriteDao<Record> extends ReadDao<Record> implements Crud<Long, Record> {

    public WriteDao(DaoMaster daoMaster, @NonNull String tableName) throws IOException {
        super(daoMaster, tableName);
        initTableIfNotExists();
    }

    /**
     * This method is used to create the table we wish to edit when it does not exist.
     * This allows us to create a new WriteDao even though the table does not exist in the database.
     */
    protected void initTableIfNotExists() throws IOException {
        String table = getTable();
        Logger.info("Write Dao Schema for " + table);
        SQLiteDatabase db = daoMaster.getReadableDatabase();
        String tableSql = SqlUtil.getTableSql(db, table);
        Logger.info(
            (tableSql==null)
            ? "Table " + table + " is not present in the database."
            : tableSql
        );
    }
    /**
     * This will build a mapping of column names to values from the specified record
     * so that they may be inserted.
     */
    public abstract ContentValues getContentValues(Record record);

    /**
     * This will create a new model and return it
     * @return a new Record to update
     */
    protected abstract Record createNewModel();

    public abstract String getIdColumn();

    public abstract Long getId(@NonNull Record record);

    public abstract void setId(@NonNull Record record, @Nullable Long id);

    /**
     * This is used to create a new model to insert into the database.
     * @return the primary key for the newly inserted {@Record}
     */
    @Override
    public Record create() {
        Cursor cursor = null;
        Record record = null;

        Logger.debug("creating record in dao for table " + getTable() + " in theDaoMaster " + daoMaster.getDatabaseName());
        try {
            SQLiteDatabase db = daoMaster.getWritableDatabase();
            record = createNewModel();
            setId(record, null);    // This way it's set by the database
            ContentValues contentValues = getContentValues(record);

            long rowId = db.insert(getTable(), null, contentValues);

            cursor = SqlUtil.loadRow(db, getTable(), rowId);

            if (cursor.moveToFirst())
            {
                record = readRecord(cursor);
            } else {
                throw new RuntimeException("Row " + rowId + " is not present in table " + getTable());
            }

            Logger.info("record = " + record.toString());
        } catch (Exception e) {
            Logger.error(e.getMessage(), e);
            record = null;
        } finally {
            SqlUtil.closeCursor(cursor);
        }
        return record;
    }

    /**
     * This will load the {@link Record} from the database associated with the given primary key
     * @param theId identifies the record
     * @return the {@link Record} loaded from the database or null if it's not present
     */
    @Override
    public final Record load(@NonNull Long theId) {
        SQLiteDatabase db = daoMaster.getReadableDatabase();
        String[] args = new String[] { theId.toString() };

        String whereClause = "(" + getIdColumn() + "=?)";
        Cursor cursor = db.query(
            getTable(),
            null,
            whereClause,
            args,
            null,
            null,
            null,
            "1"
        );
        Record record = null;
        if (cursor.moveToFirst() && !cursor.isAfterLast()) {
            record = readRecord(cursor);
        }
        cursor.close();

        return record;
    }

    /**
     * This will get the id from the record
     * @param record that contains all the information we require
     * @return true if the record was updates successfully.
     */
    public final boolean update(@NonNull Record record)
    {
        Long id = getId(record);
        if (id==null) {
            Logger.debug("updating record (" + id.toString() + ") in dao for table " + getTable() + " in theDaoMaster " + daoMaster.getDatabaseName());
            return false;
        } else {
            return update(id, record);
        }
    }

    /**
     * This will update the specified row in the database
     * @param theId is the unique identifier for the record
     * @param record is the {@link Record} that contains all the data
     * @return true if the record was updated
     */
    @Override
    public boolean update(@NonNull Long theId, @NonNull Record record) {
        SQLiteDatabase db = daoMaster.getWritableDatabase();
        ContentValues contentValues = getContentValues(record);
        contentValues.put(getIdColumn(), theId);

        Logger.debug("updating record (" + theId.toString() + ") in dao for table " + getTable() + " in theDaoMaster " + daoMaster.getDatabaseName());

        String whereClause = "(" + getIdColumn() + "=?)";
        String[] args = new String[] { theId.toString() };
        int rowsAffected = db.update(getTable(), contentValues, whereClause, args);
        return (rowsAffected > 0);
    }

    /**
     * This will delete the specified record
     * @param theId identifies the record to delete
     * @return true if a record was deleted
     */
    @Override
    public boolean delete(@NonNull Long theId) {
        SQLiteDatabase db = daoMaster.getWritableDatabase();

        Logger.debug("deleting record (" + theId.toString() + ") in dao for table " + getTable() + " in theDaoMaster " + daoMaster.getDatabaseName());
        String whereClause = "(" + getIdColumn() + "=?)";
        String[] args = new String[] { theId.toString() };
        int rowsAffected = db.delete(getTable(), whereClause, args);
        return (rowsAffected > 0);
    }

    /**
     * This will delete all rows in the table
     */
    @Override
    public void clear() {
        Logger.debug("clearing dao table " + getTable() + " in theDaoMaster " + daoMaster.getDatabaseName());
        SQLiteDatabase db = daoMaster.getWritableDatabase();
        db.delete(getTable(), "1", null);
    }

    @Override
    public void logContents()
    {
        Logger.title("Logging contents of WriteDao with table name " + getTable() + " and primary key " + getIdColumn());
        super.logContents();
    }
}


