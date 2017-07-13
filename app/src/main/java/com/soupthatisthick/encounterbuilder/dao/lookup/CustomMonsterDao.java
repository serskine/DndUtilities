package com.soupthatisthick.encounterbuilder.dao.lookup;

import android.content.ContentValues;
import android.database.Cursor;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.soupthatisthick.encounterbuilder.model.lookup.CustomMonster;
import com.soupthatisthick.util.dao.DaoMaster;
import com.soupthatisthick.util.dao.WriteDao;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by Owner on 1/28/2017.
 * Copyright of Stuart Marr Erskine, all rights reserved.
 */

public class CustomMonsterDao extends WriteDao<CustomMonster> {

    public static final String TBL_CUSTOM_MONSTER = "CUSTOM_MONSTERS";

    public static final String COL_CMN_ID = "id";
    public static final String COL_CMN_NAME = "name";
    public static final String COL_CMN_TYPE = "type";
    public static final String COL_CMN_ALIGN = "alignment";
    public static final String COL_CMN_AC = "ac";
    public static final String COL_CMN_ACTYPE = "acType";
    public static final String COL_CMN_HP = "hp";
    public static final String COL_CMN_HD = "hd";
    public static final String COL_CMN_SPEED = "speed";
    public static final String COL_CMN_STR = "str";
    public static final String COL_CMN_DEX = "dex";
    public static final String COL_CMN_CON = "con";
    public static final String COL_CMN_INT = "int";
    public static final String COL_CMN_WIS = "wis";
    public static final String COL_CMN_CHA = "cha";
    public static final String COL_CMN_SAVES = "saves";
    public static final String COL_CMN_SKILLS = "skills";
    public static final String COL_CMN_DMGRES = "dmgResistance";
    public static final String COL_CMN_DMGIMM = "dmgImmunity";
    public static final String COL_CMN_CONDIMM = "conditionImmunity";
    public static final String COL_CMN_SENSES = "senses";
    public static final String COL_CMN_LANG = "languages";
    public static final String COL_CMN_CR = "cr";
    public static final String COL_CMN_XP = "xp";
    public static final String COL_CMN_ABIL = "abilities";
    public static final String COL_CMN_ACTIONS = "actions";
    public static final String COL_CMN_LEGENDARY = "legendary";
    public static final String COL_CMN_OTHER = "other";
    public static final String COL_CMN_SOURCE = "source";

    public CustomMonsterDao(DaoMaster daoMaster) throws IOException {
        super(daoMaster, TBL_CUSTOM_MONSTER);
    }




    @Override
    public ContentValues getContentValues(CustomMonster customMonster) {
        ContentValues content = new ContentValues();

        content.put(COL_CMN_ID, customMonster.getId());
        content.put(COL_CMN_NAME, customMonster.getName());
        content.put(COL_CMN_TYPE, customMonster.getType());
        content.put(COL_CMN_ALIGN, customMonster.getAlignment());
        content.put(COL_CMN_AC, customMonster.getAc());
        content.put(COL_CMN_ACTYPE, customMonster.getAcType());
        content.put(COL_CMN_HP, customMonster.getHp());
        content.put(COL_CMN_HD, customMonster.getHd());
        content.put(COL_CMN_SPEED, customMonster.getSpeed());
        content.put(COL_CMN_STR, customMonster.getStrength());
        content.put(COL_CMN_DEX, customMonster.getDexterity());
        content.put(COL_CMN_CON, customMonster.getConstitution());
        content.put(COL_CMN_INT, customMonster.getIntelligence());
        content.put(COL_CMN_WIS, customMonster.getWisdom());
        content.put(COL_CMN_CHA, customMonster.getCharisma());
        content.put(COL_CMN_SAVES, customMonster.getSaves());
        content.put(COL_CMN_SKILLS, customMonster.getSkills());
        content.put(COL_CMN_DMGRES, customMonster.getDmgResistance());
        content.put(COL_CMN_DMGIMM, customMonster.getDmgImmunity());
        content.put(COL_CMN_CONDIMM, customMonster.getConditionImmunity());
        content.put(COL_CMN_SENSES, customMonster.getSenses());
        content.put(COL_CMN_LANG, customMonster.getLanguages());
        content.put(COL_CMN_CR, customMonster.getCr());
        content.put(COL_CMN_XP, customMonster.getXp());
        content.put(COL_CMN_ABIL, customMonster.getAbilities());
        content.put(COL_CMN_ACTIONS, customMonster.getActions());
        content.put(COL_CMN_LEGENDARY, customMonster.getLegendaryActions());
        content.put(COL_CMN_OTHER, customMonster.getOther());
        content.put(COL_CMN_SOURCE, customMonster.getSource());

        return content;
    }

    @Override
    protected CustomMonster createNewModel() {
        return new CustomMonster();
    }

    @Override
    public String getIdColumn() {
        return COL_CMN_ID;
    }

    @Override
    public Long getId(@NonNull CustomMonster customMonster) {
        return customMonster.getId();
    }

    @Override
    public void setId(@NonNull CustomMonster customMonster, @Nullable Long id) {
        customMonster.setId(id);
    }

