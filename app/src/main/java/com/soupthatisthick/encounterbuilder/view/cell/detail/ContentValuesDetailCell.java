package com.soupthatisthick.encounterbuilder.view.cell.detail;

import android.content.ContentValues;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TableLayout;

import com.soupthatisthick.encounterbuilder.util.KeyValuePair;
import com.soupthatisthick.encounterbuilder.view.cell.ReadCell;
import com.soupthatisthick.encounterbuilder.view.cell.summary.ContentValuesCell;
import com.soupthatisthick.util.adapters.KeyValuePairAdapter;

import java.util.ArrayList;
import java.util.List;

import soupthatisthick.encounterapp.R;

/**
 * Created by Owner on 6/27/2017.
 * Copyright of Stuart Marr Erskine, all rights reserved.
 */

public class ContentValuesDetailCell extends ContentValuesCell {
    private TableLayout tableLayout;
    private KeyValuePairAdapter adapter;


    public ContentValuesDetailCell(LayoutInflater inflater, View convertView, ViewGroup parent, @NonNull String primaryKey) {
        super(inflater, convertView, parent, primaryKey);
    }

    @Override
    public void updateUi(ContentValues contentValues) {
        ArrayList<KeyValuePair> data = new ArrayList<>();
        for(String key : contentValues.keySet())
        {
            String value = contentValues.getAsString(key);
            KeyValuePair<String, String> pair = new KeyValuePair<>();
            pair.setKey(key);
            pair.setValue(value);
            data.add(pair);
        }
        adapter.setData(data);
        updateTable(convertView, parent);
    }

    @Override
    public View createView(LayoutInflater inflater, View convertView, ViewGroup parent) {
        View view = inflater.inflate(R.layout.cell_content_values_detail, parent);
        tableLayout = (TableLayout) view.findViewById(R.id.theTableLayout);
        adapter = new KeyValuePairAdapter(inflater);
        return view;
    }

    /**
     * This will cuse the table to become updated
     * @param convertView
     * @param parent
     */
    private void updateTable(View convertView, ViewGroup parent)
    {
        if (adapter!=null)
        {
            tableLayout.removeAllViews();
            for(int i=0; i<adapter.getCount(); i++)
            {
                tableLayout.addView(adapter.getView(i, convertView, parent));
            }
        }
    }

}
