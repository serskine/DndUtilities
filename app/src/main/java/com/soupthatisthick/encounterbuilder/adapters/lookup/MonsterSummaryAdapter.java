package com.soupthatisthick.encounterbuilder.adapters.lookup;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.soupthatisthick.encounterbuilder.logic.enums.MonsterEnum;
import com.soupthatisthick.encounterbuilder.util.adapter.CustomListAdapter;
import com.soupthatisthick.encounterbuilder.view.cell.other.MonsterEnumCell;

/**
 * Created by Owner on 12/22/2016.
 * Copyright of Stuart Marr Erskine, all rights reserved.
 */

public class MonsterSummaryAdapter extends CustomListAdapter<MonsterEnum> {

    public MonsterSummaryAdapter(LayoutInflater inflater)
    {
        super(inflater);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        MonsterEnum monster = data.get(position);
        MonsterEnumCell cell = new MonsterEnumCell(inflater, convertView, parent);
        cell.updateUi(monster);
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
