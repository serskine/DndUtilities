package com.soupthatisthick.encounterbuilder.activity.lookup;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.soupthatisthick.encounterbuilder.adapters.lookup.CompendiumAdapter;
import com.soupthatisthick.encounterbuilder.adapters.lookup.ExItemListAdapter;
import com.soupthatisthick.encounterbuilder.dao.lookup.EntityListDao;
import com.soupthatisthick.encounterbuilder.dao.master.DndMaster;
import com.soupthatisthick.encounterbuilder.model.lookup.ItemList;
import com.soupthatisthick.encounterbuilder.util.adapter.CustomToggleAdapter;
import com.soupthatisthick.util.Logger;
import com.soupthatisthick.util.activity.DaoEditActivity;
import com.soupthatisthick.util.activity.DaoEditListActivity;
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
        Logger.warning("Editing lists is not yet implemented!");
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
    protected WriteDao createWriteDao(DaoMaster daoMaster) throws Exception {
        return new EntityListDao(daoMaster);
    }

    @Override
    protected CustomToggleAdapter<ItemList> createToggleListAdapter(LayoutInflater layoutInflater) {
        return new ExItemListAdapter(layoutInflater);
    }

    @Override
    protected void onClickSaveMastButton(View view) {
        Logger.debug("Saving the mast of lists is not yet implemented");
    }
}
