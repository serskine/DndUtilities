package com.soupthatisthick.encounterbuilder.view.cell.detail;

import android.content.res.Resources;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.soupthatisthick.encounterbuilder.dao.helper.CompendiumResource;
import com.soupthatisthick.encounterbuilder.exception.DaoModelException;
import com.soupthatisthick.encounterbuilder.model.lookup.Entity;
import com.soupthatisthick.encounterbuilder.util.Text;
import com.soupthatisthick.encounterbuilder.view.cell.ReadCell;
import com.soupthatisthick.encounterbuilder.view.cell.summary.CompendiumSummaryCell;
import com.soupthatisthick.util.Logger;

import soupthatisthick.encounterapp.R;

/**
 * Created by Owner on 5/26/2018.
 * Copyright of Stuart Marr Erskine, all rights reserved.
 */
public class EntityDetailCell extends ReadCell<Entity> {

    private TextView theTitle;
    private TextView theCategory;
    private TextView theMetadata;

    public EntityDetailCell(LayoutInflater inflater, View convertView, ViewGroup parent) {
        super(inflater, convertView, parent);
    }

    @Override
    public void updateUi(Entity entity) {
        theTitle.setText(Text.propertySpan("Entity: ", Text.toString(entity.getId().toString())));
        theCategory.setText(Text.propertySpan("Category: ", Text.toString(entity.getChildCategory().toString())));
        theMetadata.setText(Text.propertySpan("Metadata: ", Text.toString(entity.getMetadata())));
    }

    @Override
    public View createView(LayoutInflater inflater, View convertView, ViewGroup parent) {
        View view = inflater.inflate(R.layout.cell_entity_detail, parent);

        theTitle = view.findViewById(R.id.theTitle);
        theCategory = view.findViewById(R.id.theCategory);
        theMetadata = view.findViewById(R.id.theMetaData);

        return view;
    }
}
