package com.soupthatisthick.encounterbuilder.model.builder;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Property;

/**
 * Created by Owner on 6/20/2017.
 * Copyright of Stuart Marr Erskine, all rights reserved.
 */

@Entity
public class EncounterMast {

    @Id
    @Property(nameInDb = "id")
    private Long id;

    @Property(nameInDb = "name")
    private String name;

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
}
