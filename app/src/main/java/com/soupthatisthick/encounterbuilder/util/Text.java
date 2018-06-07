package com.soupthatisthick.encounterbuilder.util;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.Spanned;

import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Map;

/**
 * Created by Owner on 2/5/2017.
 * Copyright of Stuart Marr Erskine, all rights reserved.
 */

public class Text {
    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("mm/dd/yyyy");
    public static final String objectString(@Nullable Object obj)
    {
        return (obj==null) ? "null" : quote(obj.toString());
    }
    public static final String toString(String string)
    {
        return (string==null) ? "" : string;
    }
    public static final String quote(String string)
    {
        return "\"" + toString(string) + "\"";
    }
    public static final boolean isBlank(String string)
    {
        return (string==null || string.length()<1);
    }

    public static final String concat(@NonNull String seperator, Collection<String> strings)
    {
        String output = "";
        for(String string : strings)
        {
            output += (output.length()==0) ? string : seperator + string;
        }
        return output;
    }

    public static final String concat(@NonNull String seperator, String... strings)
    {
        String output = "";
        for(String string : strings)
        {
            output += (output.length()==0) ? string : seperator + string;
        }
        return output;
    }

    public static final String concatNoEmpty(@NonNull String seperator, String... strings)
    {
        String output = "";
        for(String string : strings)
        {
            if (string!=null && !string.isEmpty()) {
                output += (output.length() == 0) ? string : seperator + string;
            }
        }
        return output;
    }

    public static final String padString(String padding, int numPads)
    {
        String output = "";
        for(int i=0; i<numPads; i++)
        {
            output += padding;
        }
        return output;
    }

    public static final String fString(@Nullable String input, int fieldSize)
    {
        if (fieldSize<1) return "";
        if (input==null)
        {
            return padString(" ", fieldSize);
        } else if (input.length()<=fieldSize)
        {
            return input + padString(" ", fieldSize-input.length());
        } else {
            return input.substring(0, fieldSize);
        }
    }

    public static final String titleString(
        String text,
        char corner,
        char horizontal,
        char vertical,
        char space,
        int rowLength
    ) {
        String output = " \n";

        text = (text==null) ? "" : text;

        int spacing = rowLength-text.length()-2;
        int left = spacing/2;
        int right = spacing - left;

        String border = corner + Text.padString(""+horizontal, rowLength-2) + corner;
        String spacer = vertical + Text.padString(""+space, rowLength-2) + vertical;
        String content = vertical + Text.padString(""+space, left) + text + Text.padString(""+space, right) + vertical;

        output += "\n";
        output += border + "\n";
        output += spacer + "\n";
        output += content + "\n";
        output += spacer + "\n";
        output += border + "\n";

        return output;

    }

    public static final String mapOutput(Map<String, String> map)
    {
        int keyWidth = columnWidth(map.keySet());
        int valueWidth = columnWidth(map.values());
        String seperator = " : ";
        String output = "";
        for(String key : map.keySet())
        {
            String value = map.get(key);
            String keyString = fString(toString(key), keyWidth);
            String valueString = fString(toString(value), valueWidth);
            output += keyString + seperator + valueString + "\n";
        }
        return output;
    }

    private static final int columnWidth(Collection<String> info)
    {
        int minWidth = 0;
        for(Object element : info) {
            String value = (element==null) ? "null" : Text.toString(element.toString());
            minWidth = Math.max((value==null) ? 0 : value.length() , minWidth);
        }
        return minWidth;
    }

    public static final String bold(String text)
    {
        return "<b>" + text + "</b>";
    }

    /**
     * Used to get all the words in a search text
     * @param searchText
     * @return
     */
    public static final String[] getWords(String searchText)
    {
        searchText = searchText.toUpperCase().trim();   // Convert to upper case to make case insensitive
        String words[] = searchText.split("\\s+");  // Split on white spaces to get all words that must match something
        return words;
    }

    /**
     * Used to see if the text contains all the specified words in it anywhere
     * @param input
     * @param words
     * @return true if all words are contained within, else it returns false
     */
    public static final boolean containsAllWords(@Nullable String input, @Nullable String[] words)
    {
        if (input==null)
        {
            return false;
        }
        if (words==null || words.length<1)
        {
            return true;
        }
        for(String word : words)
        {
            if (!input.contains(word))
            {
                return false;
            }
        }
        return true;
    }


    public static final Spanned propertySpan(@NonNull String keyText, @Nullable String valueText)
    {
        keyText = (valueText==null || valueText.length()<1) ? "" : keyText;
        final SpannableStringBuilder str = new SpannableStringBuilder(keyText + valueText);
        str.setSpan(new android.text.style.StyleSpan(android.graphics.Typeface.BOLD), 0, keyText.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        return str;
    }

    public static final Spanned boldSpan(String text)
    {
        final SpannableStringBuilder str = new SpannableStringBuilder(text);
        str.setSpan(new android.text.style.StyleSpan(android.graphics.Typeface.BOLD), 0, text.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        return str;
    }

}
