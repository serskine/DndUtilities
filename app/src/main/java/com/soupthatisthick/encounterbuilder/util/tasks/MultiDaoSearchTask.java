package com.soupthatisthick.encounterbuilder.util.tasks;

import com.soupthatisthick.util.Logger;
import com.soupthatisthick.util.dao.ReadDao;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Owner on 6/22/2017.
 * Copyright of Stuart Marr Erskine, all rights reserved.
 */

public abstract class MultiDaoSearchTask extends SearchTask<Object> {

    public final ArrayList<ReadDao<?>> readDaos;
    public final boolean failOneFailAll;

    /**
     * Remember to add the dao's to the search task before executing. This assumes failing to read data from one data will not
     * prevent the other dao's from potentially adding results.
     */
    public MultiDaoSearchTask()
    {
        this(false);
    }

    /**
     * Creates a new multidao search task.
     * @param failOneFailAll determines if we want to fail the complete search when one of the dao's
     *                       fails to complete it's search. When false, if one dao fails the other
     *                       may still add results to the list.
     */
    public MultiDaoSearchTask(boolean failOneFailAll)
    {
        this.failOneFailAll = failOneFailAll;
        readDaos = new ArrayList<>();
    }


    @Override
    protected List<Object> searchForResults(String searchText) throws Exception {
        List<Object> results = new ArrayList<>();
        for(int i=0; i<readDaos.size(); i++)
        {
            ReadDao<?> dao = readDaos.get(i);
            try {
                results.addAll(dao.getRecordsLike(searchText));
            } catch (Exception e) {
                if (failOneFailAll) {
                    throw new RuntimeException("Failed to read from dao[" + i + "].", e);
                } else {
                    Logger.warning("Failed to read records from dao[" + i + "]");
                }
            }
        }
        return results;
    }

    @Override
    protected abstract void onSearchCompleted(List<Object> results);
}
