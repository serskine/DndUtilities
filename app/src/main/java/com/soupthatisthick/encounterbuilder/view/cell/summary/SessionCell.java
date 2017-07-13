package com.soupthatisthick.encounterbuilder.view.cell.summary;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.soupthatisthick.encounterbuilder.model.lookup.Session;
import com.soupthatisthick.encounterbuilder.util.translater.DateTranslater;
import com.soupthatisthick.encounterbuilder.view.cell.ReadCell;

import soupthatisthick.encounterapp.R;

/**
 * Created by Owner on 2/5/2017.
 * Copyright of Stuart Marr Erskine, all rights reserved.
 */

public class SessionCell extends ReadCell<Session>
{

    private TextView theAdventure, theDm, theDmDci, theDate;

    public SessionCell(LayoutInflater inflater, View convertView, ViewGroup parent) {
        super(inflater, convertView, parent);
    }

    @Override
    public View createView(LayoutInflater inflater, View convertView, ViewGroup parent) {
        View view = inflater.inflate(R.layout.cell_session, parent);

        theAdventure = (TextView) view.findViewById(R.id.adventureName);
        theDm = (TextView) view.findViewById(R.id.dm);
        theDmDci = (TextView) view.findViewById(R.id.dmDci);
        theDate = (TextView) view.findViewById(R.id.datePlayed);

        return view;
    }


    @Override
    public void updateUi(Session theSession)
    {
        if (theSession!=null) {
            theAdventure.setText(theSession.getAdventure());
            theDm.setText(theSession.getDmName());
            theDmDci.setText(theSession.getDmDci());
            theDate.setText(DateTranslater.translate(theSession.getDate()));
        } else {
            theAdventure.setText(R.string.es_adventure);
            theDm.setText(R.string.es_dm);
            theDmDci.setText(R.string.es_dci);
            theDate.setText(R.string.es_date_pattern);
        }
    }
}
