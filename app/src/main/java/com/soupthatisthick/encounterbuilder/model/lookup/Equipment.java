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
@Entity(nameInDb = "EQUIPMENT")
public class Equipment extends DaoModel {

    @Id
    @Property(nameInDb = "id")
    private Long id;

    @Property(nameInDb = "name")
    private String name;

    @Property(nameInDb = "cost")
    private String cost;

    @Property(nameInDb = "weight")
    private String weight;

    @Property(nameInDb = "description")
    private String description;

    @Property(nameInDb = "type")
    private String type;

    @Generated(hash = 366681975)
    public Equipment(Long id, String name, String cost, String weight,
            String description, String type) {
        this.id = id;
        this.name = name;
        this.cost = cost;
        this.weight = weight;
        this.description = description;
        this.type = type;
    }

    @Generated(hash = 748305627)
    public Equipment() {
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

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
