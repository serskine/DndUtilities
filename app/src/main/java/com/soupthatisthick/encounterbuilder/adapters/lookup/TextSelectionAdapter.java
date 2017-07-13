package com.soupthatisthick.encounterbuilder.adapters.lookup;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.soupthatisthick.encounterbuilder.view.cell.other.SelectionCell;
import com.soupthatisthick.encounterbuilder.view.cell.other.TextSelectionCell;

/**
 * Created by Owner on 5/31/2017.
 * Copyright of Stuart Marr Erskine, all rights reserved.
 */

public class TextSelectionAdapter extends SelectionAdapter<String>
{
    public TextSelectionAdapter(LayoutInflater inflater) {
        super(inflater);
    }

    @Override
    protected SelectionCell<String> createSelectionCell(LayoutInflater inflater, View convertView, ViewGroup parent) {
        return new TextSelectionCell(inflater, convertView, parent);
    }
}
