package com.soupthatisthick.encounterbuilder.view.cell.detail;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.soupthatisthick.encounterbuilder.dao.helper.CompendiumResource;
import com.soupthatisthick.encounterbuilder.model.lookup.Entity;
import com.soupthatisthick.encounterbuilder.util.sort.Category;
import com.soupthatisthick.encounterbuilder.view.cell.ReadCell;

/**
 * Created by Owner on 5/26/2018.
 * Copyright of Stuart Marr Erskine, all rights reserved.
 */
public class EntityDetailCell extends ReadCell<Entity> {

    private final CompendiumResource compendiumResource;

    public EntityDetailCell(
            CompendiumResource compendiumResource,
            LayoutInflater inflater,
            View convertView,
            ViewGroup parent
    ) {
        super(inflater, convertView, parent);
        this.compendiumResource = compendiumResource;
    }

    @Override
    public void updateUi(Entity entity) {
        if (entity.getEntityId() != null) {
            Category category = entity.getEntityCategory();
            Long theId = entity.getCategoryColumnId(category);

        }
    }

    @Override
    public View createView(LayoutInflater inflater, View convertView, ViewGroup parent) {
        return null;
    }


}
