package com.soupthatisthick.encounterbuilder.activity.lookup;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.PointF;
import android.graphics.RectF;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

import com.soupthatisthick.encounterbuilder.activity.CameraActivity;
import com.soupthatisthick.encounterbuilder.activity.MyActivity;
import com.soupthatisthick.encounterbuilder.adapters.lookup.EncounterAdapter;
import com.soupthatisthick.encounterbuilder.adapters.lookup.RandomEncounterMap;
import com.soupthatisthick.encounterbuilder.model.lookup.EncounterMap;
import com.soupthatisthick.encounterbuilder.model.lookup.Sprite;
import com.soupthatisthick.util.Logger;
import com.soupthatisthick.encounterbuilder.util.Vector;
import com.soupthatisthick.encounterbuilder.view.GridView;
import com.soupthatisthick.encounterbuilder.view.controller.GridViewScrollAndZoomDetector;

import java.io.File;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import soupthatisthick.encounterapp.R;

/**
 * Created by Owner on 1/8/2017.
 * Copyright of Stuart Marr Erskine, all rights reserved.
 */

public class EditMapActivity extends MyActivity
        implements EncounterMap.Listener, GridView.Listener, GridViewScrollAndZoomDetector.Listener
{

    public static final String TAG = EditMapActivity.class.getSimpleName();

    GridView gridView;
    Button selectButton;
    EncounterMap encounterMap;
    GridViewScrollAndZoomDetector scrollAndZoomDetector;

    private float   minTileSize = 50F;
    private float   maxTileSize = 500F;


    static final int REQUEST_IMAGE_CAPTURE = 1;

    private void dispatchTakePictureIntent() {
//        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
//        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
//            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
//        }
        Intent intent = new Intent(this, CameraActivity.class);
        startActivityForResult(intent, REQUEST_IMAGE_CAPTURE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        Logger.info("onActivityResult(requestCode=" + requestCode + ", resultCode=" + resultCode + ", Intent=" + data);

        if ((data != null) && (requestCode == REQUEST_IMAGE_CAPTURE) && (resultCode == RESULT_OK)) {
            Uri imageUri = data.getData();

            File image = new File(imageUri.getPath());
            BitmapFactory.Options bmOptions = new BitmapFactory.Options();
            Bitmap bitmap = BitmapFactory.decodeFile(image.getAbsolutePath(),bmOptions);

            addSpriteWithBitmap(bitmap);
        } else {
            Logger.error("Failed to capture picture for a new sprite", null);
        }
    }

    /**
     * This will take the bitmap from the camera and add a sprite at the current camera location
     * @param imageBitmap
     */
    private void addSpriteWithBitmap(Bitmap imageBitmap)
    {
        PointF gridPoint = gridView.getCameraGridLocation();
        try {
            Logger.debug("Creating the drawable");

            int minDimension = Math.min(imageBitmap.getWidth(), imageBitmap.getHeight());
            int srcX = (imageBitmap.getWidth()-minDimension)/2;
            int srcY = (imageBitmap.getHeight()-minDimension)/2;
            Bitmap squareBitmap = Bitmap.createBitmap(imageBitmap, srcX, srcY, minDimension, minDimension);

            Drawable drawable = new BitmapDrawable(getResources(), squareBitmap);
            Sprite sprite = new Sprite(drawable, new RectF(gridPoint.x, gridPoint.y, gridPoint.x, gridPoint.y));
            encounterMap.addSprite(sprite);
        } catch (Exception e)
        {
            Logger.error("Failed to create a new drawable for sprite.", e);
        }
    }

    // Handles the translation of sprites
    Set<Integer> selectedSprites = new HashSet<>();
    Map<Integer, RectF> selectedOrigonalBounds = new HashMap<>();
    PointF selectStart = new PointF(0f,0f);
    boolean selectionFlag = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        requestWindowFeature(Window.FEATURE_NO_TITLE);

        setContentView(R.layout.activity_edit_map);

        initGridView();
        initControls();

        setTileSizeRange(50f, 500f);
    }

    private void initControls()
    {
        selectButton = (Button) findViewById(R.id.selectButton);
    }

    private void initGridView()
    {
        gridView = (GridView) findViewById(R.id.grid_view);

        scrollAndZoomDetector = new GridViewScrollAndZoomDetector(gridView);
        scrollAndZoomDetector.listeners.addListener(this);

        gridView.listeners.addListener(this);

        encounterMap = new RandomEncounterMap(getBaseContext());
        gridView.setGridViewAdapter(new EncounterAdapter(encounterMap));


        PointF expectedCamera = new PointF(0, 0F);

        gridView.setCameraGridLocation(expectedCamera);

    }

    @Override
    public void onEncounterUpdated(EncounterMap encounterMap) {
        gridView.invalidate();

    }

    public float getMinTileSize() { return minTileSize;     }
    public float getMaxTileSize() { return maxTileSize;     }


    public void setTileSizeRange(float min, float max)
    {
        if (max < max)
        {
            setTileSizeRange(max, min);
        }
        min = (min<1) ? 1 : min;
        max = (max<min) ? min : max;

        float prevMin = this.minTileSize;
        float prevMax = this.maxTileSize;

        this.minTileSize = min;
        this.maxTileSize = max;

        onTileSizeRangeChanged(prevMin, prevMax, min, max);
    }


    private PointF snapTileSize(PointF newSize)
    {
        PointF minVector = new PointF(getMinTileSize(), getMinTileSize());
        PointF maxVector = new PointF(getMaxTileSize(), getMaxTileSize());

        newSize = (newSize.x < getMinTileSize() || newSize.y < getMinTileSize()) ? minVector : newSize;
        newSize = (newSize.x > getMaxTileSize() || newSize.y > getMaxTileSize()) ? maxVector : newSize;

        return newSize;
    }

    private void moveSelectedSprites()
    {
        PointF cameraLocation = gridView.getCameraGridLocation();
        PointF moveVector = Vector.diff(cameraLocation, selectStart);
        for(Integer i : selectedSprites)
        {
            Sprite sprite = encounterMap.getSpriteAt(i);
            RectF origonalBounds = selectedOrigonalBounds.get(i);
            RectF newBounds = Vector.translate(origonalBounds, moveVector);
            sprite.setBounds(newBounds);
        }
        gridView.invalidate();
    }

    private void startSelectingSprites()
    {

        selectButton.setText(R.string.button_select);
        selectStart = gridView.getCameraGridLocation();
        selectedSprites = encounterMap.getSpritesTouching(selectStart);
        selectedOrigonalBounds.clear();

        for(Integer i : selectedSprites)
        {
            Sprite sprite = encounterMap.getSpriteAt(i);
            selectedOrigonalBounds.put(i, sprite.getBounds());
        }
    }

    private void endSpriteSelection()
    {
        selectButton.setText(R.string.button_deselect);
        selectedSprites.clear();
        selectedOrigonalBounds.clear();
    }

    public void onClickSelectButton(View view)
    {
        selectionFlag = !selectionFlag;
        if (selectionFlag) {
            startSelectingSprites();
        } else {
            endSpriteSelection();
        }
    }

    public void onClickCaptureButton(View view)
    {
        dispatchTakePictureIntent();
    }

    @Override
    public void onGridViewUpdate(GridView view) {
       Log.d(TAG, "onGridViewUpdate()");
    }



    @Override
    public void onZoom(MotionEvent zoomDownEvent, MotionEvent currentMotionEvent, PointF zoomDownSize, PointF zoomDownVector, PointF zoomCurrentVector) {
        Log.d(TAG,"onZoom()");

        float prevLength = Vector.length(zoomDownVector);
        float currentLength = Vector.length(zoomCurrentVector);
        float scalar = currentLength / prevLength;

        PointF newSize = snapTileSize(Vector.scale(zoomDownSize, scalar));
        PointF oldCamera = gridView.getCameraGridLocation();

        gridView.setTileSize(newSize);
        gridView.setCameraGridLocation(oldCamera);
    }

    @Override
    public void onScroll(MotionEvent origonalMotionEvent, MotionEvent currentMotionEvent, PointF scrollDownOnCanvas, PointF origonDownOnCanvas) {
        Log.d(TAG, "onScroll()");

        PointF scrollPosition = getPosition(currentMotionEvent);
        PointF scrollMove = Vector.diff(scrollPosition, scrollDownOnCanvas);
        PointF newOrigonOnCanvas = Vector.sum(origonDownOnCanvas, scrollMove);

        gridView.setOrigonCanvasLocation(newOrigonOnCanvas);

        for(Integer i : selectedSprites)
        {
            Sprite sprite = encounterMap.getSpriteAt(i);
            RectF origonalBounds = selectedOrigonalBounds.get(i);
            RectF newbounds = Vector.translate(origonalBounds, gridView.toGrid(scrollMove));
            sprite.setBounds(newbounds);
        }
    }

    public void onTileSizeRangeChanged(float prevMinSize, float prevMaxSize, float newMinSize, float newMaxSize) {
        Log.d(TAG, "onSizeRangeChanged([" +prevMinSize + ", " + prevMaxSize + "] -> [" + newMinSize + ", " + newMaxSize + "]");
    }

    @Override
    public void onModeChanged(GridViewScrollAndZoomDetector.Mode prevMode, GridViewScrollAndZoomDetector.Mode currentMode) {
        Log.d(TAG, "onModeChanged(" + prevMode + ", " + currentMode + ")");
    }

    @Override
    public void onDown(MotionEvent currentMotionEvent, int pointerIndex, PointF gridPoint) {
        Set<Integer> positions = encounterMap.getSpritesTouching(gridPoint);

        for(Integer i : positions)
        {
            Sprite sprite = encounterMap.getSpriteAt(i);
            selectedOrigonalBounds.put(i, sprite.getBounds());
            selectedSprites.add(i);
        }
    }

    @Override
    public void onUp(MotionEvent currentMotionEvent, int pointerIndex, PointF gridPoint) {

    }

}