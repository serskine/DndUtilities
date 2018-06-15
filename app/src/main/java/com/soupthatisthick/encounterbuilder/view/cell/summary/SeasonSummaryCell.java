package com.soupthatisthick.encounterbuilder.view.cell.summary;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.soupthatisthick.encounterbuilder.model.lookup.Season;
import com.soupthatisthick.encounterbuilder.view.cell.ReadCell;

import soupthatisthick.encounterapp.R;

public class SeasonSummaryCell extends ReadCell<Season> {
    private TextView theTitle;

    public SeasonSummaryCell(LayoutInflater inflater, View convertView, ViewGroup parent) {
        super(inflater, convertView, parent);
    }
    @Override
    public void updateUi(Season season) {
        theTitle.setText(season.getTitle());
    }

    @Override
    public View createView(LayoutInflater inflater, View convertView, ViewGroup parent) {
        View view = inflater.inflate(R.layout.cell_season_summary, parent);
        theTitle = view.findViewById(R.id.theTitle);
        return view;
    }
}
