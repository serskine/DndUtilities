package com.soupthatisthick.encounterbuilder.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.soupthatisthick.encounterbuilder.adapters.builder.EditEncounterPageAdapter;
import com.soupthatisthick.encounterbuilder.fragment.builder.CompendiumFragment;
import com.soupthatisthick.encounterbuilder.util.listeners.UiWatcher;
import com.soupthatisthick.util.activity.AppActivity;
import com.soupthatisthick.util.activity.EditActivity;
import com.soupthatisthick.util.fragment.StubFragment;

import soupthatisthick.encounterapp.R;

/**
 * Created by Owner on 4/18/2017.
 * Copyright of Stuart Marr Erskine, all rights reserved.
 */

public class TestActivity extends AppActivity {

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
    }

    public static class TestPageAdapter extends FragmentPagerAdapter {

        protected final Context context;

        public final StubFragment tab1 = new StubFragment();
        public final StubFragment tab2 = new StubFragment();
        public final CompendiumFragment tab3 = new CompendiumFragment();

        public enum PageId {
            TAB_1, TAB_2, TAB_3
        }

        public TestPageAdapter(FragmentManager fm, @NonNull Context context) {
            super(fm);
            this.context = context;
        }

        /**
         * Return the Fragment associated with a specified position.
         *
         * @param position
         */
        @Override
        public Fragment getItem(int position) {
            PageId pageId = PageId.values()[position];
            switch (pageId)
            {
                case TAB_1:
                    return tab1;
                case TAB_2:
                    return tab2;
                case TAB_3:
                    return tab3;
                default:
                    return null;
            }
        }

        @Override
        public CharSequence getPageTitle(int position) {
            // Generate title based on item position
            TestPageAdapter.PageId pageId = TestPageAdapter.PageId.values()[position];

            switch(pageId)
            {
                case TAB_1:
                    return context.getString(R.string.test_tab1);
                case TAB_2:
                    return context.getString(R.string.test_tab2);
                case TAB_3:
                    return context.getString(R.string.test_tab3);
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
}