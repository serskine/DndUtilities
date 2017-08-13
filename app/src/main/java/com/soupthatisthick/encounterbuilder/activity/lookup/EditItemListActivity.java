package com.soupthatisthick.encounterbuilder.activity.lookup;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;

import com.soupthatisthick.encounterbuilder.adapters.lookup.ExItemAdapter;
import com.soupthatisthick.encounterbuilder.dao.lookup.ItemDao;
import com.soupthatisthick.encounterbuilder.dao.lookup.ItemListDao;
import com.soupthatisthick.encounterbuilder.dao.master.DndMaster;
import com.soupthatisthick.encounterbuilder.model.lookup.Item;
import com.soupthatisthick.encounterbuilder.model.lookup.ItemList;
import com.soupthatisthick.encounterbuilder.util.adapter.CustomListAdapter;
import com.soupthatisthick.util.Logger;
import com.soupthatisthick.util.activity.DaoEditListActivity;
import com.soupthatisthick.util.dao.DaoMaster;
import com.soupthatisthick.util.dao.WriteDao;

import soupthatisthick.encounterapp.R;

/**
 * Created by Owner on 7/30/2017.
 * Copyright of Stuart Marr Erskine, all rights reserved.
 */

public class EditItemListActivity extends DaoEditListActivity<ItemList, Item> {

    @Override
    protected DaoMaster createDaoMaster(Context context) throws Exception {
        return new DndMaster(context);
    }

    @Override
    protected void requestEditDetail(Long detailId, Item item, boolean deleteOnCancel) {
        Logger.debug("REQUEST EDIT DETAIL(" + detailId + ", " + item + ", " + deleteOnCancel + ")");
    }

    @Override
    protected int getClearListTitleStringId() {
        return R.string.eil_clear_list_title;
    }

    @Override
    protected int getClearListMessageStringId() {
        return R.string.eil_clear_list_message;
    }

    @Override
    protected int getDeleteDetailTitleStringId() {
        return R.string.eil_delete_item_title;
    }

    @Override
    protected int getDeleteDetailMessageStringId() {
        return R.string.eil_delete_message;
    }

    @Override
    protected WriteDao<Item> createDetailDao(DaoMaster daoMaster) throws Exception {
        return new ItemDao(daoMaster);
    }

    @Override
    protected WriteDao<ItemList> createMastDao(DaoMaster daoMaster) throws Exception {
        return new ItemListDao(daoMaster);
    }

    @Override
    protected CustomListAdapter<Item> createListAdapter(LayoutInflater layoutInflater) {
        return new ExItemAdapter(layoutInflater, theDaoMaster);
    }

    /**
     * This will save the information about the list of information.
     *
     * @param view
     */
    @Override
    protected void onClickSaveMastButton(View view) {
        Logger.debug("TODO: SAVE THE NEW LIST");
    }
}