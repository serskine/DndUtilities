package com.soupthatisthick.encounterbuilder.view.cell.other;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.soupthatisthick.encounterbuilder.model.lookup.SpellSchool;
import com.soupthatisthick.encounterbuilder.util.translater.SpellSchoolTranslater;
import com.soupthatisthick.encounterbuilder.view.cell.ReadCell;

import soupthatisthick.encounterapp.R;

/**
 * Created by Owner on 2/28/2017.
 * Copyright of Stuart Marr Erskine, all rights reserved.
 */

public class SpellSchoolCell extends ReadCell<SpellSchool> {

    private TextView textView;

    public SpellSchoolCell(LayoutInflater inflater, View convertView, ViewGroup parent) {
        super(inflater, convertView, parent);
    }

    @Override
    public void updateUi(SpellSchool spellSchool) {
        textView.setText(SpellSchoolTranslater.INSTANCE.toText(spellSchool));
    }


    @Override
    public View createView(LayoutInflater inflater, View convertView, ViewGroup parent) {
        View view = inflater.inflate(R.layout.cell_text_view, parent);
        textView = (TextView) view.findViewById(R.id.text_view);
        return view;
    }
}
