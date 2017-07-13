package com.soupthatisthick.encounterbuilder.view.cell.summary;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.soupthatisthick.encounterbuilder.view.cell.AbstractCell;
import com.soupthatisthick.encounterbuilder.view.cell.ReadCell;

import soupthatisthick.encounterapp.R;

/**
 * Created by Owner on 2/20/2017.
 * Copyright of Stuart Marr Erskine, all rights reserved.
 */

public class DefaultCompendiumCell extends ReadCell<Object> {

    private TextView itemDescription;
    private ImageView itemImage;

    public DefaultCompendiumCell(LayoutInflater inflater, View convertView, ViewGroup parent) {
        super(inflater, convertView, parent);
    }

    @Override
    public View createView(LayoutInflater inflater, View convertView, ViewGroup parent) {
        View view = inflater.inflate(R.layout.cell_compendium_default, parent);
        itemDescription = (TextView) view.findViewById(R.id.item_description);
        itemImage = (ImageView) view.findViewById(R.id.item_image);
        return view;
    }

    public void updateUi(Object item)
    {
        itemImage.setVisibility(View.GONE);
        itemDescription.setText(item.toString());
    }
}
