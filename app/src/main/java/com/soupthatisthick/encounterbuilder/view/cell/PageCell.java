package com.soupthatisthick.encounterbuilder.view.cell;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.soupthatisthick.encounterbuilder.printing.PageBounds;
import com.soupthatisthick.util.Logger;

import java.util.Map;

import soupthatisthick.encounterapp.R;

/**
 * Created by Owner on 4/29/2017.
 * Copyright of Stuart Marr Erskine, all rights reserved.
 */

public abstract class PageCell<PageType> extends ReadCell<PageType> {

    protected static final int DEFAULT_VIEW_WIDTH = 400;

    private ImageView theImage;

    /**
     * The default constructor for the PageCell
     * @param inflater
     * @param convertView
     * @param parent
     */
    public PageCell(LayoutInflater inflater, View convertView, ViewGroup parent) {
        super(inflater, convertView, parent);
    }

    /**
     * This will create a new Bitmap without the fields painted in, to be drawn on with the intention of being displayed on the screen.
     * @param resources
     * @param page
     * @param bounds
     * @return a {@link Bitmap} with a width in pixels equal to the screen width and height such that the aspect ratio of the {@link PageBounds} is still matched.
     */
    public final Bitmap createBitmap(Resources resources, PageType page, PageBounds bounds) {
        int reqWidth = (int) Math.max(1, bounds.BOUNDS().width());
        int reqHeight = (int) Math.max(1, bounds.BOUNDS().height());

        Bitmap bitmap = createBackgroundBitmap(resources, reqWidth, reqHeight);
        Canvas canvas = new Canvas(bitmap);

        bounds.zoomToPageWidth(bitmap.getWidth());

        paintFields(canvas, page, bounds);

        return bitmap;
    }

    /**
     * This will paint the fields onto the provided bitmap.
     * @param canvas is the canvas we use to perform drawing on the bitmap.
     * @param bounds will provide all the boundaries of the fields/
     */
    protected abstract void paintFields(Canvas canvas, PageType page, PageBounds bounds);

    /**
     * @return the integer resource id of the background image we want to display before painting the field values.
     */
    protected abstract int getBackgroundResource();

    /**
     * This will create a new bitmap with the desired background
     * @param resources
     * @param requiredWidth
     * @param requiredHeight
     * @return a Bitmap which has been scaled to fit the required bounds.
     */
    protected Bitmap createBackgroundBitmap(Resources resources, int requiredWidth, int requiredHeight)
    {
        Bitmap immutableBmp = decodeSampledBitmapFromResource(
                resources,
                getBackgroundResource(),
                requiredWidth,
                requiredHeight
        );
        Bitmap mutableBitmap = immutableBmp.copy(Bitmap.Config.ARGB_8888, true);
        return mutableBitmap;
    }

    /**
     * This method will determine the number of pixels required for the bitmap to display properly on the screen.
     * @return the number of pixels the image will be when displayed on the screen. By default it will return {@value DEFAULT_VIEW_WIDTH} pixels.
     */
    protected int getRequiredViewWidth()
    {
        return DEFAULT_VIEW_WIDTH;
    }

    /**
     * @return a new {@link PageBounds} object that will provide all the fields, and boundaries for those fields on the page
     */
    public abstract PageBounds createPageBounds();

    /**
     * This will create the drawable used to display the page on the screen
     * @param resources
     * @param page
     * @return a new drawable
     */
    public final Drawable createDrawable(Resources resources, PageType page, PageBounds bounds) {
        BitmapDrawable drawable = new BitmapDrawable(resources, createBitmap(resources, page, bounds));
        return drawable;
    }



    @Override
    public void updateUi(PageType page) {
        Resources resources = theImage.getResources();
        PageBounds bounds = createPageBounds();
        int reqWidth = getRequiredViewWidth();
        Logger.debug("reqWidth   = " + reqWidth);

        bounds.zoomToPageWidth(reqWidth);   // Uses match_parent

        Drawable drawable = createDrawable(resources, page, bounds);
        theImage.setImageDrawable(drawable);
    }


    @Override
    public View createView(LayoutInflater inflater, View convertView, ViewGroup parent) {
        View view = inflater.inflate(R.layout.cell_picture, parent);
        theImage = (ImageView) view.findViewById(R.id.the_image);
        return view;
    }


    /**
     * This is used to load a {@link Bitmap} from the specified resource id
     * @param res
     * @param resId
     * @param reqWidth
     * @param reqHeight
     * @return
     */
    protected static Bitmap decodeSampledBitmapFromResource(Resources res, int resId, int reqWidth, int reqHeight) {

        // First decode with inJustDecodeBounds=true to check dimensions
        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeResource(res, resId, options);

        // Calculate inSampleSize
        options.inSampleSize = calculateInSampleSize(options, reqWidth, reqHeight);

        // Decode bitmap with inSampleSize set
        options.inJustDecodeBounds = false;
        return BitmapFactory.decodeResource(res, resId, options);
    }

    /**
     * This is used to determine the largest sample numFaces required for scaling a bitmap into a different numFaces.
     * @param options
     * @param reqWidth
     * @param reqHeight
     * @return
     */
    protected static int calculateInSampleSize(BitmapFactory.Options options, int reqWidth, int reqHeight) {
        // Raw height and width of image
        final int height = options.outHeight;
        final int width = options.outWidth;
        int inSampleSize = 1;

        if (reqHeight<1 || reqWidth<1) {
            throw new RuntimeException("We must have a minimum required width and height of 1 pixel each. Provided required dimensions = " + reqWidth + "x" + reqHeight + " pixels.");
        }

        if (height > reqHeight || width > reqWidth) {

            final int halfHeight = height / 2;
            final int halfWidth = width / 2;

            // Calculate the largest inSampleSize value that is a power of 2 and keeps both
            // height and width larger than the requested height and width.
            while   (
                    ((halfHeight / inSampleSize) >= reqHeight)
                            &&  ((halfWidth / inSampleSize) >= reqWidth)
                    )
            {
                inSampleSize = inSampleSize * 2;
            }
        }

        return inSampleSize;
    }


    /**
     * This is used to show all the field bounds on the canvas. It is primarily used for debugging to view the boundaries on the screen.
     * @param canvas
     * @param bounds
     * @param paint
     */
    public static final void paintFieldBounds(@NonNull Canvas canvas, PageBounds bounds, @NonNull Paint paint)
    {
        Map<String, RectF> mapping = bounds.getMapping();

        for(String key : mapping.keySet())
        {
            RectF field = mapping.get(key);

            canvas.drawRect(field, paint);
        }
    }
}
