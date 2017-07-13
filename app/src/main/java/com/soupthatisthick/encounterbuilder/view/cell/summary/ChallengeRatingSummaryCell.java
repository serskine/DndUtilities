package com.soupthatisthick.encounterbuilder.view.cell.summary;

import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.soupthatisthick.encounterbuilder.logic.Challenge;
import com.soupthatisthick.encounterbuilder.model.lookup.ChallengeRating;
import com.soupthatisthick.encounterbuilder.view.cell.ReadCell;

import soupthatisthick.encounterapp.R;

/**
 * Created by Owner on 12/22/2016.
 * Copyright of Stuart Marr Erskine, all rights reserved.
 */

public class ChallengeRatingSummaryCell extends ReadCell<ChallengeRating> {

    TextView titleText;

    public ChallengeRatingSummaryCell(LayoutInflater inflater, View convertView, ViewGroup parent) {
        super(inflater, convertView, parent);
    }

    @Override
    public View createView(LayoutInflater inflater, View convertView, ViewGroup parent) {
        View view = inflater.inflate(R.layout.cell_challenge_summary, parent);
        titleText = (TextView) view.findViewById(R.id.titleText);
        return view;
    }

    public void updateUi(@Nullable ChallengeRating cr)
    {
        if (cr!=null)
        {
            titleText.setText(String.format("%s (%d xp)", cr.getName(), cr.getXp()));
        } else {
            titleText.setText(String.format("%s (unknown xp)", UNKNOWN));
        }
    }

    public void updateUi(@Nullable Challenge cr)
    {
        if (cr!=null)
        {
            titleText.setText(String.format("%s (%d xp)", cr.text, cr.xp));
        } else {
            titleText.setText(String.format("%s (unknown xp)", UNKNOWN));
        }
    }
}
