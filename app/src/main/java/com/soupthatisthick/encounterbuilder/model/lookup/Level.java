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
@Entity(nameInDb = "LEVEL_DETAILS")
public class Level extends DaoModel {

    @Id
    @Property(nameInDb = "id")
    private Long id;

    @Property(nameInDb = "name")
    private String name;

    @Property(nameInDb = "xp")
    private Integer xp;

    @Property(nameInDb = "level")
    private Integer level;

    @Property(nameInDb = "proficiency")
    private Integer proficiency;

    @Property(nameInDb = "tier")
    private Integer tier;

    @Property(nameInDb = "easy")
    private Integer easy;

    @Property(nameInDb = "normal")
    private Integer normal;

    @Property(nameInDb = "hard")
    private Integer hard;

    @Property(nameInDb = "deadly")
    private Integer deadly;

    @Generated(hash = 721781773)
    public Level(Long id, String name, Integer xp, Integer level,
            Integer proficiency, Integer tier, Integer easy, Integer normal,
            Integer hard, Integer deadly) {
        this.id = id;
        this.name = name;
        this.xp = xp;
        this.level = level;
        this.proficiency = proficiency;
        this.tier = tier;
        this.easy = easy;
        this.normal = normal;
        this.hard = hard;
        this.deadly = deadly;
    }

    @Generated(hash = 723561372)
    public Level() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getXp() {
        return xp;
    }

    public void setXp(Integer xp) {
        this.xp = xp;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public Integer getProficiency() {
        return proficiency;
    }

    public void setProficiency(Integer proficiency) {
        this.proficiency = proficiency;
    }

    public Integer getTier() {
        return tier;
    }

    public void setTier(Integer tier) {
        this.tier = tier;
    }

    public Integer getEasy() {
        return easy;
    }

    public void setEasy(Integer easy) {
        this.easy = easy;
    }

    public Integer getNormal() {
        return normal;
    }

    public void setNormal(Integer normal) {
        this.normal = normal;
    }

    public Integer getHard() {
        return hard;
    }

    public void setHard(Integer hard) {
        this.hard = hard;
    }

    public Integer getDeadly() {
        return deadly;
    }

    public void setDeadly(Integer deadly) {
        this.deadly = deadly;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString()
    {
        return String.format("%s (%d xp)", getName(), getXp());
    }
}
