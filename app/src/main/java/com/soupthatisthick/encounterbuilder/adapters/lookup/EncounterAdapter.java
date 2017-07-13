package com.soupthatisthick.encounterbuilder.adapters.lookup;

import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;

import com.soupthatisthick.encounterbuilder.model.lookup.EncounterMap;
import com.soupthatisthick.encounterbuilder.view.GridView;

/**
 * Created by Owner on 1/16/2017.
 * Copyright of Stuart Marr Erskine, all rights reserved.
 */

public class EncounterAdapter implements GridView.Adapter, EncounterMap.Listener {

    private final EncounterMap encounterMap;

    public EncounterAdapter(@NonNull EncounterMap encounterMap)
    {
        this.encounterMap = encounterMap;
        this.encounterMap.listeners.addListener(this);
    }

    @Override
    public RectF getBounds(int position) {
        return encounterMap.getSpriteAt(position).getBounds();
    }

    @Override
    public int getCount() {
        return encounterMap.countSprites();
    }

    @Override
    public Drawable getDrawable(int position) {
        return encounterMap.getSpriteAt(position).getDrawable();
    }

    @Override
    public void onEncounterUpdated(EncounterMap encounterMap) {
        this.notifyAll();
    }
}
