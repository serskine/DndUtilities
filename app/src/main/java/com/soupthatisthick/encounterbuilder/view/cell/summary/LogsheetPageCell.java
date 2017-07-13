package com.soupthatisthick.encounterbuilder.view.cell.summary;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.soupthatisthick.encounterbuilder.printing.model.LogsheetPage;
import com.soupthatisthick.encounterbuilder.model.lookup.Pc;
import com.soupthatisthick.encounterbuilder.model.lookup.Session;
import com.soupthatisthick.encounterbuilder.printing.PageBounds;
import com.soupthatisthick.encounterbuilder.util.translater.DateTranslater;
import com.soupthatisthick.encounterbuilder.printing.bounds.LogSheetPageBounds;
import com.soupthatisthick.encounterbuilder.view.cell.PageCell;
import com.soupthatisthick.util.Logger;
import com.soupthatisthick.encounterbuilder.util.view.Draw;

import soupthatisthick.encounterapp.R;

/**
 * Created by Owner on 3/4/2017.
 * Copyright of Stuart Marr Erskine, all rights reserved.
 */

public class LogsheetPageCell extends PageCell<LogsheetPage> {

    private ImageView theImage;

    public LogsheetPageCell(LayoutInflater inflater, View convertView, ViewGroup parent) {
        super(inflater, convertView, parent);
    }

    /**
     * This will paint the fields onto the provided bitmap.
     *
     * @param canvas is the canvas we use to perform drawing on the bitmap.
     * @param page
     * @param bounds will provide all the boundaries of the fields/
     */
    @Override
    protected void paintFields(Canvas canvas, LogsheetPage page, PageBounds bounds) {
        Paint textPaint = new Paint();
        Paint fieldPaint = new Paint();

        textPaint.setColor(Color.BLACK);
        textPaint.setStyle(Paint.Style.FILL_AND_STROKE);
        textPaint.setAlpha(255);

        fieldPaint.setColor(Color.GREEN);
        fieldPaint.setStyle(Paint.Style.STROKE);
        fieldPaint.setAlpha(255);

        try {
            paintFieldBounds(canvas, bounds, fieldPaint);
            paintLogsheet(canvas, page, (LogSheetPageBounds) bounds, textPaint);
        } catch (Exception e) {
            Logger.error("Failed to paint expected logsheet bounds onto the canvas.", e);
        }

    }

    /**
     * @return the integer resource id of the background image we want to display before painting the field values.
     */
    @Override
    protected int getBackgroundResource() {
        return R.drawable.logsheet_small;
    }

    /**
     * @return a new {@link PageBounds} object that will provide all the fields, and boundaries for those fields on the page
     */
    @Override
    public PageBounds createPageBounds() {
        return new LogSheetPageBounds();
    }

    /**
     * Used to paint the logsheet page onto the canvas that links to the desired bitmap
     * @param canvas
     * @param page
     */
    protected static final  void paintLogsheet(
            @NonNull Canvas canvas,
            @NonNull LogsheetPage page,
            @NonNull LogSheetPageBounds bounds,
            @NonNull Paint paint
    )
    {
        Logger.info("Painting logsheet onto a canvas.");
        LogsheetPage.log(page);
        paintHeader(canvas, page.character, page.sheetNumber, bounds, paint);
        paintSession1(canvas, page.sessions[0], (page.sheetNumber-1)*3 + 1, bounds, paint);
        paintSession2(canvas, page.sessions[1], (page.sheetNumber-1)*3 + 2, bounds, paint);
        paintSession3(canvas, page.sessions[2], (page.sheetNumber-1)*3 + 3, bounds, paint);
    }

    protected static final  void paintHeader(
        @NonNull Canvas canvas,
        @NonNull Pc pc,
        int sheetNumber,
        @NonNull LogSheetPageBounds bounds,
        @NonNull Paint paint
    )
    {
        Draw.write(canvas, bounds.PLAYER_NAME(),        paint, pc.getPlayerName());
        Draw.write(canvas, bounds.PLAYER_DCI(),         paint, pc.getPlayerDci());
        Draw.write(canvas, bounds.CHARACTER_NAME(),     paint, pc.getCharacterName());
        Draw.write(canvas, bounds.CLASSES_AND_LEVELS(), paint, pc.getClassAndLevels());
        Draw.write(canvas, bounds.FACTION(),            paint, pc.getFaction());
        Draw.write(canvas, bounds.SHEET_NUM(),          paint, Integer.toString(sheetNumber));

    }


