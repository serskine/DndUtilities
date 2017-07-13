package com.soupthatisthick.encounterbuilder.model.lookup;

import com.soupthatisthick.encounterbuilder.model.DaoModel;
import com.soupthatisthick.encounterbuilder.util.Text;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Property;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by Owner on 2/5/2017.
 * Copyright of Stuart Marr Erskine, all rights reserved.
 */
@Entity(nameInDb = "logsheets")
public class Pc extends DaoModel {

    @Id
    @Property(nameInDb = "id")
    private Long id;

    @Property(nameInDb = "playerName")
    private String playerName;

    @Property(nameInDb = "playerDci")
    private String playerDci;

    @Property(nameInDb = "characterName")
    private String characterName;

    @Property(nameInDb = "classLevels")
    private String classAndLevels;

    @Property(nameInDb = "faction")
    private String faction;

    @Generated(hash = 285643463)
    public Pc(Long id, String playerName, String playerDci, String characterName, String classAndLevels,
            String faction) {
        this.id = id;
        this.playerName = playerName;
        this.playerDci = playerDci;
        this.characterName = characterName;
        this.classAndLevels = classAndLevels;
        this.faction = faction;
    }


    @Generated(hash = 514052916)
    public Pc() {
    }

    @Override
    public String toString()
    {
        return getCharacterName() + "[" + getId() + "] (" + getPlayerName() + ":" + getPlayerDci() + ") "
                + ((Text.isBlank(getFaction()) ? " of the " + getFaction() : ""));
    }


    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public String getPlayerDci() {
        return playerDci;
    }

    public void setPlayerDci(String playerDci) {
        this.playerDci = playerDci;
    }

    public String getCharacterName() {
        return characterName;
    }

    public void setCharacterName(String characterName) {
        this.characterName = characterName;
    }

    public String getClassAndLevels() {
        return classAndLevels;
    }

    public void setClassAndLevels(String classAndLevels) {
        this.classAndLevels = classAndLevels;
    }

    public String getFaction() {
        return faction;
    }

    public void setFaction(String faction) {
        this.faction = faction;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
