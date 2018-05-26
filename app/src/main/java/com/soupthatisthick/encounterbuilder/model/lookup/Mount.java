package com.soupthatisthick.encounterbuilder.model.lookup;

import com.soupthatisthick.encounterbuilder.model.DaoModel;

// import org.greenrobot.greendao.annotation.Entity;
// import org.greenrobot.greendao.annotation.Id;
// import org.greenrobot.greendao.annotation.Property;
// import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by Owner on 5/5/2017.
 * Copyright of Stuart Marr Erskine, all rights reserved.
 */
// @Entity(nameInDb = "MOUNTS")
public class Mount extends DaoModel {

    // @Id
    // @Property(nameInDb = "id")
    private Long id;

    // @Property(nameInDb = "name")
    private String name;

    // @Property(nameInDb = "cost")
    private String cost;

    // @Property(nameInDb = "speed")
    private String speed;

    // @Property(nameInDb = "carry")
    private String carry;

    // @Property(nameInDb = "description")
    private String description;

    // @Generated(hash = 920890881)
    public Mount(Long id, String name, String cost, String speed, String carry,
            String description) {
        this.id = id;
        this.name = name;
        this.cost = cost;
        this.speed = speed;
        this.carry = carry;
        this.description = description;
    }

    // @Generated(hash = 2014362176)
    public Mount() {
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

    public String getSpeed() {
        return speed;
    }

    public void setSpeed(String speed) {
        this.speed = speed;
    }

    public String getCarry() {
        return carry;
    }

    public void setCarry(String carry) {
        this.carry = carry;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
