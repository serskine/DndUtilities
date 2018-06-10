package com.soupthatisthick.encounterbuilder.model.lookup;

import com.soupthatisthick.encounterbuilder.model.DaoModel;

import java.util.Locale;


/**
 * Created by Owner on 2/17/2017.
 * Copyright of Stuart Marr Erskine, all rights reserved.
 */

@SuppressWarnings("WeakerAccess")
public class Table extends DaoModel {

    private String type;
    private String name;
    private String tableName;
    private Long rootPage;
    private String sql;

    public Table(String type, String name, String tableName, Long rootPage, String sql) {
        this.type = type;
        this.name = name;
        this.tableName = tableName;
        this.rootPage = rootPage;
        this.sql = sql;
    }

    public Table() {
        // Do nothing
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public Long getRootPage() {
        return rootPage;
    }

    public void setRootPage(Long rootPage) {
        this.rootPage = rootPage;
    }

    public String getSql() {
        return sql;
    }

    public void setSql(String sql) {
        this.sql = sql;
    }

    public Long getId() {
        return (long) getName().hashCode();  // Same name tables will match
    }

    public String toString()
    {
        return String.format(Locale.CANADA, "%s(%d)", getName(), getId());
    }
}
