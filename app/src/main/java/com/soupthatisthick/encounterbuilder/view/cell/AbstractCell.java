package com.soupthatisthick.encounterbuilder.view.cell;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;

import com.soupthatisthick.encounterbuilder.util.view.ViewUtil;

import soupthatisthick.encounterapp.R;

/**
 * Created by Owner on 12/22/2016.
 * Copyright of Stuart Marr Erskine, all rights reserved.
 */

public abstract class AbstractCell {
    protected View view;
    protected final LayoutInflater inflater;
    protected final View convertView;
    protected final ViewGroup parent;

    protected final String VISIBLE_UNKNOWN;
    protected final String INVISIBLE_UNKNOWN;
    protected final String UNKNOWN;


    public AbstractCell(LayoutInflater inflater, View convertView, ViewGroup parent)
    {
        this.view = createView(inflater, convertView, null);
        VISIBLE_UNKNOWN = view.getResources().getString(R.string.view_visible_unknown);
        INVISIBLE_UNKNOWN = view.getResources().getString(R.string.view_invisible_unknown);
        UNKNOWN = INVISIBLE_UNKNOWN;

        this.inflater = inflater;
        this.convertView = convertView;
        this.parent = parent;
    }

    public abstract View createView(LayoutInflater inflater, View convertView, ViewGroup parent);

    public View getView()
    {
        return view;
    }


    protected static final boolean isEmpty(CharSequence sequence)
    {
        return ((sequence==null) || (sequence.length()==0));
    }

    protected static final boolean isEmpty(WebView webView)
    {
        return (webView.getOriginalUrl()==null);
    }

    @SuppressWarnings("deprecation")
    protected static final Spanned htmlString(String text)
    {
        return ViewUtil.toHtml(text);
    }


    protected static final Spanned normalString(@Nullable String text)
    {
        if (text == null)
        {
            text = "";
        }
        final SpannableStringBuilder str = new SpannableStringBuilder(text);
        return str;
    }

    protected static final Spanned titleString(@NonNull String title, @Nullable String text)
    {
        final String modifiedTitle = ((text==null) || (text.length()<1))    ? "" : title;
        final String modifiedText = ((text==null) || (text.length()<1))     ? "" : text;
        final SpannableStringBuilder str = new SpannableStringBuilder(modifiedTitle + modifiedText);
        str.setSpan(
                new android.text.style.StyleSpan(android.graphics.Typeface.BOLD),
                0,
                modifiedTitle.length(),
                Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
        );
        return str;
    }

    protected static final Spanned boldString(String text)
    {
        final SpannableStringBuilder str = new SpannableStringBuilder(text);
        str.setSpan(new android.text.style.StyleSpan(android.graphics.Typeface.BOLD), 0, text.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        return str;
    }
}
