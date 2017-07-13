package com.soupthatisthick.encounterbuilder.adapters.lookup;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.soupthatisthick.encounterbuilder.model.lookup.StandardMonster;
import com.soupthatisthick.encounterbuilder.util.adapter.CustomListAdapter;
import com.soupthatisthick.encounterbuilder.view.cell.detail.StandardMonsterDetailCell;

/**
 * Created by Owner on 3/30/2017.
 * Copyright of Stuart Marr Erskine, all rights reserved.
 */

public class StandardMonsterListAdapter extends CustomListAdapter<StandardMonster> {

    public StandardMonsterListAdapter(LayoutInflater inflater) {
        super(inflater);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        StandardMonster monster = data.get(position);
        StandardMonsterDetailCell cell = new StandardMonsterDetailCell(inflater, convertView, parent);
        cell.updateUi(monster);
        return cell.getView();
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
//        StandardMonster monster = data.get(position);
//        StandardMonsterDetailCell cell = new StandardMonsterDetailCell(inflater, convertView, parent);
//        cell.updateUi(monster);
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
//        StandardMonster monster = data.get(position);
//        ScrollTextCell cell = new ScrollTextCell(inflater, convertView, parent);
//        cell.updateUi(monster.getClassName());
//        return cell.getView();
//    }

    @Override
    public int getItemViewType(int position) {
        return 0;
    }

    @Override
    public int getViewTypeCount() {
        return  1;
    }
}
