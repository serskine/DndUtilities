package com.soupthatisthick.util.podam;

import org.apache.commons.lang3.RandomUtils;

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
    public String getValue() {
        final int idx = RandomUtils.nextInt(0, SOURCES.length);
        return SOURCES[idx];
    }

}
