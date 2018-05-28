package com.soupthatisthick.encounterbuilder.model.lookup;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.soupthatisthick.encounterbuilder.model.DaoModel;
import com.soupthatisthick.encounterbuilder.util.sort.Category;

/**
 * Created by Owner on 5/26/2018.
 * Copyright of Stuart Marr Erskine, all rights reserved.
 */
public class Entity extends DaoModel {

    private Long id;

    private String metadata;

    private Long entityId;
    private Long armorId;
    private Long characterAdvancementId;
    private Long conditionId;
    private Long containerId;
    private Long customMonsterId;
    private Long searchableDaoId;
    private Long spellId;
    private Long equipmentId;
    private Long featId;
    private Long godId;
    private Long heightWeightId;
    private Long languageId;
    private Long lifestyleId;
    private Long listId;
    private Long listItemId;
    private Long magicItemId;
    private Long mcId;
    private Long monsterId;
    private Long mountId;
    private Long msId;
    private Long multiclassingId;
    private Long noteId;
    private Long rollTableId;
    private Long rollTableEntryId;
    private Long serviceId;
    private Long multiclassSpellSlotsId;
    private Long standardMonsterId;
    private Long tradeGoodId;
    private Long waterborneVechicleId;
    private Long weaponId;


    public Entity() {

    }

    public Entity(Long id, String metadata) {
        this(id, metadata,
                null, null, null, null, null, null, null, null, null, null,
                null, null, null, null, null, null, null, null, null, null,
                null, null, null, null, null, null, null, null, null, null, null
        );
    }

    public Entity(
            Long id,
            String metadata,
            Long entityId,
            Long armorId,
            Long characterAdvancementId,
            Long conditionId,
            Long containerId,
            Long customMonsterId,
            Long searchableDaoId,
            Long spellId,
            Long equipmentId,
            Long featId,
            Long godId,
            Long heightWeightId,
            Long languageId,
            Long lifestyleId,
            Long listId,
            Long listItemId,
            Long magicItemId,
            Long mcId,
            Long monsterId,
            Long mountId,
            Long msId,
            Long multiclassingId,
            Long noteId,
            Long rollTableId,
            Long rollTableEntryId,
            Long serviceId,
            Long multiclassSpellSlotsId,
            Long standardMonsterId,
            Long tradeGoodId,
            Long waterborneVechicleId,
            Long weaponId
    ) {
        this.id = id;
        this.metadata = metadata;
        this.entityId = entityId;
        this.armorId = armorId;
        this.characterAdvancementId = characterAdvancementId;
        this.conditionId = conditionId;
        this.containerId = containerId;
        this.customMonsterId = customMonsterId;
        this.searchableDaoId = searchableDaoId;
        this.spellId = spellId;
        this.equipmentId = equipmentId;
        this.featId = featId;
        this.godId = godId;
        this.heightWeightId = heightWeightId;
        this.languageId = languageId;
        this.lifestyleId = lifestyleId;
        this.listId = listId;
        this.listItemId = listItemId;
        this.magicItemId = magicItemId;
        this.mcId = mcId;
        this.monsterId = monsterId;
        this.mountId = mountId;
        this.msId = msId;
        this.multiclassingId = multiclassingId;
        this.noteId = noteId;
        this.rollTableId = rollTableId;
        this.rollTableEntryId = rollTableEntryId;
        this.serviceId = serviceId;
        this.multiclassSpellSlotsId = multiclassSpellSlotsId;
        this.standardMonsterId = standardMonsterId;
        this.tradeGoodId = tradeGoodId;
        this.waterborneVechicleId = waterborneVechicleId;
        this.weaponId = weaponId;
    }

    public void clearAllIds() {
        this.entityId = null;
        this.armorId = null;
        this.characterAdvancementId = null;
        this.conditionId = null;
        this.containerId = null;
        this.customMonsterId = null;
        this.searchableDaoId = null;
        this.spellId = null;
        this.equipmentId = null;
        this.featId = null;
        this.godId = null;
        this.heightWeightId = null;
        this.languageId = null;
        this.lifestyleId = null;
        this.listId = null;
        this.listItemId = null;
        this.magicItemId = null;
        this.mcId = null;
        this.monsterId = null;
        this.mountId = null;
        this.msId = null;
        this.multiclassingId = null;
        this.noteId = null;
        this.rollTableId = null;
        this.rollTableEntryId = null;
        this.serviceId = null;
        this.multiclassSpellSlotsId = null;
        this.standardMonsterId = null;
        this.tradeGoodId = null;
        this.waterborneVechicleId = null;
        this.weaponId = null;
    }
    
