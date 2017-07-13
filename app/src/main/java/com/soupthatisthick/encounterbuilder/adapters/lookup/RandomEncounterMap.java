package com.soupthatisthick.encounterbuilder.adapters.lookup;

import android.content.Context;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;

import com.soupthatisthick.encounterbuilder.model.lookup.EncounterMap;
import com.soupthatisthick.encounterbuilder.model.lookup.Sprite;

import java.util.Random;

import soupthatisthick.encounterapp.R;

/**
 * Created by Owner on 1/5/2017.
 * Copyright of Stuart Marr Erskine, all rights reserved.
 */

public class RandomEncounterMap extends EncounterMap {

    Random r = new Random();

    int width=7;
    int height=7;
    int minSpriteWidth = 1;
    int minSpriteHeight = 1;
    int maxSpriteWidth = 1;
    int maxSpriteHeight = 1;
    int rW = maxSpriteWidth-minSpriteWidth;
    int rH = maxSpriteHeight-minSpriteHeight;

    public RandomEncounterMap(Context context)
    {
        int numSnappedSprites = r.nextInt(4+1);
        int numFloatingSprites = r.nextInt(4+1);

        Sprite sprite;

        sprite = new Sprite(createDrawable(context), new RectF(0f, 0f, 0f, 0f));
        sprites.add(sprite);

        for(int i=0; i<numSnappedSprites; i++)
        {
            sprite = new Sprite(createDrawable(context), toRectF(createSnappedBounds()));
            sprites.add(sprite);
        }

        for(int i=0; i<numFloatingSprites; i++)
        {
            sprite = new Sprite(createDrawable(context), createFloatingBounds());
            sprites.add(sprite);
        }
    }

    private Drawable createDrawable(Context context)
    {
        Drawable drawable = context.getResources().getDrawable(R.drawable.ic_launcher);
        return drawable;
    }

    private RectF createFloatingBounds()
    {
        float x = r.nextFloat()*width - width/2;
        float y = r.nextFloat()*height - height/2;
        float w = (rW<1) ? minSpriteWidth : r.nextInt(rW)+minSpriteWidth;
        float h = (rH<1) ? minSpriteHeight : r.nextInt(rH)+minSpriteHeight;
        return new RectF(x, y, x+w-1, y+h-1);
    }

    private Rect createSnappedBounds()
    {
        int x = r.nextInt(width) - width/2;
        int y = r.nextInt(height) - height/2;
        int w = (rW<1) ? minSpriteWidth : r.nextInt(rW)+minSpriteWidth;
        int h = (rH<1) ? minSpriteHeight : r.nextInt(rH)+minSpriteHeight;
        return new Rect(x, y, x+w-1, y+h-1);
    }
}
