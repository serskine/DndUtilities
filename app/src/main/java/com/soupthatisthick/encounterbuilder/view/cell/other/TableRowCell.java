package com.soupthatisthick.encounterbuilder.view.cell.other;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TableRow;

import com.soupthatisthick.encounterbuilder.view.cell.ReadCell;
import com.soupthatisthick.util.string.Table;

import java.util.List;

import soupthatisthick.encounterapp.R;

public class TableRowCell extends ReadCell<Table.Cell[]> {

    TableRow tableRow;

    public TableRowCell(LayoutInflater inflater, View convertView, ViewGroup parent) {
        super(inflater, convertView, parent);
    }

    @Override
    public void updateUi(Table.Cell[] row) {
        tableRow.removeAllViews();
        for(Table.Cell cell : row) {
            TableCellCell tableCellCell = new TableCellCell(inflater, null, tableRow);
            tableCellCell.updateUi(cell);
            tableRow.addView(tableCellCell.getView());
        }
    }

    @Override
    public View createView(LayoutInflater inflater, View convertView, ViewGroup parent) {
        View view = inflater.inflate(R.layout.cell_table_row, parent);
        tableRow = view.findViewById(R.id.theTableRow);
        return view;
    }
}
