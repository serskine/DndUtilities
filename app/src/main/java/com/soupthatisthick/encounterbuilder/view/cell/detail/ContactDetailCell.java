package com.soupthatisthick.encounterbuilder.view.cell.detail;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.soupthatisthick.encounterbuilder.model.lookup.Contact;
import com.soupthatisthick.encounterbuilder.view.cell.ReadCell;

import soupthatisthick.encounterapp.R;

/**
 * Created by Owner on 5/1/2017.
 * Copyright of Stuart Marr Erskine, all rights reserved.
 */

public class ContactDetailCell extends ReadCell<Contact> {

    public TextView theTitle, theDci;

    public ContactDetailCell(LayoutInflater inflater, View convertView, ViewGroup parent) {
        super(inflater, convertView, parent);
    }

    @Override
    public View createView(LayoutInflater inflater, View convertView, ViewGroup parent) {
        View view = inflater.inflate(R.layout.cell_contact_detail, parent);

        theTitle = (TextView) view.findViewById(R.id.theTitle);
        theDci = (TextView) view.findViewById(R.id.theDci);

        return view;
    }

    /**
     * This will update the UI according to the data provided by the MagicItem
     * @param item
     */
    public void updateUi(Contact item)
    {
        // If the title is blank, then we will use it's id to display it instead. However we
        // do NOT search by the id field, only the title field
        theTitle.setText(
                isEmpty(item.getName())
                        ?   getView().getResources().getString(R.string.cell_contact_name, item.getId())
                        :   item.getName()
        );
        theDci.setText(item.getDci());

        checkVisibility();
    }


    private void checkVisibility()
    {
        theDci.setVisibility(isEmpty(theDci.getText()) ? View.GONE : View.VISIBLE);
    }
}
