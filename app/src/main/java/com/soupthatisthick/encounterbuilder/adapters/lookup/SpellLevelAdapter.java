package com.soupthatisthick.encounterbuilder.adapters.lookup;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SpinnerAdapter;

import com.soupthatisthick.encounterbuilder.util.adapter.CustomListAdapter;
import com.soupthatisthick.encounterbuilder.view.cell.other.SpellLevelCell;

import java.util.ArrayList;

/**
 * Created by Owner on 2/28/2017.
 * Copyright of Stuart Marr Erskine, all rights reserved.
 */

public class SpellLevelAdapter extends CustomListAdapter<Integer> implements SpinnerAdapter {

    public static final int CANTRIP = 0;
    public static final int MAX_SPELL_LEVEL = 9;

    public SpellLevelAdapter(LayoutInflater inflater) {
        super(inflater);
        ArrayList<Integer> data = new ArrayList<>();
        for(int i=CANTRIP; i<=MAX_SPELL_LEVEL; i++)
        {
            data.add(new Integer(i));
        }
        setData(data);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        SpellLevelCell cell = new SpellLevelCell(inflater, convertView, parent);
        Integer level = (Integer) getItem(position);
        cell.updateUi(level);
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

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        return getView(position, convertView, parent);
    }
}
