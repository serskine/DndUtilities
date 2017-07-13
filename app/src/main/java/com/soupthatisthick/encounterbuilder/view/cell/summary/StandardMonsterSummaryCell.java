package com.soupthatisthick.encounterbuilder.view.cell.summary;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.soupthatisthick.encounterbuilder.model.lookup.StandardMonster;
import com.soupthatisthick.encounterbuilder.view.cell.ReadCell;

import soupthatisthick.encounterapp.R;

/**
 * Created by Owner on 4/12/2017.
 * Copyright of Stuart Marr Erskine, all rights reserved.
 */

public class StandardMonsterSummaryCell extends ReadCell<StandardMonster> {
    public TextView theName;
    public TextView theTypeAndAlign;

    // TODO: Add this later
    public TextView theCr;
    public TextView theXp;

    public TextView theSource;

    private static final String SPACE = " ";
    private static final String DELIM = "," + SPACE;
    private static final String EMPTY = "";


    public StandardMonsterSummaryCell(LayoutInflater inflater, View convertView, ViewGroup parent) {
        super(inflater, convertView, parent);
    }

    public void updateUi(StandardMonster monster)
    {
        theName.setText(monster.getName());
        theTypeAndAlign.setText(monster.getType() + DELIM + monster.getAlignment());
        theSource.setText(myHtmlString(monster.getSource()));

        this.theCr.setText(titleString("Challenge: ", monster.getChallengeRating()));
        this.theXp.setText(UNKNOWN);
        checkVisibilities();

    }


    @Override
    public View createView(LayoutInflater inflater, View convertView, ViewGroup parent) {
        View view = inflater.inflate(R.layout.cell_standard_monster_summary, parent);

        this.theName = (TextView) view.findViewById(R.id.theName);
        this.theTypeAndAlign = (TextView) view.findViewById(R.id.theTypeAndAlign);
        this.theSource = (TextView) view.findViewById(R.id.theSource);
        this.theCr = (TextView) view.findViewById(R.id.theCr);
        this.theXp = (TextView) view.findViewById(R.id.theXp);

        checkVisibilities();

        return view;
    }


    private void checkVisibilities()
    {
        theSource.setVisibility(isEmpty(theSource.getText()) ? View.GONE : View.VISIBLE);
        theCr.setVisibility(isEmpty(theCr.getText()) ? View.GONE : View.VISIBLE);
        theXp.setVisibility(isEmpty(theXp.getText()) ? View.GONE : View.VISIBLE);
    }

    public static final String myHtmlString(String html)
    {
        String parsed = htmlString(html).toString();
//        String removeLeading = "\\s*<html>(\\s*<br>)*\\s*";
//        String removeTrailing = "\\s*<br>*\\s*</html>\\s*";
//        String removeListElements = "(<li>)|(</li>)";
//        String removeMultiLineBreaks = "<br>(\\s*<br>)*";
//        parsed = parsed.replace(removeLeading,EMPTY);
//        parsed = parsed.replace(removeTrailing, EMPTY);
//        parsed = parsed.replace(removeListElements, EMPTY);
//        parsed = parsed.replace(removeMultiLineBreaks, "<br>");

        return parsed;
    }

}
