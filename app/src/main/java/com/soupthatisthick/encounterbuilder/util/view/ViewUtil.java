package com.soupthatisthick.encounterbuilder.util.view;

import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.os.PatternMatcher;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.Html;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.TextView;

import com.soupthatisthick.encounterbuilder.util.Text;
import com.soupthatisthick.util.string.Table;

import java.util.List;
import java.util.regex.Pattern;

import soupthatisthick.encounterapp.R;

/**
 * Created by Owner on 2/6/2017.
 * Copyright of Stuart Marr Erskine, all rights reserved.
 */

public class ViewUtil {
    private static final String EMPTY = "";

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

        text = preprocessHtml(text);

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N)
        {
            return Html.fromHtml(text,Html.FROM_HTML_MODE_LEGACY);
        } else
        {
            return Html.fromHtml(text);
        }

    }

    /**
     * This method will take a text string in html and turn it into a span that is visible on a text view
     * see https://commonsware.com/blog/Android/2010/05/26/html-tags-supported-by-textview.html for details.
     * Tags supported...
     *      <a href="...">
     *      <b>
     *      <big>
     *      <blockquote>
     *      <br>
     *      <cite>
     *      <dfn>
     *      <div align="...">
     *      <em>
     *      <font size="..." color="..." face="...">
     *      <h1><h2><h3><h4><h5><h6>
     *      <i>
     *      <img src="...">
     *      <p>
     *      <small>
     *      <strike>
     *      <strong>
     *      <sub>
     *      <sup>
     *      <tt>
     *      <u>
     * @param html is the html we want to transform.
     * @return the preformatted Html
     */
    public static final String preprocessHtml(final String html)
    {
        String parsed = html;

        parsed = processIrrelevantTags(parsed);
        parsed = processLeadingAndTrailingTags(parsed);
        parsed = processLineBreaks(parsed);
        parsed = processListTags(parsed);
        parsed = processItalicTags(parsed);
        parsed = processTableTags(parsed);

        return Text.toString(parsed);
    }

    /**
     * This will convert the tables into lists. It won't be pretty but it will work
     * after the lists have been processed.
     * @param html is the html we wish to process
     * @return replaces table html into list html
     */
    public static final String processTableTags(String html) {
        if (html==null) html = "";
//        String tableTags[] = {"table", "TABLE"};
//        String rowTags[] = {"tr", "TR", "tR", "Tr"};
//        String headerTags[] = {"th", "Th", "tH", "TH"};
//        String dataTags[] = {"td", "Td", "tD", "TD"};

        List<Table> tableList = Table.getTables(Table.fromHtml(html));

        // We don't support sub tables. So every odd token contains non-table content
        String[] tokens = html.split(Table.REGEX_TAG_TABLE);

        StringBuilder sb = new StringBuilder();
        int tokenIdx = 0;
        int tableIdx = 0;
        while(tokenIdx < tokens.length) {
            sb.append(tokens[tokenIdx]);
            if (tableIdx<tableList.size()) {
                Table table = tableList.get(tableIdx);
                sb.append("<p><tt>\n").append(table.describeCells(
                        false,
                        true,
                        " ",
                        "-",
                        " "
                    )
                ).append("\n</tt></p>");
                tableIdx++;
                tokenIdx++; // We want it incremented twice only if we append table data.
            }
            tokenIdx++; // Advance the token.
        }

        final String text = sb.toString();
        return text.replace("\n", "<br/>").replace(" ", "&nbsp;");
    }

    private static final String processItalicTags(String html) {

        String italicTags[] = {
                "i", "I", "em", "EM", "Em", "eM"
        };

        for (String tagName : italicTags) {
            final String openTag = "<" + tagName + ">";
            final String closeTag = "</" + tagName + ">";
            html = html.replace(openTag, "<em>");
            html = html.replace(closeTag, "</em>");
        }

        return html;
    }

    private static final String processIrrelevantTags(String html) {
        if (html==null) html = "";
        html = html.trim();
        html = html.replace("<?html>","");
        html = html.replace("<?body>","");
        return html;
    }

    private static final String processLeadingAndTrailingTags(String html) {


        String removeLeading = "\\s*<html>(\\s*<br>)*\\s*";
        String removeTrailing = "\\s*<br>*\\s*</html>\\s*";

        html = html.replace(removeLeading,EMPTY);
        html = html.replace(removeTrailing, EMPTY);

        return html;
    }

    private static final String processLineBreaks(String html) {

        String lineBreakTagNames[] = {"br", "bR", "Br", "BR"};
        for(String tagName : lineBreakTagNames) {
            String removeMultiLineBreaks = "<" + tagName + ">(\\s*<" + tagName + ">)*";
            html = html.replace(removeMultiLineBreaks, "<" + tagName + ">");
        }
        return html;
    }

    private static final String processListTags(String html) {

        String listTags[] = {
                "ol", "ul", "dl",
                "OL", "UL", "DL",
                "Ol", "Ul", "Dl",
                "oL", "uL", "dL",
        };

        String listElemTags[] = {
                "li", "LI", "Li", "lI"
        };

        for (String tagName : listTags) {
            final String openTag = "<" + tagName + ">";
            final String closeTag = "</" + tagName + ">";
            html = html.replace(openTag, "<p>");
            html = html.replace(closeTag, "</p>");
        }

        for (String tagName : listElemTags) {
            final String openTag = "<" + tagName + ">";
            final String closeTag = "</" + tagName + ">";
            html = html.replace(openTag, "<br/> • ");
            html = html.replace(closeTag, "<br/>");
        }

        return html;
    }
}
