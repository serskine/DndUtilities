package com.soupthatisthick.encounterbuilder.model.podam;

import com.soupthatisthick.encounterbuilder.logic.Challenge;

import org.apache.commons.lang3.RandomUtils;

import java.lang.annotation.Annotation;
import java.util.List;

import uk.co.jemos.podam.common.AttributeStrategy;

public class MonsterCrStrategy implements AttributeStrategy<String> {

    @Override
    public String getValue(Class<?> attrType, List<Annotation> attrAnnotations) {
        final int idx = RandomUtils.nextInt(0, Challenge.values().length);
        Challenge cr = Challenge.values()[idx];

        if (cr.value<0) {
            return Float.toString(cr.value);
        } else {
            return Integer.toString((int) cr.value);
        }
    }
}
