package com.soupthatisthick.encounterbuilder.view.cell.other;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.soupthatisthick.encounterbuilder.adapters.lookup.SpellLevelAdapter;
import com.soupthatisthick.encounterbuilder.view.cell.ReadCell;

import soupthatisthick.encounterapp.R;

/**
 * Created by Owner on 2/28/2017.
 * Copyright of Stuart Marr Erskine, all rights reserved.
 */

public class SpellLevelCell extends ReadCell<Integer> {

    private TextView textView;

    public SpellLevelCell(LayoutInflater inflater, View convertView, ViewGroup parent) {
        super(inflater, convertView, parent);
    }

    @Override
    public void updateUi(Integer value) {
        String text = null;

        if (value == SpellLevelAdapter.CANTRIP) {
            text = getView().getResources().getString(R.string.spl_adapter_cantrip);
        } else if (value==1) {
            text = getView().getResources().getString(R.string.spl_adapter_spell_level, value + "st");
        } else if (value==2) {
            text = getView().getResources().getString(R.string.spl_adapter_spell_level, value + "nd");
        } else if (value==3) {
            text = getView().getResources().getString(R.string.spl_adapter_spell_level, value + "rd");
        } else {
            text = getView().getResources().getString(R.string.spl_adapter_spell_level, value + "th");
        }

        textView.setText(text);
    }

    @Override
    public View createView(LayoutInflater inflater, View convertView, ViewGroup parent) {
        View view = inflater.inflate(R.layout.cell_text_view, parent);
        textView = (TextView) view.findViewById(R.id.text_view);
        return view;
    }
}
