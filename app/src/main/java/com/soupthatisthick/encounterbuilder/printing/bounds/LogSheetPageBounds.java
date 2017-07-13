package com.soupthatisthick.encounterbuilder.printing.bounds;

import android.graphics.RectF;

import com.soupthatisthick.encounterbuilder.printing.PageBounds;
import com.soupthatisthick.encounterbuilder.util.view.Draw;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

/**
 * Created by Owner on 3/3/2017.
 * Copyright of Stuart Marr Erskine, all rights reserved.
 * This class will contain boundaries of all the fields contained on the logsheet to match the
 * logsheet.png image packaged with the app. After drawing to the image, the image can then scaled
 * before printing and it should look proper.
 */

public class LogSheetPageBounds extends PageBounds {

    private static final int THE_WIDTH = 2625;     // Width of the logsheet image in pixels
    private static final int THE_HEIGHT = 3375;    // Height of the logsheet image in pixels

    private static final int THE_COL_HEAD_A = 189;   // left
    private static final int THE_COL_HEAD_B = 1078;  // left
    private static final int THE_COL_HEAD_C = 1966;  // left

    private static final int THE_COL_HEAD_WIDTH_A = 859;
    private static final int THE_COL_HEAD_WIDTH_B = 859;
    private static final int THE_COL_HEAD_WIDTH_C = 472;

    private static final int THE_ROW_HEAD_A = 534;   // bottom
    private static final int THE_ROW_HEAD_B = 663;   // bottom

    private static final int THE_ROW_HEAD_HEIGHT = 68;


    private static final int THE_COL_XP = 189;
    private static final int THE_COL_GOLD = 666;
    private static final int THE_COL_DOWNTIME = 1164;
    private static final int THE_COL_RENOWN = 1650;
    private static final int THE_COL_MAGIC_ITEMS = 2129;
    private static final int THE_COL_NOTES_START = THE_COL_GOLD;
    private static final int THE_COL_NOTES_LEFT = THE_COL_XP;


    private static final int THE_COL_WIDTH = 454;
    private static final int THE_COL_MAGIC_ITEMS_WIDTH = 307;
    private static final int THE_COL_BIG_WIDTH = 787;
    private static final int THE_COL_NOTES_BC_WIDTH = 2249;
    private static final int THE_COL_NOTES_A_WIDTH = THE_COL_NOTES_LEFT + THE_COL_NOTES_BC_WIDTH - THE_COL_NOTES_START;

    private static final int THE_ROW_HEIGHT = 80;
    private static final int THE_ROW_SMALL_HEIGHT = 50;

    private static final int THE_DIFF_DETAIL_TO_START = 125;
    private static final int THE_DIFF_START_TO_EARN = 125;
    private static final int THE_DIFF_EARN_TO_TOTAL = 125;
    private static final int THE_DIFF_TOTAL_TO_NOTES_A = 125;
    private static final int THE_DIFF_NOTES_A_TO_NOTES_B = 62;
    private static final int THE_DIFF_NOTES_B_TO_NOTES_C = 62;


    private static final int THE_ROW_DETAIL_1 = 861;
    private static final int THE_ROW_START_1 = 986;  // +125
    private static final int THE_ROW_EARN_1 = 1111;  // +125
    private static final int THE_ROW_TOTAL_1 = 1236; // +125
    private static final int THE_ROW_NOTES_A_1 = 1361;   // +125
    private static final int THE_ROW_NOTES_B_1 = 1423;   // +62
    private static final int THE_ROW_NOTES_C_1 = 1485;   // +62

    private static final int THE_ROW_DETAIL_2 = 1692;
    private static final int THE_ROW_START_2 = THE_ROW_DETAIL_2 + THE_DIFF_DETAIL_TO_START;
    private static final int THE_ROW_EARN_2 = THE_ROW_START_2 + THE_DIFF_START_TO_EARN;
    private static final int THE_ROW_TOTAL_2 = THE_ROW_EARN_2 + THE_DIFF_EARN_TO_TOTAL;
    private static final int THE_ROW_NOTES_A_2 = THE_ROW_TOTAL_2 + THE_DIFF_TOTAL_TO_NOTES_A;
    private static final int THE_ROW_NOTES_B_2 = THE_ROW_NOTES_A_2 + THE_DIFF_NOTES_A_TO_NOTES_B;
    private static final int THE_ROW_NOTES_C_2 = THE_ROW_NOTES_B_2 + THE_DIFF_NOTES_B_TO_NOTES_C;

