package com.soupthatisthick.encounterbuilder.view.cell;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Owner on 2/11/2017.
 * Copyright of Stuart Marr Erskine, all rights reserved.
 */

public abstract class ReadCell<Model> extends AbstractCell {
    public ReadCell(LayoutInflater inflater, View convertView, ViewGroup parent) {
        super(inflater, convertView, parent);
    }

    public abstract void updateUi(Model model);
}
