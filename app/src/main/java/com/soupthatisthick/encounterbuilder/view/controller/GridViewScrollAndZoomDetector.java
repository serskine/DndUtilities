package com.soupthatisthick.encounterbuilder.view.controller;

import android.graphics.PointF;
import android.support.annotation.NonNull;
import android.view.MotionEvent;
import android.view.View;

import com.soupthatisthick.encounterbuilder.util.Vector;
import com.soupthatisthick.encounterbuilder.util.announcer.Announcer;
import com.soupthatisthick.encounterbuilder.view.GridView;

import java.util.EventListener;

/**
 * Created by Owner on 1/4/2017.
 * Copyright of Stuart Marr Erskine, all rights reserved.
 */
public class GridViewScrollAndZoomDetector extends DefaultTouchDetector
{
    public final Announcer<GridViewScrollAndZoomDetector.Listener> listeners = Announcer.to(GridViewScrollAndZoomDetector.Listener.class);

    public interface Listener extends EventListener {
        void onZoom(
            MotionEvent zoomDownEvent,
            MotionEvent currentMotionEvent,
            PointF zoomDownSize,
            PointF zoomDownVector,
            PointF zoomCurrentVector
        );
        void onScroll(
            MotionEvent origonalMotionEvent,
            MotionEvent currentMotionEvent,
            PointF scrollDownOnCanvas,
            PointF origonalCanvasOrigon
        );
        void onModeChanged(Mode prevMode, Mode currentMode);

    void onDown(MotionEvent currentMotionEvent, int pointerIndex, PointF gridPoint);
        void onUp(MotionEvent currentMotionEvent, int pointerIndex, PointF gridPoint);

    }

    public enum Mode
    {
        IDLING,
        SCROLLING,
        ZOOMING
    }

    private final GridView view;

    private PointF origonDownOnCanvas = new PointF(0f, 0f);
    private PointF scrollDownOnCanvas = new PointF(0f, 0f);
    private MotionEvent scrollDownEvent = null;

    private PointF  zoomDownVector = new PointF(0f, 0f);
    private PointF  zoomDownSize = new PointF(1f, 1f);
    private MotionEvent zoomDownEvent = null;

    MotionEvent prevMotionEvent = null;
    Mode prevMode = Mode.IDLING;
    Mode currentMode = Mode.IDLING;

    public GridViewScrollAndZoomDetector(@NonNull GridView view)
    {
        super(GridViewScrollAndZoomDetector.class.getSimpleName());
        this.view = view;
        view.setOnTouchListener(this);
    }

    private static Mode getMode(MotionEvent motionEvent)
    {
        if (motionEvent==null)
        {
            return Mode.IDLING;
        } else {
            return getMode(motionEvent.getAction(), motionEvent.getPointerCount());
        }
    }

    @Override
    protected void onBefore(View v, MotionEvent motionEvent)
    {
        transitionTo(getMode(motionEvent), motionEvent);

        logger.info(((prevMode!=currentMode) ? "CHANGE : " : "UPDATE : ") + prevMode + " => " + currentMode + " : " + actionString(motionEvent.getAction()));
    }


    @Override
    protected void onAfter(View v, MotionEvent motionEvent)
    {
        prevMotionEvent = motionEvent;
        prevMode = currentMode;
    }

    @Override
    protected void onDown(View v, MotionEvent motionEvent)
    {
        PointF canvasLocation = getPosition(motionEvent);
        PointF gridPoint = view.toGrid(canvasLocation);
        listeners.announce().onDown(motionEvent, 0, gridPoint);
    }

    @Override
    protected void onUp(View v, MotionEvent motionEvent)
    {
        PointF canvasLocation = getPosition(motionEvent);
        PointF gridPoint = view.toGrid(canvasLocation);
        listeners.announce().onUp(motionEvent, 0, gridPoint);
    }

    @Override
    protected void onPointerDown(View v, MotionEvent motionEvent)
    {
        int index = motionEvent.getActionIndex();
        MotionEvent.PointerCoords coords = new MotionEvent.PointerCoords();
        motionEvent.getPointerCoords(index, coords);
        PointF canvasDown = new PointF(coords.x, coords.y);
        PointF gridPoint = view.toGrid(canvasDown);
        listeners.announce().onDown(motionEvent, index, gridPoint);
    }

