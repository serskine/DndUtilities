package com.soupthatisthick.encounterbuilder.view.cell.detail;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.soupthatisthick.encounterbuilder.model.lookup.ItemList;
import com.soupthatisthick.encounterbuilder.view.cell.ReadCell;

import soupthatisthick.encounterapp.R;

/**
 * Created by Owner on 4/21/2017.
 * Copyright of Stuart Marr Erskine, all rights reserved.
 */

public class ItemListDetailCell extends ReadCell<ItemList> {

    private static final String NEW_LIST = "New List";
    private TextView theTextView;

    public ItemListDetailCell(LayoutInflater inflater, View convertView, ViewGroup parent) {
        super(inflater, convertView, parent);
    }

    @Override
    public void updateUi(ItemList itemList) {
        theTextView.setText(
            (itemList.getName()==null)
            ?   getView().getResources().getString(R.string.text_key_value, "ItemList", "" + itemList.getId())
            :   itemList.getName()
        );
    }

    @Override
    public View createView(LayoutInflater inflater, View convertView, ViewGroup parent) {
        View view = inflater.inflate(R.layout.cell_text_view, parent);
        theTextView = (TextView) view.findViewById(R.id.text_view);
        return view;
    }
}
