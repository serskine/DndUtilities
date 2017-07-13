package com.soupthatisthick.encounterbuilder.view.cell.detail;

import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.soupthatisthick.encounterbuilder.model.lookup.LifeStyle;
import com.soupthatisthick.encounterbuilder.view.cell.ReadCell;
import com.soupthatisthick.util.dao.ReadDao;

import java.util.List;
import java.util.Set;

import soupthatisthick.encounterapp.R;

/**
 * Created by Owner on 5/25/2017.
 * Copyright of Stuart Marr Erskine, all rights reserved.
 */

public class LifestyleDetailCell extends ReadCell<LifeStyle> {
    private TextView theTitle;
    private TextView theContent;

    public LifestyleDetailCell(LayoutInflater inflater, View convertView, ViewGroup parent) {
        super(inflater, convertView, parent);
    }

    @Override
    public View createView(LayoutInflater inflater, View convertView, ViewGroup parent) {
        View view = inflater.inflate(R.layout.cell_lifestyle_detail, parent);
        theTitle = (TextView) view.findViewById(R.id.theTitle);
        theContent = (TextView) view.findViewById(R.id.theContent);
        return view;
    }

    @Override
    public void updateUi(LifeStyle lifeStyle) {
        theTitle.setText(normalString(String.format("%s (%s)", lifeStyle.getName(), lifeStyle.getPricePerDay())));
        theContent.setText(htmlString(lifeStyle.getDescription()));
    }
}

