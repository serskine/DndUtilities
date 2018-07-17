package com.soupthatisthick.util.podam;

import org.apache.commons.lang3.RandomUtils;

import uk.co.jemos.podam.common.AttributeStrategy;

public class MonsterSubtypeAttrStrategy implements AttributeStrategy<String> {
    String[] SUBTYPES = new String[] {
        "human",
        "goblin",
        "kobold",
        "orc",
        "half-elf",
        "elf",
        "gnome",
        "halfling",
        "goliath",
        "kenku",
        "triton",
        "goblin",
    };
    @Override
    public String getValue() {
        int roll = RandomUtils.nextInt(0, SUBTYPES.length);
        return SUBTYPES[roll];
    }
}
