package com.soupthatisthick.encounterbuilder.util.translater;

import com.soupthatisthick.encounterbuilder.util.Text;
import com.soupthatisthick.util.Logger;

import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Owner on 3/2/2017.
 * Copyright of Stuart Marr Erskine, all rights reserved.
 */

public class ScoreTranslater {

    /**
     * use this regular expression to see if a string matches the pattern
     */
    public static final String REGEX = "\\w*(\\d+)\\w*\\([+-] *\\d+\\)\\w*";

    public static final ScoreTranslater INSTANCE = new ScoreTranslater();

    private ScoreTranslater()
    {

    }

    public final int toScore(String input, int prevScore)
    {
        try {
            Pattern pattern = Pattern.compile(REGEX);
            Matcher matcher = pattern.matcher(input);
            String scoreText = matcher.group(1);
            return Integer.parseInt(scoreText);
        } catch (Exception e) {
            Logger.error("Failed to extrace score value from " + Text.quote(input) + ". Assuming previous value " + prevScore, e);
            return prevScore;
        }
    }

    public final String toText(int score)
    {
        int mod = (score-10)/2;
        String sign = (mod>=0) ? "+" : "";
        return String.format(Locale.getDefault(), "%d (%s%d)", score, sign, mod);
    }

    public final int getMod(int score)
    {
        return (score-10)/2;
    }
}
