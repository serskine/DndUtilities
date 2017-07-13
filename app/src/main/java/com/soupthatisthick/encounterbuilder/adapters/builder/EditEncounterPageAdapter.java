package com.soupthatisthick.encounterbuilder.adapters.builder;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.soupthatisthick.encounterbuilder.fragment.builder.MeasureEncounterAlliesFragment;
import com.soupthatisthick.encounterbuilder.fragment.builder.MeasureEncounterEnemiesFragment;
import com.soupthatisthick.encounterbuilder.fragment.builder.MeasureEncounterStatsFragment;

import soupthatisthick.encounterapp.R;

/**
 * Created by Owner on 5/30/2017.
 * Copyright of Stuart Marr Erskine, all rights reserved.
 */

public class EditEncounterPageAdapter extends FragmentPagerAdapter {

    protected final Context context;

    public EditEncounterPageAdapter(FragmentManager fm, @NonNull Context context) {
        super(fm);
        this.context = context;
    }

    // The order of the enumeratio
    private enum PageId
    {
        ALLIES,
        STATS,
        ENEMIES
    }

    public final MeasureEncounterAlliesFragment measureEncounterAlliesFragment = new MeasureEncounterAlliesFragment();
    public final MeasureEncounterEnemiesFragment measureEncounterEnemiesFragment = new MeasureEncounterEnemiesFragment();
    public final MeasureEncounterStatsFragment measureEncounterStatsFragment = new MeasureEncounterStatsFragment();

    /**
     * Return the Fragment associated with a specified position.
     *
     * @param position
     */
    @Override
    public Fragment getItem(int position) {
        PageId pageId = PageId.values()[position];
        switch(pageId)
        {
            case ALLIES:
                return measureEncounterAlliesFragment;
            case ENEMIES:
                return measureEncounterEnemiesFragment;
            case STATS:
                return measureEncounterStatsFragment;
            default:
                return null;
        }
    }


    @Override
    public CharSequence getPageTitle(int position) {
        // Generate title based on item position
        PageId pageId = PageId.values()[position];

        switch(pageId)
        {
            case STATS:
                return context.getString(R.string.ee_tab_stats);
            case ALLIES:
                return context.getString(R.string.ee_tab_allies);
            case ENEMIES:
                return context.getString(R.string.ee_tab_enemies);
            default:
                return null;
        }
    }

    /**
     * Return the number of views available.
     */
    @Override
    public int getCount() {
        return PageId.values().length;
    }


}
