package com.soupthatisthick.encounterbuilder.view.cell.summary;

import android.content.ContentValues;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TableLayout;

import com.soupthatisthick.encounterbuilder.adapters.lookup.KeyValuePairCell;
import com.soupthatisthick.encounterbuilder.util.KeyValuePair;
import com.soupthatisthick.encounterbuilder.view.cell.ReadCell;
import com.soupthatisthick.encounterbuilder.view.cell.other.ContentValuesFieldCell;
import com.soupthatisthick.util.adapters.KeyValuePairAdapter;
import com.soupthatisthick.util.adapters.ReadCellAdapter;

import soupthatisthick.encounterapp.R;

/**
 * Created by Owner on 6/27/2017.
 * Copyright of Stuart Marr Erskine, all rights reserved.
 */

public class ContentValuesSummaryCell extends ContentValuesCell {

    private TableLayout tableLayout;
    private KeyValuePairCell cell;

    public ContentValuesSummaryCell(LayoutInflater inflater, View convertView, ViewGroup parent, @NonNull String primaryKey) {
        super(inflater, convertView, parent, primaryKey);
    }

    @Override
    public void updateUi(ContentValues contentValues) {
        String value = contentValues.getAsString(primaryKey);
        cell.updateUi(primaryKey, value);
    }

    @Override
    public View createView(LayoutInflater inflater, View convertView, ViewGroup parent) {
        View view = inflater.inflate(R.layout.cell_content_values_summary, parent);
        tableLayout = (TableLayout) view.findViewById(R.id.theTableLayout);
        cell = new KeyValuePairCell(inflater, convertView, parent);
        tableLayout.addView(cell.getView());
        return view;
    }
}
