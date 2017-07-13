package com.soupthatisthick.encounterbuilder.adapters.lookup;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.soupthatisthick.encounterbuilder.model.lookup.Pc;
import com.soupthatisthick.encounterbuilder.util.adapter.CustomListAdapter;
import com.soupthatisthick.encounterbuilder.view.cell.summary.PcCell;

/**
 * Created by Owner on 2/10/2017.
 * Copyright of Stuart Marr Erskine, all rights reserved.
 */

public class PcAdapter extends CustomListAdapter<Pc> {

    public PcAdapter(LayoutInflater inflater) {
        super(inflater);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        PcCell cell = new PcCell(inflater, convertView, parent);
        Pc playerCharacter = (Pc) getItem(position);
        cell.updateUi(playerCharacter);
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
