package com.soupthatisthick.util.view;

import android.content.Context;
import android.os.Build;
import android.support.annotation.CallSuper;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputConnection;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Created by Owner on 4/4/2017.
 * Copyright of Stuart Marr Erskine, all rights reserved.
 */

public class HtmlEdit extends EditText implements TextView.OnEditorActionListener {
    protected static final LineBreakWatcher LINE_BREAK_WATCHER = new LineBreakWatcher();


    public HtmlEdit(Context context) {
        super(context);
        init();
    }
    public HtmlEdit(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }
    public HtmlEdit(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public HtmlEdit(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    @Override
    public InputConnection onCreateInputConnection(EditorInfo outAttrs)
    {
        InputConnection conn = super.onCreateInputConnection(outAttrs);
        outAttrs.imeOptions &= ~EditorInfo.IME_FLAG_NO_ENTER_ACTION;
        return conn;
    }

    /**
     * Called at the end of each constructor.
     */
    @CallSuper
    protected void init()
    {
        this.addTextChangedListener(LINE_BREAK_WATCHER);
    }

    /**
     * Called when an action is being performed.
     *
     * @param v        The view that was clicked.
     * @param actionId Identifier of the action.  This will be either the
     *                 identifier you supplied, or {@link EditorInfo#IME_NULL
     *                 EditorInfo.IME_NULL} if being called due to the enter key
     *                 being pressed.
     * @param event    If triggered by an enter key, this is the event;
     *                 otherwise, this is null.
     * @return Return true if you have consumed the action, else false.
     */
    @Override
    public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
        setText(getText() + "<br>\n");
        return true;
    }
}
