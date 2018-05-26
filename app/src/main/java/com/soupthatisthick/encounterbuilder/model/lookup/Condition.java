package com.soupthatisthick.encounterbuilder.model.lookup;

import com.soupthatisthick.encounterbuilder.model.DaoModel;

// import org.greenrobot.greendao.annotation.Entity;
// import org.greenrobot.greendao.annotation.Id;
// import org.greenrobot.greendao.annotation.Property;
// import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by Owner on 5/5/2017.
 * Copyright of Stuart Marr Erskine, all rights reserved.
 */

// @Entity(nameInDb = "CONDITIONS")
public class Condition extends DaoModel {


    // @Id
    // @Property(nameInDb = "id")
    private Long id;

    // @Property(nameInDb = "name")
    private String name;

    // @Property(nameInDb = "description")
    private String description;

    // @Generated(hash = 1324086194)
    public Condition(Long id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }

    // @Generated(hash = 1179462728)
    public Condition() {
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
