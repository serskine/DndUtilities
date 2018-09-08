package com.soupthatisthick.encounterbuilder.util.translater;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class CommaListTranslater {

    public static List<String> asList(String input) {
        ArrayList<String> items = new ArrayList<>();
        if (input==null) {
            return items;
        }
        String[] tokens = input.split(",");
        for(String token : tokens) {
            items.add(token.trim());
        }
        return items;
    }

    public static Set<String> asSet(String input) {
        HashSet<String> items = new HashSet<>();
        if (input==null) {
            return items;
        }
        String[] tokens = input.split(",");
        for(String token : tokens) {
            items.add(token.trim());
        }
        return items;
    }

    public static String asText(Iterable<String> items) {
        StringBuilder sb = new StringBuilder();
        boolean isFirst = true;
        for(String item : items) {
            if (!isFirst) {
                sb.append(", ");
            }
            sb.append(item);
            isFirst = false;
        }
        return sb.toString();
    }
}
