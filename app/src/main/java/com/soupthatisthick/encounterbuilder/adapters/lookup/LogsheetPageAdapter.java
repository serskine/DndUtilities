package com.soupthatisthick.encounterbuilder.adapters.lookup;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.soupthatisthick.encounterbuilder.printing.model.LogsheetPage;
import com.soupthatisthick.encounterbuilder.util.adapter.CustomListAdapter;
import com.soupthatisthick.encounterbuilder.view.cell.summary.LogsheetPageCell;

/**
 * Created by Owner on 3/4/2017.
 * Copyright of Stuart Marr Erskine, all rights reserved.
 */

public class LogsheetPageAdapter extends CustomListAdapter<LogsheetPage> {

    public LogsheetPageAdapter(LayoutInflater inflater) {
        super(inflater);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LogsheetPageCell cell = new LogsheetPageCell(inflater, convertView, parent);
        LogsheetPage page = getCastedItem(position);
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
