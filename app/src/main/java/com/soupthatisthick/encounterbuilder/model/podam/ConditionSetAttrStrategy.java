package com.soupthatisthick.encounterbuilder.model.podam;

import org.apache.commons.lang3.RandomUtils;

import java.lang.annotation.Annotation;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import uk.co.jemos.podam.common.AttributeStrategy;

public class ConditionSetAttrStrategy implements AttributeStrategy<String> {
    String[] CONDITIONS = new String[] {
            "blinded",
            "charmed",
            "deafened",
            "frightened",
            "grappled",
            "incapacitated",
            "invisible",
            "paralyzed",
            "petrified",
            "poisoned",
            "prone",
            "restrained",
            "stunned"
    };

    @Override
    public String getValue(Class<?> attrType, List<Annotation> attrAnnotations) {
        if (RandomUtils.nextBoolean()) {
            Set<String> types = new HashSet<>();
            final int numTries = RandomUtils.nextInt(0, CONDITIONS.length-2);

            for(int i=0; i<numTries; i++) {
                final int idx = RandomUtils.nextInt(0, CONDITIONS.length);
                final String type = CONDITIONS[idx];
                types.add(type);
            }

            StringBuilder sb = new StringBuilder();
            boolean isFirst = true;
            for(String chosen : types) {
                if (!isFirst) {
                    sb.append(", ");
                }
                sb.append(chosen);
                isFirst = false;
            }
            return sb.toString();
        } else {
            return "";
        }
    }
}
