package com.soupthatisthick.encounterbuilder.activity.lookup;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import com.soupthatisthick.encounterbuilder.adapters.lookup.ExContactsAdapter;
import com.soupthatisthick.encounterbuilder.adapters.lookup.FactionAdapter;
import com.soupthatisthick.encounterbuilder.adapters.lookup.SessionAdapter;
import com.soupthatisthick.encounterbuilder.dao.lookup.ContactDao;
import com.soupthatisthick.encounterbuilder.model.lookup.Contact;
import com.soupthatisthick.util.activity.AppActivity;
import com.soupthatisthick.util.dao.DaoMaster;
import com.soupthatisthick.encounterbuilder.dao.lookup.PcDao;
import com.soupthatisthick.encounterbuilder.dao.lookup.SessionDao;
import com.soupthatisthick.encounterbuilder.dao.master.LogsheetMaster;
import com.soupthatisthick.encounterbuilder.model.lookup.Faction;
import com.soupthatisthick.encounterbuilder.model.lookup.LogsheetSummary;
import com.soupthatisthick.encounterbuilder.model.lookup.Pc;
import com.soupthatisthick.encounterbuilder.model.lookup.Session;
import com.soupthatisthick.encounterbuilder.util.translater.FactionTranslater;
import com.soupthatisthick.util.Logger;
import com.soupthatisthick.encounterbuilder.util.Tables;
import com.soupthatisthick.encounterbuilder.util.view.ViewUtil;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import soupthatisthick.encounterapp.R;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;


public class OldEditLogsheetActivity extends AppActivity {

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

    private EditText theCharacterName, thePlayerName, thePlayerDci, theClasses;
    private Spinner theFaction, thePlayerContact;
    private ListView theSessionList;

    private TextView theXpTotal, theGoldTotal, theDowntimeTotal, theRenownTotal, theMagicItemsTotal, theLevel;

    private DaoMaster daoMaster;
    private PcDao pcDao;
    private SessionDao sessionDao;
    private ContactDao contactDao;

    private SessionAdapter sessionAdapter;
    private ExContactsAdapter contactAdapter;

    private Pc thePc = new Pc();
    private FieldWatcher fieldWatcher = new FieldWatcher();
    private SessionListWatcher sessionListWatcher = new SessionListWatcher();
    private FactionSelectionListener factionListener = new FactionSelectionListener();
    private ContactSelectionListener playerListener = new ContactSelectionListener();

    private boolean isListeningToUi = false;

    public static final String KEY_REQUEST_CODE = "KEY_REQUEST_CODE";

    private class FieldWatcher implements TextView.OnEditorActionListener, TextWatcher
    {
        public void listenTo(EditText editText)
        {
            editText.addTextChangedListener(this);
            editText.setOnEditorActionListener(this);
        }

        public void ignore(EditText editText)
        {
            editText.removeTextChangedListener(this);
            editText.setOnEditorActionListener(null);
        }

