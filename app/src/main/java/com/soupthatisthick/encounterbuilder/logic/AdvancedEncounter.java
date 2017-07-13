package com.soupthatisthick.encounterbuilder.logic;

import android.support.annotation.NonNull;

import com.soupthatisthick.encounterbuilder.model.lookup.CustomMonster;
import com.soupthatisthick.encounterbuilder.model.lookup.StandardMonster;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

/**
 * Created by Owner on 4/27/2017.
 * Copyright of Stuart Marr Erskine, all rights reserved.
 */

public class AdvancedEncounter extends Encounter {

    private static final int MAX_MONSTERS_OF_ONE_TYPE = 6;
    private String name = "";
    private String location = "";

    private Map<Integer, Set<String>> initMap = new HashMap<Integer, Set<String>>();
    private Map<String, Integer> monsterCount = new HashMap<>();

    private final Set<String> getMonstersAtInit(int initiative)
    {
        Set<String> monsters = initMap.get(initiative);
        if (monsters==null)
        {
            monsters = new TreeSet<String>();
            initMap.put(initiative, monsters);
        }
        return monsters;
    }

    private int getMonsterCount(@NonNull String monsterName)
    {
        Integer count = monsterCount.get(monsterName);
        return (count==null) ? 0 : count;
    }

    private void setMonsterCount(@NonNull String monsterName, int count)
    {
        if (count<1)
        {
            monsterCount.remove(monsterName);
        } else {
            monsterCount.put(monsterName, count);
        }
    }

    private final void addInit(String name, int initiative)
    {
        Set<String> monsters = getMonstersAtInit(initiative);
        monsters.add(name);
    }

    public final void addStandardMonster(StandardMonster standardMonster, int initiative)
    {

        Challenge challenge = getChallenge(standardMonster);
        addChallenge(challenge);
    }

    public final void addCustomMonster(CustomMonster customMonster, int initiative)
    {
        String monsterGroup = customMonster.getName();
        setMonsterCount(monsterGroup, getMonsterCount(monsterGroup)+1);

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    protected Challenge getChallenge(StandardMonster standardMonster)
    {
        return Challenge.CR_0;
    }

    protected Challenge getChallenge(CustomMonster customMonster)
    {
        return Challenge.CR_0;
    }
}
