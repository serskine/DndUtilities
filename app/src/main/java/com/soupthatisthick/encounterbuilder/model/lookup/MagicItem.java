package com.soupthatisthick.encounterbuilder.model.lookup;

import com.soupthatisthick.encounterbuilder.model.DaoModel;


/**
 * Created by Owner on 2/2/2017.
 * Copyright of Stuart Marr Erskine, all rights reserved.
 */

@SuppressWarnings("SpellCheckingInspection")
public class MagicItem extends DaoModel {

    private Long id;
    private String name;
    private String type;
    private String rarity;
    private String attunement;
    private String location;
    private String description;
    private int treasurePoints;

    public MagicItem(Long id, String name, String type, String rarity, String attunement, String location, String description, int treasurePoints) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.rarity = rarity;
        this.attunement = attunement;
        this.location = location;
        this.description = description;
        this.treasurePoints = treasurePoints;
    }

    public MagicItem() {
        // Do nothing
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getRarity() {
        return rarity;
    }

    public void setRarity(String rarity) {
        this.rarity = rarity;
    }

    public String getAttunement() {
        return attunement;
    }

    public void setAttunement(String attunement) {
        this.attunement = attunement;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String toString() {
        return "magicItem[" + getId() + "][" + getName() + "][" + getType() + "][" + getRarity() + "][" + getAttunement() + "][" + getTreasurePoints() + " tp]";
    }

    public int getTreasurePoints() {
        return treasurePoints;
    }

    public void setTreasurePoints(int treasurePoints) {
        this.treasurePoints = treasurePoints;
    }
}
