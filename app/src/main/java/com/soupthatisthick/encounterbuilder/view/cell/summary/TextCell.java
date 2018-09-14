package com.soupthatisthick.encounterbuilder.view.cell.summary;

import android.support.annotation.NonNull;
import android.text.Spanned;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.soupthatisthick.encounterbuilder.view.cell.ReadCell;

import soupthatisthick.encounterapp.R;

/**
 * Created by Owner on 4/12/2017.
 * Copyright of Stuart Marr Erskine, all rights reserved.
 */

public class TextCell extends ReadCell<String>
{

    protected TextView theText;
    protected final String defaultText;

    public TextCell(LayoutInflater inflater, View convertView, ViewGroup parent)
    {
        this(inflater, convertView, parent, "");
    }

    public TextCell(LayoutInflater inflater, View convertView, ViewGroup parent, @NonNull String defaultText) {
        super(inflater, convertView, parent);
        this.defaultText = defaultText;
    }

    @Override
    public View createView(LayoutInflater inflater, View convertView, ViewGroup parent) {
        View view = inflater.inflate(R.layout.cell_text_view, parent);

        theText = (TextView) view.findViewById(R.id.text_view);

        return view;
    }


    @Override
    public void updateUi(String theText)
    {
        if (theText!=null) {
            this.theText.setText(theText);
        } else {
            this.theText.setText(defaultText);
        }
    }

    public void updateUi(Spanned spanned) {
        if (spanned!=null) {
            this.theText.setText(spanned);
        } else {
            this.theText.setText(defaultText);
        }
    }
}