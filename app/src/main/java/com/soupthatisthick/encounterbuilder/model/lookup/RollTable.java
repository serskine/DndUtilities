package com.soupthatisthick.encounterbuilder.model.lookup;

import com.soupthatisthick.encounterbuilder.model.DaoModel;


/**
 * Created by Owner on 5/9/2017.
 * Copyright of Stuart Marr Erskine, all rights reserved.
 */

public class RollTable extends DaoModel {

    private Long id;
    private String name;
    private int dieCount;
    private int dieSize;

    public RollTable(Long id, String name, int dieCount, int dieSize) {
        this.id = id;
        this.name = name;
        this.dieCount = dieCount;
        this.dieSize = dieSize;
    }

    public RollTable() {
        // Do nothing
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
