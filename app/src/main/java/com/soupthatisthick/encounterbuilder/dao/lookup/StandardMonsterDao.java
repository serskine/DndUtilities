package com.soupthatisthick.encounterbuilder.dao.lookup;

import android.content.ContentValues;
import android.database.Cursor;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.soupthatisthick.encounterbuilder.model.lookup.StandardMonster;
import com.soupthatisthick.encounterbuilder.util.Text;
import com.soupthatisthick.util.Logger;
import com.soupthatisthick.util.dao.DaoMaster;
import com.soupthatisthick.util.dao.WriteDao;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by Owner on 3/13/2017.
 * Copyright of Stuart Marr Erskine, all rights reserved.
 */

public class StandardMonsterDao extends WriteDao<StandardMonster> {

    public static final String TBL_STANDARD_MONSTER = "STANDARD_MONSTERS";
    public static final String COL_SMN_id = "id";
    public static final String COL_SMN_name = "name";
    public static final String COL_SMN_size = "size";
    public static final String COL_SMN_type = "type";
    public static final String COL_SMN_subtype = "subtype";
    public static final String COL_SMN_alignment = "alignment";
    public static final String COL_SMN_armor_class = "armor_class";
    public static final String COL_SMN_hit_points = "hit_points";
    public static final String COL_SMN_hit_dice = "hit_dice";
    public static final String COL_SMN_speed = "speed";
    public static final String COL_SMN_strength = "strength";
    public static final String COL_SMN_dexterity = "dexterity";
    public static final String COL_SMN_constitution = "constitution";
    public static final String COL_SMN_intelligence = "intelligence";
    public static final String COL_SMN_wisdom = "wisdom";
    public static final String COL_SMN_charisma = "charisma";
    public static final String COL_SMN_constitution_save = "constitution_save";
    public static final String COL_SMN_intelligence_save = "intelligence_save";
    public static final String COL_SMN_wisdom_save = "wisdom_save";
    public static final String COL_SMN_history = "history";
    public static final String COL_SMN_perception = "perception";
    public static final String COL_SMN_damage_vulnerabilities = "damage_vulnerabilities";
    public static final String COL_SMN_damage_resistances = "damage_resistances";
    public static final String COL_SMN_damage_immunities = "damage_immunities";
    public static final String COL_SMN_condition_immunities = "condition_immunities";
    public static final String COL_SMN_senses = "senses";
    public static final String COL_SMN_languages = "languages";
    public static final String COL_SMN_challenge_rating = "challenge_rating";
    public static final String COL_SMN_special_abilities = "special_abilities";
    public static final String COL_SMN_actions = "actions";
    public static final String COL_SMN_legendary_actions = "legendary_actions";
    public static final String COL_SMN_medicine = "medicine";
    public static final String COL_SMN_religion = "religion";
    public static final String COL_SMN_dexterity_save = "dexterity_save";
    public static final String COL_SMN_charisma_save = "charisma_save";
    public static final String COL_SMN_stealth = "stealth";
    public static final String COL_SMN_persuasion = "persuasion";
    public static final String COL_SMN_insight = "insight";
    public static final String COL_SMN_deception = "deception";
    public static final String COL_SMN_arcana = "arcana";
    public static final String COL_SMN_athletics = "athletics";
    public static final String COL_SMN_acrobatics = "acrobatics";
    public static final String COL_SMN_strength_save = "strength_save";
    public static final String COL_SMN_reactions = "reactions";
    public static final String COL_SMN_survival = "survival";
    public static final String COL_SMN_investigation = "investigation";
    public static final String COL_SMN_nature = "nature";
    public static final String COL_SMN_intimidation = "intimidation";
    public static final String COL_SMN_performance = "performance";
    public static final String COL_SMN_source = "source";

    public StandardMonsterDao(DaoMaster daoMaster) throws IOException {
        super(daoMaster, TBL_STANDARD_MONSTER);
    }

