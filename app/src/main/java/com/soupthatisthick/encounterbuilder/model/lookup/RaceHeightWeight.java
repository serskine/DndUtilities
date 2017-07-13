package com.soupthatisthick.encounterbuilder.model.lookup;

import com.soupthatisthick.encounterbuilder.model.DaoModel;

/**
 * Created by Owner on 5/5/2017.
 * Copyright of Stuart Marr Erskine, all rights reserved.
 */

public class RaceHeightWeight extends DaoModel {
    private Long id;
    private String race, baseHeight, heightModifier, baseWeight, weightModifier;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRace() {
        return race;
    }

    public void setRace(String race) {
        this.race = race;
    }

    public String getBaseHeight() {
        return baseHeight;
    }

    public void setBaseHeight(String baseHeight) {
        this.baseHeight = baseHeight;
    }

    public String getHeightModifier() {
        return heightModifier;
    }

    public void setHeightModifier(String heightModifier) {
        this.heightModifier = heightModifier;
    }

    public String getBaseWeight() {
        return baseWeight;
    }

    public void setBaseWeight(String baseWeight) {
        this.baseWeight = baseWeight;
    }

    public String getWeightModifier() {
        return weightModifier;
    }

    public void setWeightModifier(String weightModifier) {
        this.weightModifier = weightModifier;
    }
}
