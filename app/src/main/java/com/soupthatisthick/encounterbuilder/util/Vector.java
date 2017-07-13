package com.soupthatisthick.encounterbuilder.util;

import android.graphics.PointF;
import android.graphics.RectF;

/**
 * Created by Owner on 1/6/2017.
 * Copyright of Stuart Marr Erskine, all rights reserved.
 */

public class Vector {
    public static boolean isEqual(PointF vector1, PointF vector2)
    {
        return (vector1.equals(vector2.x, vector2.y));
    }

    public static PointF scale(PointF vector, float scalar)
    {
        return new PointF(vector.x*scalar, vector.y*scalar);
    }

    public static PointF diff(PointF dest, PointF src)
    {
        return sum(dest, inverse(src));
    }


    public static PointF sum(PointF... vectors)
    {
        float x = 0f;
        float y = 0f;
        for(PointF v : vectors)
        {
            x += v.x;
            y += v.y;
        }
        return new PointF(x,y);
    }

    public static float length(PointF vector)
    {
        return (float) Math.sqrt(vector.x*vector.x + vector.y*vector.y);
    }

    public static PointF inverse(PointF vector)
    {
        return new PointF(-vector.x, -vector.y);
    }

    public static float innerProduct(PointF... vectors)
    {
        float x = 1f;
        float y = 1F;
        for(PointF v : vectors)
        {
            x *= v.x;
            y *= v.y;
        }
        return x + y;
    }

    public static RectF translate(RectF rect, PointF vector)
    {
        return new RectF(
            rect.left+vector.x,
            rect.top + vector.y,
            rect.right + vector.x,
            rect.bottom + vector.y
        );
    }
}
