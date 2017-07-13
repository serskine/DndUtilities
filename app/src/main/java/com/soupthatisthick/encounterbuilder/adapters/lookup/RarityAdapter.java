package com.soupthatisthick.encounterbuilder.adapters.lookup;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SpinnerAdapter;

import com.soupthatisthick.encounterbuilder.model.lookup.Rarity;
import com.soupthatisthick.encounterbuilder.util.adapter.CustomListAdapter;
import com.soupthatisthick.encounterbuilder.view.cell.other.RarityCell;

import java.util.ArrayList;

/**
 * Created by Owner on 2/26/2017.
 * Copyright of Stuart Marr Erskine, all rights reserved.
 */

public class RarityAdapter extends CustomListAdapter<Rarity> implements SpinnerAdapter {
    public RarityAdapter(LayoutInflater inflater) {
        super(inflater);

        ArrayList<Rarity> data = new ArrayList<>();
        for(int i=0; i<Rarity.values().length; i++)
        {
            data.add(Rarity.values()[i]);
        }
        setData(data);
    }

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        return getView(position, convertView, parent);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        RarityCell cell = new RarityCell(inflater, convertView, parent);
        cell.updateUi((Rarity) getItem(position));
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
