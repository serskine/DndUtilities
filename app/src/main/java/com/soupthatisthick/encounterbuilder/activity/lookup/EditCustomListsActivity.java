package com.soupthatisthick.encounterbuilder.activity.lookup;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;

import com.soupthatisthick.encounterbuilder.adapters.lookup.ExItemListAdapter;
import com.soupthatisthick.encounterbuilder.dao.lookup.ItemListDao;
import com.soupthatisthick.encounterbuilder.dao.master.DndMaster;
import com.soupthatisthick.encounterbuilder.model.lookup.ItemList;
import com.soupthatisthick.encounterbuilder.util.adapter.CustomToggleAdapter;
import com.soupthatisthick.util.Logger;
import com.soupthatisthick.util.activity.DaoEditToggleListActivity;
import com.soupthatisthick.util.dao.DaoMaster;
import com.soupthatisthick.util.dao.WriteDao;

import soupthatisthick.encounterapp.R;

/**
 * Created by Owner on 4/21/2017.
 * Copyright of Stuart Marr Erskine, all rights reserved.
 */

public class EditCustomListsActivity extends DaoEditToggleListActivity<Object, ItemList> {
    @Override
    protected DaoMaster createDaoMaster(Context context) throws Exception {
        return new DndMaster(context);
    }

    @Override
    protected void requestEditDetail(Long detailId, ItemList itemList, boolean deleteOnCancel) {
        Logger.toast(getApplication(), R.string.not_supported);
    }

    @Override
    protected int getClearListTitleStringId() {
        return R.string.ecl_clear_list_title;
    }

    @Override
    protected int getClearListMessageStringId() {
        return R.string.ecl_clear_list_message;
    }

    @Override
    protected int getDeleteDetailTitleStringId() {
        return R.string.ecl_delete_item_title;
    }

    @Override
    protected int getDeleteDetailMessageStringId() {
        return R.string.ecl_delete_item_message;
    }

    @Override
    protected WriteDao<ItemList> createWriteDao(DaoMaster db) throws Exception {
        return new ItemListDao(db);
    }

    @Override
    protected CustomToggleAdapter<ItemList> createToggleListAdapter(LayoutInflater layoutInflater) {
        return new ExItemListAdapter(layoutInflater);
    }

    /**
     * This will save the information about the list of information.
     *
     * @param view
     */
    @Override
    protected void onClickSaveMastButton(View view) {

    }
}
