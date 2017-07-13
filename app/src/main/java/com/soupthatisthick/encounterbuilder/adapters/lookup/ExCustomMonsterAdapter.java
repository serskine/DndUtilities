package com.soupthatisthick.encounterbuilder.adapters.lookup;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import com.soupthatisthick.encounterbuilder.model.lookup.CustomMonster;
import com.soupthatisthick.encounterbuilder.util.adapter.CustomToggleAdapter;
import com.soupthatisthick.encounterbuilder.view.cell.detail.CustomMonsterDetailCell;
import com.soupthatisthick.encounterbuilder.view.cell.summary.CustomMonsterSummaryCell;

/**
 * Created by Owner on 4/12/2017.
 * Copyright of Stuart Marr Erskine, all rights reserved.
 */

public class ExCustomMonsterAdapter extends CustomToggleAdapter<CustomMonster> {
    public ExCustomMonsterAdapter(LayoutInflater inflater) {
        super(inflater);
    }

    @Override
    public View getCollapsedView(int position, View convertView, ViewGroup parent) {
        CustomMonsterSummaryCell cell = new CustomMonsterSummaryCell(inflater, convertView, parent);
        cell.updateUi(getCastedItem(position));
        return cell.getView();
    }

    @Override
    public View getExpandedView(int position, View convertView, ViewGroup parent) {
        CustomMonsterDetailCell cell = new CustomMonsterDetailCell(inflater, convertView, parent);
        cell.updateUi(getCastedItem(position));
        return cell.getView();
    }

    @Override
    protected int getViewType(int position, boolean isExpanded) {
        return (isExpanded) ? 1 : 0;
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
