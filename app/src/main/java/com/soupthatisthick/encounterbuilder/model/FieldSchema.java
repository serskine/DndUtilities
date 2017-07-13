package com.soupthatisthick.encounterbuilder.model;

import android.support.annotation.NonNull;

import com.soupthatisthick.encounterbuilder.util.Text;

/**
 * Created by Owner on 4/17/2017.
 * Copyright of Stuart Marr Erskine, all rights reserved.
 */

public class FieldSchema {
    public Type getType() {
        return type;
    }

    public void setType(@NonNull Type type) {
        this.type = type;
    }

    public boolean isNotNull() {
        return notNull;
    }

    public void setNotNull(boolean notNull) {
        this.notNull = notNull;
    }

    public boolean isPrimaryKey() {
        return primaryKey;
    }

    public void setPrimaryKey(boolean primaryKey) {
        this.primaryKey = primaryKey;
    }

    public boolean isAutoIncrement() {
        return autoIncrement;
    }

    public void setAutoIncrement(boolean autoIncrement) {
        this.autoIncrement = autoIncrement;
    }

    public boolean isUnique() {
        return unique;
    }

    public void setUnique(boolean unique) {
        this.unique = unique;
    }

    public enum Type
    {
        INTEGER,
        TEXT,
        BLOB,
        REAL
    }

    private Type type;
    private boolean notNull, primaryKey, autoIncrement, unique;

    /**
     * Creates a new field specifying all the informaiton rquired of it.
     * @param type
     * @param notNull
     * @param primaryKey
     * @param autoIncrement
     * @param unique
     */
    public FieldSchema(@NonNull Type type, boolean notNull, boolean primaryKey, boolean autoIncrement, boolean unique)
    {
        this.type = type;
        this.notNull = notNull;
        this.primaryKey = primaryKey;
        this.autoIncrement = autoIncrement;
        this.unique = unique;
    }

    /**
     * Specifies only the type of information contained within. The following assumptions are made.
     * - Nullable values are allowed.
     * - It is not unique
     * - Autoincrementing is disabled.
     * - It is not a primary key
     * - Values need not be unique
     * @param type
     */
    public FieldSchema(@NonNull Type type)
    {
        this(type, false, false, false, false);
    }

    /**
     * This will create a new field. The following assumptions are made.
     * - It is a text field.
     * - There is no auto increment
     * - It is not a primary key
     * - values need not be unique
     */
    public FieldSchema()
    {
        this(Type.TEXT);
    }

    @Override
    public String toString()
    {
        return Text.concat(
            " ",
            getType().toString(),
            (isNotNull() ? "NOT NULL" : ""),
            (isPrimaryKey() ? "PRIMARY KEY" : ""),
            (isAutoIncrement() ? "AUTOINCREMENT" : ""),
            (isUnique() ? "UNIQUE" : "")
        );
    }
}
