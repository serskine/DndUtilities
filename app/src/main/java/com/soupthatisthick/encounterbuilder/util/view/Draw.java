package com.soupthatisthick.encounterbuilder.util.view;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Region;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.soupthatisthick.util.Logger;
import com.soupthatisthick.encounterbuilder.util.Text;

/**
 * Created by Owner on 3/3/2017.
 * Copyright of Stuart Marr Erskine, all rights reserved.
 */

public class Draw {
    private static final String NULL_TEXT = "null";

    public static final RectF rect(float x, float y, float down, float right)
    {
        return new RectF(x,y,x+down,y+right);
    }
    public static final Rect rect(int x, int y, int down, int right)
    {
        return new Rect(x,y,x+down,y+right);
    }

    public static final RectF properRect(float x, float y, float right, float up)
    {
        return rect(x, y-up, right, up);
    }
    public static final Rect properRect(int x, int y, int right, int up)
    {
        return rect(x, y-up, right, up);
    }
    public static final Rect outerRect(Rect source, int padding)
    {
        return new Rect(source.left-padding, source.top-padding, source.right+padding, source.bottom+padding);
    }
    public static final int minDimension(Rect source)
    {
        return Math.min(source.width(), source.height());
    }
    public static final Rect innerRect(Rect source, int padding)
    {
        padding = Math.min(padding, minDimension(source)/2);
        return new Rect(source.left+padding, source.top+padding, source.right-padding, source.bottom-padding);
    }

    /**
     * This will write the specified text into the given field. Text will be sized to the height of the field.
     * It will be truncated at the end.
     * @param canvas
     * @param bounds
     * @param paint
     * @param text
     */
    public static final void write(@NonNull Canvas canvas, @NonNull RectF bounds, @NonNull Paint paint, @Nullable String text)
    {
        setTextSizeForHeight(bounds.height(), paint);
        float newBottom = Math.abs(paint.getFontMetrics().bottom);

        String output = (text==null) ? NULL_TEXT : text;
        Logger.info(" - Painting text " + Text.toString(output) + " onto " + bounds.toString());

        Rect oldClip = canvas.getClipBounds();
        canvas.clipRect(bounds, Region.Op.REPLACE);
        canvas.drawText(output, bounds.left, bounds.bottom-newBottom, paint);
        canvas.clipRect(oldClip, Region.Op.REPLACE);
    }

    /**
     * This will write the specified text into the given field. Text will be sized to the height of the field.
     * It will be truncated at the end.
     * @param canvas
     * @param bounds
     * @param paint
     * @param text
     */
    public static final void write(@NonNull Canvas canvas, @NonNull Rect bounds, @NonNull Paint paint, @Nullable String text)
    {
        write(
            canvas,
            new RectF(
                (float) bounds.left,
                (float) bounds.top,
                (float) bounds.right,
                (float) bounds.bottom
            ),
            paint,
            text
        );

    }

    /**
     * This will change the text numFaces on the paint to be appropriate for the given field height.
     * @param height
     * @param paint
     */
    private static final void setTextSizeForHeight(float height, Paint paint)
    {
        Paint.FontMetrics metrics = paint.getFontMetrics();
        float total = Math.abs(metrics.top) + Math.abs(metrics.bottom);
        float recommended = Math.abs(metrics.ascent) + Math.abs(metrics.descent);
        float newSize = height * recommended / total;

        paint.setTextSize(newSize);
    }

    /**
     * This will write the given text over the specified fields ensuring the text numFaces fills the
     * height of each specified bounds. It will stop when all text has been written or we have
     * run out of fields to place text into. The text will be truncated on the last field if it does
     * not fit into all of the fields.
     * @param canvas
     * @param paint
     * @param text
     * @param bounds
     */
    public static final void writeOverFields(@NonNull Canvas canvas, @NonNull Paint paint, @Nullable String text, @NonNull RectF bounds[])
    {
        if (text!=null) {
            boolean done;
            int boundsIdx = 0;
            int charIdx = 0;

            String fieldOutput = "";

            done = ((charIdx >= text.length()) || (boundsIdx >= bounds.length));
            while (!done) {
                RectF fieldBounds = bounds[boundsIdx];
                paint.setTextSize(fieldBounds.height());
                char c = text.charAt(charIdx);
                float width = paint.measureText(fieldOutput);

                if (width > fieldBounds.width()) {
                    String output = fieldOutput.substring(0, fieldOutput.length() - 1).toString();
                    write(
                            canvas,
                            fieldBounds,
                            paint,
                            output
                    );
                    fieldOutput = "" + fieldOutput.charAt(fieldOutput.length() - 1);
                    boundsIdx++;
                }

                // Add the character to the output for the field
                fieldOutput += c;
                charIdx++;

                done = ((charIdx >= text.length()) || (boundsIdx >= bounds.length));

                // Write out the remaining characters yet to be drawn into the current field
                if ((boundsIdx < bounds.length) && (fieldOutput.length() > 0)) {
                    fieldBounds = bounds[boundsIdx];
                    paint.setTextSize(fieldBounds.height());
                    write(
                            canvas,
                            fieldBounds,
                            paint,
                            fieldOutput.toString()
                    );
                }
            }
        }
    }

}
