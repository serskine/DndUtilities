package com.soupthatisthick.encounterbuilder.activity.lookup;

import android.content.Context;
import android.view.View;
import android.widget.EditText;

import com.soupthatisthick.encounterbuilder.dao.lookup.FeatDao;
import com.soupthatisthick.encounterbuilder.dao.master.DndMaster;
import com.soupthatisthick.encounterbuilder.model.lookup.Feat;
import com.soupthatisthick.util.activity.DaoEditActivity;
import com.soupthatisthick.util.dao.DaoMaster;
import com.soupthatisthick.util.dao.WriteDao;

import soupthatisthick.encounterapp.R;

/**
 * Created by Owner on 5/5/2017.
 * Copyright of Stuart Marr Erskine, all rights reserved.
 */

public class EditFeatActivity extends DaoEditActivity<Feat> {
    private EditText theName, thePrerequisite, theDescription;

    @Override
    protected void initModelWithoutUi() {
        theName = (EditText) findViewById(R.id.ef_name);
        thePrerequisite = (EditText) findViewById(R.id.ef_prerequisite);
        theDescription = (EditText) findViewById(R.id.ef_description);
    }

    @Override
    protected void listenToUi() {
        uiWatcher.listenTo(theName);
        uiWatcher.listenTo(thePrerequisite);
        uiWatcher.listenTo(theDescription);
    }

    @Override
    protected void ignoreUi() {
        uiWatcher.ignore(theName);
        uiWatcher.ignore(thePrerequisite);
        uiWatcher.ignore(theDescription);
    }

    @Override
    protected void updateModelFromUi() {
        model.setName(theName.getText().toString());
        model.setPrerequisite(thePrerequisite.getText().toString());
        model.setDescription(theDescription.getText().toString());
    }

    @Override
    protected void updateUiFromModel() {
        theName.setText(model.getName());
        thePrerequisite.setText(model.getPrerequisite());
        theDescription.setText(model.getDescription());
    }

    @Override
    protected View findSaveButton() {
        return findViewById(R.id.ef_save_button);
    }

    @Override
    protected View findDeleteButton() {
        return findViewById(R.id.ef_delete_button);
    }

    @Override
    protected View findCancelButton() {
        return findViewById(R.id.ef_cancel_button);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_edit_feat;
    }

    @Override
    protected int getDeleteTitleStringId() {
        return R.string.ef_list_delete_title;
    }

    @Override
    protected int getDeleteMessageStringId() {
        return R.string.ef_delete_message;
    }

    @Override
    protected DaoMaster createDaoMaster(Context context) throws Exception {
        return new DndMaster(context);
    }

    @Override
    protected WriteDao createWriteDao(DaoMaster daoMaster) throws Exception {
        return new FeatDao(daoMaster);
    }
}
