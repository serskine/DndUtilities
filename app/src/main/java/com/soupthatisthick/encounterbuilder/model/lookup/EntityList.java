package com.soupthatisthick.encounterbuilder.model.lookup;

import com.soupthatisthick.encounterbuilder.model.DaoModel;
import com.soupthatisthick.encounterbuilder.util.sort.Category;

// import org.greenrobot.greendao.annotation.Entity;
// import org.greenrobot.greendao.annotation.Id;
// import org.greenrobot.greendao.annotation.Property;
// import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by Owner on 4/18/2017.
 * Copyright of Stuart Marr Erskine, all rights reserved.
 */
// @Entity(nameInDb = "LISTS")
public class EntityList extends DaoModel {

    // @Id
    // @Property(nameInDb = "id")
    private Long id;

    // @Property(nameInDb = "name")
    private String name;

    // @Generated(hash = 383842011)
    public EntityList(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    // @Generated(hash = 70580157)
    public EntityList() {
    }

    /**
     * This returns the unique identifier for the specified list
     * // @return
     */
    public Long getId() {
        return id;
    }

    /**
     * This sets the id of the specific list
     * // @param id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * This will return the name of the list used for display purposes
     * // @return
     */
    public String getName() {
        return name;
    }

    /**
     * This will set the name of the list used for display purposes.
     * // @param name
     */
    public void setName(String name) {
        this.name = name;
    }

}
