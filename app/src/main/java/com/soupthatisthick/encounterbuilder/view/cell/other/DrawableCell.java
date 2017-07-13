package com.soupthatisthick.encounterbuilder.view.cell.other;

import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.soupthatisthick.encounterbuilder.view.cell.ReadCell;

import soupthatisthick.encounterapp.R;

/**
 * Created by Owner on 3/3/2017.
 * Copyright of Stuart Marr Erskine, all rights reserved.
 */

public class DrawableCell extends ReadCell<Drawable> {

    private ImageView theImage;

    public DrawableCell(LayoutInflater inflater, View convertView, ViewGroup parent) {
        super(inflater, convertView, parent);
    }

    @Override
    public void updateUi(@NonNull Drawable drawable) {
        theImage.setImageDrawable(drawable);
    }

    @Override
    public View createView(LayoutInflater inflater, View convertView, ViewGroup parent) {
        View view = inflater.inflate(R.layout.cell_picture, parent);
        theImage = (ImageView) view.findViewById(R.id.the_image);
        return view;
    }
}
