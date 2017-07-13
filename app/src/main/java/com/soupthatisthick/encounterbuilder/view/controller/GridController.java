package com.soupthatisthick.encounterbuilder.view.controller;

import android.graphics.PointF;
import android.view.MotionEvent;
import android.view.View;

import com.soupthatisthick.encounterbuilder.view.GridView;

/**
 * Created by Owner on 1/9/2017.
 * Copyright of Stuart Marr Erskine, all rights reserved.
 */

public abstract class GridController extends DefaultTouchDetector {

    protected final GridView gridView;

    public GridController(String tag, GridView gridView) {
        super(tag);
        this.gridView = gridView;
    }

    protected abstract void onGridDown(PointF gridPoint);
    protected abstract void onGridUp(PointF gridPoint);
    protected abstract void onGridMove(PointF gridPoint);

    @Override
    protected void onDown(View view, MotionEvent motionEvent)
    {
       onGridDown(gridView.toGrid(getPosition(motionEvent)));
    }

    @Override
    protected void onMove(View view, MotionEvent motionEvent)
    {
        onGridMove(gridView.toGrid(getPosition(motionEvent)));
    }

    @Override
    protected void onUp(View view, MotionEvent motionEvent)
    {
        onGridUp(gridView.toGrid(getPosition(motionEvent)));
    }

}
