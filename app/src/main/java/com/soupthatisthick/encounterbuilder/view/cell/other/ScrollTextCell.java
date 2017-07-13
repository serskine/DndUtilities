package com.soupthatisthick.encounterbuilder.view.cell.other;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.soupthatisthick.encounterbuilder.view.cell.summary.TextCell;

import soupthatisthick.encounterapp.R;

/**
 * Created by Owner on 4/12/2017.
 * Copyright of Stuart Marr Erskine, all rights reserved.
 */

public class ScrollTextCell extends TextCell {

    public ScrollTextCell(LayoutInflater inflater, View convertView, ViewGroup parent) {
        super(inflater, convertView, parent);
    }
    public ScrollTextCell(LayoutInflater inflater, View convertView, ViewGroup parent, String defaultText) {
        super(inflater, convertView, parent, defaultText);
    }

    @Override
    public View createView(LayoutInflater inflater, View convertView, ViewGroup parent) {
        View view = inflater.inflate(R.layout.cell_scroll_text, parent);

        theText = (TextView) view.findViewById(R.id.text_view);

        return view;
    }

}
