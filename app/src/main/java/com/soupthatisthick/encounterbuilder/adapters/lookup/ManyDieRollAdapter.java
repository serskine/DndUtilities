package com.soupthatisthick.encounterbuilder.adapters.lookup;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import com.soupthatisthick.encounterbuilder.model.DieRoll;
import com.soupthatisthick.encounterbuilder.model.DieSize;
import com.soupthatisthick.encounterbuilder.util.adapter.CustomListAdapter;
import com.soupthatisthick.encounterbuilder.view.cell.other.DieRollCell;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Owner on 5/11/2017.
 * Copyright of Stuart Marr Erskine, all rights reserved.
 */

public class ManyDieRollAdapter extends CustomListAdapter<DieRoll> {

    /**
     * Used primarily for testing, it will produce a roll for every face of every sized die possible and add it to a list
     * @return a {@link List<DieRoll>}
     */
    public static final List<DieRoll> getAllPossibleRolls()
    {
        List<DieRoll> theList = new ArrayList<>();
        for(DieSize dieSize : DieSize.values())
        {
            for(int i=1; i<= dieSize.numFaces; i++)
            {
                DieRoll dieRoll = new DieRoll(dieSize, i);
                theList.add(dieRoll);
            }
        }
        return theList;
    }

    public ManyDieRollAdapter(LayoutInflater inflater) {
        super(inflater);

        setData(getAllPossibleRolls());
    }

    /**
     * Get a View that displays the data at the specified position in the data set. You can either
     * create a View manually or inflate it from an XML layout file. When the View is inflated, the
     * parent View (GridView, ListView...) will apply default layout parameters unless you use
     * {@link LayoutInflater#inflate(int, ViewGroup, boolean)}
     * to specify a root view and to prevent attachment to the root.
     *
     * @param position    The position of the item within the adapter's data set of the item whose view
     *                    we want.
     * @param convertView The old view to reuse, if possible. Note: You should check that this view
     *                    is non-null and of an appropriate type before using. If it is not possible to convert
     *                    this view to display the correct data, this method can create a new view.
     *                    Heterogeneous lists can specify their number of view types, so that this View is
     *                    always of the right type (see {@link #getViewTypeCount()} and
     *                    {@link #getItemViewType(int)}).
     * @param parent      The parent that this view will eventually be attached to
     * @return A View corresponding to the data at the specified position.
     */
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        DieRollCell cell = new DieRollCell(inflater, convertView, parent);
        cell.updateUi(getCastedItem(position));
        return cell.getView();
    }

    /**
     * Get the type of View that will be created by {@link #getView} for the specified item.
     *
     * @param position The position of the item within the adapter's data set whose view type we
     *                 want.
     * @return An integer representing the type of View. Two views should share the same type if one
     * can be converted to the other in {@link #getView}. Note: Integers must be in the
     * range 0 to {@link #getViewTypeCount} - 1. {@link #IGNORE_ITEM_VIEW_TYPE} can
     * also be returned.
     * @see #IGNORE_ITEM_VIEW_TYPE
     */
    @Override
    public int getItemViewType(int position) {
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
        return 1;
    }
}
