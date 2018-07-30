package com.soupthatisthick.encounterbuilder.util.sort;

import android.support.annotation.Nullable;

import com.soupthatisthick.encounterbuilder.dao.lookup.AdventureDao;
import com.soupthatisthick.encounterbuilder.dao.lookup.ArmorDao;
import com.soupthatisthick.encounterbuilder.dao.lookup.ChallengeRatingDao;
import com.soupthatisthick.encounterbuilder.dao.lookup.ConditionDao;
import com.soupthatisthick.encounterbuilder.dao.lookup.CustomMonsterDao;
import com.soupthatisthick.encounterbuilder.dao.lookup.EntityDao;
import com.soupthatisthick.encounterbuilder.dao.lookup.EntityListDao;
import com.soupthatisthick.encounterbuilder.dao.lookup.EquipmentDao;
import com.soupthatisthick.encounterbuilder.dao.lookup.FeatDao;
import com.soupthatisthick.encounterbuilder.dao.lookup.GodsDao;
import com.soupthatisthick.encounterbuilder.dao.lookup.LevelDao;
import com.soupthatisthick.encounterbuilder.dao.lookup.LifeStyleDao;
import com.soupthatisthick.encounterbuilder.dao.lookup.MagicItemDao;
import com.soupthatisthick.encounterbuilder.dao.lookup.MountDao;
import com.soupthatisthick.encounterbuilder.dao.lookup.NotesDao;
import com.soupthatisthick.encounterbuilder.dao.lookup.SeasonDao;
import com.soupthatisthick.encounterbuilder.dao.lookup.SpellDao;
import com.soupthatisthick.encounterbuilder.dao.lookup.StandardMonsterDao;
import com.soupthatisthick.encounterbuilder.dao.lookup.WeaponDao;
import com.soupthatisthick.encounterbuilder.dao.lookup.BackgroundDao;
import com.soupthatisthick.encounterbuilder.model.lookup.Adventure;
import com.soupthatisthick.encounterbuilder.model.lookup.ChallengeRating;
import com.soupthatisthick.encounterbuilder.model.lookup.Armor;
import com.soupthatisthick.encounterbuilder.model.lookup.Background;
import com.soupthatisthick.encounterbuilder.model.lookup.Condition;
import com.soupthatisthick.encounterbuilder.model.lookup.CustomMonster;
import com.soupthatisthick.encounterbuilder.model.lookup.Entity;
import com.soupthatisthick.encounterbuilder.model.lookup.EntityList;
import com.soupthatisthick.encounterbuilder.model.lookup.Equipment;
import com.soupthatisthick.encounterbuilder.model.lookup.Feat;
import com.soupthatisthick.encounterbuilder.model.lookup.God;
import com.soupthatisthick.encounterbuilder.model.lookup.Level;
import com.soupthatisthick.encounterbuilder.model.lookup.LifeStyle;
import com.soupthatisthick.encounterbuilder.model.lookup.MagicItem;
import com.soupthatisthick.encounterbuilder.model.lookup.Mount;
import com.soupthatisthick.encounterbuilder.model.lookup.Note;
import com.soupthatisthick.encounterbuilder.model.lookup.Season;
import com.soupthatisthick.encounterbuilder.model.lookup.Spell;
import com.soupthatisthick.encounterbuilder.model.lookup.StandardMonster;
import com.soupthatisthick.encounterbuilder.model.lookup.Weapon;

/**
 * Created by Owner on 5/27/2017.
 * Copyright of Stuart Marr Erskine, all rights reserved.
 */


