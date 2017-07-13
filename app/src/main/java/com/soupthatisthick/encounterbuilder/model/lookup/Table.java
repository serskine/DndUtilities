package com.soupthatisthick.encounterbuilder.model.lookup;

import com.soupthatisthick.encounterbuilder.model.DaoModel;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Property;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by Owner on 2/17/2017.
 * Copyright of Stuart Marr Erskine, all rights reserved.
 */

@Entity(nameInDb = "sqlite_master")
public class Table extends DaoModel {

    @Property(nameInDb = "type")
    private String type;

    @Property(nameInDb = "name")
    private String name;

    @Property(nameInDb = "tbl_name")
    private String tableName;

    @Property(nameInDb = "rootpage")
    private Long rootPage;

    @Property(nameInDb = "sql")
    private String sql;

    @Generated(hash = 1147649556)
    public Table(String type, String name, String tableName, Long rootPage,
            String sql) {
        this.type = type;
        this.name = name;
        this.tableName = tableName;
        this.rootPage = rootPage;
        this.sql = sql;
    }

    @Generated(hash = 752389689)
    public Table() {
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

    @Override
    public Long getId() {
        return new Long(getName().hashCode());  // Same name tables will match
    }

    @Override
    public String toString()
    {
        return String.format("%s(%d)", getName(), getId());
    }
}
