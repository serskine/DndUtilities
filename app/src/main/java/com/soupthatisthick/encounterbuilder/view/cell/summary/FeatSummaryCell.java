package com.soupthatisthick.encounterbuilder.view.cell.summary;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.soupthatisthick.encounterbuilder.model.lookup.Feat;
import com.soupthatisthick.encounterbuilder.view.cell.ReadCell;

import soupthatisthick.encounterapp.R;

/**
 * Created by Owner on 5/5/2017.
 * Copyright of Stuart Marr Erskine, all rights reserved.
 */

public class FeatSummaryCell extends ReadCell<Feat> {

    private TextView theName, thePrerequisite;

    public FeatSummaryCell(LayoutInflater inflater, View convertView, ViewGroup parent) {
        super(inflater, convertView, parent);
    }

    @Override
    public void updateUi(Feat feat) {
        theName.setText(feat.getName());
        thePrerequisite.setText(feat.getPrerequisite());
        checkVisibilities();
    }

    @Override
    public View createView(LayoutInflater inflater, View convertView, ViewGroup parent) {
        View view = inflater.inflate(R.layout.cell_feat_summary, parent);

        theName = (TextView) view.findViewById(R.id.theName);
        thePrerequisite = (TextView) view.findViewById(R.id.thePrerequisite);
        checkVisibilities();
        return view;
    }

    private void checkVisibilities()
    {
        thePrerequisite.setVisibility((isEmpty(thePrerequisite.getText()) ? View.GONE : View.VISIBLE));
    }
}
