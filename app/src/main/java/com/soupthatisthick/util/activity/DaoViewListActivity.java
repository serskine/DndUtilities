package com.soupthatisthick.util.activity;

import android.content.Context;

import com.soupthatisthick.util.dao.ReadDao;
import com.soupthatisthick.util.dao.DaoMaster;
import com.soupthatisthick.util.Logger;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Owner on 2/9/2017.
 * Copyright of Stuart Marr Erskine, all rights reserved.
 */

public abstract class DaoViewListActivity<DataType> extends ViewListActivity<DataType> {


    private ReadDao<DataType> readDao;
    protected abstract DaoMaster createDaoMaster(Context context) throws Exception;

    protected abstract ReadDao<DataType> createReadDao(DaoMaster db) throws Exception;


    @Override
    protected void onStop() {
        super.onStop();
        Logger.debug("onStop()");

        if (db != null) {
            db.close();
            db = null;
        }
    }


    @Override
    protected void loadAllData() {
        try {
            db = createDaoMaster(getBaseContext());
            readDao = createReadDao(db);
            Logger.info("Opened readDao for table " + readDao.getTable() + ".");
            readDao.logContents();
        } catch (Exception e) {
            throw new RuntimeException("Failed to open the readDao!", e);
        }
    }

    /**
     * This is the default search method used to look for results within the Dao
     * @param searchText is the text we want to search for
     * @return a {@link List <DataType>} containing the results. If an exception
     * occurred the results list will be empty.
     * @throws IOException
     */
    protected List<DataType> searchForResults(String searchText) throws Exception
    {
        List<DataType> results;
        Logger.info("SEARCHING FOR '" + searchText + "'");
        if (searchText.length()>0)
        {
            try {
                results = getReadDao().getRecordsLike(searchText);
            } catch (IOException e) {
                Logger.error("Could not get records like '" + searchText + "'", e);
                results = new ArrayList<>();
            }
        } else {
            try {
                results = getReadDao().getAllRecords();
            } catch (IOException e) {
                Logger.error("Could not get all records.", e);
                results = new ArrayList<>();
            }
        }

        return results;
    }


    protected List<DataType> getAllRecords() throws Exception {
        return getReadDao().getAllRecords();
    }

    protected final ReadDao<DataType> getReadDao()
    {
        return this.readDao;
    }



}
