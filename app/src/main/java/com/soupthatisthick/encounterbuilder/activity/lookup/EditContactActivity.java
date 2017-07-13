package com.soupthatisthick.encounterbuilder.activity.lookup;

import android.content.Context;
import android.view.View;
import android.widget.EditText;

import com.soupthatisthick.encounterbuilder.dao.lookup.ContactDao;
import com.soupthatisthick.encounterbuilder.dao.master.LogsheetMaster;
import com.soupthatisthick.encounterbuilder.model.lookup.Contact;
import com.soupthatisthick.util.activity.DaoEditActivity;
import com.soupthatisthick.util.dao.DaoMaster;
import com.soupthatisthick.util.dao.WriteDao;

import soupthatisthick.encounterapp.R;

/**
 * Created by Owner on 5/1/2017.
 * Copyright of Stuart Marr Erskine, all rights reserved.
 */

public class EditContactActivity extends DaoEditActivity<Contact> {

    private EditText theName, theDci;

    @Override
    protected int getDeleteTitleStringId() {
        return R.string.ec_delete_title;
    }

    @Override
    protected int getDeleteMessageStringId() {
        return R.string.ec_delete_message;
    }

    @Override
    protected DaoMaster createDaoMaster(Context context) throws Exception {
        return new LogsheetMaster(context);
    }

    @Override
    protected WriteDao createWriteDao(DaoMaster daoMaster) throws Exception {
        return new ContactDao(daoMaster);
    }

    @Override
    protected void initUiWithoutModel()
    {
        super.initUiWithoutModel();
        theName = (EditText) findViewById(R.id.theName);
        theDci = (EditText) findViewById(R.id.theDci);
    }

    @Override
    protected void initModelWithoutUi() {
        model = new Contact();
    }

    @Override
    protected void listenToUi() {
        uiWatcher.listenTo(theName);
        uiWatcher.listenTo(theDci);
    }


    @Override
    protected void ignoreUi() {
        uiWatcher.ignore(theName);
        uiWatcher.ignore(theDci);
    }

    @Override
    protected void updateModelFromUi() {
        model.setName(theName.getText().toString());
        model.setDci(theDci.getText().toString());
    }

    @Override
    protected void updateUiFromModel() {
        theName.setText(model.getName());
        theDci.setText(model.getDci());
    }

    @Override
    protected View findSaveButton() {
        return findViewById(R.id.ec_save_button);
    }

    @Override
    protected View findDeleteButton() {
        return findViewById(R.id.ec_delete_button);
    }

    @Override
    protected View findCancelButton() {
        return findViewById(R.id.ec_cancel_button);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_edit_contact;
    }

}



