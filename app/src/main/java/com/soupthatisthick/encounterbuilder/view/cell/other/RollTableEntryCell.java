package com.soupthatisthick.encounterbuilder.view.cell.other;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.soupthatisthick.encounterbuilder.model.lookup.RollTableEntry;
import com.soupthatisthick.encounterbuilder.view.cell.ReadCell;
import com.soupthatisthick.util.Logger;

import soupthatisthick.encounterapp.R;

/**
 * Created by Owner on 5/10/2017.
 * Copyright of Stuart Marr Erskine, all rights reserved.
 */

public class RollTableEntryCell extends ReadCell<RollTableEntry> {

    private TextView theRange, theQty, theReRoll, theUnit;

    public RollTableEntryCell(LayoutInflater inflater, View convertView, ViewGroup parent) {
        super(inflater, convertView, parent);
    }

    public void updateUiAsHeader()
    {
        theReRoll.setText(boldString("Re-Roll"));
        theUnit.setText(boldString("Unit"));
        theQty.setText(boldString("Qty"));
        theRange.setText(boldString("Range"));
    }

    /**
     * This will update the ui assuming the quantity has not yet been determined
     * @param rollTableEntry
     */
    @Override
    public void updateUi(RollTableEntry rollTableEntry) {
        updateUnitUi(rollTableEntry);
        updateRangeUi(rollTableEntry);
        updateQtyUi(rollTableEntry);
        updateReRollUi(rollTableEntry);
    }

    /**
     * This will update the ui assuming the quantity for the ui has been determined
     * @param rollTableEntry
     * @param qty
     */
    public void updateUi(RollTableEntry rollTableEntry, long qty)
    {
        updateUnitUi(rollTableEntry);
        updateRangeUi(rollTableEntry);
        updateQtyUi(rollTableEntry, qty);
        updateReRollUi(rollTableEntry);
    }

    protected void updateReRollUi(RollTableEntry entry)
    {
        if (entry.getReRollTableId()==null || entry.getReRollTableId()<1)
        {
            theReRoll.setText("");
        } else {
            theReRoll.setText("" + entry.getReRollTableId());
        }
    }

    protected void updateUnitUi(RollTableEntry entry)
    {
        String text = (entry.getUnitGpValue() <= 0.0d)
                ?   entry.getUnit() + " (priceless)"
                :   String.format("%s (%.2f gp each)",
                        entry.getUnit(),
                        entry.getUnitGpValue()
                    );
        Logger.debug(
            String.format(
                "[%d]%s (%f) => %s",
                entry.getId(),
                entry.getUnit(),
                entry.getUnitGpValue(),
                text
            ));

        theUnit.setText(
            text
        );
    }

    protected void updateRangeUi(RollTableEntry entry)
    {
        if (entry.getMinRoll() != entry.getMaxRoll()) {
            theRange.setText(String.format("[%d-%d]", entry.getMinRoll(), entry.getMaxRoll()));
        } else {
            theRange.setText(String.format("[%d]", entry.getMinRoll()));
        }
    }

    protected void updateQtyUi(RollTableEntry entry)
    {
        String text = "";
        if (entry.getDieSize()==1) {
            text = "" + entry.getRollAvg();
        } else {
            text = String.format(
                    "%dd%d%s (%d)",
                    entry.getDieQty(),
                    entry.getDieSize(),
                    ((entry.getRollMul()!=1) ? ("x" + entry.getRollMul()) : ""),
                    entry.getRollAvg()
            );
        }
        theQty.setText(text);
    }

    public void updateQtyUi(RollTableEntry entry, long qtyValue)
    {
        theQty.setText("" + qtyValue);
    }

    @Override
    public View createView(LayoutInflater inflater, View convertView, ViewGroup parent) {
        View view = inflater.inflate(R.layout.cell_roll_table_entry, parent);
        theRange = (TextView) view.findViewById(R.id.theRange);
        theReRoll = (TextView) view.findViewById(R.id.theReroll);
        theQty = (TextView) view.findViewById(R.id.theQuantity);
        theUnit = (TextView) view.findViewById(R.id.theResult);
        return view;
    }

}
