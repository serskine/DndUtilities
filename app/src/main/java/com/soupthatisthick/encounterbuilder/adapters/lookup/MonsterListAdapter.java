package com.soupthatisthick.encounterbuilder.adapters.lookup;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.soupthatisthick.encounterbuilder.model.lookup.CustomMonster;
import com.soupthatisthick.encounterbuilder.util.adapter.CustomListAdapter;
import com.soupthatisthick.encounterbuilder.view.cell.detail.CustomMonsterDetailCell;

/**
 * Created by Owner on 1/26/2017.
 * Copyright of Stuart Marr Erskine, all rights reserved.
 */

public class MonsterListAdapter extends CustomListAdapter<CustomMonster> {

    protected int EXPANDED_VIEW_TYPE = 1;
    protected int COLLAPSED_VIEW_TYPE = 0;

    public MonsterListAdapter(LayoutInflater inflater) {
        super(inflater);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        CustomMonster customMonster = data.get(position);
        CustomMonsterDetailCell cell = new CustomMonsterDetailCell(inflater, convertView, parent);
        cell.updateUi(customMonster);
        return cell.getView();
    }
    @Override
    public int getItemViewType(int position) {
        return 0;
    }


//    /**
//     * This will return the view when it is expanded
//     *
//     * @param position
//     * @return an expanded detail view of that element
//     */
//    @Override
//    protected View getExpandedView(int position, View convertView, ViewGroup parent) {
//        CustomMonster monster = data.get(position);
//        CustomMonsterDetailCell cell = new CustomMonsterDetailCell(inflater, convertView, parent);
//        cell.updateUi(monster);
//        return cell.getView();
//    }
//
//    /**
//     * This willl return the view when it is collapsed
//     *
//     * @param position
//     * @return a collapsed summary view of the element
//     */
//    @Override
//    protected View getCollapsedView(int position, View convertView, ViewGroup parent) {
//        CustomMonster monster = data.get(position);
//        ScrollTextCell cell = new ScrollTextCell(inflater, convertView, parent);
//        cell.updateUi(monster.getClassName());
//        return cell.getView();
//    }

    @Override
    public int getViewTypeCount() {
        return  2;
    }
}
