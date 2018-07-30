package com.soupthatisthick.encounterbuilder.model.podam;

import org.apache.commons.lang3.RandomUtils;

import java.lang.annotation.Annotation;
import java.util.List;

import uk.co.jemos.podam.common.AttributeStrategy;

public class ProficiencyAttributeStrategy implements AttributeStrategy<Integer> {

    @Override
    public Integer getValue(Class<?> attrType, List<Annotation> attrAnnotations) {
        if (RandomUtils.nextBoolean()) {
            return RandomUtils.nextInt(2, 7);
        } else {
            return 0;
        }
    }
}
