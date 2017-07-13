package com.soupthatisthick.encounterbuilder.view.cell.summary;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.soupthatisthick.encounterbuilder.model.lookup.Level;
import com.soupthatisthick.encounterbuilder.view.cell.ReadCell;

import soupthatisthick.encounterapp.R;

/**
 * Created by Owner on 5/8/2017.
 * Copyright of Stuart Marr Erskine, all rights reserved.
 */

public class LevelSummaryCell extends ReadCell<Level> {

    private TextView theTitle;

    public LevelSummaryCell(LayoutInflater inflater, View convertView, ViewGroup parent) {
        super(inflater, convertView, parent);
    }

    @Override
    public View createView(LayoutInflater inflater, View convertView, ViewGroup parent) {
        View view = inflater.inflate(R.layout.cell_level_summary, parent);
        theTitle = (TextView) view.findViewById(R.id.titleText);

        return view;
    }

    @Override
    public void updateUi(Level lvl)
    {
        if (lvl==null)
        {
            theTitle.setText(String.format("Level %s (%s xp)", VISIBLE_UNKNOWN, VISIBLE_UNKNOWN));
        } else {
            String titleText = String.format("%s (%s xp)", lvl.getName(), lvl.getXp());
            theTitle.setText(titleText);
        }
    }
}
