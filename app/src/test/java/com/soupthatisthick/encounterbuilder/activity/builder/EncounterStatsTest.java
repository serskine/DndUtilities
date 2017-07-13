package com.soupthatisthick.encounterbuilder.activity.builder;

import com.soupthatisthick.encounterbuilder.model.builder.EncounterStats;

import org.junit.Test;

import static junit.framework.Assert.assertEquals;

/**
 * Created by Owner on 7/10/2017.
 * Copyright of Stuart Marr Erskine, all rights reserved.
 */

public class EncounterStatsTest {

    @Test
    public void testModifiers() {
        assertEquals("No Monsters: ", 0d, EncounterStats.MODIFIERS[0]);
        assertEquals("Rank 1     : ", 0.5d, EncounterStats.MODIFIERS[1]);
        assertEquals("Rank 2     : ", 1d, EncounterStats.MODIFIERS[2]);
        assertEquals("Rank 3     : ", 1.5d, EncounterStats.MODIFIERS[3]);
        assertEquals("Rank 4     : ", 2d, EncounterStats.MODIFIERS[4]);
        assertEquals("Rank 5     : ", 2.5d, EncounterStats.MODIFIERS[5]);
        assertEquals("Rank 6     : ", 3d, EncounterStats.MODIFIERS[6]);
        assertEquals("Rank 7     : ", 4d, EncounterStats.MODIFIERS[7]);
        assertEquals("Rank 8     : ", 5d, EncounterStats.MODIFIERS[8]);
    }

    @Test
    public void testGetEnemyXpModifierNormalAllies() {
        for(int numAllies = 3; numAllies <= 5; numAllies++) {
            String message = "Num allies = " + numAllies + ": ";
            assertEquals(message, EncounterStats.MODIFIERS[0], EncounterStats.getEnemyXpModifier(numAllies, 0));
            assertEquals(message, EncounterStats.MODIFIERS[2], EncounterStats.getEnemyXpModifier(numAllies, 1));
            assertEquals(message, EncounterStats.MODIFIERS[3], EncounterStats.getEnemyXpModifier(numAllies, 2));
            assertEquals(message, EncounterStats.MODIFIERS[4], EncounterStats.getEnemyXpModifier(numAllies, 3));
            assertEquals(message, EncounterStats.MODIFIERS[4], EncounterStats.getEnemyXpModifier(numAllies, 4));
            assertEquals(message, EncounterStats.MODIFIERS[4], EncounterStats.getEnemyXpModifier(numAllies, 5));
            assertEquals(message, EncounterStats.MODIFIERS[4], EncounterStats.getEnemyXpModifier(numAllies, 6));
            assertEquals(message, EncounterStats.MODIFIERS[5], EncounterStats.getEnemyXpModifier(numAllies, 7));
            assertEquals(message, EncounterStats.MODIFIERS[5], EncounterStats.getEnemyXpModifier(numAllies, 8));
            assertEquals(message, EncounterStats.MODIFIERS[5], EncounterStats.getEnemyXpModifier(numAllies, 9));
            assertEquals(message, EncounterStats.MODIFIERS[5], EncounterStats.getEnemyXpModifier(numAllies, 10));
            assertEquals(message, EncounterStats.MODIFIERS[6], EncounterStats.getEnemyXpModifier(numAllies, 11));
            assertEquals(message, EncounterStats.MODIFIERS[6], EncounterStats.getEnemyXpModifier(numAllies, 12));
            assertEquals(message, EncounterStats.MODIFIERS[6], EncounterStats.getEnemyXpModifier(numAllies, 13));
            assertEquals(message, EncounterStats.MODIFIERS[6], EncounterStats.getEnemyXpModifier(numAllies, 14));
            assertEquals(message, EncounterStats.MODIFIERS[7], EncounterStats.getEnemyXpModifier(numAllies, 15));
            assertEquals(message, EncounterStats.MODIFIERS[7], EncounterStats.getEnemyXpModifier(numAllies, 16));
            assertEquals(message, EncounterStats.MODIFIERS[7], EncounterStats.getEnemyXpModifier(numAllies, 17));
            assertEquals(message, EncounterStats.MODIFIERS[7], EncounterStats.getEnemyXpModifier(numAllies, 18));
            assertEquals(message, EncounterStats.MODIFIERS[7], EncounterStats.getEnemyXpModifier(numAllies, 19));
        }
    }


