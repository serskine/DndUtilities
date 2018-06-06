package com.soupthatisthick.encounterbuilder.view.cell.summary;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.soupthatisthick.encounterbuilder.model.lookup.EntityList;
import com.soupthatisthick.encounterbuilder.view.cell.ReadCell;

import soupthatisthick.encounterapp.R;

/**
 * Created by Owner on 7/2/2017.
 * Copyright of Stuart Marr Erskine, all rights reserved.
 */

public class ItemListSummaryCell extends ReadCell<EntityList> {

    TextView theTitle;
    public ItemListSummaryCell(LayoutInflater inflater, View convertView, ViewGroup parent) {
        super(inflater, convertView, parent);
    }

    @Override
    public void updateUi(EntityList entityList) {
        theTitle.setText(String.format("%s (%d)", entityList.getName(), entityList.getId()));
    }

    @Override
    public View createView(LayoutInflater inflater, View convertView, ViewGroup parent) {
        View view = inflater.inflate(R.layout.cell_item_list_summary, parent);
        theTitle = (TextView) view.findViewById(R.id.theTitle);
        return view;
    }
}