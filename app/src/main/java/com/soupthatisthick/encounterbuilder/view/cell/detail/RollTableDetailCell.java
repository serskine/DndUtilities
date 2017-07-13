package com.soupthatisthick.encounterbuilder.view.cell.detail;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.soupthatisthick.encounterbuilder.model.lookup.RollTable;
import com.soupthatisthick.encounterbuilder.view.cell.ReadCell;

import soupthatisthick.encounterapp.R;

/**
 * Created by Owner on 5/14/2017.
 * Copyright of Stuart Marr Erskine, all rights reserved.
 */

public class RollTableDetailCell extends ReadCell<RollTable> {
    private TextView theTitle, theDice;
    public RollTableDetailCell(LayoutInflater inflater, View convertView, ViewGroup parent) {
        super(inflater, convertView, parent);
    }

    @Override
    public void updateUi(RollTable rollTable) {
        theTitle.setText(rollTable.getName());
        theDice.setText(titleString("Roll: ", String.format("%dd%d", rollTable.getDieCount(), rollTable.getDieSize())));
    }

    @Override
    public View createView(LayoutInflater inflater, View convertView, ViewGroup parent) {
        View view = inflater.inflate(R.layout.cell_roll_table_detailed, parent);
        theTitle = (TextView) view.findViewById(R.id.theTitle);
        theDice = (TextView) view.findViewById(R.id.theDice);
        return view;
    }
}
