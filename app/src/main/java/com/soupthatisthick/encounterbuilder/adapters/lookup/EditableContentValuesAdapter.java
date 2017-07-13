package com.soupthatisthick.encounterbuilder.adapters.lookup;

import android.content.ContentValues;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.soupthatisthick.encounterbuilder.util.adapter.CustomListAdapter;
import com.soupthatisthick.encounterbuilder.view.cell.other.ContentValuesFieldCell;

import java.util.ArrayList;

/**
 * Created by Owner on 3/20/2017.
 * Copyright of Stuart Marr Erskine, all rights reserved.
 */

public class EditableContentValuesAdapter extends CustomListAdapter<String> {

    protected enum InputType
    {
        SIGNED_INTEGER,
        UNSIGNED_INTEGER,
        REAL,
        STRING
    }

    private ContentValues content = new ContentValues();

    public EditableContentValuesAdapter(LayoutInflater inflater) {
        super(inflater);
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        String key = (String) getItem(position);
        ContentValuesFieldCell cell = new ContentValuesFieldCell(key, inflater, convertView, parent);
        cell.updateUi(content);
        return cell.getView();
    }

    @Override
    public int getItemViewType(int position) {
        return 0;
    }

    @Override
    public int getViewTypeCount() {
        return 1;
    }

    public ContentValues getContent() {
        return content;
    }

    public void setContent(ContentValues content) {
        this.content = content;
        
        ArrayList<String> data = new ArrayList<>();
        data.addAll(content.keySet());
        setData(data);
    }


}



