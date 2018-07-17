package com.soupthatisthick.util.podam;

import org.apache.commons.lang3.RandomUtils;

import uk.co.jemos.podam.common.AttributeStrategy;

public class MonsterAbilityScoreAttributeStrategy implements AttributeStrategy<Integer> {
    @Override
    public Integer getValue() {
        return RandomUtils.nextInt(1, 7) + RandomUtils.nextInt(1, 7) + RandomUtils.nextInt(1, 7) + RandomUtils.nextInt(1, 7);
    }
}
