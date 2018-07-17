package com.soupthatisthick.util.podam;

import org.apache.commons.lang3.RandomUtils;

import uk.co.jemos.podam.common.AttributeStrategy;

public class DiceRollAttributeStrategy implements AttributeStrategy<String> {

    @Override
    public String getValue() {
        int size[] = {
            4, 6, 8, 10, 12, 20, 100
        };
        return text(RandomUtils.nextInt(1, 8), size[RandomUtils.nextInt(0, size.length)], RandomUtils.nextInt(0, 10));
    }

    private String text(int n, int size, int mod) {
        StringBuilder sb = new StringBuilder();
        sb.append("");
        if (n>0 && size>0) {
            sb.append(n).append("d").append(size);
        }
        if (mod != 0) {
            if (mod > 0) {
                sb.append("+");
            } else if (mod < 0) {
                sb.append("-");
            }
            sb.append(Math.abs(mod));
        }
        return sb.toString();
    }
}
