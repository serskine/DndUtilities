package com.soupthatisthick.encounterbuilder.model.lookup;

import com.soupthatisthick.encounterbuilder.model.DaoModel;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Property;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by Owner on 1/27/2017.
 * Copyright of Stuart Marr Erskine, all rights reserved.
 */
@Entity(nameInDb = "EDITABLE_SPELLS")
public class Spell extends DaoModel {

    @Id
    @Property(nameInDb = "id")
    private Long id;

    @Property(nameInDb = "level")
    private int level;

    @Property(nameInDb = "name")
    private String name;

    @Property(nameInDb = "type")
    private String type;

    @Property(nameInDb = "castingTime")
    private String castingTime;

    @Property(nameInDb = "range")
    private String range;

    @Property(nameInDb = "components")
    private String components;

    @Property(nameInDb = "duration")
    private String duration;

    @Property(nameInDb = "description")
    private String description;

    @Property(nameInDb = "materials")
    private String materials;

    @Property(nameInDb = "class")
    private String classes;

    @Generated(hash = 449169983)
    public Spell(Long id, int level, String name, String type, String castingTime,
            String range, String components, String duration, String description,
            String materials, String classes) {
        this.id = id;
        this.level = level;
        this.name = name;
        this.type = type;
        this.castingTime = castingTime;
        this.range = range;
        this.components = components;
        this.duration = duration;
        this.description = description;
        this.materials = materials;
        this.classes = classes;
    }

    @Generated(hash = 2133214849)
    public Spell() {
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
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

    public String getCastingTime() {
        return castingTime;
    }

    public void setCastingTime(String castingTime) {
        this.castingTime = castingTime;
    }

    public String getRange() {
        return range;
    }

    public void setRange(String range) {
        this.range = range;
    }

    public String getComponents() {
        return components;
    }

    public void setComponents(String components) {
        this.components = components;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getMaterials() {
        return materials;
    }

    public void setMaterials(String materials) {
        this.materials = materials;
    }

    public String getClasses() {
        return classes;
    }

    public void setClasses(String classes) {
        this.classes = classes;
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
        return "spell(" + getId()+")[" + getName() + "]";
    }
}