    private static final int THE_ROW_DETAIL_3 = 2527;
    private static final int THE_ROW_START_3 = THE_ROW_DETAIL_3 + THE_DIFF_DETAIL_TO_START;
    private static final int THE_ROW_EARN_3 = THE_ROW_START_3 + THE_DIFF_START_TO_EARN;
    private static final int THE_ROW_TOTAL_3 = THE_ROW_EARN_3 + THE_DIFF_EARN_TO_TOTAL;
    private static final int THE_ROW_NOTES_A_3 = THE_ROW_TOTAL_3 + THE_DIFF_TOTAL_TO_NOTES_A;
    private static final int THE_ROW_NOTES_B_3 = THE_ROW_NOTES_A_3 + THE_DIFF_NOTES_A_TO_NOTES_B;
    private static final int THE_ROW_NOTES_C_3 = THE_ROW_NOTES_B_3 + THE_DIFF_NOTES_B_TO_NOTES_C;

    public LogSheetPageBounds()
    {
        this.mod = 1F;
    }

    /**
     * This will set the modifier to ensure the width of the page after modification matches
     * the specified width in pixels. The aspect rstio will be retained
     * @param width
     */
    public void setLogsheetWidth(float width)
    {
        setMod(width / THE_WIDTH);
    }

    /**
     * This will set the modifier to ensure the height of the page after modification matches
     * the specified height in pixels. The aspect ratio will be retained
     * @param height
     */
    public void setLogsheetHeight(float height)
    {
        setMod(height/ THE_HEIGHT);
    }


    private float mod;

    public void setMod(float mod) {
        this.mod = mod;
    }

    public float getMod()
    {
        return this.mod;
    }

    //
    // LOGSHEET HEADER INFORMATION
    //

    /**
     * @return a {@link Map <String,  RectF >} containing all the fields on the page image before zooming.
     */
    @Override
    public Map<String, RectF> getRawMapping() {
        return mapping();
    }

    @Override
    public float getRawPageWidth() {
        return THE_WIDTH;
    }

    @Override
    public float getRawPageHeight() {
        return THE_HEIGHT;
    }

    public RectF CHARACTER_NAME() { return Draw.properRect(THE_COL_HEAD_A *mod, THE_ROW_HEAD_A *mod, THE_COL_HEAD_WIDTH_A *mod, THE_ROW_HEAD_HEIGHT *mod); }
    public RectF PLAYER_NAME() { return Draw.properRect(THE_COL_HEAD_A *mod, THE_ROW_HEAD_B *mod, THE_COL_HEAD_WIDTH_A *mod, THE_ROW_HEAD_HEIGHT *mod); }

    public RectF CLASSES_AND_LEVELS() { return Draw.properRect(THE_COL_HEAD_B *mod, THE_ROW_HEAD_A *mod, THE_COL_HEAD_WIDTH_B *mod, THE_ROW_HEAD_HEIGHT *mod); }
    public RectF PLAYER_DCI() { return Draw.properRect(THE_COL_HEAD_B *mod, THE_ROW_HEAD_B *mod, THE_COL_HEAD_WIDTH_B *mod, THE_ROW_HEAD_HEIGHT *mod); }

    public RectF FACTION() { return Draw.properRect(THE_COL_HEAD_C *mod, THE_ROW_HEAD_A *mod, THE_COL_HEAD_WIDTH_C *mod, THE_ROW_HEAD_HEIGHT *mod); }
    public RectF SHEET_NUM() { return Draw.properRect(THE_COL_HEAD_C *mod, THE_ROW_HEAD_B *mod, THE_COL_HEAD_WIDTH_C *mod, THE_ROW_HEAD_HEIGHT *mod); }


    //
    // STARTING BOUNDS
    //

    public RectF XP_START_1() { return Draw.properRect(    THE_COL_XP *mod, THE_ROW_START_1 *mod, THE_COL_WIDTH *mod, THE_ROW_HEIGHT *mod); }
    public RectF XP_START_2() { return Draw.properRect(    THE_COL_XP *mod, THE_ROW_START_2 *mod, THE_COL_WIDTH *mod, THE_ROW_HEIGHT *mod); }
    public RectF XP_START_3() { return Draw.properRect(    THE_COL_XP *mod, THE_ROW_START_3 *mod, THE_COL_WIDTH *mod, THE_ROW_HEIGHT *mod); }

