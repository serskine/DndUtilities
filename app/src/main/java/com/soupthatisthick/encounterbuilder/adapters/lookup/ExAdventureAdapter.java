package com.soupthatisthick.encounterbuilder.adapters.lookup;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.soupthatisthick.encounterbuilder.model.lookup.Adventure;
import com.soupthatisthick.encounterbuilder.model.lookup.Item;
import com.soupthatisthick.encounterbuilder.util.adapter.CustomToggleAdapter;
import com.soupthatisthick.encounterbuilder.view.cell.detail.AdventureDetailCell;
import com.soupthatisthick.encounterbuilder.view.cell.summary.AdventureSummaryCell;

public class ExAdventureAdapter extends CustomToggleAdapter<Adventure> {
    @Override
    public View getCollapsedView(int position, View convertView, ViewGroup parent) {
        AdventureSummaryCell cell = new AdventureSummaryCell(inflater, convertView, parent);
        cell.updateUi(getCastedItem(position));
        return cell.getView();
    }

    @Override
    public View getExpandedView(int position, View convertView, ViewGroup parent) {
        AdventureDetailCell cell = new AdventureDetailCell(inflater, convertView, parent);
        cell.updateUi(getCastedItem(position));
        return cell.getView();
    }

    public ExAdventureAdapter(LayoutInflater inflater) {
        super(inflater);
    }

    @Override
    protected int getViewType(int position, boolean isExpanded) {
        return (isExpanded) ? 1 : 0;
    }


    @Override
    public int getViewTypeCount() {
        return 2;
    }
}
