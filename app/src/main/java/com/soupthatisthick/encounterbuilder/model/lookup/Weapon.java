package com.soupthatisthick.encounterbuilder.model.lookup;

import com.soupthatisthick.encounterbuilder.model.DaoModel;

// import org.greenrobot.greendao.annotation.Entity;
// import org.greenrobot.greendao.annotation.Id;
// import org.greenrobot.greendao.annotation.Property;
// import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by Owner on 5/25/2017.
 * Copyright of Stuart Marr Erskine, all rights reserved.
 */
// @Entity(nameInDb = "WEAPONS")
public class Weapon extends DaoModel {

    // @Id
    // @Property(nameInDb = "id")
    private Long id;



    // @Property(nameInDb = "name")
    private String name;

    // @Property(nameInDb = "cost")
    private String cost;

    // @Property(nameInDb = "damage")
    private String damage;

    // @Property(nameInDb = "weight")
    private String weight;

    // @Property(nameInDb = "properties")
    private String properties;

    // @Property(nameInDb = "type")
    private String type;

    // @Property(nameInDb = "description")
    private String description;



    // @Generated(hash = 1935464022)
    public Weapon(Long id, String name, String cost, String damage, String weight,
            String properties, String type, String description) {
        this.id = id;
        this.name = name;
        this.cost = cost;
        this.damage = damage;
        this.weight = weight;
        this.properties = properties;
        this.type = type;
        this.description = description;
    }

    // @Generated(hash = 2014139261)
    public Weapon() {
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

    public String getDamage() {
        return damage;
    }

    public void setDamage(String damage) {
        this.damage = damage;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getProperties() {
        return properties;
    }

    public void setProperties(String properties) {
        this.properties = properties;
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
