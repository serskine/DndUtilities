package com.soupthatisthick.encounterbuilder.printing;

import android.graphics.RectF;

import com.soupthatisthick.encounterbuilder.util.view.Draw;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Owner on 4/24/2017.
 * Copyright of Stuart Marr Erskine, all rights reserved.
 */

public abstract class PageBounds {

    protected float zoom = 1f;

    /**
     * @param zoom sets the magnification factor for the bounds
     */
    public final void setZoom(float zoom) {
        this.zoom = zoom;
    }

    /**
     *@return the magnification multiplier currently set for the bounds
     */
    public final float getZoom()
    {
        return this.zoom;
    }

    /**
     *@return the width of the page after zooming in pixels
     */
    public final float getPageWidth()
    {
        return getRawPageWidth()*getZoom();
    }

    /**
     * @return the height of the page after zooming in pixels
     */
    public final float getPageHeight()
    {
        return getRawPageHeight()*getZoom();
    }

    /**
     * This will adjust the zoom factor such that the page width is the specified value
     * @param pageWidth
     */
    public final void zoomToPageWidth(float pageWidth)
    {
        setZoom(pageWidth/getRawPageWidth());
    }

    /**
     * This will adjust the zoom factor such that the page height is the specified value in pixels
     * @param pageHeight
     */
    public final void zoomToPageHeight(float pageHeight)
    {
        setZoom(pageHeight/getRawPageHeight());
    }


    /**
     * @return a {@link Map<String, RectF>} containing all the fields on the page image before zooming.
     */
    public abstract Map<String, RectF> getRawMapping();

    public abstract float getRawPageWidth();
    public abstract float getRawPageHeight();

    /**
     * @return a map containing all the known field bounds on the page after zooming
     */
    public Map<String, RectF> getMapping()
    {
        Map<String, RectF> pixelMapping = getRawMapping();
        Map<String, RectF> zoomMapping = new HashMap<>();
        float z = getZoom();

        for(String key : pixelMapping.keySet())
        {

            RectF value = pixelMapping.get(key);
            RectF newValue = new RectF(value.left*z, value.top*z, value.right*z, value.bottom*z);
            zoomMapping.put(key, newValue);
        }
        return zoomMapping;
    }

    /**
     * This method will return the bounding rectangle for the entire page.
     * @return the bounding rectangle for the entire page.
     */
    public final RectF BOUNDS()
    {
        return Draw.rect(0*getZoom(), 0*getZoom(), getRawPageWidth() *getZoom(), getRawPageHeight() *getZoom());
    }


}
