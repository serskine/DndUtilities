package com.soupthatisthick.util.podam;

import com.soupthatisthick.encounterbuilder.model.lookup.Alignment;

import org.apache.commons.lang3.RandomUtils;

import uk.co.jemos.podam.common.AttributeStrategy;

public class MonsterAlignmentAttributeStrategy implements AttributeStrategy<String> {
    @Override
    public String getValue() {
        return Alignment.values()[RandomUtils.nextInt(0, Alignment.values().length)].toString();
    }
}
