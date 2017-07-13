package com.soupthatisthick.encounterbuilder.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Picture;
import android.graphics.Point;
import android.graphics.PointF;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;

import com.soupthatisthick.encounterbuilder.util.Vector;
import com.soupthatisthick.encounterbuilder.util.announcer.Announcer;

import java.util.EventListener;

/**
 * Created by Owner on 1/3/2017.
 * Copyright of Stuart Marr Erskine, all rights reserved.
 */

public class GridView extends View {

    public static final String TAG = GridView.class.getSimpleName();

    public final Announcer<GridView.Listener> listeners = Announcer.to(GridView.Listener.class);

    public float getMinGridLineDistance() {
        return minGridLineDistance;
    }

    public interface Listener extends EventListener{
        void onGridViewUpdate(GridView view);
    }

    @Override
    public void invalidate()
    {
        super.invalidate();
        listeners.announce().onGridViewUpdate(this);
    }


    public void setMinGridLineDistance(float minGridLineDistance) {
        this.minGridLineDistance = minGridLineDistance;
    }

    public boolean isPaintVisible() {
        return paintVisible;
    }

    public void setPaintVisible(boolean paintVisible) {
        if (paintVisible != this.paintVisible) {
            this.paintVisible = paintVisible;
            invalidate();
        }
    }

    public boolean isCoordsVisible() {
        return coordsVisible;
    }

    public void setCoordsVisible(boolean coordsVisible) {
        if (coordsVisible != this.coordsVisible) {
            this.coordsVisible = coordsVisible;
            invalidate();
        }
    }

    public boolean isGridVisible() {
        return gridVisible;
    }

    public void setGridVisible(boolean gridVisible) {
        if (gridVisible != this.gridVisible) {
            this.gridVisible = gridVisible;
            invalidate();
        }
    }

    public boolean isSpritesVisible() {
        return spritesVisible;
    }

    public void setSpritesVisible(boolean spritesVisible) {
        if (spritesVisible != this.spritesVisible) {
            this.spritesVisible = spritesVisible;
            invalidate();
        }
    }

    public interface Adapter
    {

        /**
         * Returns the bounds on the grid (not the canvas) for the given drawable
         * @param position indicates the ordering of the drawing starting at 0
         * @return the bounds on the grid for the given drawable
         */
        RectF getBounds(int position);

        int getCount();

        Drawable getDrawable(int position);
    }

    private PointF origonCanvasLocation;
    private PointF gridSize;
    private float gridTextSize;
    private float minGridLineDistance;

    private Paint gridLinePaint;
    private Paint textPaint;
    private Paint backgroundPaint;

    private boolean gridVisible, coordsVisible, paintVisible, spritesVisible;

    private Adapter gridViewAdapter = null;

    public GridView(Context context) {
        super(context);
        init();
    }

    public GridView(Context context, AttributeSet attributeSet)
    {
        super(context, attributeSet);
        init();
    }

    public GridView(Context context, AttributeSet attributeSet, int defStyleAttr)
    {
        super(context, attributeSet, defStyleAttr);
        init();
    }

    protected void init()
    {

        paintVisible = true;
        coordsVisible = true;
        gridVisible = true;
        spritesVisible = true;

        origonCanvasLocation = new PointF(getWidth()/2f,getHeight()/2f);

        gridTextSize = 50f;
        minGridLineDistance = 50F;

        gridSize = new PointF(200F, 200F);

        backgroundPaint = new Paint();
        backgroundPaint.setColor(Color.BLACK);

        gridLinePaint = new Paint();
        gridLinePaint.setColor(Color.YELLOW);

        textPaint = new Paint();
        textPaint.setTextSize(50f);
        textPaint.setTextAlign(Paint.Align.CENTER);
        textPaint.setColor(Color.YELLOW);
    }

    public float gridTextSize()
    {
        return gridTextSize;
    }

    public void setGridTextSize(float size)
    {
        this.gridTextSize = size;
        invalidate();
    }

    public PointF getTileSize()
    {
        return new PointF(tileWidth(), tileHeight());
    }

    public void setTileSize(PointF tileSize)
    {
        setTileSize(tileSize.x, tileSize.y);
    }

    public void setTileSize(float tileWidth, float tileHeight)
    {
        if (tileWidth>0 && tileHeight>0)
        {
            float modX = tileWidth / tileWidth();
            float modY = tileHeight / tileHeight();

            gridSize = new PointF(tileWidth, tileHeight);

            invalidate();
        }
    }


