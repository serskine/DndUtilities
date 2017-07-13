package com.soupthatisthick.encounterbuilder.view.cell.detail;

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

public class SpellDetailCell extends ReadCell<Spell> {

    public TextView theName, theType, theClasses, theCastingTime, theRange, theComponents, theDuration, theMaterials, theDescription;

    public SpellDetailCell(LayoutInflater inflater, View convertView, ViewGroup parent) {
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
        theClasses = (TextView) view.findViewById(R.id.theClasses);

        return view;
    }

    public void updateUi(Spell spell)
    {

        theName.setText(spell.getName());

        theCastingTime.setText(htmlString("<br><b>Casting Time: </b>" + spell.getCastingTime()));
        theRange.setText(htmlString("<b>Range: </b>" + spell.getRange()));

        String uiComponents = spell.getComponents();
        if (spell.getMaterials()!=null && spell.getMaterials().length()>0)
        {
            uiComponents += " (" + spell.getMaterials() + ")";
        }

        theComponents.setText(htmlString("<b>Components: </b>" + uiComponents));

        theDuration.setText(htmlString("<b>Duration: </b>" + spell.getDuration()));

        theDescription.setText(htmlString("<br> " + spell.getDescription()));
        theType.setText(htmlString(spell.getType()));
        theClasses.setText(htmlString(spell.getClasses()));
    }
}
