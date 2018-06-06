package com.soupthatisthick.encounterbuilder.activity.lookup;

import android.content.Context;
import android.view.LayoutInflater;
import android.widget.EditText;
import android.widget.ListView;

import com.soupthatisthick.encounterbuilder.dao.lookup.EntityListDao;
import com.soupthatisthick.encounterbuilder.dao.master.DndMaster;
import com.soupthatisthick.encounterbuilder.model.lookup.EntityList;
import com.soupthatisthick.encounterbuilder.util.adapter.CustomListAdapter;
import com.soupthatisthick.util.activity.DaoViewListActivity;
import com.soupthatisthick.util.dao.DaoMaster;
import com.soupthatisthick.util.dao.ReadDao;

import soupthatisthick.encounterapp.R;

/**
 * Created by Owner on 4/21/2017.
 * Copyright of Stuart Marr Erskine, all rights reserved.
 */

public class ExViewListsActivity extends DaoViewListActivity<EntityList> {
    @Override
    protected DaoMaster createDaoMaster(Context context) throws Exception {
        return new DndMaster(context);
    }

    @Override
    protected ReadDao<EntityList> createReadDao(DaoMaster db) throws Exception {
        return new EntityListDao(db);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_view_expandable_list;
    }

    @Override
    protected EditText findSearchEditText() {
        return null;
    }

    @Override
    protected ListView findListView() {
        return null;
    }

    @Override
    protected CustomListAdapter<EntityList> createListAdapter(LayoutInflater layoutInflater) {
        return null;
    }
}
