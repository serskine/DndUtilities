package com.soupthatisthick.encounterbuilder.util.listeners;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.HashSet;

/**
 * Created by Owner on 2/27/2017.
 * Copyright of Stuart Marr Erskine, all rights reserved.
 */

public abstract class UiWatcher implements
    Spinner.OnItemSelectedListener,
    TextView.OnEditorActionListener,
    TextWatcher,
    CheckBox.OnCheckedChangeListener
{

    private HashSet<View> uiControls = new HashSet<>();

    /**
     * This method is called every time a control this is listening to has been changed by the user
     */
    protected abstract void onUiUpdate();

    public final synchronized boolean isListeningTo(View view)
    {
        return uiControls.contains(view);
    }

    public final synchronized void listenTo(Spinner spinner)
    {
        if (!uiControls.contains(spinner)) {
            uiControls.add(spinner);
            spinner.setOnItemSelectedListener(this);
        }
    }
    public final synchronized void ignore(Spinner spinner)
    {
        if (uiControls.contains(spinner)) {
            uiControls.remove(spinner);
            spinner.setOnItemSelectedListener(null);
        }
    }
    public final synchronized void listenTo(EditText editText)
    {
        if (!uiControls.contains(editText)) {
            uiControls.add(editText);
            editText.setOnEditorActionListener(this);
            editText.addTextChangedListener(this);
        }
    }
    public final synchronized void ignore(EditText editText)
    {
        if (uiControls.contains(editText)) {
            uiControls.remove(editText);
            editText.setOnEditorActionListener(null);
            editText.removeTextChangedListener(this);
        }
    }

    public final synchronized void listenTo(CheckBox checkBox)
    {
        if (!uiControls.contains(checkBox)) {
            uiControls.add(checkBox);
            checkBox.setOnCheckedChangeListener(this);
        }
    }

    public final synchronized void ignore(CheckBox checkBox)
    {
        if (uiControls.contains(checkBox)) {
            uiControls.remove(checkBox);
            checkBox.setOnCheckedChangeListener(null);
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        onUiUpdate();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        onUiUpdate();
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        onUiUpdate();
    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable s) {

    }

    @Override
    public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
        onUiUpdate();
        return false;
    }

    /**
     * Called when the checked state of a compound button has changed.
     *
     * @param buttonView The compound button view whose state has changed.
     * @param isChecked  The new checked state of buttonView.
     */
    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        onUiUpdate();
    }
}