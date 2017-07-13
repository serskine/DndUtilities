package com.soupthatisthick.encounterbuilder.activity.lookup;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import com.soupthatisthick.encounterbuilder.adapters.lookup.ExNotesAdapter;
import com.soupthatisthick.encounterbuilder.dao.lookup.NotesDao;
import com.soupthatisthick.encounterbuilder.dao.master.DndMaster;
import com.soupthatisthick.encounterbuilder.model.lookup.Note;
import com.soupthatisthick.util.Logger;
import com.soupthatisthick.encounterbuilder.util.adapter.CustomToggleAdapter;
import com.soupthatisthick.util.activity.DaoEditActivity;
import com.soupthatisthick.util.activity.DaoEditToggleListActivity;
import com.soupthatisthick.util.dao.DaoMaster;
import com.soupthatisthick.util.dao.WriteDao;

import soupthatisthick.encounterapp.R;

/**
 * Created by Owner on 4/13/2017.
 * Copyright of Stuart Marr Erskine, all rights reserved.
 */

public class ExEditNotesActivity extends DaoEditToggleListActivity<Object, Note> {

    @Override
    protected CustomToggleAdapter<Note> createToggleListAdapter(LayoutInflater layoutInflater) {
        return new ExNotesAdapter(layoutInflater);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        saveMastButton.setVisibility(View.GONE);
    }

    @Override
    protected DaoMaster createDaoMaster(Context context) throws Exception {
        return new DndMaster(context);
    }

    @Override
    protected void requestEditDetail(Long detailId, Note note, boolean deleteOnCancel) {
        Logger.info("request to edit note " + note.toString() + " with id " + detailId);

        Intent intent = new Intent(this, EditNoteActivity.class);
        intent.putExtra(DaoEditActivity.KEY_DELETE_ON_CANCEL, deleteOnCancel);
        intent.putExtra(DaoEditActivity.KEY_MODEL_ID, detailId);
        startActivity(intent);
    }

    @Override
    protected int getClearListTitleStringId() {
        return R.string.esn_list_delete_title;
    }

    @Override
    protected int getClearListMessageStringId() {
        return R.string.esn_list_clear_message;
    }

    @Override
    protected int getDeleteDetailTitleStringId() {
        return R.string.esn_list_delete_title;
    }

    @Override
    protected int getDeleteDetailMessageStringId() {
        return R.string.esn_list_delete_message;
    }

    @Override
    protected WriteDao<Note> createWriteDao(DaoMaster db) throws Exception {
        return new NotesDao(db);
    }

    @Override
    protected void onClickSaveMastButton(View view) {
        // Do nothing. There is no mast to save
    }

}
