package com.soupthatisthick.encounterbuilder.util.adapter;

import android.database.DataSetObserver;
import android.support.annotation.CallSuper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.SpinnerAdapter;

import com.soupthatisthick.encounterbuilder.dao.lookup.LevelDao;
import com.soupthatisthick.encounterbuilder.dao.master.DndMaster;
import com.soupthatisthick.util.adapters.DaoAdapter;
import com.soupthatisthick.util.dao.ReadDao;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Owner on 12/22/2016.
 * Copyright of Stuart Marr Erskine, all rights reserved.
 */

public abstract class CustomListAdapter<DataType> extends DaoAdapter<DataType>
    implements ListAdapter, SpinnerAdapter
{
    protected final ArrayList<DataSetObserver> observers = new ArrayList<>();
    protected final ArrayList<DataType> data = new ArrayList<>();

    protected final LayoutInflater inflater;

    public CustomListAdapter(LayoutInflater inflater)
    {
        this.inflater = inflater;
    }

    @CallSuper
    public void setData(List<DataType> data)
    {
        this.data.clear();
        this.data.addAll(data);
        notifyObservers();
    }

    public final void notifyObservers()
    {
        for(DataSetObserver observer : observers)
        {
            observer.onChanged();
        }
    }

    @Override
    public void registerDataSetObserver(DataSetObserver observer) {
        observers.add(observer);
    }

    @Override
    public void unregisterDataSetObserver(DataSetObserver observer) {
        observers.remove(observer);
    }

    @Override
    public final int getCount() {
        return data.size();
    }

    @Override
    public final Object getItem(int position) {
        return data.get(position);
    }

    public final DataType getCastedItem(int position) {
        return (DataType) getItem(position);
    }

    @Override
    public final long getItemId(int position) {
        return position;
    }

    @Override
    public final boolean hasStableIds() {
        return true;
    }

    @Override
    public final boolean isEmpty() {
        return data.isEmpty();
    }

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        return getView(position, convertView, parent);
    }

    @Override
    public final boolean areAllItemsEnabled() {
        return true;
    }

    @Override
    public final boolean isEnabled(int position) {
        return true;
    }

}
