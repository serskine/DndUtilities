package com.soupthatisthick.encounterbuilder.view.cell.detail;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.soupthatisthick.encounterbuilder.model.lookup.Background;
import com.soupthatisthick.encounterbuilder.view.cell.ReadCell;

import soupthatisthick.encounterapp.R;

/**
 * Created by Owner on 5/8/2017.
 * Copyright of Stuart Marr Erskine, all rights reserved.
 */

public class BackgroundDetailCell extends ReadCell<Background> {

    private TextView theName, theDesc, theSkills, theEquipment, theTools, theFeatures, theLanguages;

    public BackgroundDetailCell(LayoutInflater inflater, View convertView, ViewGroup parent) {
        super(inflater, convertView, parent);
    }

    @Override
    public void updateUi(Background background) {
        // TODO: Change to use resource title strings
        theName.setText(background.getName());
        theSkills.setText(titleString("Skill Proficiencies: ", background.getSkills()));
        theLanguages.setText(titleString("Language Proficiencies: ", background.getLanguages()));
        theTools.setText(titleString("Tool Proficiencies: ", background.getTools()));
        theEquipment.setText(titleString("Equipment: ", background.getEquipment()));
        theDesc.setText(htmlString(background.getDescription()));
        theFeatures.setText(titleString("Feature: ", background.getFeatures()));
        checkVisibilities();
    }

    @Override
    public View createView(LayoutInflater inflater, View convertView, ViewGroup parent) {
        View view = inflater.inflate(R.layout.cell_background_detailed, parent);
        theName = (TextView) view.findViewById(R.id.theName);
        theSkills = (TextView) view.findViewById(R.id.theSkills);
        theTools = (TextView) view.findViewById(R.id.theTools);
        theLanguages = (TextView) view.findViewById(R.id.theLanguages);
        theEquipment = (TextView) view.findViewById(R.id.theEquipment);
        theFeatures = (TextView) view.findViewById(R.id.theFeatures);
        theDesc = (TextView) view.findViewById(R.id.theDescription);
        return view;
    }

    private void checkVisibilities() {
        theSkills.setVisibility(isEmpty(theSkills.getText()) ? View.GONE : View.VISIBLE);
        theTools.setVisibility(isEmpty(theTools.getText()) ? View.GONE : View.VISIBLE);
        theLanguages.setVisibility(isEmpty(theLanguages.getText()) ? View.GONE : View.VISIBLE);
        theEquipment.setVisibility(isEmpty(theEquipment.getText()) ? View.GONE : View.VISIBLE);
        theDesc.setVisibility(isEmpty(theDesc.getText()) ? View.GONE : View.VISIBLE);
        theFeatures.setVisibility(isEmpty(theFeatures.getText()) ? View.GONE : View.VISIBLE);
    }
}
