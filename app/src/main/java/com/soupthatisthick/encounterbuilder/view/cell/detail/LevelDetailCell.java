package com.soupthatisthick.encounterbuilder.view.cell.detail;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.soupthatisthick.encounterbuilder.model.lookup.Level;
import com.soupthatisthick.encounterbuilder.view.cell.ReadCell;

import soupthatisthick.encounterapp.R;

/**
 * Created by Owner on 12/22/2016.
 * Copyright of Stuart Marr Erskine, all rights reserved.
 */

public class LevelDetailCell extends ReadCell<Level> {

    private TextView theTitle;
    private TextView easy;
    private TextView medium;
    private TextView hard;
    private TextView deadly;
    private TextView proficiency;
    private TextView tier;



    public LevelDetailCell(LayoutInflater inflater, View convertView, ViewGroup parent) {
        super(inflater, convertView, parent);
    }

    @Override
    public View createView(LayoutInflater inflater, View convertView, ViewGroup parent) {
        View view = inflater.inflate(R.layout.cell_level_detail, parent);

        theTitle = (TextView) view.findViewById(R.id.titleText);
        easy = (TextView) view.findViewById(R.id.easyText);
        medium = (TextView) view.findViewById(R.id.normalText);
        hard = (TextView) view.findViewById(R.id.hardText);
        deadly = (TextView) view.findViewById(R.id.deadlyText);
        proficiency = (TextView) view.findViewById(R.id.profText);
        tier = (TextView) view.findViewById(R.id.tierText);

        return view;
    }

    @Override
    public void updateUi(Level lvl)
    {
        if (lvl==null)
        {
            theTitle.setText(String.format("Level %s (%s xp)", VISIBLE_UNKNOWN, VISIBLE_UNKNOWN));

            easy.setText(titleString("Easy: ", VISIBLE_UNKNOWN));
            medium.setText(titleString("Normal: ", VISIBLE_UNKNOWN));
            hard.setText(titleString("Hard: ", VISIBLE_UNKNOWN));
            deadly.setText(titleString("Deadly: ", VISIBLE_UNKNOWN));
            proficiency.setText(titleString("Proficiency: ", VISIBLE_UNKNOWN));
            tier.setText(titleString("Tier: ", VISIBLE_UNKNOWN));
        } else {
            String titleText = String.format("%s (%s xp)", lvl.getName(), lvl.getXp());
            theTitle.setText(titleText);

            easy.setText(titleString("Easy: ", "" + lvl.getEasy()));
            medium.setText(titleString("Normal: ", "" + lvl.getNormal()));
            hard.setText(titleString("Hard: ", "" + lvl.getHard()));
            deadly.setText(titleString("Deadly: ", "" + lvl.getDeadly()));
            proficiency.setText(titleString("Proficiency: ", "" + lvl.getProficiency()));
            tier.setText(titleString("Tier: ", "" + lvl.getTier()));
        }
    }
}
