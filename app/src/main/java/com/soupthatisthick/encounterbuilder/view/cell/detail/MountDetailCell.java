package com.soupthatisthick.encounterbuilder.view.cell.detail;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.soupthatisthick.encounterbuilder.model.lookup.Mount;
import com.soupthatisthick.encounterbuilder.view.cell.ReadCell;

import soupthatisthick.encounterapp.R;

/**
 * Created by Owner on 5/25/2017.
 * Copyright of Stuart Marr Erskine, all rights reserved.
 */

public class MountDetailCell extends ReadCell<Mount> {

    private TextView theName, theCarry, theSpeed, theCost, theDesc;

    public MountDetailCell(LayoutInflater inflater, View convertView, ViewGroup parent) {
        super(inflater, convertView, parent);
    }

    @Override
    public void updateUi(Mount mount) {
        theName.setText(mount.getName());
        theCarry.setText(titleString("Carry: ", mount.getCarry()));
        theCost.setText(titleString("Cost: ", mount.getCost()));
        theSpeed.setText(titleString("Name: ", mount.getSpeed()));
        theDesc.setText(htmlString(mount.getDescription()));
    }

    @Override
    public View createView(LayoutInflater inflater, View convertView, ViewGroup parent) {
        View view = inflater.inflate(R.layout.cell_mount_detail, parent);
        theName = (TextView) view.findViewById(R.id.theName);
        theCarry = (TextView) view.findViewById(R.id.theCarry);
        theCost = (TextView) view.findViewById(R.id.theCost);
        theSpeed = (TextView) view.findViewById(R.id.theSpeed);
        theDesc = (TextView) view.findViewById(R.id.theDescription);
        return view;
    }
}