    @Override
    public ContentValues getContentValues(StandardMonster monster) {
        ContentValues content = new ContentValues();
        content.put(COL_SMN_id, monster.getId());
        content.put(COL_SMN_name, monster.getName());
        content.put(COL_SMN_size, monster.getSize());
        content.put(COL_SMN_type, monster.getType());
        content.put(COL_SMN_subtype, monster.getSubType());
        content.put(COL_SMN_alignment, monster.getAlignment());
        content.put(COL_SMN_armor_class, monster.getArmorClass());
        content.put(COL_SMN_hit_points, monster.getHitPoints());
        content.put(COL_SMN_hit_dice, monster.getHitDice());
        content.put(COL_SMN_speed, monster.getSpeed());
        content.put(COL_SMN_strength, monster.getStrength());
        content.put(COL_SMN_dexterity, monster.getDexterity());
        content.put(COL_SMN_constitution, monster.getConstitution());
        content.put(COL_SMN_intelligence, monster.getIntelligence());
        content.put(COL_SMN_wisdom, monster.getWisdom());
        content.put(COL_SMN_charisma, monster.getCharisma());
        content.put(COL_SMN_constitution_save, monster.getConstitutionSave());
        content.put(COL_SMN_intelligence_save, monster.getIntelligenceSave());
        content.put(COL_SMN_wisdom_save, monster.getWisdomSave());
        content.put(COL_SMN_history, monster.getHistory());
        content.put(COL_SMN_perception, monster.getPerception());
        content.put(COL_SMN_damage_vulnerabilities, monster.getDamageVulnerabilities());
        content.put(COL_SMN_damage_resistances, monster.getDamageResistances());
        content.put(COL_SMN_damage_immunities, monster.getDamageImmunities());
        content.put(COL_SMN_condition_immunities, monster.getConditionImmunities());
        content.put(COL_SMN_senses, monster.getSenses());
        content.put(COL_SMN_languages, monster.getLanguages());
        content.put(COL_SMN_challenge_rating, monster.getChallengeRating());
        content.put(COL_SMN_special_abilities, monster.getSpecialAbilities());
        content.put(COL_SMN_actions, monster.getActions());
        content.put(COL_SMN_legendary_actions, monster.getLegendaryActions());
        content.put(COL_SMN_medicine, monster.getMedicine());
        content.put(COL_SMN_religion, monster.getReligion());
        content.put(COL_SMN_dexterity_save, monster.getDexteritySave());
        content.put(COL_SMN_charisma_save, monster.getCharismaSave());
        content.put(COL_SMN_stealth, monster.getStealth());
        content.put(COL_SMN_persuasion, monster.getPersuasion());
        content.put(COL_SMN_insight, monster.getInsight());
        content.put(COL_SMN_deception, monster.getDeception());
        content.put(COL_SMN_arcana, monster.getArcana());
        content.put(COL_SMN_athletics, monster.getAthletics());
        content.put(COL_SMN_acrobatics, monster.getAcrobatics());
        content.put(COL_SMN_strength_save, monster.getStrengthSave());
        content.put(COL_SMN_reactions, monster.getReactions());
        content.put(COL_SMN_survival, monster.getSurvival());
        content.put(COL_SMN_investigation, monster.getInvestigation());
        content.put(COL_SMN_nature, monster.getNature());
        content.put(COL_SMN_intimidation, monster.getIntimidation());
        content.put(COL_SMN_performance, monster.getPerformance());
        content.put(COL_SMN_source, monster.getSource());

        return content;
    }

    @Override
    protected StandardMonster createNewModel() {
        return new StandardMonster();
    }

    @Override
    public String getIdColumn() {
        return COL_SMN_id;
    }

    @Override
    public Long getId(@NonNull StandardMonster monster) {
        return monster.getId();
    }

    @Override
    public void setId(@NonNull StandardMonster monster, @Nullable Long id) {
        monster.setId(id);
    }

