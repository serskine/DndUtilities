package com.soupthatisthick.encounterbuilder.model.lookup;

import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;

import com.soupthatisthick.encounterbuilder.util.announcer.Announcer;

import java.util.EventListener;

/**
 * Created by Owner on 1/8/2017.
 * Copyright of Stuart Marr Erskine, all rights reserved.
 */

public class Sprite {

    private RectF bounds = new RectF(0f, 0f, 0f, 0f);
    private Drawable drawable = null;
    private boolean selected = false;

    public final Announcer<Sprite.Listener> listeners = Announcer.to(Sprite.Listener.class);

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
        if (drawable != null)
        {
            drawable.setAlpha((selected) ? 128 : 255);
        }
    }

    public interface Listener extends EventListener
    {
        void onSpriteChanged(Sprite sprite);
    }

    public Sprite(@NonNull Drawable drawable, @NonNull RectF bounds)
    {
        this.drawable = drawable;
        this.bounds = bounds;
    }

    public void setBounds(@NonNull RectF bounds)
    {
        this.bounds = bounds;
    }

    public RectF getBounds()
    {
        return this.bounds;
    }

    public Drawable getDrawable()
    {
        return this.drawable;
    }

    public void setDrawable(@NonNull Drawable drawable)
    {
        this.drawable = drawable;
    }
}