    public Category getEntityCategory() {
        if (this.entityId != null) return Category.ENTITY;
        if (this.armorId != null) return Category.ARMOR;
        if (this.characterAdvancementId != null) return Category.LEVEL;
        if (this.conditionId != null) return Category.CONDITION;
        if (this.customMonsterId != null) return Category.CUSTOM_MONSTER;
        if (this.spellId != null) return Category.SPELL;
        if (this.equipmentId != null) return Category.EQUIPMENT;
        if (this.featId != null) return Category.FEAT;
        if (this.godId != null) return Category.GOD;
        if (this.lifestyleId != null) return Category.LIFESTYLE;
        if (this.magicItemId != null) return Category.MAGIC_ITEM;
        if (this.mountId != null) return Category.MOUNT;
        if (this.noteId != null) return Category.NOTE;
        if (this.standardMonsterId != null) return Category.STANDARD_MONSTER;
        if (this.weaponId != null) return Category.WEAPON;

        // TODO: Implement category and ui's for the following tables daos
//        if (this.waterborneVechicleId != null) return Category.DEFAULT;
//        if (this.tradeGoodId != null) return Category.DEFAULT;
//        if (this.multiclassSpellSlotsId != null) return Category.DEFAULT;
//        if (this.serviceId != null) return Category.DEFAULT;
//        if (this.rollTableEntryId != null) return Category.DEFAULT;
//        if (this.rollTableId != null) return Category.DEFAULT;
//        if (this.multiclassingId != null) return Category.DEFAULT;
//        if (this.msId != null) return Category.DEFAULT;
//        if (this.monsterId != null) return Category.DEFAULT;
//        if (this.mcId != null) return Category.DEFAULT;
//        if (this.listItemId != null) return Category.DEFAULT;
//        if (this.listId != null) return Category.DEFAULT;
//        if (this.languageId != null) return Category.DEFAULT;
//        if (this.heightWeightId != null) return Category.DEFAULT;
//        if (this.searchableDaoId != null) return Category.DEFAULT;
//        if (this.containerId != null) return Category.DEFAULT;

        return Category.DEFAULT;
    }

    /**
     * Given the category, we will set the appropriate entity id with the specified value.
     * Because we can only ever be assigned to one category we will clear all the columns first on the entity
     * @param category will determine which column id to update
     * @param newId is the new value
     */
    public void setCategoryColumnId(@NonNull Category category, @Nullable Long newId) {
        if (category==null) {
            throw new RuntimeException("There can never be an entity column assigned to a null category.");
        }
        clearAllIds();
        switch(category) {
            case CONDITION:
                setConditionId(newId);
			    break;
            case CUSTOM_MONSTER:
                setCustomMonsterId(newId);
			    break;
            case STANDARD_MONSTER:
                setStandardMonsterId(newId);
			    break;
            case MAGIC_ITEM:
                setMagicItemId(newId);
                break;
            case SPELL:
                setSpellId(newId);
			    break;
            case FEAT:
                setFeatId(newId);
			    break;
            case ARMOR:
                setArmorId(newId);
			    break;
            case WEAPON:
                setWeaponId(newId);
			    break;
            case EQUIPMENT:
                setEquipmentId(newId);
			    break;
            case NOTE:
                setNoteId(newId);
			    break;
            case LEVEL:
                setCharacterAdvancementId(newId);
			    break;
            case GOD:
                setGodId(newId);
			    break;
            case LIFESTYLE:
                setLifestyleId(newId);
			    break;
            case MOUNT:
                setMountId(newId);
			    break;
            case ENTITY:
                setEntityId(newId);
			    break;
            case BACKGROUND:
            case CHALLENGE_RATING:
            case DEFAULT:
                
        }
        throw new RuntimeException("Category " + category + " has not yet had a column on the entity class assigned to it yet.");
    }
    
    public Long getCategoryColumnId(@NonNull Category category) {
        if (category==null) {
            throw new RuntimeException("There can never be an entity column assigned to a null category.");
        }
        switch(category) {

            case CONDITION:
                return getConditionId();
            case CUSTOM_MONSTER:
                return getCustomMonsterId();
            case STANDARD_MONSTER:
                return getStandardMonsterId();
            case MAGIC_ITEM:
                return getMagicItemId();
            case SPELL:
                return getSpellId();
            case FEAT:
                return getFeatId();
            case ARMOR:
                return getArmorId();
            case WEAPON:
                return getWeaponId();
            case EQUIPMENT:
                return getEquipmentId();
            case NOTE:
                return getNoteId();
            case LEVEL:
                return getCharacterAdvancementId();
            case GOD:
                return getGodId();
            case LIFESTYLE:
                return getLifestyleId();
            case MOUNT:
                return getMountId();
            case ENTITY:
                return getEntityId();
            case BACKGROUND:
            case CHALLENGE_RATING:
            case DEFAULT:
                        
        }
        throw new RuntimeException("Category " + category + " has not yet had a column on the entity class assigned to it yet.");
    }

    
    @Override
    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMetadata() {
        return metadata;
    }

    public void setMetadata(String metadata) {
        this.metadata = metadata;
    }

    public Long getEntityId() {
        return entityId;
    }

    public void setEntityId(Long entityId) {
        this.entityId = entityId;
    }

