package com.soupthatisthick.encounterbuilder.util.translater;

/**
 * Created by Owner on 3/1/2017.
 * Copyright of Stuart Marr Erskine, all rights reserved.
 */

public class SpellLevelTranslater {

    public static final SpellLevelTranslater INSTANCE = new SpellLevelTranslater();

    private SpellLevelTranslater()
    {

    }

    public String toText(Integer value) {
        if (value==null)
        {
            value = new Integer(0);
        }

        if (value<1)
        {
            return "Cantrip";
        }
        String suffix = getSuffix(value);
        return "" + value.toString() + suffix + " level";
    }

    public Integer toSpellLevel(String text)
    {
        if (text==null) {
            return 0;
        }

        String numbers = extractNumbers(text);
        if (numbers.isEmpty())
        {
            return 0;
        }

        try {
            return Integer.parseInt(numbers);
        } catch (NumberFormatException e) {
            return 0;
        }
    }

    private String getSuffix(int value)
    {
        int remainder = value%10;
        switch(remainder)
        {
            case 1:
                return "st";
            case 2:
                return "nd";
            case 3:
                return "rd";
            default:
                return "th";
        }
    }

    private boolean isDigit(char c) {
        switch (c) {
            case '0':
            case '1':
            case '2':
            case '3':
            case '4':
            case '5':
            case '6':
            case '7':
            case '8':
            case '9':
                return true;
            default:
                return false;
        }
    }

    private String extractNumbers(String text) {
        String numbers = "";
        for(int i=0; i<text.length(); i++) {
            char c = text.charAt(i);
            if (isDigit(c)) {
                numbers += c;
            } else if (numbers.length()>0) {
                break;
            }
        }
        return numbers;
    }
}
