package com.soupthatisthick.encounterbuilder.util.view;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;

import com.soupthatisthick.encounterbuilder.model.FieldSchema;
import com.soupthatisthick.encounterbuilder.util.DatabaseHelper2;
import com.soupthatisthick.encounterbuilder.util.adapter.JsonNode;
import com.soupthatisthick.util.dao.SqlUtil;

import java.util.ArrayList;
import java.util.Set;

import static com.soupthatisthick.util.dao.SqlUtil.closeCursor;

/**
 * Created by Owner on 9/30/2018.
 * Copyright of Stuart Marr Erskine, all rights reserved.
 */
public class DatabaseHelper3 extends DatabaseHelper2 {
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
    public DatabaseHelper3(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    /**
     * This will create the json representing the entire contents of the database
     * @return the entire contents
     */
    public JsonNode getContentsJson() {
        JsonNode.Builder schemaBuilder = JsonNode.builder();

        Set<String> tables = SqlUtil.getTables(getReadableDatabase());
        for(String table : tables) {
            schemaBuilder = schemaBuilder.property(table, getTableJson(table));
        }
        return schemaBuilder.build();
    }

    public JsonNode getTableJson(String table) {
        SQLiteDatabase db = getReadableDatabase();
        JsonNode.Builder tableBuilder = JsonNode.builder();
        Cursor cursor = null;
        ArrayList<JsonNode> rows = new ArrayList<>();

        try {
            cursor = db.query(table, null, null, null, null, null, null);
            cursor.moveToFirst();
            while (cursor.moveToNext()) {
                rows.add(getRowJson(cursor));
            }
        } finally {
            closeCursor(cursor);
        }

        return tableBuilder.values(rows.toArray()).build();
    }

    public JsonNode getTableSchema(String table) {
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = null;

        try {
            cursor = db.query(table, null, null, null, null, null, null, "1");
            int numColumns = cursor.getColumnCount();

            JsonNode[] columns = new JsonNode[numColumns];
            for(int i=0; i<numColumns; i++) {
                columns[i] = getColumnSchema(cursor, i);
            }
            return JsonNode.builder()
                    .property("numColumns", numColumns)
                    .property("columns", JsonNode.builder()
                        .values(columns)
                        .build()
                    )
                    .property("extras", getBundleJson(cursor.getExtras()))
                    .build();
        } finally {
            SqlUtil.closeCursor(cursor);
        }
    }

    /**
     * Recursively creates a Json object based on the bundle contents
     * @param bundle contains the contents
     * @return a JsonNode
     */
    public JsonNode getBundleJson(Bundle bundle) {
        JsonNode.Builder builder = JsonNode.builder();
        if (bundle==null) {
            return builder.build();
        }
        for(String key : bundle.keySet()) {
            Object value = bundle.get(key);
            if (value instanceof Bundle) {
                builder.property(key, getBundleJson((Bundle) value));
            } else {
                builder.property(key, value);
            }
        }
        return builder.build();
    }

    public JsonNode getColumnSchema(Cursor cursor, int idx) {
        return JsonNode.builder()
            .property("index", idx)
            .property("name", cursor.getColumnName(idx))
            .property("type", cursor.getType(idx))
            .build();
    }

    /**
     * This is used to get json representing the current table row
     * @param cursor
     * @return
     */
    JsonNode getRowJson(Cursor cursor) {
        JsonNode.Builder rowBuilder = JsonNode.builder();
        for (int i = 0; i < cursor.getColumnCount(); i++) {
            String name = cursor.getColumnName(i);
            Object value = SqlUtil.getColumnValue(cursor, i);
            rowBuilder = rowBuilder.property(name, value);
        }
        return rowBuilder.build();
    }

}




