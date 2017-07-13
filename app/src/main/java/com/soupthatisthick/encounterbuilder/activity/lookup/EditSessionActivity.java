package com.soupthatisthick.encounterbuilder.activity.lookup;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v4.app.NavUtils;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.soupthatisthick.encounterbuilder.dao.lookup.SessionDao;
import com.soupthatisthick.encounterbuilder.dao.master.LogsheetMaster;
import com.soupthatisthick.encounterbuilder.model.lookup.Session;
import com.soupthatisthick.encounterbuilder.util.translater.DateTranslater;
import com.soupthatisthick.util.Logger;
import com.soupthatisthick.encounterbuilder.util.Text;
import com.soupthatisthick.encounterbuilder.util.view.ViewUtil;
import com.soupthatisthick.util.activity.AppActivity;

import java.io.IOException;
import java.util.Date;

import soupthatisthick.encounterapp.R;

import static com.soupthatisthick.encounterbuilder.util.view.ViewUtil.markAsInvalid;
import static com.soupthatisthick.encounterbuilder.util.view.ViewUtil.markAsValid;


public class EditSessionActivity extends AppActivity {

    public static final String KEY_SESSION_ID = "KEY_SESSION_ID";
    public static final String KEY_PC_ID = "KEY_PC_ID";

    private LogsheetMaster db;
    private SessionDao theSessionDao;
    private Session theSession = new Session();
    private Boolean formValid = Boolean.FALSE;
    private Boolean listenToUiUpdates = Boolean.TRUE;

    private EditText theDate;
    private EditText theAdventure, theDm, theDmDci, theNotes;
    private EditText theXp, theGold, theDowntime, theRenown, theMagicItems;
    private TextView theMessage, theSessionId, theCharacterId;

    private ImageButton theSaveButton;
    private ImageButton theDeleteButton;

    private boolean isListeningToUi = false;

