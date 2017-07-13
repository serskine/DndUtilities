package com.soupthatisthick.encounterbuilder.adapters.lookup;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.soupthatisthick.encounterbuilder.model.lookup.Spell;
import com.soupthatisthick.encounterbuilder.util.adapter.CustomListAdapter;
import com.soupthatisthick.encounterbuilder.view.cell.detail.SpellDetailCell;

/**
 * Created by Owner on 1/26/2017.
 * Copyright of Stuart Marr Erskine, all rights reserved.
 */

public class SpellListAdapter extends CustomListAdapter<Spell> {

    public SpellListAdapter(LayoutInflater inflater) {
        super(inflater);
    }

//    /**
//     * This will return the view when it is expanded
//     *
//     * @param position
//     * @param convertView
//     * @param parent      @return an expanded detail view of that element
//     */
//    @Override
//    protected View getExpandedView(int position, View convertView, ViewGroup parent) {
//        Spell spell = data.get(position);
//        SpellDetailCell cell = new SpellDetailCell(inflater, convertView, parent);
//        cell.updateUi(spell);
//        return cell.getView();
//    }
//
//    /**
//     * This willl return the view when it is collapsed
//     *
//     * @param position
//     * @param convertView
//     * @param parent      @return a collapsed summary view of the element
//     */
//    @Override
//    protected View getCollapsedView(int position, View convertView, ViewGroup parent) {
//        Spell spell = data.get(position);
//        ScrollTextCell cell = new ScrollTextCell(inflater, convertView, parent);
//        cell.updateUi(spell.getClassName());
//        return cell.getView();
//    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Spell spell = data.get(position);
        SpellDetailCell cell = new SpellDetailCell(inflater, convertView, parent);
        cell.updateUi(spell);
        return cell.getView();
    }

    @Override
    public int getItemViewType(int position) {
        return 0;
    }

    @Override
    public int getViewTypeCount() {
        return  1;
    }
}
