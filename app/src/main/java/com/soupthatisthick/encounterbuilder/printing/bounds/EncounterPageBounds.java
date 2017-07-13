package com.soupthatisthick.encounterbuilder.printing.bounds;

import android.graphics.RectF;

import com.soupthatisthick.encounterbuilder.printing.PageBounds;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Owner on 4/24/2017.
 * Copyright of Stuart Marr Erskine, all rights reserved.
 */

public class EncounterPageBounds extends PageBounds {
    private static final int WIDTH = 2625;
    private static final int HEIGHT = 3397;

    /**
     * @return a {@link Map <String,  RectF >} containing all the fields on the page image before zooming.
     */
    @Override
    public Map<String, RectF> getRawMapping() {
        Map<String, RectF> map = new HashMap<>();
        for(Field field : Field.values())
        {
            String name = field.toString();
            map.put(name, field.bounds);
        }
        return map;
    }

    @Override
    public float getRawPageWidth() {
        return WIDTH;
    }

    @Override
    public float getRawPageHeight() {
        return HEIGHT;
    }

    private static final int M1_TO_M2 = 934;
    private static final int M1_TO_M3 = 1866;

    private static final int HEADER_COL_1_LEFT = 1167;
    private static final int HEADER_COL_1_WIDTH = 2113 - HEADER_COL_1_LEFT;
    private static final int HEADER_COL_2_LEFT = 2124;
    private static final int HEADER_COL_2_WIDTH = 2625 - HEADER_COL_2_LEFT;
    private static final int HEADER_ROW_1_BOTTOM = 287;
    private static final int HEADER_ROW_2_BOTTOM = 382;
    private static final int HEADER_ROW_HEIGHT = 52;

    private static final int INIT_LEFT = 175;

    private static final int INIT_HEIGHT = 55;
    private static final int INIT_WIDTH = 586-INIT_LEFT;

    private static final int INIT_BOTTOM_25 = 687;
    private static final int INIT_BOTTOM_24 = 753;
    private static final int INIT_BOTTOM_23 = 807;
    private static final int INIT_BOTTOM_22 = 867;
    private static final int INIT_BOTTOM_21 = 928;
    private static final int INIT_BOTTOM_20 = 988;
    private static final int INIT_BOTTOM_19 = 1048;
    private static final int INIT_BOTTOM_18 = 1109;
    private static final int INIT_BOTTOM_17 = 1169;
    private static final int INIT_BOTTOM_16 = 1229;
    private static final int INIT_BOTTOM_15 = 1290;
    private static final int INIT_BOTTOM_14 = 1350;
    private static final int INIT_BOTTOM_13 = 1410;
    private static final int INIT_BOTTOM_12 = 1471;
    private static final int INIT_BOTTOM_11 = 1531;
    private static final int INIT_BOTTOM_10 = 1591;
    private static final int INIT_BOTTOM_9 = 1652;
    private static final int INIT_BOTTOM_8 = 1712;
    private static final int INIT_BOTTOM_7 = 1772;
    private static final int INIT_BOTTOM_6 = 1833;
    private static final int INIT_BOTTOM_5 = 1893;
    private static final int INIT_BOTTOM_4 = 1953;
    private static final int INIT_BOTTOM_3 = 2014;
    private static final int INIT_BOTTOM_2 = 2074;
    private static final int INIT_BOTTOM_1 = 2134;
    private static final int INIT_BOTTOM_0 = 2195;
    private static final int INIT_BOTTOM_N1 = 2255;
    private static final int INIT_BOTTOM_N2 = 2315;
    private static final int INIT_BOTTOM_N3 = 2376;
    private static final int INIT_BOTTOM_N4 = 2436;
    private static final int INIT_BOTTOM_N5 = 2496;


    private static final int NOTES_LEFT = 105;
    private static final int NOTES_TOP = 2588;
    private static final int NOTES_WIDTH = 507;
    private static final int NOTES_HEIGHT = 665;

    private static final int STAT_BOX_WIDTH = 110;
    private static final int STAT_BOX_HEIGHT = 75;

    private static final int ABIL_MOD_COL_1_LEFT = 732;
    private static final int ABIL_MOD_COL_2_LEFT = 916;
    private static final int ABIL_MOD_COL_3_LEFT = 1102;

    private static final int ABIL_SCORE_WIDTH = 65;
    private static final int ABIL_SCORE_HEIGHT = 35;

    private static final int ABIL_SCORE_COL_1_LEFT = 752;
    private static final int ABIL_SCORE_COL_2_LEFT = 938;
    private static final int ABIL_SCORE_COL_3_LEFT = 1126;

