package com.soupthatisthick.util.view;

import android.text.Editable;
import android.text.TextWatcher;

import com.soupthatisthick.util.Logger;

/**
 * Created by Owner on 4/4/2017.
 * Copyright of Stuart Marr Erskine, all rights reserved.
 */

public class LineBreakWatcher implements TextWatcher {
    private static final String LINE_BREAK = "<br>";
    private String prev;
    private int start, after;

    public void onTextChanged(CharSequence s, int start, int before, int count) {
        // This will do nothing
    }

    /**
     * Remembers the portion of the text that has changed
     * @param s
     * @param start
     * @param count
     * @param after
     */
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        prev = s.subSequence(start, start+count).toString();
        this.start = start;
        this.after = after;
    }

    /**
     * Looks at the previous text and determines if we should add a line break or remove the text for the line break
     * @param s
     */
    public void afterTextChanged(Editable s) {
        for(int i = s.length(); i > 0; i--) {
            if(s.subSequence(i-1, i).toString().equals("\n"))
                s.replace(i-1, i, LINE_BREAK);
        }
        if (prev.equals(LINE_BREAK))
        {
            s.replace(start, start+after, "");
        }
        Logger.info("Text changed to " + s.toString());
    }
}
