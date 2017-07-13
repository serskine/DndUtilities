package com.soupthatisthick.encounterbuilder.model.lookup;

import com.soupthatisthick.encounterbuilder.model.DaoModel;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Property;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by Owner on 2/2/2017.
 * Copyright of Stuart Marr Erskine, all rights reserved.
 */

@Entity(nameInDb = "MAGIC_ITEMS")
public class MagicItem extends DaoModel {

    @Id
    @Property(nameInDb = "id")
    private Long id;

    @Property(nameInDb = "name")
    private String name;

    @Property(nameInDb = "type")
    private String type;

    @Property(nameInDb = "rarity")
    private String rarity;

    @Property(nameInDb = "attunement")
    private String attunement;

    @Property(nameInDb = "location")
    private String location;

    @Property(nameInDb = "description")
    private String description;

    @Generated(hash = 547490682)
    public MagicItem(Long id, String name, String type, String rarity, String attunement, String location, String description) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.rarity = rarity;
        this.attunement = attunement;
        this.location = location;
        this.description = description;
    }

    @Generated(hash = 22852346)
    public MagicItem() {
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

    @Override
    public String toString()
    {
        return "magicItem[" + getId() + "][" + getName() + "][" + getType() + "][" + getRarity() + "][" + getAttunement() + "]";
    }
}
