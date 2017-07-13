package com.soupthatisthick.encounterbuilder.activity.lookup;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import com.soupthatisthick.encounterbuilder.adapters.lookup.ExContactsAdapter;
import com.soupthatisthick.encounterbuilder.dao.lookup.ContactDao;
import com.soupthatisthick.encounterbuilder.dao.master.LogsheetMaster;
import com.soupthatisthick.encounterbuilder.model.lookup.Contact;
import com.soupthatisthick.encounterbuilder.util.adapter.CustomToggleAdapter;
import com.soupthatisthick.util.Logger;
import com.soupthatisthick.util.activity.DaoEditActivity;
import com.soupthatisthick.util.activity.DaoEditToggleListActivity;
import com.soupthatisthick.util.dao.DaoMaster;
import com.soupthatisthick.util.dao.WriteDao;

import soupthatisthick.encounterapp.R;

/**
 * Created by Owner on 5/1/2017.
 * Copyright of Stuart Marr Erskine, all rights reserved.
 */

public class ExEditContactsActivity extends DaoEditToggleListActivity<Object, Contact> {

    @Override
    protected CustomToggleAdapter<Contact> createToggleListAdapter(LayoutInflater layoutInflater) {
        return new ExContactsAdapter(layoutInflater);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        saveMastButton.setVisibility(View.GONE);
    }

    @Override
    protected DaoMaster createDaoMaster(Context context) throws Exception {
        return new LogsheetMaster(context);
    }

    @Override
    protected void requestEditDetail(Long detailId, Contact contact, boolean deleteOnCancel) {
        Logger.info("request to edit contact " + contact.toString() + " with id " + detailId);

        Intent intent = new Intent(this, EditContactActivity.class);
        intent.putExtra(DaoEditActivity.KEY_DELETE_ON_CANCEL, deleteOnCancel);
        intent.putExtra(DaoEditActivity.KEY_MODEL_ID, detailId);
        startActivity(intent);
    }

    @Override
    protected int getClearListTitleStringId() {
        return R.string.ec_list_delete_title;
    }

    @Override
    protected int getClearListMessageStringId() {
        return R.string.ec_list_clear_message;
    }

    @Override
    protected int getDeleteDetailTitleStringId() {
        return R.string.ec_list_delete_title;
    }

    @Override
    protected int getDeleteDetailMessageStringId() {
        return R.string.ec_list_delete_message;
    }

    @Override
    protected WriteDao<Contact> createWriteDao(DaoMaster db) throws Exception {
        return new ContactDao(db);
    }

    @Override
    protected void onClickSaveMastButton(View view) {
        // Do nothing. There is no mast to save
    }

}