        @Override
        public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                updateLogsheetFromUi();
                ViewUtil.hideInputWindow(OldEditLogsheetActivity.this);
            }
            return true;
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {
            updateLogsheetFromUi();
        }
    }

    private class FactionSelectionListener implements AdapterView.OnItemSelectedListener
    {

        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            updateLogsheetFromUi();
        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {
            updateLogsheetFromUi();
        }
    }

    private class ContactSelectionListener implements AdapterView.OnItemSelectedListener
    {

        /**
         * <p>Callback method to be invoked when an item in this view has been
         * selected. This callback is invoked only when the newly selected
         * position is different from the previously selected position or if
         * there was no selected item.</p>
         * <p>
         * Impelmenters can call getItemAtPosition(position) if they need to access the
         * data associated with the selected item.
         *
         * @param parent   The AdapterView where the selection happened
         * @param view     The view within the AdapterView that was clicked
         * @param position The position of the view in the adapter
         * @param id       The row id of the item that is selected
         */
        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            updateLogsheetFromUi();
        }

        /**
         * Callback method to be invoked when the selection disappears from this
         * view. The selection can disappear for instance when touch is activated
         * or when the adapter becomes empty.
         *
         * @param parent The AdapterView that now contains no selected item.
         */
        @Override
        public void onNothingSelected(AdapterView<?> parent) {
            updateLogsheetFromUi();
        }
    }

    private class SessionListWatcher implements ListView.OnItemClickListener, ListView.OnItemSelectedListener
    {
        public void listenTo(ListView editText)
        {
            editText.setOnItemClickListener(this);
            editText.setOnItemSelectedListener(this);
        }

        public void ignore(ListView editText)
        {
            editText.setOnItemClickListener(null);
            editText.setOnItemSelectedListener(null);
        }

        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            Session session = (Session) parent.getItemAtPosition(position);
            Logger.debug("Clicked on session position " + position);
            startEditingSession(session.getSessionId());
        }

        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            Session session = (Session) parent.getItemAtPosition(position);
            Logger.debug("Item selected on session position " + position);
            startEditingSession(session.getSessionId());
        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {
            // Do nothing
        }
    }

    private void listenToUi()
    {
        if (!isListeningToUi) {
            isListeningToUi = true;
            fieldWatcher.listenTo(theCharacterName);
            fieldWatcher.listenTo(theClasses);
            fieldWatcher.listenTo(thePlayerName);
            fieldWatcher.listenTo(thePlayerDci);
            theFaction.setOnItemSelectedListener(factionListener);
            sessionListWatcher.listenTo(theSessionList);
            thePlayerContact.setOnItemSelectedListener(playerListener);
        }
    }

    private void ignoreUi()
    {
        if (isListeningToUi) {
            isListeningToUi = false;
            fieldWatcher.ignore(theCharacterName);
            fieldWatcher.ignore(theClasses);
            fieldWatcher.ignore(thePlayerName);
            fieldWatcher.ignore(thePlayerDci);
            theFaction.setOnItemSelectedListener(null);
            sessionListWatcher.ignore(theSessionList);
            thePlayerContact.setOnItemSelectedListener(null);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        try {
            Logger.debug("onCreate()");

            Logger.debug("started onCreate()");

            setContentView(R.layout.activity_edit_logsheet);

            theCharacterName = (EditText) findViewById(R.id.character_name);
            thePlayerName = (EditText) findViewById(R.id.player_name);
            thePlayerContact = (Spinner) findViewById(R.id.player_spinner);

            thePlayerDci = (EditText) findViewById(R.id.player_dci);
            theClasses = (EditText) findViewById(R.id.character_class);

            theLevel = (TextView) findViewById(R.id.character_level);

            theXpTotal = (TextView) findViewById(R.id.el_total_xp);
            theGoldTotal = (TextView) findViewById(R.id.el_total_gold);
            theDowntimeTotal = (TextView) findViewById(R.id.el_total_downtime);
            theRenownTotal = (TextView) findViewById(R.id.el_total_renown);
            theMagicItemsTotal = (TextView) findViewById(R.id.el_total_magic_items);

            theFaction = (Spinner) findViewById(R.id.faction);
            theSessionList = (ListView) findViewById(R.id.session_list);
            sessionAdapter = new SessionAdapter(getLayoutInflater());
            theSessionList.setAdapter(sessionAdapter);
            theSessionList.setClickable(false);

            contactAdapter = new ExContactsAdapter(getLayoutInflater());
            thePlayerContact.setAdapter(contactAdapter);

        } catch (Exception e) {
            Logger.error(e.getMessage(), e);
            finish();
        }

        Logger.debug("finished onCreate()");
    }

    private void initPc()
    {
        Logger.debug("--- Initializing the Pc ---");
        Intent intent = getIntent();
        String theIdStr = intent.getStringExtra(KEY_PC_ID);

        if (theIdStr!=null) {
            Long theId = Long.parseLong(theIdStr);
            loadExistingPc(theId);
        } else {
            Logger.warning("We don't know what pc we need to edit a logsheet for.");
            finish();
        }

        logPc("from initPc()", thePc);
    }

    private void loadExistingPc(@NonNull Long theId)
    {
        Logger.debug("LOADING AN EXISTING PC");
        Logger.debug("Loading PC with id " + theId.toString() + " from the dao.");
        thePc = pcDao.load(theId);
        if ((thePc == null) || (theId<1)) {
            Logger.warning("Failed to load session with id " + theId);
            finish();
        } else {
            Logger.info("Loaded pc " + thePc.toString() + " from the dao.");
        }
    }

    protected final void loadActivityState()
    {
        Logger.debug("loadActivityState()");
        // Initialize the shared preferences;
        SharedPreferences source = getSharedPreferences(getClass().getSimpleName(), SHARED_PREF_MODE);

        if (thePc==null)
        {
            thePc = new Pc();
        }

        thePc.setId(new Long(source.getLong(KEY_PC_ID, 0)));
        thePc.setCharacterName(source.getString(KEY_PC_CHARACTER_NAME, ""));
        thePc.setClassAndLevels(source.getString(KEY_PC_CLASS_AND_LEVELS, ""));
        thePc.setPlayerName(source.getString(KEY_PC_PLAYER_NAME, ""));
        thePc.setPlayerDci(source.getString(KEY_PC_PLAYER_DCI, ""));
        thePc.setFaction(source.getString(KEY_PC_FACTION, ""));

        logPc("from loadActivityState()", thePc);
    }

    /**
     * Use this to save things into the shared preferences
     */
    protected final void saveActivityState()
    {
        Logger.debug("saveActivityState()");
        SharedPreferences sharedPref = getSharedPreferences(getClass().getSimpleName(), SHARED_PREF_MODE);
        SharedPreferences.Editor sharedPrefEditor = sharedPref.edit();
        if (thePc != null) {
            sharedPrefEditor.putLong(KEY_PC_ID, thePc.getId());
            sharedPrefEditor.putString(KEY_PC_CHARACTER_NAME, thePc.getCharacterName());
            sharedPrefEditor.putString(KEY_PC_CLASS_AND_LEVELS, thePc.getClassAndLevels());
            sharedPrefEditor.putString(KEY_PC_PLAYER_NAME, thePc.getPlayerName());
            sharedPrefEditor.putString(KEY_PC_PLAYER_DCI, thePc.getPlayerDci());
            sharedPrefEditor.putString(KEY_PC_FACTION, thePc.getFaction());

            sharedPrefEditor.commit();
        }
    }

    private void openDaos()
    {
        try {
            daoMaster = new LogsheetMaster(getBaseContext());
        } catch (IOException e) {
            Logger.error("Failed to open connection to the logsheet database.", e);
            return;
        }

        try {
            pcDao = new PcDao(daoMaster);
        } catch (IOException e) {
            Logger.error("Failed to open logsheet dao", e);
            return;
        }

        try {
            sessionDao = new SessionDao(daoMaster);
            return;
        } catch (IOException e) {
            Logger.error("Failed to open session dao", e);
        }

        try {
            contactDao = new ContactDao(daoMaster);
            return;
        } catch (IOException e) {
            Logger.error("Failed to open contact dao", e);
        }

    }

    private void closeDaos()
    {
        if (daoMaster != null)
        {
            daoMaster.close();
            daoMaster = null;
        }
    }

    @Override
    protected void onStop()
    {
        super.onStop();
        try {
            closeDaos();
        } catch (Exception e) {
            Logger.error(e.getMessage(), e);
        }
    }

    @Override
    protected void onStart()
    {
        super.onStart();
        Logger.debug("started onStart()");
        try {
            openDaos();
        } catch (Exception e) {
            Logger.error(e.getMessage(), e);
            finish();
        }
    }

    @Override
    protected void onResume()
    {
        super.onResume();

        Logger.debug("onResume()");
        try {
            loadActivityState();

            initPc();
            initFactionChoice();
            initPlayerChoice();
            listenToUi();

            updateUiFromLogsheet(thePc);
        } catch (Exception e) {
            Logger.error(e.getMessage(), e);
            finish();
        }
        Logger.debug("finished onResume()");
    }

    @Override
    protected void onPause()
    {
        super.onPause();
        try {
            Logger.debug("onPause()");
            saveActivityState();
            pcDao.logContents();
            contactDao.logContents();
        } catch (Exception e) {
            Logger.error(e.getMessage(), e);
        }
    }

    private void initFactionChoice()
    {
        theFaction.setAdapter(new FactionAdapter(getLayoutInflater()));
    }

    private void initPlayerChoice()
    {
        ExContactsAdapter adapter = new ExContactsAdapter(getLayoutInflater());
        thePlayerContact.setAdapter(adapter);
        try {
            List<Contact> contacts = contactDao.getAllRecords();
            adapter.setData(contacts);
        } catch (Exception e) {
            Logger.error("Failed to initialize the player choice.", e);
        }

    }

    private void updateUiFromLogsheet(Pc pc)
    {
        Logger.logCodeTransition();

        ignoreUi();

        Logger.debug("Updating ui from pc " + pc.toString());

        if (pc==null)
        {
            theCharacterName.setText("");
            thePlayerName.setText("");
            thePlayerDci.setText("");
            theClasses.setText("");

            updateFactionBanner(Faction.NONE.toString());
            updatePlayerChoice(null);

        } else {
            theCharacterName.setText(pc.getCharacterName());
            thePlayerName.setText(pc.getPlayerName());
            thePlayerDci.setText(pc.getPlayerDci());
            theClasses.setText(pc.getClassAndLevels());

            updateFactionBanner(pc.getFaction());
            updatePlayerChoice(pc.getPlayerName(), pc.getPlayerDci());
            updateSessionAdapter(pc.getId());
        }
        listenToUi();

        Logger.debug("Finished updating the UI for pc " + pc.toString());

    }

    private void updateLogsheetFromUi()
    {

        logPc("updateLogsheetFromUi()", thePc);

        if (thePc==null)
        {
            throw new RuntimeException("We could not update a logsheet for a new pc because the new pc could not be created in the dao.");
        }

        Faction faction = (Faction) theFaction.getSelectedItem();
        thePc.setFaction(FactionTranslater.INSTANCE.toText(faction));

        thePc.setCharacterName(theCharacterName.getText().toString());
        thePc.setPlayerName(thePlayerName.getText().toString());
        thePc.setPlayerDci(thePlayerDci.getText().toString());
        thePc.setClassAndLevels(theClasses.getText().toString());

        Contact contact = (Contact) thePlayerContact.getSelectedItem();
        if (contact!=null)
        {
            Logger.info("Setting contact info from the spinner data.");
            thePc.setPlayerName(contact.getName());
            thePc.setPlayerDci(contact.getDci());
        }
        savePc();

        updateSummary(thePc.getId());
    }

    /**
     * This will save the PC to the dao
     */
    protected void savePc()
    {
        if (thePc.getId()!=null) {
            if (pcDao.update(thePc.getId(), thePc)) {
                Logger.info("Updated PC " + thePc.getId());
                logPc("thePc", thePc);

                Pc loadedPc = pcDao.load(thePc.getId());
                logPc("loadedPc", loadedPc);

                thePc = loadedPc;

            } else {
                Logger.error("Failed to update PC " + thePc.getId(), null);
            }
        } else {
            Logger.warning("We are updating a PC that has not been created in the Dao yet. thePc.getFieldId()==null");
        }
    }

    private static final void logPc(@NonNull String header, @Nullable Pc pc)
    {
        Logger.info("---- " + header + " ----");

        if (pc==null)
        {
            Logger.info("pc is null");
        } else {
            Logger.info("pc.id             - " + ((pc.getId() == null) ? "null" : pc.getId().toString()));
            Logger.info("pc.characterName  - " + ((pc.getCharacterName()==null) ? "null" : pc.getCharacterName()));
            Logger.info("pc.classAndLevels - " + ((pc.getClassAndLevels()==null) ? "null" : pc.getClassAndLevels()));
            Logger.info("pc.playerName     - " + ((pc.getPlayerName()==null) ? "null" : pc.getPlayerName()));
            Logger.info("pc.playerDci      - " + ((pc.getPlayerDci()==null) ? "null" : pc.getPlayerDci()));
            Logger.info("pc.faction        - " + ((pc.getFaction()==null) ? "null" : pc.getFaction()));
        }
    }

    /**
     * Given the text provided in the faction field this will update the spinner selection
     * @param factionText
     */
    private void updateFactionBanner(String factionText)
    {
        Faction faction = FactionTranslater.INSTANCE.toFaction(factionText);

        Logger.info("updateFactionBanner('" + factionText + " => " + faction.toString() + "')");

        theFaction.setSelection(faction.ordinal());
    }

    private void updatePlayerSpinner(String playerName, String playerDci)
    {
        try {
            List<Contact> results = contactDao.getRecordsLike(playerName + " " + playerDci);
            contactAdapter.setData(results);
            if (results.size()>0) {
                thePlayerContact.setSelection(0);
            } else {
                thePlayerContact.setSelection(-1);
            }
            thePlayerContact.setSelection(0);
        } catch (Exception e) {
            Logger.error(e.getMessage(), e);
        }
    }

    private void updateSessionAdapter(@Nullable Long characterId)
    {
        if (characterId!=null) {
            Logger.debug("Updating session adapter for characterId " + characterId);
            try {
                List<Session> sessions = sessionDao.getSessionsFor(characterId);
                Logger.debug(" - sessions numFaces = " + sessions.size());
                sessionAdapter.setData(sessions);
            } catch (IOException e) {
                Logger.error("Failed to retrieve sessions for character with id " + characterId, e);
            }
            Logger.debug("Finished updating the session adapter for characterId " + characterId);
        } else {
            Logger.debug("Updating session adapter for new character");
            sessionAdapter.setData(new ArrayList<Session>());
        }
    }

    private void updateContactsAdapter()
    {
        Logger.debug("Updating players adapter");
        try {
            List<Contact> contacts = contactDao.getAllRecords();
            Logger.debug(" - contacts numFaces = " + contacts.size());
            contactAdapter.setData(contacts);
            thePlayerContact.setAdapter(contactAdapter);
        } catch (Exception e) {
            Logger.error("Failed to retrieve contacts", e);
        }
        Logger.debug("Finished updating the contacts adapter.");
    }

    private void updateSummary(@NonNull Long characterId)
    {
        Logger.debug("Updating the summary for characterId " + characterId);
        try {
            LogsheetSummary summary = sessionDao.getSummaryFor(characterId);
            theXpTotal.setText(Integer.toString(summary.getTotalXp()));
            theGoldTotal.setText(Integer.toString(summary.getTotalGold()));
            theDowntimeTotal.setText(Integer.toString(summary.getTotalDowntime()));
            theRenownTotal.setText(Integer.toString(summary.getTotalRenown()));
            theMagicItemsTotal.setText(Integer.toString(summary.getTotalMagicItems()));
            theLevel.setText(getString(R.string.el_total_level, Tables.getLevel(summary.getTotalXp())));
        } catch (IOException e) {
            Logger.error("Failed to retrieve summary for character with id " + characterId, e);
            theXpTotal.setText(Integer.toString(0));
            theGoldTotal.setText(Integer.toString(0));
            theDowntimeTotal.setText(Integer.toString(0));
            theRenownTotal.setText(Integer.toString(0));
            theMagicItemsTotal.setText(Integer.toString(0));
            theLevel.setText(getString(R.string.el_total_level,Tables.getLevel(0)));
        }
    }

    /**
     * This method is called when we click the add session button
     * @param view that triggered the event
     */
    public void onClickAddSessionButton(View view)
    {
        Logger.info("ADD SESSION!!!");
        Intent intent = new Intent(this, EditSessionActivity.class);

        intent.putExtra(EditSessionActivity.KEY_PC_ID, thePc.getId());
        intent.putExtra(KEY_REQUEST_CODE, REQUEST_NEW_SESSIION);

        startActivityForResult(intent, REQUEST_NEW_SESSIION);
    }

    /**
     * This will be called when we want to delete the character
     * @param view
     */
    public void onClickDeleteSheetButton(View view)
    {
        if (thePc!=null && thePc.getId() != null) {
            try {
                ignoreUi();
                Logger.info("DELETING PC LOGSHEET " + thePc.getId());
                pcDao.delete(thePc.getId());
                sessionDao.deleteSessionsFor(thePc.getId());
                finish();
            } catch (Exception e) {
                Logger.error("Error while deleting pc " + thePc.getId(), e);
            }
        } else {
            Logger.info("There is no PC to delete from the dao.");
        }
    }

    /**
     * This will be called when we want to print out a logsheet
     * @param view
     */
    public void onClickPrintSheetButton(View view)
    {
        if (thePc != null && thePc.getId() != null) {
            Intent intent = new Intent(this, PrintLogsheetActivity.class);
            intent.putExtra(PrintLogsheetActivity.KEY_PC_ID, thePc.getId());
            startActivity(intent);
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // Do nothing for now. Hopefully the UI is still the same.
        Logger.debug("onActivityResult(requestCode=" + requestCode + ", resultCode=" + requestCode + ", data=" + data + ")");

    }

    private void startEditingSession(Long sessionId)
    {
        Logger.info("EDIT SESSION!!!");
        Intent intent = new Intent(this, EditSessionActivity.class);

        intent.putExtra(EditSessionActivity.KEY_PC_ID, thePc.getId());
        intent.putExtra(EditSessionActivity.KEY_SESSION_ID, sessionId);
        intent.putExtra(KEY_REQUEST_CODE, REQUEST_EDIT_SESSION);

        startActivityForResult(intent, REQUEST_EDIT_SESSION);

    }

    private void updatePlayerChoice(@Nullable Contact contact)
    {
        if (contact==null)
        {
            updatePlayerChoice(null,null);
        } else {
            updatePlayerChoice(contact.getName(), contact.getDci());
        }
    }


    private void updatePlayerChoice(@Nullable String playerName, @Nullable String playerDci)
    {
        playerName = (playerName==null) ? "???" : playerName;
        playerDci = (playerDci==null) ? "???" : playerDci;
        Logger.toast(getApplication(), String.format("Updating player choice %s (%s)", playerName, playerDci));
    }
}
