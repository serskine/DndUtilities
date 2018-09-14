package com.soupthatisthick.encounterbuilder.view.cell.detail;

import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.soupthatisthick.encounterbuilder.model.lookup.Note;
import com.soupthatisthick.encounterbuilder.view.cell.ReadCell;

import com.android.xamoom.htmltextview.HtmlTextView;

import soupthatisthick.encounterapp.R;

/**
 * Created by Owner on 4/13/2017.
 * Copyright of Stuart Marr Erskine, all rights reserved.
 */

public class NoteDetailCell extends ReadCell<Note> {

    public TextView theTitle;
    public TextView theContent;
//    public HtmlTextView theContent;

    public NoteDetailCell(LayoutInflater inflater, View convertView, ViewGroup parent) {
        super(inflater, convertView, parent);
    }

    @Override
    public View createView(LayoutInflater inflater, View convertView, ViewGroup parent) {
        View view = inflater.inflate(R.layout.cell_note_detail, parent);

        theTitle = view.findViewById(R.id.theTitle);
        theContent = view.findViewById(R.id.theContent);

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
        final String noteTitle = "Note " + item.getId();
        theTitle.setText(
            isEmpty(item.getTitle())
            ?   noteTitle
            :   item.getTitle()
        );
//        theContent.setHtmlText(item.getContent());
        theContent.setText(htmlString(item.getContent()));
        checkVisibility();
    }

    public void updateUi(@Nullable String title, @Nullable String content)
    {
        // If the title is blank, then we will use it's id to display it instead. However we
        // do NOT search by the id field, only the title field
        theTitle.setText(isEmpty(title) ? "Unknown Content" : title);
        theContent.setText(content);
//        theContent.setHtmlText(content, getView().getWidth());

        checkVisibility();

    }


    private void checkVisibility()
    {
    }
}
