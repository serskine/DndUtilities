package com.soupthatisthick.encounterbuilder.dao.lookup;

import android.content.ContentValues;
import android.database.Cursor;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.soupthatisthick.encounterbuilder.model.DaoModel;
import com.soupthatisthick.encounterbuilder.model.lookup.ChallengeRating;
import com.soupthatisthick.encounterbuilder.model.lookup.Entity;
import com.soupthatisthick.encounterbuilder.model.lookup.Equipment;
import com.soupthatisthick.encounterbuilder.model.lookup.God;
import com.soupthatisthick.encounterbuilder.model.lookup.Level;
import com.soupthatisthick.encounterbuilder.model.lookup.LifeStyle;
import com.soupthatisthick.encounterbuilder.model.lookup.Mount;
import com.soupthatisthick.encounterbuilder.model.lookup.Note;
import com.soupthatisthick.encounterbuilder.model.lookup.Weapon;
import com.soupthatisthick.encounterbuilder.util.sort.Category;
import com.soupthatisthick.util.dao.DaoMaster;
import com.soupthatisthick.util.dao.WriteDao;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by Owner on 5/26/2018.
 * Copyright of Stuart Marr Erskine, all rights reserved.
 */
public class EntityDao extends WriteDao<Entity> {

    public static final String TBL_ENTITY = "ENTITY";
    public static final String COL_ID = "id";
    public static final String COL_PARENT = "parent";
    public static final String COL_METADATA = "metadata";

    public static final String COL_ENTITY = "ENTITY";
    public static final String COL_ARMOR = "ARMOR";
    public static final String COL_BACKGROUND = "BACKGROUND";
    public static final String COL_CHARACTER_ADVANCEMENT = "CHARACTER_ADVANCEMENT";
    public static final String COL_CR_DETAILS = "CR_DETAILS";
    public static final String COL_CONDITIONS = "CONDITIONS";
    public static final String COL_CONTAINERS = "CONTAINERS";
    public static final String COL_CUSTOM_MONSTERS = "CUSTOM_MONSTERS";
    public static final String COL_DAO_SEARCHABLE = "DAO_SEARCHABLE";
    public static final String COL_EDITABLE_SPELLS = "EDITABLE_SPELLS";
    public static final String COL_EQUIPMENT = "EQUIPMENT";
    public static final String COL_FEATS = "FEATS";
    public static final String COL_GODS = "GODS";
    public static final String COL_HEIGHT_WEIGHT = "HEIGHT_WEIGHT";
    public static final String COL_LANGUAGES = "LANGUAGES";
    public static final String COL_LIFESTYLE = "LIFESTYLE";
    public static final String COL_LISTS = "LISTS";
    public static final String COL_LIST_ITEMS = "LIST_ITEMS";
    public static final String COL_MAGIC_ITEMS = "MAGIC_ITEMS";
    public static final String COL_MC = "MC";
    public static final String COL_MONSTERS = "MONSTERS";
    public static final String COL_MOUNTS = "MOUNTS";
    public static final String COL_MS = "MS";
    public static final String COL_MULTICLASSING = "MULTICLASSING";
    public static final String COL_NOTES = "NOTES";
    public static final String COL_ROLL_TABLE = "ROLL_TABLE";
    public static final String COL_ROLL_TABLE_ENTRY = "ROLL_TABLE_ENTRY";
    public static final String COL_SERVICE = "SERVICE";
    public static final String COL_SPELL_SLOTS_MULTICLASS = "SPELL_SLOTS_MULTICLASS";
    public static final String COL_STANDARD_MONSTERS = "STANDARD_MONSTERS";
    public static final String COL_TRADE_GOODS = "TRADE_GOODS";
    public static final String COL_WATERBORNE_VECHICLES = "WATERBORNE_VECHICLES";
    public static final String COL_WEAPONS = "WEAPONS";


    public EntityDao(DaoMaster daoMaster) throws IOException {
        super(daoMaster, TBL_ENTITY);
    }

