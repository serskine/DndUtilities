package com.soupthatisthick.encounterbuilder.ifaces;

import android.graphics.Bitmap;
import android.graphics.RectF;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.util.Map;

/**
 * Created by Owner on 3/7/2017.
 * Copyright of Stuart Marr Erskine, all rights reserved.
 */

public interface PrintTemplate<Page> {

    /**
     * This is used to provide a list of all fields
     * @return a {@link Map<String, RectF>} which tells us where all the boundaries are
     */
    @NonNull
    Map<String, RectF> getMapping();

    /**
     * This is used to extract a field value from an object for printing
     * @param page that will provide the data for the field. If the input is null
     *             or can't be determined it is appropriate to return an empty string
     * @return a non null string
     */
    @NonNull
    String getValue(@Nullable Page page);

    /**
     * This will return the background image that we will draw the fields onto
     * @return a {@link Bitmap} for editing
     */
    @NonNull
    Bitmap getBackground();
}