    public Long getArmorId() {
        return armorId;
    }

    public void setArmorId(Long armorId) {
        this.armorId = armorId;
    }

    public Long getCharacterAdvancementId() {
        return characterAdvancementId;
    }

    public void setCharacterAdvancementId(Long characterAdvancementId) {
        this.characterAdvancementId = characterAdvancementId;
    }

    public Long getConditionId() {
        return conditionId;
    }

    public void setConditionId(Long conditionId) {
        this.conditionId = conditionId;
    }

    public Long getContainerId() {
        return containerId;
    }

    public void setContainerId(Long containerId) {
        this.containerId = containerId;
    }

    public Long getCustomMonsterId() {
        return customMonsterId;
    }

    public void setCustomMonsterId(Long customMonsterId) {
        this.customMonsterId = customMonsterId;
    }

    public Long getSearchableDaoId() {
        return searchableDaoId;
    }

    public void setSearchableDaoId(Long searchableDaoId) {
        this.searchableDaoId = searchableDaoId;
    }

    public Long getSpellId() {
        return spellId;
    }

    public void setSpellId(Long spellId) {
        this.spellId = spellId;
    }

    public Long getEquipmentId() {
        return equipmentId;
    }

    public void setEquipmentId(Long equipmentId) {
        this.equipmentId = equipmentId;
    }

    public Long getFeatId() {
        return featId;
    }

    public void setFeatId(Long featId) {
        this.featId = featId;
    }

    public Long getGodId() {
        return godId;
    }

    public void setGodId(Long godId) {
        this.godId = godId;
    }

    public Long getHeightWeightId() {
        return heightWeightId;
    }

    public void setHeightWeightId(Long heightWeightId) {
        this.heightWeightId = heightWeightId;
    }

    public Long getLanguageId() {
        return languageId;
    }

    public void setLanguageId(Long languageId) {
        this.languageId = languageId;
    }

    public Long getLifestyleId() {
        return lifestyleId;
    }

    public void setLifestyleId(Long lifestyleId) {
        this.lifestyleId = lifestyleId;
    }

    public Long getListId() {
        return listId;
    }

    public void setListId(Long listId) {
        this.listId = listId;
    }

    public Long getListItemId() {
        return listItemId;
    }

    public void setListItemId(Long listItemId) {
        this.listItemId = listItemId;
    }

    public Long getMagicItemId() {
        return magicItemId;
    }

    public void setMagicItemId(Long magicItemId) {
        this.magicItemId = magicItemId;
    }

    public Long getMcId() {
        return mcId;
    }

    public void setMcId(Long mcId) {
        this.mcId = mcId;
    }

    public Long getMonsterId() {
        return monsterId;
    }

    public void setMonsterId(Long monsterId) {
        this.monsterId = monsterId;
    }

    public Long getMountId() {
        return mountId;
    }

    public void setMountId(Long mountId) {
        this.mountId = mountId;
    }

    public Long getMsId() {
        return msId;
    }

    public void setMsId(Long msId) {
        this.msId = msId;
    }

    public Long getMulticlassingId() {
        return multiclassingId;
    }

    public void setMulticlassingId(Long multiclassingId) {
        this.multiclassingId = multiclassingId;
    }

    public Long getNoteId() {
        return noteId;
    }

    public void setNoteId(Long noteId) {
        this.noteId = noteId;
    }

    public Long getRollTableId() {
        return rollTableId;
    }

    public void setRollTableId(Long rollTableId) {
        this.rollTableId = rollTableId;
    }

    public Long getRollTableEntryId() {
        return rollTableEntryId;
    }

    public void setRollTableEntryId(Long rollTableEntryId) {
        this.rollTableEntryId = rollTableEntryId;
    }

    public Long getServiceId() {
        return serviceId;
    }

    public void setServiceId(Long serviceId) {
        this.serviceId = serviceId;
    }

    public Long getMulticlassSpellSlotsId() {
        return multiclassSpellSlotsId;
    }

    public void setMulticlassSpellSlotsId(Long multiclassSpellSlotsId) {
        this.multiclassSpellSlotsId = multiclassSpellSlotsId;
    }

    public Long getStandardMonsterId() {
        return standardMonsterId;
    }

    public void setStandardMonsterId(Long standardMonsterId) {
        this.standardMonsterId = standardMonsterId;
    }

    public Long getTradeGoodId() {
        return tradeGoodId;
    }

    public void setTradeGoodId(Long tradeGoodId) {
        this.tradeGoodId = tradeGoodId;
    }

    public Long getWaterborneVechicleId() {
        return waterborneVechicleId;
    }

    public void setWaterborneVechicleId(Long waterborneVechicleId) {
        this.waterborneVechicleId = waterborneVechicleId;
    }

    public Long getWeaponId() {
        return weaponId;
    }

    public void setWeaponId(Long weaponId) {
        this.weaponId = weaponId;
    }
}