    /**
     * This will build a mapping of column names to values from the specified record
     * so that they may be inserted.
     *
     * @param entity
     */
    @Override
    public ContentValues getContentValues(Entity entity) {
        ContentValues cv = new ContentValues();

        cv.put(COL_ID, entity.getId());
        cv.put(COL_METADATA, entity.getMetadata());

        cv.put(COL_ENTITY, entity.getEntityId());
        cv.put(COL_ARMOR, entity.getArmorId());
        cv.put(COL_BACKGROUND, entity.getBackgroundId());
        cv.put(COL_CHARACTER_ADVANCEMENT, entity.getCharacterAdvancementId());
        cv.put(COL_CONDITIONS, entity.getConditionId());
        cv.put(COL_CONTAINERS, entity.getContainerId());
        cv.put(COL_CUSTOM_MONSTERS, entity.getCustomMonsterId());
        cv.put(COL_DAO_SEARCHABLE, entity.getSearchableDaoId());
        cv.put(COL_EDITABLE_SPELLS, entity.getSpellId());
        cv.put(COL_EQUIPMENT, entity.getEquipmentId());
        cv.put(COL_FEATS, entity.getFeatId());
        cv.put(COL_GODS, entity.getGodId());
        cv.put(COL_HEIGHT_WEIGHT, entity.getHeightWeightId());
        cv.put(COL_LANGUAGES, entity.getLanguageId());
        cv.put(COL_LIFESTYLE, entity.getLifestyleId());
        cv.put(COL_LISTS, entity.getListId());
        cv.put(COL_LIST_ITEMS, entity.getListItemId());
        cv.put(COL_MAGIC_ITEMS, entity.getMagicItemId());
        cv.put(COL_MC, entity.getMcId());
        cv.put(COL_MONSTERS, entity.getMonsterId());
        cv.put(COL_MOUNTS, entity.getMountId());
        cv.put(COL_MS, entity.getMsId());
        cv.put(COL_MULTICLASSING, entity.getMulticlassingId());
        cv.put(COL_NOTES, entity.getNoteId());
        cv.put(COL_ROLL_TABLE, entity.getRollTableId());
        cv.put(COL_ROLL_TABLE_ENTRY, entity.getRollTableEntryId());
        cv.put(COL_SERVICE, entity.getServiceId());
        cv.put(COL_SPELL_SLOTS_MULTICLASS, entity.getMulticlassSpellSlotsId());
        cv.put(COL_STANDARD_MONSTERS, entity.getStandardMonsterId());
        cv.put(COL_TRADE_GOODS, entity.getTradeGoodId());
        cv.put(COL_WATERBORNE_VECHICLES, entity.getWaterborneVechicleId());
        cv.put(COL_WEAPONS, entity.getWeaponId());

        return cv;
    }

    /**
     * This will create a new model and return it
     *
     * @return a new Record to update
     */
    @Override
    protected Entity createNewModel() {
        return new Entity();
    }

    @Override
    public String getIdColumn() {
        return COL_ID;
    }

    @Override
    public Long getId(@NonNull Entity entity) {
        return entity.getId();
    }

    @Override
    public void setId(@NonNull Entity entity, @Nullable Long id) {
        entity.setId(id);
    }

