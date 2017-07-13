package com.soupthatisthick.util.fragment;

import android.os.Bundle;
import android.support.annotation.CallSuper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;

import com.soupthatisthick.encounterbuilder.util.adapter.CustomListAdapter;
import com.soupthatisthick.util.Logger;

import java.util.List;

/**
 * Created by Owner on 5/31/2017.
 * Copyright of Stuart Marr Erskine, all rights reserved.
 */

public abstract class EditListFragment<Mast,Detail> extends ViewListFragment<Detail> {
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
            theListView.setSelection(position);
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

    @Override
    @CallSuper
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View root = super.onCreateView(inflater, container, savedInstanceState);

        addDetailButton = findAddDetailButton(root);
        deleteDetailButton = findDeleteDetailButton(root);
        editDetailButton = findEditDetailButton(root);
        clearDetailsButton = findClearDetailsButton(root);
        saveMastButton = findSaveMastButton(root);

        addDetailButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickAddDetailButton(v);
            }
        });

        deleteDetailButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickDeleteDetailButton(v);
            }
        });

        editDetailButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Logger.info("Edit button pressed. Selected position = " + getSelectedPosition());
                if (isDetailSelected()) {
                    onClickEditDetailButton(v, getSelectedPosition());
                }
            }
        });

        clearDetailsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickClearDetailsButton(v);
            }
        });

        saveMastButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickAddDetailButton(v);
            }
        });

        Logger.debug("Setting on item click listener for the list");
        theListView.setFocusable(true);
        theListView.setClickable(true);
        theListView.setSelection(0);
        theListView.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Logger.debug("CLICKED ON POSITION " + position);
                        setSelectedPosition(position);
                    }
                }

        );

        return root;
    }

    protected abstract int getLayoutId();
    protected abstract ListView findListView(View root);
    protected abstract View findAddDetailButton(View root);
    protected abstract View findDeleteDetailButton(View root);
    protected abstract View findEditDetailButton(View root);
    protected abstract View findClearDetailsButton(View root);
    protected abstract View findSaveMastButton(View root);

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

}
