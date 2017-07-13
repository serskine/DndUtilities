package com.soupthatisthick.encounterbuilder.activity.lookup;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.soupthatisthick.util.activity.AppActivity;

import soupthatisthick.encounterapp.R;

/**
 * Created by Owner on 5/8/2017.
 * Copyright of Stuart Marr Erskine, all rights reserved.
 */

public class EditDatabasesActivity extends AppActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_databases);
    }

    public void onClickEditFeats(View view)
    {
        Intent intent = new Intent(this, EditFeatListActivity.class);
        startActivity(intent);
    }

    public void onClickEditContactsButton(View view)
    {
        Intent intent = new Intent(this, ExEditContactsActivity.class);
        startActivity(intent);
    }

    public void onClickCustomListsButton(View view)
    {
        Intent intent = new Intent(this, EditCustomListsActivity.class);
        startActivity(intent);
    }

    public void onClickPersonalNotesButton(View view)
    {
        Intent intent = new Intent(this, ExEditNotesActivity.class);
        startActivity(intent);
    }

    public void onClickViewCompendium(View view)
    {
        Intent intent = new Intent(this, CompendiumActivity.class);
        startActivity(intent);
    }

    public void onClickEditMap(View view) {
        Intent intent = new Intent(this, EditMapActivity.class);
        startActivity(intent);
    }

    public void onClickEditCustomMonsters(View view)
    {
        Intent intent = new Intent(this, EditMonsterManualActivity.class);
        startActivity(intent);
    }

    public void onClickViewSpells(View view) {
        Intent intent = new Intent(this, EditSpellBookActivity.class);
        startActivity(intent);
    }

    public void onClickViewMagicItems(View view) {
        Intent intent = new Intent(this, EditMagicItemsListActivity.class);
        startActivity(intent);
    }

    public void onClickEditLogsheets(View view) {
        Intent intent = new Intent(this, EditLogsheetsListActivity.class);
        startActivity(intent);
    }

}