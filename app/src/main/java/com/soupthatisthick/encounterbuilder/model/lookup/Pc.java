package com.soupthatisthick.encounterbuilder.model.lookup;

import com.soupthatisthick.encounterbuilder.model.DaoModel;
import com.soupthatisthick.encounterbuilder.util.Text;


/**
 * Created by Owner on 2/5/2017.
 * Copyright of Stuart Marr Erskine, all rights reserved.
 */
public class Pc extends DaoModel {

    private Long id;
    private String playerName;
    private String playerDci;
    private String characterName;
    private String classAndLevels;
    private String faction;

    public Pc(Long id, String playerName, String playerDci, String characterName, String classAndLevels, String faction) {
        this.id = id;
        this.playerName = playerName;
        this.playerDci = playerDci;
        this.characterName = characterName;
        this.classAndLevels = classAndLevels;
        this.faction = faction;
    }


    public Pc() {
        // Do nothing
    }

    public String toString() {
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
