package com.soupthatisthick.encounterbuilder.view.cell.other;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.soupthatisthick.encounterbuilder.model.lookup.Rarity;
import com.soupthatisthick.encounterbuilder.view.cell.ReadCell;

import soupthatisthick.encounterapp.R;

/**
 * Created by Owner on 2/26/2017.
 * Copyright of Stuart Marr Erskine, all rights reserved.
 */

public class RarityCell extends ReadCell<Rarity> {
    private TextView rarityView;

    public RarityCell(LayoutInflater inflater, View convertView, ViewGroup parent) {
        super(inflater, convertView, parent);
    }

    @Override
    public void updateUi(Rarity rarity) {
        rarityView.setText(rarity.displayString);
    }

    @Override
    public View createView(LayoutInflater inflater, View convertView, ViewGroup parent) {
        View view = inflater.inflate(R.layout.cell_text_view, parent);
        rarityView = (TextView) view.findViewById(R.id.text_view);
        return view;
    }
}
