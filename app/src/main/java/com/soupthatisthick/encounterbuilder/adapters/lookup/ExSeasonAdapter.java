package com.soupthatisthick.encounterbuilder.adapters.lookup;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.soupthatisthick.encounterbuilder.model.lookup.Season;
import com.soupthatisthick.encounterbuilder.util.adapter.CustomToggleAdapter;
import com.soupthatisthick.encounterbuilder.view.cell.detail.SeasonDetailCell;
import com.soupthatisthick.encounterbuilder.view.cell.summary.SeasonSummaryCell;

public class ExSeasonAdapter extends CustomToggleAdapter<Season> {
    public ExSeasonAdapter(LayoutInflater inflater) {
        super(inflater);
    }

    @Override
    public View getCollapsedView(int position, View convertView, ViewGroup parent) {
        SeasonSummaryCell cell = new SeasonSummaryCell(inflater, convertView, parent);
        cell.updateUi(getCastedItem(position));
        return cell.getView();
    }

    @Override
    public View getExpandedView(int position, View convertView, ViewGroup parent) {
        SeasonDetailCell cell = new SeasonDetailCell(inflater, convertView, parent);
        cell.updateUi(getCastedItem(position));
        return cell.getView();
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
