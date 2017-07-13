package com.soupthatisthick.encounterbuilder.dao.lookup;

import android.content.ContentValues;
import android.database.Cursor;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.soupthatisthick.encounterbuilder.model.lookup.Setting;
import com.soupthatisthick.encounterbuilder.model.lookup.SettingId;
import com.soupthatisthick.util.Logger;
import com.soupthatisthick.util.dao.DaoMaster;
import com.soupthatisthick.util.dao.WriteDao;

import org.greenrobot.greendao.annotation.Entity;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by Owner on 5/28/2017.
 * Copyright of Stuart Marr Erskine, all rights reserved.
 */
public class SettingDao extends WriteDao<Setting> {

    public static final String TBL_SETTINGS = "SETTINGS";
    public static final String COL_ID = "id";
    public static final String COL_NAME = "name";
    public static final String COL_VALUE = "value";

    public SettingDao(DaoMaster daoMaster) throws IOException {
        super(daoMaster, TBL_SETTINGS);
    }

    @Override
    public Setting readRecord(Cursor cursor) {
        Setting setting = new Setting();
        setting.setId(cursor.getLong(cursor.getColumnIndex(COL_ID)));
        setting.setName(cursor.getString(cursor.getColumnIndex(COL_NAME)));
        setting.setValue(cursor.getString(cursor.getColumnIndex(COL_VALUE)));
        return setting;
    }

    /**
     * This method will return all the columns that may be searched for matches in a text search.
     *
     * @return a Set of columns elidgible for searching with a like query
     */
    @Override
    public Set<String> getSearchableColumns() {
        Set<String> columns = new HashSet<>();
        columns.add(COL_NAME);
        return columns;
    }

    /**
     * This is a list of columns that we will sort the results by when we return a dataset.
     *
     * @return a List of columns. Sorting is first done by the first column then by the next column etc until all columns have been ordered.
     */
    @Override
    public List<String> getColumnSortingOrder() {
        ArrayList<String> order = new ArrayList<>();
        order.add(COL_NAME);
        return order;
    }

    /**
     * This will build a mapping of column names to values from the specified record
     * so that they may be inserted.
     *
     * @param setting
     */
    @Override
    public ContentValues getContentValues(Setting setting) {
        ContentValues content = new ContentValues();
        content.put(COL_ID, setting.getId());
        content.put(COL_NAME, setting.getValue());
        content.put(COL_VALUE, setting.getValue());
        return content;
    }

    /**
     * This will create a new model and return it
     *
     * @return a new Record to update
     */
    @Override
    protected Setting createNewModel() {
        return new Setting();
    }

    @Override
    public String getIdColumn() {
        return COL_ID;
    }

    @Override
    public Long getId(@NonNull Setting setting) {
        return setting.getId();
    }

    @Override
    public void setId(@NonNull Setting setting, @Nullable Long id) {
        setting.setId(id);
    }

    /**
     * This will return the setting object. If it does not exist then it will be created
     * if possible and persisted with the default value provided.
     * @param name
     * @param defaultValue
     * @return
     * @throws Exception
     */
    protected Setting getSetting(String name, String defaultValue) throws Exception
    {
        List<Setting> results = getRecords(
                COL_NAME + "=?",
                new String[]{name}
        );
        if (results.size()>1)
        {
            throw new RuntimeException("Name of the setting '" + name + "' is not unique!");
        }
        if (results.size()==1)
        {
            return results.get(0);
        } else {
            Setting setting = create();
            setting.setName(name);
            setting.setValue(defaultValue);
            update(setting);
            return setting;
        }
    }

    /**
     * Sets the setting with the given name to the specified value
     * @param name
     * @param value
     * @return true if it was updted successfully and false if it was not
     */
    private boolean set(@NonNull String name, @Nullable String value)
    {
        try {
            Setting setting = getSetting(name, value);
            setting.setValue(value);
            update(setting);
            return true;
        } catch (Exception e) {
            Logger.error("Failed to update setting '" + name + "'", e);
            return false;
        }
    }

    /**
     * This will attempt to load the setting.
     * @param name
     * @param defaultValue
     * @return the value of the setting with the name provided
     */
    private String get(@NonNull String name, @Nullable String defaultValue)
    {
        try {
            return getSetting(name, defaultValue).getValue();
        } catch (Exception e) {
            Logger.error("Failed to retrieve setting " + name, e);
            return defaultValue;
        }
    }

    /**
     * This is the prefered interface to ensure that all the globalPreferences match those given by the enumeration
     * @param settingId
     * @param defaultValue
     * @return
     */
    public final String get(@NonNull SettingId settingId, @Nullable String defaultValue)
    {
        return get(settingId.toString(), defaultValue);
    }

    /**
     * This is the prefered interface to ensure that all globalPreferences match those given by the enumeration
     * @param settingId
     * @param value
     * @return
     */
    public boolean set(@NonNull SettingId settingId, @Nullable String value)
    {
        return set(settingId.toString(), value);
    }

}


