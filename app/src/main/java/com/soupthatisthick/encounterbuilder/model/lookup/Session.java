package com.soupthatisthick.encounterbuilder.model.lookup;

import com.soupthatisthick.encounterbuilder.model.DaoModel;
import com.soupthatisthick.util.dao.ReadDao;


import java.util.Date;

/**
 * Created by Owner on 2/5/2017.
 * Copyright of Stuart Marr Erskine, all rights reserved.
 */
public class Session extends DaoModel {

    // This is not a property stored in the database
    private Long rowId;
    private Long sessionId;
    private Long characterId;
    private String adventure;
    private Date date;
    private String dmName;
    private String dmDci;
    private int xp;
    private int gold;
    private int downtime;
    private int renown;
    private int magicItems;
    private String notes;

    public Session(
        Long rowId, Long sessionId, Long characterId, String adventure, Date date,
        String dmName, String dmDci, int xp, int gold, int downtime, int renown,
        int magicItems, String notes
    ) {
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

    public Session() {
        // Do nothing
    }

    public String toString() {
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

    @SuppressWarnings("WeakerAccess")
    public Long getRowId() {
        return rowId;
    }

    public void setRowId(Long rowId) {
        this.rowId = rowId;
    }

    public Long getId() {
        return getSessionId();
    }
}
