package com.soupthatisthick.encounterbuilder.activity.lookup;

import android.content.Context;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;

import com.soupthatisthick.encounterbuilder.adapters.lookup.SpellLevelAdapter;
import com.soupthatisthick.encounterbuilder.adapters.lookup.SpellSchoolAdapter;
import com.soupthatisthick.util.activity.DaoEditActivity;
import com.soupthatisthick.util.dao.DaoMaster;
import com.soupthatisthick.encounterbuilder.dao.lookup.SpellDao;
import com.soupthatisthick.util.dao.WriteDao;
import com.soupthatisthick.encounterbuilder.dao.master.DndMaster;
import com.soupthatisthick.encounterbuilder.model.lookup.Spell;
import com.soupthatisthick.encounterbuilder.model.lookup.SpellSchool;
import com.soupthatisthick.encounterbuilder.util.translater.SpellLevelTranslater;
import com.soupthatisthick.encounterbuilder.util.translater.SpellSchoolTranslater;

import soupthatisthick.encounterapp.R;

/**
 * Created by Owner on 2/28/2017.
 * Copyright of Stuart Marr Erskine, all rights reserved.
 */

public class EditSpellActivity extends DaoEditActivity<Spell> {

    protected EditText theName, theClasses, theCastingTime, theRange, theComponents, theDuration, theDescription, theMaterials;
    protected Spinner theSpellLevel, theType;

    @Override
    protected void initUiWithoutModel()
    {
        super.initUiWithoutModel();

        theSpellLevel = (Spinner) findViewById(R.id.theSpellLevel);
        theSpellLevel.setAdapter(new SpellLevelAdapter(getLayoutInflater()));

        theType = (Spinner) findViewById(R.id.theType);
        theType.setAdapter(new SpellSchoolAdapter(getLayoutInflater()));

        theName = (EditText) findViewById(R.id.theName);
        theClasses = (EditText) findViewById(R.id.theClasses);
        theCastingTime = (EditText) findViewById(R.id.theCastingTime);
        theRange = (EditText) findViewById(R.id.theRange);
        theComponents = (EditText) findViewById(R.id.theComponents);
        theMaterials = (EditText) findViewById(R.id.theMaterials);
        theDuration = (EditText) findViewById(R.id.theDuration);
        theDescription = (EditText) findViewById(R.id.theDescription);
    }

    @Override
    protected int getDeleteTitleStringId() {
        return R.string.esp_delete_title;
    }

    @Override
    protected int getDeleteMessageStringId() {
        return R.string.esp_delete_message;
    }

    @Override
    protected DaoMaster createDaoMaster(Context context) throws Exception {
        return new DndMaster(context);
    }

    @Override
    protected WriteDao createWriteDao(DaoMaster daoMaster) throws Exception {
        return new SpellDao(daoMaster);
    }

    @Override
    protected void initModelWithoutUi() {
        model = new Spell();
    }


    @Override
    protected void listenToUi() {

        uiWatcher.listenTo(theSpellLevel);
        uiWatcher.listenTo(theName);
        uiWatcher.listenTo(theClasses);
        uiWatcher.listenTo(theType);
        uiWatcher.listenTo(theCastingTime);
        uiWatcher.listenTo(theRange);
        uiWatcher.listenTo(theComponents);
        uiWatcher.listenTo(theMaterials);
        uiWatcher.listenTo(theDuration);
        uiWatcher.listenTo(theDescription);

    }

    @Override
    protected void ignoreUi() {

        uiWatcher.ignore(theSpellLevel);
        uiWatcher.ignore(theName);
        uiWatcher.ignore(theClasses);
        uiWatcher.ignore(theType);
        uiWatcher.ignore(theCastingTime);
        uiWatcher.ignore(theRange);
        uiWatcher.ignore(theComponents);
        uiWatcher.ignore(theMaterials);
        uiWatcher.ignore(theDuration);
        uiWatcher.ignore(theDescription);

    }

    @Override
    protected void updateModelFromUi() {
        Integer level = (Integer) theSpellLevel.getSelectedItem();
        SpellSchool school = (SpellSchool) theType.getSelectedItem();

        model.setLevel(level);    // Spell level value matches the position

        String levelText = SpellLevelTranslater.INSTANCE.toText(level);
        String schoolText = SpellSchoolTranslater.INSTANCE.toText(school);

        if (level < 1)
        {

            model.setType(schoolText + " " + levelText);
        } else {
            model.setType(levelText + " " + schoolText);
        }

        model.setName(theName.getText().toString());
        model.setClasses(theClasses.getText().toString());

        model.setCastingTime(theCastingTime.getText().toString());
        model.setRange(theRange.getText().toString());
        model.setComponents(theComponents.getText().toString());
        model.setMaterials(theMaterials.getText().toString());
        model.setDuration(theDuration.getText().toString());
        model.setDescription(theDescription.getText().toString());
    }

    @Override
    protected void updateUiFromModel() {
        theSpellLevel.setSelection(model.getLevel());    // Spell level value matches the position
        theName.setText(model.getName());
        theClasses.setText(model.getClasses());

        SpellSchool school = SpellSchoolTranslater.INSTANCE.toSpellSchool(model.getType());
        theType.setSelection(school.ordinal());

        theCastingTime.setText(model.getCastingTime());
        theRange.setText(model.getRange());
        theComponents.setText(model.getComponents());
        theMaterials.setText(model.getMaterials());
        theDuration.setText(model.getDuration());
        theDescription.setText(model.getDescription());
    }

    @Override
    protected View findSaveButton() {
        return findViewById(R.id.esp_save_button);
    }

    @Override
    protected View findDeleteButton() {
        return findViewById(R.id.esp_delete_button);
    }

    @Override
    protected View findCancelButton() {
        View view =  findViewById(R.id.esp_cancel_button);
        // The cancel button is hidden. We either save or delete the spell.
        // This is due to how we handle insertion of new items.
        view.setVisibility(View.GONE);
        return view;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_edit_spell;
    }
}
