package com.soupthatisthick.encounterbuilder.model.lookup;

import com.soupthatisthick.encounterbuilder.model.DaoModel;

// import org.greenrobot.greendao.annotation.Entity;
// import org.greenrobot.greendao.annotation.Id;
// import org.greenrobot.greendao.annotation.Property;
// import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by Owner on 5/8/2017.
 * Copyright of Stuart Marr Erskine, all rights reserved.
 */

// @Entity(nameInDb = "CR_DETAILS")
public class ChallengeRating extends DaoModel {
    // @Id
    // @Property(nameInDb = "id")
    private Long id;

    // @Property(nameInDb = "name")
    private String name;

    // @Property(nameInDb = "value")
    private Double value;

    // @Property(nameInDb = "prof")
    private Integer proficiency;

    // @Property(nameInDb = "maxAc")
    private Integer maxAc;

    // @Property(nameInDb = "minHp")
    private Integer minHp;

    // @Property(nameInDb = "maxHp")
    private Integer maxHp;

    // @Property(nameInDb = "minDmg")
    private Integer minDmg;

    // @Property(nameInDb = "maxDmg")
    private Integer maxDmg;

    // @Property(nameInDb = "saveDc")
    private Integer saveDc;

    // @Property(nameInDb = "xp")
    private Integer xp;


    // @Generated(hash = 183221197)
    public ChallengeRating(Long id, String name, Double value, Integer proficiency,
            Integer maxAc, Integer minHp, Integer maxHp, Integer minDmg,
            Integer maxDmg, Integer saveDc, Integer xp) {
        this.id = id;
        this.name = name;
        this.value = value;
        this.proficiency = proficiency;
        this.maxAc = maxAc;
        this.minHp = minHp;
        this.maxHp = maxHp;
        this.minDmg = minDmg;
        this.maxDmg = maxDmg;
        this.saveDc = saveDc;
        this.xp = xp;
    }

    // @Generated(hash = 1806178765)
    public ChallengeRating() {
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

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    public Integer getProficiency() {
        return proficiency;
    }

    public void setProficiency(Integer proficiency) {
        this.proficiency = proficiency;
    }

    public Integer getMaxAc() {
        return maxAc;
    }

    public void setMaxAc(Integer maxAc) {
        this.maxAc = maxAc;
    }

    public Integer getMinHp() {
        return minHp;
    }

    public void setMinHp(Integer minHp) {
        this.minHp = minHp;
    }

    public Integer getMaxHp() {
        return maxHp;
    }

    public void setMaxHp(Integer maxHp) {
        this.maxHp = maxHp;
    }

    public Integer getMinDmg() {
        return minDmg;
    }

    public void setMinDmg(Integer minDmg) {
        this.minDmg = minDmg;
    }

    public Integer getMaxDmg() {
        return maxDmg;
    }

    public void setMaxDmg(Integer maxDmg) {
        this.maxDmg = maxDmg;
    }

    public Integer getSaveDc() {
        return saveDc;
    }

    public void setSaveDc(Integer saveDc) {
        this.saveDc = saveDc;
    }

    public Integer getXp() {
        return xp;
    }

    public void setXp(Integer xp) {
        this.xp = xp;
    }

    // @Override
    public String toString()
    {
        return String.format("%s (%d xp)", getName(), getXp());
    }
}
