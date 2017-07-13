package com.soupthatisthick.util.activity;

import android.os.Bundle;
import android.support.annotation.CallSuper;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.soupthatisthick.util.Logger;

public abstract class EditListActivity<Mast,Detail> extends ViewListActivity<Detail>
{
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

    @CallSuper
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        addDetailButton = findAddDetailButton();
        deleteDetailButton = findDeleteDetailButton();
        editDetailButton = findEditDetailButton();
        clearDetailsButton = findClearDetailsButton();
        saveMastButton = findSaveMastButton();

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

    }

    protected abstract int getLayoutId();
    protected abstract ListView findListView();
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

}
