package com.soupthatisthick.encounterbuilder.model;

import android.support.annotation.NonNull;

import com.soupthatisthick.encounterbuilder.model.FieldSchema;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Created by Owner on 4/17/2017.
 * Copyright of Stuart Marr Erskine, all rights reserved.
 */

public class TableSchema {
    private String name;

    private final Map<String, FieldSchema> fields = new HashMap<String, FieldSchema>();

    public TableSchema(@NonNull String name)
    {
        this.name = name;
    }

    public Set<String> getFields()
    {
        return fields.keySet();
    }

    public FieldSchema getFieldSchema(String fieldName)
    {
        return fields.get(fieldName);
    }

    public void setField(@NonNull String fieldName, @NonNull FieldSchema fieldSchema)
    {
        fields.put(fieldName, fieldSchema);
    }

    public final String sqlCreateTable()
    {
        return sqlCreateTableWithFlag(false);
    }

    public final String sqlCreateTableIfNotExists()
    {
        return sqlCreateTableWithFlag(true);
    }

    private String sqlCreateTableWithFlag(boolean useIfNotExists)
    {
        String sql = String.format("CREATE TABLE %s'%s' (",
            (useIfNotExists) ? "IF NOT EXISTS " : "",
            getName()
        );
        boolean isFirstField = true;
        for(String fieldName : getFields())
        {
            FieldSchema fieldSchema = getFieldSchema(fieldName);
            sql += (isFirstField) ? "\n\t" : ",\n\t";
            sql += String.format("%s %s", fieldName, fieldSchema.toString());
        }
        sql += "\n);\n";
        return sql;
    }

    public String getName() {
        return name;
    }

    public void setName(@NonNull String name)
    {
        this.name = name;
    }
}
