package com.soupthatisthick.encounterbuilder.view.cell.other;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.soupthatisthick.encounterbuilder.view.cell.ReadCell;
import com.soupthatisthick.util.string.Table;

import soupthatisthick.encounterapp.R;

public class TableCellCell extends ReadCell<Table.Cell> {
    private TextView theTableCell;

    public TableCellCell(LayoutInflater inflater, View convertView, ViewGroup parent) {
        super(inflater, convertView, parent);
    }

    @Override
    public void updateUi(Table.Cell cell) {
        theTableCell.setText(htmlString(cell.text));
    }

    @Override
    public View createView(LayoutInflater inflater, View convertView, ViewGroup parent) {
        View view = inflater.inflate(R.layout.cell_table_cell, parent);
        theTableCell = view.findViewById(R.id.theTableCell);
        return view;
    }
}
