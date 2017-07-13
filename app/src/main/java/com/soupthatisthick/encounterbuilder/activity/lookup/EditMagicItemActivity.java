package com.soupthatisthick.encounterbuilder.activity.lookup;

import android.content.Context;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;

import com.soupthatisthick.encounterbuilder.adapters.lookup.RarityAdapter;
import com.soupthatisthick.util.activity.DaoEditActivity;
import com.soupthatisthick.util.dao.DaoMaster;
import com.soupthatisthick.encounterbuilder.dao.lookup.MagicItemDao;
import com.soupthatisthick.util.dao.WriteDao;
import com.soupthatisthick.encounterbuilder.dao.master.DndMaster;
import com.soupthatisthick.encounterbuilder.model.lookup.MagicItem;
import com.soupthatisthick.encounterbuilder.model.lookup.Rarity;
import com.soupthatisthick.encounterbuilder.util.translater.RarityTranslater;

import soupthatisthick.encounterapp.R;

/**
 * Created by Owner on 2/26/2017.
 * Copyright of Stuart Marr Erskine, all rights reserved.
 */

public class EditMagicItemActivity extends DaoEditActivity<MagicItem> {

    private EditText theName, theType, theAttunement, theDescription, theLocation;
    private Spinner theRarity;

    @Override
    protected int getDeleteTitleStringId() {
        return R.string.emi_delete_title;
    }

    @Override
    protected int getDeleteMessageStringId() {
        return R.string.emi_delete_message;
    }

    @Override
    protected DaoMaster createDaoMaster(Context context) throws Exception {
        return new DndMaster(context);
    }

    @Override
    protected WriteDao createWriteDao(DaoMaster daoMaster) throws Exception {
        return new MagicItemDao(daoMaster);
    }

    @Override
    protected void initUiWithoutModel()
    {
        super.initUiWithoutModel();
        theName = (EditText) findViewById(R.id.emi_name);
        theType = (EditText) findViewById(R.id.emi_type);
        theAttunement = (EditText) findViewById(R.id.emi_attunement);
        theLocation = (EditText) findViewById(R.id.emi_location);
        theDescription = (EditText) findViewById(R.id.emi_description);
        theRarity = (Spinner) findViewById(R.id.emi_rarity);

        theRarity.setAdapter(new RarityAdapter(getLayoutInflater()));
    }

    @Override
    protected void initModelWithoutUi() {
        model = new MagicItem();
    }

    @Override
    protected void listenToUi() {
        uiWatcher.listenTo(theName);
        uiWatcher.listenTo(theType);
        uiWatcher.listenTo(theAttunement);
        uiWatcher.listenTo(theDescription);
        uiWatcher.listenTo(theLocation);
        uiWatcher.listenTo(theRarity);
    }


    @Override
    protected void ignoreUi() {
        uiWatcher.ignore(theName);
        uiWatcher.ignore(theType);
        uiWatcher.ignore(theAttunement);
        uiWatcher.ignore(theDescription);
        uiWatcher.ignore(theLocation);
        uiWatcher.ignore(theRarity);
    }

    @Override
    protected void updateModelFromUi() {
        model.setName(theName.getText().toString());
        model.setType(theType.getText().toString());
        model.setAttunement(theAttunement.getText().toString());
        model.setLocation(theLocation.getText().toString());
        model.setDescription(theDescription.getText().toString());

        Rarity rarity = (Rarity) theRarity.getSelectedItem();
        model.setRarity( RarityTranslater.INSTANCE.toText(rarity));
    }

    @Override
    protected void updateUiFromModel() {
        theName.setText(model.getName());
        theType.setText(model.getType());
        theAttunement.setText(model.getAttunement());
        theLocation.setText(model.getLocation());
        theDescription.setText(model.getDescription());

        Rarity rarity = RarityTranslater.INSTANCE.toRarity(model.getRarity());
        theRarity.setSelection(rarity.ordinal());
    }

    @Override
    protected View findSaveButton() {
        return findViewById(R.id.emi_save_button);
    }

    @Override
    protected View findDeleteButton() {
        return findViewById(R.id.emi_delete_button);
    }

    @Override
    protected View findCancelButton() {
        return findViewById(R.id.emi_cancel_button);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_edit_magic_item;
    }

}



