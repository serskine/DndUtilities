package com.soupthatisthick.encounterbuilder.activity.lookup;

import android.content.Context;
import android.content.Intent;
import android.speech.RecognizerIntent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;

import com.soupthatisthick.encounterbuilder.dao.lookup.NotesDao;
import com.soupthatisthick.encounterbuilder.dao.master.DndMaster;
import com.soupthatisthick.encounterbuilder.model.lookup.Note;
import com.soupthatisthick.util.activity.DaoEditActivity;
import com.soupthatisthick.util.dao.DaoMaster;
import com.soupthatisthick.util.dao.WriteDao;

import java.util.List;

import soupthatisthick.encounterapp.R;

/**
 * Created by Owner on 4/13/2017.
 * Copyright of Stuart Marr Erskine, all rights reserved.
 */

public class EditNoteActivity extends DaoEditActivity<Note> {
    private EditText theTitle;
    private EditText theContent;

    private static final int SPEECH_REQUEST_CODE = 0;

    protected void initUiWithoutModel()
    {
        super.initUiWithoutModel();
        View speechButton = findViewById(R.id.en_speech_button);
        speechButton.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return false;
            }
        });
    }

    // Create an intent that can start the Speech Recognizer activity
    private void displaySpeechRecognizer() {
        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        // Start the activity, the intent will be populated with the speech text
        startActivityForResult(intent, SPEECH_REQUEST_CODE);
    }

    // This callback is invoked when the Speech Recognizer returns.
    // This is where you process the intent and extract the speech text from the intent.
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == SPEECH_REQUEST_CODE && resultCode == RESULT_OK) {
            List<String> results = data.getStringArrayListExtra(
                    RecognizerIntent.EXTRA_RESULTS);
            String spokenText = results.get(0);
            String prevText = theContent.getText().toString();
            theContent.setText(prevText + spokenText);
            // Do something with spokenText
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    protected void initModelWithoutUi() {
        theTitle = (EditText) findViewById(R.id.theTitle);
        theContent = (EditText) findViewById(R.id.theContent);
    }

    @Override
    protected void listenToUi() {
        uiWatcher.listenTo(theTitle);
        uiWatcher.listenTo(theContent);
    }

    @Override
    protected void ignoreUi() {
        uiWatcher.ignore(theTitle);
        uiWatcher.ignore(theContent);
    }

    @Override
    protected void updateModelFromUi() {
        model.setTitle(theTitle.getText().toString());
        model.setContent(theContent.getText().toString());
    }

    @Override
    protected void updateUiFromModel() {
        theTitle.setText(model.getTitle());
        theContent.setText(model.getContent());
    }

    @Override
    protected View findSaveButton() {
        return findViewById(R.id.en_save_button);
    }

    @Override
    protected View findDeleteButton() {
        return findViewById(R.id.en_delete_button);
    }

    @Override
    protected View findCancelButton() {
        return findViewById(R.id.en_cancel_button);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_edit_note;
    }

    @Override
    protected int getDeleteTitleStringId() {
        return R.string.en_delete_title;
    }

    @Override
    protected int getDeleteMessageStringId() {
        return R.string.en_delete_message;
    }

    @Override
    protected DaoMaster createDaoMaster(Context context) throws Exception {
        return new DndMaster(context);
    }

    @Override
    protected WriteDao createWriteDao(DaoMaster daoMaster) throws Exception {
        return new NotesDao(daoMaster);
    }

}
