package com.soupthatisthick.encounterbuilder.adapters.lookup;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SpinnerAdapter;

import com.soupthatisthick.encounterbuilder.model.lookup.SpellSchool;
import com.soupthatisthick.encounterbuilder.util.adapter.CustomListAdapter;
import com.soupthatisthick.encounterbuilder.view.cell.other.SpellSchoolCell;

import java.util.ArrayList;

/**
 * Created by Owner on 2/28/2017.
 * Copyright of Stuart Marr Erskine, all rights reserved.
 */

public class SpellSchoolAdapter extends CustomListAdapter<SpellSchool> implements SpinnerAdapter {
    public SpellSchoolAdapter(LayoutInflater inflater) {
        super(inflater);
        ArrayList<SpellSchool> data = new ArrayList<>();
        for(int i=0; i<SpellSchool.values().length; i++)
        {
            data.add(SpellSchool.values()[i]);
        }
        setData(data);
    }

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        return getView(position, convertView, parent);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        SpellSchoolCell cell = new SpellSchoolCell(inflater, convertView, parent);
        cell.updateUi((SpellSchool) getItem(position));
        return cell.getView();
    }

    @Override
    public int getItemViewType(int position) {
        return 0;
    }

    @Override
    public int getViewTypeCount() {
        return 1;
    }
}

