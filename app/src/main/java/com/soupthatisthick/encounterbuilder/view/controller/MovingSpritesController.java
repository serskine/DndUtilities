package com.soupthatisthick.encounterbuilder.view.controller;

import android.graphics.PointF;
import android.graphics.RectF;

import com.soupthatisthick.encounterbuilder.model.lookup.EncounterMap;
import com.soupthatisthick.encounterbuilder.model.lookup.Sprite;
import com.soupthatisthick.encounterbuilder.util.Vector;
import com.soupthatisthick.encounterbuilder.view.GridView;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Created by Owner on 1/9/2017.
 * Copyright of Stuart Marr Erskine, all rights reserved.
 */

public class MovingSpritesController extends GridController {

    private EncounterMap encounterMap;
    private Map<Integer, PointF> selectedSpritesDown = new HashMap<>();

    public MovingSpritesController(String tag, EncounterMap encounterMap, GridView gridView) {
        super(tag, gridView);
        this.encounterMap = encounterMap;
    }


    @Override
    protected void onGridDown(PointF gridPoint) {
        Set<Integer> pointsDown = encounterMap.getSpritesTouching(gridPoint);
        for(Integer index : pointsDown)
        {
            selectedSpritesDown.put(index, gridPoint);
        }
    }

    @Override
    protected void onGridUp(PointF gridPoint) {

        for(Integer index : selectedSpritesDown.keySet())
        {
            PointF selectedDown = selectedSpritesDown.get(index);
            Sprite sprite = encounterMap.sprites.get(index);
            RectF oldBounds = sprite.getBounds();
            PointF move = Vector.diff(gridPoint, selectedDown);
            RectF newBounds = Vector.translate(oldBounds, move);
            sprite.setBounds(newBounds);
        }
        selectedSpritesDown.clear();
        gridView.invalidate();
    }

    @Override
    protected void onGridMove(PointF gridPoint) {

    }

    protected void moveSprites(PointF gridPoint)
    {

        for(Integer index : selectedSpritesDown.keySet())
        {
            PointF gridDown = selectedSpritesDown.get(index);
            Sprite sprite = encounterMap.sprites.get(index);
            PointF move = Vector.diff(gridPoint, gridDown);

            RectF oldBounds = sprite.getBounds();
            RectF newBounds = Vector.translate(oldBounds, move);
            sprite.setBounds(newBounds);
        }

        gridView.invalidate();


    }
}
