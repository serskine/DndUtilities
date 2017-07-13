package com.soupthatisthick.encounterbuilder.dao.lookup;

import android.content.ContentValues;
import android.database.Cursor;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.soupthatisthick.encounterbuilder.model.DaoModel;
import com.soupthatisthick.encounterbuilder.model.lookup.Condition;
import com.soupthatisthick.encounterbuilder.model.lookup.CustomMonster;
import com.soupthatisthick.encounterbuilder.model.lookup.Item;
import com.soupthatisthick.encounterbuilder.util.sort.Category;
import com.soupthatisthick.util.dao.DaoMaster;
import com.soupthatisthick.util.dao.WriteDao;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by Owner on 4/18/2017.
 * Copyright of Stuart Marr Erskine, all rights reserved.
 */

public class ItemDao extends WriteDao<Item> {

    public static final String TBL_LISTITEMS = "LIST_ITEMS";
    public static final String COL_ITEMID = "itemId";
    public static final String COL_LISTID = "listId";
    public static final String COL_METADATA = "metaData";
    public static final String COL_TABLE = "table";
    public static final String COL_PRIMARY_KEY = "primaryKey";
    public static final String COL_KEY = "key";

    private Long id, listId, fieldId;
    private String tableName;

    public ItemDao(DaoMaster daoMaster) throws IOException {
        super(daoMaster, TBL_LISTITEMS);
    }

    @Override
    public Item readRecord(Cursor cursor) {
        Item item = new Item();
        item.setItemId(cursor.getLong(cursor.getColumnIndex(COL_ITEMID)));
        item.setItemId(cursor.getLong(cursor.getColumnIndex(COL_LISTID)));
        item.setMetaData(cursor.getString(cursor.getColumnIndex(COL_METADATA)));
        item.setMetaData(cursor.getString(cursor.getColumnIndex(COL_TABLE)));
        item.setPrimaryKey(cursor.getString(cursor.getColumnIndex(COL_PRIMARY_KEY)));
        item.setKey(cursor.getLong(cursor.getColumnIndex(COL_KEY)));
        return item;
    }

    /**
     * This method will return all the columns that may be searched for matches in a text search.
     *
     * @return a Set of columns elidgible for searching with a like query
     */
    @Override
    public Set<String> getSearchableColumns() {
        HashSet<String> searchableColumns = new HashSet<>();
        searchableColumns.add(COL_METADATA);
        return searchableColumns;
    }

    /**
     * This is a list of columns that we will sort the results by when we return a dataset.
     *
     * @return a List of columns. Sorting is first done by the first column then by the next column etc until all columns have been ordered.
     */
    @Override
    public List<String> getColumnSortingOrder() {
        ArrayList<String> sortingOrder = new ArrayList<String>();
        sortingOrder.add(COL_TABLE);
        sortingOrder.add(COL_LISTID);
        return sortingOrder;
    }

    /**
     * This will build a mapping of column names to values from the specified record
     * so that they may be inserted.
     *
     * @param item
     */
    @Override
    public ContentValues getContentValues(Item item) {
        ContentValues contentValues = new ContentValues();

        contentValues.put(COL_ITEMID, item.getItemId());
        contentValues.put(COL_LISTID, item.getListId());
        contentValues.put(COL_METADATA, item.getMetaData());
        contentValues.put(COL_TABLE, item.getTable());
        contentValues.put(COL_PRIMARY_KEY, item.getPrimaryKey());
        contentValues.put(COL_KEY, item.getKey());

        return contentValues;
    }

    /**
     * This will create a new model and return it
     *
     * @return a new Record to update
     */
    @Override
    protected Item createNewModel() {
        return new Item();
    }

    @Override
    public String getIdColumn() {
        return COL_ITEMID;
    }

    @Override
    public Long getId(@NonNull Item item) {
        return item.getItemId();
    }

    @Override
    public void setId(@NonNull Item item, @Nullable Long id) {
        item.setItemId(id);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getListId() {
        return listId;
    }

    public void setListId(Long listId) {
        this.listId = listId;
    }

    public Long getFieldId() {
        return fieldId;
    }

    public void setFieldId(Long fieldId) {
        this.fieldId = fieldId;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }
}