    private static final int M1_ABIL_SCORE_ROW_1_TOP = 813;
    private static final int M1_ABIL_SCORE_ROW_2_TOP = 1026;
    private static final int M1_ABIL_MOD_ROW_1_TOP = 878;
    private static final int M1_ABIL_MOD_ROW_2_TOP = 1095;
    private static final int M1_STAT_ROW_TOP = 1280;

    private static final int M2_ABIL_SCORE_ROW_1_TOP = M1_ABIL_SCORE_ROW_1_TOP + M1_TO_M2;
    private static final int M2_ABIL_SCORE_ROW_2_TOP = M1_ABIL_SCORE_ROW_2_TOP + M1_TO_M2;
    private static final int M2_ABIL_MOD_ROW_1_TOP = M1_ABIL_MOD_ROW_1_TOP + M1_TO_M2;
    private static final int M2_ABIL_MOD_ROW_2_TOP = M1_ABIL_MOD_ROW_2_TOP + M1_TO_M2;
    private static final int M2_STAT_ROW_TOP = M1_STAT_ROW_TOP + M1_TO_M2;

    private static final int M3_ABIL_SCORE_ROW_1_TOP = M1_ABIL_SCORE_ROW_1_TOP + M1_TO_M3;
    private static final int M3_ABIL_SCORE_ROW_2_TOP = M1_ABIL_SCORE_ROW_2_TOP + M1_TO_M3;
    private static final int M3_ABIL_MOD_ROW_1_TOP = M1_ABIL_MOD_ROW_1_TOP + M1_TO_M3;
    private static final int M3_ABIL_MOD_ROW_2_TOP = M1_ABIL_MOD_ROW_2_TOP + M1_TO_M3;
    private static final int M3_STAT_ROW_TOP = M1_STAT_ROW_TOP + M1_TO_M3;

    private static final int M_INIT_LEFT = ABIL_MOD_COL_1_LEFT;
    private static final int M_SPEED_LEFT = ABIL_MOD_COL_2_LEFT;
    private static final int M_AC_LEFT = ABIL_MOD_COL_3_LEFT;
    private static final int M_NAME_LEFT = 714;
    private static final int M_NAME_WIDTH = 420;
    private static final int M_NAME_HEIGHT = 60;

    private static final int M1_NAME_TOP = 1130;
    private static final int M2_NAME_TOP = M1_NAME_TOP + M1_TO_M2;
    private static final int M3_NAME_TOP = M1_NAME_TOP + M1_TO_M3;

    private static final int M_HP_1_LEFT = 1315;
    private static final int M_HP_2_LEFT = 1535;
    private static final int M_HP_3_LEFT = 1758;
    private static final int M_HP_4_LEFT = 1980;
    private static final int M_HP_5_LEFT = 2200;
    private static final int M_HP_6_LEFT = 2423;

    private static final int M_ATTACK_COL_1_LEFT = 1317;
    private static final int M_ATTACK_COL_2_LEFT = 1697;
    private static final int M_ATTACK_COL_3_LEFT = 1743;

    private static final int M1_ATTACK_ROW_1_TOP = 854;
    private static final int M1_ATTACK_ROW_2_TOP = 928;
    private static final int M1_ATTACK_ROW_3_TOP = 1004;

    private static final int M2_ATTACK_ROW_1_TOP = 854 + M1_TO_M2;
    private static final int M2_ATTACK_ROW_2_TOP = 928 + M1_TO_M2;
    private static final int M2_ATTACK_ROW_3_TOP = 1004 + M1_TO_M2;

    private static final int M3_ATTACK_ROW_1_TOP = 854 + M1_TO_M3;
    private static final int M3_ATTACK_ROW_2_TOP = 928 + M1_TO_M3;
    private static final int M3_ATTACK_ROW_3_TOP = 1004 + M1_TO_M3;

    private static final int M_ATTACK_COL_1_WIDTH = 260;
    private static final int M_ATTACK_COL_2_WIDTH = 127;
    private static final int M_ATTACK_COL_3_WIDTH = 254;

    private static final int M_ATTACK_ROW_HEIGHT = 65;

    private static final int M1_SPELLCASTING_TOP = 1083;
    private static final int M2_SPELLCASTING_TOP = M1_SPELLCASTING_TOP + M1_TO_M2;
    private static final int M3_SPELLCASTING_TOP = M1_SPELLCASTING_TOP + M1_TO_M3;

    private static final int M_SPELLCASTING_WIDTH = 681;
    private static final int M_SPELLCASTING_HEIGHT = 85;

