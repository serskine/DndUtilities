package com.soupthatisthick.util.podam;

import org.apache.commons.lang3.RandomUtils;

import uk.co.jemos.podam.common.AttributeStrategy;

public class MonsterArmorClassAttributeStrategy implements AttributeStrategy<String> {
    @Override
    public String getValue() {
        StringBuilder sb = new StringBuilder();

        String[] baseArmors = new String[] {
            "",
            "Padded",
            "Leather",
            "Studded Leather",

            "Hide",
            "Chain Shirt",
            "Scale Mail",
            "Breast Plate",
            "Half-Plate",
            "Spiked Armor",

            "Ring Mail",
            "Chain Mail",
            "Splint Mail",
            "Plate Mail",
        };
        int[] baseAc = new int[] {
            10,
            11,
            12,
            12,

            13,
            14,
            14,
            15,
            14,
            14,

            14,
            16,
            17,
            18
        };

        boolean isBaseArmor = RandomUtils.nextBoolean();
        sb.append("");
        if (!isBaseArmor) {
            sb.append(10 + RandomUtils.nextInt(0, 10)).append(" (Natural Armor)");
        } else {
            int idx = RandomUtils.nextInt(0, baseArmors.length);
            sb.append(10 + baseAc[idx]);
            if (baseArmors[idx].length()>0) {
                sb.append(" (").append(baseArmors[idx]).append(")");
            }
        }



        return sb.toString();
    }
}
