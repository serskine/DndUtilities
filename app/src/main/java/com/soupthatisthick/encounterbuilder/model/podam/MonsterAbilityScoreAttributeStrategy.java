package com.soupthatisthick.encounterbuilder.model.podam;

import org.apache.commons.lang3.RandomUtils;

import java.lang.annotation.Annotation;
import java.util.List;

import uk.co.jemos.podam.common.AttributeStrategy;

public class MonsterAbilityScoreAttributeStrategy implements AttributeStrategy<Integer> {

    @Override
    public Integer getValue(Class<?> attrType, List<Annotation> attrAnnotations) {
        return RandomUtils.nextInt(1, 7) + RandomUtils.nextInt(1, 7) + RandomUtils.nextInt(1, 7) + RandomUtils.nextInt(1, 7);
    }
}
