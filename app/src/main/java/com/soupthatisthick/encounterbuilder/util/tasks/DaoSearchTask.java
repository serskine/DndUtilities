package com.soupthatisthick.encounterbuilder.util.tasks;

import android.support.annotation.NonNull;

import com.soupthatisthick.util.dao.ReadDao;

import java.util.List;

/**
 * Created by Owner on 6/22/2017.
 * Copyright of Stuart Marr Erskine, all rights reserved.
 */

public abstract class DaoSearchTask<DataType> extends SearchTask<DataType> {

    protected final ReadDao<DataType> dao;

    public DaoSearchTask(@NonNull ReadDao<DataType> dao)
    {
        this.dao = dao;
    }

    @Override
    protected final List<DataType> searchForResults(String searchText) throws Exception {
        return dao.getRecordsLike(searchText);
    }
}