    @Override
    public Entity readRecord(Cursor cursor) {

        Entity entity = new Entity();
        entity.setId(cursor.getLong(cursor.getColumnIndex(COL_ID)));
        entity.setParentId(cursor.getLong(cursor.getColumnIndex(COL_PARENT)));
        entity.setMetadata(cursor.getString(cursor.getColumnIndex(COL_METADATA)));

        entity.setEntityId(cursor.getLong(cursor.getColumnIndex(COL_ENTITY)));
        entity.setArmorId(cursor.getLong(cursor.getColumnIndex(COL_ARMOR)));
        entity.setBackgroundId(cursor.getLong(cursor.getColumnIndex(COL_BACKGROUND)));
        entity.setConditionId(cursor.getLong(cursor.getColumnIndex(COL_CONDITIONS)));
        entity.setChallengeRatingId(cursor.getLong(cursor.getColumnIndex(COL_CR_DETAILS)));
        entity.setCharacterAdvancementId(cursor.getLong(cursor.getColumnIndex(COL_CHARACTER_ADVANCEMENT)));
        entity.setContainerId(cursor.getLong(cursor.getColumnIndex(COL_CONTAINERS)));
        entity.setCustomMonsterId(cursor.getLong(cursor.getColumnIndex(COL_CUSTOM_MONSTERS)));
        entity.setSearchableDaoId(cursor.getLong(cursor.getColumnIndex(COL_DAO_SEARCHABLE)));
        entity.setSpellId(cursor.getLong(cursor.getColumnIndex(COL_EDITABLE_SPELLS)));
        entity.setEquipmentId(cursor.getLong(cursor.getColumnIndex(COL_EQUIPMENT)));
        entity.setFeatId(cursor.getLong(cursor.getColumnIndex(COL_FEATS)));
        entity.setGodId(cursor.getLong(cursor.getColumnIndex(COL_GODS)));
        entity.setHeightWeightId(cursor.getLong(cursor.getColumnIndex(COL_HEIGHT_WEIGHT)));
        entity.setLanguageId(cursor.getLong(cursor.getColumnIndex(COL_LANGUAGES)));
        entity.setLifestyleId(cursor.getLong(cursor.getColumnIndex(COL_LIFESTYLE)));
        entity.setListId(cursor.getLong(cursor.getColumnIndex(COL_LISTS)));
        entity.setListItemId(cursor.getLong(cursor.getColumnIndex(COL_LIST_ITEMS)));
        entity.setMagicItemId(cursor.getLong(cursor.getColumnIndex(COL_MAGIC_ITEMS)));
        entity.setMcId(cursor.getLong(cursor.getColumnIndex(COL_MC)));
        entity.setMonsterId(cursor.getLong(cursor.getColumnIndex(COL_MONSTERS)));
        entity.setMountId(cursor.getLong(cursor.getColumnIndex(COL_MOUNTS)));
        entity.setMsId(cursor.getLong(cursor.getColumnIndex(COL_MS)));
        entity.setMulticlassingId(cursor.getLong(cursor.getColumnIndex(COL_MULTICLASSING)));
        entity.setNoteId(cursor.getLong(cursor.getColumnIndex(COL_NOTES)));
        entity.setRollTableId(cursor.getLong(cursor.getColumnIndex(COL_ROLL_TABLE)));
        entity.setRollTableEntryId(cursor.getLong(cursor.getColumnIndex(COL_ROLL_TABLE_ENTRY)));
        entity.setServiceId(cursor.getLong(cursor.getColumnIndex(COL_SERVICE)));
        entity.setMulticlassSpellSlotsId(cursor.getLong(cursor.getColumnIndex(COL_SPELL_SLOTS_MULTICLASS)));
        entity.setStandardMonsterId(cursor.getLong(cursor.getColumnIndex(COL_STANDARD_MONSTERS)));
        entity.setTradeGoodId(cursor.getLong(cursor.getColumnIndex(COL_TRADE_GOODS)));
        entity.setWaterborneVechicleId(cursor.getLong(cursor.getColumnIndex(COL_WATERBORNE_VECHICLES)));
        entity.setWeaponId(cursor.getLong(cursor.getColumnIndex(COL_WEAPONS)));

        return entity;
    }

    /**
     * This method will return all the columns that may be searched for matches in a text search.
     *
     * @return a Set of columns elidgible for searching with a like query
     */
    @Override
    public Set<String> getSearchableColumns() {
        Set<String> columns = new HashSet<>();
        columns.add(COL_METADATA);
        return columns;
    }

    /**
     * This is a list of columns that we will sort the results by when we return a dataset.
     *
     * @return a List of columns. Sorting is first done by the first column then by the next column etc until all columns have been ordered.
     */
    @Override
    public List<String> getColumnSortingOrder() {
        List<String> columns = new ArrayList<>();
        columns.add(COL_ID);
        return columns;
    }

}