    @Override
    public CustomMonster readRecord(Cursor cursor)
    {
        CustomMonster customMonster = new CustomMonster();

        customMonster.setId(          cursor.getLong(cursor.getColumnIndex(COL_CMN_ID))       );

        customMonster.setName(        cursor.getString(cursor.getColumnIndex(COL_CMN_NAME))   );
        customMonster.setType(        cursor.getString(cursor.getColumnIndex(COL_CMN_TYPE))   );
        customMonster.setAlignment(   cursor.getString(cursor.getColumnIndex(COL_CMN_ALIGN))  );

        customMonster.setCr(          cursor.getString(cursor.getColumnIndex(COL_CMN_CR))     );
        customMonster.setXp(          cursor.getInt(cursor.getColumnIndex(COL_CMN_XP))        );

        customMonster.setSpeed(       cursor.getString(cursor.getColumnIndex(COL_CMN_SPEED))  );
        customMonster.setSenses(      cursor.getString(cursor.getColumnIndex(COL_CMN_SENSES)) );

        customMonster.setAc(          cursor.getInt(cursor.getColumnIndex(COL_CMN_AC))        );
        customMonster.setAcType(      cursor.getString(cursor.getColumnIndex(COL_CMN_ACTYPE)) );

        customMonster.setHd(          cursor.getString(cursor.getColumnIndex(COL_CMN_HD))     );
        customMonster.setHp(          cursor.getString(cursor.getColumnIndex(COL_CMN_HP))     );

        customMonster.setStrength(        cursor.getInt(cursor.getColumnIndex(COL_CMN_STR))   );
        customMonster.setDexterity(       cursor.getInt(cursor.getColumnIndex(COL_CMN_DEX))   );
        customMonster.setConstitution(    cursor.getInt(cursor.getColumnIndex(COL_CMN_CON))   );
        customMonster.setIntelligence(    cursor.getInt(cursor.getColumnIndex(COL_CMN_INT))   );
        customMonster.setWisdom(          cursor.getInt(cursor.getColumnIndex(COL_CMN_WIS))   );
        customMonster.setCharisma(        cursor.getInt(cursor.getColumnIndex(COL_CMN_CHA))   );

        customMonster.setSaves(           cursor.getString(cursor.getColumnIndex(COL_CMN_SAVES))  );
        customMonster.setSkills(          cursor.getString(cursor.getColumnIndex(COL_CMN_SKILLS)) );
        customMonster.setLanguages(       cursor.getString(cursor.getColumnIndex(COL_CMN_LANG))   );

        customMonster.setConditionImmunity(   cursor.getString(cursor.getColumnIndex(COL_CMN_CONDIMM))    );
        customMonster.setDmgResistance(       cursor.getString(cursor.getColumnIndex(COL_CMN_DMGRES))     );
        customMonster.setDmgImmunity(         cursor.getString(cursor.getColumnIndex(COL_CMN_DMGIMM))     );

        customMonster.setAbilities(           cursor.getString(cursor.getColumnIndex(COL_CMN_ABIL))       );
        customMonster.setActions(             cursor.getString(cursor.getColumnIndex(COL_CMN_ACTIONS))    );
        customMonster.setLegendaryActions(    cursor.getString(cursor.getColumnIndex(COL_CMN_LEGENDARY))  );
        customMonster.setOther(               cursor.getString(cursor.getColumnIndex(COL_CMN_OTHER))      );

        customMonster.setSource( cursor.getString(cursor.getColumnIndex(COL_CMN_SOURCE))  );

        return customMonster;
    }

    @Override
    public Set<String> getSearchableColumns() {
        HashSet<String> columns = new HashSet<>();
        
        columns.add(COL_CMN_NAME);
        columns.add(COL_CMN_TYPE);
        columns.add(COL_CMN_ALIGN);
        columns.add(COL_CMN_AC);
        columns.add(COL_CMN_ACTYPE);
//        columns.add(COL_CMN_HP);
//        columns.add(COL_CMN_HD);
//        columns.add(COL_CMN_SPEED);
//        columns.add(COL_CMN_STR);
//        columns.add(COL_CMN_DEX);
//        columns.add(COL_CMN_CON);
//        columns.add(COL_CMN_INT);
//        columns.add(COL_CMN_WIS);
//        columns.add(COL_CMN_CHA);
        columns.add(COL_CMN_SAVES);
        columns.add(COL_CMN_SKILLS);
        columns.add(COL_CMN_DMGRES);
        columns.add(COL_CMN_DMGIMM);
        columns.add(COL_CMN_CONDIMM);
        columns.add(COL_CMN_SENSES);
        columns.add(COL_CMN_LANG);
//        columns.add(COL_CMN_CR);
//        columns.add(COL_CMN_XP);
        // We don't want to search abilities and actions because it will
        // cause to many hits
//        columns.add(COL_CMN_ABIL);
//        columns.add(COL_CMN_ACTIONS);
//        columns.add(COL_CMN_LEGENDARY);
        columns.add(COL_CMN_OTHER);
        columns.add(COL_CMN_SOURCE);

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
        columns.add(COL_CMN_CR);
        columns.add(COL_CMN_NAME);
        return columns;

    }

}
