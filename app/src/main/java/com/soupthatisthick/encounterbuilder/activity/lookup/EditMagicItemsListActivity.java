package com.soupthatisthick.encounterbuilder.activity.lookup;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import com.soupthatisthick.encounterbuilder.adapters.lookup.ExMagicItemAdapter;
import com.soupthatisthick.encounterbuilder.dao.lookup.MagicItemDao;
import com.soupthatisthick.encounterbuilder.util.adapter.CustomToggleAdapter;
import com.soupthatisthick.util.activity.DaoEditActivity;
import com.soupthatisthick.util.activity.DaoEditToggleListActivity;
import com.soupthatisthick.util.dao.WriteDao;
import com.soupthatisthick.encounterbuilder.dao.master.DndMaster;
import com.soupthatisthick.encounterbuilder.model.lookup.MagicItem;
import com.soupthatisthick.util.dao.DaoMaster;
import com.soupthatisthick.util.Logger;

import soupthatisthick.encounterapp.R;

public class EditMagicItemsListActivity extends DaoEditToggleListActivity<Object, MagicItem> {


    @Override
    protected CustomToggleAdapter<MagicItem> createToggleListAdapter(LayoutInflater layoutInflater) {
        return new ExMagicItemAdapter(layoutInflater);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        saveMastButton.setVisibility(View.GONE);
    }

    @Override
    protected WriteDao<MagicItem> createWriteDao(DaoMaster db) throws Exception {
        return new MagicItemDao(db);
    }

    @Override
    protected void requestEditDetail(Long detailId, MagicItem magicItem, boolean deleteOnCancel) {
        Logger.info("request to edit magic item " + magicItem.toString() + " with id " + detailId);

        Intent intent = new Intent(this, EditMagicItemActivity.class);
        intent.putExtra(DaoEditActivity.KEY_DELETE_ON_CANCEL, deleteOnCancel);
        intent.putExtra(DaoEditActivity.KEY_MODEL_ID, detailId);
        startActivity(intent);
    }

    @Override
    protected int getClearListTitleStringId() {
        return R.string.emi_list_clear_title;
    }

    @Override
    protected int getClearListMessageStringId() {
        return R.string.emi_list_clear_message;
    }

    @Override
    protected int getDeleteDetailTitleStringId() {
        return R.string.emi_list_delete_title;
    }

    @Override
    protected int getDeleteDetailMessageStringId() {
        return R.string.emi_list_delete_message;
    }


    @Override
    protected DaoMaster createDaoMaster(Context context) throws Exception {
        return new DndMaster(getBaseContext());
    }


    @Override
    protected void onClickSaveMastButton(View view) {

    }
}