    /**
     * This will zoom the grid. Relative zoom can't be zero.
     *
     * @param relativeZoom is the factor to multiply all dimensions by
     */
    public void zoom(float relativeZoom)
    {
        if (relativeZoom>0) {
            setTileSize(tileHeight() * relativeZoom , tileWidth() * relativeZoom);
            setGridTextSize(gridTextSize() * relativeZoom);
            invalidate();
        }
    }


    @Override
    protected void onSizeChanged(int w1, int h1, int w2, int h2)
    {
        super.onSizeChanged(w1, h1, w2, h2);
        setCameraGridLocation(getCameraGridLocation());
    }

    public void drawDotCanvas(Canvas canvas, int color, PointF canvasLocation)
    {
        Paint paint = new Paint();
        paint.setColor(color);
        paint.setAlpha(128);
        canvas.drawCircle(canvasLocation.x, canvasLocation.y, 50F, paint);
    }

    public void drawDotGrid(Canvas canvas, int color, PointF gridLocation)
    {
        drawDotCanvas(canvas, color, toCanvas(gridLocation));
    }

    public float tileWidth()
    {
        return gridSize.x;
    }

    public float tileHeight()
    {
        return gridSize.y;
    }

    public int snapCoord(float coord)
    {
        return (coord>=0) ? (int) coord : (int) coord - 1;
    }

    /**
     * This will return the location of the canvas point in grid coordinates
     * @param canvasLocation
     * @return a {link PointF} indicating exactly where on the grid the specified point is at.
     */
    public PointF toGrid(PointF canvasLocation)
    {
        return new PointF(
            gridX(canvasLocation.x),
            gridY(canvasLocation.y)
        );
    }

    public PointF toCanvas(PointF gridLocation)
    {
        return new PointF(
            canvasX(gridLocation.x),
            canvasY(gridLocation.y)
        );
    }

    public RectF toCanvas(RectF gridBounds)
    {
        return new RectF(
            tileLeft(gridBounds.left),
            tileTop(gridBounds.top),
            tileRight(gridBounds.right),
            tileBottom(gridBounds.bottom)
        );
    }

    /**
     * This will snap the specific location into a grid location with no fractional information
     * @param gridLocation
     * @return the origon point of the bounding location
     */
    public Point snapTile(PointF gridLocation)
    {
        return new Point(snapCoord(gridLocation.x), snapCoord(gridLocation.y));
    }


    /**
     * Draw a tile on the canvas
     * @param canvas
     * @param gridLocation
     * @param paint
     */
    public void drawTile(Canvas canvas, Point gridLocation, Paint paint)
    {
        RectF bounds = tileBounds(gridLocation);

        drawX(canvas, bounds, paint);
        drawCross(canvas, bounds, paint);
    }

    public static final float wrap(float min, float max, float value)
    {
        if (min>max) return wrap(max, min, value);
        float diff = max-min;
        while(value<min)
        {
            value += diff;
        }
        while(value >= max)
        {
            value -= diff;
        }
        return value;
    }

    public static final RectF toRectF(Rect rect)
    {
        return new RectF(rect.left, rect.top, rect.width(), rect.height());
    }


    public void drawX(Canvas canvas, RectF bounds, Paint paint)
    {
        canvas.drawLine(bounds.left, bounds.top, bounds.right, bounds.bottom, paint);
        canvas.drawLine(bounds.left, bounds.bottom, bounds.right, bounds.top, paint);
    }

    public void drawCross(Canvas canvas, RectF bounds, Paint paint)
    {
        canvas.drawLine(bounds.left, bounds.centerY(), bounds.right, bounds.centerY(), paint);
        canvas.drawLine(bounds.centerX(), bounds.top, bounds.centerX(), bounds.bottom, paint);
    }

    public float gridX(float canvasX)
    {
        return (canvasX - origonCanvasLocation.x + tileWidth()/2F)/tileWidth();
    }

    public float gridY(float canvasY)
    {
        return (canvasY - origonCanvasLocation.y + tileHeight()/2F)/tileHeight();
    }

    public float canvasX(float gridX)
    {
        return (gridX * tileWidth() - tileWidth()/2F) + origonCanvasLocation.x;
    }

    public float canvasY(float gridY)
    {
        return (gridY * tileHeight() - tileHeight()/2F) + origonCanvasLocation.y;
    }