    private FieldWatcher fieldWatcher;

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
            if (actionId == EditorInfo.IME_ACTION_DONE)
            {
                updateSessionFromUi(theSession);
                ViewUtil.hideInputWindow(EditSessionActivity.this);
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
            updateSessionFromUi(theSession);
        }
    }

    protected final boolean isFormValid()
    {
        return this.formValid;
    }

    protected final void invalidateForm()
    {
        this.formValid = false;
        this.theSaveButton.setEnabled(false);
    }

    protected final void validateForm()
    {
        this.formValid = true;
        this.theSaveButton.setEnabled(true);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Logger.debug("*** EDITING SESSION ***");
        setContentView(R.layout.activity_edit_session);

        fieldWatcher = new FieldWatcher();

        theCharacterId = (TextView) findViewById(R.id.theCharacterId);
        theSessionId = (TextView) findViewById(R.id.theSessionId);

        theAdventure = (EditText) findViewById(R.id.adventureName);
        theDm = (EditText) findViewById(R.id.dm);
        theDmDci = (EditText) findViewById(R.id.dmDci);
        theDate = (EditText) findViewById(R.id.datePlayed);
        theNotes = (EditText) findViewById(R.id.notes);

        theXp = (EditText) findViewById(R.id.xpEarned);
        theGold = (EditText) findViewById(R.id.goldEarned);
        theDowntime = (EditText) findViewById(R.id.downtimeEarned);
        theRenown = (EditText) findViewById(R.id.renownEarned);
        theMagicItems = (EditText) findViewById(R.id.magicItemsEarned);

        theMessage = (TextView) findViewById(R.id.error_message);

        theSaveButton = (ImageButton) findViewById(R.id.saveButton);
        theDeleteButton = (ImageButton) findViewById(R.id.deleteButton);

   }

    private void initSession()
    {
        Intent intent = getIntent();
        Long sessionId = intent.getLongExtra(KEY_SESSION_ID, -1);
        Long characterId = intent.getLongExtra(KEY_PC_ID, -1);

        try {
            if (sessionId > 0) {
                initExistingSession(sessionId);
            } else if (characterId > 0) {
                initNewSession(characterId);
            } else {
                throw new RuntimeException("Require a character id for new session or a session id to edit an existing session. We have neither.");
            }
        } catch (Exception e) {
            logErrorMessage(e.getMessage(), e);
            goBack();
        }
    }

    private void initNewSession(@NonNull Long characterId)
    {
        try {
            theSession = theSessionDao.create();
            theSession.setCharacterId(characterId);
        } catch (Exception e)
        {
            logErrorMessage("Failed to init new session.", e);
            goBack();
        }
    }

    private void initExistingSession(@NonNull Long sessionId)
    {
        try {
            theSession = theSessionDao.load(sessionId);
        } catch (Exception e) {
            logErrorMessage("Failed to load session with id " + sessionId, e);
            goBack();
        }
    }


    @Override
    public void onResume()
    {
        super.onStart();
        openDaos();
        initSession();
        listenToUi();
        updateUiFromSession(theSession);
    }


    @Override
    public void onPause()
    {
        super.onStop();
        ignoreUi();
        closeDaos();
    }

    private void updateUiFromSession(Session theSession)
    {

        if (theSession != null) {

            ignoreUi();

            Logger.debug(Text.quote(theSession.getDmDci()) + ", " + Text.quote(theSession.getDmName()));

            // TODO: Remove these views when ready.
            theSessionId.setText("Session[" + theSession.getSessionId() + "]");
            theCharacterId.setText("Character[" + theSession.getCharacterId() + "]");

            theAdventure.setText(Text.toString(theSession.getAdventure()));
            theDm.setText(Text.toString(theSession.getDmName()));
            theDmDci.setText(Text.toString(theSession.getDmDci()));

            Date date = (theSession.getDate() == null) ? new Date() : theSession.getDate();

            theDate.setText(DateTranslater.translate(date));

            theNotes.setText(Text.toString(theSession.getNotes()));
            theXp.setText(Integer.toString(theSession.getXp()));
            theGold.setText(Integer.toString(theSession.getGold()));
            theDowntime.setText(Integer.toString(theSession.getDowntime()));
            theRenown.setText(Integer.toString(theSession.getRenown()));
            theMagicItems.setText(Integer.toString(theSession.getMagicItems()));

            theNotes.setText(Text.toString(theSession.getNotes()));

            listenToUi();
        } else {
            logErrorMessage("theSession is null!", null);
        }
    }

    /**
     * This will take the values from the ui and update the session contents with them
     * @param theSession
     */
    private void updateSessionFromUi(Session theSession)
    {
        Logger.debug("updateSessionFromUi");


        synchronized (listenToUiUpdates) {
            if (listenToUiUpdates) {
                listenToUiUpdates = Boolean.FALSE;
                synchronized (formValid) {

                    validateForm();

                    theSession.setAdventure(theAdventure.getText().toString());
                    if (theSession.getAdventure().length() > 0) {
                        markAsValid(theAdventure);
                    } else {
                        markAsInvalid(theAdventure);
                        invalidateForm();
                    }

                    theSession.setDmName(theDm.getText().toString());
                    if (theSession.getDmName().length() > 0) {
                        markAsValid(theDm);
                    } else {
                        markAsInvalid(theDm);
                        invalidateForm();
                    }

                    theSession.setDmDci(theDmDci.getText().toString());
                    if (theSession.getDmDci().length() > 0) {
                        markAsValid(theDmDci);
                    } else {
                        markAsInvalid(theDmDci);
                        invalidateForm();
                    }

                    try {
                        String dateStr = theDate.getText().toString();
                        String dateRegex = DateTranslater.REGEX;
                        if (dateStr.matches(dateRegex)) {
                            Date dateValue = DateTranslater.translate(dateStr);
                            theSession.setDate(dateValue);
                            markAsValid(theDate);
                        } else {
                            invalidateForm();
                            markAsInvalid(theDate);
                        }
                    } catch (Exception e) {
                        logErrorMessage("Invalid Date", e);
                        invalidateForm();
                        markAsInvalid(theDate);
                    }

                    theSession.setNotes(theNotes.getText().toString());

                    try {
                        theSession.setXp(Integer.parseInt(theXp.getText().toString()));
                        markAsValid(theXp);
                    } catch (NumberFormatException e) {
                        invalidateForm();
                        markAsInvalid(theXp);
                    }

                    try {
                        theSession.setGold(Integer.parseInt(theGold.getText().toString()));
                        markAsValid(theGold);
                    } catch (NumberFormatException e) {
                        invalidateForm();
                        markAsInvalid(theGold);
                    }

                    try {
                        theSession.setDowntime(Integer.parseInt(theDowntime.getText().toString()));
                        markAsValid(theDowntime);
                    } catch (NumberFormatException e) {
                        invalidateForm();
                        markAsInvalid(theDowntime);
                    }

                    try {
                        theSession.setRenown(Integer.parseInt(theRenown.getText().toString()));
                        markAsValid(theRenown);
                    } catch (NumberFormatException e) {
                        invalidateForm();
                        markAsInvalid(theRenown);
                    }

                    try {
                        theSession.setMagicItems(Integer.parseInt(theMagicItems.getText().toString()));
                        markAsValid(theMagicItems);
                    } catch (NumberFormatException e) {
                        invalidateForm();
                        markAsInvalid(theMagicItems);
                    }

                    if (isFormValid()) {
                        theMessage.setText(R.string.es_valid_message);
                        theMessage.setTextColor(Color.BLACK);
                    } else {
                        theMessage.setText(R.string.es_invalid_message);
                        theMessage.setTextColor(Color.RED);
                    }
                }


                listenToUiUpdates = Boolean.TRUE;

            }
        }

    }

    private void openDaos()
    {
        try {
            db = new LogsheetMaster(getBaseContext());
        } catch (IOException e) {
            logErrorMessage("Failed to open connection to the logsheet database.", e);
            return;
        }
        try {
            theSessionDao = new SessionDao(db);
        } catch (IOException e) {
            logErrorMessage("Failed to open session dao", e);
            return;
        }

    }

    private void closeDaos()
    {
        if (db != null)
        {
            db.close();
        }
    }

    /**
     * This is called when we want to save our session to the database
     * @param view
     */
    public void onClickSaveButton(View view)
    {
        saveTheSession();
    }

    public void saveTheSession()
    {
        printSession(theSession);

        try {
            if (theSessionDao.update(theSession.getSessionId(), theSession)) {
                logMessage("Session " + theSession.getSessionId() + " saved.");
                goBack();
            } else {
                throw new RuntimeException("Failed to save session " + theSession.getSessionId());
            }
        } catch (Exception e)
        {
            logErrorMessage(e.getMessage(), e);
        }
    }

    public void onClickDeleteButton(View view)
    {
        Logger.debug("onClickDeleteButton()");
        deleteTheSession();
    }

    private void deleteTheSession()
    {
        try {
            theSessionDao.delete(theSession.getSessionId());
            goBack();
        } catch (Exception e) {
            logErrorMessage(e.getMessage(), e);
        }
    }

    private void logErrorMessage(String message, Exception e)
    {
        Logger.error(message, e);
        theMessage.setText(message);
    }

    private void logMessage(String message)
    {
        Logger.info(message);
        theMessage.setText(message);
    }

    private static void printSession(Session session)
    {
        Logger.debug("********** session **********");
        Logger.debug("sessionId   = " + session.getSessionId());
        Logger.debug("characterId = " + session.getCharacterId());
        Logger.debug("------------------------------");
        Logger.debug("adventure   = " + session.getAdventure());
        Logger.debug("dmName      = " + session.getDmName());
        Logger.debug("dmDci       = " + session.getDmDci());
        Logger.debug("date        = " + session.getDate());
        Logger.debug("xp          = " + session.getXp());
        Logger.debug("gold        = " + session.getGold());
        Logger.debug("downtime    = " + session.getDowntime());
        Logger.debug("renown      = " + session.getRenown());
        Logger.debug("magicItems  = " + session.getMagicItems());
        Logger.debug("notes       = " + session.getNotes());

    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void onBackPressed() {
        //Include the code here
        Logger.debug("BACK BUTTON PRESSED!");
        goBack();
    }

    public void goBack()
    {
        Intent intent = NavUtils.getParentActivityIntent(this);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
        NavUtils.navigateUpTo(this, intent);
        finish();
    }


    private void listenToUi()
    {
        if (!isListeningToUi) {
            isListeningToUi = true;

            fieldWatcher.listenTo(theAdventure);
            fieldWatcher.listenTo(theDm);
            fieldWatcher.listenTo(theDmDci);
            fieldWatcher.listenTo(theDate);
            fieldWatcher.listenTo(theNotes);

            fieldWatcher.listenTo(theXp);
            fieldWatcher.listenTo(theGold);
            fieldWatcher.listenTo(theDowntime);
            fieldWatcher.listenTo(theRenown);
            fieldWatcher.listenTo(theMagicItems);
        }
    }

    private void ignoreUi()
    {
        if (isListeningToUi) {
            isListeningToUi = false;
            fieldWatcher.ignore(theAdventure);
            fieldWatcher.ignore(theDm);
            fieldWatcher.ignore(theDmDci);
            fieldWatcher.ignore(theDate);
            fieldWatcher.ignore(theNotes);

            fieldWatcher.ignore(theXp);
            fieldWatcher.ignore(theGold);
            fieldWatcher.ignore(theDowntime);
            fieldWatcher.ignore(theRenown);
            fieldWatcher.ignore(theMagicItems);
        }

    }

}


