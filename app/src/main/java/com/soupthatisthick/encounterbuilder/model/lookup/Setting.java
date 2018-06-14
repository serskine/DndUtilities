package com.soupthatisthick.encounterbuilder.model.lookup;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.soupthatisthick.encounterbuilder.model.DaoModel;


/**
 * Created by Owner on 5/28/2017.
 * Copyright of Stuart Marr Erskine, all rights reserved.
 */

public class Setting extends DaoModel {

    private Long id;
    private String name;
    private String value;

    public Setting(Long id, String name, String value) {
        this.id = id;
        this.name = name;
        this.value = value;
    }

    public Setting() {
        // Do nothing
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}



