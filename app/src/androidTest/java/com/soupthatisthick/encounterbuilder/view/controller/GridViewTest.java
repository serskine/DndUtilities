package com.soupthatisthick.encounterbuilder.view.controller;

import android.content.Context;
import android.graphics.PointF;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import com.soupthatisthick.encounterbuilder.view.GridView;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static junit.framework.Assert.assertEquals;
import static junit.framework.TestCase.assertTrue;

/**
 * Created by Owner on 1/6/2017.
 * Copyright of Stuart Marr Erskine, all rights reserved.
 */

@RunWith(AndroidJUnit4.class)
public class GridViewTest extends AbstractTest {

    private GridView gridView;

    private static final int WIDTH = 1000;
    private static final int HEIGHT = 1000;

    private static PointF[] points = new PointF[] {
        new PointF(-1f, -1f),
        new PointF(-1f, 0f),
        new PointF(-1f, 1f),
        new PointF(0f, -1f),
        new PointF(0f, 0f),
        new PointF(0f, 1f),
        new PointF(1f, -1f),
        new PointF(1f, 0f),
        new PointF(1f, 1f)
    };

    @Before
    public void setUp()
    {
        Context context = InstrumentationRegistry.getContext();
        gridView = new GridView(context);

        gridView.setLeft(0);
        gridView.setRight(WIDTH);
        gridView.setTop(0);
        gridView.setBottom(HEIGHT);
    }

    @Test
    public void testSetup()
    {
        log("View dimensions = " + gridView.getWidth() + " x " + gridView.getHeight());
        assertEquals(WIDTH, gridView.getWidth());
        assertEquals(HEIGHT, gridView.getHeight());
    }

    @Test
    public void testToCanvasThenToGrid()
    {
        for(PointF p : points)
        {
            PointF canvasP = gridView.toCanvas(p);
            PointF gridP = gridView.toGrid(canvasP);

            assertEquals(p, gridP);
        }
    }

    @Test
    public void testToGridThenToCanvas()
    {
        for(PointF p : points)
        {
            PointF gridP = gridView.toGrid(p);
            PointF canvasP = gridView.toCanvas(gridP);

            assertEquals(p, canvasP);
        }
    }

    @Test
    public void testSetOrigonCanvas()
    {
        for(PointF p : points)
        {
            gridView.setOrigonCanvasLocation(p);
            assertEquals(p, gridView.getOrigonCanvasLocation());
        }
    }

    @Test
    public void testSetCameraGridLocation()
    {
        PointF center = gridView.getScreenCenter();
        for(PointF p : points)
        {
            gridView.setCameraGridLocation(p);
            assertEquals(center, gridView.getCameraCanvasLocation());
            assertEquals(p, gridView.getCameraGridLocation());
        }
    }


    @Test
    public void testMapGridPointToCanvasPoint()
    {
        PointF cameraGridLocation = gridView.getCameraGridLocation();
        PointF cameraCanvasLocation = gridView.getCameraCanvasLocation();
        PointF origonCanvasLocation = gridView.getOrigonCanvasLocation();


        PointF screenCenter = gridView.getScreenCenter();

        for(PointF p : points)
        {
            gridView.mapGridPointToCanvasPoint(p, screenCenter);
            PointF canvas = gridView.toCanvas(p);
            assertEquals("Mapping grid(" + p.toString() + ") to canvas(" + screenCenter.toString() + ")", screenCenter, canvas);
        }

    }
}