    private static final int M_FEATURES_AND_SKILLS_WIDTH = 468;
    private static final int M_FEATURES_AND_SKILLS_HEIGHT = 555;
    private static final int M_FEATURES_AND_SKILLS_LEFT = 2087;
    private static final int M1_FEATURES_AND_SKILLS_TOP = 621;
    private static final int M2_FEATURES_AND_SKILLS_TOP = 621 + M1_TO_M2;
    private static final int M3_FEATURES_AND_SKILLS_TOP = 621 + M1_TO_M3;

    // 2087, 621

    public enum Field {
        PAGE            (0,     0,      WIDTH,      HEIGHT),
        ENCOUNTER_NAME  (HEADER_COL_1_LEFT, HEADER_ROW_1_BOTTOM, HEADER_COL_1_WIDTH, -HEADER_ROW_HEIGHT),
        DIFFICULTY      (HEADER_COL_2_LEFT, HEADER_ROW_1_BOTTOM, HEADER_COL_2_WIDTH, -HEADER_ROW_HEIGHT),
        XP_VALUE        (HEADER_COL_1_LEFT, HEADER_ROW_2_BOTTOM, HEADER_COL_1_WIDTH, -HEADER_ROW_HEIGHT),
        LOCATION        (HEADER_COL_2_LEFT, HEADER_ROW_2_BOTTOM, HEADER_COL_2_WIDTH, -HEADER_ROW_HEIGHT),
        INIT_NEG_5      (INIT_LEFT, INIT_BOTTOM_N5, INIT_WIDTH, -INIT_HEIGHT),
        INIT_NEG_4      (INIT_LEFT, INIT_BOTTOM_N4, INIT_WIDTH, -INIT_HEIGHT),
        INIT_NEG_3      (INIT_LEFT, INIT_BOTTOM_N3, INIT_WIDTH, -INIT_HEIGHT),
        INIT_NEG_2      (INIT_LEFT, INIT_BOTTOM_N2, INIT_WIDTH, -INIT_HEIGHT),
        INIT_NEG_1      (INIT_LEFT, INIT_BOTTOM_N1, INIT_WIDTH, -INIT_HEIGHT),
        INIT_ZERO       (INIT_LEFT, INIT_BOTTOM_0, INIT_WIDTH, -INIT_HEIGHT),
        INIT_1          (INIT_LEFT, INIT_BOTTOM_1, INIT_WIDTH, -INIT_HEIGHT),
        INIT_2          (INIT_LEFT, INIT_BOTTOM_2, INIT_WIDTH, -INIT_HEIGHT),
        INIT_3          (INIT_LEFT, INIT_BOTTOM_3, INIT_WIDTH, -INIT_HEIGHT),
        INIT_4          (INIT_LEFT, INIT_BOTTOM_4, INIT_WIDTH, -INIT_HEIGHT),
        INIT_5          (INIT_LEFT, INIT_BOTTOM_5, INIT_WIDTH, -INIT_HEIGHT),
        INIT_6          (INIT_LEFT, INIT_BOTTOM_6, INIT_WIDTH, -INIT_HEIGHT),
        INIT_7          (INIT_LEFT, INIT_BOTTOM_7, INIT_WIDTH, -INIT_HEIGHT),
        INIT_8          (INIT_LEFT, INIT_BOTTOM_8, INIT_WIDTH, -INIT_HEIGHT),
        INIT_9          (INIT_LEFT, INIT_BOTTOM_9, INIT_WIDTH, -INIT_HEIGHT),
        INIT_10         (INIT_LEFT, INIT_BOTTOM_10, INIT_WIDTH, -INIT_HEIGHT),
        INIT_11         (INIT_LEFT, INIT_BOTTOM_11, INIT_WIDTH, -INIT_HEIGHT),
        INIT_12         (INIT_LEFT, INIT_BOTTOM_12, INIT_WIDTH, -INIT_HEIGHT),
        INIT_13         (INIT_LEFT, INIT_BOTTOM_13, INIT_WIDTH, -INIT_HEIGHT),
        INIT_14         (INIT_LEFT, INIT_BOTTOM_14, INIT_WIDTH, -INIT_HEIGHT),
        INIT_15         (INIT_LEFT, INIT_BOTTOM_15, INIT_WIDTH, -INIT_HEIGHT),
        INIT_16         (INIT_LEFT, INIT_BOTTOM_16, INIT_WIDTH, -INIT_HEIGHT),
        INIT_17         (INIT_LEFT, INIT_BOTTOM_17, INIT_WIDTH, -INIT_HEIGHT),
        INIT_18         (INIT_LEFT, INIT_BOTTOM_18, INIT_WIDTH, -INIT_HEIGHT),
        INIT_19         (INIT_LEFT, INIT_BOTTOM_19, INIT_WIDTH, -INIT_HEIGHT),
        INIT_20         (INIT_LEFT, INIT_BOTTOM_20, INIT_WIDTH, -INIT_HEIGHT),
        INIT_21         (INIT_LEFT, INIT_BOTTOM_21, INIT_WIDTH, -INIT_HEIGHT),
        INIT_22         (INIT_LEFT, INIT_BOTTOM_22, INIT_WIDTH, -INIT_HEIGHT),
        INIT_23         (INIT_LEFT, INIT_BOTTOM_23, INIT_WIDTH, -INIT_HEIGHT),
        INIT_24         (INIT_LEFT, INIT_BOTTOM_24, INIT_WIDTH, -INIT_HEIGHT),
        INIT_25         (INIT_LEFT, INIT_BOTTOM_25, INIT_WIDTH, -INIT_HEIGHT),

