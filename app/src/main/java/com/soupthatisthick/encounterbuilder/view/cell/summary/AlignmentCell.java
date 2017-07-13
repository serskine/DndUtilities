package com.soupthatisthick.encounterbuilder.view.cell.summary;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.soupthatisthick.encounterbuilder.model.lookup.Alignment;
import com.soupthatisthick.encounterbuilder.view.cell.ReadCell;

import soupthatisthick.encounterapp.R;

/**
 * Created by Owner on 3/2/2017.
 * Copyright of Stuart Marr Erskine, all rights reserved.
 */

public class AlignmentCell extends ReadCell<Alignment> {

    private TextView textView;

    public AlignmentCell(LayoutInflater inflater, View convertView, ViewGroup parent) {
        super(inflater, convertView, parent);
    }

    @Override
    public void updateUi(Alignment alignment) {
        textView.setText(alignment.displayString);
    }

    @Override
    public View createView(LayoutInflater inflater, View convertView, ViewGroup parent) {
        View view = inflater.inflate(R.layout.cell_text_view, parent);
        textView = (TextView) view.findViewById(R.id.text_view);
        return view;
    }
}
