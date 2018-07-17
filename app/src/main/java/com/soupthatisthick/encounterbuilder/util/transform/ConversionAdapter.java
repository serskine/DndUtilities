package com.soupthatisthick.encounterbuilder.util.transform;

public interface ConversionAdapter<SourceType, TargetType> {
    void apply(final SourceType source, final TargetType target);
}
