package com.soupthatisthick.encounterbuilder.activity.storage;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.CallSuper;
import android.text.Html;
import android.text.Spanned;
import android.view.View;
import android.widget.Checkable;
import android.widget.TextView;

import com.soupthatisthick.encounterbuilder.activity.builder.MeasureEncounterActivity;
import com.soupthatisthick.encounterbuilder.dao.master.DndMaster;
import com.soupthatisthick.encounterbuilder.dao.master.EncounterMaster;
import com.soupthatisthick.encounterbuilder.dao.master.LogsheetMaster;
import com.soupthatisthick.encounterbuilder.util.DatabaseHelper2;
import com.soupthatisthick.encounterbuilder.util.view.ViewUtil;
import com.soupthatisthick.util.Logger;
import com.soupthatisthick.util.activity.AppActivity;
import com.soupthatisthick.util.dao.DaoMaster;
import com.soupthatisthick.util.view.HtmlEdit;
import com.soupthatisthick.util.view.HtmlView;

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

    Checkable compendiumCheck;
    Checkable encountersCheck;
    Checkable logsheetsCheck;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_storage);
        messageView = findViewById(R.id.messageView);

        compendiumCheck = findViewById(R.id.compendium_toggle);
        encountersCheck = findViewById(R.id.encounters_toggle);
        logsheetsCheck = findViewById(R.id.logsheet_toggle);
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
        copy(ASSETS_DIRECTORY, WORKING_DATABASE_DIR, false);
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
        StringBuilder errors = new StringBuilder();
        message.append("");
        errors.append("");



        try {

            if (compendiumCheck.isChecked()) {
                dndMaster.copy(source, destination, asBackup);
                message.append(buildCopyMessage(dndMaster, source, destination, asBackup));
            }
        } catch (Exception e) {
            Logger.warning(e.getMessage());
            errors.append(buildErrorMessage(dndMaster, source, destination, asBackup, e));
        }

        try {
            if (logsheetsCheck.isChecked()) {
                logsheetMaster.copy(source, destination, asBackup);
                message.append(buildCopyMessage(logsheetMaster, source, destination, asBackup));
            }
        } catch (Exception e) {
            Logger.warning(e.getMessage());
            errors.append(buildErrorMessage(logsheetMaster, source, destination, asBackup, e));
        }
        try {
            if (encountersCheck.isChecked()) {
                encounterMaster.copy(source, destination, asBackup);
                message.append(buildCopyMessage(encounterMaster, source, destination, asBackup));
            }
        } catch (Exception e) {
            Logger.warning(e.getMessage());
            errors.append(buildErrorMessage(encounterMaster, source, destination, asBackup, e));
        }


        if (message.length()<1 && errors.length() < 1) {
            messageView.setVisibility(View.INVISIBLE);
        } else {
            final String finalMessage = "<p>" + message.toString() + "<p>" + errors.toString();

            Spanned spannedMessage = Html.fromHtml(finalMessage);
            messageView.setText(spannedMessage);
            messageView.setVisibility(View.VISIBLE);
        }

    }

    private final String buildErrorMessage(
        final DaoMaster daoMaster,
        final DatabaseHelper2.Location source,
        final DatabaseHelper2.Location destination,
        final boolean asBackup,
        final Exception e
    ) {
        final String toWhere = locationText(daoMaster, destination, asBackup);
        final String fromWhere = locationText(daoMaster, source, false);
        final String what = daoMaster.getDatabaseName();
        final String errorMessage = e.getMessage();

        if (!(destination == DatabaseHelper2.Location.WORKING_DATABASE_DIR)) {
            return ViewUtil.getColoredHtml(
                "<p><b>Failed to export <i>" + what + ".</i></b><br/><i>" + errorMessage + "<br/></i>",
                ViewUtil.errorTextColor(this)
            );
        } else {
            return ViewUtil.getColoredHtml(
                "<p><b>Failed to import <i>" + what + ".</i></b><br/><i>" + errorMessage + "<br/></i>",
                ViewUtil.errorTextColor(this)
            );
        }
    }

    private final String buildCopyMessage(
        DaoMaster daoMaster,
        DatabaseHelper2.Location source,
        DatabaseHelper2.Location destination,
        boolean asBackup
    ) {
        final StringBuilder sb = new StringBuilder();
        final String toWhere = locationText(daoMaster, destination, asBackup);
        final String fromWhere = locationText(daoMaster, source, false);
        final String what = daoMaster.getDatabaseName();

        if (!(destination == DatabaseHelper2.Location.WORKING_DATABASE_DIR)) {
            sb
                    .append("<p><b>Exported <i>" + what + "</i> to</b>")
                    .append("<br/><i>")
                    .append(toWhere)
                    .append("</i><br>");
        } else {
            sb
                    .append("<p><b>Imported <i>" + what + "</i> from</b>")
                    .append("<br/><i>")
                    .append(fromWhere)
                    .append("</i><br>");
        }
        return sb.toString();
    }

    private String locationText(DaoMaster daoMaster, DatabaseHelper2.Location location, boolean isBackup) {
        try {
            return daoMaster.getLocationPath(location, isBackup);
        } catch (Exception e) {
            return location.name() + " location.";
        }
    }
}
