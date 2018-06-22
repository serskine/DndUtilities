package com.soupthatisthick.encounterbuilder.view.cell.summary;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.soupthatisthick.encounterbuilder.model.lookup.Adventure;
import com.soupthatisthick.encounterbuilder.util.Text;
import com.soupthatisthick.encounterbuilder.view.cell.ReadCell;
import com.soupthatisthick.util.view.HtmlView;

import soupthatisthick.encounterapp.R;

public class AdventureSummaryCell extends ReadCell<Adventure> {

    private TextView theTitle, theLevelBand, theRuntimeHours;

    public AdventureSummaryCell(LayoutInflater inflater, View convertView, ViewGroup parent) {
        super(inflater, convertView, parent);
    }

    @Override
    public void updateUi(Adventure adventure) {
        theTitle.setText(adventure.getCode() + " " + adventure.getTitle());
        theLevelBand.setText(titleString("Level range: ", adventure.getLevelBand()));
        theRuntimeHours.setText(titleString("Runtime Hours: ", adventure.getRuntimeHours()));
    }

    @Override
    public View createView(LayoutInflater inflater, View convertView, ViewGroup parent) {
        View view = inflater.inflate(R.layout.cell_adventure_summary, parent);
        theTitle = view.findViewById(R.id.theTitle);
        theLevelBand = view.findViewById(R.id.theLevelBand);
        theRuntimeHours = view.findViewById(R.id.theRuntimeHours);
        return view;
    }
}
