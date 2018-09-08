package com.soupthatisthick.encounterbuilder.dao.lookup;

import android.content.ContentValues;
import android.database.Cursor;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.soupthatisthick.encounterbuilder.model.lookup.MagicItem;
import com.soupthatisthick.encounterbuilder.util.translater.CommaListTranslater;
import com.soupthatisthick.util.dao.DaoMaster;
import com.soupthatisthick.util.dao.WriteDao;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by Owner on 2/2/2017.
 * Copyright of Stuart Marr Erskine, all rights reserved.
 */

public class MagicItemDao extends WriteDao<MagicItem> {

    public static final String TBL_ITEMS = "MAGIC_ITEMS";
    public static final String COL_ID = "id";
    public static final String COL_NAME = "name";
    public static final String COL_TYPE = "type";
    public static final String COL_RARITY = "rarity";
    public static final String COL_ATTUNEMENT = "attunement";
    public static final String COL_TREASURE_POINTS = "treasure_points";
    public static final String COL_TREASURE_TABLE = "treasure_table";


    // These are to be added later for AL legal items.
    public static final String COL_DESC = "description";
    public static final String COL_LOCATION = "location";

    public MagicItemDao(DaoMaster daoMaster) throws IOException {
        super(daoMaster, TBL_ITEMS);
    }

    @Override
    public ContentValues getContentValues(MagicItem magicItem) {
        ContentValues content = new ContentValues();

        content.put(COL_ID, magicItem.getId());
        content.put(COL_NAME, magicItem.getName());
        content.put(COL_TYPE, magicItem.getType());
        content.put(COL_RARITY, magicItem.getRarity());
        content.put(COL_ATTUNEMENT, magicItem.getAttunement());
        content.put(COL_DESC, magicItem.getDescription());
        content.put(COL_LOCATION, magicItem.getLocation());
        content.put(COL_TREASURE_POINTS, magicItem.getTreasurePoints());

        return content;
    }

    @Override
    protected MagicItem createNewModel() {
        return new MagicItem();
    }


    @Override
    public String getIdColumn() {
        return COL_ID;
    }

    @Override
    public Long getId(@NonNull MagicItem magicItem) {
        return magicItem.getId();
    }

    @Override
    public void setId(@NonNull MagicItem magicItem, @Nullable Long id) {
        magicItem.setId(id);
    }

    @Override
    public MagicItem readRecord(Cursor cursor) {
        MagicItem item = new MagicItem();

        item.setId(cursor.getLong(cursor.getColumnIndex(COL_ID)));
        item.setName(cursor.getString(cursor.getColumnIndex(COL_NAME)));
        item.setType(cursor.getString(cursor.getColumnIndex(COL_TYPE)));
        item.setRarity(cursor.getString(cursor.getColumnIndex(COL_RARITY)));
        item.setAttunement(cursor.getString(cursor.getColumnIndex(COL_ATTUNEMENT)));

        item.setDescription(cursor.getString(cursor.getColumnIndex(COL_DESC)));
        item.setLocation(cursor.getString(cursor.getColumnIndex(COL_LOCATION)));

        item.setTreasurePoints(cursor.getInt(cursor.getColumnIndex(COL_TREASURE_POINTS)));
        item.setTreasureTableText(cursor.getString(cursor.getColumnIndex(COL_TREASURE_TABLE)));

        return item;
    }

    @Override
    public Set<String> getSearchableColumns() {
        HashSet<String> columns = new HashSet<>();

        // We don't want to search by id because that field is not visible to the user.
        columns.add(COL_NAME);
        columns.add(COL_TYPE);
        columns.add(COL_RARITY);
        columns.add(COL_ATTUNEMENT);
        // We don't want to search the description to prevent to many hits
        columns.add(COL_LOCATION);
        columns.add(COL_TREASURE_TABLE);

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
        columns.add(COL_NAME);
        columns.add(COL_TREASURE_POINTS);
        columns.add(COL_RARITY);
        columns.add(COL_TYPE);
        columns.add(COL_ATTUNEMENT);
        return columns;
    }



}
