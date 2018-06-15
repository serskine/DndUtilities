package com.soupthatisthick.encounterbuilder.view.cell.detail;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.soupthatisthick.encounterbuilder.model.lookup.Season;
import com.soupthatisthick.encounterbuilder.view.cell.ReadCell;

import soupthatisthick.encounterapp.R;

public class SeasonDetailCell extends ReadCell<Season> {
    TextView theTitle, theContent;

    public SeasonDetailCell(LayoutInflater inflater, View convertView, ViewGroup parent) {
        super(inflater, convertView, parent);
    }

    @Override
    public void updateUi(Season season) {
        theTitle.setText(season.getTitle());
        theContent.setText(R.string.test_string);   // TODO: change later when we add descriptions to seasons
    }

    @Override
    public View createView(LayoutInflater inflater, View convertView, ViewGroup parent) {
        View view = inflater.inflate(R.layout.cell_season_detail, parent);
        theTitle = view.findViewById(R.id.theTitle);
        theContent = view.findViewById(R.id.theContent);
        theContent.setVisibility(View.GONE);    // TODO: Make visible later
        return view;
    }
}
