package com.soupthatisthick.encounterbuilder.view.cell.summary;

import android.content.ContentValues;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.soupthatisthick.encounterbuilder.view.cell.ReadCell;

/**
 * Created by Owner on 6/27/2017.
 * Copyright of Stuart Marr Erskine, all rights reserved.
 */

public abstract class ContentValuesCell extends ReadCell<ContentValues> {

    protected final String primaryKey;

    public ContentValuesCell(LayoutInflater inflater, View convertView, ViewGroup parent, @NonNull String primaryKey) {
        super(inflater, convertView, parent);
        this.primaryKey = primaryKey;
    }


}
