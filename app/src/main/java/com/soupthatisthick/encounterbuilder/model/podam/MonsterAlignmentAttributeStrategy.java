package com.soupthatisthick.encounterbuilder.model.podam;

import com.soupthatisthick.encounterbuilder.model.lookup.Alignment;

import org.apache.commons.lang3.RandomUtils;

import java.lang.annotation.Annotation;
import java.util.List;

import uk.co.jemos.podam.common.AttributeStrategy;

public class MonsterAlignmentAttributeStrategy implements AttributeStrategy<String> {

    @Override
    public String getValue(Class<?> attrType, List<Annotation> attrAnnotations) {
        return Alignment.values()[RandomUtils.nextInt(0, Alignment.values().length)].toString();
    }
}
