package com.soupthatisthick.encounterbuilder.model.podam;

import org.apache.commons.lang3.RandomUtils;

import java.lang.annotation.Annotation;
import java.util.List;

import uk.co.jemos.podam.common.AttributeStrategy;

public class IntegerTextAttributeStrategy implements AttributeStrategy<String> {

    @Override
    public String getValue(Class<?> attrType, List<Annotation> attrAnnotations) {
        return Integer.toString(RandomUtils.nextInt(0, 100));
    }
}
