package com.soupthatisthick.encounterbuilder.view.cell.summary;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.soupthatisthick.encounterbuilder.view.cell.ReadCell;
import com.soupthatisthick.encounterbuilder.view.cell.detail.CompendiumDetailCell;

/**
 * Created by Owner on 5/26/2018.
 * Copyright of Stuart Marr Erskine, all rights reserved.
 */
public class EntityCell extends ReadCell<Object> {

    private CompendiumSummaryCell summaryCell;
    private CompendiumDetailCell detailCell;

    public EntityCell(LayoutInflater inflater, View convertView, ViewGroup parent) {
        super(inflater, convertView, parent);
        summaryCell = new CompendiumSummaryCell(inflater, convertView, parent);
        detailCell = new CompendiumDetailCell(inflater, convertView, parent);
    }

    @Override
    public void updateUi(Object o) {

    }

    @Override
    public View createView(LayoutInflater inflater, View convertView, ViewGroup parent) {
        return null;
    }
}
