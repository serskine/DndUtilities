package com.soupthatisthick.encounterbuilder.dao.lookup;

import android.content.ContentValues;
import android.database.Cursor;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.soupthatisthick.encounterbuilder.model.lookup.Note;
import com.soupthatisthick.util.dao.DaoMaster;
import com.soupthatisthick.util.dao.WriteDao;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by Owner on 4/13/2017.
 * Copyright of Stuart Marr Erskine, all rights reserved.
 */

public class NotesDao extends WriteDao<Note> {

    public static final String TBL_NOTES = "NOTES";
    public static final String COL_ID = "id";
    public static final String COL_TITLE = "title";
    public static final String COL_CONTENT = "content";

    public NotesDao(DaoMaster daoMaster) throws IOException {
        super(daoMaster, TBL_NOTES);
    }

    @Override
    public Note readRecord(Cursor cursor) {
        Note note = new Note();
        note.setId(cursor.getLong(cursor.getColumnIndex(COL_ID)));
        note.setTitle(cursor.getString(cursor.getColumnIndex(COL_TITLE)));
        note.setContent(cursor.getString(cursor.getColumnIndex(COL_CONTENT)));
        return note;
    }

    /**
     * This method will return all the columns that may be searched for matches in a text search.
     *
     * @return a Set of columns elidgible for searching with a like query
     */
    @Override
    public Set<String> getSearchableColumns() {
        HashSet<String> columns = new HashSet<>();
        columns.add(COL_TITLE);
        return columns;
    }

    /**
     * This is a list of columns that we will sort the results by when we return a dataset.
     *
     * @return a List of columns. Sorting is first done by the first column then by the next column etc until all columns have been ordered.
     */
    @Override
    public List<String> getColumnSortingOrder() {
        ArrayList<String> list = new ArrayList<>();
        list.add(COL_TITLE);
        return list;
    }

    /**
     * This will build a mapping of column names to values from the specified record
     * so that they may be inserted.
     *
     * @param note
     */
    @Override
    public ContentValues getContentValues(Note note) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_ID, note.getId());
        contentValues.put(COL_TITLE, note.getTitle());
        contentValues.put(COL_CONTENT, note.getContent());
        return contentValues;
    }

    /**
     * This will create a new model and return it
     *
     * @return a new Record to update
     */
    @Override
    protected Note createNewModel() {
        return new Note();
    }

    @Override
    public String getIdColumn() {
        return COL_ID;
    }

    @Override
    public Long getId(@NonNull Note note) {
        return note.getId();
    }

    @Override
    public void setId(@NonNull Note note, @Nullable Long id) {
        note.setId(id);
    }
}
