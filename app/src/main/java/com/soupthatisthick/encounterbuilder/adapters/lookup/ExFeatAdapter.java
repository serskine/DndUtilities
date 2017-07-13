package com.soupthatisthick.encounterbuilder.adapters.lookup;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import com.soupthatisthick.encounterbuilder.model.lookup.Feat;
import com.soupthatisthick.encounterbuilder.util.adapter.CustomToggleAdapter;
import com.soupthatisthick.encounterbuilder.view.cell.detail.FeatDetailCell;
import com.soupthatisthick.encounterbuilder.view.cell.summary.FeatSummaryCell;

/**
 * Created by Owner on 5/5/2017.
 * Copyright of Stuart Marr Erskine, all rights reserved.
 */

public class ExFeatAdapter extends CustomToggleAdapter<Feat> {
    public ExFeatAdapter(LayoutInflater inflater) {
        super(inflater);
    }

    @Override
    public View getCollapsedView(int position, View convertView, ViewGroup parent) {
        FeatSummaryCell cell = new FeatSummaryCell(inflater, convertView, parent);
        cell.updateUi(getCastedItem(position));
        return cell.getView();
    }

    @Override
    public View getExpandedView(int position, View convertView, ViewGroup parent) {
        FeatDetailCell cell = new FeatDetailCell(inflater, convertView, parent);
        cell.updateUi(getCastedItem(position));
        return cell.getView();
    }

    @Override
    protected int getViewType(int position, boolean isExpanded) {
        return (isExpanded ? 1 : 0);
    }

    /**
     * <p>
     * Returns the number of types of Views that will be created by
     * {@link #getView}. Each type represents a set of views that can be
     * converted in {@link #getView}. If the adapter always returns the same
     * type of View for all items, this method should return 1.
     * </p>
     * <p>
     * This method will only be called when the adapter is set on the {@link AdapterView}.
     * </p>
     *
     * @return The number of types of Views that will be created by this adapter
     */
    @Override
    public int getViewTypeCount() {
        return 2;
    }
}
