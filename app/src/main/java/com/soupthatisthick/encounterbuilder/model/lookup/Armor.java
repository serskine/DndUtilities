package com.soupthatisthick.encounterbuilder.model.lookup;

import com.soupthatisthick.encounterbuilder.model.DaoModel;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Property;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by Owner on 5/5/2017.
 * Copyright of Stuart Marr Erskine, all rights reserved.
 */
@Entity(nameInDb = "ARMOR")
public class Armor extends DaoModel {

    @Id
    private Long id;

    @Property(nameInDb = "id")
    private String name;

    @Property(nameInDb = "cost")
    private String cost;

    @Property(nameInDb = "ac")
    private String ac;

    @Property(nameInDb = "strength")
    private String strengthRequirement;

    @Property(nameInDb = "stealth")
    private String stealthEffect;

    @Property(nameInDb = "weight")
    private String weight;

    @Property(nameInDb = "type")
    private String type;

    @Property(nameInDb = "description")
    private String description;

    @Generated(hash = 1795786874)
    public Armor(Long id, String name, String cost, String ac,
            String strengthRequirement, String stealthEffect, String weight,
            String type, String description) {
        this.id = id;
        this.name = name;
        this.cost = cost;
        this.ac = ac;
        this.strengthRequirement = strengthRequirement;
        this.stealthEffect = stealthEffect;
        this.weight = weight;
        this.type = type;
        this.description = description;
    }

    @Generated(hash = 560524666)
    public Armor() {
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

    public String getCost() {
        return cost;
    }

    public void setCost(String cost) {
        this.cost = cost;
    }

    public String getAc() {
        return ac;
    }

    public void setAc(String ac) {
        this.ac = ac;
    }

    public String getStrengthRequirement() {
        return strengthRequirement;
    }

    public void setStrengthRequirement(String strengthRequirement) {
        this.strengthRequirement = strengthRequirement;
    }

    public String getStealthEffect() {
        return stealthEffect;
    }

    public void setStealthEffect(String stealthRequirement) {
        this.stealthEffect = stealthRequirement;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
