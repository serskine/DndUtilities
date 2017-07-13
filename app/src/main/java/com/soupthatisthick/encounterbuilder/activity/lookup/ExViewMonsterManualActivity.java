package com.soupthatisthick.encounterbuilder.activity.lookup;

import android.os.AsyncTask;
import android.view.LayoutInflater;
import android.widget.EditText;
import android.widget.ExpandableListView;

import com.soupthatisthick.encounterbuilder.adapters.lookup.ExStandardMonsterAdapter;
import com.soupthatisthick.encounterbuilder.dao.lookup.StandardMonsterDao;
import com.soupthatisthick.encounterbuilder.dao.master.DndMaster;
import com.soupthatisthick.encounterbuilder.model.lookup.StandardMonster;
import com.soupthatisthick.util.Logger;
import com.soupthatisthick.encounterbuilder.util.adapter.CustomToggleAdapter;
import com.soupthatisthick.util.activity.ViewToggleListActivity;

import java.util.List;

import soupthatisthick.encounterapp.R;

/**
 * Created by Owner on 4/12/2017.
 * Copyright of Stuart Marr Erskine, all rights reserved.
 */

public class ExViewMonsterManualActivity extends ViewToggleListActivity<StandardMonster> {

    private final Object DB_LOCK = new Object();
    private DndMaster db;
    private StandardMonsterDao newStandardMonsterDao;

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
                        db = new DndMaster(getBaseContext());
                        Logger.info("Opening the DaoMaster for database " + db.getDatabaseName() + ".");

                        newStandardMonsterDao = new StandardMonsterDao(db);
                        Logger.info("Opened newStandardMonsterDao for table " + newStandardMonsterDao.getTable() + ".");
                        newStandardMonsterDao.logSchema();

                        Logger.info("Completed opening all dao's for the compendium.");
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

    @Override
    protected ExpandableListView findExpandableListView() {
        return (ExpandableListView) findViewById(R.id.theExpandableListView);
    }

    @Override
    protected CustomToggleAdapter<StandardMonster> createToggleListAdapter(LayoutInflater layoutInflater) {
        return new ExStandardMonsterAdapter(layoutInflater);
    }

    @Override
    protected List<StandardMonster> searchForResults(String searchText) throws Exception {
        return newStandardMonsterDao.getRecordsLike(searchText);
    }

    @Override
    protected List<StandardMonster> getAllRecords() throws Exception {
        return newStandardMonsterDao.getAllRecords();
    }
}