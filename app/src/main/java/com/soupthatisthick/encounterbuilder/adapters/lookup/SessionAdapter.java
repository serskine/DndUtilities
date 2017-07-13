package com.soupthatisthick.encounterbuilder.adapters.lookup;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.soupthatisthick.encounterbuilder.model.lookup.Session;
import com.soupthatisthick.encounterbuilder.util.adapter.CustomListAdapter;
import com.soupthatisthick.encounterbuilder.view.cell.summary.SessionCell;
import com.soupthatisthick.encounterbuilder.view.cell.ReadCell;

/**
 * Created by Owner on 2/5/2017.
 * Copyright of Stuart Marr Erskine, all rights reserved.
 */

public class SessionAdapter extends CustomListAdapter<Session> {
    public SessionAdapter(LayoutInflater inflater) {
        super(inflater);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ReadCell<Session> cell = new SessionCell(inflater, convertView, parent);
        Session session = (Session) getItem(position);
        cell.updateUi(session);    // Tell it it is a summary
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
}
