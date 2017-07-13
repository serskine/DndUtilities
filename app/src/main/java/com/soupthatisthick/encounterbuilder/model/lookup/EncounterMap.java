package com.soupthatisthick.encounterbuilder.model.lookup;

import android.graphics.PointF;
import android.graphics.Rect;
import android.graphics.RectF;

import com.soupthatisthick.encounterbuilder.util.announcer.Announcer;

import java.util.ArrayList;
import java.util.EventListener;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Owner on 1/8/2017.
 * Copyright of Stuart Marr Erskine, all rights reserved.
 */

public class EncounterMap implements Sprite.Listener {
    public final ArrayList<Sprite> sprites = new ArrayList<>();
    public final Announcer<Listener> listeners = Announcer.to(Listener.class);

    @Override
    public void onSpriteChanged(Sprite sprite) {
        updateListeners();
    }

    public interface Listener extends EventListener {
        void onEncounterUpdated(EncounterMap encounterMap);
    }

    public void updateListeners()
    {
        listeners.announce().onEncounterUpdated(this);
    }

    public int getPosition(Sprite sprite)
    {
        return sprites.indexOf(sprite);
    }

    public Sprite getSpriteAt(int position)
    {
        return sprites.get(position);
    }

    public void swapSprites(int position1, int position2)
    {
        Sprite s1 = sprites.get(position1);
        Sprite s2 = sprites.get(position2);

        sprites.set(position1, s2);
        sprites.set(position2, s1);
        updateListeners();
    }

    public void removeSprite(Sprite sprite)
    {
        int index = getPosition(sprite);
        if (index>=0 && index<sprites.size())
        {
            removeSprite(index);
        }
    }

    public void removeSprite(int position)
    {
        Sprite removed = sprites.remove(position);
        removed.listeners.removeListener(this);
        updateListeners();
    }

    public void addSprite(Sprite sprite)
    {
        sprites.add(sprite);
        sprite.listeners.addListener(this);
        updateListeners();
    }

    public void setSprite(int position, Sprite sprite)
    {
        Sprite prevSprite = sprites.set(position, sprite);
        prevSprite.listeners.removeListener(this);
        sprite.listeners.addListener(this);
        updateListeners();
    }

    public static RectF toRectF(Rect bounds)
    {
        return new RectF(bounds.left, bounds.top, bounds.right, bounds.bottom);
    }

    public int countSprites() {
        return sprites.size();
    }

    public Set<Integer> getSpritesTouching(PointF gridPoint)
    {
        HashSet<Integer> touching = new HashSet<>();
        for(int i=0; i<countSprites(); i++)
        {
            Sprite sprite = sprites.get(i);
            if (sprite.getBounds().contains(gridPoint.x, gridPoint.y))
            {
                touching.add(i);
            }
        }
        return touching;
    }

}
