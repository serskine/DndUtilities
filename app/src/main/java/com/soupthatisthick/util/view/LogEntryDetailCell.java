package com.soupthatisthick.util.view;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.soupthatisthick.encounterbuilder.view.cell.ReadCell;
import com.soupthatisthick.util.model.LogEntry;

import soupthatisthick.encounterapp.R;


/**
 * Created by Owner on 5/11/2017.
 * Copyright of Stuart Marr Erskine, all rights reserved.
 */

public class LogEntryDetailCell extends ReadCell<LogEntry>
{
    private TextView theTextView, theTimeView, theTagView, theContentView;


    public LogEntryDetailCell(LayoutInflater inflater, View convertView, ViewGroup parent) {
        super(inflater, convertView, parent);
    }

    @Override
    public void updateUi(LogEntry logEntry) {
        theTextView.setText(htmlString(logEntry.text));
        theTimeView.setText(titleString("Time: ",logEntry.getTime()));
        theTagView.setText(titleString("Header: ",logEntry.getHeader()));
        theContentView.setText(titleString("Content: ",logEntry.getContent()));
    }

    @Override
    public View createView(LayoutInflater inflater, View convertView, ViewGroup parent) {
        View view = inflater.inflate(R.layout.cell_log_entry_detail, parent);
        theTextView = (TextView) view.findViewById(R.id.theTextView);
        theTimeView = (TextView) view.findViewById(R.id.theTimeView);
        theTagView = (TextView) view.findViewById(R.id.theTagView);
        theContentView = (TextView) view.findViewById(R.id.theContentView);
        return view;
    }
}
