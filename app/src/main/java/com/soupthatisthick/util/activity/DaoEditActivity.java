package com.soupthatisthick.util.activity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.CallSuper;
import android.support.annotation.Nullable;
import android.view.View;

import com.soupthatisthick.util.dao.DaoMaster;
import com.soupthatisthick.util.dao.WriteDao;
import com.soupthatisthick.util.Logger;
import com.soupthatisthick.encounterbuilder.util.listeners.UiWatcher;

import java.util.Set;

/**
 * Created by Owner on 2/27/2017. This is used to edit a specifix item.
 * Copyright of Stuart Marr Erskine, all rights reserved.
 */

public abstract class DaoEditActivity<Model> extends EditActivity {

    public static final String KEY_MODEL_ID = "KEY_MODEL_ID";
    public static final String KEY_DELETE_ON_CANCEL = "KEY_DELETE_ON_CANCEL";

    private DaoMaster daoMaster;
    private WriteDao<Model> writeDao;

    protected Model model = null;

    protected final UiWatcher uiWatcher = new UiWatcher() {
        @Override
        protected void onUiUpdate() {
            ignoreUi();
            updateModelFromUi();
            listenToUi();
        }
    };

    protected abstract int getDeleteTitleStringId();
    protected abstract int getDeleteMessageStringId();

    protected abstract DaoMaster createDaoMaster(Context context) throws Exception;
    protected abstract WriteDao createWriteDao(DaoMaster daoMaster) throws Exception;

    protected final WriteDao<Model> getWriteDao()
    {
        return this.writeDao;
    }

    @CallSuper
    @Override
    protected void initUiWithoutModel()
    {
        super.initUiWithoutModel();
        Logger.debug("initUiWithoutModel()");
    }

    protected final void loadModelFromBackEndStore()
    {
        Logger.debug("loadModelFromBackEndStore()");

        Long theId;
        try {
            Intent intent = getIntent();
            theId  = intent.getLongExtra(KEY_MODEL_ID, -1);

            if (theId==null)
            {
                throw new RuntimeException("We don't know the id of the model we wish to edit. Finishing activity.");
            }
            if (theId<1)
            {
                throw new RuntimeException("Id of the model should be greater than 0 but was " + theId + ". "
                        + KEY_MODEL_ID + " key in the intent was probably not set by the parent activity." + "\n"
                        + intentString(intent)
                );
            }

            model = getWriteDao().load(theId);
            if (model==null)
            {
                String output = "Failed to load model with id " + theId + " from table " + getWriteDao().getTable() + ".\n";
                output += "The primary key for this table is " + getWriteDao().getIdColumn() + ".\n";
                throw new RuntimeException(output);
            } else {
                Logger.info("Loaded model with id " + theId + " from table " + getWriteDao().getTable() + ".\n");
                logModel();
            }
        } catch (Exception e) {
            Logger.error(e.getMessage(), e);
            finish();
        }
    }

    protected void onClickSaveButton(View view)
    {
        Logger.debug("onClickSaveButton()");
        try {
            Long id = getWriteDao().getId(model);
            getWriteDao().update(id, model);
            finish();
        } catch (Exception e) {
            Logger.error(e.getMessage(), e);
        }
    }

    /**
     * This will delete the model from the back end store.
     */
    private void deleteFromBackEndStore()
    {
        try {
            Long id = getWriteDao().getId(model);
            getWriteDao().delete(id);
            finish();
        } catch (Exception e) {
            Logger.error(e.getMessage(), e);
        }
    }


    protected void onClickDeleteButton(View view)
    {
        Logger.debug("onClickDeleteButton()");

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(getDeleteTitleStringId());
        builder.setMessage(getDeleteMessageStringId());
        builder.setPositiveButton(android.R.string.yes,
            new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                   deleteFromBackEndStore();
                }
            }
        );
        builder.setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                // do nothing
            }
        });
        builder.setIcon(android.R.drawable.ic_dialog_alert);

        builder.show();


    }

    protected void onClickCancelButton(View view)
    {
        Logger.debug("onClickCancelButton()");
        Boolean  deleteModel = getIntent().getBooleanExtra(KEY_DELETE_ON_CANCEL, false);
        if (deleteModel)
        {
            deleteFromBackEndStore();
        }

        finish();
    }

    @CallSuper
    @Override
    protected void onStart()
    {
        super.onStart();
        Logger.debug("onStart()");
        try {
            this.daoMaster = createDaoMaster(getBaseContext());
            this.writeDao = createWriteDao(daoMaster);
        } catch (Exception e) {
            Logger.error(e.getMessage(), e);
            finish();
        }
    }

    @CallSuper
    @Override
    protected void onStop() {
        super.onStop();
        Logger.debug("onStop()");

        DaoMaster.close(daoMaster);
    }


    protected static final String intentString(@Nullable Intent intent)
    {

        String output = "";

        if (intent==null)
        {
            output += "INTENT IS NULL!!!\n";
        } else {
            output += "***\n*** Intent data\n***\n";
            Set<String> categories = intent.getCategories();
            if (categories!=null) {
                for (String category : categories) {
                    output += " - " + category + " = " + intent.getStringExtra(category) + "\n";
                }
            } else {
                output += " - NO CATEGORIES PRESENT\n";
            }
        }

        return output;
    }

    protected void logModel()
    {
        // TO Be implemented by subclasses
    }
}
