package com.soupthatisthick.encounterbuilder.activity;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;

import com.soupthatisthick.encounterbuilder.adapters.lookup.SearchFilterAdapter;
import com.soupthatisthick.encounterbuilder.dao.lookup.SearchFilterDao;
import com.soupthatisthick.encounterbuilder.dao.master.DndMaster;
import com.soupthatisthick.encounterbuilder.model.lookup.SearchFilter;
import com.soupthatisthick.encounterbuilder.util.adapter.CustomListAdapter;
import com.soupthatisthick.util.Logger;
import com.soupthatisthick.util.activity.DaoEditListActivity;
import com.soupthatisthick.util.dao.DaoMaster;
import com.soupthatisthick.util.dao.WriteDao;

import soupthatisthick.encounterapp.R;

/**
 * Created by Owner on 5/23/2017.
 * Copyright of Stuart Marr Erskine, all rights reserved.
 */

public class SearchFiltersActivity extends DaoEditListActivity<Object, SearchFilter> {
    @Override
    protected DaoMaster createDaoMaster(Context context) throws Exception {
        return new DndMaster(context);
    }

    @Override
    protected void requestEditDetail(Long detailId, SearchFilter searchFilter, boolean deleteOnCancel) {
        Logger.info("TODO: Make invisible! Not applicable");
    }

    @Override
    protected int getClearListTitleStringId() {
        return R.string.escf_clear_title;
    }

    @Override
    protected int getClearListMessageStringId() {
        return R.string.escf_clear_message;
    }

    @Override
    protected int getDeleteDetailTitleStringId() {
        return R.string.escf_delete_title;
    }

    @Override
    protected int getDeleteDetailMessageStringId() {
        return R.string.escf_delete_message;
    }

    @Override
    protected WriteDao<SearchFilter> createDetailDao(DaoMaster daoMaster) throws Exception {
        return new SearchFilterDao(daoMaster);
    }

    @Override
    protected WriteDao<Object> createMastDao(DaoMaster daoMaster) throws Exception {
        return null;    // Not applicable
    }

    @Override
    protected CustomListAdapter<SearchFilter> createListAdapter(LayoutInflater layoutInflater) {
        return new SearchFilterAdapter(layoutInflater);
    }

    /**
     * This will save the information about the list of information.
     *
     * @param view
     */
    @Override
    protected void onClickSaveMastButton(View view) {
        Logger.info("TODO: Make invisible! Not applicable");
    }
}
