package com.soupthatisthick.encounterbuilder.view.cell.detail;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.soupthatisthick.encounterbuilder.model.lookup.Equipment;
import com.soupthatisthick.encounterbuilder.view.cell.ReadCell;

import soupthatisthick.encounterapp.R;

/**
 * Created by Owner on 5/25/2017.
 * Copyright of Stuart Marr Erskine, all rights reserved.
 */

public class EquipmentDetailCell extends ReadCell<Equipment> {
    private TextView theTitle, theCost, theWeight, theDescription, theType;
    public EquipmentDetailCell(LayoutInflater inflater, View convertView, ViewGroup parent) {
        super(inflater, convertView, parent);
    }

    @Override
    public void updateUi(Equipment equipment) {
        theTitle.setText(equipment.getName());
        theType.setText(titleString("Type: ", equipment.getType()));
        theCost.setText(titleString("Cost: ", equipment.getCost()));
        theWeight.setText(titleString("Weight: ", equipment.getWeight()));
        theDescription.setText(htmlString(equipment.getDescription()));
        checkVisibilities();
    }

    @Override
    public View createView(LayoutInflater inflater, View convertView, ViewGroup parent) {
        View view = inflater.inflate(R.layout.cell_equipment_detail, parent);
        theTitle = (TextView) view.findViewById(R.id.theTitle);
        theType = (TextView) view.findViewById(R.id.theType);
        theCost = (TextView) view.findViewById(R.id.theCost);
        theWeight = (TextView) view.findViewById(R.id.theWeight);
        theDescription = (TextView) view.findViewById(R.id.theDescription);
        return view;
    }

    private void checkVisibilities()
    {
        theCost.setVisibility(isEmpty(theCost.getText()) ? View.GONE : View.VISIBLE);
        theType.setVisibility(isEmpty(theType.getText()) ? View.GONE : View.VISIBLE);
        theWeight.setVisibility(isEmpty(theWeight.getText()) ? View.GONE : View.VISIBLE);
        theDescription.setVisibility(isEmpty(theDescription.getText()) ? View.GONE : View.VISIBLE);
    }
}
