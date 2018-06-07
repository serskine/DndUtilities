package com.soupthatisthick.encounterbuilder.activity.lookup;

import android.content.Context;
import android.view.View;
import android.widget.EditText;

import com.soupthatisthick.encounterbuilder.dao.lookup.EntityDao;
import com.soupthatisthick.encounterbuilder.dao.master.DndMaster;
import com.soupthatisthick.encounterbuilder.model.lookup.Entity;
import com.soupthatisthick.encounterbuilder.util.Text;
import com.soupthatisthick.util.activity.DaoEditActivity;
import com.soupthatisthick.util.dao.DaoMaster;
import com.soupthatisthick.util.dao.WriteDao;

import soupthatisthick.encounterapp.R;

public class EditEntityActivity extends DaoEditActivity<Entity> {

    EditText theMetadata;

    @Override
    protected int getDeleteTitleStringId() {
        return R.string.ee_delete_title;
    }

    @Override
    protected int getDeleteMessageStringId() {
        return R.string.ee_delete_message;
    }

    @Override
    protected DaoMaster createDaoMaster(Context context) throws Exception {
        return new DndMaster(context);
    }

    @Override
    protected WriteDao createWriteDao(DaoMaster daoMaster) throws Exception {
        return new EntityDao(daoMaster);
    }

    @Override
    protected void initModelWithoutUi() {
        theMetadata = findViewById(R.id.ee_metadata_edit);
    }

    @Override
    protected void listenToUi() {
        uiWatcher.listenTo(theMetadata);
    }

    @Override
    protected void ignoreUi() {
        uiWatcher.ignore(theMetadata);
    }

    @Override
    protected void updateModelFromUi() {
        model.setMetadata(Text.toString(theMetadata.getText().toString()));
    }

    @Override
    protected void updateUiFromModel() {
        theMetadata.setText(Text.toString(model.getMetadata()));
    }

    @Override
    protected View findSaveButton() {
        return findViewById(R.id.ee_save_button);
    }

    @Override
    protected View findDeleteButton() {
        return findViewById(R.id.ee_delete_button);
    }

    @Override
    protected View findCancelButton() {
        return findViewById(R.id.ee_cancel_button);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_edit_entity;
    }
}
