package com.soupthatisthick.encounterbuilder.view.cell.detail;

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

public class ArmorDetailCell extends ReadCell<Armor> {
    private TextView theName, theAc, theCost, theType, theStrength, theStealth, theWeight, theDesc;

    public ArmorDetailCell(LayoutInflater inflater, View convertView, ViewGroup parent) {
        super(inflater, convertView, parent);
    }

    @Override
    public void updateUi(Armor armor) {
        theName.setText(armor.getName());
        theType.setText(titleString("Type: ", armor.getType()));
        theAc.setText(titleString("Armor Class: ", armor.getAc()));
        theCost.setText(titleString("Cost: ", armor.getCost()));
        theStrength.setText(titleString("Strength Requirement: ", armor.getStrengthRequirement()));
        theStealth.setText(titleString("Stealth Effect: ", armor.getStealthEffect()));
        theWeight.setText(titleString("Weight: ", armor.getWeight()));
        theDesc.setText(htmlString(armor.getDescription()));

        checkVisibilities();
    }

    @Override
    public View createView(LayoutInflater inflater, View convertView, ViewGroup parent) {
        View view = inflater.inflate(R.layout.cell_armor_detail, parent);

        theName = (TextView) view.findViewById(R.id.theName);
        theAc = (TextView) view.findViewById(R.id.theAc);
        theCost = (TextView) view.findViewById(R.id.theCost);
        theType = (TextView) view.findViewById(R.id.theType);
        theStrength = (TextView) view.findViewById(R.id.theStrengthRequirement);
        theStealth = (TextView) view.findViewById(R.id.theStealth);
        theWeight = (TextView) view.findViewById(R.id.theWeight);
        theDesc = (TextView) view.findViewById(R.id.theDescription);

        return view;
    }

    private void checkVisibilities()
    {
        theStealth.setVisibility(isEmpty(theStealth.getText()) ? View.GONE : View.VISIBLE);
        theStrength.setVisibility(isEmpty(theStrength.getText()) ? View.GONE : View.VISIBLE);
    }
}
