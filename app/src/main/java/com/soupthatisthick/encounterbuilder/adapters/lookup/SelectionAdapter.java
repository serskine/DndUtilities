package com.soupthatisthick.encounterbuilder.adapters.lookup;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import com.soupthatisthick.encounterbuilder.model.Selection;
import com.soupthatisthick.encounterbuilder.util.adapter.CustomListAdapter;
import com.soupthatisthick.encounterbuilder.view.cell.WriteCell;
import com.soupthatisthick.encounterbuilder.view.cell.other.SelectionCell;
import com.soupthatisthick.util.Logger;
import com.soupthatisthick.util.adapters.WriteCellAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Owner on 5/27/2017.
 * Copyright of Stuart Marr Erskine, all rights reserved.
 */

public abstract class SelectionAdapter<DataType> extends WriteCellAdapter<Selection<DataType> >
{

    public SelectionAdapter(LayoutInflater inflater) {
        super(inflater);
    }

    @Override
    protected WriteCell<Selection<DataType> > createWriteCell(LayoutInflater inflater, View convertView, ViewGroup parent) {
        return createSelectionCell(inflater, convertView, parent);
    }

    public synchronized final void selectAll()
    {
        for(int i=0; i<getCount(); i++)
        {
            selectPosition(i);
        }
    }

    public synchronized final void deselectAll()
    {
        for(int i=0; i<getCount(); i++)
        {
            deselectPosition(i);
        }
        notifyObservers();
    }

    public synchronized final void selectPosition(int position)
    {
        Selection model = getCastedItem(position);
        model.setSelected(true);
        listeners.announce().positionUpdated(this, model, position);
        notifyObservers();
    }

    public synchronized final void deselectPosition(int position)
    {
        Selection model = getCastedItem(position);
        model.setSelected(false);
        listeners.announce().positionUpdated(this, model, position);
        notifyObservers();
    }

    public synchronized final boolean isPositionSelected(int position)
    {
        return getCastedItem(position).isSelected();
    }

    /**
     * This will determine if all the items are selected. It will stop
     * after finding the first unselected item. O(n) complexity at worst
     * @return true if all the items are selected
     */
    public synchronized final boolean isAllSelected()
    {
        for(int i=0; i<getCount(); i++)
        {
            if (!isPositionSelected(i))
            {
                return false;
            }
        }
        return true;
    }

    /**
     * This will check to see if all the items are unselected an still stop
     * after finding the first item that is selected. O(n) complexity at worst
     * @return true if all the items are not selected
     */
    public synchronized final boolean isAllUnSelected()
    {
        for(int i=0; i<getCount(); i++)
        {
            if (isPositionSelected(i))
            {
                return false;
            }
        }
        return true;

    }

    /**
     * This will do a single pass and group all the items into two lists.
     * The positional information will be lost
     * @param selectedList will contain all selected items
     * @param notSelectedList will contain all items that are not selected
     */
    public synchronized final void groupSelections(List<Object> selectedList, List<Object> notSelectedList)
    {
        for(int i=0; i<getCount(); i++)
        {
            Object item = getItem(i);
            if (isPositionSelected(i))
            {
                selectedList.add(item);
            } else {
                notSelectedList.add(item);
            }
        }
    }


    /**
     * If you need all items selected and not selected, don't make two calls,
     * use{@link #groupSelections} instead to do it in one pass
     * @return a {@link List<Object>} of all items that are selected
     */
    public synchronized final List<Object> getSelectedItems()
    {
        List<Object> selectedList = new ArrayList<>();
        for(int i=0; i<getCount(); i++)
        {
            Object item = getItem(i);
            if (isPositionSelected(i))
            {
                selectedList.add(item);
            }
        }
        return selectedList;
    }

    /**
     * If you need all items selected and not selected, don't make two calls,
     * use{@link #groupSelections} instead to do it in one pass
     * @return a {@link List<Object>} of all items not selected
     */
    public synchronized final List<Object> getUnselectedItems()
    {
        List<Object> selectedList = new ArrayList<>();
        for(int i=0; i<getCount(); i++)
        {
            Object item = getItem(i);
            if (isPositionSelected(i))
            {
                selectedList.add(item);
            }
        }
        return selectedList;
    }

    protected abstract SelectionCell<DataType> createSelectionCell(
        LayoutInflater inflater,
        View convertView,
        ViewGroup parent
    );
}




