package com.soupthatisthick.encounterbuilder.model.lookup;

import com.soupthatisthick.encounterbuilder.model.DaoModel;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Property;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by Owner on 5/9/2017.
 * Copyright of Stuart Marr Erskine, all rights reserved.
 */

@Entity(nameInDb = "ROLL_TABLE")
public class RollTable extends DaoModel {

    @Id
    @Property(nameInDb = "id")
    private Long id;

    @Property(nameInDb = "name")
    private String name;

    @Property(nameInDb = "dieQty")
    private int dieCount;

    @Property(nameInDb = "dieSize")
    private int dieSize;

    @Generated(hash = 1182137725)
    public RollTable(Long id, String name, int dieCount, int dieSize) {
        this.id = id;
        this.name = name;
        this.dieCount = dieCount;
        this.dieSize = dieSize;
    }

    @Generated(hash = 820797020)
    public RollTable() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getDieCount() {
        return dieCount;
    }

    public void setDieCount(int dieCount) {
        this.dieCount = dieCount;
    }

    public int getDieSize() {
        return dieSize;
    }

    public void setDieSize(int dieSize) {
        this.dieSize = dieSize;
    }
}
