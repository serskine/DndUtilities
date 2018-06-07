package com.soupthatisthick.encounterbuilder.adapters.lookup;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.soupthatisthick.encounterbuilder.dao.helper.CompendiumResource;
import com.soupthatisthick.encounterbuilder.exception.DaoModelException;
import com.soupthatisthick.encounterbuilder.model.lookup.Entity;
import com.soupthatisthick.encounterbuilder.util.adapter.CustomListAdapter;
import com.soupthatisthick.encounterbuilder.util.adapter.CustomToggleAdapter;
import com.soupthatisthick.encounterbuilder.util.sort.Category;
import com.soupthatisthick.encounterbuilder.view.cell.detail.EntityDetailCell;
import com.soupthatisthick.encounterbuilder.view.cell.summary.EntitySummaryCell;
import com.soupthatisthick.util.Logger;
import com.soupthatisthick.util.dao.WriteDao;

import java.util.ArrayList;
import java.util.List;

public class EntityAdapter extends CustomToggleAdapter<Entity> {

    private final CompendiumAdapter compendiumAdapter;
    private final CompendiumResource compendiumResource;

    public EntityAdapter(LayoutInflater inflater) {
        this(inflater, null);
    }
    public EntityAdapter(LayoutInflater inflater, CompendiumResource compendiumResource) {
        super(inflater);
        compendiumAdapter = new CompendiumAdapter(inflater);
        this.compendiumResource = compendiumResource;
    }

    @Override
    public View getCollapsedView(int position, View convertView, ViewGroup parent) {
        EntitySummaryCell entitySummaryCell = new EntitySummaryCell(inflater, convertView, parent);
        entitySummaryCell.updateUi(getCastedItem(position));
        return entitySummaryCell.getView();
    }

    @Override
    public View getExpandedView(int position, View convertView, ViewGroup parent) {
        EntityDetailCell entityDetailCell = new EntityDetailCell(inflater, convertView, parent);
        entityDetailCell.updateUi(getCastedItem(position));
        return entityDetailCell.getView();
    }

    @Override
    public void setData(List<Entity> data) {
        super.setData(data);
        List<Object> children = new ArrayList<>();

        for(Entity entity : data) {
            Object child;
            try {
                Category category = entity.getChildCategory();
                WriteDao writeDao = compendiumResource.getDaoForCategory(category);
                Long id = entity.getCategoryColumnId(category);
                if (id==null) {
                    throw new RuntimeException("We failed to retrieve the child id for column of category " + category + ".");
                }
                child = writeDao.getId(id);
                if (child==null) {
                    throw new RuntimeException("We have an entity that does not have a child!");
                }
            } catch (Exception e) {
                Logger.error("Using the actual entity object for display.\n" + e.getMessage(), e);
                child = entity;
            }
            children.add(child);
        }
        compendiumAdapter.setData(children);
    }

    @Override
    public int getViewTypeCount() {
        return 1;
    }
}
