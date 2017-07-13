package com.soupthatisthick.encounterbuilder.view.cell.summary;

import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.soupthatisthick.encounterbuilder.model.lookup.Note;
import com.soupthatisthick.encounterbuilder.view.cell.ReadCell;

import soupthatisthick.encounterapp.R;

/**
 * Created by Owner on 4/13/2017.
 * Copyright of Stuart Marr Erskine, all rights reserved.
 */

public class NoteSummaryCell extends ReadCell<Note> {

    public TextView theTitle;

    public NoteSummaryCell(LayoutInflater inflater, View convertView, ViewGroup parent) {
        super(inflater, convertView, parent);
    }

    @Override
    public View createView(LayoutInflater inflater, View convertView, ViewGroup parent) {
        View view = inflater.inflate(R.layout.cell_note_detail, parent);

        theTitle = (TextView) view.findViewById(R.id.theTitle);

        return view;
    }

    /**
     * This will update the UI according to the data provided by the MagicItem
     * @param item
     */
    public void updateUi(Note item)
    {
        // If the title is blank, then we will use it's id to display it instead. However we
        // do NOT search by the id field, only the title field
        theTitle.setText(
                isEmpty(item.getTitle())
                        ?   getView().getResources().getString(R.string.cell_notes_title, item.getId())
                        :   item.getTitle()
        );
    }

    public void updateUi(@Nullable String title)
    {
        theTitle.setText((title==null) ? "" : title);
    }
}
