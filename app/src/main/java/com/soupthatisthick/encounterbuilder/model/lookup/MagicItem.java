package com.soupthatisthick.encounterbuilder.model.lookup;

import com.soupthatisthick.encounterbuilder.model.DaoModel;
import com.soupthatisthick.encounterbuilder.util.translater.CommaListTranslater;

import java.util.Set;


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
    private String treasureTableText;

    public MagicItem(Long id, String name, String type, String rarity, String attunement, String location, String description, int treasurePoints, String treasureTableText) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.rarity = rarity;
        this.attunement = attunement;
        this.location = location;
        this.description = description;
        this.treasurePoints = treasurePoints;
        this.treasureTableText = treasureTableText;
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

    public String getTreasureTableText() {
        return treasureTableText;
    }

    public void setTreasureTableText(String treasureTableText) {
        this.treasureTableText = treasureTableText;
    }

    public Set<String> getTreasureTables() {
        return CommaListTranslater.asSet(getTreasureTableText());
    }

    public void setTreasureTables(Set<String> tables) {
        setTreasureTableText(CommaListTranslater.asText(tables));
    }
}
