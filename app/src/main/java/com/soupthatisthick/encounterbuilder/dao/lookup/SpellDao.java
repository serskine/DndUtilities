package com.soupthatisthick.encounterbuilder.dao.lookup;

import android.content.ContentValues;
import android.database.Cursor;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.soupthatisthick.encounterbuilder.model.lookup.Spell;
import com.soupthatisthick.util.dao.DaoMaster;
import com.soupthatisthick.util.dao.WriteDao;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Owner on 1/28/2017.
 * Copyright of Stuart Marr Erskine, all rights reserved.
 */

public class SpellDao extends WriteDao<Spell> {

    public static final String TBL_SP = "EDITABLE_SPELLS";
    public static final String COL_SP_ID = "id";
    public static final String COL_SP_LEVEL = "level";
    public static final String COL_SP_NAME = "name";
    public static final String COL_SP_TYPE = "type";
    public static final String COL_SP_CASTING = "castingTime";
    public static final String COL_SP_RANGE = "range";
    public static final String COL_SP_COMPONENTS = "components";
    public static final String COL_SP_DURATION = "duration";
    public static final String COL_SP_DESC = "description";
    public static final String COL_SP_CLASSES = "class";
    public static final String COL_SP_MATERIALS = "materials";

    public SpellDao(DaoMaster daoMaster) throws IOException {
        super(daoMaster, TBL_SP);
    }

    @Override
    public ContentValues getContentValues(Spell spell) {
        ContentValues content = new ContentValues();

        content.put(COL_SP_ID, spell.getId());
        content.put(COL_SP_LEVEL, spell.getLevel());
        content.put(COL_SP_NAME, spell.getName());
        content.put(COL_SP_TYPE, spell.getType());
        content.put(COL_SP_CASTING, spell.getCastingTime());
        content.put(COL_SP_RANGE, spell.getRange());
        content.put(COL_SP_COMPONENTS, spell.getComponents());
        content.put(COL_SP_DURATION, spell.getDuration());
        content.put(COL_SP_DESC, spell.getDescription());
        content.put(COL_SP_CLASSES, spell.getClasses());
        content.put(COL_SP_MATERIALS, spell.getMaterials());

        return content;
    }

    @Override
    protected Spell createNewModel() {
        return new Spell();
    }

    @Override
    public String getIdColumn() {
        return COL_SP_ID;
    }

    @Override
    public Long getId(@NonNull Spell spell) {
        return spell.getId();
    }

    @Override
    public void setId(@NonNull Spell spell, @Nullable Long id) {
        spell.setId(id);
    }

    @Override
    public Spell readRecord(Cursor cursor) {
        Spell spell = new Spell();

        spell.setId(cursor.getLong(cursor.getColumnIndex(COL_SP_ID)));
        spell.setLevel(cursor.getInt(cursor.getColumnIndex(COL_SP_LEVEL)));
        spell.setName(cursor.getString(cursor.getColumnIndex(COL_SP_NAME)));
        spell.setCastingTime(cursor.getString(cursor.getColumnIndex(COL_SP_CASTING)));
        spell.setRange(cursor.getString(cursor.getColumnIndex(COL_SP_RANGE)));
        spell.setComponents(cursor.getString(cursor.getColumnIndex(COL_SP_COMPONENTS)));
        spell.setDuration(cursor.getString(cursor.getColumnIndex(COL_SP_DURATION)));

        // TODO: Fix problems with the database.
        spell.setDescription(cursor.getString(cursor.getColumnIndex(COL_SP_DESC)));
        spell.setMaterials(cursor.getString(cursor.getColumnIndex(COL_SP_MATERIALS)));

        spell.setType(cursor.getString(cursor.getColumnIndex(COL_SP_TYPE)));
        spell.setClasses(cursor.getString(cursor.getColumnIndex(COL_SP_CLASSES)));


        return spell;
    }

    public static final String[] extractComponentsAndDescription(String source)
    {
        String materials = "";
        String description = source;

        String regex = "\\(([^()]*|\n)\\)";
        Pattern pattern = Pattern.compile(regex, Pattern.MULTILINE);
        Matcher matcher = pattern.matcher(source);

        if (matcher.find()) {
            String match = matcher.group(1);
            if (description.startsWith("(" + match + ")")) {
                materials = match;
                description = matcher.replaceFirst("");
            }
        }

        return new String[] {
            materials,
            description
        };

    }

    @Override
    public Set<String> getSearchableColumns() {
        Set<String> columns = new HashSet<>();

        columns.add(COL_SP_LEVEL);
        columns.add(COL_SP_NAME);
        columns.add(COL_SP_TYPE);
        columns.add(COL_SP_CASTING);
        columns.add(COL_SP_RANGE);
        columns.add(COL_SP_COMPONENTS);
        columns.add(COL_SP_DURATION);
        // We don't want to search the description because it cause too many matchess to keywords.
        columns.add(COL_SP_CLASSES);
        columns.add(COL_SP_MATERIALS);

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
        columns.add(COL_SP_NAME);
        columns.add(COL_SP_LEVEL);
        return columns;
    }

}
