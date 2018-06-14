package com.soupthatisthick.encounterbuilder.model.lookup;

import android.support.annotation.NonNull;

import com.soupthatisthick.encounterbuilder.model.DaoModel;
import com.soupthatisthick.encounterbuilder.util.sort.Category;


/**
 * Created by Owner on 4/18/2017.
 * Copyright of Stuart Marr Erskine, all rights reserved.
 */
@SuppressWarnings("WeakerAccess")
public class Item extends DaoModel {

            private Long itemId;

        private Long listId;

        private String metaData;

        private String table;

        private String primaryKey;

        private Long key;

        public Item(Long itemId, Long listId, String metaData, String table, String primaryKey, Long key) {
        this.itemId = itemId;
        this.listId = listId;
        this.metaData = metaData;
        this.table = table;
        this.primaryKey = primaryKey;
        this.key = key;
    }

        public Item() {
    }

    /**
     * Gets the id of the item
     */
    public Long getItemId() {
        return itemId;
    }

    /**
     * This will set the unique id for this items. All items should have different id's no matter what
     * list they are contained within.
     */
    public void setItemId(Long itemId) {
        this.itemId = itemId;
    }

    /**
     * Gets the id of the list this belongs to
     */
    public Long getListId() {
        return listId;
    }

    /**
     * This will indicate which list this list item belongs to
     */
    public void setListId(Long listId) {
        this.listId = listId;
    }

    /**
     * This will return all the text that describes this list item in addition to the item itself
     */
    public String getMetaData() {
        return metaData;
    }

    /**
     * This is a column that will be included for search queries. It is not intended to be visible to the user
     * except by editing
     */
    public void setMetaData(String metaData) {
        this.metaData = metaData;
    }

    /**
     * Gets the table associated with the Item
     */
    public String getTable() {
        return table;
    }

    /**
     * This will determine what database table we access
     */
    public void setTable(String table) {
        this.table = table;
    }

    /**
     * returns the primary key of the table the item is on
     */
    public String getPrimaryKey() {
        return primaryKey;
    }

    /**
     * This determines what table the item is contained in
     */
    public void setPrimaryKey(String primaryKey) {
        this.primaryKey = primaryKey;
    }

    /**
     * Returns the key of the item on the other table
     */
    public Long getKey() {
        return key;
    }

    /**
     * Sets the key to reference the item from the database with using a limit 1 select query on the primary key column.
     */
    public void setKey(Long key) {
        this.key = key;
    }

    public Long getId() {
        return getItemId();
    }

    /**
     * This will set the key, table and primary key based on the category and id of the specified item.
     */
    public void init(@NonNull DaoModel item)
    {
        Category category = item.getCategory();
        setKey(item.getId());
        setTable(category.tableName);
        setPrimaryKey(category.primaryKey);
    }
}
