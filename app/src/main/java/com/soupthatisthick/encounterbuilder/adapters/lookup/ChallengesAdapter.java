package com.soupthatisthick.encounterbuilder.adapters.lookup;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.soupthatisthick.encounterbuilder.logic.Challenge;
import com.soupthatisthick.encounterbuilder.util.adapter.CustomListAdapter;
import com.soupthatisthick.encounterbuilder.view.cell.summary.ChallengeRatingSummaryCell;

/**
 * Created by Owner on 12/22/2016.
 * Copyright of Stuart Marr Erskine, all rights reserved.
 */

public class ChallengesAdapter extends CustomListAdapter<Challenge> {

    public ChallengesAdapter(LayoutInflater inflater) {
        super(inflater);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Challenge challenge = data.get(position);
        ChallengeRatingSummaryCell cell = new ChallengeRatingSummaryCell(inflater, convertView, parent);
//        cell.updateUi(challenge);
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
