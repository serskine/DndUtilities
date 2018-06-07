package com.soupthatisthick.util.activity;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;

import com.soupthatisthick.encounterbuilder.dao.master.EncounterMaster;
import com.soupthatisthick.encounterbuilder.util.adapter.ContentValuesAdapter;
import com.soupthatisthick.encounterbuilder.dao.lookup.ContentValuesDao;
import com.soupthatisthick.encounterbuilder.util.adapter.CustomToggleAdapter;
import com.soupthatisthick.util.Logger;
import com.soupthatisthick.util.activity.DaoEditToggleListActivity;
import com.soupthatisthick.util.dao.DaoMaster;
import com.soupthatisthick.util.dao.WriteDao;

import soupthatisthick.encounterapp.R;

/**
 * Created by Owner on 6/27/2017.
 * Copyright of Stuart Marr Erskine, all rights reserved.
 */

public class EditTableActivity extends DaoEditToggleListActivity<Object, ContentValues> {

    public static final String KEY_PATH = "KEY_PATH";
    public static final String KEY_FILENAME = "KEY_FILENAME";
    public static final String KEY_VERSION = "KEY_VERSION";
    public static final String KEY_TABLE = "KEY_TABLE";
    public static final String KEY_PRIMARY_KEY = "KEY_PRIMARY_KEY";

    private String path, table, filename, primaryKey;
    private int dbaseVersion;

    public static final void launch(
        @NonNull Activity activity,
        @NonNull String path,
        @NonNull String filename,
        @NonNull int version,
        @NonNull String table,
        @NonNull String primaryKey
    ) {
        Logger.debug("launch");
        Intent intent = new Intent(activity, EditTableActivity.class);

        // Set up to edit a database table using content values
        intent.putExtra(EditTableActivity.KEY_PRIMARY_KEY, "id");
        intent.putExtra(EditTableActivity.KEY_TABLE, "TEST_TABLE");
        intent.putExtra(EditTableActivity.KEY_PATH, DaoMaster.DATABASE_FILE_PATH);
        intent.putExtra(EditTableActivity.KEY_FILENAME, EncounterMaster.DB_FILE);
        intent.putExtra(EditTableActivity.KEY_VERSION, EncounterMaster.VERSION);

        activity.startActivity(intent);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Logger.debug("onCreate");
        try {
            Intent intent = getIntent();

            path = intent.getStringExtra(KEY_PATH);
            if (path == null) {
                throw new RuntimeException("The path to the database file is not specified.");
            } else {
                Logger.info("path             = " + path);
            }
            filename = intent.getStringExtra(KEY_FILENAME);
            if (filename ==null) {
                throw new RuntimeException("The database file name is not specified.");
            } else {
                Logger.info("filename         = " + filename);
            }
            dbaseVersion = intent.getIntExtra(KEY_VERSION, -1);
            if (dbaseVersion<1) {
                throw new RuntimeException("The expected database version is not specified. This must be > 0 but is " + dbaseVersion);
            } else {
                Logger.info("database version = " + dbaseVersion);
            }
            table = intent.getStringExtra(KEY_TABLE);
            if (table ==null) {
                throw new RuntimeException("The table we want to edit on the database is not specified.");
            } else {
                Logger.info("table            = " + table);
            }
            primaryKey = intent.getStringExtra(KEY_PRIMARY_KEY);
            if (primaryKey==null) {
                throw new RuntimeException("The primary key column to access records with is not specified.");
            } else {
                Logger.info("primary key      = " + primaryKey);
            }
        } catch (Exception e) {
            Logger.error("Failed to edit database", e);
            finish();
        }
        super.onCreate(savedInstanceState);
    }

    @Override
    protected DaoMaster createDaoMaster(Context context) throws Exception {
        Logger.debug("createDaoMaster");

        return new DaoMaster(context, path, filename, dbaseVersion);
    }

    @Override
    protected void requestEditDetail(Long detailId, ContentValues contentValues, boolean deleteOnCancel) {
        throw new RuntimeException("TODO: Implement!");
    }

    @Override
    protected int getClearListTitleStringId() {
        return R.string.et_clear_title;
    }

    @Override
    protected int getClearListMessageStringId() {
        return R.string.et_clear_message;
    }

    @Override
    protected int getDeleteDetailTitleStringId() {
        return R.string.et_delete_title;
    }

    @Override
    protected int getDeleteDetailMessageStringId() {
        return R.string.et_delete_message;
    }

    @Override
    protected WriteDao<ContentValues> createDetailDao(DaoMaster daoMaster) throws Exception {
        return new ContentValuesDao(daoMaster, table, primaryKey);
    }

    @Override
    protected WriteDao<Object> createMastDao(DaoMaster daoMaster) throws Exception {
        return null;
    }

    @Override
    protected CustomToggleAdapter<ContentValues> createToggleListAdapter(LayoutInflater layoutInflater) {
        return new ContentValuesAdapter(layoutInflater, primaryKey);
    }

}
