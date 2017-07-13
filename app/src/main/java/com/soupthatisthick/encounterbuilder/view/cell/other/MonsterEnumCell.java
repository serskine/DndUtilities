package com.soupthatisthick.encounterbuilder.view.cell.other;

import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.soupthatisthick.encounterbuilder.logic.enums.MonsterEnum;
import com.soupthatisthick.encounterbuilder.view.cell.ReadCell;

import soupthatisthick.encounterapp.R;

/**
 * Created by Owner on 12/22/2016.
 * Copyright of Stuart Marr Erskine, all rights reserved.
 */

public class MonsterEnumCell extends ReadCell<MonsterEnum> {

    TextView name, cr, page, type;

    public MonsterEnumCell(LayoutInflater inflater, View convertView, ViewGroup parent) {
        super(inflater, convertView, parent);
    }

    public View createView(LayoutInflater inflater, View convertView, ViewGroup parent) {
        View view = inflater.inflate(R.layout.cell_standard_monster_summary, parent);
        name = (TextView) view.findViewById(R.id.nameText);
        cr = (TextView) view.findViewById(R.id.crText);
        page = (TextView) view.findViewById(R.id.pageText);
        type = (TextView) view.findViewById(R.id.typeText);
        return view;
    }

    public void updateUi(@Nullable MonsterEnum monster)
    {
        if (monster!=null) {
            name.setText(monster.name);
            cr.setText(monster.cr.text);
            page.setText("MM PageBounds " + Integer.toString(monster.page));
            type.setText(monster.type);
        } else {
            name.setText("");
            cr.setText("");
            page.setText("");
            type.setText("");
        }
    }


}
