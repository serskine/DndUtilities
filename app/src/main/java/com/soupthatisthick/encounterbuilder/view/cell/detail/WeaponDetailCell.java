package com.soupthatisthick.encounterbuilder.view.cell.detail;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.soupthatisthick.encounterbuilder.model.lookup.Weapon;
import com.soupthatisthick.encounterbuilder.view.cell.ReadCell;

import soupthatisthick.encounterapp.R;

/**
 * Created by Owner on 5/25/2017.
 * Copyright of Stuart Marr Erskine, all rights reserved.
 */

public class WeaponDetailCell extends ReadCell<Weapon> {

    private TextView theName, theCost, theDamage, theWeight, theProperties, theType, theDescription;

    public WeaponDetailCell(LayoutInflater inflater, View convertView, ViewGroup parent) {
        super(inflater, convertView, parent);
    }

    @Override
    public void updateUi(Weapon weapon) {
        theName.setText(weapon.getName());
        theCost.setText(titleString("Cost: " , weapon.getCost()));
        theDamage.setText(titleString("Damage: ", weapon.getDamage()));
        theWeight.setText(titleString("Weight: ", weapon.getWeight()));
        theProperties.setText(titleString("Properties: ", weapon.getProperties()));
        theType.setText(titleString("Type: ", weapon.getType()));
        theDescription.setText(htmlString(weapon.getDescription()));

        checkVisibilities();
    }

    @Override
    public View createView(LayoutInflater inflater, View convertView, ViewGroup parent) {
        View view = inflater.inflate(R.layout.cell_weapon_detail, parent);
        theName = (TextView) view.findViewById(R.id.theName);
        theCost = (TextView) view.findViewById(R.id.theCost);
        theDamage = (TextView) view.findViewById(R.id.theDamage);
        theWeight = (TextView) view.findViewById(R.id.theWeight);
        theProperties = (TextView) view.findViewById(R.id.theProperties);
        theType = (TextView) view.findViewById(R.id.theType);
        theDescription = (TextView) view.findViewById(R.id.theDescription);
        return view;
    }

    private void checkVisibilities()
    {
        theCost.setVisibility(isEmpty(theCost.getText()) ? View.GONE : View.VISIBLE);
        theDamage.setVisibility(isEmpty(theDamage.getText()) ? View.GONE : View.VISIBLE);
        theWeight.setVisibility(isEmpty(theWeight.getText()) ? View.GONE : View.VISIBLE);
        theProperties.setVisibility(isEmpty(theProperties.getText()) ? View.GONE : View.VISIBLE);
        theType.setVisibility(isEmpty(theType.getText()) ? View.GONE : View.VISIBLE);
        theDescription.setVisibility(isEmpty(theDescription.getText()) ? View.GONE : View.VISIBLE);
    }
}
