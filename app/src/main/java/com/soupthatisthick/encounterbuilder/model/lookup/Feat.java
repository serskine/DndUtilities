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
// @Entity(nameInDb = "FEATS")
public class Feat extends DaoModel {

    // @Id
    // @Property(nameInDb = "id")
    private Long id;

    // @Property(nameInDb = "name")
    private String name;

    // @Property(nameInDb = "prerequisite")
    private String prerequisite;

    // @Property(nameInDb = "description")
    private String description;

    // @Generated(hash = 57394742)
    public Feat(Long id, String name, String prerequisite, String description) {
        this.id = id;
        this.name = name;
        this.prerequisite = prerequisite;
        this.description = description;
    }

    // @Generated(hash = 1953217192)
    public Feat() {
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

    public String getPrerequisite() {
        return prerequisite;
    }

    public void setPrerequisite(String prerequisite) {
        this.prerequisite = prerequisite;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
