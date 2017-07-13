package com.soupthatisthick.encounterbuilder.adapters.lookup;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.soupthatisthick.encounterbuilder.model.lookup.CustomMonster;
import com.soupthatisthick.encounterbuilder.util.adapter.CustomListAdapter;
import com.soupthatisthick.encounterbuilder.view.cell.other.MonsterDetailWebCell;

/**
 * Created by Owner on 3/27/2017.
 * Copyright of Stuart Marr Erskine, all rights reserved.
 */

public class MonsterListWebAdapter extends CustomListAdapter<CustomMonster> {
    public MonsterListWebAdapter(LayoutInflater inflater) {
        super(inflater);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        CustomMonster customMonster = data.get(position);
        MonsterDetailWebCell cell = new MonsterDetailWebCell(inflater, convertView, parent);
        cell.updateUi(customMonster);
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
