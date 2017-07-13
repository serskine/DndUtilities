package com.soupthatisthick.encounterbuilder.adapters.lookup;

import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.soupthatisthick.encounterbuilder.util.adapter.CustomListAdapter;
import com.soupthatisthick.encounterbuilder.view.cell.other.DrawableCell;

/**
 * Created by Owner on 3/3/2017.
 * Copyright of Stuart Marr Erskine, all rights reserved.
 */

public class DrawableAdapter extends CustomListAdapter<Drawable> {
    public DrawableAdapter(LayoutInflater inflater) {
        super(inflater);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        DrawableCell cell = new DrawableCell(inflater, convertView, parent);
        Drawable item = getCastedItem(position);
        cell.updateUi(item);
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
