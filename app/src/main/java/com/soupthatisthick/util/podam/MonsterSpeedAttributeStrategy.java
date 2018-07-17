package com.soupthatisthick.util.podam;

import org.apache.commons.lang3.RandomUtils;

import java.util.HashMap;
import java.util.Map;

import uk.co.jemos.podam.common.AttributeStrategy;

public class MonsterSpeedAttributeStrategy implements AttributeStrategy<String> {
    @Override
    public String getValue() {
        boolean isWalk = RandomUtils.nextInt(1, 7) <= 4;
        boolean isFly = RandomUtils.nextInt(1, 7) == 1;
        boolean isHover = (isFly && RandomUtils.nextInt(1, 7) <= 2);
        boolean isSwim = (!isWalk) || RandomUtils.nextInt(1, 7) <= 2;
        boolean isClimb = isWalk && RandomUtils.nextInt(1, 7) <= 2;
        boolean isBurrow = isWalk && RandomUtils.nextInt(1, 7) <= 2;

        StringBuilder sb = new StringBuilder();

        Map<String, Integer> speeds = new HashMap<>();
        speeds.put("Walk", randomSpeed(isWalk));
        speeds.put("Fly", randomSpeed(isFly));
        speeds.put("Swim", randomSpeed(isSwim));
        speeds.put("Climb", randomSpeed(isClimb));
        speeds.put("Burrow", randomSpeed(isBurrow));

        sb.append("" + speeds.get("Walk"));
        speeds.remove("Walk");

        for(String moveType : speeds.keySet()) {
            int movement = speeds.get(moveType);
            if (movement>0) {
                sb.append(", ").append(moveType).append(" ");
                sb.append(movement).append(" ft.");
                if (moveType.equalsIgnoreCase("Fly") && isHover) {
                    sb.append(" (hover)");
                }
            }
        }
        return sb.toString();
    }

    private int randomSpeed(boolean isEnabled) {
        if (!isEnabled) {
            return 0;
        }
        return 5 * RandomUtils.nextInt(5, 8);
    }
}
