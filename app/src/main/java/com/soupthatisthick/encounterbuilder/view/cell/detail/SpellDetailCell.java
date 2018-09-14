package com.soupthatisthick.encounterbuilder.view.cell.detail;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.soupthatisthick.encounterbuilder.model.lookup.Spell;
import com.soupthatisthick.encounterbuilder.view.cell.ReadCell;

import com.android.xamoom.htmltextview.HtmlTextView;

import soupthatisthick.encounterapp.R;

/**
 * Created by Owner on 1/1/2017.
 * Copyright of Stuart Marr Erskine, all rights reserved.
 */

public class SpellDetailCell extends ReadCell<Spell> {

    public TextView theName, theType, theClasses, theCastingTime, theRange, theComponents, theDuration, theMaterials;
//    public TextView theDescription;
    public HtmlTextView theDescription;

    public SpellDetailCell(LayoutInflater inflater, View convertView, ViewGroup parent) {
        super(inflater, convertView, parent);
    }

    @Override
    public View createView(LayoutInflater inflater, View convertView, ViewGroup parent) {
        View view = inflater.inflate(R.layout.cell_spell_detail, parent);

        theName = view.findViewById(R.id.theName);
        theCastingTime = view.findViewById(R.id.theCastingTime);
        theRange = view.findViewById(R.id.theRange);
        theComponents = view.findViewById(R.id.theComponents);
        theDuration = view.findViewById(R.id.theDuration);
        theMaterials = view.findViewById(R.id.theMaterials);
        theDescription = view.findViewById(R.id.theDescription);
        theType = view.findViewById(R.id.theType);
        theClasses = view.findViewById(R.id.theClasses);

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

        theDescription.setHtmlText(spell.getDescription(), getView().getWidth());
        theType.setText(htmlString(spell.getType()));
        theClasses.setText(htmlString(spell.getClasses()));
    }
}