    protected static final  void paintSession1(
        @NonNull Canvas canvas,
        @Nullable Session session,
        int sessionNumber,
        @NonNull LogSheetPageBounds bounds,
        @NonNull Paint paint
    )
    {
        if (session != null)
        {

            Logger.info("Painting session 1 - " + session.toString());

            String theAdventure = session.getAdventure();
            String theDm = session.getDmName() + " (" + session.getDmDci() + ")";

            Draw.write(canvas, bounds.NAME_1(), paint, theAdventure);
            Draw.write(canvas, bounds.DM_1(), paint, theDm);

            Draw.write(canvas, bounds.SESSION_1(), paint, "" + sessionNumber);

            Draw.write(canvas, bounds.DATE_1(), paint, DateTranslater.translate(session.getDate()));
            Draw.write(canvas, bounds.XP_EARN_1(), paint, "" + session.getXp() + " xp");
            Draw.write(canvas, bounds.GOLD_EARN_1(), paint, "" + session.getGold() + " gp");
            Draw.write(canvas, bounds.DOWNTIME_EARN_1(), paint, "" + session.getDowntime() + " days");
            Draw.write(canvas, bounds.RENOWN_EARN_1(), paint, "" + session.getRenown() + " pts");
            Draw.write(canvas, bounds.MAGIC_ITEMS_EARN_1(), paint, "" + session.getMagicItems() + " items");

            Draw.writeOverFields(
                    canvas,
                    paint,
                    session.getNotes(),
                    new RectF[]{
                            bounds.NOTES_A_1(),
                            bounds.NOTES_B_1(),
                            bounds.NOTES_C_1()
                    }
            );

        }
    }

    protected static final  void paintSession2(
            @NonNull Canvas canvas,
            @Nullable Session session,
            int sessionNumber,
            @NonNull LogSheetPageBounds bounds,
            @NonNull Paint paint)
    {
        if(session != null)
        {
            Logger.info("Painting session 2 - " + session.toString());

            String theAdventure = session.getAdventure();
            String theDm = session.getDmName() + " (" + session.getDmDci() + ")";

            Draw.write(canvas, bounds.NAME_2(), paint, theAdventure);
            Draw.write(canvas, bounds.DM_2(), paint, theDm);
            Draw.write(canvas, bounds.DATE_2(), paint, DateTranslater.translate(session.getDate()));
            Draw.write(canvas, bounds.XP_EARN_2(), paint, "" + session.getXp() + " xp");
            Draw.write(canvas, bounds.GOLD_EARN_2(), paint, "" + session.getGold() + " gp");
            Draw.write(canvas, bounds.DOWNTIME_EARN_2(), paint, "" + session.getDowntime() + " days");
            Draw.write(canvas, bounds.RENOWN_EARN_2(), paint, "" + session.getRenown() + " pts");
            Draw.write(canvas, bounds.MAGIC_ITEMS_EARN_2(), paint, "" + session.getMagicItems() + " items");

            Draw.writeOverFields(
                    canvas,
                    paint,
                    session.getNotes(),
                    new RectF[]{
                            bounds.NOTES_A_2(),
                            bounds.NOTES_B_2(),
                            bounds.NOTES_C_2()
                    }
            );
        }
    }

    protected static final  void paintSession3(
            @NonNull Canvas canvas,
            @Nullable Session session,
            int sessionNumber,
            @NonNull LogSheetPageBounds bounds,
            @NonNull Paint paint)
    {
        if (session!=null)
        {
            Logger.info("Painting session 3 - " + session.toString());

            String theAdventure = session.getAdventure();
            String theDm = session.getDmName() + " (" + session.getDmDci() + ")";

            Draw.write(canvas, bounds.NAME_3(), paint, theAdventure);
            Draw.write(canvas, bounds.DM_3(), paint, theDm);
            Draw.write(canvas, bounds.DATE_3(), paint, DateTranslater.translate(session.getDate()));
            Draw.write(canvas, bounds.XP_EARN_3(), paint, "" + session.getXp() + " xp");
            Draw.write(canvas, bounds.GOLD_EARN_3(), paint, "" + session.getGold() + " gp");
            Draw.write(canvas, bounds.DOWNTIME_EARN_3(), paint, "" + session.getDowntime() + " days");
            Draw.write(canvas, bounds.RENOWN_EARN_3(), paint, "" + session.getRenown() + " pts");
            Draw.write(canvas, bounds.MAGIC_ITEMS_EARN_3(), paint, "" + session.getMagicItems() + " items");

            Draw.writeOverFields(
                canvas,
                paint,
                session.getNotes(),
                new RectF[]{
                    bounds.NOTES_A_3(),
                    bounds.NOTES_B_3(),
                    bounds.NOTES_C_3()
                }
            );
        }

    }
}
