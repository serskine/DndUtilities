package com.soupthatisthick.encounterbuilder.view.cell;

import android.support.annotation.CallSuper;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.soupthatisthick.encounterbuilder.util.sort.Category;
import com.soupthatisthick.encounterbuilder.view.cell.summary.DefaultCompendiumCell;
import com.soupthatisthick.util.Logger;

/**
 * Created by Owner on 6/5/2017.
 * Copyright of Stuart Marr Erskine, all rights reserved.
 */

public abstract class CompendiumCell extends ReadCell<Object> {

    private ReadCell<?> cell = null;
    private Object model = null;

    public CompendiumCell(LayoutInflater inflater, View convertView, ViewGroup parent) {
        super(inflater, convertView, parent);
    }

    /**
     * This method should be called first before we actually update the ui. This is because we make certain that
     * the appropriate layout view and cell has been created and set first
     * @param item
     */
    @Override
    public final void updateUi(Object item) {
        Category itemCategory = Category.parse(item);
        Category modelCategory = Category.parse(model);
        model = item;

        Logger.debug("model category = " + modelCategory);
        Logger.debug("item category = " + itemCategory);
        if (cell == null || itemCategory != modelCategory)
        {
            view = createView(inflater, convertView, parent);   // Causes the cell to be created
        }

        updateCellUi(itemCategory, cell, item);
    }

    /**
     * This method will create a new view for the specified category.
     * @param category
     * @param inflater
     * @param convertView
     * @param parent
     * @return
     */
    protected abstract ReadCell<?> createCell(
        @NonNull Category category,
        @NonNull LayoutInflater inflater,
        @Nullable View convertView,
        @Nullable ViewGroup parent
    );

    /**
     * When this method is called, the cell has initialized and the view created within the cell. However the cell will need to be updated
     * @param category determines the type of cell that was created
     * @param cell is the actual instance of the cell to be updated
     * @param item is what we want to update the cell ui with
     */
    protected abstract void updateCellUi(
        @NonNull Category category,
        @NonNull ReadCell<?> cell,
        @Nullable Object item
    );


    protected final boolean isViewCreatedForObject(@Nullable Object newModel)
    {
        if (model==null)
        {
            return (newModel==null);
        } else {
            Category oldCategory = Category.parse(model);
            Category newCategory = Category.parse(newModel);
            return (oldCategory == newCategory);
        }
    }

    /**
     * This method will be called before we actually update the view.
     * @param inflater
     * @param convertView
     * @param parent
     * @return
     */
    @Override
    public final View createView(LayoutInflater inflater, View convertView, ViewGroup parent)
    {
        Category category = Category.parse(model);
        this.cell =  createCell(category, inflater, convertView, parent);
        return cell.getView();
    }
}