    @Override
    protected void onPointerUp(View v, MotionEvent motionEvent)
    {
        int index = motionEvent.getActionIndex();
        MotionEvent.PointerCoords coords = new MotionEvent.PointerCoords();
        motionEvent.getPointerCoords(index, coords);
        PointF canvasDown = new PointF(coords.x, coords.y);
        PointF gridPoint = view.toGrid(canvasDown);
        listeners.announce().onUp(motionEvent, index, gridPoint);
    }

    private void startZoom(MotionEvent motionEvent)
    {
        zoomDownVector = getZoomVector(motionEvent);
        zoomDownSize = view.getTileSize();
        zoomDownEvent = motionEvent;
    }


    private void onZoom(MotionEvent motionEvent)
    {
        PointF currentZoomVector = getZoomVector(motionEvent);
        if (currentZoomVector!=null) {
            listeners.announce().onZoom(zoomDownEvent, motionEvent, zoomDownSize, zoomDownVector, currentZoomVector);
        }
    }

    private void startScroll(MotionEvent motionEvent)
    {
        scrollDownOnCanvas = getPosition(motionEvent);
        origonDownOnCanvas = view.getOrigonCanvasLocation();
        scrollDownEvent = motionEvent;
    }

    private void onScroll(MotionEvent motionEvent)
    {
        listeners.announce().onScroll(scrollDownEvent, motionEvent, scrollDownOnCanvas, origonDownOnCanvas);
    }

    private void idle()
    {

    }

    private static PointF getZoomVector(MotionEvent motionEvent)
    {

        if (motionEvent.getPointerCount()<2)
        {
            return null;
        } else {
            MotionEvent.PointerCoords c0 = new MotionEvent.PointerCoords();
            MotionEvent.PointerCoords c1 = new MotionEvent.PointerCoords();

            motionEvent.getPointerCoords(0, c0);
            motionEvent.getPointerCoords(1, c1);

            PointF p0 = new PointF(c0.x, c0.y);
            PointF p1 = new PointF(c1.x, c1.y);

            return Vector.diff(p1, p0);
        }
    }

    private void transitionTo(Mode mode, MotionEvent motionEvent)
    {
        Mode prevMode = currentMode;
        currentMode = mode;

        listeners.announce().onModeChanged(prevMode, currentMode);

        switch(mode)
        {
            case IDLING:
                idle();
                break;
            case ZOOMING:
                if (prevMode != currentMode)
                {
                    startZoom(motionEvent);
                } else {
                    onZoom(motionEvent);
                }
                break;
            case SCROLLING:
                if (prevMode != currentMode)
                {
                    startScroll(motionEvent);
                } else {
                    onScroll(motionEvent);
                }
                break;
            default:
                break;
        }
    }

    private static Mode getMode(int action, int currentPointerCount)
    {

        switch(action)
        {
            case MotionEvent.ACTION_BUTTON_PRESS:
            case MotionEvent.ACTION_DOWN:
            case MotionEvent.ACTION_POINTER_DOWN:
                if (currentPointerCount>1) {
                    return Mode.ZOOMING;
                } else if (currentPointerCount==1) {
                    return Mode.SCROLLING;
                } else {
                    return Mode.IDLING;
                }

            case MotionEvent.ACTION_BUTTON_RELEASE:
            case MotionEvent.ACTION_CANCEL:
            case MotionEvent.ACTION_HOVER_EXIT:
            case MotionEvent.ACTION_OUTSIDE:
            case MotionEvent.ACTION_POINTER_UP:
            case MotionEvent.ACTION_UP:
                return Mode.IDLING;
            case MotionEvent.ACTION_HOVER_MOVE:
            case MotionEvent.ACTION_MOVE:
            case MotionEvent.ACTION_SCROLL:
                if (currentPointerCount>1) {
                    return Mode.ZOOMING;
                } else if (currentPointerCount==1) {
                    return Mode.SCROLLING;
                } else {
                    return Mode.IDLING;
                }
            default:
                return Mode.IDLING;
        }

    }


}
