package com.soupthatisthick.encounterbuilder.view.cell.summary;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.soupthatisthick.encounterbuilder.model.lookup.Spell;
import com.soupthatisthick.encounterbuilder.view.cell.ReadCell;

import soupthatisthick.encounterapp.R;

/**
 * Created by Owner on 1/1/2017.
 * Copyright of Stuart Marr Erskine, all rights reserved.
 */

public class SpellCardCell extends ReadCell<Spell> {

    public TextView theName, theCastingTime, theRange, theComponents, theDuration, theMaterials, theDescription, theType, theClass;

    public SpellCardCell(LayoutInflater inflater, View convertView, ViewGroup parent) {
        super(inflater, convertView, parent);
    }

    @Override
    public View createView(LayoutInflater inflater, View convertView, ViewGroup parent) {
        View view = inflater.inflate(R.layout.cell_spell_detail, parent);

        theName = (TextView) view.findViewById(R.id.theName);
        theCastingTime = (TextView) view.findViewById(R.id.theCastingTime);
        theRange = (TextView) view.findViewById(R.id.theRange);
        theComponents = (TextView) view.findViewById(R.id.theComponents);
        theDuration = (TextView) view.findViewById(R.id.theDuration);
        theMaterials = (TextView) view.findViewById(R.id.theMaterials);
        theDescription = (TextView) view.findViewById(R.id.theDescription);
        theType = (TextView) view.findViewById(R.id.theType);
        theClass = (TextView) view.findViewById(R.id.theClass);

        return view;
    }

    public void updateUi(Spell spell)
    {
        theName.setText(spell.getName());

        theCastingTime.setText(spell.getCastingTime());
        theRange.setText(spell.getRange());

        String uiComponents = spell.getComponents();
        if (spell.getMaterials()!=null && spell.getMaterials().length()>0)
        {
            uiComponents += " (" + spell.getMaterials() + ")";
        }

        theComponents.setText(uiComponents);
        theDuration.setText(spell.getDuration());

        theMaterials.setText("");   // TODO: Figure out what to put here from the database information

        theDescription.setText(spell.getDescription());
        theType.setText(spell.getType());
//        theClass.setItem(spell.getClassList());

    }
}
