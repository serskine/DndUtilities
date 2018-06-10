package com.soupthatisthick.encounterbuilder.model.lookup;

import com.soupthatisthick.encounterbuilder.model.DaoModel;


/**
 * Created by Owner on 5/5/2017.
 * Copyright of Stuart Marr Erskine, all rights reserved.
 */

public class LifeStyle extends DaoModel {

    private Long id;
    private String name;
    private String pricePerDay;
    private String description;

    public LifeStyle(Long id, String name, String pricePerDay, String description) {
        this.id = id;
        this.name = name;
        this.pricePerDay = pricePerDay;
        this.description = description;
    }

    public LifeStyle() {
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

    public String getPricePerDay() {
        return pricePerDay;
    }

    public void setPricePerDay(String pricePerDay) {
        this.pricePerDay = pricePerDay;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