public enum Category
{
    DEFAULT(0, null, null, ""),
    CONDITION(-1, ConditionDao.TBL_CONDITION, ConditionDao.COL_ID, Condition.class.getCanonicalName()),
    CUSTOM_MONSTER(-2, CustomMonsterDao.TBL_CUSTOM_MONSTER, CustomMonsterDao.COL_CMN_ID, CustomMonster.class.getCanonicalName()),
    STANDARD_MONSTER(-3, StandardMonsterDao.TBL_STANDARD_MONSTER, StandardMonsterDao.COL_SMN_id, StandardMonster.class.getCanonicalName()),
    MAGIC_ITEM(-4, MagicItemDao.TBL_ITEMS, MagicItemDao.COL_ID, MagicItem.class.getCanonicalName()),
    SPELL(-5, SpellDao.TBL_SP, SpellDao.COL_SP_ID, Spell.class.getCanonicalName()),
    FEAT(-6, FeatDao.TBL_FEATS, FeatDao.COL_ID, Feat.class.getCanonicalName()),
    BACKGROUND(-7, BackgroundDao.TBL_BACKGROUND, BackgroundDao.COL_ID, Background.class.getCanonicalName()),
    ARMOR(-8, ArmorDao.TBL_ARMOR, ArmorDao.COL_ID, Armor.class.getCanonicalName()),
    WEAPON(-9, WeaponDao.TBL_WEAPONS, WeaponDao.COL_ID, Weapon.class.getCanonicalName()),
    EQUIPMENT(-10, EquipmentDao.TBL_EQUIPMNT, EquipmentDao.COL_ID, Equipment.class.getCanonicalName()),
    NOTE(-11, NotesDao.TBL_NOTES, NotesDao.COL_ID, Note.class.getCanonicalName()),
    CHALLENGE_RATING(-12, ChallengeRatingDao.TBL_CR_DETAILS, ChallengeRatingDao.COL_ID, ChallengeRating.class.getCanonicalName()),
    LEVEL(-13, LevelDao.TBL_LEVEL_DETAILS, LevelDao.COL_ID, Level.class.getCanonicalName()),
    GOD(-14, GodsDao.TBL_GODS, GodsDao.COL_ID, God.class.getCanonicalName()),
    LIFESTYLE(-15, LifeStyleDao.TBL_LIFESTYLE, LifeStyleDao.COL_ID, LifeStyle.class.getCanonicalName()),
    MOUNT(-16, MountDao.TBL_MOUNTS, MountDao.COL_ID, Mount.class.getCanonicalName()),
    ENTITY(-17, EntityDao.TBL_ENTITY, EntityDao.COL_ID, Entity.class.getCanonicalName()),
    ENTITY_LIST(-18, EntityListDao.TBL_LISTS, EntityListDao.COL_ID, EntityList.class.getCanonicalName()),
    SEASON(-19, SeasonDao.TBL_SEASON, SeasonDao.COL_ID, Season.class.getCanonicalName()),
    ADVENTURE(-20, AdventureDao.TBL_ADVENTURE, AdventureDao.COL_ID, Adventure.class.getCanonicalName())
    ;

    public final int orderValue;
    public final String tableName, primaryKey;
    public final String classCanocicalName;

    private Category(int orderValue, String tableName, String primaryKey, String classCanocicalName)
    {
        this.orderValue = orderValue;
        this.tableName = tableName;
        this.primaryKey = primaryKey;
        this.classCanocicalName = classCanocicalName;
    }

    public static final <T> Category findForClass(Class<T> tClass) {
        if (tClass==null) {
            return DEFAULT;
        }
        for(Category category : values()) {
            if (category.classCanocicalName.equals(tClass.getCanonicalName())) {
                return category;
            }
        }
        return DEFAULT;
    }

    public static final Category parse(@Nullable Object item)
    {
        if (item==null) {
            return DEFAULT;
        }
        return findForClass(item.getClass());
//        if (item.getClass().equals(Condition.class)) {
//            return CONDITION;
//        }
//        if (item.getClass().equals(Spell.class)) {
//            return SPELL;
//        }
//        if (item.getClass().equals(CustomMonster.class)) {
//            return CUSTOM_MONSTER;
//        }
//        if (item.getClass().equals(StandardMonster.class)) {
//            return STANDARD_MONSTER;
//        }
//        if (item.getClass().equals(MagicItem.class)) {
//            return MAGIC_ITEM;
//        }
//        if (item.getClass().equals(Feat.class)) {
//            return FEAT;
//        }
//        if (item.getClass().equals(Background.class)) {
//            return BACKGROUND;
//        }
//        if (item.getClass().equals(Armor.class)) {
//            return ARMOR;
//        }
//        if (item.getClass().equals(Weapon.class)) {
//            return WEAPON;
//        }
//        if (item.getClass().equals(Equipment.class)) {
//            return EQUIPMENT;
//        }
//        if (item.getClass().equals(Note.class)) {
//            return NOTE;
//        }
//        if (item.getClass().equals(ChallengeRating.class)) {
//            return CHALLENGE_RATING;
//        }
//        if (item.getClass().equals(Level.class)) {
//            return LEVEL;
//        }
//        if (item.getClass().equals(God.class)) {
//            return GOD;
//        }
//        if (item.getClass().equals(LifeStyle.class)) {
//            return LIFESTYLE;
//        }
//        if (item.getClass().equals(Mount.class)) {
//            return MOUNT;
//        }
//        if (item.getClass().equals(Entity.class)) {
//            return ENTITY;
//        }
//        if (item.getClass().equals(Season.class)) {
//            return SEASON;
//        }
//        if (item.getClass().equals(Adventure.class)) {
//            return ADVENTURE;
//        }
//        return DEFAULT;
    }
};
