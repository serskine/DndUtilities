package com.soupthatisthick.encounterbuilder.view.cell.other;

import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.soupthatisthick.encounterbuilder.model.DieRoll;
import com.soupthatisthick.encounterbuilder.view.cell.ReadCell;

import soupthatisthick.encounterapp.R;

/**
 * Created by Owner on 5/11/2017.
 * Copyright of Stuart Marr Erskine, all rights reserved.
 */

public class DieRollCell extends ReadCell<DieRoll> {

    ImageView imageView;
    DieRoll dieRoll = null;

    public DieRollCell(LayoutInflater inflater, View convertView, ViewGroup parent) {
        super(inflater, convertView, parent);
    }

    @Override
    public void updateUi(@Nullable DieRoll dieRoll) {
        this.dieRoll = dieRoll;
        Drawable imageDrawable = getView().getResources().getDrawable(dieRoll.size.imgResource);
        imageView.setImageDrawable(imageDrawable);
        checkVisibilities();
    }

    @Override
    public View createView(LayoutInflater inflater, View convertView, ViewGroup parent) {
        View view = inflater.inflate(R.layout.cell_die_roll, parent);
        imageView = (ImageView) view.findViewById(R.id.theImageView);
        checkVisibilities();
        return view;
    }

    /**
     * If there is no die roll in the cell then it will still take up space but not be visible
     */
    private void checkVisibilities()
    {
//        imageView.setVisibility((dieRoll==null) ? View.INVISIBLE : View.VISIBLE);
        imageView.setVisibility(View.VISIBLE);
    }
}
