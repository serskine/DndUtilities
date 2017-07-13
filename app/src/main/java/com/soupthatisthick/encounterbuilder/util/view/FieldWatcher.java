package com.soupthatisthick.encounterbuilder.util.view;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.TextView;

/**
 * Created by Owner on 2/6/2017.
 * Copyright of Stuart Marr Erskine, all rights reserved.
 */

public class FieldWatcher implements TextWatcher {
    public final String regexPattern;
    public final TextView textView;

    public FieldWatcher(@NonNull TextView textView, @Nullable String regexPattern)
    {
        this.regexPattern = regexPattern;
        this.textView = textView;
        textView.addTextChangedListener(this);
    }

    public boolean isValid(String text)
    {
        return (regexPattern==null) || text.matches(regexPattern);
    }


    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        // Do nothing
    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        // Do nothing
    }

    @Override
    public void afterTextChanged(Editable s) {
        // Change the text color
        ViewUtil.markValidity(textView, isValid(textView.getText().toString()));
    }
}