    // Integer arguments
    public float tileLeft(int x) {  return canvasX(x) - tileWidth()/2F;    }
    public float tileRight(int x)
    {
        return tileLeft(x) + tileWidth();
    }
    public float tileTop(int y)
    {
        return canvasY(y) - tileHeight()/2F;
    }
    public float tileBottom(int y)
    {
        return tileTop(y) + tileHeight();
    }
    public float tileCenterX(int x)
    {
        return canvasX(x);
    }
    public float tileCenterY(int y)
    {
        return canvasY(y);
    }

    // Float arguments
    public float tileLeft(float x)  { return canvasX(x) - tileWidth()/2F;}
    public float tileRight(float x) { return tileLeft(x) + tileWidth();    }
    public float tileTop(float y)   { return canvasY(y) - tileHeight()/2F;}
    public float tileBottom(float y) { return tileTop(y) + tileHeight();}
    public float tileCenterX(float x) {return canvasX(x);}
    public float tileCenterY(float y) { return canvasY(y); }

    public PointF canvasLocation(PointF gridLocation)
    {
        return new PointF(
            tileCenterX(gridLocation.x),
            tileCenterY(gridLocation.y)
        );
    }

    public RectF tileBounds(Point gridLocation)
    {
        return new RectF(
            tileLeft(gridLocation.x),
            tileTop(gridLocation.y),
            tileRight(gridLocation.x),
            tileBottom(gridLocation.y)
        );
    }

    /**
     * This will take the bounds of a rectangle on a grid and convert them into
     * an area on the canvas
     * @param gridArea
     * @return the canvas bounds of the rectangle
     */
    public RectF gridAreaBounds(Rect gridArea)
    {
        return new RectF(
            tileLeft(gridArea.left),
            tileTop(gridArea.top),
            tileRight(gridArea.right),
            tileBottom(gridArea.bottom)
        );
    }

    public RectF gridAreaBounds(RectF gridArea)
    {
        return new RectF(
            tileLeft(gridArea.left),
            tileTop(gridArea.top),
            tileRight(gridArea.right),
            tileBottom(gridArea.bottom)
        );
    }

    public Rect gridAreaShown(Canvas canvas)
    {
        float startX = gridX(0F);
        float startY = gridY(0F);
        float endX = gridX(canvas.getWidth());
        float endY = gridY(canvas.getHeight());

        Point start = snapTile(new PointF(startX, startY));
        Point end = snapTile(new PointF(endX, endY));

        return new Rect(start.x, start.y, end.x, end.y);
    }

    /**
     * This will display the coordinates for each tile at it's location on the canvas
     * @param canvas
     * @param paint
     */
    public void drawCoords(Canvas canvas, Paint paint)
    {

        float startX = gridX(0F);
        float startY = gridY(0F);
        float endX = gridX(canvas.getWidth());
        float endY = gridY(canvas.getHeight());

        Point start = snapTile(new PointF(startX, startY));
        Point end = snapTile(new PointF(endX, endY));

        float widthStart = paint.measureText(" (" + start.x + "," + start.y + ") ");
        float widthEnd = paint.measureText(" (" + end.x + "," + end.y + ") ");
        float minWidth = Math.max(widthStart, widthEnd);

        boolean doesTextFit = (tileWidth() >= minWidth);
        doesTextFit = true;

        if (doesTextFit) {
            for (int x = start.x; x <= end.x; x++) {
                for (int y = start.y; y <= end.y; y++) {
                    float centerX = canvasX(x);
                    float centerY = canvasY(y);
                    canvas.drawText("(" + x + "," + y + ")", centerX, centerY, paint);
                }
            }
        }

    }

    /**
     * This will draw the grid using the provided paint
     * @param canvas
     * @param paint
     */
    public void drawGrid(Canvas canvas, Paint  paint)
    {
        float width = canvas.getWidth();
        float height = canvas.getHeight();
        float dx = tileWidth();
        float dy = tileHeight();

        float startX = wrap(0, tileWidth(), origonCanvasLocation.x);
        float startY = wrap(0, tileHeight(), origonCanvasLocation.y);

        float minStep = 50f;

        while(dx<minStep || dy<minStep)
        {
            dx *= 2F;
            dy *= 2F;
        }

        // Draw the borders
        for(float x=startX; x<width; x+=dx)
        {
            for(float y=startY; y<height; y+=dy)
            {
                canvas.drawLine(0, y, width, y, paint);
            }

            canvas.drawLine(x, 0, x, height, paint);
        }

    }

