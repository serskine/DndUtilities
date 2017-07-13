package com.soupthatisthick.encounterbuilder.model.builder;

import android.support.annotation.NonNull;

/**
 * Created by Owner on 5/29/2017.
 * Copyright of Stuart Marr Erskine, all rights reserved.
 */

public class EncounterStats {
    private final int numEnemies;
    private final int numAllies;
    private final XpThresholds xpThresholds;
    private final int unmodifiedTotalEnemyXp;

    public static final double[] MODIFIERS = {
        0d, 0.5d, 1d, 1.5d, 2d, 2.5d, 3d, 4d, 5d
    };


    public EncounterStats(
        int numAllies,
        @NonNull XpThresholds xpThresholds,
        int numEnemies,
        int unmodifiedTotalEnemyXp)
    {
        this.numAllies = numAllies;
        this.numEnemies = numEnemies;
        this.xpThresholds = new XpThresholds(
            xpThresholds.easy,
            xpThresholds.medium,
            xpThresholds.hard,
            xpThresholds.deadly
        );
        this.unmodifiedTotalEnemyXp = unmodifiedTotalEnemyXp;
    }

    public final int getNumEnemies() {
        return numEnemies;
    }

    public final int getNumAllies() {
        return numAllies;
    }

    public XpThresholds getXpThresholds() {
        return xpThresholds;
    }

    public final int getUnmodifiedTotalEnemyXp() {
        return unmodifiedTotalEnemyXp;
    }

    public final double getEnemyXpModifier()
    {
        return getEnemyXpModifier(numAllies, numEnemies);
    }

    public final double getModifiedTotalEnemyXp() {
        return getEnemyXpModifier() * getUnmodifiedTotalEnemyXp();
    }

    public final double getXpPerPlayer() {
        return (getNumAllies()<=0d) ? 0d : getUnmodifiedTotalEnemyXp() / getNumAllies();
    }

    public final ThreatLevel getThreatLevel()
    {
        XpThresholds xpThresholds = getXpThresholds();
        double modifiedEnemyXp = getModifiedTotalEnemyXp();

        if (modifiedEnemyXp < xpThresholds.easy)
        {
            return ThreatLevel.TRIVIAL;
        } else if (modifiedEnemyXp < xpThresholds.medium) {
            return ThreatLevel.EASY;
        } else if (modifiedEnemyXp < xpThresholds.hard) {
            return ThreatLevel.NORMAL;
        } else if (modifiedEnemyXp < xpThresholds.deadly) {
            return ThreatLevel.HARD;
        } else {
            return ThreatLevel.DEADLY;
        }
    }

    /**
     * This is used to determine the modifier we should use to modify the total enemy xp by when
     * determining the threat level of all the enemies
     * @param numAllies
     * @param numEnemies
     * @return
     */
    public static double getEnemyXpModifier(int numAllies, int numEnemies)
    {

        int rank=0;

        if (numEnemies==0) {
            return MODIFIERS[0];
        } else if (numEnemies==1) {
            rank = 2;
        } else if (numEnemies==2) {
            rank = 3;
        } else if (numEnemies<=6) {
            rank = 4;
        } else if (numEnemies<=10) {
            rank = 5;
        } else if (numEnemies<=14) {
            rank = 6;
        } else {
            rank = 7;
        }

        if (numAllies<3)
        {
            rank = Math.max(0, rank-1);
        } else if (numAllies>5) {
            rank = Math.min(MODIFIERS.length-1, rank+1);
        }

        return MODIFIERS[rank];
    }

    public String getDetailedString()
    {
        String output = "";
        output += " - num allies  = " + getNumAllies() + "\n";
        output += " - num enemies = " + getNumEnemies() + "\n";
        output += " Xp Thresholds\n";
        output += "  - easy   = " + getXpThresholds().easy + "\n";
        output += "  - medium = " + getXpThresholds().medium + "\n";
        output += "  - hard   = " + getXpThresholds().hard + "\n";
        output += "  - deadl  = " + getXpThresholds().deadly + "\n";
        output += " Threat Level  = " + getThreatLevel() + "\n";
        return output;
    }

}
