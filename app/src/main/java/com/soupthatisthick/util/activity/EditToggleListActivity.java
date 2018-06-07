package com.soupthatisthick.util.activity;

import android.os.Bundle;
import android.support.annotation.CallSuper;
import android.view.View;
import android.widget.ExpandableListView;

import com.soupthatisthick.encounterbuilder.util.listeners.UiWatcher;
import com.soupthatisthick.util.Logger;

/**
 * Created by Owner on 4/12/2017.
 * Copyright of Stuart Marr Erskine, all rights reserved.
 */

public abstract class EditToggleListActivity<Mast, Detail> extends ViewToggleListActivity<Detail> {
    protected final UiWatcher uiWatcher = new UiWatcher() {
        @Override
        protected void onUiUpdate() {
            ignoreUi();
            updateModelFromUi();
            listenToUi();
        }
    };

    // Used to maintain control of editing the list
    protected abstract void initModelWithoutUi();
    protected abstract void listenToUi();
    protected abstract void ignoreUi();
    protected abstract void updateModelFromUi();
    protected abstract void updateUiFromModel();
    protected abstract void loadModelFromBackEndStore();

    protected View clearDetailsButton, editDetailButton, addDetailButton, deleteDetailButton, saveMastButton;

    protected int selectedPosition = -1;

    protected final int getSelectedPosition()
    {
        return selectedPosition;
    }

    protected final void setSelectedPosition(int position)
    {
        Logger.debug("setSelectedPosition(" + position + ")");
        selectedPosition = position;
        if (isPositionValid(position)) {
            theExpandableListView.setSelection(position);
        }
    }

    protected final boolean isPositionValid(int position)
    {
        return (position >= 0) && (position < theListAdapter.getCount());
    }

    protected final boolean isDetailSelected()
    {
        return isPositionValid(getSelectedPosition());
    }

    protected final Detail getDetailAtPosition(int position)
    {
        if (isPositionValid(position))
        {
            return (Detail) theListAdapter.getItem(getSelectedPosition());
        } else {
            return null;
        }
    }

    protected final Detail getSelectedDetail()
    {
        return getDetailAtPosition(getSelectedPosition());
    }


    @CallSuper
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        initUiWithoutModel();
        initModelWithoutUi();
    }

    protected abstract int getLayoutId();
    protected abstract ExpandableListView findExpandableListView();
    protected abstract View findAddDetailButton();
    protected abstract View findDeleteDetailButton();
    protected abstract View findEditDetailButton();
    protected abstract View findClearDetailsButton();
    protected abstract View findSaveMastButton();

    /**
     * This method should be called when we want to add a new detail to the list
     * @param view
     */
    protected abstract void onClickAddDetailButton(View view);

    /**
     * This method should be calleed when we want to delete a detail from the list
     * to the list.
     * @param view
     */
    protected abstract void onClickDeleteDetailButton(View view);

    /**
     * This is called when we click the button to request clearing the list.
     * @param view
     */
    protected abstract void onClickClearDetailsButton(View view);

    /**
     * This will indicate we want to edit the specified detail
     * @param view
     */
    protected abstract void onClickEditDetailButton(View view, int position);

    /**
     * This will save the information about the list of information.
     * @param view
     */
    protected abstract void onClickSaveMastButton(View view);

    @CallSuper
    protected void initUiWithoutModel()
    {

        addDetailButton = findAddDetailButton();
        deleteDetailButton = findDeleteDetailButton();
        editDetailButton = findEditDetailButton();
        clearDetailsButton = findClearDetailsButton();
        saveMastButton = findSaveMastButton();

        addDetailButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    onClickAddDetailButton(v);
                } catch (Exception e) {
                    Logger.error(e.getMessage(), e);
                }
            }
        });

        deleteDetailButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    onClickDeleteDetailButton(v);
                } catch (Exception e) {
                    Logger.error(e.getMessage(), e);
                }
            }
        });

        editDetailButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Logger.info("Edit button pressed. Selected position = " + getSelectedPosition());
                try {
                    if (isDetailSelected()) {
                        onClickEditDetailButton(v, getSelectedPosition());
                    } else {
                        Logger.info("There is no selected detail.");
                    }
                } catch (Exception e) {
                    Logger.error(e.getMessage(), e);
                }
            }
        });

        clearDetailsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    onClickClearDetailsButton(v);
                } catch (Exception e) {
                    Logger.error(e.getMessage(), e);
                }
            }
        });

        saveMastButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    onClickSaveMastButton(v);
                } catch (Exception e) {
                    Logger.error(e.getMessage(), e);
                }
            }
        });

        Logger.debug("Setting on item click listener for the list");

        theExpandableListView.setFocusable(true);
        theExpandableListView.setClickable(true);
        theExpandableListView.setSelection(0);

        theExpandableListView.setOnGroupClickListener(
                new ExpandableListView.OnGroupClickListener() {
                    @Override
                    public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {
                        Logger.debug("CLICKED ON GROUP POSITION " + groupPosition);
                        setSelectedPosition(groupPosition);
                        return false;
                    }
                }
        );

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