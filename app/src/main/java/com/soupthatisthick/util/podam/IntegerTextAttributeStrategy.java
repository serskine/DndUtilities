package com.soupthatisthick.util.podam;

import org.apache.commons.lang3.RandomUtils;

import uk.co.jemos.podam.common.AttributeStrategy;

public class IntegerTextAttributeStrategy implements AttributeStrategy<String> {
    @Override
    public String getValue() {
        return Integer.toString(RandomUtils.nextInt(0, 100));
    }
}
