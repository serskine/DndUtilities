package com.soupthatisthick.encounterbuilder.activity.lookup;

import android.content.Context;
import android.os.AsyncTask;
import android.view.LayoutInflater;
import android.widget.EditText;

import com.soupthatisthick.encounterbuilder.adapters.lookup.CompendiumAdapter;
import com.soupthatisthick.util.Logger;
import com.soupthatisthick.encounterbuilder.util.adapter.CustomToggleAdapter;
import com.soupthatisthick.util.activity.ViewToggleListActivity;
import com.soupthatisthick.util.dao.DaoMaster;
import com.soupthatisthick.util.dao.WriteDao;

import java.util.List;

import soupthatisthick.encounterapp.R;

/**
 * Created by Owner on 4/12/2017.
 * Copyright of Stuart Marr Erskine, all rights reserved.
 */

public abstract class ExDaoEditActivity extends ViewToggleListActivity<Object> {

    private final Object DB_LOCK = new Object();
    private DaoMaster daoMaster;
    private WriteDao<Object> daoSession;

    protected abstract DaoMaster createDaoMaster(Context context);
    protected abstract WriteDao<Object> createDao(DaoMaster daoMaster);

    @Override
    public void loadAllData()
    {
        final AsyncTask<Object, Object, Object> loadTask = new AsyncTask<Object, Object, Object>() {

            /**
             * Override this method to perform a computation on a background thread. The
             * specified parameters are the parameters passed to {@link #execute}
             * by the caller of this task.
             * <p>
             * This method can call {@link #publishProgress} to publish updates
             * on the UI thread.
             *
             * @param params The parameters of the task.
             * @return A result, defined by the subclass of this task.
             * @see #onPreExecute()
             * @see #onPostExecute
             * @see #publishProgress
             */
            @Override
            protected Object doInBackground(Object... params) {
                synchronized (DB_LOCK) {
                    try {
                        daoMaster = createDaoMaster(getBaseContext());
                        Logger.info("Opening the DaoMaster for database " + daoMaster.getDatabaseName() + ".");

                        daoSession = createDao(daoMaster);
                        Logger.info("Opened a dao session on table " + daoSession.getTable() + " from dao master.");

                        return null;
                    } catch (Exception e) {
                        throw new RuntimeException("Failed to open all the dao's!", e);
                    }
                }
            }
        };
        loadTask.execute(null, null, null);

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_view_expandable_list;
    }

    @Override
    protected EditText findSearchEditText() {
        return (EditText) findViewById(R.id.theSearchEdit);
    }

    /**
     * Unless overriden, this method will return an {@link CompendiumAdapter} that is capable
     * of displaying any type of object. By default it will display the toString of any object type
     * it does not know about.
     * @param layoutInflater
     * @return a {@link CustomToggleAdapter<Object>} that will provide custom view for expanded and collapsed items.
     */
    @Override
    protected CustomToggleAdapter<Object> createToggleListAdapter(LayoutInflater layoutInflater) {
        return new CompendiumAdapter(layoutInflater);
    }

    @Override
    protected List<Object> searchForResults(String searchText) throws Exception {
        return daoSession.getRecordsLike(searchText);
    }

    @Override
    protected List<Object> getAllRecords() throws Exception {
        return daoSession.getAllRecords();
    }


}

