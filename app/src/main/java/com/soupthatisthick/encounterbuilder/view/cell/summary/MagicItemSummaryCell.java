package com.soupthatisthick.encounterbuilder.view.cell.summary;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.soupthatisthick.encounterbuilder.model.lookup.MagicItem;
import com.soupthatisthick.encounterbuilder.util.Text;
import com.soupthatisthick.encounterbuilder.view.cell.ReadCell;

import soupthatisthick.encounterapp.R;

/**
 * Created by Owner on 4/12/2017.
 * Copyright of Stuart Marr Erskine, all rights reserved.
 */

public class MagicItemSummaryCell extends ReadCell<MagicItem> {

    public TextView theName, theType, theLocation, theTreasureValue;

    public MagicItemSummaryCell(LayoutInflater inflater, View convertView, ViewGroup parent) {
        super(inflater, convertView, parent);
    }

    @Override
    public View createView(LayoutInflater inflater, View convertView, ViewGroup parent) {
        View view = inflater.inflate(R.layout.cell_magic_item_summary, parent);

        theName = (TextView) view.findViewById(R.id.theName);
        theType = (TextView) view.findViewById(R.id.theType);
        theLocation = (TextView) view.findViewById(R.id.theLocation);
        theTreasureValue = (TextView) view.findViewById(R.id.theTreasureValue);

        return view;
    }

    /**
     * This will update the UI according to the data provided by the MagicItem
     * @param item
     */
    public void updateUi(MagicItem item)
    {
        theName.setText(item.getName());

        String attunementField = isEmpty(item.getAttunement()) ? "" : " (" + stripBrackets(item.getAttunement()) + ")";

        String typeLine = item.getType() + ", " + item.getRarity() + attunementField;
        theType.setText(typeLine);

        theLocation.setText(item.getLocation());


        final String treasureField = "" + Text.toString(item.getTreasureTableText()) + " (" + item.getTreasurePoints() + " TCP)";
        theTreasureValue.setText(
                (item.getTreasurePoints()==0)
                        ?   ""
                        :   titleString(
                        ((item.getTreasureTables().size() > 1) ? "Treasure Tables: " :"Treasure Table: "),
                        treasureField
                )
        );
        checkVisibility();
    }

    private static final String stripBrackets(String text)
    {
        text = text.replaceAll("\\(","");
        text = text.replaceAll("\\)","");
        return text;
    }

    private void checkVisibility()
    {
        theLocation.setVisibility(isEmpty(theLocation.getText()) ? View.GONE : View.VISIBLE);
        theTreasureValue.setVisibility(isEmpty(theTreasureValue.getText()) ? View.GONE : View.VISIBLE);
    }
}
