package com.soupthatisthick.encounterbuilder.model.podam;

import org.apache.commons.lang3.RandomUtils;

import java.lang.annotation.Annotation;
import java.util.List;

import uk.co.jemos.podam.common.AttributeStrategy;

public class MonsterSizeAndCreatureTypeAttrStrategy implements AttributeStrategy<String> {

    // Added types to represent probabilities
    private static final String[] CREATURE_TYPES = new String[] {
        "Abberation",
        "Beast",
        "Beast",
        "Beast",
        "Beast",
        "Beast",
        "Beast",
        "Beast",
        "celestial",
        "Construct",
        "Dragon",
        "Elemental",
        "Fey",
        "Fey",
        "Fey",
        "Fey",
        "Fiend",
        "Fiend",
        "Fiend",
        "Fiend",
        "Giant",
        "Giant",
        "Humanoid",
        "Humanoid",
        "Humanoid",
        "Humanoid",
        "Humanoid",
        "Humanoid",
        "Humanoid",
        "Humanoid",
        "Humanoid",
        "Humanoid",
        "Humanoid",
        "Humanoid",
        "Humanoid",
        "Humanoid",
        "Humanoid",
        "Humanoid",
        "Monstrosity",
        "Monstrosity",
        "Monstrosity",
        "Monstrosity",
        "Monstrosity",
        "Monstrosity",
        "Monstrosity",
        "Monstrosity",
        "Monstrosity",
        "Monstrosity",
        "Monstrosity",
        "Ooze",
        "Plant",
        "Undead"
    };


    @Override
    public String getValue(Class<?> attrType, List<Annotation> attrAnnotations) {
        int roll = RandomUtils.nextInt(0, CREATURE_TYPES.length);
        return CREATURE_TYPES[roll];
    }
}