    public RectF GOLD_START_1() { return Draw.properRect(    THE_COL_GOLD *mod, THE_ROW_START_1 *mod, THE_COL_WIDTH *mod, THE_ROW_HEIGHT *mod); }
    public RectF GOLD_START_2() { return Draw.properRect(    THE_COL_GOLD *mod, THE_ROW_START_2 *mod, THE_COL_WIDTH *mod, THE_ROW_HEIGHT *mod); }
    public RectF GOLD_START_3() { return Draw.properRect(    THE_COL_GOLD *mod, THE_ROW_START_3 *mod, THE_COL_WIDTH *mod, THE_ROW_HEIGHT *mod); }

    public RectF DOWNTIME_START_1() { return Draw.properRect(    THE_COL_DOWNTIME *mod, THE_ROW_START_1 *mod, THE_COL_WIDTH *mod, THE_ROW_HEIGHT *mod); }
    public RectF DOWNTIME_START_2() { return Draw.properRect(    THE_COL_DOWNTIME *mod, THE_ROW_START_2 *mod, THE_COL_WIDTH *mod, THE_ROW_HEIGHT *mod); }
    public RectF DOWNTIME_START_3() { return Draw.properRect(    THE_COL_DOWNTIME *mod, THE_ROW_START_3 *mod, THE_COL_WIDTH *mod, THE_ROW_HEIGHT *mod); }

    public RectF RENOWN_START_1() { return Draw.properRect(    THE_COL_RENOWN *mod, THE_ROW_START_1 *mod, THE_COL_WIDTH *mod, THE_ROW_HEIGHT *mod); }
    public RectF RENOWN_START_2() { return Draw.properRect(    THE_COL_RENOWN *mod, THE_ROW_START_2 *mod, THE_COL_WIDTH *mod, THE_ROW_HEIGHT *mod); }
    public RectF RENOWN_START_3() { return Draw.properRect(    THE_COL_RENOWN *mod, THE_ROW_START_3 *mod, THE_COL_WIDTH *mod, THE_ROW_HEIGHT *mod); }

    public RectF MAGIC_ITEMS_START_1() { return Draw.properRect(    THE_COL_MAGIC_ITEMS *mod, THE_ROW_START_1 *mod, THE_COL_MAGIC_ITEMS_WIDTH *mod, THE_ROW_HEIGHT *mod); }
    public RectF MAGIC_ITEMS_START_2() { return Draw.properRect(    THE_COL_MAGIC_ITEMS *mod, THE_ROW_START_2 *mod, THE_COL_MAGIC_ITEMS_WIDTH *mod, THE_ROW_HEIGHT *mod); }
    public RectF MAGIC_ITEMS_START_3() { return Draw.properRect(    THE_COL_MAGIC_ITEMS *mod, THE_ROW_START_3 *mod, THE_COL_MAGIC_ITEMS_WIDTH *mod, THE_ROW_HEIGHT *mod); }

    //
    // EARNING BOUNDS
    //

    public RectF XP_EARN_1() { return Draw.properRect(    THE_COL_XP *mod, THE_ROW_EARN_1 *mod, THE_COL_WIDTH *mod, THE_ROW_HEIGHT *mod); }
    public RectF XP_EARN_2() { return Draw.properRect(    THE_COL_XP *mod, THE_ROW_EARN_2 *mod, THE_COL_WIDTH *mod, THE_ROW_HEIGHT *mod); }
    public RectF XP_EARN_3() { return Draw.properRect(    THE_COL_XP *mod, THE_ROW_EARN_3 *mod, THE_COL_WIDTH *mod, THE_ROW_HEIGHT *mod); }

    public RectF GOLD_EARN_1() { return Draw.properRect(    THE_COL_GOLD *mod, THE_ROW_EARN_1 *mod, THE_COL_WIDTH *mod, THE_ROW_HEIGHT *mod); }
    public RectF GOLD_EARN_2() { return Draw.properRect(    THE_COL_GOLD *mod, THE_ROW_EARN_2 *mod, THE_COL_WIDTH *mod, THE_ROW_HEIGHT *mod); }
    public RectF GOLD_EARN_3() { return Draw.properRect(    THE_COL_GOLD *mod, THE_ROW_EARN_3 *mod, THE_COL_WIDTH *mod, THE_ROW_HEIGHT *mod); }

