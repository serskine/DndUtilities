package com.soupthatisthick.encounterbuilder.view.cell.other;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.soupthatisthick.encounterbuilder.model.lookup.Faction;
import com.soupthatisthick.encounterbuilder.view.cell.ReadCell;

import soupthatisthick.encounterapp.R;

/**
 * Created by Owner on 2/7/2017.
 * Copyright of Stuart Marr Erskine, all rights reserved.
 */

public class FactionCell extends ReadCell<Faction> {

    private ImageView factionView;

    public FactionCell(LayoutInflater inflater, View convertView, ViewGroup parent) {
        super(inflater, convertView, parent);
    }

    @Override
    public View createView(LayoutInflater inflater, View convertView, ViewGroup parent) {
        View view = inflater.inflate(R.layout.cell_faction, parent);
        factionView = (ImageView) view.findViewById(R.id.faction_view);
        updateUi(Faction.NONE);
        return view;
    }

    public void updateUi(Faction faction) {
        factionView.setImageResource(faction.drawable);
    }
}