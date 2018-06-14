package com.soupthatisthick.encounterbuilder.model.lookup;

import com.soupthatisthick.encounterbuilder.model.DaoModel;


/**
 * Created by Owner on 5/4/2017.
 * Copyright of Stuart Marr Erskine, all rights reserved.
 */
public class SearchFilter extends DaoModel {

    private Long id = null;
    private String table = null;
    private String column = null;

    // This is not a property stored in the database and is only used in code.
    private boolean allowed = true;

    public SearchFilter(Long id, String table, String column, boolean allowed) {
        this.id = id;
        this.table = table;
        this.column = column;
        this.allowed = allowed;
    }

    public SearchFilter() {
        // Do nothing
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTable() {
        return table;
    }

    public void setTable(String table) {
        this.table = table;
    }

    public String getColumn() {
        return column;
    }

    public void setColumn(String column) {
        this.column = column;
    }

    public String toString() {
        return getTable() + "." + getColumn();
    }

    public boolean isAllowed() {
        return allowed;
    }

    public void setAllowed(boolean allowed) {
        this.allowed = allowed;
    }

    public boolean getAllowed() {
        return this.allowed;
    }
}
