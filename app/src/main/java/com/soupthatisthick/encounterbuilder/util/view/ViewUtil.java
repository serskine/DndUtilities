package com.soupthatisthick.encounterbuilder.util.view;

import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.Html;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.TextView;

import soupthatisthick.encounterapp.R;

/**
 * Created by Owner on 2/6/2017.
 * Copyright of Stuart Marr Erskine, all rights reserved.
 */

public class ViewUtil {

    // These color values are used for pre marshmellow devices.
    // Otherwise the colors used are defined in the Colors resources file.
    public static final int ERROR_TEXT_COLOR = 0xFFFF0000;
    public static final int ERROR_HINT_COLOR = 0x77FF0000;
    public static final int INFO_TEXT_COLOR = 0xFF0051FF;
    public static final int INFO_HINT_COLOR = 0x770051FF;
    public static final int NORMAL_TEXT_COLOR = 0xFF000000;
    public static final int NORMAL_HINT_COLOR = 0x77000000;
    public static final int VALID_TEXT_COLOR = 0xFF28C251;
    public static final int VALID_HINT_COLOR = 0x7728C251;
    public static final int WARNING_TEXT_COLOR = 0xFFF5F900;
    public static final int WARING_HINT_COLOR = 0x77F5F900;

    public static final int errorTextColor(Context context)
    {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            return context.getColor(R.color.error_text_color);
        } else {
            return ERROR_TEXT_COLOR;
        }
    }

    public static final int errorHintColor(Context context)
    {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            return context.getColor(R.color.error_hint_color);
        } else {
            return ERROR_HINT_COLOR;
        }
    }

    public static final String getColoredHtml(String text, int color) {
        return getColoredHtml(text, "#" + Integer.toHexString(color));
    }
    public static final String getColoredHtml(String text, String color) {
        String input = "<font color=" + color + ">" + text + "</font>";
        return input;
    }

    public static final SpannableString getColoredSpanned(String text, int color) {
        SpannableString spannableString = new SpannableString(text);
        spannableString.setSpan(new ForegroundColorSpan(color), 0, text.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        return spannableString;
    }


    public static final int normalTextColor(Context context)
    {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            return context.getColor(R.color.normal_text_color);
        } else {
            return NORMAL_TEXT_COLOR;

        }
    }

    public static final int normalHintColor(Context context)
    {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            return context.getColor(R.color.normal_hint_color);
        } else {
            return NORMAL_HINT_COLOR;
        }
    }

    public static final int warningTextColor(Context context)
    {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            return context.getColor(R.color.warning_text_color);
        } else {
            return WARNING_TEXT_COLOR;

        }
    }

    public static final int warningHintColor(Context context)
    {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            return context.getColor(R.color.warning_hint_color);
        } else {
            return WARING_HINT_COLOR;
        }
    }


    public static final int validTextColor(Context context)
    {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            return context.getColor(R.color.valid_text_color);
        } else {
            return VALID_TEXT_COLOR;

        }
    }

    public static final int validHintColor(Context context)
    {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            return context.getColor(R.color.valid_hint_color);
        } else {
            return VALID_HINT_COLOR;
        }
    }


    public static final void markAsInvalid(@NonNull TextView editText)
    {
        Context context = editText.getContext();

        editText.setTextColor(errorTextColor(context));
        editText.setHintTextColor(errorHintColor(context));
    }

    public static final void markAsValid(@NonNull TextView editText)
    {
        Context context = editText.getContext();

        editText.setTextColor(validTextColor(context));
        editText.setHintTextColor(validHintColor(context));
    }

    public static final void markAsNormal(@NonNull TextView textView)
    {
        Context context = textView.getContext();

        textView.setTextColor(normalTextColor(context));
        textView.setHintTextColor(normalHintColor(context));
    }

    public static final void markAsWarning(@NonNull TextView textView)
    {
        Context context = textView.getContext();

        textView.setTextColor(warningTextColor(context));
        textView.setHintTextColor(warningHintColor(context));
    }



    public static final void markValidity(@NonNull TextView editText, boolean isValid)
    {
        if (isValid)
        {
            markAsValid(editText);
        } else {
            markAsInvalid(editText);
        }
    }

    public static final void setText(@NonNull TextView textView, @Nullable String text)
    {
        try {
            textView.setText(text);
            markAsValid(textView);
        } catch (Exception e) {
            textView.setText("");
            markAsInvalid(textView);
        }
    }

    /**
     * This will hide the soft keyboard given the specified activity
     * @param activity
     */
    public static final void hideInputWindow(Activity activity)
    {
        // Check if no view has focus:
        View view = activity.getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    public static final Spanned toHtml(String text)
    {
//        if (text==null) text = "";
//        text = text.trim();
//        text = text.replace("<?html>","");
//        text = text.replace("<?body>","");

        text = myHtmlString(text);

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N)
        {
            return Html.fromHtml(text,Html.FROM_HTML_MODE_LEGACY);
        } else
        {
            return Html.fromHtml(text);
        }

    }

    /**
     * This is a preprocessing method of sorts.
     * @param html
     * @return
     */
    private static final String myHtmlString(String html)
    {

        if (html==null) html = "";
        html = html.trim();
        html = html.replace("<?html>","");
        html = html.replace("<?body>","");

        String EMPTY = "";
        String parsed = html;

        String removeLeading = "\\s*<html>(\\s*<br>)*\\s*";
        String removeTrailing = "\\s*<br>*\\s*</html>\\s*";
        String removeMultiLineBreaks = "<br>(\\s*<br>)*";
        String endListElements = "</li>";
        String startListElement = "<li>";

        parsed = parsed.replace(removeLeading,EMPTY);
        parsed = parsed.replace(removeTrailing, EMPTY);
        parsed = parsed.replace(startListElement, " • ");
        parsed = parsed.replace(endListElements, "<br>");
        parsed = parsed.replace(removeMultiLineBreaks, "<br>");

        return parsed;
    }
}
