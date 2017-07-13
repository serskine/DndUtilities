package com.soupthatisthick.encounterbuilder.model.builder;

/**
 * Created by Owner on 5/29/2017.
 * Copyright of Stuart Marr Erskine, all rights reserved.
 */

public class XpThresholds {
    public final int easy;
    public final int medium;
    public final int hard;
    public final int deadly;

    public XpThresholds()
    {
        this(0,0,0,0);
    }
    public XpThresholds(int easy, int medium, int hard, int deadly)
    {
        this.easy = easy;
        this.medium = medium;
        this.hard = hard;
        this.deadly = deadly;
    }
}
