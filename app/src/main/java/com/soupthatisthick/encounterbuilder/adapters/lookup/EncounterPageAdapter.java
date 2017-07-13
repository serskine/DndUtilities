package com.soupthatisthick.encounterbuilder.adapters.lookup;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.soupthatisthick.encounterbuilder.printing.model.EncounterPage;
import com.soupthatisthick.encounterbuilder.util.adapter.CustomListAdapter;
import com.soupthatisthick.encounterbuilder.view.cell.summary.EncounterPageCell;

/**
 * Created by Owner on 4/29/2017.
 * Copyright of Stuart Marr Erskine, all rights reserved.
 */

public class EncounterPageAdapter extends CustomListAdapter<EncounterPage> {

    public EncounterPageAdapter(LayoutInflater inflater) {
        super(inflater);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        EncounterPageCell cell = new EncounterPageCell(inflater, convertView, parent);
        EncounterPage page = getCastedItem(position);
        cell.updateUi(page);
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
