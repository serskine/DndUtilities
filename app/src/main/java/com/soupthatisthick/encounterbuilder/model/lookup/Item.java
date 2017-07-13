package com.soupthatisthick.encounterbuilder.model.lookup;

import android.support.annotation.NonNull;

import com.soupthatisthick.encounterbuilder.model.DaoModel;
import com.soupthatisthick.encounterbuilder.util.sort.Category;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Property;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by Owner on 4/18/2017.
 * Copyright of Stuart Marr Erskine, all rights reserved.
 */
@Entity(nameInDb = "LIST_ITEMS")
public class Item extends DaoModel {

    @Id
    @Property(nameInDb = "itemId")
    private Long itemId;

    @Property(nameInDb = "listId")
    private Long listId;

    @Property(nameInDb = "metaData")
    private String metaData;

    @Property(nameInDb = "table")
    private String table;

    @Property(nameInDb = "primaryKey")
    private String primaryKey;

    @Property(nameInDb = "key")
    private Long key;

    @Generated(hash = 1640487811)
    public Item(Long itemId, Long listId, String metaData, String table, String primaryKey, Long key) {
        this.itemId = itemId;
        this.listId = listId;
        this.metaData = metaData;
        this.table = table;
        this.primaryKey = primaryKey;
        this.key = key;
    }

    @Generated(hash = 1470900980)
    public Item() {
    }

    /**
     * @return a unique identifier for an item in a list.
     */
    public Long getItemId() {
        return itemId;
    }

    /**
     * This will set the unique id for this items. All items should have different id's no matter what
     * list they are contained within.
     * @param itemId
     */
    public void setItemId(Long itemId) {
        this.itemId = itemId;
    }

    /**
     * @return the id of the list that this item belongs to
     */
    public Long getListId() {
        return listId;
    }

    /**
     * This will indicate which list this list item belongs to
     * @param listId
     */
    public void setListId(Long listId) {
        this.listId = listId;
    }

    /**
     * This will return all the text that describes this list item in addition to the item itself
     * @return
     */
    public String getMetaData() {
        return metaData;
    }

    /**
     * This is a column that will be included for search queries. It is not intended to be visible to the user
     * except by editing
     * @param metaData
     */
    public void setMetaData(String metaData) {
        this.metaData = metaData;
    }

    /**
     * @return the name of the database table that will contain the item
     */
    public String getTable() {
        return table;
    }

    /**
     * This will determine what database table we access
     * @param table is the name of the table in the database (or DaoMaster)
     */
    public void setTable(String table) {
        this.table = table;
    }

    /**
     * @return the name of the database column for the primary key used to select the item from
     */
    public String getPrimaryKey() {
        return primaryKey;
    }

    /**
     * This determines what table the item is contained in
     * @param primaryKey
     */
    public void setPrimaryKey(String primaryKey) {
        this.primaryKey = primaryKey;
    }

    /**
     * @return the id in the database table this is contained in
     */
    public Long getKey() {
        return key;
    }

    /**
     * Sets the key to reference the item from the database with using a limit 1 select query on the primary key column.
     * @param key
     */
    public void setKey(Long key) {
        this.key = key;
    }

    @Override
    public Long getId() {
        return getItemId();
    }

    /**
     * This will set the key, table and primary key based on the category and id of the specified item.
     * @param item
     */
    public void init(@NonNull DaoModel item)
    {
        Category category = item.getCategory();
        setKey(item.getId());
        setTable(category.tableName);
        setPrimaryKey(category.primaryKey);
    }
}
