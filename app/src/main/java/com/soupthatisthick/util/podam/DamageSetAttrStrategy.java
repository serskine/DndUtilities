package com.soupthatisthick.util.podam;

import org.apache.commons.lang3.RandomUtils;

import java.util.HashSet;
import java.util.Set;

import uk.co.jemos.podam.common.AttributeStrategy;

public class DamageSetAttrStrategy implements AttributeStrategy<String> {
    String[] DTYPES = new String[] {
        "bludgeoning",
        "slashing",
        "piercing",
        "necrotic",
        "fire",
        "cold",
        "thunder",
        "acid",
        "poison",
        "lightning",
        "force"
    };

    @Override
    public String getValue() {
        if (RandomUtils.nextBoolean()) {
            Set<String> types = new HashSet<>();
            final int numTries = RandomUtils.nextInt(0, DTYPES.length-2);

            for(int i=0; i<numTries; i++) {
                final int idx = RandomUtils.nextInt(0, DTYPES.length);
                final String type = DTYPES[idx];
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