    public RectF DOWNTIME_EARN_1() { return Draw.properRect(    THE_COL_DOWNTIME *mod, THE_ROW_EARN_1 *mod, THE_COL_WIDTH *mod, THE_ROW_HEIGHT *mod); }
    public RectF DOWNTIME_EARN_2() { return Draw.properRect(    THE_COL_DOWNTIME *mod, THE_ROW_EARN_2 *mod, THE_COL_WIDTH *mod, THE_ROW_HEIGHT *mod); }
    public RectF DOWNTIME_EARN_3() { return Draw.properRect(    THE_COL_DOWNTIME *mod, THE_ROW_EARN_3 *mod, THE_COL_WIDTH *mod, THE_ROW_HEIGHT *mod); }

    public RectF RENOWN_EARN_1() { return Draw.properRect(    THE_COL_RENOWN *mod, THE_ROW_EARN_1 *mod, THE_COL_WIDTH *mod, THE_ROW_HEIGHT *mod); }
    public RectF RENOWN_EARN_2() { return Draw.properRect(    THE_COL_RENOWN *mod, THE_ROW_EARN_2 *mod, THE_COL_WIDTH *mod, THE_ROW_HEIGHT *mod); }
    public RectF RENOWN_EARN_3() { return Draw.properRect(    THE_COL_RENOWN *mod, THE_ROW_EARN_3 *mod, THE_COL_WIDTH *mod, THE_ROW_HEIGHT *mod); }

    public RectF MAGIC_ITEMS_EARN_1() { return Draw.properRect(    THE_COL_MAGIC_ITEMS *mod, THE_ROW_EARN_1 *mod, THE_COL_MAGIC_ITEMS_WIDTH *mod, THE_ROW_HEIGHT *mod); }
    public RectF MAGIC_ITEMS_EARN_2() { return Draw.properRect(    THE_COL_MAGIC_ITEMS *mod, THE_ROW_EARN_2 *mod, THE_COL_MAGIC_ITEMS_WIDTH *mod, THE_ROW_HEIGHT *mod); }
    public RectF MAGIC_ITEMS_EARN_3() { return Draw.properRect(    THE_COL_MAGIC_ITEMS *mod, THE_ROW_EARN_3 *mod, THE_COL_MAGIC_ITEMS_WIDTH *mod, THE_ROW_HEIGHT *mod); }

    //
    // TOTAL BOUNDS
    //

    public RectF XP_TOTAL_1() { return Draw.properRect(    THE_COL_XP *mod, THE_ROW_TOTAL_1 *mod, THE_COL_WIDTH *mod, THE_ROW_HEIGHT *mod); }
    public RectF XP_TOTAL_2() { return Draw.properRect(    THE_COL_XP *mod, THE_ROW_TOTAL_2 *mod, THE_COL_WIDTH *mod, THE_ROW_HEIGHT *mod); }
    public RectF XP_TOTAL_3() { return Draw.properRect(    THE_COL_XP *mod, THE_ROW_TOTAL_3 *mod, THE_COL_WIDTH *mod, THE_ROW_HEIGHT *mod); }

    public RectF GOLD_TOTAL_1() { return Draw.properRect(    THE_COL_GOLD *mod, THE_ROW_TOTAL_1 *mod, THE_COL_WIDTH *mod, THE_ROW_HEIGHT *mod); }
    public RectF GOLD_TOTAL_2() { return Draw.properRect(    THE_COL_GOLD *mod, THE_ROW_TOTAL_2 *mod, THE_COL_WIDTH *mod, THE_ROW_HEIGHT *mod); }
    public RectF GOLD_TOTAL_3() { return Draw.properRect(    THE_COL_GOLD *mod, THE_ROW_TOTAL_3 *mod, THE_COL_WIDTH *mod, THE_ROW_HEIGHT *mod); }

