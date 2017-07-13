package com.soupthatisthick.encounterbuilder.adapters.lookup;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.soupthatisthick.encounterbuilder.model.lookup.Level;
import com.soupthatisthick.encounterbuilder.util.adapter.CustomListAdapter;
import com.soupthatisthick.encounterbuilder.view.cell.detail.LevelDetailCell;

/**
 * Created by Owner on 12/22/2016.
 * Copyright of Stuart Marr Erskine, all rights reserved.
 */

public class PlayersAdapter extends CustomListAdapter<Level> {
    public PlayersAdapter(LayoutInflater inflater) {
        super(inflater);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Level lvl = data.get(position);
        LevelDetailCell cell = new LevelDetailCell(inflater, convertView, parent);
        cell.updateUi(lvl);
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