    @Override
    public StandardMonster readRecord(Cursor cursor)
    {
        StandardMonster monster = new StandardMonster();

        monster.setId(cursor.getLong(cursor.getColumnIndex(COL_SMN_id)));
        monster.setName(cursor.getString(cursor.getColumnIndex(COL_SMN_name)));
        monster.setSize(cursor.getString(cursor.getColumnIndex(COL_SMN_size)));
        monster.setType(cursor.getString(cursor.getColumnIndex(COL_SMN_type)));
        monster.setSubType(cursor.getString(cursor.getColumnIndex(COL_SMN_subtype)));
        monster.setAlignment(cursor.getString(cursor.getColumnIndex(COL_SMN_alignment)));
        monster.setArmorClass(cursor.getString(cursor.getColumnIndex(COL_SMN_armor_class)));
        monster.setHitPoints(cursor.getString(cursor.getColumnIndex(COL_SMN_hit_points)));
        monster.setHitDice(cursor.getString(cursor.getColumnIndex(COL_SMN_hit_dice)));
        monster.setSpeed(cursor.getString(cursor.getColumnIndex(COL_SMN_speed)));

        monster.setStrength(cursor.getInt(cursor.getColumnIndex(COL_SMN_strength)));
        monster.setDexterity(cursor.getInt(cursor.getColumnIndex(COL_SMN_dexterity)));
        monster.setConstitution(cursor.getInt(cursor.getColumnIndex(COL_SMN_constitution)));
        monster.setIntelligence(cursor.getInt(cursor.getColumnIndex(COL_SMN_intelligence)));
        monster.setWisdom(cursor.getInt(cursor.getColumnIndex(COL_SMN_wisdom)));
        monster.setCharisma(cursor.getInt(cursor.getColumnIndex(COL_SMN_charisma)));

        monster.setStrengthSave(getInteger(cursor,COL_SMN_strength_save));
        monster.setDexteritySave(getInteger(cursor,COL_SMN_dexterity_save));
        monster.setConstitutionSave(getInteger(cursor,COL_SMN_constitution_save));
        monster.setIntelligenceSave(getInteger(cursor,COL_SMN_intelligence_save));
        monster.setWisdomSave(getInteger(cursor,COL_SMN_wisdom_save));
        monster.setCharismaSave(getInteger(cursor,COL_SMN_charisma_save));

        monster.setDamageVulnerabilities(cursor.getString(cursor.getColumnIndex(COL_SMN_damage_vulnerabilities)));
        monster.setDamageResistances(cursor.getString(cursor.getColumnIndex(COL_SMN_damage_resistances)));
        monster.setDamageImmunities(cursor.getString(cursor.getColumnIndex(COL_SMN_damage_immunities)));
        monster.setConditionImmunities(cursor.getString(cursor.getColumnIndex(COL_SMN_condition_immunities)));
        monster.setSenses(cursor.getString(cursor.getColumnIndex(COL_SMN_senses)));
        monster.setLanguages(cursor.getString(cursor.getColumnIndex(COL_SMN_languages)));
        monster.setChallengeRating(cursor.getString(cursor.getColumnIndex(COL_SMN_challenge_rating)));
        monster.setSpecialAbilities(cursor.getString(cursor.getColumnIndex(COL_SMN_special_abilities)));
        monster.setActions(cursor.getString(cursor.getColumnIndex(COL_SMN_actions)));
        monster.setReactions(cursor.getString(cursor.getColumnIndex(COL_SMN_reactions)));
        monster.setLegendaryActions(cursor.getString(cursor.getColumnIndex(COL_SMN_legendary_actions)));

        monster.setHistory(getInteger(cursor,COL_SMN_history));
        monster.setPerception(getInteger(cursor,COL_SMN_perception));
        monster.setMedicine(getInteger(cursor,COL_SMN_medicine));
        monster.setReligion(getInteger(cursor,COL_SMN_religion));
        monster.setStealth(getInteger(cursor,COL_SMN_stealth));
        monster.setPersuasion(getInteger(cursor,COL_SMN_persuasion));
        monster.setInsight(getInteger(cursor,COL_SMN_insight));
        monster.setDeception(getInteger(cursor,COL_SMN_deception));
        monster.setArcana(getInteger(cursor,COL_SMN_arcana));
        monster.setAthletics(getInteger(cursor,COL_SMN_athletics));
        monster.setAcrobatics(getInteger(cursor,COL_SMN_acrobatics));
        monster.setSurvival(getInteger(cursor,COL_SMN_survival));
        monster.setInvestigation(getInteger(cursor,COL_SMN_investigation));
        monster.setNature(getInteger(cursor,COL_SMN_nature));
        monster.setIntimidation(getInteger(cursor,COL_SMN_intimidation));
        monster.setPerformance(getInteger(cursor,COL_SMN_performance));

        monster.setSource(cursor.getString(cursor.getColumnIndex(COL_SMN_source)));
        return monster;
    }

    /**
     * This will determine what the value is for the column as an integer. If it can't be parsed into a number
     * IT is it null, blank or some weird text it is assumed to be null
     * @param cursor
     * @param colName
     * @return the expected value for the column
     */
    private static final Integer getInteger(Cursor cursor, String colName) {
        int columnIndex = cursor.getColumnIndex(colName);
        String text = cursor.getString(columnIndex);
        if (Text.isBlank(text)) {
            Logger.debug(" - " + colName + " = \"" + text + "\"");
            return null;
        } else {
            Logger.debug(" - " + colName + " = \"" + cursor.getString(columnIndex) + "\"");
            return cursor.getInt(columnIndex);
        }
    }

    @Override
    public Set<String> getSearchableColumns() {
        HashSet<String> columns = new HashSet<>();
        columns.add(COL_SMN_name);
        columns.add(COL_SMN_size);
        columns.add(COL_SMN_type);
        columns.add(COL_SMN_subtype);
        columns.add(COL_SMN_alignment);

        columns.add(COL_SMN_speed);

        columns.add(COL_SMN_damage_vulnerabilities);
        columns.add(COL_SMN_damage_resistances);
        columns.add(COL_SMN_damage_immunities);
        columns.add(COL_SMN_condition_immunities);
        columns.add(COL_SMN_senses);
        columns.add(COL_SMN_languages);


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
        columns.add(COL_SMN_challenge_rating);
        columns.add(COL_SMN_name);
        return columns;

    }

}