        NOTES (NOTES_LEFT, NOTES_TOP, NOTES_WIDTH, NOTES_HEIGHT),

        // REGIONS FOR EACH MONSTER
        MONSTER_1_NAME          (M_NAME_LEFT, M1_NAME_TOP, M_NAME_WIDTH, M_NAME_HEIGHT),
        MONSTER_1_STR_SCORE     (ABIL_SCORE_COL_1_LEFT, M1_ABIL_SCORE_ROW_1_TOP, ABIL_SCORE_WIDTH, ABIL_SCORE_HEIGHT),
        MONSTER_1_DEX_SCORE     (ABIL_SCORE_COL_2_LEFT, M1_ABIL_SCORE_ROW_1_TOP, ABIL_SCORE_WIDTH, ABIL_SCORE_HEIGHT),
        MONSTER_1_CON_SCORE     (ABIL_SCORE_COL_3_LEFT, M1_ABIL_SCORE_ROW_1_TOP, ABIL_SCORE_WIDTH, ABIL_SCORE_HEIGHT),
        MONSTER_1_INT_SCORE     (ABIL_SCORE_COL_1_LEFT, M1_ABIL_SCORE_ROW_2_TOP, ABIL_SCORE_WIDTH, ABIL_SCORE_HEIGHT),
        MONSTER_1_WIS_SCORE     (ABIL_SCORE_COL_2_LEFT, M1_ABIL_SCORE_ROW_2_TOP, ABIL_SCORE_WIDTH, ABIL_SCORE_HEIGHT),
        MONSTER_1_CHA_SCORE     (ABIL_SCORE_COL_3_LEFT, M1_ABIL_SCORE_ROW_2_TOP, ABIL_SCORE_WIDTH, ABIL_SCORE_HEIGHT),
        MONSTER_1_STR_MOD     (ABIL_MOD_COL_1_LEFT, M1_ABIL_MOD_ROW_1_TOP, STAT_BOX_WIDTH, STAT_BOX_HEIGHT),
        MONSTER_1_DEX_MOD     (ABIL_MOD_COL_2_LEFT, M1_ABIL_MOD_ROW_1_TOP, STAT_BOX_WIDTH, STAT_BOX_HEIGHT),
        MONSTER_1_CON_MOD     (ABIL_MOD_COL_3_LEFT, M1_ABIL_MOD_ROW_1_TOP, STAT_BOX_WIDTH, STAT_BOX_HEIGHT),
        MONSTER_1_INT_MOD     (ABIL_MOD_COL_1_LEFT, M1_ABIL_MOD_ROW_2_TOP, STAT_BOX_WIDTH, STAT_BOX_HEIGHT),
        MONSTER_1_WIS_MOD     (ABIL_MOD_COL_2_LEFT, M1_ABIL_MOD_ROW_2_TOP, STAT_BOX_WIDTH, STAT_BOX_HEIGHT),
        MONSTER_1_CHA_MOD     (ABIL_MOD_COL_3_LEFT, M1_ABIL_MOD_ROW_2_TOP, STAT_BOX_WIDTH, STAT_BOX_HEIGHT),
        MONSTER_1_INIT        (M_INIT_LEFT, M1_STAT_ROW_TOP, STAT_BOX_WIDTH, STAT_BOX_HEIGHT),
        MONSTER_1_SPEED       (M_SPEED_LEFT, M1_STAT_ROW_TOP, STAT_BOX_WIDTH, STAT_BOX_HEIGHT),
        MONSTER_1_AC          (M_AC_LEFT, M1_STAT_ROW_TOP, STAT_BOX_WIDTH, STAT_BOX_HEIGHT),
        MONSTER_1_HP_1          (M_HP_1_LEFT, M1_STAT_ROW_TOP, STAT_BOX_WIDTH, STAT_BOX_HEIGHT),
        MONSTER_1_HP_2          (M_HP_2_LEFT, M1_STAT_ROW_TOP, STAT_BOX_WIDTH, STAT_BOX_HEIGHT),
        MONSTER_1_HP_3          (M_HP_3_LEFT, M1_STAT_ROW_TOP, STAT_BOX_WIDTH, STAT_BOX_HEIGHT),
        MONSTER_1_HP_4          (M_HP_4_LEFT, M1_STAT_ROW_TOP, STAT_BOX_WIDTH, STAT_BOX_HEIGHT),
        MONSTER_1_HP_5          (M_HP_5_LEFT, M1_STAT_ROW_TOP, STAT_BOX_WIDTH, STAT_BOX_HEIGHT),
        MONSTER_1_HP_6          (M_HP_6_LEFT, M1_STAT_ROW_TOP, STAT_BOX_WIDTH, STAT_BOX_HEIGHT),
        MONSTER_1_ATTACK_1      (M_ATTACK_COL_1_LEFT, M1_ATTACK_ROW_1_TOP, M_ATTACK_COL_1_WIDTH, M_ATTACK_ROW_HEIGHT),
        MONSTER_1_ATTACK_2      (M_ATTACK_COL_1_LEFT, M1_ATTACK_ROW_2_TOP, M_ATTACK_COL_1_WIDTH, M_ATTACK_ROW_HEIGHT),
        MONSTER_1_ATTACK_3      (M_ATTACK_COL_1_LEFT, M1_ATTACK_ROW_3_TOP, M_ATTACK_COL_1_WIDTH, M_ATTACK_ROW_HEIGHT),
        MONSTER_1_ATK_BONUS_1   (M_ATTACK_COL_2_LEFT, M1_ATTACK_ROW_1_TOP, M_ATTACK_COL_2_WIDTH, M_ATTACK_ROW_HEIGHT),
        MONSTER_1_ATK_BONUS_2   (M_ATTACK_COL_2_LEFT, M1_ATTACK_ROW_2_TOP, M_ATTACK_COL_2_WIDTH, M_ATTACK_ROW_HEIGHT),
        MONSTER_1_ATK_BONUS_3   (M_ATTACK_COL_2_LEFT, M1_ATTACK_ROW_3_TOP, M_ATTACK_COL_2_WIDTH, M_ATTACK_ROW_HEIGHT),
        MONSTER_1_DAMAGE_1      (M_ATTACK_COL_3_LEFT, M1_ATTACK_ROW_1_TOP, M_ATTACK_COL_3_WIDTH, M_ATTACK_ROW_HEIGHT),
        MONSTER_1_DAMAGE_2      (M_ATTACK_COL_3_LEFT, M1_ATTACK_ROW_2_TOP, M_ATTACK_COL_3_WIDTH, M_ATTACK_ROW_HEIGHT),
        MONSTER_1_DAMAGE_3      (M_ATTACK_COL_3_LEFT, M1_ATTACK_ROW_3_TOP, M_ATTACK_COL_3_WIDTH, M_ATTACK_ROW_HEIGHT),
        MONSTER_1_ATK_AND_SPELL (M_ATTACK_COL_1_LEFT, M1_SPELLCASTING_TOP, M_SPELLCASTING_WIDTH, M_SPELLCASTING_HEIGHT),
        MONSTER_1_FEATURES_AND_SKILLS (M_FEATURES_AND_SKILLS_LEFT, M1_FEATURES_AND_SKILLS_TOP, M_FEATURES_AND_SKILLS_WIDTH, M_FEATURES_AND_SKILLS_HEIGHT),

