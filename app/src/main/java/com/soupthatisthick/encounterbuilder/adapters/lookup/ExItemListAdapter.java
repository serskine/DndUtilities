package com.soupthatisthick.encounterbuilder.adapters.lookup;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.soupthatisthick.encounterbuilder.model.lookup.ItemList;
import com.soupthatisthick.encounterbuilder.util.adapter.CustomToggleAdapter;

/**
 * Created by Owner on 4/21/2017.
 * Copyright of Stuart Marr Erskine, all rights reserved.
 */

public class ExItemListAdapter  extends CustomToggleAdapter<ItemList> {
    public ExItemListAdapter(LayoutInflater inflater) {
        super(inflater);
    }

    @Override
    public View getCollapsedView(int position, View convertView, ViewGroup parent) {
        return null;
    }

    @Override
    public View getExpandedView(int position, View convertView, ViewGroup parent) {
        return null;
    }

    @Override
    protected int getViewType(int position, boolean isExpanded) {
        return 0;
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
        return 0;
    }
}
