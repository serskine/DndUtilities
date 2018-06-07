package com.soupthatisthick.encounterbuilder.activity.lookup;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import com.soupthatisthick.encounterbuilder.adapters.lookup.ExSpellAdapter;
import com.soupthatisthick.encounterbuilder.util.adapter.CustomToggleAdapter;
import com.soupthatisthick.util.activity.DaoEditActivity;
import com.soupthatisthick.util.activity.DaoEditToggleListActivity;
import com.soupthatisthick.util.dao.DaoMaster;
import com.soupthatisthick.encounterbuilder.dao.lookup.SpellDao;
import com.soupthatisthick.util.dao.WriteDao;
import com.soupthatisthick.encounterbuilder.dao.master.DndMaster;
import com.soupthatisthick.encounterbuilder.model.lookup.Spell;
import com.soupthatisthick.util.Logger;

import soupthatisthick.encounterapp.R;

/**
 * Created by Owner on 2/28/2017.
 * Copyright of Stuart Marr Erskine, all rights reserved.
 */

public class EditSpellBookActivity extends DaoEditToggleListActivity<Object, Spell> {

    @Override
    protected CustomToggleAdapter<Spell> createToggleListAdapter(LayoutInflater layoutInflater) {
        return new ExSpellAdapter(layoutInflater);
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
    protected void requestEditDetail(Long detailId, Spell spell, boolean deleteOnCancel) {
        Logger.info("request to edit spell " + spell.toString() + " with id " + detailId);

        Intent intent = new Intent(this, EditSpellActivity.class);
        intent.putExtra(DaoEditActivity.KEY_DELETE_ON_CANCEL, deleteOnCancel);
        intent.putExtra(DaoEditActivity.KEY_MODEL_ID, detailId);
        startActivity(intent);
    }

    @Override
    protected int getClearListTitleStringId() {
        return R.string.esp_list_delete_title;
    }

    @Override
    protected int getClearListMessageStringId() {
        return R.string.esp_list_clear_message;
    }

    @Override
    protected int getDeleteDetailTitleStringId() {
        return R.string.esp_list_delete_title;
    }

    @Override
    protected int getDeleteDetailMessageStringId() {
        return R.string.esp_list_delete_message;
    }

    @Override
    protected WriteDao<Spell> createDetailDao(DaoMaster daoMaster) throws Exception {
        return new SpellDao(daoMaster);
    }

    @Override
    protected WriteDao<Object> createMastDao(DaoMaster daoMaster) throws Exception {
        return null;
    }

}