        // REGIONS FOR EACH MONSTER
        MONSTER_2_NAME          (M_NAME_LEFT, M2_NAME_TOP, M_NAME_WIDTH, M_NAME_HEIGHT),
        MONSTER_2_STR_SCORE     (ABIL_SCORE_COL_1_LEFT, M2_ABIL_SCORE_ROW_1_TOP, ABIL_SCORE_WIDTH, ABIL_SCORE_HEIGHT),
        MONSTER_2_DEX_SCORE     (ABIL_SCORE_COL_2_LEFT, M2_ABIL_SCORE_ROW_1_TOP, ABIL_SCORE_WIDTH, ABIL_SCORE_HEIGHT),
        MONSTER_2_CON_SCORE     (ABIL_SCORE_COL_3_LEFT, M2_ABIL_SCORE_ROW_1_TOP, ABIL_SCORE_WIDTH, ABIL_SCORE_HEIGHT),
        MONSTER_2_INT_SCORE     (ABIL_SCORE_COL_1_LEFT, M2_ABIL_SCORE_ROW_2_TOP, ABIL_SCORE_WIDTH, ABIL_SCORE_HEIGHT),
        MONSTER_2_WIS_SCORE     (ABIL_SCORE_COL_2_LEFT, M2_ABIL_SCORE_ROW_2_TOP, ABIL_SCORE_WIDTH, ABIL_SCORE_HEIGHT),
        MONSTER_2_CHA_SCORE     (ABIL_SCORE_COL_3_LEFT, M2_ABIL_SCORE_ROW_2_TOP, ABIL_SCORE_WIDTH, ABIL_SCORE_HEIGHT),
        MONSTER_2_STR_MOD     (ABIL_MOD_COL_1_LEFT, M2_ABIL_MOD_ROW_1_TOP, STAT_BOX_WIDTH, STAT_BOX_HEIGHT),
        MONSTER_2_DEX_MOD     (ABIL_MOD_COL_2_LEFT, M2_ABIL_MOD_ROW_1_TOP, STAT_BOX_WIDTH, STAT_BOX_HEIGHT),
        MONSTER_2_CON_MOD     (ABIL_MOD_COL_3_LEFT, M2_ABIL_MOD_ROW_1_TOP, STAT_BOX_WIDTH, STAT_BOX_HEIGHT),
        MONSTER_2_INT_MOD     (ABIL_MOD_COL_1_LEFT, M2_ABIL_MOD_ROW_2_TOP, STAT_BOX_WIDTH, STAT_BOX_HEIGHT),
        MONSTER_2_WIS_MOD     (ABIL_MOD_COL_2_LEFT, M2_ABIL_MOD_ROW_2_TOP, STAT_BOX_WIDTH, STAT_BOX_HEIGHT),
        MONSTER_2_CHA_MOD     (ABIL_MOD_COL_3_LEFT, M2_ABIL_MOD_ROW_2_TOP, STAT_BOX_WIDTH, STAT_BOX_HEIGHT),
        MONSTER_2_INIT        (M_INIT_LEFT, M2_STAT_ROW_TOP, STAT_BOX_WIDTH, STAT_BOX_HEIGHT),
        MONSTER_2_SPEED       (M_SPEED_LEFT, M2_STAT_ROW_TOP, STAT_BOX_WIDTH, STAT_BOX_HEIGHT),
        MONSTER_2_AC          (M_AC_LEFT, M2_STAT_ROW_TOP, STAT_BOX_WIDTH, STAT_BOX_HEIGHT),
        MONSTER_2_HP_1          (M_HP_1_LEFT, M2_STAT_ROW_TOP, STAT_BOX_WIDTH, STAT_BOX_HEIGHT),
        MONSTER_2_HP_2          (M_HP_2_LEFT, M2_STAT_ROW_TOP, STAT_BOX_WIDTH, STAT_BOX_HEIGHT),
        MONSTER_2_HP_3          (M_HP_3_LEFT, M2_STAT_ROW_TOP, STAT_BOX_WIDTH, STAT_BOX_HEIGHT),
        MONSTER_2_HP_4          (M_HP_4_LEFT, M2_STAT_ROW_TOP, STAT_BOX_WIDTH, STAT_BOX_HEIGHT),
        MONSTER_2_HP_5          (M_HP_5_LEFT, M2_STAT_ROW_TOP, STAT_BOX_WIDTH, STAT_BOX_HEIGHT),
        MONSTER_2_HP_6          (M_HP_6_LEFT, M2_STAT_ROW_TOP, STAT_BOX_WIDTH, STAT_BOX_HEIGHT),
        MONSTER_2_ATTACK_1      (M_ATTACK_COL_1_LEFT, M2_ATTACK_ROW_1_TOP, M_ATTACK_COL_1_WIDTH, M_ATTACK_ROW_HEIGHT),
        MONSTER_2_ATTACK_2      (M_ATTACK_COL_1_LEFT, M2_ATTACK_ROW_2_TOP, M_ATTACK_COL_1_WIDTH, M_ATTACK_ROW_HEIGHT),
        MONSTER_2_ATTACK_3      (M_ATTACK_COL_1_LEFT, M2_ATTACK_ROW_3_TOP, M_ATTACK_COL_1_WIDTH, M_ATTACK_ROW_HEIGHT),
        MONSTER_2_ATK_BONUS_1   (M_ATTACK_COL_2_LEFT, M2_ATTACK_ROW_1_TOP, M_ATTACK_COL_2_WIDTH, M_ATTACK_ROW_HEIGHT),
        MONSTER_2_ATK_BONUS_2   (M_ATTACK_COL_2_LEFT, M2_ATTACK_ROW_2_TOP, M_ATTACK_COL_2_WIDTH, M_ATTACK_ROW_HEIGHT),
        MONSTER_2_ATK_BONUS_3   (M_ATTACK_COL_2_LEFT, M2_ATTACK_ROW_3_TOP, M_ATTACK_COL_2_WIDTH, M_ATTACK_ROW_HEIGHT),
        MONSTER_2_DAMAGE_1      (M_ATTACK_COL_3_LEFT, M2_ATTACK_ROW_1_TOP, M_ATTACK_COL_3_WIDTH, M_ATTACK_ROW_HEIGHT),
        MONSTER_2_DAMAGE_2      (M_ATTACK_COL_3_LEFT, M2_ATTACK_ROW_2_TOP, M_ATTACK_COL_3_WIDTH, M_ATTACK_ROW_HEIGHT),
        MONSTER_2_DAMAGE_3      (M_ATTACK_COL_3_LEFT, M2_ATTACK_ROW_3_TOP, M_ATTACK_COL_3_WIDTH, M_ATTACK_ROW_HEIGHT),
        MONSTER_2_ATK_AND_SPELL (M_ATTACK_COL_1_LEFT, M2_SPELLCASTING_TOP, M_SPELLCASTING_WIDTH, M_SPELLCASTING_HEIGHT),
        MONSTER_2_FEATURES_AND_SKILLS (M_FEATURES_AND_SKILLS_LEFT, M2_FEATURES_AND_SKILLS_TOP, M_FEATURES_AND_SKILLS_WIDTH, M_FEATURES_AND_SKILLS_HEIGHT),

