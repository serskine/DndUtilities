package com.soupthatisthick.encounterbuilder.model.lookup;

/**
 * Created by Owner on 2/13/2017.
 * Copyright of Stuart Marr Erskine, all rights reserved.
 */

public class LogsheetSummary {
    private int totalXp = 0;
    private int totalGold = 0;
    private int totalDowntime = 0;
    private int totalRenown = 0;
    private int totalMagicItems = 0;

    public int getTotalXp() {
        return totalXp;
    }

    public void setTotalXp(int totalXp) {
        this.totalXp = totalXp;
    }

    public int getTotalGold() {
        return totalGold;
    }

    public void setTotalGold(int totalGold) {
        this.totalGold = totalGold;
    }

    public int getTotalDowntime() {
        return totalDowntime;
    }

    public void setTotalDowntime(int totalDowntime) {
        this.totalDowntime = totalDowntime;
    }

    public int getTotalRenown() {
        return totalRenown;
    }

    public void setTotalRenown(int totalRenown) {
        this.totalRenown = totalRenown;
    }

    public int getTotalMagicItems() {
        return totalMagicItems;
    }

    public void setTotalMagicItems(int totalMagicItems) {
        this.totalMagicItems = totalMagicItems;
    }
}
