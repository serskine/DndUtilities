package com.soupthatisthick.encounterbuilder.activity;

import android.content.Intent;
import android.os.Bundle;

import android.view.View;

import com.soupthatisthick.encounterbuilder.activity.builder.MeasureEncounterActivity;
import com.soupthatisthick.encounterbuilder.activity.lookup.EditContentValuesActivity;
import com.soupthatisthick.encounterbuilder.activity.lookup.EditCustomListsActivity;
import com.soupthatisthick.encounterbuilder.activity.lookup.EditDatabasesActivity;
import com.soupthatisthick.encounterbuilder.activity.lookup.EditFeatListActivity;
import com.soupthatisthick.encounterbuilder.activity.lookup.EditLogsheetsListActivity;
import com.soupthatisthick.encounterbuilder.activity.lookup.EditMagicItemsListActivity;
import com.soupthatisthick.encounterbuilder.activity.lookup.EditMapActivity;
import com.soupthatisthick.encounterbuilder.activity.lookup.EditMonsterManualActivity;
import com.soupthatisthick.encounterbuilder.activity.lookup.EditSpellBookActivity;
import com.soupthatisthick.encounterbuilder.activity.lookup.ExEditContactsActivity;
import com.soupthatisthick.encounterbuilder.activity.lookup.ExEditNotesActivity;
import com.soupthatisthick.encounterbuilder.activity.lookup.CompendiumActivity;
import com.soupthatisthick.encounterbuilder.activity.lookup.ExViewMonsterManualActivity;
import com.soupthatisthick.encounterbuilder.activity.lookup.LootTableActivity;
import com.soupthatisthick.encounterbuilder.dao.master.EncounterMaster;
import com.soupthatisthick.util.activity.AppActivity;
import com.soupthatisthick.util.activity.EditTableActivity;
import com.soupthatisthick.util.dao.DaoMaster;

import soupthatisthick.encounterapp.R;

import static junit.framework.Assert.assertEquals;

public class MainActivity extends AppActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onClickTestButton(View view)
    {
        Intent intent = new Intent(this, EditCustomListsActivity.class);
        startActivity(intent);
    }

    public void onClickMeasureEncounterButton(View view)
    {
        Intent intent = new Intent(this, MeasureEncounterActivity.class);
        startActivity(intent);
    }

    public void onClickSettingsButton(View view)
    {
        Intent intent = new Intent(this, SettingsActivity.class);
        startActivity(intent);
    }

    public void onClickRollForLootButton(View view)
    {
        Intent intent = new Intent(this, LootTableActivity.class);
        startActivity(intent);
    }

    public void onClickEditDatabases(View view)
    {
        Intent intent = new Intent(this, EditDatabasesActivity.class);
        startActivity(intent);
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