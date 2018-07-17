package com.soupthatisthick.util.podam;

import org.apache.commons.lang3.RandomUtils;

import uk.co.jemos.podam.common.AttributeStrategy;

public class MonsterSizeAttributeStrategy implements AttributeStrategy<String> {
    // Added entries to represent probabilities
    private static final String SIZES[] = new String[] {
            "tiny",
            "tiny",
            "tiny",
            "tiny",
            "small",
            "small",
            "small",
            "small",
            "small",
            "small",
            "small",
            "small",
            "medium",
            "medium",
            "medium",
            "medium",
            "medium",
            "medium",
            "medium",
            "medium",
            "medium",
            "medium",
            "medium",
            "medium",
            "medium",
            "medium",
            "medium",
            "medium",
            "large",
            "large",
            "large",
            "large",
            "large",
            "large",
            "large",
            "large",
            "huge",
            "huge",
            "huge",
            "huge",
            "guargantuan",
            "guargantuan",
            "collossal"
    };

    @Override
    public String getValue() {
        int roll = RandomUtils.nextInt(0, SIZES.length);
        return SIZES[roll];
    }
}
