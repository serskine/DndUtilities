package com.soupthatisthick.encounterbuilder.activity.lookup;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ListView;

import com.soupthatisthick.encounterbuilder.adapters.lookup.EditableContentValuesAdapter;
import com.soupthatisthick.encounterbuilder.dao.lookup.ContentValuesDao;
import com.soupthatisthick.util.activity.DaoEditActivity;
import com.soupthatisthick.util.dao.DaoMaster;
import com.soupthatisthick.util.dao.WriteDao;
import com.soupthatisthick.encounterbuilder.dao.master.DndMaster;
import com.soupthatisthick.util.Logger;

import soupthatisthick.encounterapp.R;

/**
 * Created by Owner on 3/14/2017.
 * Copyright of Stuart Marr Erskine, all rights reserved.
 */

public class EditContentValuesActivity extends DaoEditActivity<ContentValues> {


    public static final String KEY_TABLE = "KEY_TABLE";
    public static final String KEY_PRIMARY_KEY = "PRIMARY_KEY";

    private String table = null;
    private String primaryKey = null;

    private ListView listView = null;
    private EditableContentValuesAdapter listAdapter;
    private ContentValues model = null;
    private ContentValuesDao cvDao = null;

    @Override
    protected int getDeleteTitleStringId() {
        return R.string.ecv_delete_title;
    }

    @Override
    protected int getDeleteMessageStringId() {
        return R.string.ecv_delete_message;
    }

    @Override
    protected DaoMaster createDaoMaster(Context context) throws Exception {
        return new DndMaster(context);
    }

    @Override
    protected WriteDao createWriteDao(DaoMaster daoMaster) throws Exception {
        Intent intent = getIntent();

        table = intent.getStringExtra(KEY_TABLE);
        primaryKey = intent.getStringExtra(KEY_PRIMARY_KEY);
        if (table == null) {
            throw new RuntimeException("We don't know what table we need to edit. This must be specified when starting the intent.");
        }
        if (primaryKey == null) {
            throw new RuntimeException("We don't know what column is the primary key. This must be specified when starting the intent.");
        }

        cvDao = new ContentValuesDao(daoMaster, table, primaryKey);
        cvDao.logContents();
        return cvDao;
    }

    @Override
    protected void initUiWithoutModel() {
        super.initUiWithoutModel();

        listView = (ListView) findViewById(R.id.listView);
        listAdapter = new EditableContentValuesAdapter(getLayoutInflater());
        listView.setAdapter(listAdapter);

    }

    @Override
    protected void initModelWithoutUi() {
        model = new ContentValues();
    }



    @Override
    protected void listenToUi() {

    }

    @Override
    protected void ignoreUi() {

    }

    @Override
    protected void updateModelFromUi() {
        logContent("UPDATING MODEL FROM UI", model);
    }

    @Override
    protected void updateUiFromModel() {
        listAdapter.setContent(model);
    }

    @Override
    protected View findSaveButton() {
        return findViewById(R.id.saveButton);
    }

    @Override
    protected View findDeleteButton() {
        return findViewById(R.id.deleteButton);
    }

    @Override
    protected View findCancelButton() {
        return findViewById(R.id.cancelButton);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_edit_content_values;
    }

    /**
     * Logs the information about the content to the console
     * @param title
     * @param content
     */
    private static final void logContent(@NonNull String title, @Nullable ContentValues content)
    {
        Logger.title(title, 1);
        if (content==null)
        {
            Logger.info(" - Content is null!", 1);
        } else {
            if (content.keySet() == null || content.keySet().isEmpty()) {
                Logger.info(" - There is no content!", 1);
            } else {
                for (String key : content.keySet()) {
                    String value = content.getAsString(key);
                    Logger.info(" [" + key + "][" + value + "]", 1);
                }
            }
        }
    }
}
