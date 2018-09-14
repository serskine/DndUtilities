package com.soupthatisthick.util.view;

import android.content.Context;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.Html;
import android.text.Spanned;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.widget.TextView;

import com.soupthatisthick.encounterbuilder.util.view.ViewUtil;
import com.soupthatisthick.util.Logger;

/**
 * Created by Owner on 3/27/2017.
 * Copyright of Stuart Marr Erskine, all rights reserved.
 */

public class HtmlView extends android.support.v7.widget.AppCompatTextView {

    protected String html = null;

    public HtmlView(Context context) {
        super(context);
    }

    @SuppressWarnings("deprecation")
    protected static final Spanned htmlString(String text)
    {
        Spanned toRet;

        if (text==null) text = "";
        text = ViewUtil.preprocessHtml(text);
        text = text.trim();

        Logger.info("TEXT: " + text);

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N)
        {
            toRet = Html.fromHtml(text,Html.FROM_HTML_MODE_LEGACY);
        } else
        {
            toRet = Html.fromHtml(text);
        }
        return toRet;

    }


    public HtmlView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public HtmlView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void setHtml(String html)
    {
        this.html = htmlString(html).toString();
        setText(this.html);
    }

    public String getHtml()
    {
        return this.html;
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
