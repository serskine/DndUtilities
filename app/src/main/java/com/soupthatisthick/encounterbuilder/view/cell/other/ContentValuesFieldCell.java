package com.soupthatisthick.encounterbuilder.view.cell.other;

import android.content.ContentValues;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.soupthatisthick.encounterbuilder.util.listeners.UiWatcher;
import com.soupthatisthick.encounterbuilder.view.cell.WriteCell;

import soupthatisthick.encounterapp.R;

/**
 * Created by Owner on 3/20/2017.
 * Copyright of Stuart Marr Erskine, all rights reserved.
 */

public class ContentValuesFieldCell extends WriteCell<ContentValues> {

    String key;
    EditText editText;
    TextView textView;

    public ContentValuesFieldCell(String key, LayoutInflater inflater, View convertView, ViewGroup parent) {
        super(inflater, convertView, parent);
        this.key = key;
    }

    /**
     * This method will remove all the fields from the ui watcher so that updates do not trigger when their fields are changed
     *
     * @param watcher
     */
    @Override
    protected void ignoreUi(UiWatcher watcher) {
        watcher.ignore(editText);
    }

    /**
     * This method will add all the fields to the ui watcher so that updates will trigger when their fields are changed
     *
     * @param watcher
     */
    @Override
    protected void listenToUi(UiWatcher watcher) {
        watcher.listenTo(editText);
    }

    /**
     * This will update the model from the fields on the ui. When this is called we will not be listening to the ui so we don't
     * have to worry about resursive changes
     *
     * @param contentValues
     */
    @Override
    protected void updateModelFromUi(@Nullable ContentValues contentValues) {
        contentValues.put(key, editText.getText().toString());
    }


    @Override
    public void updateUiFromModel(@Nullable ContentValues contentValues) {
        editText.setText(contentValues.getAsString(key));
    }

    @Override
    public View createView(LayoutInflater inflater, View convertView, ViewGroup parent) {
        View view = inflater.inflate(R.layout.cell_edit_text, parent);
        editText = (EditText) view.findViewById(R.id.edit_text);
//        editText.addTextChangedListener(new LineBreakWatcher());
        editText.setHint(key);

        textView = (TextView) view.findViewById(R.id.text_view);
        textView.setText(key);
        return view;
    }

    private class LineBreakWatcher implements TextWatcher {
        private static final String LINE_BREAK = "<br>";
        private String prev;
        private int start, after;

        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            prev = s.subSequence(start, start+count).toString();
            this.start = start;
            this.after = after;
        }

        public void afterTextChanged(Editable s) {
            for(int i = s.length(); i > 0; i--) {
                if(s.subSequence(i-1, i).toString().equals("\n"))
                    s.replace(i-1, i, LINE_BREAK);
            }
            if (prev.equals(LINE_BREAK))
            {
                s.replace(start, start+after, "");
            }
        }
    }
}
