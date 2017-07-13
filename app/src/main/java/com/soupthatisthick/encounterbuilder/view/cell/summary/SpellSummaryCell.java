package com.soupthatisthick.encounterbuilder.view.cell.summary;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.soupthatisthick.encounterbuilder.model.lookup.Spell;
import com.soupthatisthick.encounterbuilder.view.cell.ReadCell;

import soupthatisthick.encounterapp.R;

/**
 * Created by Owner on 4/12/2017.
 * Copyright of Stuart Marr Erskine, all rights reserved.
 */

public class SpellSummaryCell extends ReadCell<Spell> {

    public TextView theName, theType, theClasses;

    public SpellSummaryCell(LayoutInflater inflater, View convertView, ViewGroup parent) {
        super(inflater, convertView, parent);
    }

    @Override
    public View createView(LayoutInflater inflater, View convertView, ViewGroup parent) {
        View view = inflater.inflate(R.layout.cell_spell_summary, parent);

        theName = (TextView) view.findViewById(R.id.theName);
        theType = (TextView) view.findViewById(R.id.theType);
        theClasses = (TextView) view.findViewById(R.id.theClasses);

        return view;
    }

    public void updateUi(Spell spell)
    {

        theName.setText(spell.getName());
        theType.setText(htmlString(spell.getType()));
        theClasses.setText(htmlString(spell.getClasses()));
    }
}