    /**
     * Draw the picture, stretched to fit into the dst rectangle.
     * @param canvas is the canvas to which we draw onto
     * @param areaOnGrid is the rectangle on the grid (not canvas coords) we want the image tobe placed in
     * @param picture is the image to be displayed
     */
    public void drawSprite(Canvas canvas, Rect areaOnGrid, Picture picture)
    {
        canvas.drawPicture(picture, gridAreaBounds(areaOnGrid));
    }

    /**
     * This will draw the given picture into the bounds for the specified tile on the grid
     * @param canvas
     * @param pointOnGrid
     * @param picture
     */
    public void drawSprite(Canvas canvas, Point pointOnGrid, Picture picture)
    {
        canvas.drawPicture(picture, tileBounds(pointOnGrid));
    }

    public void drawSprite(Canvas canvas, RectF areaOnGrid, Drawable drawable)
    {
        RectF bounds = gridAreaBounds(areaOnGrid);
        Rect snappedBounds = new Rect(
            (int) bounds.left,
            (int) bounds.top,
            (int) bounds.right,
            (int) bounds.bottom
        );
        drawable.setBounds(snappedBounds);
        drawable.draw(canvas);
    }

    public Paint getGridLinePaint() {
        return gridLinePaint;
    }

    public void setGridLinePaint(Paint gridLinePaint) {
        this.gridLinePaint = gridLinePaint;
        invalidate();
    }

    public Paint getBackgroundPaint() {
        return backgroundPaint;
    }

    public void setBackgroundPaint(Paint background) {
        this.backgroundPaint = background;
        invalidate();
    }

    public Paint getTextPaint() {
        return textPaint;
    }

    public void setTextPaint(Paint textPaint) {
        this.textPaint = textPaint;
        invalidate();
    }

    public void setOrigonCanvasLocation(PointF canvasLocation)
    {
        origonCanvasLocation = canvasLocation;
        invalidate();
    }

    public PointF getOrigonCanvasLocation()
    {
        return this.origonCanvasLocation;
    }

    public PointF getScreenCenter() { return new PointF(getWidth()/2F, getHeight()/2F);}

    public PointF getCameraGridLocation()
    {
        return toGrid(getScreenCenter());
    }

    public PointF getCameraCanvasLocation()
    {
        return getScreenCenter();
    }

    protected void drawSprites(Canvas canvas)
    {
        GridView.Adapter adapter = getGridViewAdapter();

        if (adapter != null && adapter.getCount()>0)
        {
            Rect gridShown = gridAreaShown(canvas);
            RectF gridShownDetailed = new RectF(gridShown.left, gridShown.top, gridShown.right, gridShown.bottom);

            for(int i=0; i<adapter.getCount(); i++)
            {
                RectF bounds = adapter.getBounds(i);
                Drawable drawable = adapter.getDrawable(i);
                drawSprite(canvas, bounds, drawable);
            }
        }
    }

    /**
     * @return the {@link GridView.Adapter} responsible for providing the drawable on the grid
     */
    public Adapter getGridViewAdapter() {
        return gridViewAdapter;
    }

    /**
     * Sets the adapter responsible for providing the ordering and locations of all the drawables on the grid
     * @param gridViewAdapter
     */
    public void setGridViewAdapter(Adapter gridViewAdapter) {
        this.gridViewAdapter = gridViewAdapter;
        invalidate();
    }

    /**
     * This will translate the origon on the canvas in such a way that
     * toCanvas(gridPoint) = canvasPoint
     * @param gridPoint
     * @param canvasPoint
     */
    public void mapGridPointToCanvasPoint(PointF gridPoint, PointF canvasPoint) {
        PointF gridPointOnCanvas = toCanvas(gridPoint);
        PointF canvasMove = Vector.diff(canvasPoint, gridPointOnCanvas);
        PointF newOrigon = Vector.sum(origonCanvasLocation, canvasMove);

        setOrigonCanvasLocation(newOrigon);
    }

    public void setCameraGridLocation(PointF gridLocation)
    {
        mapGridPointToCanvasPoint(gridLocation, getScreenCenter());
    }

    @Override
    public void onDraw(Canvas canvas)
    {
        if (isPaintVisible())
        {
            canvas.drawPaint(getBackgroundPaint());
        }

        if (isGridVisible()) {
            drawGrid(canvas, getGridLinePaint());
        }

        if (isCoordsVisible()) {
            drawCoords(canvas, getTextPaint());
        }

        if (isSpritesVisible()) {
            drawSprites(canvas);
        }
    }
}