    public RectF DOWNTIME_TOTAL_1() { return Draw.properRect(    THE_COL_DOWNTIME *mod, THE_ROW_TOTAL_1 *mod, THE_COL_WIDTH *mod, THE_ROW_HEIGHT *mod); }
    public RectF DOWNTIME_TOTAL_2() { return Draw.properRect(    THE_COL_DOWNTIME *mod, THE_ROW_TOTAL_2 *mod, THE_COL_WIDTH *mod, THE_ROW_HEIGHT *mod); }
    public RectF DOWNTIME_TOTAL_3() { return Draw.properRect(    THE_COL_DOWNTIME *mod, THE_ROW_TOTAL_3 *mod, THE_COL_WIDTH *mod, THE_ROW_HEIGHT *mod); }

    public RectF RENOWN_TOTAL_1() { return Draw.properRect(    THE_COL_RENOWN *mod, THE_ROW_TOTAL_1 *mod, THE_COL_WIDTH *mod, THE_ROW_HEIGHT *mod); }
    public RectF RENOWN_TOTAL_2() { return Draw.properRect(    THE_COL_RENOWN *mod, THE_ROW_TOTAL_2 *mod, THE_COL_WIDTH *mod, THE_ROW_HEIGHT *mod); }
    public RectF RENOWN_TOTAL_3() { return Draw.properRect(    THE_COL_RENOWN *mod, THE_ROW_TOTAL_3 *mod, THE_COL_WIDTH *mod, THE_ROW_HEIGHT *mod); }

    public RectF MAGIC_ITEMS_TOTAL_1() { return Draw.properRect(    THE_COL_MAGIC_ITEMS *mod, THE_ROW_TOTAL_1 *mod, THE_COL_MAGIC_ITEMS_WIDTH *mod, THE_ROW_HEIGHT *mod); }
    public RectF MAGIC_ITEMS_TOTAL_2() { return Draw.properRect(    THE_COL_MAGIC_ITEMS *mod, THE_ROW_TOTAL_2 *mod, THE_COL_MAGIC_ITEMS_WIDTH *mod, THE_ROW_HEIGHT *mod); }
    public RectF MAGIC_ITEMS_TOTAL_3() { return Draw.properRect(    THE_COL_MAGIC_ITEMS *mod, THE_ROW_TOTAL_3 *mod, THE_COL_MAGIC_ITEMS_WIDTH *mod, THE_ROW_HEIGHT *mod); }

    //
    // NOTES BOUND
    //

    public RectF NOTES_A_1() { return Draw.properRect( THE_COL_NOTES_START *mod, THE_ROW_NOTES_A_1 *mod, THE_COL_NOTES_A_WIDTH *mod, THE_ROW_SMALL_HEIGHT *mod); }
    public RectF NOTES_B_1() { return Draw.properRect( THE_COL_NOTES_LEFT *mod, THE_ROW_NOTES_B_1 *mod, THE_COL_NOTES_BC_WIDTH *mod, THE_ROW_SMALL_HEIGHT *mod); }
    public RectF NOTES_C_1() { return Draw.properRect( THE_COL_NOTES_LEFT *mod, THE_ROW_NOTES_C_1 *mod, THE_COL_NOTES_BC_WIDTH *mod, THE_ROW_SMALL_HEIGHT *mod); }

    public RectF NOTES_A_2() { return Draw.properRect( THE_COL_NOTES_START *mod, THE_ROW_NOTES_A_2 *mod, THE_COL_NOTES_A_WIDTH *mod, THE_ROW_SMALL_HEIGHT *mod); }
    public RectF NOTES_B_2() { return Draw.properRect( THE_COL_NOTES_LEFT *mod, THE_ROW_NOTES_B_2 *mod, THE_COL_NOTES_BC_WIDTH *mod, THE_ROW_SMALL_HEIGHT *mod); }
    public RectF NOTES_C_2() { return Draw.properRect( THE_COL_NOTES_LEFT *mod, THE_ROW_NOTES_C_2 *mod, THE_COL_NOTES_BC_WIDTH *mod, THE_ROW_SMALL_HEIGHT *mod); }

    public RectF NOTES_A_3() { return Draw.properRect( THE_COL_NOTES_START *mod, THE_ROW_NOTES_A_3 *mod, THE_COL_NOTES_A_WIDTH *mod, THE_ROW_SMALL_HEIGHT *mod); }
    public RectF NOTES_B_3() { return Draw.properRect( THE_COL_NOTES_LEFT *mod, THE_ROW_NOTES_B_3 *mod, THE_COL_NOTES_BC_WIDTH *mod, THE_ROW_SMALL_HEIGHT *mod); }
    public RectF NOTES_C_3() { return Draw.properRect( THE_COL_NOTES_LEFT *mod, THE_ROW_NOTES_C_3 *mod, THE_COL_NOTES_BC_WIDTH *mod, THE_ROW_SMALL_HEIGHT *mod); }

