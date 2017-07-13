package com.soupthatisthick.encounterbuilder.view.cell.summary;

import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.soupthatisthick.encounterbuilder.model.lookup.Faction;
import com.soupthatisthick.encounterbuilder.model.lookup.Pc;
import com.soupthatisthick.encounterbuilder.util.translater.FactionTranslater;
import com.soupthatisthick.encounterbuilder.view.cell.ReadCell;

import soupthatisthick.encounterapp.R;

/**
 * Created by Owner on 2/10/2017.
 * Copyright of Stuart Marr Erskine, all rights reserved.
 */

public class PcCell extends ReadCell<Pc> {

    public TextView thePlayerName, thePlayerDci, theCharacterName, theClassAndLevels;
    public ImageView theFaction;


    public PcCell(LayoutInflater inflater, View convertView, ViewGroup parent) {
        super(inflater, convertView, parent);
    }

    @Override
    public View createView(LayoutInflater inflater, View convertView, ViewGroup parent) {
        View view = inflater.inflate(R.layout.cell_pc, parent);

        thePlayerName = (TextView) view.findViewById(R.id.player_name);
        thePlayerDci = (TextView) view.findViewById(R.id.player_dci);
        theCharacterName = (TextView) view.findViewById(R.id.character_name);
        theClassAndLevels = (TextView) view.findViewById(R.id.character_class);
        theFaction = (ImageView) view.findViewById(R.id.faction);

        return view;
    }

    public void updateUi(Pc pc)
    {
        thePlayerName.setText(htmlString(pc.getPlayerName()));
        thePlayerDci.setText(htmlString(pc.getPlayerDci()));
        theCharacterName.setText(htmlString(pc.getCharacterName()));
        theClassAndLevels.setText(htmlString(pc.getClassAndLevels()));

        Faction faction = FactionTranslater.INSTANCE.toFaction(pc.getFaction());
        Drawable imageDrawable = getView().getResources().getDrawable(faction.drawable);
        theFaction.setImageDrawable(imageDrawable);
    }

}
