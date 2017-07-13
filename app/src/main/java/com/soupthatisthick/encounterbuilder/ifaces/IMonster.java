package com.soupthatisthick.encounterbuilder.ifaces;

import android.support.annotation.Nullable;

/**
 * Created by Owner on 3/14/2017.
 * Copyright of Stuart Marr Erskine, all rights reserved.
 */

public interface IMonster {

    Long getId();

    void setId(@Nullable Long id);

    int getLevel();

    void setLevel(int level);

    String getName();

    void setName(String name);

    String getType();

    void setType(String type);

    String getAlignment();

    void setAlignment(String alignment);

    void setAc(int ac);

    String getAcType();

    void setAcType(String acType);

    String getHp();

    void setHp(String hp);

    String getHd();

    void setHd(String hd);

    String getSpeed();

    void setSpeed(String speed);

    int getStrength();

    void setStrength(int strength);

    int getDexterity();

    void setDexterity(int dexterity);

    int getConstitution();

    void setConstitution(int constitution);

    int getIntelligence();

    void setIntelligence(int intelligence);

    int getWisdom();

    void setWisdom(int wisdom);

    int getCharisma();

    void setCharisma(int charisma);

    String getSaves();

    void setSaves(String saves);

    String getSkills();

    void setSkills(String skills);

    String getDmgResistance();

    void setDmgResistance(String dmgResistance);

    String getDmgImmunity();

    void setDmgImmunity(String dmgImmunity);

    String getConditionImmunity();

    void setConditionImmunity(String conditionImmunity);

    String getSenses();

    void setSenses(String senses);

    String getLanguages();

    void setLanguages(String languages);

    String getCr();

    void setCr(String cr);

    int getXp();

    void setXp(int xp);

    String getAbilities();

    void setAbilities(String abilities);

    String getActions();

    void setActions(String actions);

    String getLegendaryActions();

    void setLegendaryActions(String legendaryActions);

    String getOther();

    void setOther(String other);

    String getSource();

    void setSource(String source);

}
