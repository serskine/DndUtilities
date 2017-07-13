package com.soupthatisthick.encounterbuilder.activity.lookup;

import android.graphics.Bitmap;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.print.PrintHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ListView;

import com.soupthatisthick.encounterbuilder.dao.lookup.StandardMonsterDao;
import com.soupthatisthick.encounterbuilder.printing.PageBounds;
import com.soupthatisthick.encounterbuilder.util.adapter.CustomListAdapter;
import com.soupthatisthick.encounterbuilder.view.cell.PageCell;
import com.soupthatisthick.util.Logger;
import com.soupthatisthick.util.activity.ViewListActivity;
import com.soupthatisthick.util.dao.DaoMaster;

import java.util.List;

import soupthatisthick.encounterapp.R;

/**
 * Created by Owner on 4/29/2017.
 * Copyright of Stuart Marr Erskine, all rights reserved.
 */

public abstract class PrintPagesActivity<PageModel> extends ViewListActivity<PageModel> {

    DaoMaster daoMaster = null;
    StandardMonsterDao sessionDao = null;

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
    protected abstract CustomListAdapter<PageModel> createListAdapter(LayoutInflater layoutInflater);

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
     * We need to be able to create a PageCell because the PageCell is used to determine how to print
     * onto the fields.
     * @param inflater
     * @param convertView
     * @param parent
     * @return
     */
    protected abstract PageCell<PageModel> createCell(@NonNull LayoutInflater inflater, @Nullable View convertView, @Nullable ViewGroup parent);

    /**
     * This will use the logsheet page information to print to the printer.
     * @param jobName
     * @param page
     */
    protected final void printPage(@NonNull String jobName, @Nullable PageModel page)
    {
        try {
            if (page != null) {
                PageCell<PageModel> cell = createCell(getLayoutInflater(), null, null);
                PageBounds pageBounds = cell.createPageBounds();
                Bitmap bitmap = cell.createBitmap(
                        getResources(),
                        page,
                        pageBounds
                );
                printBitmap(jobName, bitmap);
            } else {
                throw new RuntimeException("Attempted to print a null page!");
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
            List<PageModel> pages = getAllRecords();

            for(int i=0; i<pages.size(); i++)
            {
                PageModel pageModel = pages.get(i);
                printPage("PageBounds " + (i+1), pageModel);
            }
        } catch (Exception e) {
            Logger.error("Failed to print the pages. " , e);
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

