package com.soupthatisthick.encounterbuilder.activity.lookup;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;

import com.soupthatisthick.encounterbuilder.adapters.lookup.ExItemListAdapter;
import com.soupthatisthick.encounterbuilder.dao.lookup.EntityListDao;
import com.soupthatisthick.encounterbuilder.dao.master.DndMaster;
import com.soupthatisthick.encounterbuilder.model.lookup.EntityList;
import com.soupthatisthick.encounterbuilder.util.adapter.CustomToggleAdapter;
import com.soupthatisthick.util.Logger;
import com.soupthatisthick.util.activity.DaoEditActivity;
import com.soupthatisthick.util.activity.DaoEditToggleListActivity;
import com.soupthatisthick.util.dao.DaoMaster;
import com.soupthatisthick.util.dao.WriteDao;
import com.soupthatisthick.util.json.JsonUtil;

import soupthatisthick.encounterapp.R;

/**
 * Created by Owner on 4/21/2017.
 * Copyright of Stuart Marr Erskine, all rights reserved.
 */
public class EditCustomListsActivity extends DaoEditToggleListActivity<Object, EntityList> {

    @Override
    protected DaoMaster createDaoMaster(Context context) throws Exception {
        return new DndMaster(context);
    }

    @Override
    protected void requestEditDetail(Long detailId, EntityList entityList, boolean deleteOnCancel) {
        Logger.debug("Request to edit custom list (" + detailId + ", " + entityList.toString() + ", " + deleteOnCancel + ")");
        Logger.debug(JsonUtil.toJson(entityList, true));

        Intent intent = new Intent(this, EditEntityListActivity.class);
        intent.putExtra(DaoEditActivity.KEY_DELETE_ON_CANCEL, deleteOnCancel);
        intent.putExtra(DaoEditActivity.KEY_MODEL_ID, detailId);
        startActivity(intent);
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
    protected WriteDao<EntityList> createDetailDao(DaoMaster daoMaster) throws Exception {
        return new EntityListDao(daoMaster);
    }

    @Override
    protected WriteDao<Object> createMastDao(DaoMaster daoMaster) throws Exception {
        return null;
    }

    @Override
    protected CustomToggleAdapter<EntityList> createToggleListAdapter(LayoutInflater layoutInflater) {
        return new ExItemListAdapter(layoutInflater);
    }

    @Override
    protected void initUiWithoutModel() {
        super.initUiWithoutModel();
        saveMastButton.setVisibility(View.GONE);
    }

    @Override
    protected void initModelWithoutUi() {
        // Do nothing. We don't use the model
    }

    @Override
    protected void listenToUi() {
        // Do nothing. We don't use the model
    }

    @Override
    protected void ignoreUi() {
        // Do nothing. We dont use the model
    }

    @Override
    protected void updateModelFromUi() {
        // Do nothing. we don't use the mast
    }

    @Override
    protected void updateUiFromModel() {
        // Do nothing, we don't use the mast
    }

    @Override
    protected void loadModelFromBackEndStore() {
        // Do nothing. We don't use the mast
    }
}
