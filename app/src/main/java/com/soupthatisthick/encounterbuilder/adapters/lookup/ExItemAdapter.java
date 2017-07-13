package com.soupthatisthick.encounterbuilder.adapters.lookup;

import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.soupthatisthick.encounterbuilder.model.lookup.Item;
import com.soupthatisthick.encounterbuilder.util.adapter.CustomToggleAdapter;
import com.soupthatisthick.encounterbuilder.view.cell.detail.ItemErrorDetailCell;
import com.soupthatisthick.encounterbuilder.view.cell.summary.ItemErrorSummaryCell;
import com.soupthatisthick.util.dao.DaoMaster;

import java.io.IOException;

import soupthatisthick.encounterapp.R;

/**
 * Created by Owner on 4/20/2017.
 * Copyright of Stuart Marr Erskine, all rights reserved.
 */

public class ExItemAdapter extends CustomToggleAdapter<Item>
{
    protected final DaoMaster daoMaster;


    public ExItemAdapter(LayoutInflater inflater, DaoMaster daoMaster) {
        super(inflater);
        this.daoMaster = daoMaster;
    }

    @Override
    public View getCollapsedView(int position, View convertView, ViewGroup parent) {
        Item item = getCastedItem(position);
        try {
            throw new IOException(parent.getResources().getString(R.string.eil_item_access_error, item.getKey(), item.getTable()));
        } catch (IOException e) {
            ItemErrorSummaryCell itemErrorSummaryCell = new ItemErrorSummaryCell(inflater, convertView, parent);
            itemErrorSummaryCell.updateUi(new Pair<Item, Exception>(item, e));
            return itemErrorSummaryCell.getView();
        }
    }

    @Override
    public View getExpandedView(int position, View convertView, ViewGroup parent) {
        Item item = getCastedItem(position);
        try {
            throw new IOException(parent.getResources().getString(R.string.eil_item_access_error, item.getKey(), item.getTable()));
        } catch (IOException e) {
            ItemErrorDetailCell itemErrorDetailCell = new ItemErrorDetailCell(inflater, convertView, parent);
            itemErrorDetailCell.updateUi(new Pair<Item, Exception>(item, e));
            return itemErrorDetailCell.getView();
        }
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
