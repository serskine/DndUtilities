package com.soupthatisthick.encounterbuilder.adapters.lookup;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.soupthatisthick.encounterbuilder.model.lookup.ContentValuesField;
import com.soupthatisthick.encounterbuilder.util.KeyValuePair;
import com.soupthatisthick.encounterbuilder.view.cell.ReadCell;

import soupthatisthick.encounterapp.R;

/**
 * Created by Owner on 6/27/2017.
 * Copyright of Stuart Marr Erskine, all rights reserved.
 */

public class KeyValuePairCell extends ReadCell<KeyValuePair<String, String>> {

    private TextView theKey, theValue;

    public KeyValuePairCell(LayoutInflater inflater, View convertView, ViewGroup parent) {
        super(inflater, convertView, parent);
    }

    public void updateUi(@Nullable String key, @Nullable String value)
    {
        String UNKNOWN = getView().getResources().getString(R.string.view_visible_unknown);
        key = (key==null) ? UNKNOWN : key;
        value = (value==null) ? UNKNOWN : value;
        theKey.setText(key);
        theValue.setText(value);
    }

    @Override
    public void updateUi(KeyValuePair<String, String> keyValuePair) {
        updateUi(keyValuePair.getKey(), keyValuePair.getValue());
    }

    @Override
    public View createView(LayoutInflater inflater, View convertView, ViewGroup parent) {
        View view = inflater.inflate(R.layout.cell_key_value, parent);
        theKey = (TextView) view.findViewById(R.id.theKey);
        theValue = (TextView) view.findViewById(R.id.theValue);
        return view;
    }
}
