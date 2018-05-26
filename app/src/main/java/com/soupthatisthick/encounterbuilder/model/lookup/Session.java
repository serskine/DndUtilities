package com.soupthatisthick.encounterbuilder.model.lookup;

import com.soupthatisthick.encounterbuilder.model.DaoModel;
import com.soupthatisthick.util.dao.ReadDao;

// import org.greenrobot.greendao.annotation.Entity;
// import org.greenrobot.greendao.annotation.Id;
// import org.greenrobot.greendao.annotation.Property;

import java.util.Date;
// import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by Owner on 2/5/2017.
 * Copyright of Stuart Marr Erskine, all rights reserved.
 */
// @Entity(nameInDb = "gameSessions")
public class Session extends DaoModel {

    // This is not a property stored in the database
    private Long rowId;

    // @Id
    // @Property(nameInDb = "sessionId")
    private Long sessionId;

    // @Property(nameInDb = "characterId")
    private Long characterId;

    // @Property(nameInDb = "adventure")
    private String adventure;

    // @Property(nameInDb = "date")
    private Date date;

    // @Property(nameInDb = "dmName")
    private String dmName;

    // @Property(nameInDb = "dmDci")
    private String dmDci;

    // @Property(nameInDb = "xpEarned")
    private int xp;

    // @Property(nameInDb = "goldEarned")
    private int gold;

    // @Property(nameInDb = "downtimeEarned")
    private int downtime;

    // @Property(nameInDb = "renownEarned")
    private int renown;

    // @Property(nameInDb = "magicItemsEarned")
    private int magicItems;

    // @Property(nameInDb = "notes")
    private String notes;

    // @Generated(hash = 597483656)
    public Session(Long rowId, Long sessionId, Long characterId, String adventure, Date date, String dmName, String dmDci, int xp, int gold, int downtime, int renown,
            int magicItems, String notes) {
        this.rowId = rowId;
        this.sessionId = sessionId;
        this.characterId = characterId;
        this.adventure = adventure;
        this.date = date;
        this.dmName = dmName;
        this.dmDci = dmDci;
        this.xp = xp;
        this.gold = gold;
        this.downtime = downtime;
        this.renown = renown;
        this.magicItems = magicItems;
        this.notes = notes;
    }

    // @Generated(hash = 1317889643)
    public Session() {
    }

    // @Override
    public String toString()
    {
        return "<" + ReadDao.COL_THE_ROWID + "=" + getRowId() + ">: " + getAdventure() + " on " + getDate().toString() + " under " + getDmName() + "(" + getDmDci() + ")";
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getDmName() {
        return dmName;
    }

    public void setDmName(String dmName) {
        this.dmName = dmName;
    }

    public String getDmDci() {
        return dmDci;
    }

    public void setDmDci(String dmDci) {
        this.dmDci = dmDci;
    }

    public int getXp() {
        return xp;
    }

    public void setXp(int xp) {
        this.xp = xp;
    }

    public int getGold() {
        return gold;
    }

    public void setGold(int gold) {
        this.gold = gold;
    }

    public int getDowntime() {
        return downtime;
    }

    public void setDowntime(int downtime) {
        this.downtime = downtime;
    }

    public int getRenown() {
        return renown;
    }

    public void setRenown(int renown) {
        this.renown = renown;
    }

    public int getMagicItems() {
        return magicItems;
    }

    public void setMagicItems(int magicItems) {
        this.magicItems = magicItems;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getAdventure() {
        return adventure;
    }

    public void setAdventure(String adventure) {
        this.adventure = adventure;
    }

    public Long getSessionId() {
        return sessionId;
    }

    public void setSessionId(Long sessionId) {
        this.sessionId = sessionId;
    }

    public Long getCharacterId() {
        return characterId;
    }

    public void setCharacterId(Long characterId) {
        this.characterId = characterId;
    }

    public Long getRowId() {
        return rowId;
    }

    public void setRowId(Long rowId) {
        this.rowId = rowId;
    }

    // @Override
    public Long getId() {
        return getSessionId();
    }
}
