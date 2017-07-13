package com.soupthatisthick.encounterbuilder.view.cell.detail;

import android.content.res.Resources;
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

public class ChallengeRatingDetailCell extends ReadCell<ChallengeRating> {

    private TextView titleText, valueText, maxAcText, minHpText, maxHpText, minDmgText, maxDmgText, profText, saveDcText;

    public ChallengeRatingDetailCell(LayoutInflater inflater, View convertView, ViewGroup parent) {
        super(inflater, convertView, parent);
    }

    @Override
    public View createView(LayoutInflater inflater, View convertView, ViewGroup parent) {
        View view = inflater.inflate(R.layout.cell_challenge_detailed, parent);
        titleText = (TextView) view.findViewById(R.id.titleText);

        valueText = (TextView) view.findViewById(R.id.valueText);
        maxAcText = (TextView) view.findViewById(R.id.maxAcText);
        minHpText = (TextView) view.findViewById(R.id.minHpText);
        maxHpText = (TextView) view.findViewById(R.id.maxHpText);
        minDmgText = (TextView) view.findViewById(R.id.minDmgText);
        maxDmgText = (TextView) view.findViewById(R.id.maxDmgText);
        profText = (TextView) view.findViewById(R.id.profText);
        saveDcText = (TextView) view.findViewById(R.id.saveDcText);
        valueText = (TextView) view.findViewById(R.id.valueText);
        valueText = (TextView) view.findViewById(R.id.valueText);

        return view;
    }

    public void updateUi(@Nullable ChallengeRating cr)
    {
        Resources res = getView().getResources();
        if (cr!=null)
        {
            titleText.setText(String.format("%s (%s xp)", cr.getName(), "" + cr.getXp()));

            valueText.setText(titleString(res.getString(R.string.cell_cr_value), String.format("%.2f", cr.getValue())));
            profText.setText(titleString(res.getString(R.string.cell_cr_prof), "" + cr.getProficiency()));
            maxAcText.setText(titleString(res.getString(R.string.cell_cr_max_ac), "" + cr.getMaxAc()));
            saveDcText.setText(titleString(res.getString(R.string.cell_cr_save_dc), "" + cr.getSaveDc()));

            minHpText.setText(titleString(res.getString(R.string.cell_cr_min_hp), "" + cr.getMinHp()));
            maxHpText.setText(titleString(res.getString(R.string.cell_cr_max_hp), "" + cr.getMaxHp()));
            minDmgText.setText(titleString(res.getString(R.string.cell_cr_min_dmg), "" + cr.getMinDmg()));
            maxDmgText.setText(titleString(res.getString(R.string.cell_cr_max_dmg), "" + cr.getMaxDmg()));
        } else {
            titleText.setText(String.format("%s (" + UNKNOWN + " xp)", UNKNOWN));

            valueText.setText(titleString(res.getString(R.string.cell_cr_value), UNKNOWN));
            profText.setText(titleString(res.getString(R.string.cell_cr_prof), UNKNOWN));
            maxAcText.setText(titleString(res.getString(R.string.cell_cr_max_ac), UNKNOWN));
            saveDcText.setText(titleString(res.getString(R.string.cell_cr_save_dc), UNKNOWN));

            minHpText.setText(titleString(res.getString(R.string.cell_cr_min_hp), UNKNOWN));
            maxHpText.setText(titleString(res.getString(R.string.cell_cr_max_hp), UNKNOWN));
            minDmgText.setText(titleString(res.getString(R.string.cell_cr_min_dmg), UNKNOWN));
            maxDmgText.setText(titleString(res.getString(R.string.cell_cr_max_dmg), UNKNOWN));
        }
    }

    public void updateUi(@Nullable Challenge cr)
    {
        Resources res = getView().getResources();
        if (cr!=null)
        {
            titleText.setText(String.format("%s (%s xp)", cr.text, "" + cr.xp));

            valueText.setText(titleString(res.getString(R.string.cell_cr_value), String.format("%.2f", cr.value)));
            profText.setText(titleString(res.getString(R.string.cell_cr_prof), "" + cr.prof));
            maxAcText.setText(titleString(res.getString(R.string.cell_cr_max_ac), "" + cr.maxAc));
            saveDcText.setText(titleString(res.getString(R.string.cell_cr_save_dc), "" + cr.saveDc));

            minHpText.setText(titleString(res.getString(R.string.cell_cr_min_hp), "" + cr.minHp));
            maxHpText.setText(titleString(res.getString(R.string.cell_cr_max_hp), "" + cr.maxHp));
            minDmgText.setText(titleString(res.getString(R.string.cell_cr_min_dmg), "" + cr.minDmgRound));
            maxDmgText.setText(titleString(res.getString(R.string.cell_cr_max_dmg), "" + cr.maxDmgRound));
        } else {
            titleText.setText(String.format("%s (" + UNKNOWN + " xp)", UNKNOWN));

            valueText.setText(titleString(res.getString(R.string.cell_cr_value), UNKNOWN));
            profText.setText(titleString(res.getString(R.string.cell_cr_prof), UNKNOWN));
            maxAcText.setText(titleString(res.getString(R.string.cell_cr_max_ac), UNKNOWN));
            saveDcText.setText(titleString(res.getString(R.string.cell_cr_save_dc), UNKNOWN));

            minHpText.setText(titleString(res.getString(R.string.cell_cr_min_hp), UNKNOWN));
            maxHpText.setText(titleString(res.getString(R.string.cell_cr_max_hp), UNKNOWN));
            minDmgText.setText(titleString(res.getString(R.string.cell_cr_min_dmg), UNKNOWN));
            maxDmgText.setText(titleString(res.getString(R.string.cell_cr_max_dmg), UNKNOWN));
        }
    }
}
