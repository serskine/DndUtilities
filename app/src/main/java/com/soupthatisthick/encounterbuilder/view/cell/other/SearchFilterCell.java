package com.soupthatisthick.encounterbuilder.view.cell.other;

import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import com.soupthatisthick.encounterbuilder.model.lookup.SearchFilter;
import com.soupthatisthick.encounterbuilder.util.listeners.UiWatcher;
import com.soupthatisthick.encounterbuilder.view.cell.WriteCell;

import soupthatisthick.encounterapp.R;

/**
 * Created by Owner on 5/23/2017.
 * Copyright of Stuart Marr Erskine, all rights reserved.
 */

public class SearchFilterCell extends WriteCell<SearchFilter> {

    TextView theTitle;
    CheckBox theToggle;

    public SearchFilterCell(LayoutInflater inflater, View convertView, ViewGroup parent) {
        super(inflater, convertView, parent);
    }

    @Override
    public void updateUiFromModel(@Nullable SearchFilter searchFilter) {
        if (searchFilter!=null) {
            theTitle.setText("" + searchFilter.getTable() + "." + searchFilter.getColumn());
            theToggle.setChecked(searchFilter.isAllowed());
        } else {
            theTitle.setText("Table.Column");
            theToggle.setChecked(false);
        }
    }

    @Override
    public View createView(LayoutInflater inflater, View convertView, ViewGroup parent) {
        View view = inflater.inflate(R.layout.cell_search_filter, parent);
        theTitle = (TextView) view.findViewById(R.id.theTitle);
        theToggle = (CheckBox) view.findViewById(R.id.theToggle);
        return view;
    }

    /**
     * This method will remove all the fields from the ui watcher so that updates do not trigger when their fields are changed
     *
     * @param watcher
     */
    @Override
    protected void ignoreUi(UiWatcher watcher) {
        watcher.ignore(theToggle);
    }

    /**
     * This method will add all the fields to the ui watcher so that updates will trigger when their fields are changed
     *
     * @param watcher
     */
    @Override
    protected void listenToUi(UiWatcher watcher) {
        watcher.listenTo(theToggle);
    }

    /**
     * This will update the model from the fields on the ui. When this is called we will not be listening to the ui so we don't
     * have to worry about resursive changes
     */
    @Override
    protected void updateModelFromUi(@Nullable SearchFilter model) {
    }
}
