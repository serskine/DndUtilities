package com.soupthatisthick.encounterbuilder.view.cell.summary;

import android.database.Cursor;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.soupthatisthick.encounterbuilder.model.lookup.ItemList;
import com.soupthatisthick.encounterbuilder.view.cell.ReadCell;
import com.soupthatisthick.util.dao.DaoMaster;
import com.soupthatisthick.util.dao.ReadDao;

import java.io.IOException;
import java.util.List;
import java.util.Set;

import soupthatisthick.encounterapp.R;

/**
 * Created by Owner on 7/2/2017.
 * Copyright of Stuart Marr Erskine, all rights reserved.
 */

public class ItemListSummaryCell extends ReadCell<ItemList> {

    TextView theTitle;
    public ItemListSummaryCell(LayoutInflater inflater, View convertView, ViewGroup parent) {
        super(inflater, convertView, parent);
    }

    @Override
    public void updateUi(ItemList itemList) {
        theTitle.setText(String.format("%s (%d)", itemList.getName(), itemList.getId()));
    }

    @Override
    public View createView(LayoutInflater inflater, View convertView, ViewGroup parent) {
        View view = inflater.inflate(R.layout.cell_item_list_summary, parent);
        theTitle = (TextView) view.findViewById(R.id.theTitle);
        return view;
    }
}