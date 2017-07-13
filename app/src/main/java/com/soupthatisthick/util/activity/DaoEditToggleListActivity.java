package com.soupthatisthick.util.activity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.View;
import android.widget.EditText;
import android.widget.ExpandableListView;

import com.soupthatisthick.util.dao.DaoMaster;
import com.soupthatisthick.util.dao.WriteDao;
import com.soupthatisthick.util.Logger;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import soupthatisthick.encounterapp.R;

/**
 * Created by Owner on 2/17/2017.
 * Copyright of Stuart Marr Erskine, all rights reserved.
 */

public abstract class DaoEditToggleListActivity<Mast, Detail> extends EditToggleListActivity<Mast, Detail>
{
    private WriteDao<Detail> writeDao;

    protected final WriteDao<Detail> getWriteDao()
    {
        return this.writeDao;
    }

    protected abstract DaoMaster createDaoMaster(Context context) throws Exception;
    protected abstract void requestEditDetail(Long detailId, Detail detail, boolean deleteOnCancel);

    // These will provide the resource id's for the strings used in the confirmation dialogs
    protected abstract int getClearListTitleStringId();
    protected abstract int getClearListMessageStringId();
    protected abstract int getDeleteDetailTitleStringId();
    protected abstract int getDeleteDetailMessageStringId();


    @Override
    protected int getLayoutId()
    {
        return R.layout.activity_edit_toggle_list;
    }

    @Override
    protected ExpandableListView findExpandableListView()
    {
        return (ExpandableListView) findViewById(R.id.ml_list_view);
    }

    @Override
    protected EditText findSearchEditText()
    {
        return (EditText) findViewById(R.id.ml_search_edit);
    }

    protected abstract WriteDao<Detail> createWriteDao(DaoMaster db) throws Exception;

    @Override
    protected void onResume()
    {
        Logger.debug("onResume()");
        try {
            DaoMaster daoMaster = createDaoMaster(getBaseContext());
            writeDao = createWriteDao(daoMaster);
        } catch (Exception e) {
            Logger.error(e.getMessage(), e);

            finish();
        }
        super.onResume();
    }

    @Override
    protected void onPause() {
        Logger.debug("onPause()");

        super.onPause();

        DaoMaster.close(db);
    }


    @Override
    protected void loadAllData() {
        Logger.debug("loadAllData()");
        try {
            db = createDaoMaster(getBaseContext());
            writeDao = createWriteDao(db);
            Logger.info("Opened writeDao for table " + writeDao.getTable() + ".");
        } catch (Exception e) {
            throw new RuntimeException("Failed to open the writeDao!", e);
        }
    }

    /**
     * This is the default search method used to look for results within the Dao
     * @param searchText is the text we want to search for
     * @return a {@link List <Detail>} containing the results. If an exception
     * occurred the results list will be empty.
     * @throws IOException
     */
    protected List<Detail> searchForResults(String searchText) throws Exception
    {
        List<Detail> results;
        Logger.info("SEARCHING FOR '" + searchText + "'");
        if (searchText.length()>0)
        {
            try {
                results = getWriteDao().getRecordsLike(searchText);
            } catch (IOException e) {
                Logger.error("Could not get records like '" + searchText + "'", e);
                results = new ArrayList<>();
            }
        } else {
            try {
                results = getWriteDao().getAllRecords();
            } catch (IOException e) {
                Logger.error("Could not get all records.", e);
                results = new ArrayList<>();
            }
        }

        return results;
    }


    protected List<Detail> getAllRecords() throws Exception {
        return getWriteDao().getAllRecords();
    }



    @Override
    protected View findAddDetailButton() {
        return findViewById(R.id.ml_add_button);
    }

    @Override
    protected View findDeleteDetailButton() {
        return findViewById(R.id.ml_delete_button);
    }

    @Override
    protected View findEditDetailButton() {
        return findViewById(R.id.ml_edit_button);
    }

    @Override
    protected View findClearDetailsButton() {
        return findViewById(R.id.ml_clear_button);
    }

    @Override
    protected View findSaveMastButton() {
        View view = findViewById(R.id.ml_save_button);
        view.setVisibility(View.GONE);
        return view;
    }





    private void deleteSelectedDetail()
    {
        Logger.debug("deleteSelectedDetail()");

        try {
            if (isDetailSelected()) {
                Detail magicItem = getSelectedDetail();
                getWriteDao().delete(getWriteDao().getId(magicItem));
                searchForText();
            }
        } catch (Exception e) {
            Logger.error(e.getMessage(), e);
        }
    }


    private void clearAllDetails()
    {
        Logger.debug("clearAllDetails()");

        try {
            getWriteDao().clear();
            searchForText();
        } catch (Exception e) {
            Logger.error(e.getMessage(), e);
        }
    }

    public void onClickAddDetailButton(View view) {
        Logger.debug("onClickAddDetailButton()");

        try {
            Detail detail = getWriteDao().create();
            requestEditDetail(getWriteDao().getId(detail), detail, true);
        } catch (Exception e) {
            Logger.error(e.getMessage(), e);
        }
    }

    @Override
    public void onClickDeleteDetailButton(View view) {
        Logger.debug("onClickDeleteDetailButton()");

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(getDeleteDetailTitleStringId());
        builder.setMessage(getDeleteDetailMessageStringId());
        builder.setPositiveButton(android.R.string.yes,
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        deleteSelectedDetail();
                    }
                }
        );
        builder.setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                // do nothing
            }
        });
        builder.setIcon(android.R.drawable.ic_dialog_alert);

        builder.show();
    }

    @Override
    public void onClickClearDetailsButton(View view) {
        Logger.debug("onClickClearDetailsButton()");

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(getClearListTitleStringId());
        builder.setMessage(getClearListMessageStringId());
        builder.setPositiveButton(android.R.string.yes,
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        clearAllDetails();
                    }
                }
        );
        builder.setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                // do nothing
            }
        });
        builder.setIcon(android.R.drawable.ic_dialog_alert);

        builder.show();

    }

    @Override
    public void onClickEditDetailButton(View view, int position) {
        Logger.debug("onClickEditDetailButton()");

        try {
            Detail detail = getDetailAtPosition(position);
            requestEditDetail(getWriteDao().getId(detail), detail, false);
        } catch (Exception e) {
            Logger.error(e.getMessage(), e);
        }
    }


}
