package com.soupthatisthick.encounterbuilder.adapters.lookup;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SpinnerAdapter;

import com.soupthatisthick.encounterbuilder.model.lookup.Faction;
import com.soupthatisthick.encounterbuilder.util.adapter.CustomListAdapter;
import com.soupthatisthick.encounterbuilder.view.cell.other.FactionCell;

import java.util.ArrayList;

/**
 * Created by Owner on 2/7/2017.
 * Copyright of Stuart Marr Erskine, all rights reserved.
 */

public class FactionAdapter extends CustomListAdapter<Faction> implements SpinnerAdapter {

    /**
     * By default we will include all enumeration values for the faction
     * @param inflater
     */
    public FactionAdapter(LayoutInflater inflater) {
        super(inflater);
        ArrayList<Faction> aValues = new ArrayList<>();
        for(int i=0; i<Faction.values().length; i++)
        {
            aValues.add(Faction.values()[i]);
        }
        setData(aValues);
    }

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        return getView(position, convertView, parent);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        FactionCell cell = new FactionCell(inflater, convertView, parent);
        cell.updateUi(Faction.values()[position]);
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