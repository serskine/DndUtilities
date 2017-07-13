package com.soupthatisthick.encounterbuilder.activity.lookup;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;

import com.soupthatisthick.encounterbuilder.adapters.lookup.ExFeatAdapter;
import com.soupthatisthick.encounterbuilder.dao.lookup.FeatDao;
import com.soupthatisthick.encounterbuilder.dao.master.DndMaster;
import com.soupthatisthick.encounterbuilder.model.lookup.Feat;
import com.soupthatisthick.encounterbuilder.util.adapter.CustomToggleAdapter;
import com.soupthatisthick.util.Logger;
import com.soupthatisthick.util.activity.DaoEditActivity;
import com.soupthatisthick.util.activity.DaoEditToggleListActivity;
import com.soupthatisthick.util.dao.DaoMaster;
import com.soupthatisthick.util.dao.WriteDao;

import soupthatisthick.encounterapp.R;

/**
 * Created by Owner on 5/5/2017.
 * Copyright of Stuart Marr Erskine, all rights reserved.
 */

public class EditFeatListActivity extends DaoEditToggleListActivity<Object, Feat> {
    @Override
    protected DaoMaster createDaoMaster(Context context) throws Exception {
        return new DndMaster(context);
    }

    @Override
    protected void requestEditDetail(Long detailId, Feat feat, boolean deleteOnCancel) {
        Logger.info("request to edit feat " + feat.toString() + " with id " + detailId);

        Intent intent = new Intent(this, EditFeatActivity.class);
        intent.putExtra(DaoEditActivity.KEY_DELETE_ON_CANCEL, deleteOnCancel);
        intent.putExtra(DaoEditActivity.KEY_MODEL_ID, detailId);
        startActivity(intent);
    }

    @Override
    protected int getClearListTitleStringId() {
        return R.string.ef_list_clear_title;
    }

    @Override
    protected int getClearListMessageStringId() {
        return R.string.ef_list_clear_message;
    }

    @Override
    protected int getDeleteDetailTitleStringId() {
        return R.string.ef_list_delete_title;
    }

    @Override
    protected int getDeleteDetailMessageStringId() {
        return R.string.ef_list_delete_message;
    }

    @Override
    protected WriteDao<Feat> createWriteDao(DaoMaster db) throws Exception {
        return new FeatDao(db);
    }

    @Override
    protected CustomToggleAdapter<Feat> createToggleListAdapter(LayoutInflater layoutInflater) {
        return new ExFeatAdapter(layoutInflater);
    }

    /**
     * This will save the information about the list of information.
     *
     * @param view
     */
    @Override
    protected void onClickSaveMastButton(View view) {
        // Do nothing. There is no need for a save button
    }
}
