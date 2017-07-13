package com.soupthatisthick.encounterbuilder.adapters.lookup;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.soupthatisthick.encounterbuilder.model.lookup.Alignment;
import com.soupthatisthick.encounterbuilder.util.adapter.CustomListAdapter;
import com.soupthatisthick.encounterbuilder.view.cell.summary.AlignmentCell;

import java.util.ArrayList;

/**
 * Created by Owner on 3/2/2017.
 * Copyright of Stuart Marr Erskine, all rights reserved.
 */

public class AlignmentAdapter extends CustomListAdapter<Alignment> {
    public AlignmentAdapter(LayoutInflater inflater) {
        super(inflater);

        ArrayList<Alignment> data = new ArrayList<>();
        for(int i=0; i<Alignment.values().length; i++)
        {
            data.add(Alignment.values()[i]);
        }
        setData(data);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        AlignmentCell cell = new AlignmentCell(inflater, convertView, parent);
        cell.updateUi((Alignment) getItem(position));
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
