package com.soupthatisthick.encounterbuilder.view.cell.other;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.soupthatisthick.encounterbuilder.view.cell.ReadCell;
import com.soupthatisthick.util.string.Table;

import soupthatisthick.encounterapp.R;

public class TableViewCell extends ReadCell<Table> {
    private TableLayout theTable;
    public TableViewCell(LayoutInflater inflater, View convertView, ViewGroup parent) {
        super(inflater, convertView, parent);
    }

    @Override
    public void updateUi(Table table) {
        theTable.removeAllViews();
        for(int row=0; row<table.numRows(); row++) {
            TableRowCell tableRowCell = new TableRowCell(inflater, null, theTable);
            tableRowCell.updateUi(table.getRow(row));
            theTable.addView(tableRowCell.getView());
        }
    }

    @Override
    public View createView(LayoutInflater inflater, View convertView, ViewGroup parent) {
        View view = inflater.inflate(R.layout.cell_table, parent);
        theTable = view.findViewById(R.id.theTable);
        return view;
    }
}
