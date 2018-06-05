package com.soupthatisthick.encounterbuilder.activity.lookup;

import android.content.Context;
import android.view.View;
import android.widget.EditText;

import com.soupthatisthick.encounterbuilder.dao.lookup.EntityListDao;
import com.soupthatisthick.encounterbuilder.dao.master.DndMaster;
import com.soupthatisthick.encounterbuilder.model.lookup.ItemList;
import com.soupthatisthick.util.Logger;
import com.soupthatisthick.util.activity.DaoEditActivity;
import com.soupthatisthick.util.dao.DaoMaster;
import com.soupthatisthick.util.dao.WriteDao;

import soupthatisthick.encounterapp.R;

/**
 * Created by Owner on 7/30/2017.
 * Copyright of Stuart Marr Erskine, all rights reserved.
 */

public class EditItemListActivity extends DaoEditActivity<ItemList> {

    EditText nameEdit;

    @Override
    protected int getDeleteTitleStringId() {
        return R.string.eil_delete_title;
    }

    @Override
    protected int getDeleteMessageStringId() {
        return R.string.eil_delete_message;
    }

    @Override
    protected DaoMaster createDaoMaster(Context context) throws Exception {
        return new DndMaster(context);
    }

    @Override
    protected WriteDao createWriteDao(DaoMaster daoMaster) throws Exception {
        return new EntityListDao(daoMaster);
    }

    @Override
    protected void initModelWithoutUi() {
        nameEdit = (EditText) findViewById(R.id.eil_name_edit);
        model = new ItemList();
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
            Logger.info("name edit(" + nameEdit.getText() + ") => model.getName()");
            model.setName(nameEdit.getText().toString());
            Logger.info("name edit(" + model.getName() + ")");
        } catch (Exception e) {
            Logger.error("Failed to update the model from the ui! \n" + e.getMessage(), e);
        }
    }

    @Override
    protected void updateUiFromModel() {
        try {
            Logger.info("name edit() <= model.getName(" + model.getName() + ")");
            nameEdit.setText(model.getName());
            Logger.info("name edit(" + nameEdit.getText() + ")");
        } catch (Exception e) {
            Logger.error("Failed to update the ui from the model. \n" + e.getMessage(), e);
        }
    }

    @Override
    protected View findSaveButton() {
        return findViewById(R.id.eil_save_button);
    }

    @Override
    protected View findDeleteButton() {
        return findViewById(R.id.eil_delete_button);
    }

    @Override
    protected View findCancelButton() {
        return findViewById(R.id.eil_cancel_button);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_edit_item_list;
    }
}