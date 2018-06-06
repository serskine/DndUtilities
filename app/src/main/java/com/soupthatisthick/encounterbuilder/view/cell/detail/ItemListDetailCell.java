package com.soupthatisthick.encounterbuilder.view.cell.detail;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.soupthatisthick.encounterbuilder.model.lookup.EntityList;
import com.soupthatisthick.encounterbuilder.view.cell.ReadCell;
import com.soupthatisthick.util.Logger;

import soupthatisthick.encounterapp.R;

/**
 * Created by Owner on 4/21/2017.
 * Copyright of Stuart Marr Erskine, all rights reserved.
 */

public class ItemListDetailCell extends ReadCell<EntityList> {

    private static final String NEW_LIST = "New List";
    private TextView theTitle;
    private TextView theContent;

    public ItemListDetailCell(LayoutInflater inflater, View convertView, ViewGroup parent) {
        super(inflater, convertView, parent);
    }

    @Override
    public void updateUi(EntityList entityList) {
        theTitle.setText(
            (entityList.getName()==null)
            ?   getView().getResources().getString(R.string.text_key_value, "EntityList", "" + entityList.getId())
            :   entityList.getName()
        );
        // TODO: Create a description for a list here.
        Logger.warning("Currently the detail of an item list doesn't exist.");
    }

    @Override
    public View createView(LayoutInflater inflater, View convertView, ViewGroup parent) {
        View view = inflater.inflate(R.layout.cell_item_list_detail, parent);
        theTitle = (TextView) view.findViewById(R.id.theTitle);
        theContent = (TextView) view.findViewById(R.id.theContent);
        theContent.setVisibility(View.GONE);
        Logger.warning("Setting the content for the ListItem detail view to be GONE");
        return view;
    }
}
