package com.soupthatisthick.util.podam;

import org.apache.commons.lang3.RandomUtils;

import uk.co.jemos.podam.common.AttributeStrategy;

public class ProficiencyAttributeStrategy implements AttributeStrategy<Integer> {
    @Override
    public Integer getValue() {
        if (RandomUtils.nextBoolean()) {
            return RandomUtils.nextInt(2, 7);
        } else {
            return 0;
        }
    }
}
