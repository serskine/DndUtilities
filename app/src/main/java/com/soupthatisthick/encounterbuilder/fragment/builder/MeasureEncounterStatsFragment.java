package com.soupthatisthick.encounterbuilder.fragment.builder;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.soupthatisthick.encounterbuilder.model.builder.EncounterStats;
import com.soupthatisthick.encounterbuilder.model.builder.ThreatLevel;
import com.soupthatisthick.encounterbuilder.model.builder.XpThresholds;
import com.soupthatisthick.util.Logger;

import soupthatisthick.encounterapp.R;

/**
 * Created by Owner on 5/29/2017.
 * Copyright of Stuart Marr Erskine, all rights reserved.
 */

public class MeasureEncounterStatsFragment extends Fragment {

    public interface Listener
    {
        void clearAll();
    }

    private ImageView theThreatPicture;

    private TextView theThreatLevel, theEasyThreshold, theMediumThreshold, theHardThreshold, theDeadlyThreshold;
    private TextView theEnemyXpFormula, theAllyXpFormula;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_encounter_stats, container, false);

        theThreatPicture = (ImageView) view.findViewById(R.id.theThreatPicture);

        theThreatLevel = (TextView) view.findViewById(R.id.theThreatLevel);
        theEasyThreshold = (TextView) view.findViewById(R.id.theEasyThreshold);
        theMediumThreshold = (TextView) view.findViewById(R.id.theMediumThreshold);
        theHardThreshold = (TextView) view.findViewById(R.id.theHardThreshold);
        theDeadlyThreshold = (TextView) view.findViewById(R.id.theDeadlyThreshold);

        theEnemyXpFormula = (TextView) view.findViewById(R.id.theEnemyXpFormula);
        theAllyXpFormula = (TextView) view.findViewById(R.id.theAllyXpFormula);

        return view;
    }

    /**
     * This will update the UI with the provided stats
     * @param encounterStats
     */
    public void updateUi(@NonNull EncounterStats encounterStats) {

        try {
            if (isAdded()) {
                XpThresholds xpThresholds = encounterStats.getXpThresholds();

                updateThreatUi(encounterStats.getThreatLevel());

                theEasyThreshold.setText(getString(R.string.fes_xp, xpThresholds.easy));
                theMediumThreshold.setText(getString(R.string.fes_xp, xpThresholds.medium));
                theHardThreshold.setText(getString(R.string.fes_xp, xpThresholds.hard));
                theDeadlyThreshold.setText(getString(R.string.fes_xp, xpThresholds.deadly));

                theEnemyXpFormula.setText(
                        getString(
                                R.string.fes_enemy_xp_formula,
                                (float) encounterStats.getEnemyXpModifier(),
                                (int) encounterStats.getUnmodifiedTotalEnemyXp(),
                                (int) encounterStats.getModifiedTotalEnemyXp()
                        )
                );

                theAllyXpFormula.setText(
                        getString(
                                R.string.fes_ally_xp_formula,
                                (int) encounterStats.getUnmodifiedTotalEnemyXp(),
                                (int) encounterStats.getNumAllies(),
                                (int) encounterStats.getXpPerPlayer()
                        )
                );
            }
        } catch (Exception e) {
            Logger.error("Failed to update the MeasureEncounterStatsFragment Ui", e);
        }
    }


    public void updateThreatUi(@NonNull ThreatLevel threatLevel)
    {
        try {
            int drawableResourceId;

            switch (threatLevel) {
                case TRIVIAL:
                    theThreatLevel.setText(R.string.fes_trivial);
                    drawableResourceId = R.drawable.ic_threat_trivial;
                    break;
                case EASY:
                    theThreatLevel.setText(R.string.fes_easy);
                    drawableResourceId = R.drawable.ic_threat_easy;
                    break;
                case NORMAL:
                    theThreatLevel.setText(R.string.fes_normal);
                    drawableResourceId = R.drawable.ic_threat_normal;
                    break;
                case HARD:
                    theThreatLevel.setText(R.string.fes_hard);
                    drawableResourceId = R.drawable.ic_threat_hard;
                    break;
                case DEADLY:
                    theThreatLevel.setText(R.string.fes_deadly);
                    drawableResourceId = R.drawable.ic_threat_deadly;
                    break;
                default:
                    theThreatLevel.setText(R.string.test_to_be_implemented);
                    drawableResourceId = R.drawable.ic_control_info;
                    break;
            }

            Drawable imageDrawable = getView().getResources().getDrawable(drawableResourceId);
            theThreatPicture.setImageDrawable(imageDrawable);
        } catch (Exception e) {
            Logger.error("Failed to update the MeasureEncounterStatsFragment", e);
        }
    }
}
