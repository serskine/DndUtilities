package com.soupthatisthick.encounterbuilder.view;

import android.content.Context;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * Created by Owner on 1/30/2017.
 * Copyright of Stuart Marr Erskine, all rights reserved.
 */

public class TitleTextView extends TextView {
    private String title = "";

    public TitleTextView(Context context) {
        super(context);
    }

    public TitleTextView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public TitleTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public TitleTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes)
    {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    /**
     * This will ensure that the title for the text is always displayed
     * @return getTitle() + (text set by setItem())
     */
    @Override
    public CharSequence getText()
    {
        return getTitle() + super.getText();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(@Nullable String title) {
        this.title = (title==null) ? "" : title;
    }
}