        // REGIONS FOR EACH MONSTER
        MONSTER_3_NAME          (M_NAME_LEFT, M3_NAME_TOP, M_NAME_WIDTH, M_NAME_HEIGHT),
        MONSTER_3_STR_SCORE     (ABIL_SCORE_COL_1_LEFT, M3_ABIL_SCORE_ROW_1_TOP, ABIL_SCORE_WIDTH, ABIL_SCORE_HEIGHT),
        MONSTER_3_DEX_SCORE     (ABIL_SCORE_COL_2_LEFT, M3_ABIL_SCORE_ROW_1_TOP, ABIL_SCORE_WIDTH, ABIL_SCORE_HEIGHT),
        MONSTER_3_CON_SCORE     (ABIL_SCORE_COL_3_LEFT, M3_ABIL_SCORE_ROW_1_TOP, ABIL_SCORE_WIDTH, ABIL_SCORE_HEIGHT),
        MONSTER_3_INT_SCORE     (ABIL_SCORE_COL_1_LEFT, M3_ABIL_SCORE_ROW_2_TOP, ABIL_SCORE_WIDTH, ABIL_SCORE_HEIGHT),
        MONSTER_3_WIS_SCORE     (ABIL_SCORE_COL_2_LEFT, M3_ABIL_SCORE_ROW_2_TOP, ABIL_SCORE_WIDTH, ABIL_SCORE_HEIGHT),
        MONSTER_3_CHA_SCORE     (ABIL_SCORE_COL_3_LEFT, M3_ABIL_SCORE_ROW_2_TOP, ABIL_SCORE_WIDTH, ABIL_SCORE_HEIGHT),
        MONSTER_3_STR_MOD     (ABIL_MOD_COL_1_LEFT, M3_ABIL_MOD_ROW_1_TOP, STAT_BOX_WIDTH, STAT_BOX_HEIGHT),
        MONSTER_3_DEX_MOD     (ABIL_MOD_COL_2_LEFT, M3_ABIL_MOD_ROW_1_TOP, STAT_BOX_WIDTH, STAT_BOX_HEIGHT),
        MONSTER_3_CON_MOD     (ABIL_MOD_COL_3_LEFT, M3_ABIL_MOD_ROW_1_TOP, STAT_BOX_WIDTH, STAT_BOX_HEIGHT),
        MONSTER_3_INT_MOD     (ABIL_MOD_COL_1_LEFT, M3_ABIL_MOD_ROW_2_TOP, STAT_BOX_WIDTH, STAT_BOX_HEIGHT),
        MONSTER_3_WIS_MOD     (ABIL_MOD_COL_2_LEFT, M3_ABIL_MOD_ROW_2_TOP, STAT_BOX_WIDTH, STAT_BOX_HEIGHT),
        MONSTER_3_CHA_MOD     (ABIL_MOD_COL_3_LEFT, M3_ABIL_MOD_ROW_2_TOP, STAT_BOX_WIDTH, STAT_BOX_HEIGHT),
        MONSTER_3_INIT        (M_INIT_LEFT, M3_STAT_ROW_TOP, STAT_BOX_WIDTH, STAT_BOX_HEIGHT),
        MONSTER_3_SPEED       (M_SPEED_LEFT, M3_STAT_ROW_TOP, STAT_BOX_WIDTH, STAT_BOX_HEIGHT),
        MONSTER_3_AC          (M_AC_LEFT, M3_STAT_ROW_TOP, STAT_BOX_WIDTH, STAT_BOX_HEIGHT),
        MONSTER_3_HP_1          (M_HP_1_LEFT, M3_STAT_ROW_TOP, STAT_BOX_WIDTH, STAT_BOX_HEIGHT),
        MONSTER_3_HP_2          (M_HP_2_LEFT, M3_STAT_ROW_TOP, STAT_BOX_WIDTH, STAT_BOX_HEIGHT),
        MONSTER_3_HP_3          (M_HP_3_LEFT, M3_STAT_ROW_TOP, STAT_BOX_WIDTH, STAT_BOX_HEIGHT),
        MONSTER_3_HP_4          (M_HP_4_LEFT, M3_STAT_ROW_TOP, STAT_BOX_WIDTH, STAT_BOX_HEIGHT),
        MONSTER_3_HP_5          (M_HP_5_LEFT, M3_STAT_ROW_TOP, STAT_BOX_WIDTH, STAT_BOX_HEIGHT),
        MONSTER_3_HP_6          (M_HP_6_LEFT, M3_STAT_ROW_TOP, STAT_BOX_WIDTH, STAT_BOX_HEIGHT),
        MONSTER_3_ATTACK_1      (M_ATTACK_COL_1_LEFT, M3_ATTACK_ROW_1_TOP, M_ATTACK_COL_1_WIDTH, M_ATTACK_ROW_HEIGHT),
        MONSTER_3_ATTACK_2      (M_ATTACK_COL_1_LEFT, M3_ATTACK_ROW_2_TOP, M_ATTACK_COL_1_WIDTH, M_ATTACK_ROW_HEIGHT),
        MONSTER_3_ATTACK_3      (M_ATTACK_COL_1_LEFT, M3_ATTACK_ROW_3_TOP, M_ATTACK_COL_1_WIDTH, M_ATTACK_ROW_HEIGHT),
        MONSTER_3_ATK_BONUS_1   (M_ATTACK_COL_2_LEFT, M3_ATTACK_ROW_1_TOP, M_ATTACK_COL_2_WIDTH, M_ATTACK_ROW_HEIGHT),
        MONSTER_3_ATK_BONUS_2   (M_ATTACK_COL_2_LEFT, M3_ATTACK_ROW_2_TOP, M_ATTACK_COL_2_WIDTH, M_ATTACK_ROW_HEIGHT),
        MONSTER_3_ATK_BONUS_3   (M_ATTACK_COL_2_LEFT, M3_ATTACK_ROW_3_TOP, M_ATTACK_COL_2_WIDTH, M_ATTACK_ROW_HEIGHT),
        MONSTER_3_DAMAGE_1      (M_ATTACK_COL_3_LEFT, M3_ATTACK_ROW_1_TOP, M_ATTACK_COL_3_WIDTH, M_ATTACK_ROW_HEIGHT),
        MONSTER_3_DAMAGE_2      (M_ATTACK_COL_3_LEFT, M3_ATTACK_ROW_2_TOP, M_ATTACK_COL_3_WIDTH, M_ATTACK_ROW_HEIGHT),
        MONSTER_3_DAMAGE_3      (M_ATTACK_COL_3_LEFT, M3_ATTACK_ROW_3_TOP, M_ATTACK_COL_3_WIDTH, M_ATTACK_ROW_HEIGHT),
        MONSTER_3_ATK_AND_SPELL (M_ATTACK_COL_1_LEFT, M3_SPELLCASTING_TOP, M_SPELLCASTING_WIDTH, M_SPELLCASTING_HEIGHT),
        MONSTER_3_FEATURES_AND_SKILLS (M_FEATURES_AND_SKILLS_LEFT, M3_FEATURES_AND_SKILLS_TOP, M_FEATURES_AND_SKILLS_WIDTH, M_FEATURES_AND_SKILLS_HEIGHT),

        ;

        public final RectF bounds;


        private Field()
        {
            this(0,0,0,0);
        }

        /**
         * Specifies the bounds
         * @param x         horizontal coordinate of the left bound
         * @param y         vertical coordinate of the top bound
         * @param width     x+width equals the x coordinate of the right side if z is positive
         * @param height    y+width equals the y coordinate of the bottom side if y is positive
         */
        private Field(int x, int y, int width, int height)
        {
            int top, bottom, left, right;

            left = (width<0) ? x+width : x;
            top = (height<0) ? y+height : y;
            right = (height<0) ? x : x+width;
            bottom = (height<0) ? y : y+height;

            bounds = new RectF(
                left,
                top,
                right,
                bottom
            );
        }


    }

}
