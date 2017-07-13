package com.soupthatisthick.encounterbuilder.activity.lookup;

import android.content.Intent;
import android.graphics.Bitmap;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.print.PrintHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;

import com.soupthatisthick.encounterbuilder.adapters.lookup.LogsheetPageAdapter;
import com.soupthatisthick.encounterbuilder.printing.model.LogsheetPage;
import com.soupthatisthick.encounterbuilder.util.adapter.CustomListAdapter;
import com.soupthatisthick.util.activity.ViewListActivity;
import com.soupthatisthick.util.dao.DaoMaster;
import com.soupthatisthick.encounterbuilder.dao.lookup.PcDao;
import com.soupthatisthick.encounterbuilder.dao.lookup.SessionDao;
import com.soupthatisthick.encounterbuilder.dao.master.LogsheetMaster;
import com.soupthatisthick.encounterbuilder.model.lookup.Pc;
import com.soupthatisthick.encounterbuilder.model.lookup.Session;
import com.soupthatisthick.encounterbuilder.printing.bounds.LogSheetPageBounds;
import com.soupthatisthick.util.Logger;
import com.soupthatisthick.encounterbuilder.view.cell.summary.LogsheetPageCell;

import java.util.ArrayList;
import java.util.List;

import soupthatisthick.encounterapp.R;

/**
 * Created by Owner on 3/3/2017.
 * Copyright of Stuart Marr Erskine, all rights reserved.
 */

public class PrintLogsheetActivity extends ViewListActivity<LogsheetPage> {

    private DaoMaster daoMaster = null;
    private PcDao pcDao = null;
    private SessionDao sessionDao = null;

    public static final String KEY_PC_ID = "KEY_PC_ID";

    protected Long thePcId = null;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_print_logsheet;
    }

    @Override
    protected EditText findSearchEditText() {
        return (EditText) findViewById(R.id.theSearchEdit);
    }

    @Override
    protected ListView findListView() {
        return (ListView) findViewById(R.id.theListView);
    }

    @Override
    protected CustomListAdapter<LogsheetPage> createListAdapter(LayoutInflater layoutInflater) {
        return new LogsheetPageAdapter(layoutInflater);
    }

    @Override
    protected List<LogsheetPage> searchForResults(String searchText) throws Exception {
        return getAllRecords();
    }

    @Override
    protected List<LogsheetPage> getAllRecords() throws Exception {
        List<LogsheetPage> pages = new ArrayList<>();
        Pc character = pcDao.load(thePcId);
        try {
            List<Session> sessions = sessionDao.getSessionsFor(thePcId);
            for (int i = 0; i < sessions.size(); i += 3) {
                Session session1, session2, session3;
                session1 = sessions.get(i);
                session2 = (sessions.size()-i>1) ? sessions.get(i+1) : null;
                session3 = (sessions.size()-i>2) ? sessions.get(i+2) : null;

                LogsheetPage page = new LogsheetPage(character, pages.size()+1, session1, session2, session3);

                pages.add(page);
            }
        } catch (Exception e) {
            Logger.error("Failed to load the pages for character id " + thePcId, e);
        }
        Logger.info("Created " + pages.size() + " pages");
        return pages;
    }


    @Override
    protected void loadAllData() {
        try {
            daoMaster = new LogsheetMaster(getBaseContext());
            pcDao = new PcDao(daoMaster);
            sessionDao = new SessionDao(daoMaster);

            Intent intent = getIntent();
            thePcId = intent.getLongExtra(KEY_PC_ID, -1);
            if (thePcId==null || thePcId<1)
            {
                throw new RuntimeException("We don't know the id of the character we want to load sessions for.");
            } else {
                Logger.info("Loading sessions for character " + thePcId);
                List<LogsheetPage> pages = getAllRecords();
                theListAdapter.setData(pages);
            }
        } catch (Exception e) {
            Logger.error(e.getMessage(), e);
            finish();
        }
    }

    @Override
    protected void onStop()
    {
        super.onStop();
        DaoMaster.close(daoMaster);
    }

    /**
     * This will send the bitmap to the printer
     * @param jobName
     * @param bitmap
     */
    private void printBitmap(String jobName, Bitmap bitmap) {
        PrintHelper photoPrinter = new PrintHelper(this);
        photoPrinter.setScaleMode(PrintHelper.SCALE_MODE_FIT);
        photoPrinter.printBitmap(jobName, bitmap);
    }

    /**
     * This will use the logsheet page information to print to the printer
     * @param jobName
     * @param page
     */
    private void printLogsheetPage(@NonNull String jobName, @Nullable LogsheetPage page)
    {
        try {
            if (page != null) {
                LogSheetPageBounds bounds = new LogSheetPageBounds();
                LogsheetPageCell cell = new LogsheetPageCell(getLayoutInflater(), null, null);
                Bitmap bitmap = cell.createBitmap(
                        getResources(),
                        page,
                        bounds
                );
                printBitmap(jobName, bitmap);
            } else {
                throw new RuntimeException("Attempted to print a null log sheet page!");
            }
        } catch (Exception e) {
            Logger.error(e.getMessage(), e);
        }
    }

    /**
     * Called when we click the print logsheet button. This will cause all
     * selected sheets to be printed.
     * @param view
     */
    public void onClickPrintSheetButton(View view) {
        try {
            List<LogsheetPage> pages = getAllRecords();

            for(int i=0; i<pages.size(); i++)
            {
                LogsheetPage page = pages.get(i);
                printLogsheetPage("Logsheet PageBounds " + (i+1), page);
            }
        } catch (Exception e) {
            Logger.error("Failed to print the logsheets. " , e);
        }

    }

    /**
     * Called when we click the cancel button
     * @param view
     */
    public void onClickCancelButton(View view)
    {
        finish();
    }
}



