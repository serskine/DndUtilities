package com.soupthatisthick.encounterbuilder.view.cell.summary;

import android.content.res.Resources;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.soupthatisthick.encounterbuilder.model.lookup.Item;
import com.soupthatisthick.encounterbuilder.view.cell.ReadCell;

import soupthatisthick.encounterapp.R;

/**
 * Created by Owner on 4/20/2017.
 * Copyright of Stuart Marr Erskine, all rights reserved.
 */

public class ItemErrorSummaryCell extends ReadCell<Pair<Item, Exception>> {

    TextView theItemId, theListId, theMessage;
    public ItemErrorSummaryCell(LayoutInflater inflater, View convertView, ViewGroup parent) {
        super(inflater, convertView, parent);

    }

    @Override
    public void updateUi(Pair<Item, Exception> itemExceptionPair) {
        Item item = itemExceptionPair.first;
        Exception exception = itemExceptionPair.second;

        Resources res = getView().getResources();
        theItemId.setText(res.getString(R.string.text_key_value, "Item Id", "" + item.getItemId()));
        theListId.setText(res.getString(R.string.text_key_value, "List Id", "" + item.getListId()));
        theMessage.setText(exception.getMessage());
    }


    @Override
    public View createView(LayoutInflater inflater, View convertView, ViewGroup parent) {
        View view = inflater.inflate(R.layout.cell_item_error_detail, parent);
        theItemId = (TextView) view.findViewById(R.id.theItemId);
        theListId = (TextView) view.findViewById(R.id.theListId);
        theMessage = (TextView) view.findViewById(R.id.theMessage);
        return view;
    }
}