    //
    // DETAILS BOUNDS
    //

    public RectF NAME_1() { return Draw.properRect(THE_COL_XP *mod, THE_ROW_DETAIL_1 *mod, THE_COL_WIDTH *mod, THE_ROW_HEIGHT *mod); }
    public RectF NAME_2() { return Draw.properRect(THE_COL_XP *mod, THE_ROW_DETAIL_2 *mod, THE_COL_WIDTH *mod, THE_ROW_HEIGHT *mod); }
    public RectF NAME_3() { return Draw.properRect(THE_COL_XP *mod, THE_ROW_DETAIL_3 *mod, THE_COL_WIDTH *mod, THE_ROW_HEIGHT *mod); }

    public RectF SESSION_1() { return Draw.properRect(THE_COL_GOLD *mod, THE_ROW_DETAIL_1 *mod, THE_COL_WIDTH *mod, THE_ROW_HEIGHT *mod); }
    public RectF SESSION_2() { return Draw.properRect(THE_COL_GOLD *mod, THE_ROW_DETAIL_2 *mod, THE_COL_WIDTH *mod, THE_ROW_HEIGHT *mod); }
    public RectF SESSION_3() { return Draw.properRect(THE_COL_GOLD *mod, THE_ROW_DETAIL_3 *mod, THE_COL_WIDTH *mod, THE_ROW_HEIGHT *mod); }

    public RectF DATE_1() { return Draw.properRect(THE_COL_DOWNTIME *mod, THE_ROW_DETAIL_1 *mod, THE_COL_WIDTH *mod, THE_ROW_HEIGHT *mod); }
    public RectF DATE_2() { return Draw.properRect(THE_COL_DOWNTIME *mod, THE_ROW_DETAIL_2 *mod, THE_COL_WIDTH *mod, THE_ROW_HEIGHT *mod); }
    public RectF DATE_3() { return Draw.properRect(THE_COL_DOWNTIME *mod, THE_ROW_DETAIL_3 *mod, THE_COL_WIDTH *mod, THE_ROW_HEIGHT *mod); }

    public RectF DM_1() { return Draw.properRect(THE_COL_RENOWN *mod, THE_ROW_DETAIL_1 *mod, THE_COL_BIG_WIDTH *mod, THE_ROW_HEIGHT *mod); }
    public RectF DM_2() { return Draw.properRect(THE_COL_RENOWN *mod, THE_ROW_DETAIL_2 *mod, THE_COL_BIG_WIDTH *mod, THE_ROW_HEIGHT *mod); }
    public RectF DM_3() { return Draw.properRect(THE_COL_RENOWN *mod, THE_ROW_DETAIL_3 *mod, THE_COL_BIG_WIDTH *mod, THE_ROW_HEIGHT *mod); }

    public Set<String> keys() {
        return mapping().keySet();
    }

    public Collection<RectF> values() {
        return mapping().values();
    }

