package com.soupthatisthick.encounterbuilder.view.controller;

import android.view.MotionEvent;
import android.view.View;

/**
 * Created by Owner on 1/5/2017.
 * Copyright of Stuart Marr Erskine, all rights reserved.
 */

public class LoggingTouchController extends AbstractTouchDetector {

    public LoggingTouchController(String tag) {
        super(tag);
    }

    protected void onDown(View v, MotionEvent motionEvent) {
        logger.info("onDown(" + motionEvent + ")");
    }

    protected void onUp(View v, MotionEvent motionEvent) {
        logger.info("onUp(" + motionEvent + ")");
    }

    protected void onDefault(View v, MotionEvent motionEvent) {
        logger.info("onDefault(" + motionEvent.toString() + ")");
    }

    protected void onScroll(View v, MotionEvent motionEvent) {
        logger.info("onScroll(" + motionEvent.toString() + ")");
    }

    protected void onPointerIndexMask(View v, MotionEvent motionEvent) {
        logger.info("onPointerIndexMask(" + motionEvent.toString() + ")");
    }

    protected void onOutside(View v, MotionEvent motionEvent) {
        logger.info("onOutside(" + motionEvent.toString() + ")");
    }

    protected void onMask(View v, MotionEvent motionEvent) {
        logger.info("onMask(" + motionEvent.toString() + ")");
    }

    protected void onHoverExit(View v, MotionEvent motionEvent) {
        logger.info("onHoverExit(" + motionEvent.toString() + ")");
    }

    protected void onHoverEnter(View v, MotionEvent motionEvent) {
        logger.info("onHoverExit(" + motionEvent.toString() + ")");
    }

    protected void onCancel(View v, MotionEvent motionEvent) {
        logger.info("onCancel(" + motionEvent.toString() + ")");
    }

    protected void onPointerDown(View v, MotionEvent motionEvent)
    {
        logger.info("onPointerDown(" + motionEvent.toString() + ")");
    }

    protected void onPointerUp(View v, MotionEvent motionEvent)
    {
        logger.info("onPointerUp(" + motionEvent.toString() + ")");
    }

    protected void onMove(View v, MotionEvent motionEvent)
    {
        logger.info("onPointerMove(" + motionEvent.toString() + ")");
    }

    protected void onHoverMove(View v, MotionEvent motionEvent)
    {
        logger.info("onHoverMove(" + motionEvent.toString() + ")");
    }

    protected void onButtonPress(View v, MotionEvent motionEvent)
    {
        logger.info("onButtonPress(" + motionEvent + ")");
    }

    protected void onButtonRelease(View v, MotionEvent motionEvent)
    {
        logger.info("onButtonRelease(" + motionEvent + ")");
    }

    @Override
    protected void onBefore(View v, MotionEvent motionEvent) {
        logger.info("onBefore(" + motionEvent + ")");
    }

    @Override
    protected void onAfter(View v, MotionEvent motionEvent) {
        logger.info("onAfter(" + motionEvent + ")");
    }


}
