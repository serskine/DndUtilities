package com.soupthatisthick.encounterbuilder.dao.lookup;

import android.database.Cursor;

import com.soupthatisthick.encounterbuilder.model.lookup.Table;
import com.soupthatisthick.util.dao.DaoMaster;
import com.soupthatisthick.util.dao.ReadDao;
import com.soupthatisthick.util.dao.SqlUtil;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by Owner on 2/17/2017.
 * Copyright of Stuart Marr Erskine, all rights reserved.
 */

public class TableDao extends ReadDao<Table> {

    public static final String TBL_SQLITE_MASTER = SqlUtil.TBL_SQLITE_MASTER;
    public static final String COL_TYPE = "type";
    public static final String COL_NAME = "name";
    public static final String COL_TABLE_NAME = "tbl_name";
    public static final String COL_ROOTPAGE = "rootpage";
    public static final String COL_SQL = "sql";

    public TableDao(DaoMaster daoMaster) throws IOException {
        super(daoMaster, TBL_SQLITE_MASTER);
    }

    @Override
    public Table readRecord(Cursor cursor) {
        Table table = new Table();

        table.setName(cursor.getString(cursor.getColumnIndex(COL_NAME)));
        table.setTableName(cursor.getString(cursor.getColumnIndex(COL_TABLE_NAME)));
        table.setType(cursor.getString(cursor.getColumnIndex(COL_TYPE)));
        table.setSql(cursor.getString(cursor.getColumnIndex(COL_SQL)));
        table.setRootPage(cursor.getLong(cursor.getColumnIndex(COL_ROOTPAGE)));

        return table;
    }

    @Override
    public Set<String> getSearchableColumns() {
        HashSet<String> columns = new HashSet<>();
        columns.add(COL_NAME);
        columns.add(COL_TABLE_NAME);
        columns.add(COL_TYPE);
        // We purposly don't want to search the sql for a table
        columns.add(COL_ROOTPAGE);
        return columns;
    }

    /**
     * This is a list of columns that we will sort the results by when we return a dataset.
     *
     * @return a List of columns. Sorting is first done by the first column then by the next column etc until all columns have been ordered.
     */
    @Override
    public List<String> getColumnSortingOrder() {
        List<String> columns = new ArrayList<>();
        columns.add(COL_TABLE_NAME);
        return columns;

    }
}
