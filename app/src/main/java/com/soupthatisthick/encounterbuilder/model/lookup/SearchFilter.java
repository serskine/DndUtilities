package com.soupthatisthick.encounterbuilder.model.lookup;

import com.soupthatisthick.encounterbuilder.model.DaoModel;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Property;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by Owner on 5/4/2017.
 * Copyright of Stuart Marr Erskine, all rights reserved.
 */
@Entity(nameInDb = "DAO_SEARCHABLE")
public class SearchFilter extends DaoModel {

    @Id
    @Property(nameInDb = "id")
    private Long id = null;

    @Property(nameInDb = "table")
    private String table = null;

    @Property(nameInDb = "column")
    private String column = null;

    // This is not a property stored in the databaseand is only used in code.
    private boolean allowed = true;

    @Generated(hash = 1923860968)
    public SearchFilter(Long id, String table, String column, boolean allowed) {
        this.id = id;
        this.table = table;
        this.column = column;
        this.allowed = allowed;
    }

    @Generated(hash = 85855130)
    public SearchFilter() {
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

    @Override
    public String toString()
    {
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
