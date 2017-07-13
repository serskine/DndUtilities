package com.soupthatisthick.encounterbuilder.view.cell.detail;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.soupthatisthick.encounterbuilder.model.builder.EncounterStats;
import com.soupthatisthick.encounterbuilder.view.cell.ReadCell;

/**
 * Created by Owner on 5/29/2017.
 * Copyright of Stuart Marr Erskine, all rights reserved.
 */

public class EncounterStatsDetailCell extends ReadCell<EncounterStats> {
    public EncounterStatsDetailCell(LayoutInflater inflater, View convertView, ViewGroup parent) {
        super(inflater, convertView, parent);
    }

    @Override
    public void updateUi(EncounterStats encounterStats) {

    }

    @Override
    public View createView(LayoutInflater inflater, View convertView, ViewGroup parent) {
        return null;
    }
}
