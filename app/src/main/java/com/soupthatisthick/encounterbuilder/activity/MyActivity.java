package com.soupthatisthick.encounterbuilder.activity;

import android.annotation.SuppressLint;
import android.graphics.PointF;
import android.util.Log;
import android.view.MotionEvent;

import com.soupthatisthick.util.activity.AppActivity;

/**
 * Created by Owner on 1/17/2017.
 * Copyright of Stuart Marr Erskine, all rights reserved.
 */

@SuppressLint("Registered")
public class MyActivity extends AppActivity {

    public static final String TAG = MyActivity.class.getSimpleName();

    protected static String actionString(int action)
    {
        switch(action) {
            case MotionEvent.ACTION_DOWN:
                return "ACTION_DOWN";
            case MotionEvent.ACTION_UP:
                return "ACTION_UP";
            case MotionEvent.ACTION_POINTER_DOWN:
                return "ACTION_POINTER_DOWN";
            case MotionEvent.ACTION_POINTER_UP:
                return "ACTION_POINTER_UP";
            case MotionEvent.ACTION_MOVE:
                return "ACTION_MOVE";
            case MotionEvent.ACTION_HOVER_MOVE:
                return "ACTION_HOVER_MOVE";
            case MotionEvent.ACTION_BUTTON_PRESS:
                return "ACTION_BUTTON_PRESS";
            case MotionEvent.ACTION_BUTTON_RELEASE:
                return "ACTION_BUTTON_RELEASE";
            case MotionEvent.ACTION_CANCEL:
                return "ACTION_CANCEL";
            case MotionEvent.ACTION_HOVER_ENTER:
                return "ACTION_HOVER_ENTER";
            case MotionEvent.ACTION_HOVER_EXIT:
                return "ACTION_HOVER_EXIT";
            case MotionEvent.ACTION_MASK:
                return "ACTION_MASK";
            case MotionEvent.ACTION_OUTSIDE:
                return "ACTION_OUTSIDE";
            case MotionEvent.ACTION_POINTER_INDEX_MASK:
                return "ACTION_POINTER_INDEX_MASK";
            case MotionEvent.ACTION_SCROLL:
                return "ACTION_SCROLL";
            default:
                return "ACTION_DEFAULT (" + action + ")";
        }
    }

    protected void logPossibleActions()
    {
        Log.d(TAG, "ACTION_DOWN                = " + MotionEvent.ACTION_DOWN);
        Log.d(TAG, "ACTION_UP                  = " + MotionEvent.ACTION_UP);
        Log.d(TAG, "ACTION_MOVE                = " + MotionEvent.ACTION_MOVE);
        Log.d(TAG, "ACTION_CANCEL              = " + MotionEvent.ACTION_CANCEL);
        Log.d(TAG, "ACTION_OUTSIDE             = " + MotionEvent.ACTION_OUTSIDE);
        Log.d(TAG, "ACTION_POINTER_DOWN        = " + MotionEvent.ACTION_POINTER_DOWN);
        Log.d(TAG, "ACTION_POINTER_UP          = " + MotionEvent.ACTION_POINTER_UP);
        Log.d(TAG, "ACTION_HOVER_MOVE          = " + MotionEvent.ACTION_HOVER_MOVE);
        Log.d(TAG, "ACTION_SCROLL              = " + MotionEvent.ACTION_SCROLL);
        Log.d(TAG, "ACTION_POINTER_INDEX_SHIFT = " + MotionEvent.ACTION_POINTER_INDEX_SHIFT);
        Log.d(TAG, "ACTION_HOVER_ENTER         = " + MotionEvent.ACTION_HOVER_ENTER);
        Log.d(TAG, "ACTION_HOVER_EXIT          = " + MotionEvent.ACTION_HOVER_EXIT);
        Log.d(TAG, "ACTION_BUTTON_PRESS        = " + MotionEvent.ACTION_BUTTON_PRESS);
        Log.d(TAG, "ACTION_BUTTON_RELEASE      = " + MotionEvent.ACTION_BUTTON_RELEASE);
        Log.d(TAG, "ACTION_MASK                = " + MotionEvent.ACTION_MASK);
        Log.d(TAG, "ACTION_POINTER_INDEX_MASK  = " + MotionEvent.ACTION_POINTER_INDEX_MASK);
    }
    protected static final PointF getPosition(MotionEvent motionEvent)
    {
        return new PointF(motionEvent.getX(), motionEvent.getY());
    }

    protected static PointF centerOfPointers(MotionEvent motionEvent)
    {
        PointF sum = new PointF(0f, 0f);
        if (motionEvent.getPointerCount()<1)
        {
            return new PointF(motionEvent.getX(), motionEvent.getY());
        } else {

            int numPointers = motionEvent.getPointerCount();
            for(int i=0; i<numPointers; i++)
            {

                MotionEvent.PointerCoords coords = new MotionEvent.PointerCoords();
                motionEvent.getPointerCoords(i, coords);
                sum.x += coords.x;
                sum.y += coords.y;
            }
            sum.x /= numPointers;
            sum.y /= numPointers;
            return sum;
        }
    }
}
