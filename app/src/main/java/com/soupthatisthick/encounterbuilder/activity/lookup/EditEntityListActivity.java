package com.soupthatisthick.encounterbuilder.activity.lookup;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ExpandableListView;

import com.soupthatisthick.encounterbuilder.adapters.lookup.EntityAdapter;
import com.soupthatisthick.encounterbuilder.dao.helper.CompendiumResource;
import com.soupthatisthick.encounterbuilder.dao.lookup.EntityDao;
import com.soupthatisthick.encounterbuilder.dao.lookup.EntityListDao;
import com.soupthatisthick.encounterbuilder.dao.master.DndMaster;
import com.soupthatisthick.encounterbuilder.model.lookup.Entity;
import com.soupthatisthick.encounterbuilder.model.lookup.EntityList;
import com.soupthatisthick.encounterbuilder.util.adapter.CustomListAdapter;
import com.soupthatisthick.encounterbuilder.util.adapter.CustomToggleAdapter;
import com.soupthatisthick.encounterbuilder.util.listeners.UiWatcher;
import com.soupthatisthick.encounterbuilder.util.sort.Category;
import com.soupthatisthick.util.Logger;
import com.soupthatisthick.util.activity.DaoEditListActivity;
import com.soupthatisthick.util.activity.DaoEditToggleListActivity;
import com.soupthatisthick.util.dao.DaoMaster;
import com.soupthatisthick.util.dao.WriteDao;

import java.util.List;

import soupthatisthick.encounterapp.R;

/**
 * Created by Owner on 7/30/2017.
 * Copyright of Stuart Marr Erskine, all rights reserved.
 */

public class EditEntityListActivity extends DaoEditToggleListActivity<EntityList, Entity> {

    EditText nameEdit;

    public static final String KEY_ENTITY_ID = "KEY_ENTITY_ID";


    @Override
    public int getLayoutId() {
        return R.layout.activity_edit_item_list;
    }

    @Override
    protected DaoMaster createDaoMaster(Context context) throws Exception {
        return new DndMaster(context);
    }

    @Override
    protected void requestEditDetail(Long detailId, Entity entity, boolean deleteOnCancel) {
        Logger.warning("Editing entity details is not allowed. Only addition and removal are.");
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
        return R.string.eil_delete_item_detail;
    }

    @Override
    protected WriteDao<Entity> createDetailDao(DaoMaster daoMaster) throws Exception {
        return new EntityDao(daoMaster);
    }

    @Override
    protected WriteDao<EntityList> createMastDao(DaoMaster daoMaster) throws Exception {
        return new EntityListDao(daoMaster);
    }

    @Override
    protected CustomToggleAdapter<Entity> createToggleListAdapter(LayoutInflater layoutInflater) {
        return new EntityAdapter(layoutInflater, new CompendiumResource(getApplicationContext()));
    }

    @Override
    protected void initModelWithoutUi() {
        setMast(new EntityList());
    }

    @Override
    protected void listenToUi() {
        uiWatcher.listenTo(nameEdit);
    }

    @Override
    protected void ignoreUi() {
        uiWatcher.ignore(nameEdit);
    }

    @Override
    protected void updateModelFromUi() {
        try {
            getMast().setName(nameEdit.getText().toString());
            getMastDao().update(getMast());
            EntityDao entityDao = (EntityDao) getDetailDao();
            List<Entity> children = entityDao.getChildrenOf(getMast().getId());

            // Update the metadata on all children of this list accordingly.
            for (Entity detailEntity : children) {
                String metadata = entityDao.getDesirableMetadata(detailEntity);
                metadata += ((metadata.length() < 1) ? "" : " ") + getMastDao().getDesirableMetadata(getMast());
                detailEntity.setMetadata(metadata);
                entityDao.update(detailEntity);
            }
        } catch (Exception e) {
            Logger.error(e.getMessage(), e);
        }
    }

    @Override
    public void initUiWithoutModel() {
        super.initUiWithoutModel();
        nameEdit = findViewById(R.id.eil_name_edit);
    }

    @Override
    protected void updateUiFromModel() {
        nameEdit.setText(getMast().getName());
    }

    @Override
    protected final void loadModelFromBackEndStore() {
         try {
              Long id = getIntent().getLongExtra(KEY_MODEL_ID, 0);
              if (id<1) {
                  throw new RuntimeException("The " + KEY_MODEL_ID + " property was not specified so we don't know which list to edit.");
              }
              setMast(getMastDao().load(id));
         } catch (Exception e) {
             Logger.error(e.getMessage(), e);
             finish();
         }
    }

}