package com.soupthatisthick.util.activity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.annotation.CallSuper;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;

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

public abstract class DaoEditListActivity<Mast, Detail> extends EditListActivity<Mast, Detail>
{

    public static final String KEY_MODEL_ID = "KEY_MODEL_ID";
    public static final String KEY_DELETE_ON_CANCEL = "KEY_DELETE_ON_CANCEL";

    private WriteDao<Detail> detailDao;
    private WriteDao<Mast> mastDao;
    private DaoMaster daoMaster;

    protected final WriteDao<Detail> getDetailDao()
    {
        return this.detailDao;
    }
    protected final WriteDao<Mast> getMastDao() { return this.mastDao; }

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
        return R.layout.activity_edit_list;
    }

    @Override
    protected ListView findListView()
    {
        return (ListView) findViewById(R.id.ml_list_view);
    }

    @Override
    protected EditText findSearchEditText()
    {
        return (EditText) findViewById(R.id.ml_search_edit);
    }

    protected abstract WriteDao<Detail> createDetailDao(DaoMaster daoMaster) throws Exception;
    protected abstract WriteDao<Mast> createMastDao(DaoMaster daoMaster) throws Exception;

    @Override
    @CallSuper
    protected void onResume()
    {
        Logger.debug("onResume()");
        super.onResume();
        try {
            daoMaster = createDaoMaster(getBaseContext());
            detailDao = createDetailDao(daoMaster);
            mastDao = createMastDao(daoMaster);
        } catch (Exception e) {
            Logger.error(e.getMessage(), e);

            finish();
        }
    }

    @Override
    @CallSuper
    protected void onPause() {
        Logger.debug("onPause()");

        super.onPause();

        DaoMaster.close(getDaoMaster());
    }


    @Override
    @CallSuper
    protected void loadAllData() {
        Logger.debug("loadAllData()");
        try {
            setDaoMaster(createDaoMaster(getBaseContext()));
            detailDao = createDetailDao(getDaoMaster());
            if (detailDao!=null) {
                Logger.info("Opened write dao for table " + detailDao.getTable() + ".");
            } else {
                Logger.warning("There is no detail dao!");
            }

            mastDao = createMastDao(getDaoMaster());
            if (mastDao!=null) {
                Logger.info("Opened write dao for table " + mastDao.getTable() + ".");
            } else {
                Logger.warning("There is no mast dao!");
            }
        } catch (Exception e) {
            Logger.error(e.getMessage(), e);
            finish();
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
                results = getDetailDao().getRecordsLike(searchText);
            } catch (IOException e) {
                Logger.error("Could not get records like '" + searchText + "'", e);
                results = new ArrayList<>();
            }
        } else {
            try {
                results = getDetailDao().getAllRecords();
            } catch (IOException e) {
                Logger.error("Could not get all records.", e);
                results = new ArrayList<>();
            }
        }

        return results;
    }


    protected List<Detail> getAllRecords() throws Exception {
        return getDetailDao().getAllRecords();
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
        return findViewById(R.id.ml_save_button);
    }

    @CallSuper
    protected final void showMastControls()
    {
        findSaveMastButton().setVisibility(View.VISIBLE);
    }

    @CallSuper
    protected final void hideMastControls()
    {
        findSaveMastButton().setVisibility(View.GONE);
    }

    @CallSuper
    protected void showDetailControls()
    {
        findClearDetailsButton().setVisibility(View.VISIBLE);
        findAddDetailButton().setVisibility(View.VISIBLE);
        findDeleteDetailButton().setVisibility(View.VISIBLE);
        findEditDetailButton().setVisibility(View.VISIBLE);
    }

    @CallSuper
    protected void hideDetailControls()
    {
        findClearDetailsButton().setVisibility(View.GONE);
        findAddDetailButton().setVisibility(View.GONE);
        findDeleteDetailButton().setVisibility(View.GONE);
        findEditDetailButton().setVisibility(View.GONE);
    }

    @CallSuper
    protected void showAllControls()
    {
        showMastControls();
        showDetailControls();
    }

    @CallSuper
    protected void hideAllControls()
    {
        hideMastControls();
        hideDetailControls();
    }

    private void deleteSelectedDetail()
    {
        Logger.debug("deleteSelectedDetail()");

        try {
            if (isDetailSelected()) {
                Detail magicItem = getSelectedDetail();
                getDetailDao().delete(getDetailDao().getId(magicItem));
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
            getDetailDao().clear();
            searchForText();
        } catch (Exception e) {
            Logger.error(e.getMessage(), e);
        }
    }

    public void onClickAddDetailButton(View view) {
        Logger.debug("onClickAddDetailButton()");

        try {
            Detail detail = getDetailDao().create();
            requestEditDetail(getDetailDao().getId(detail), detail, true);
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
            requestEditDetail(getDetailDao().getId(detail), detail, false);
        } catch (Exception e) {
            Logger.error(e.getMessage(), e);
        }
    }


}
