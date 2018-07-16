package com.soupthatisthick.encounterbuilder.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.widget.TextView;

import com.soupthatisthick.encounterbuilder.util.view.ViewUtil;

import soupthatisthick.encounterapp.R;

public class HtmlTextView extends TextView {

    private String html = "";

    public HtmlTextView(Context context) {
        super(context);
    }

    public HtmlTextView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        TypedArray a = context.getTheme().obtainStyledAttributes(
                attrs,
                R.styleable.HtmlTextView,
                0,
                0
        );
        try {
            setHtml(a.getString(R.styleable.HtmlTextView_html));
        } finally {
            a.recycle();
        }
    }

    public void setHtml(int resourceId) {
        final String resourceText = getContext().getResources().getString(resourceId);
        setHtml(resourceText);
    }

    public void setHtml(String html) {
        this.html = html;
        super.setText(ViewUtil.toHtml(this.html));
        invalidate();
        requestLayout();
    }

    public String getHtml() {
        return this.html;
    }

}
