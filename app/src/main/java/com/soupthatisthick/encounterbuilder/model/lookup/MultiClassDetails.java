package com.soupthatisthick.encounterbuilder.model.lookup;

import com.soupthatisthick.encounterbuilder.model.DaoModel;

/**
 * Created by Owner on 5/5/2017.
 * Copyright of Stuart Marr Erskine, all rights reserved.
 */

public class MultiClassDetails extends DaoModel {
    private Long id;
    private String prerequisite, proficiencies, className;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public String getPrerequisite() {
        return prerequisite;
    }

    public void setPrerequisite(String prerequisite) {
        this.prerequisite = prerequisite;
    }

    public String getProficiencies() {
        return proficiencies;
    }

    public void setProficiencies(String proficiencies) {
        this.proficiencies = proficiencies;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }
}
