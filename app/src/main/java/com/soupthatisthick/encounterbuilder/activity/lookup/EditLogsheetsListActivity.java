package com.soupthatisthick.encounterbuilder.activity.lookup;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;

import com.soupthatisthick.encounterbuilder.adapters.lookup.PcAdapter;
import com.soupthatisthick.encounterbuilder.util.adapter.CustomListAdapter;
import com.soupthatisthick.util.activity.DaoEditActivity;
import com.soupthatisthick.util.activity.DaoEditListActivity;
import com.soupthatisthick.util.dao.DaoMaster;
import com.soupthatisthick.encounterbuilder.dao.lookup.PcDao;
import com.soupthatisthick.encounterbuilder.dao.lookup.SessionDao;
import com.soupthatisthick.util.dao.WriteDao;
import com.soupthatisthick.encounterbuilder.dao.master.LogsheetMaster;
import com.soupthatisthick.encounterbuilder.model.lookup.Pc;
import com.soupthatisthick.util.Logger;

import soupthatisthick.encounterapp.R;

/**
 * Created by Owner on 2/10/2017.
 * Copyright of Stuart Marr Erskine, all rights reserved.
 */

public class EditLogsheetsListActivity extends DaoEditListActivity<Object, Pc>
{

    private PcDao pcDao;
    private SessionDao sessionDao;

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        showDetailControls();
        hideMastControls();
    }

    @Override
    protected void onClickSaveMastButton(View view) {

    }

    @Override
    protected DaoMaster createDaoMaster(Context context) throws Exception {
        return new LogsheetMaster(getBaseContext());
    }

    @Override
    protected WriteDao<Pc> createDetailDao(DaoMaster db) throws Exception {
        pcDao = new PcDao(db);
        sessionDao = new SessionDao(db);
        return pcDao;
    }

    @Override
    protected WriteDao<Object> createMastDao(DaoMaster daoMaster) throws Exception {
        return null;
    }

    @Override
    protected void requestEditDetail(Long detailId, Pc pc, boolean deleteOnCancel) {
        Intent intent = new Intent(this, OldEditLogsheetActivity.class);
        intent.putExtra(OldEditLogsheetActivity.KEY_PC_ID, detailId.toString());
        intent.putExtra(DaoEditActivity.KEY_DELETE_ON_CANCEL, deleteOnCancel);
        intent.putExtra(DaoEditActivity.KEY_MODEL_ID, detailId);
        startActivity(intent);
    }

    @Override
    protected int getClearListTitleStringId() {
        return R.string.mpc_list_clear_title;
    }

    @Override
    protected int getClearListMessageStringId() {
        return R.string.mpc_list_clear_message;
    }

    @Override
    protected int getDeleteDetailTitleStringId() {
        return R.string.mpc_list_delete_title;
    }

    @Override
    protected int getDeleteDetailMessageStringId() {
        return R.string.mpc_list_delete_message;
    }

    @Override
    protected CustomListAdapter<Pc> createListAdapter(LayoutInflater layoutInflater) {
        PcAdapter adapter = new PcAdapter(layoutInflater);
        return adapter;
    }

    /**
     * Call this when you want to create a new pc Logsheet
     * @return the new pc
     */
    public void createPcLogsheet()
    {
        Pc pc = getDetailDao().create();
        if (pc!=null) {
            editPcLogsheet(pc);
        } else {
            Logger.warning("Failed to create a new pc. Pc is null.");
        }
    }

    /**
     * This will delete an existing pc logsheet
     * @param pc
     */
    public void deletePcLogsheet(@NonNull Pc pc)
    {

        if (pc.getId()!=null)
        {
            Logger.debug("DELETING " + pc.getId());
            boolean deletedPc = (getDetailDao().delete(pc.getId()));
            boolean deletedSessions = true;
            try {
                sessionDao.deleteSessionsFor(pc.getId());
            } catch (Exception e) {
                Logger.error(e.getMessage(), e);
                deletedSessions = false;
            }
            Logger.debug( (deletedPc&&deletedSessions) ? " - SUCCESS" : " - FAILURE");
        } else {
            Logger.warning("Id of pc is null.");
            Logger.warning("- pc = " + pc.toString());
        }
    }

    /**
     * This will clear all logsheets currently displayed in the adapter.
     * This does NOT mean that all logsheets have been deleted.
     */
    public void clearAllLogsheets()
    {
        for(int i=0; i<theListAdapter.getCount(); i++) {
            Pc pc = (Pc) theListAdapter.getItem(i);
            deletePcLogsheet(pc);
        }
        searchForText();
    }


    /**
     * Call this when you want to edit an existing logsheet
     * @param pc
     */
    public void editPcLogsheet(@NonNull Pc pc)
    {
        Intent intent = new Intent(this, OldEditLogsheetActivity.class);

        if (pc.getId() != null) {
            Logger.debug("EDITING " + pc.getId());
            intent.putExtra(OldEditLogsheetActivity.KEY_PC_ID, pc.getId().toString());

            startActivity(intent);
        } else {
            Logger.warning("Id of pc is null.");
            Logger.warning("- pc = " + pc.toString());
        }
    }

    @Override
    public void onClickAddDetailButton(View view) {
        Logger.debug("onClickAddButton(View view)");
        createPcLogsheet();
    }

    @Override
    public void onClickDeleteDetailButton(View view) {
        Logger.debug("onClickDeleteButton(View view)");
        Pc pc = getSelectedDetail();
        if (pc!=null) {
            deletePcLogsheet(pc);
            searchForText();
        } else {
            Logger.warning("Failed to delete seleted pc. It is null");
        }
    }

    @Override
    public void onClickClearDetailsButton(View view) {
        Logger.debug("onClickClearButton(View view)");
        clearAllLogsheets();
    }

}