    @Test
    public void testGetEnemyXpModifierWeakAllies() {
        for(int numAllies = 0; numAllies <= 2; numAllies++) {
            String message = "Num allies = " + numAllies + ": ";
            assertEquals(message, EncounterStats.MODIFIERS[0], EncounterStats.getEnemyXpModifier(numAllies, 0));
            assertEquals(message, EncounterStats.MODIFIERS[1], EncounterStats.getEnemyXpModifier(numAllies, 1));
            assertEquals(message, EncounterStats.MODIFIERS[2], EncounterStats.getEnemyXpModifier(numAllies, 2));
            assertEquals(message, EncounterStats.MODIFIERS[3], EncounterStats.getEnemyXpModifier(numAllies, 3));
            assertEquals(message, EncounterStats.MODIFIERS[3], EncounterStats.getEnemyXpModifier(numAllies, 4));
            assertEquals(message, EncounterStats.MODIFIERS[3], EncounterStats.getEnemyXpModifier(numAllies, 5));
            assertEquals(message, EncounterStats.MODIFIERS[3], EncounterStats.getEnemyXpModifier(numAllies, 6));
            assertEquals(message, EncounterStats.MODIFIERS[4], EncounterStats.getEnemyXpModifier(numAllies, 7));
            assertEquals(message, EncounterStats.MODIFIERS[4], EncounterStats.getEnemyXpModifier(numAllies, 8));
            assertEquals(message, EncounterStats.MODIFIERS[4], EncounterStats.getEnemyXpModifier(numAllies, 9));
            assertEquals(message, EncounterStats.MODIFIERS[4], EncounterStats.getEnemyXpModifier(numAllies, 10));
            assertEquals(message, EncounterStats.MODIFIERS[5], EncounterStats.getEnemyXpModifier(numAllies, 11));
            assertEquals(message, EncounterStats.MODIFIERS[5], EncounterStats.getEnemyXpModifier(numAllies, 12));
            assertEquals(message, EncounterStats.MODIFIERS[5], EncounterStats.getEnemyXpModifier(numAllies, 13));
            assertEquals(message, EncounterStats.MODIFIERS[5], EncounterStats.getEnemyXpModifier(numAllies, 14));
            assertEquals(message, EncounterStats.MODIFIERS[6], EncounterStats.getEnemyXpModifier(numAllies, 15));
            assertEquals(message, EncounterStats.MODIFIERS[6], EncounterStats.getEnemyXpModifier(numAllies, 16));
            assertEquals(message, EncounterStats.MODIFIERS[6], EncounterStats.getEnemyXpModifier(numAllies, 17));
            assertEquals(message, EncounterStats.MODIFIERS[6], EncounterStats.getEnemyXpModifier(numAllies, 18));
            assertEquals(message, EncounterStats.MODIFIERS[6], EncounterStats.getEnemyXpModifier(numAllies, 19));
        }
    }

    @Test
    public void testGetEnemyXpModifierStrongAllies() {
        for(int numAllies = 6; numAllies <= 10; numAllies++) {
            String message = "Num allies = " + numAllies + ": ";
            assertEquals(message, EncounterStats.MODIFIERS[0], EncounterStats.getEnemyXpModifier(numAllies, 0));
            assertEquals(message, EncounterStats.MODIFIERS[3], EncounterStats.getEnemyXpModifier(numAllies, 1));
            assertEquals(message, EncounterStats.MODIFIERS[4], EncounterStats.getEnemyXpModifier(numAllies, 2));
            assertEquals(message, EncounterStats.MODIFIERS[5], EncounterStats.getEnemyXpModifier(numAllies, 3));
            assertEquals(message, EncounterStats.MODIFIERS[5], EncounterStats.getEnemyXpModifier(numAllies, 4));
            assertEquals(message, EncounterStats.MODIFIERS[5], EncounterStats.getEnemyXpModifier(numAllies, 5));
            assertEquals(message, EncounterStats.MODIFIERS[5], EncounterStats.getEnemyXpModifier(numAllies, 6));
            assertEquals(message, EncounterStats.MODIFIERS[6], EncounterStats.getEnemyXpModifier(numAllies, 7));
            assertEquals(message, EncounterStats.MODIFIERS[6], EncounterStats.getEnemyXpModifier(numAllies, 8));
            assertEquals(message, EncounterStats.MODIFIERS[6], EncounterStats.getEnemyXpModifier(numAllies, 9));
            assertEquals(message, EncounterStats.MODIFIERS[6], EncounterStats.getEnemyXpModifier(numAllies, 10));
            assertEquals(message, EncounterStats.MODIFIERS[7], EncounterStats.getEnemyXpModifier(numAllies, 11));
            assertEquals(message, EncounterStats.MODIFIERS[7], EncounterStats.getEnemyXpModifier(numAllies, 12));
            assertEquals(message, EncounterStats.MODIFIERS[7], EncounterStats.getEnemyXpModifier(numAllies, 13));
            assertEquals(message, EncounterStats.MODIFIERS[7], EncounterStats.getEnemyXpModifier(numAllies, 14));
            assertEquals(message, EncounterStats.MODIFIERS[8], EncounterStats.getEnemyXpModifier(numAllies, 15));
            assertEquals(message, EncounterStats.MODIFIERS[8], EncounterStats.getEnemyXpModifier(numAllies, 16));
            assertEquals(message, EncounterStats.MODIFIERS[8], EncounterStats.getEnemyXpModifier(numAllies, 17));
            assertEquals(message, EncounterStats.MODIFIERS[8], EncounterStats.getEnemyXpModifier(numAllies, 18));
            assertEquals(message, EncounterStats.MODIFIERS[8], EncounterStats.getEnemyXpModifier(numAllies, 19));
        }
    }


}
