package com.soupthatisthick.encounterbuilder.view.controller;

import android.graphics.PointF;
import android.view.MotionEvent;
import android.view.View;

import java.util.logging.Logger;

/**
 * Created by Owner on 1/5/2017.
 * Copyright of Stuart Marr Erskine, all rights reserved.
 */

public abstract class AbstractTouchDetector implements View.OnTouchListener
{

    protected final Logger logger;
    
    public AbstractTouchDetector(String tag)
    {
        logger = Logger.getLogger(tag);
    }

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
        logger.info("ACTION_DOWN                = " + MotionEvent.ACTION_DOWN);
        logger.info("ACTION_UP                  = " + MotionEvent.ACTION_UP);
        logger.info("ACTION_MOVE                = " + MotionEvent.ACTION_MOVE);
        logger.info("ACTION_CANCEL              = " + MotionEvent.ACTION_CANCEL);
        logger.info("ACTION_OUTSIDE             = " + MotionEvent.ACTION_OUTSIDE);
        logger.info("ACTION_POINTER_DOWN        = " + MotionEvent.ACTION_POINTER_DOWN);
        logger.info("ACTION_POINTER_UP          = " + MotionEvent.ACTION_POINTER_UP);
        logger.info("ACTION_HOVER_MOVE          = " + MotionEvent.ACTION_HOVER_MOVE);
        logger.info("ACTION_SCROLL              = " + MotionEvent.ACTION_SCROLL);
        logger.info("ACTION_POINTER_INDEX_SHIFT = " + MotionEvent.ACTION_POINTER_INDEX_SHIFT);
        logger.info("ACTION_HOVER_ENTER         = " + MotionEvent.ACTION_HOVER_ENTER);
        logger.info("ACTION_HOVER_EXIT          = " + MotionEvent.ACTION_HOVER_EXIT);
        logger.info("ACTION_BUTTON_PRESS        = " + MotionEvent.ACTION_BUTTON_PRESS);
        logger.info("ACTION_BUTTON_RELEASE      = " + MotionEvent.ACTION_BUTTON_RELEASE);
        logger.info("ACTION_MASK                = " + MotionEvent.ACTION_MASK);
        logger.info("ACTION_POINTER_INDEX_MASK  = " + MotionEvent.ACTION_POINTER_INDEX_MASK);
    }

    protected abstract void onDown(View v, MotionEvent motionEvent);

    protected abstract void onUp(View v, MotionEvent motionEvent);

    protected abstract void onDefault(View v, MotionEvent motionEvent);

    protected abstract void onScroll(View v, MotionEvent motionEvent);

    protected abstract void onPointerIndexMask(View v, MotionEvent motionEvent);

    protected abstract void onOutside(View v, MotionEvent motionEvent);

    protected abstract void onMask(View v, MotionEvent motionEvent);

    protected abstract void onHoverExit(View v, MotionEvent motionEvent);

    protected abstract void onHoverEnter(View v, MotionEvent motionEvent);

    protected abstract void onCancel(View v, MotionEvent motionEvent);

    protected abstract void onPointerDown(View v, MotionEvent motionEvent);

    protected abstract void onPointerUp(View v, MotionEvent motionEvent);

    protected abstract void onMove(View v, MotionEvent motionEvent);

    protected abstract void onHoverMove(View v, MotionEvent motionEvent);

    protected abstract void onButtonPress(View v, MotionEvent motionEvent);

    protected abstract void onButtonRelease(View v, MotionEvent motionEvent);


    /**
     * Execution of this method occurs before any other event
     * @param v
     * @param motionEvent
     */
    protected abstract void onBefore(View v, MotionEvent motionEvent);

    /**
     * Execution of this method occurs after any other event
     * @param v
     * @param motionEvent
     */
    protected abstract void onAfter(View v, MotionEvent motionEvent);



    @Override
    public final boolean onTouch(View v, MotionEvent motionEvent) {
        int action = motionEvent.getAction();

        onBefore(v, motionEvent);

        switch(action)
        {
            case MotionEvent.ACTION_DOWN:
                onDown(v, motionEvent);
                break;
            case MotionEvent.ACTION_UP:
                onUp(v, motionEvent);
                break;
            case MotionEvent.ACTION_POINTER_DOWN:
                onPointerDown(v, motionEvent);
                break;
            case MotionEvent.ACTION_POINTER_UP:
                onPointerUp(v, motionEvent);
                break;
            case MotionEvent.ACTION_MOVE:
                onMove(v, motionEvent);
                break;
            case MotionEvent.ACTION_HOVER_MOVE:
                onHoverMove(v, motionEvent);
                break;
            case MotionEvent.ACTION_BUTTON_PRESS:
                onButtonPress(v, motionEvent);
                break;
            case MotionEvent.ACTION_BUTTON_RELEASE:
                onButtonRelease(v, motionEvent);
                break;
            case MotionEvent.ACTION_CANCEL:
                onCancel(v, motionEvent);
                break;
            case MotionEvent.ACTION_HOVER_ENTER:
                onHoverEnter(v, motionEvent);
                break;
            case MotionEvent.ACTION_HOVER_EXIT:
                onHoverExit(v, motionEvent);
                break;
            case MotionEvent.ACTION_MASK:
                onMask(v, motionEvent);
                break;
            case MotionEvent.ACTION_OUTSIDE:
                onOutside(v, motionEvent);
                break;
            case MotionEvent.ACTION_POINTER_INDEX_MASK:
                onPointerIndexMask(v, motionEvent);
                break;
            case MotionEvent.ACTION_SCROLL:
                onScroll(v, motionEvent);
                break;
            default:
                onDefault(v, motionEvent);
                break;


        }

        onAfter(v, motionEvent);

        return true;
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
