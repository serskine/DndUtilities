package com.soupthatisthick.encounterbuilder.activity.lookup;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;

import com.soupthatisthick.encounterbuilder.adapters.lookup.ExCustomMonsterAdapter;
import com.soupthatisthick.encounterbuilder.dao.lookup.CustomMonsterDao;
import com.soupthatisthick.encounterbuilder.dao.master.DndMaster;
import com.soupthatisthick.encounterbuilder.model.lookup.CustomMonster;
import com.soupthatisthick.util.Logger;
import com.soupthatisthick.encounterbuilder.util.adapter.CustomToggleAdapter;
import com.soupthatisthick.util.activity.DaoEditActivity;
import com.soupthatisthick.util.activity.DaoEditToggleListActivity;
import com.soupthatisthick.util.dao.DaoMaster;
import com.soupthatisthick.util.dao.WriteDao;

import soupthatisthick.encounterapp.R;

public class EditMonsterManualActivity extends DaoEditToggleListActivity<Object, CustomMonster> {

    @Override
    protected DaoMaster createDaoMaster(Context context) throws Exception {
        return new DndMaster(context);
    }

    @Override
    protected WriteDao<CustomMonster> createWriteDao(DaoMaster db) throws Exception {
        return new CustomMonsterDao(db);
    }

    @Override
    protected void requestEditDetail(Long detailId, CustomMonster customMonster, boolean deleteOnCancel) {
        Intent intent = new Intent(this, EditCustomMonsterActivity.class);
        Logger.debug("Requesting editing of customMonster " + customMonster.toString());
        intent.putExtra(DaoEditActivity.KEY_MODEL_ID, detailId);
        startActivity(intent);
    }

    @Override
    protected int getClearListTitleStringId() {
        return R.string.em_list_clear_title;
    }

    @Override
    protected int getClearListMessageStringId() {
        return R.string.em_list_clear_message;
    }

    @Override
    protected int getDeleteDetailTitleStringId() {
        return R.string.em_list_delete_title;
    }

    @Override
    protected int getDeleteDetailMessageStringId() {
        return R.string.em_list_delete_message;
    }


    @Override
    protected void onClickSaveMastButton(View view) {
        Logger.toast(getApplication(), R.string.test_to_be_implemented);
    }

    @Override
    protected CustomToggleAdapter<CustomMonster> createToggleListAdapter(LayoutInflater layoutInflater) {
        return new ExCustomMonsterAdapter(layoutInflater);
    }
}
