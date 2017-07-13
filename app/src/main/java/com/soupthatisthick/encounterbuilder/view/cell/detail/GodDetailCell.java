package com.soupthatisthick.encounterbuilder.view.cell.detail;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.soupthatisthick.encounterbuilder.model.lookup.Equipment;
import com.soupthatisthick.encounterbuilder.model.lookup.God;
import com.soupthatisthick.encounterbuilder.view.cell.ReadCell;

import soupthatisthick.encounterapp.R;

import static soupthatisthick.encounterapp.R.id.theType;
import static soupthatisthick.encounterapp.R.id.title;

/**
 * Created by Owner on 5/25/2017.
 * Copyright of Stuart Marr Erskine, all rights reserved.
 */

public class GodDetailCell extends ReadCell<God> {

    TextView theTitle, theAlignment, theDomains, theSymbol, theSource, theNotes;

    public GodDetailCell(LayoutInflater inflater, View convertView, ViewGroup parent) {
        super(inflater, convertView, parent);
    }

    @Override
    public void updateUi(God god) {
        theTitle.setText(god.getName());

        theAlignment.setText(titleString("Alignment: ", god.getAlignment()));
        theDomains.setText(titleString("Domains: ", god.getDomains()));
        theSymbol.setText(titleString("Symbol: ", god.getSymbol()));
        theSource.setText(titleString("Source: ", god.getSource()));
        theNotes.setText(titleString("Notes: ", god.getNotes()));
        checkVisibilities();
    }

    @Override
    public View createView(LayoutInflater inflater, View convertView, ViewGroup parent) {
        View view = inflater.inflate(R.layout.cell_god_detail, parent);
        theTitle = (TextView) view.findViewById(R.id.theTitle);
        theAlignment = (TextView) view.findViewById(R.id.theAlignment);
        theDomains = (TextView) view.findViewById(R.id.theDomains);
        theSymbol = (TextView) view.findViewById(R.id.theSymbol);
        theSource = (TextView) view.findViewById(R.id.theSource);
        theNotes = (TextView) view.findViewById(R.id.theNotes);
        return view;
    }

    private void checkVisibilities()
    {
        theAlignment.setVisibility(isEmpty(theAlignment.getText()) ? View.GONE : View.VISIBLE);
        theDomains.setVisibility(isEmpty(theDomains.getText()) ? View.GONE : View.VISIBLE);
        theSymbol.setVisibility(isEmpty(theSymbol.getText()) ? View.GONE : View.VISIBLE);
        theSource.setVisibility(isEmpty(theSource.getText()) ? View.GONE : View.VISIBLE);
        theNotes.setVisibility(isEmpty(theNotes.getText()) ? View.GONE : View.VISIBLE);
    }

}
