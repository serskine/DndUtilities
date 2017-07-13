package com.soupthatisthick.encounterbuilder.util;

/**
 * Created by Owner on 3/3/2017.
 * Copyright of Stuart Marr Erskine, all rights reserved.
 */

public class Tables {
    public static final int getMinXp(int level)
    {
        if (level>20) {
            level = 20;
        }
        switch(level)
        {
            case 20:
                return 355000;
            case 19:
                return 305000;
            case 18:
                return 265000;
            case 17:
                return 225000;
            case 16:
                return 195000;
            case 15:
                return 165000;
            case 14:
                return 140000;
            case 13:
                return 120000;
            case 12:
                return 100000;
            case 11:
                return 85000;
            case 10:
                return 64000;
            case 9:
                return 48000;
            case 8:
                return 34000;
            case 7:
                return 23000;
            case 6:
                return 14000;
            case 5:
                return 6500;
            case 4:
                return 2700;
            case 3:
                return 900;
            case 2:
                return 300;
            case 1:
            case 0:
            default:
                return 0;
        }
    }
    public static final int getLevel(int xp)
    {
        int level = 0;
        while(xp >= getMinXp(level))
        {
            level++;
        }
        return level-1;
    }
}
