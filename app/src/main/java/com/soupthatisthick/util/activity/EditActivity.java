package com.soupthatisthick.util.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.CallSuper;
import android.view.View;

import com.soupthatisthick.encounterbuilder.util.listeners.UiWatcher;
import com.soupthatisthick.util.Logger;

/**
 * Created by Owner on 2/18/2017.
 * Copyright of Stuart Marr Erskine, all rights reserved.
 */

public abstract class EditActivity extends AppActivity {

    protected final UiWatcher uiWatcher = new UiWatcher() {
        @Override
        protected void onUiUpdate() {
            ignoreUi();
            updateModelFromUi();
            listenToUi();
        }
    };

    protected View saveButton, deleteButton, cancelButton;

    protected abstract void initModelWithoutUi();
    protected abstract void listenToUi();
    protected abstract void ignoreUi();
    protected abstract void updateModelFromUi();
    protected abstract void updateUiFromModel();
    protected abstract void loadModelFromBackEndStore();

    protected abstract View findSaveButton();
    protected abstract View findDeleteButton();
    protected abstract View findCancelButton();

    protected abstract void onClickDeleteButton(View view);
    protected abstract void onClickSaveButton(View view);
    protected abstract void onClickCancelButton(View view);

    protected abstract int getLayoutId();

    @CallSuper
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        Logger.debug("onCreate()");
        setContentView(getLayoutId());
        initUiWithoutModel();
        initModelWithoutUi();
    }

    @CallSuper
    protected void initUiWithoutModel()
    {
        Logger.debug("initUiWithoutModel()");
        saveButton = findSaveButton();
        deleteButton = findDeleteButton();
        cancelButton = findCancelButton();

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickSaveButton(v);
            }
        });

        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickDeleteButton(v);
            }
        });

        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickCancelButton(v);
            }
        });
    }

    @Override
    @CallSuper
    protected void onResume()
    {
        super.onResume();
        Logger.debug("onResume()");

        loadModelFromBackEndStore();
        ignoreUi();
        updateUiFromModel();
        listenToUi();
    }

    @Override
    @CallSuper
    protected void onPause()
    {
        super.onPause();
        Logger.debug("onPause()");

    }
}
