package com.soupthatisthick.encounterbuilder.activity.lookup;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;

import com.soupthatisthick.encounterbuilder.adapters.lookup.SessionAdapter;
import com.soupthatisthick.encounterbuilder.dao.lookup.PcDao;
import com.soupthatisthick.encounterbuilder.dao.lookup.SessionDao;
import com.soupthatisthick.encounterbuilder.dao.master.LogsheetMaster;
import com.soupthatisthick.encounterbuilder.model.lookup.Pc;
import com.soupthatisthick.encounterbuilder.model.lookup.Session;
import com.soupthatisthick.encounterbuilder.util.adapter.CustomListAdapter;
import com.soupthatisthick.encounterbuilder.util.listeners.UiWatcher;
import com.soupthatisthick.util.Logger;
import com.soupthatisthick.util.activity.DaoEditListActivity;
import com.soupthatisthick.util.dao.DaoMaster;
import com.soupthatisthick.util.dao.WriteDao;

import soupthatisthick.encounterapp.R;

/**
 * Created by Owner on 5/1/2017.
 * Copyright of Stuart Marr Erskine, all rights reserved.
 */

public class EditLogsheetActivity extends DaoEditListActivity<Pc, Session>
{
    protected final UiWatcher uiWatcher = new UiWatcher() {
        @Override
        protected void onUiUpdate() {
            updateModelFromUi();
        }
    };

    // These are used to save the ui to the shared preferences
    protected static final String KEY_PC_ID = "KEY_PC_ID";
    protected static final String KEY_PC_PLAYER_NAME = "KEY_PC_PLAYER_NAME";
    protected static final String KEY_PC_PLAYER_DCI = "KEY_PC_PLAYER_DCI";
    protected static final String KEY_PC_CHARACTER_NAME = "KEY_PC_CHARACTER_NAME";
    protected static final String KEY_PC_CLASS_AND_LEVELS = "KEY_PC_CLASS_AND_LEVELS";
    protected static final String KEY_PC_FACTION = "KEY_PC_FACTION";

    private static final int REQUEST_NEW_SESSIION = 0;
    private static final int REQUEST_EDIT_SESSION = 1;

    protected static final int SHARED_PREF_MODE = 0;

    public static final String KEY_REQUEST_CODE = "KEY_REQUEST_CODE";

    private Pc thePc = null;

    @Override
    protected DaoMaster createDaoMaster(Context context) throws Exception {
        return new LogsheetMaster(context);
    }

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        showAllControls();
    }

    @Override
    protected void loadAllData()
    {
        super.loadAllData();
        initPc();
    }

    /**
     * This will attempt to initialize the PC. If we are unable to the activity will finish.
     */
    private void initPc()
    {
        Intent intent = getIntent();
        String theIdStr = intent.getStringExtra(KEY_PC_ID);

        if (theIdStr!=null) {
            Long theId = Long.parseLong(theIdStr);
            loadExistingPc(theId);
        } else {
            Logger.warning("We don't know what pc we need to edit a logsheet for.");
            finish();
        }
    }

    /**
     * This will load the desired PC information from the Dao
     * @param theId
     */
    private void loadExistingPc(@NonNull Long theId)
    {
        Logger.debug("Loading PC with id " + theId.toString() + " from the dao.");
        thePc = getMastDao().load(theId);
        if ((thePc == null) || (theId<1)) {
            Logger.warning("Failed to load session with id " + theId);
            finish();
        } else {
            Logger.info("Loaded pc " + thePc.toString() + " from the dao.");
        }
    }

    @Override
    protected void requestEditDetail(Long detailId, Session session, boolean deleteOnCancel) {
        Intent intent = new Intent(this, EditSessionActivity.class);

        intent.putExtra(EditSessionActivity.KEY_PC_ID, thePc.getId());
        intent.putExtra(KEY_REQUEST_CODE, REQUEST_NEW_SESSIION);

        startActivityForResult(intent, REQUEST_NEW_SESSIION);
    }

    @Override
    protected int getClearListTitleStringId() {
        return R.string.el_clear_sessions_title;
    }

    @Override
    protected int getClearListMessageStringId() {
        return R.string.el_clear_sessions_message;
    }

    @Override
    protected int getDeleteDetailTitleStringId() {
        return R.string.el_delete_session_title;
    }

    @Override
    protected int getDeleteDetailMessageStringId() {
        return R.string.el_delete_session_message;
    }

    @Override
    protected WriteDao<Session> createDetailDao(DaoMaster daoMaster) throws Exception {
        return new SessionDao(daoMaster);
    }

    @Override
    protected WriteDao<Pc> createMastDao(DaoMaster daoMaster) throws Exception {
        return new PcDao(daoMaster);
    }

    @Override
    protected CustomListAdapter<Session> createListAdapter(LayoutInflater layoutInflater) {
        return new SessionAdapter(layoutInflater);
    }

    /**
     * This will save the information about the list of information.
     *
     * @param view
     */
    @Override
    protected void onClickSaveMastButton(View view) {
        Logger.toast(getApplication(), R.string.test_to_be_implemented);
    }

    /**
     * Called when we want to update the UI from the backend store
     */
    protected void updateUiFromModel()
    {
        Logger.toast(getApplication(), "UPDATING THE UI FROM THE MODEL");
    }

    /**
     * This is called when we want to update the model from the UI
     */
    protected void updateModelFromUi()
    {
        Logger.toast(getApplication(), "UPDATING THE MODEL FROM THE UI");
    }

}
