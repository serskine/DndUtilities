package com.soupthatisthick.encounterbuilder.activity.storage;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.CallSuper;
import android.view.View;
import android.widget.TextView;

import com.soupthatisthick.encounterbuilder.activity.builder.MeasureEncounterActivity;
import com.soupthatisthick.encounterbuilder.dao.master.DndMaster;
import com.soupthatisthick.encounterbuilder.dao.master.EncounterMaster;
import com.soupthatisthick.encounterbuilder.dao.master.LogsheetMaster;
import com.soupthatisthick.encounterbuilder.util.DatabaseHelper2;
import com.soupthatisthick.util.Logger;
import com.soupthatisthick.util.activity.AppActivity;
import com.soupthatisthick.util.dao.DaoMaster;

import java.io.IOException;

import soupthatisthick.encounterapp.R;

import static com.soupthatisthick.encounterbuilder.util.DatabaseHelper2.Location.ASSETS_DIRECTORY;
import static com.soupthatisthick.encounterbuilder.util.DatabaseHelper2.Location.EXTERNAL_FILES_DIR;
import static com.soupthatisthick.encounterbuilder.util.DatabaseHelper2.Location.INTERNAL_FILES_DIR;
import static com.soupthatisthick.encounterbuilder.util.DatabaseHelper2.Location.WORKING_DATABASE_DIR;

public class StorageActivity extends AppActivity {

    TextView messageView;

    DndMaster dndMaster;
    LogsheetMaster logsheetMaster;
    EncounterMaster encounterMaster;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_storage);
        messageView = findViewById(R.id.messageView);
    }
    @Override
    @CallSuper
    protected void onResume()
    {
        Logger.debug("onResume()");
        super.onResume();
        try {
            dndMaster = new DndMaster(this);
            logsheetMaster = new LogsheetMaster(this);
            encounterMaster = new EncounterMaster(this);
        } catch (Exception e) {
            Logger.error(e.getMessage(), e);

            finish();
        }
    }


    public void onResetButtonClicked(View view)
    {
        try {
            logsheetMaster.copy(ASSETS_DIRECTORY, WORKING_DATABASE_DIR, false);
        } catch (IOException e) {
            Logger.warning(e.getMessage());
        }
    }

    public void onSaveBackupButtonClicked(View view)
    {
        copy(WORKING_DATABASE_DIR, INTERNAL_FILES_DIR, false);
    }

    public void onLoadBackupClicked(View view)
    {
        copy(INTERNAL_FILES_DIR, WORKING_DATABASE_DIR, false);
    }

    public void onImportButtonClicked(View view)
    {
        copy(EXTERNAL_FILES_DIR, WORKING_DATABASE_DIR, false);
    }

    public void onExportButtonClicked(View view)
    {
        copy(WORKING_DATABASE_DIR, EXTERNAL_FILES_DIR, true);
    }

    private void copy(DatabaseHelper2.Location source, DatabaseHelper2.Location destination, boolean asBackup) {
        StringBuilder message = new StringBuilder();
        message.append("");

        try {
            dndMaster.copy(source, destination, asBackup);
        } catch (Exception e) {
            Logger.warning(e.getMessage());
            message.append(e.getMessage());
        }
//        try {
//            logsheetMaster.copy(source, destination, asBackup);
//        } catch (Exception e) {
//            Logger.warning(e.getMessage());
//            message.append(e.getMessage());
//        }
//        try {
//            encounterMaster.copy(source, destination, asBackup);
//        } catch (Exception e) {
//            Logger.warning(e.getMessage());
//            message.append(e.getMessage());
//        }

        final String finalMessage = message.toString();

        if (finalMessage.length()>0) {
            messageView.setText(finalMessage);
            messageView.setVisibility(View.VISIBLE);
        } else {
            messageView.setVisibility(View.GONE);
        }

    }
}
