package com.soupthatisthick.encounterbuilder.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.soupthatisthick.encounterbuilder.InstrumentationTest;
import com.soupthatisthick.util.dao.DaoMaster;
import com.soupthatisthick.util.dao.WriteDao;

import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;
import static junit.framework.Assert.assertNotSame;
import static junit.framework.Assert.assertTrue;
import static junit.framework.Assert.fail;

/**
 * Created by Owner on 2/13/2017.
 * Copyright of Stuart Marr Erskine, all rights reserved.
 */
public class WriteDaoTest extends InstrumentationTest {

    private class Record
    {
        private Long id;
        private String name, notes;

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getNotes() {
            return notes;
        }

        public void setNotes(String notes) {
            this.notes = notes;
        }

        @Override
        public String toString()
        {
            return getClass().getSimpleName() + "(id=" + getId() + ", name=" + getName() + ", notes=" + getNotes() + ")";
        }
    }

    private class TheDao extends WriteDao<Record>
    {
        public static final String TBL_TEST = "TEST_TABLE";
        public static final String COL_NAME = "name";
        public static final String COL_NOTES = "notes";
        public static final String COL_ID = "id";

        public TheDao(DaoMaster daoMaster) throws IOException {
            super(daoMaster, TBL_TEST);
        }

        @Override
        public ContentValues getContentValues(Record record) {
            ContentValues content = new ContentValues();
            content.put(COL_NAME, record.getName());
            content.put(COL_NOTES, record.getNotes());
            content.put(COL_ID, record.getId());
            return content;
        }

        @Override
        protected Record createNewModel() {
            return new Record();
        }

        @Override
        public String getIdColumn() {
            return COL_ID;
        }

        @Override
        public Long getId(@NonNull Record record) {
            return record.getId();
        }

        @Override
        public void setId(@NonNull Record record, @Nullable Long id) {
            record.setId(id);
        }

        @Override
        public Record readRecord(Cursor cursor) {

            Record record = new Record();

            record.setId(cursor.getLong(cursor.getColumnIndex(COL_ID)));
            record.setName(cursor.getString(cursor.getColumnIndex(COL_NAME)));
            record.setNotes(cursor.getString(cursor.getColumnIndex(COL_NOTES)));

            return record;
        }

        @Override
        public Set<String> getSearchableColumns() {
            Set<String> theSet = new HashSet<>();
            theSet.add(COL_NAME);
            return theSet;

        }

        /**
         * This is a list of columns that we will sort the results by when we return a dataset.
         *
         * @return a List of columns. Sorting is first done by the first column then by the next column etc until all columns have been ordered.
         */
        @Override
        public List<String> getColumnSortingOrder() {
            ArrayList<String> sortOrder = new ArrayList<>();
            sortOrder.add(COL_NAME);
            return sortOrder;
        }
    }

    private class TheDaoMaster extends DaoMaster {

        public static final int VERSION = 1;
        public static final String RAW_PATH = "android.resource://com.soupthatisthick.encounterbuilder//raw//";
        public static final String DB_FILE = "testdb.db";

        public TheDaoMaster(Context context) throws IOException {
            super(context, RAW_PATH, DB_FILE, VERSION);
        }
    }

    DaoMaster daoMaster;
    TheDao theDao;


    @Override
    protected void onSetup() {

        if (daoMaster!=null)
        {
            daoMaster.close();
        }

        daoMaster = null;
        theDao = null;
        try {
            daoMaster = new TheDaoMaster(context);
            theDao = new TheDao(daoMaster);
            theDao.logContents();
        } catch (IOException e) {
            daoMaster = null;
            theDao = null;
        }

        assertNotNull(daoMaster);
        assertNotNull(theDao);
        theDao.clear();
        try {
            assertEquals(0, theDao.countRecords());
            theDao.logContents();
        } catch (IOException e) {
            fail(e.getMessage());
        }


    }

    @Test
    public void testCreate()
    {
        try {
            Record record = theDao.create();
            assertNotNull(record);
            assertNotNull(record.getId());
        } catch (Exception e) {
            fail(e.getMessage());
        }
    }

    @Test
    public void testCreateCreateDelete()
    {

        try {
            int firstCount = theDao.countRecords();
            Record record1 = theDao.create();
            assertNotNull(record1);
            theDao.logContents();

            int secondCount = theDao.countRecords();
            assertEquals(firstCount+1, secondCount);

            Record record2 = theDao.create();
            int thirdCount = theDao.countRecords();
            assertNotNull(record2);
            assertEquals(secondCount+1, thirdCount);

            assertNotSame(theDao.getId(record1), theDao.getId(record2));

            boolean deleted = theDao.delete(theDao.getId(record1));
            assertTrue(deleted);

            int fourthCount = theDao.countRecords();
            assertEquals(secondCount, fourthCount);

            List<Record> theList = theDao.getAllRecords();
            assertEquals(fourthCount, theList.size());

            // Verify we deleted the right record
            for(Record theRecord : theList)
            {
                assertNotSame(theDao.getId(record1), theDao.getId(theRecord));
            }

        } catch (IOException e) {
            fail(e.getMessage());
        }
    }
}