    private Map<String, RectF> mapping()
    {
        ArrayList<RectF> values = new ArrayList<>();
        TreeMap<String, RectF> map = new TreeMap<String, RectF>();

        map.put("BOUNDS",BOUNDS());

        map.put("CHARACTER_NAME",CHARACTER_NAME());
        map.put("PLAYER_NAME",PLAYER_NAME());

        map.put("CLASSES_AND_LEVELS",CLASSES_AND_LEVELS());
        map.put("PLAYER_DCI",PLAYER_DCI());

        map.put("FACTION",FACTION());
        map.put("SHEET_NUM",SHEET_NUM());


        //
        // STARTING BOUNDS
        //

        map.put("XP_START_1",XP_START_1());
        map.put("XP_START_2",XP_START_2());
        map.put("XP_START_3",XP_START_3());

        map.put("GOLD_START_1",GOLD_START_1());
        map.put("GOLD_START_2",GOLD_START_2());
        map.put("GOLD_START_3",GOLD_START_3());

        map.put("DOWNTIME_START_1",DOWNTIME_START_1());
        map.put("DOWNTIME_START_2",DOWNTIME_START_2());
        map.put("DOWNTIME_START_3",DOWNTIME_START_3());

        map.put("RENOWN_START_1",RENOWN_START_1());
        map.put("RENOWN_START_2",RENOWN_START_2());
        map.put("RENOWN_START_3",RENOWN_START_3());

        map.put("MAGIC_ITEMS_START_1",MAGIC_ITEMS_START_1());
        map.put("MAGIC_ITEMS_START_2",MAGIC_ITEMS_START_2());
        map.put("MAGIC_ITEMS_START_3",MAGIC_ITEMS_START_3());

        //
        // EARNING BOUNDS
        //

        map.put("XP_EARN_1",XP_EARN_1());
        map.put("XP_EARN_2",XP_EARN_2());
        map.put("XP_EARN_3",XP_EARN_3());

        map.put("GOLD_EARN_1",GOLD_EARN_1());
        map.put("GOLD_EARN_2",GOLD_EARN_2());
        map.put("GOLD_EARN_3",GOLD_EARN_3());

        map.put("DOWNTIME_EARN_1",DOWNTIME_EARN_1());
        map.put("DOWNTIME_EARN_2",DOWNTIME_EARN_2());
        map.put("DOWNTIME_EARN_3",DOWNTIME_EARN_3());

        map.put("RENOWN_EARN_1",RENOWN_EARN_1());
        map.put("RENOWN_EARN_2",RENOWN_EARN_2());
        map.put("RENOWN_EARN_3",RENOWN_EARN_3());

        map.put("MAGIC_ITEMS_EARN_1",MAGIC_ITEMS_EARN_1());
        map.put("MAGIC_ITEMS_EARN_2",MAGIC_ITEMS_EARN_2());
        map.put("MAGIC_ITEMS_EARN_3",MAGIC_ITEMS_EARN_3());

        //
        // TOTAL BOUNDS
        //

        map.put("XP_TOTAL_1",XP_TOTAL_1());
        map.put("XP_TOTAL_2",XP_TOTAL_2());
        map.put("XP_TOTAL_3",XP_TOTAL_3());

        map.put("GOLD_TOTAL_1",GOLD_TOTAL_1());
        map.put("GOLD_TOTAL_2",GOLD_TOTAL_2());
        map.put("GOLD_TOTAL_3",GOLD_TOTAL_3());

        map.put("DOWNTIME_TOTAL_1",DOWNTIME_TOTAL_1());
        map.put("DOWNTIME_TOTAL_2",DOWNTIME_TOTAL_2());
        map.put("DOWNTIME_TOTAL_3",DOWNTIME_TOTAL_3());

        map.put("RENOWN_TOTAL_1",RENOWN_TOTAL_1());
        map.put("RENOWN_TOTAL_2",RENOWN_TOTAL_2());
        map.put("RENOWN_TOTAL_3",RENOWN_TOTAL_3());

        map.put("MAGIC_ITEMS_TOTAL_1",MAGIC_ITEMS_TOTAL_1());
        map.put("MAGIC_ITEMS_TOTAL_2",MAGIC_ITEMS_TOTAL_2());
        map.put("MAGIC_ITEMS_TOTAL_3",MAGIC_ITEMS_TOTAL_3());

        //
        // NOTES BOUND
        //

        map.put("NOTES_A_1",NOTES_A_1());
        map.put("NOTES_A_2",NOTES_A_2());
        map.put("NOTES_A_3",NOTES_A_3());

        map.put("NOTES_B_1",NOTES_B_1());
        map.put("NOTES_B_2",NOTES_B_2());
        map.put("NOTES_B_3",NOTES_B_3());

        map.put("NOTES_C_1",NOTES_C_1());
        map.put("NOTES_C_2",NOTES_C_2());
        map.put("NOTES_C_3",NOTES_C_3());

        //
        // DETAILS BOUNDS
        //

        map.put("NAME_1",NAME_1());
        map.put("NAME_2",NAME_2());
        map.put("NAME_3",NAME_3());

        map.put("SESSION_1",SESSION_1());
        map.put("SESSION_2",SESSION_2());
        map.put("SESSION_3",SESSION_3());

        map.put("DATE_1",DATE_1());
        map.put("DATE_2",DATE_2());
        map.put("DATE_3",DATE_3());

        map.put("DM_1",DM_1());
        map.put("DM_2",DM_2());
        map.put("DM_3",DM_3());

        return map;
    }
}



