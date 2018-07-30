package com.soupthatisthick.encounterbuilder.model.podam;

import org.apache.commons.lang3.RandomUtils;

import java.lang.annotation.Annotation;
import java.util.List;

import uk.co.jemos.podam.common.AttributeStrategy;

public class SourceMaterialAttrStrategy implements AttributeStrategy<String> {
    private String[] SOURCES = new String[] {
        "PHB",
        "DMG",
        "MM",
        "HotDQ",
        "RoT",
        "PoTA",
        "OotA",
        "CoS",
        "SKT",
        "TftYP",
        "ToA",
        "SCAG",
        "EE",
        "VOLO",
        "MToF",
        "XGtE"
    };

    @Override
    public String getValue(Class<?> attrType, List<Annotation> attrAnnotations) {
        final int idx = RandomUtils.nextInt(0, SOURCES.length);
        return SOURCES[idx];
    }
}
