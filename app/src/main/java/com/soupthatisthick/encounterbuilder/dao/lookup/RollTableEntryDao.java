package com.soupthatisthick.encounterbuilder.dao.lookup;

import android.content.ContentValues;
import android.database.Cursor;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.soupthatisthick.encounterbuilder.model.lookup.RollTable;
import com.soupthatisthick.encounterbuilder.model.lookup.RollTableEntry;
import com.soupthatisthick.util.Logger;
import com.soupthatisthick.util.dao.DaoMaster;
import com.soupthatisthick.util.dao.SqlUtil;
import com.soupthatisthick.util.dao.WriteDao;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by Owner on 5/9/2017.
 * Copyright of Stuart Marr Erskine, all rights reserved.
 */

public class RollTableEntryDao extends WriteDao<RollTableEntry> {

    public static final String TBL_ROLL_TABLE_ENTRY = "ROLL_TABLE_ENTRY";
    public static final String COL_ENTRY_ID = "entryId";
    public static final String COL_TABLE_ID = "tableId";
    public static final String COL_MIN_ROLL = "minRoll";
    public static final String COL_MAX_ROLL = "maxRoll";
    public static final String COL_RESULT = "result";
    public static final String COL_UNIT = "unit";
    public static final String COL_REROLL_TABLE_ID = "rerollTableId";
    public static final String COL_DIE_QTY = "dieQty";
    public static final String COL_DIE_SIZE = "dieSize";
    public static final String COL_ROLL_MUL = "rollMul";
    public static final String COL_ROLL_AVG = "rollAvg";
    public static final String COL_UNIT_GP_VALUE = "unitGpValue";

    public RollTableEntryDao(DaoMaster daoMaster) throws IOException {
        super(daoMaster, TBL_ROLL_TABLE_ENTRY);
    }

    @Override
    public RollTableEntry readRecord(Cursor cursor) {
        RollTableEntry rollTableEntry = new RollTableEntry();

        rollTableEntry.setId(cursor.getLong(cursor.getColumnIndex(COL_ENTRY_ID)));
        rollTableEntry.setTableId(cursor.getLong(cursor.getColumnIndex(COL_TABLE_ID)));
        rollTableEntry.setMinRoll(cursor.getInt(cursor.getColumnIndex(COL_MIN_ROLL)));
        rollTableEntry.setMaxRoll(cursor.getInt(cursor.getColumnIndex(COL_MAX_ROLL)));
        rollTableEntry.setResult(cursor.getString(cursor.getColumnIndex(COL_RESULT)));
        rollTableEntry.setUnit(cursor.getString(cursor.getColumnIndex(COL_UNIT)));
        rollTableEntry.setReRollTableId(cursor.getLong(cursor.getColumnIndex(COL_REROLL_TABLE_ID)));
        rollTableEntry.setDieQty(cursor.getInt(cursor.getColumnIndex(COL_DIE_QTY)));
        rollTableEntry.setDieSize(cursor.getInt(cursor.getColumnIndex(COL_DIE_SIZE)));
        rollTableEntry.setRollMul(cursor.getInt(cursor.getColumnIndex(COL_ROLL_MUL)));
        rollTableEntry.setRollAvg(cursor.getInt(cursor.getColumnIndex(COL_ROLL_AVG)));
        rollTableEntry.setUnitGpValue(cursor.getDouble(cursor.getColumnIndex(COL_UNIT_GP_VALUE)));

        return rollTableEntry;
    }

    /**
     * This method will return all the columns that may be searched for matches in a text search.
     *
     * @return a Set of columns elidgible for searching with a like query
     */
    @Override
    public Set<String> getSearchableColumns() {
        Set<String> columns = new HashSet<>();
        columns.add(COL_RESULT);
        return columns;
    }

    /**
     * This is a list of columns that we will sort the results by when we return a dataset.
     *
     * @return a List of columns. Sorting is first done by the first column then by the next column etc until all columns have been ordered.
     */
    @Override
    public List<String> getColumnSortingOrder() {
        List<String> order = new ArrayList<>();

        order.add(COL_TABLE_ID);
        order.add(COL_ENTRY_ID);

        return order;
    }

    /**
     * This will build a mapping of column names to values from the specified record
     * so that they may be inserted.
     *
     * @param rollTableEntry
     */
    @Override
    public ContentValues getContentValues(RollTableEntry rollTableEntry) {
        ContentValues contentValues = new ContentValues();

        contentValues.put(COL_ENTRY_ID, rollTableEntry.getId());
        contentValues.put(COL_TABLE_ID, rollTableEntry.getTableId());
        contentValues.put(COL_MIN_ROLL, rollTableEntry.getMinRoll());
        contentValues.put(COL_MAX_ROLL, rollTableEntry.getMaxRoll());
        contentValues.put(COL_RESULT, rollTableEntry.getResult());
        contentValues.put(COL_UNIT, rollTableEntry.getUnit());
        contentValues.put(COL_REROLL_TABLE_ID, rollTableEntry.getReRollTableId());
        contentValues.put(COL_DIE_QTY, rollTableEntry.getDieQty());
        contentValues.put(COL_DIE_SIZE, rollTableEntry.getDieSize());
        contentValues.put(COL_ROLL_MUL, rollTableEntry.getRollMul());
        contentValues.put(COL_ROLL_AVG, rollTableEntry.getRollAvg());
        contentValues.put(COL_UNIT_GP_VALUE, rollTableEntry.getUnitGpValue());

        return contentValues;
    }

    /**
     * This will create a new model and return it
     *
     * @return a new Record to update
     */
    @Override
    protected RollTableEntry createNewModel() {
        return new RollTableEntry();
    }

    @Override
    public String getIdColumn() {
        return COL_ENTRY_ID;
    }

    @Override
    public Long getId(@NonNull RollTableEntry rollTableEntry) {
        return rollTableEntry.getId();
    }

    @Override
    public void setId(@NonNull RollTableEntry rollTableEntry, @Nullable Long id) {
        rollTableEntry.setId(id);
    }

    /**
     * This will return all results on where the specified dice roll is in the roll range for the
     * given table.
     * @param tableId
     * @param diceRoll
     * @return
     * @throws IOException
     */
    public List<RollTableEntry> lookupDiceRoll(long tableId, int diceRoll) throws IOException {
        String whereClause = SqlUtil.all(
            COL_TABLE_ID + "=?",
            COL_MIN_ROLL + "<=?",
            COL_MAX_ROLL + ">=?"
        );

        Logger.info(
            String.format(
                "%s <= [%s,%s,%s]",
                whereClause,
                "" + tableId,
                "" + diceRoll,
                "" + diceRoll
            )
        );
        return getRecords(
            whereClause,
            new String[] {
                "" + tableId,
                "" + diceRoll,
                "" + diceRoll
            }
        );
    }

    /**
     * This is used to retrieve all table entries that belong to the specified table if
     * @param rollTableId
     * @return all entries belonging to the table with the specified id
     * @throws IOException when the table is not present or the id is null
     */
    public List<RollTableEntry> getAllEntriesForTable(@NonNull Long rollTableId) throws IOException
    {
        if (rollTableId==null) {
            return getAllRecords(); // The table is null so we get ALL records instead
        } else {
            String key = "" + rollTableId;
            Logger.info(String.format("Getting all entries belonging to table %s", key));
            return getRecords(
                (COL_TABLE_ID + "=?"),
                new String[] {
                    key
                },
                null,
                null,
                COL_MIN_ROLL
            );
        }
    }

}
