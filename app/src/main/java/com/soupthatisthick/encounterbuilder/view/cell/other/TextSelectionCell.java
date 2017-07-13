package com.soupthatisthick.encounterbuilder.view.cell.other;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.soupthatisthick.encounterbuilder.view.cell.ReadCell;
import com.soupthatisthick.encounterbuilder.view.cell.summary.TextCell;

/**
 * Created by Owner on 5/31/2017.
 * Copyright of Stuart Marr Erskine, all rights reserved.
 */

public class TextSelectionCell extends SelectionCell<String> {
    public TextSelectionCell(LayoutInflater inflater, View convertView, ViewGroup parent) {
        super(inflater, convertView, parent);
    }

    /**
     * This is used to create views for individual items
     *
     * @param inflater
     * @param convertView
     * @param parent
     * @return
     */
    @Override
    protected ReadCell<String> createItemCell(LayoutInflater inflater, View convertView, ViewGroup parent) {
        return new TextCell(inflater, convertView, parent);
    }

}
