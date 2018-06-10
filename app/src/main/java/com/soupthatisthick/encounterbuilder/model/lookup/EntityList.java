package com.soupthatisthick.encounterbuilder.model.lookup;

import com.soupthatisthick.encounterbuilder.model.DaoModel;
import com.soupthatisthick.encounterbuilder.util.sort.Category;


/**
 * Created by Owner on 4/18/2017.
 * Copyright of Stuart Marr Erskine, all rights reserved.
 */
public class EntityList extends DaoModel {

    private Long id;
    private String name;

    public EntityList(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public EntityList() {
        // Do nothing
    }

    /**
     * This returns the unique identifier for the specified list
     */
    public Long getId() {
        return id;
    }

    /**
     * This sets the id of the specific list
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * This will return the name of the list used for display purposes
     */
    public String getName() {
        return name;
    }

    /**
     * This will set the name of the list used for display purposes.
     */
    public void setName(String name) {
        this.name = name;
    }

}
