package com.soupthatisthick.encounterbuilder.view.cell.summary;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.soupthatisthick.encounterbuilder.model.lookup.Armor;
import com.soupthatisthick.encounterbuilder.view.cell.ReadCell;

import soupthatisthick.encounterapp.R;

/**
 * Created by Owner on 5/25/2017.
 * Copyright of Stuart Marr Erskine, all rights reserved.
 */

public class ArmorSummaryCell extends ReadCell<Armor> {
    private TextView theName, theAc, theCost, theType, theStrength, theStealth, theWeight, theDesc;

    public ArmorSummaryCell(LayoutInflater inflater, View convertView, ViewGroup parent) {
        super(inflater, convertView, parent);
    }

    @Override
    public void updateUi(Armor armor) {
        theName.setText(armor.getName());
    }

    @Override
    public View createView(LayoutInflater inflater, View convertView, ViewGroup parent) {
        View view = inflater.inflate(R.layout.cell_armor_summary, parent);

        theName = (TextView) view.findViewById(R.id.theName);

        return view;
    }
}
