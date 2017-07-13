package com.soupthatisthick.encounterbuilder.dao.lookup;

import android.content.ContentValues;
import android.database.Cursor;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.soupthatisthick.encounterbuilder.model.lookup.Contact;
import com.soupthatisthick.util.dao.DaoMaster;
import com.soupthatisthick.util.dao.WriteDao;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by Owner on 5/1/2017.
 * Copyright of Stuart Marr Erskine, all rights reserved.
 */

public class ContactDao extends WriteDao<Contact> {

    public static final String TBL_CONTACTS = "CONTACTS";
    public static final String COL_ID = "id";
    public static final String COL_NAME = "name";
    public static final String COL_DCI = "dci";

    public ContactDao(DaoMaster daoMaster) throws IOException {
        super(daoMaster, TBL_CONTACTS);
    }

    @Override
    public Contact readRecord(Cursor cursor) {
        Contact contact = new Contact();
        contact.setId(cursor.getLong(cursor.getColumnIndex(COL_ID)));
        contact.setDci(cursor.getString(cursor.getColumnIndex(COL_DCI)));
        contact.setName(cursor.getString(cursor.getColumnIndex(COL_NAME)));
        return contact;
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
        columns.add(COL_DCI);
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
        columns.add(COL_DCI);
        return columns;
    }

    /**
     * This will build a mapping of column names to values from the specified record
     * so that they may be inserted.
     *
     * @param contact
     */
    @Override
    public ContentValues getContentValues(Contact contact) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_ID, contact.getId());
        contentValues.put(COL_NAME, contact.getName());
        contentValues.put(COL_DCI, contact.getDci());
        return contentValues;
    }

    /**
     * This will create a new model and return it
     *
     * @return a new Record to update
     */
    @Override
    protected Contact createNewModel() {
        return new Contact();
    }

    @Override
    public String getIdColumn() {
        return COL_ID;
    }

    @Override
    public Long getId(@NonNull Contact contact) {
        return contact.getId();
    }

    @Override
    public void setId(@NonNull Contact contact, @Nullable Long id) {
        contact.setId(id);
    }
}
