package com.soupthatisthick.util.string;

/**
 * Created by Owner on 8/5/2017.
 * Copyright of Stuart Marr Erskine, all rights reserved.
 */

public class StringUtil {
    private StringUtil() {

    }

    public static boolean isNotNullOrEmpty(String string) {
        return !isNullOrEmpty(string);
    }

    public static boolean isNullOrEmpty(String string) {
        return (string==null)||(string.trim().isEmpty());
    }

    public static boolean isNotNullButEmpty(String string) {
        return (string!=null) && (string.trim().isEmpty());
    }
}
