package com.soupthatisthick.encounterbuilder.model.lookup;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.soupthatisthick.encounterbuilder.model.DaoModel;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Property;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by Owner on 5/28/2017.
 * Copyright of Stuart Marr Erskine, all rights reserved.
 */

@Entity(nameInDb = "SETTINGS")
public class Setting extends DaoModel {

    @Id
    @Property(nameInDb = "id")
    private Long id;

    @Property(nameInDb = "name")
    private String name;

    @Property(nameInDb = "value")
    private String value;

    @Generated(hash = 323559)
    public Setting(Long id, String name, String value) {
        this.id = id;
        this.name = name;
        this.value = value;
    }

    @Generated(hash = 909716735)
    public Setting() {
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



