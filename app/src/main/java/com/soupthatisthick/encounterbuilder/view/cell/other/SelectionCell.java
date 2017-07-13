package com.soupthatisthick.encounterbuilder.view.cell.other;

import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.FrameLayout;

import com.soupthatisthick.encounterbuilder.model.Selection;
import com.soupthatisthick.encounterbuilder.util.listeners.UiWatcher;
import com.soupthatisthick.encounterbuilder.view.cell.ReadCell;
import com.soupthatisthick.encounterbuilder.view.cell.WriteCell;
import com.soupthatisthick.util.Logger;

import soupthatisthick.encounterapp.R;

/**
 * Created by Owner on 5/27/2017.
 * Copyright of Stuart Marr Erskine, all rights reserved.
 */

public abstract class SelectionCell<DataType> extends WriteCell<Selection<DataType>>
{
    private CheckBox theCheckBox;
    private FrameLayout theItemFrame;

    private ReadCell<DataType> itemCell;

    public SelectionCell(LayoutInflater inflater, View convertView, ViewGroup parent) {
        super(inflater, convertView, parent);
    }

    /**
     * This method will remove all the fields from the ui watcher so that updates do not trigger when their fields are changed
     *
     * @param watcher
     */
    @Override
    protected void ignoreUi(UiWatcher watcher) {
        Logger.debug("Ignoring to the checkbox");
        watcher.ignore(theCheckBox);
    }

    /**
     * This method will add all the fields to the ui watcher so that updates will trigger when their fields are changed
     *
     * @param watcher
     */
    @Override
    protected void listenToUi(UiWatcher watcher) {
        Logger.debug("Listening to the checkbox");
        watcher.listenTo(theCheckBox);
    }

    /**
     * This will update the model from the fields on the ui. When this is called we will not be listening to the ui so we don't
     * have to worry about resursive changes
     *
     * @param selection
     */
    @Override
    protected void updateModelFromUi(@Nullable Selection<DataType> selection) {
        try {
            selection.setSelected(theCheckBox.isChecked());
            Logger.debug("Updated selection: " + selection.toString());

        } catch (Exception e) {
            Logger.error("Failed to update selection", e);
        }
    }

    @Override
    public void updateUiFromModel(@Nullable Selection<DataType> selection) {
        if (selection != null) {
            theCheckBox.setChecked(selection.isSelected());
            itemCell.getView().setVisibility(View.VISIBLE);
            itemCell.updateUi(selection.getItem());
        } else {
            theCheckBox.setText(theCheckBox.getResources().getString(R.string.view_visible_unknown));
            itemCell.getView().setVisibility(View.GONE);
        }
    }

    @Override
    public View createView(LayoutInflater inflater, View convertView, ViewGroup parent) {
        View view = inflater.inflate(R.layout.cell_selection, parent);

        theCheckBox = (CheckBox) view.findViewById(R.id.theCheckbox);
        theItemFrame = (FrameLayout) view.findViewById(R.id.theItemFrame);

        theItemFrame.removeAllViews();
        itemCell = createItemCell(inflater, null, theItemFrame);
        theItemFrame.addView(itemCell.getView());
        return view;
    }

    /**
     * This is used to create views for individual items
     * @return
     */
    protected abstract ReadCell<DataType> createItemCell(LayoutInflater inflater, View convertView, ViewGroup parent);

}
