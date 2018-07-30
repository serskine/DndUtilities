package com.soupthatisthick.encounterbuilder.model.podam;

import org.apache.commons.lang3.RandomUtils;

import java.lang.annotation.Annotation;
import java.util.List;

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
    public String getValue(Class<?> attrType, List<Annotation> attrAnnotations) {
        int roll = RandomUtils.nextInt(0, SUBTYPES.length);
        return SUBTYPES[roll];
    }
}
