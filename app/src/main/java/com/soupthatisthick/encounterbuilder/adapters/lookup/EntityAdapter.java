package com.soupthatisthick.encounterbuilder.adapters.lookup;

import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.soupthatisthick.encounterbuilder.DndUtilApp;
import com.soupthatisthick.encounterbuilder.dao.helper.CompendiumResource;
import com.soupthatisthick.encounterbuilder.exception.DaoModelException;
import com.soupthatisthick.encounterbuilder.model.lookup.Entity;
import com.soupthatisthick.encounterbuilder.util.adapter.CustomListAdapter;
import com.soupthatisthick.encounterbuilder.util.adapter.CustomToggleAdapter;
import com.soupthatisthick.encounterbuilder.util.progress.ProgressMonitor;
import com.soupthatisthick.encounterbuilder.util.sort.Category;
import com.soupthatisthick.encounterbuilder.view.cell.detail.EntityDetailCell;
import com.soupthatisthick.encounterbuilder.view.cell.summary.EntitySummaryCell;
import com.soupthatisthick.util.Logger;
import com.soupthatisthick.util.dao.DaoMaster;
import com.soupthatisthick.util.dao.ReadDao;
import com.soupthatisthick.util.dao.WriteDao;

import java.util.ArrayList;
import java.util.List;

public class EntityAdapter extends CustomToggleAdapter<Entity> implements CompendiumResource.Listener {

    private final CompendiumAdapter compendiumAdapter;

    private DisplayType collapsedType = DisplayType.ENTITY_CONTENTS;
    private DisplayType expandedType = DisplayType.ENTITY_CONTENTS;

    @Override
    public void loadDaoMasterSuccess(String daoMasterKey, DaoMaster daoMaster) {
        // Do nothing
    }

    @Override
    public void loadDaoMasterFailure(String daoMasterKey) {
        // Do nothing
    }

    @Override
    public void loadDaoSuccess(String daoKey, ReadDao readDao) {

    }

    @Override
    public void loadDaoFailure(String daoKey) {

    }

    @Override
    public void update(ProgressMonitor monitor, int numSteps, int numFailedSteps, int numSuccessSteps, int numPendingSteps) {
        setData(data);  // Reset the data so that the compendium adapter will be updated. Refresh screen
        notifyObservers();
    }


    public enum DisplayType {
        ENTITY_CONTENTS,
        ENTITY_DETAILS
    }

    public EntityAdapter(LayoutInflater inflater) {
        this(inflater, null);
    }
    public EntityAdapter(LayoutInflater inflater, CompendiumResource compendiumResource) {
        super(inflater);
        compendiumAdapter = new CompendiumAdapter(inflater);
    }

    @Override
    public View getCollapsedView(int position, View convertView, ViewGroup parent) {
        switch(getCollapsedType()) {
            case ENTITY_DETAILS:
                EntitySummaryCell entitySummaryCell = new EntitySummaryCell(inflater, convertView, parent);
                entitySummaryCell.updateUi(getCastedItem(position));
                return entitySummaryCell.getView();
            case ENTITY_CONTENTS:
            default:
                return compendiumAdapter.getCollapsedView(position, convertView, parent);
        }
    }

    @Override
    public View getExpandedView(int position, View convertView, ViewGroup parent) {
        switch(getExpandedType()) {
            case ENTITY_DETAILS:
                EntityDetailCell entityDetailCell = new EntityDetailCell(inflater, convertView, parent);
                entityDetailCell.updateUi(getCastedItem(position));
                return entityDetailCell.getView();
            case ENTITY_CONTENTS:
            default:
                return compendiumAdapter.getExpandedView(position, convertView, parent);
        }
    }

    @Override
    public void setData(List<Entity> data) {
        super.setData(data);
        List<Object> children = new ArrayList<>();

        final CompendiumResource compendiumResource = DndUtilApp.getInstance().getCompendiumResource();

        for(Entity entity : data) {
            Object child;
            try {
                Category category = entity.getChildCategory();
                WriteDao writeDao = compendiumResource.getDaoForCategory(category);
                if (writeDao==null) {
                    throw new RuntimeException("There is no write dao for category " + category + ". The Compendium resource returned null.");
                }
                Long id = entity.getCategoryColumnId(category);
                if (id==null) {
                    throw new RuntimeException("We failed to retrieve the child id for column of category " + category + ".");
                }
                child = writeDao.load(id);
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


    public DisplayType getCollapsedType() {
        return collapsedType;
    }

    public void setCollapsedType(@NonNull DisplayType collapsedType) {
        this.collapsedType = collapsedType;
        notifyObservers();
    }

    public DisplayType getExpandedType() {
        return expandedType;
    }

    public void setExpandedType(@NonNull DisplayType expandedType) {
        this.expandedType = expandedType;
        notifyObservers();
    }
}
