package com.soupthatisthick.encounterbuilder.activity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.soupthatisthick.util.Logger;
import com.soupthatisthick.util.activity.AppActivity;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import soupthatisthick.encounterapp.R;

/**
 * Created by Owner on 2/3/2017.
 * Copyright of Stuart Marr Erskine, all rights reserved.
 */
public class CameraActivity extends AppActivity {

    ImageView imageView;
    Button takePictureButton;
    Uri file;

    int PICTURE_REQUEST_CODE = 0;
    int CAPTURE_REQUEST = 100;

    /**
     * Called when we create the activity
     * @param savedInstanceSstate
     */
    @Override
    public void onCreate(Bundle savedInstanceSstate)
    {
        super.onCreate(savedInstanceSstate);
        setContentView(R.layout.activity_camera);


        takePictureButton = (Button) findViewById(R.id.btn_takepicture);
        imageView = (ImageView) findViewById(R.id.image_view);

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            takePictureButton.setEnabled(false);
            ActivityCompat.requestPermissions(this, new String[] { Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE }, PICTURE_REQUEST_CODE);
        }
    }

    /**
     * Called when we want to take a picture
     * @param view
     */
    public void onClickCaptureButton(View view)
    {
        takePicture();
    }

    /**
     * Called when we have been granted or denied permissions
     * @param requestCode
     * @param permissions
     * @param grantResults
     */
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        if (requestCode == PICTURE_REQUEST_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED
                    && grantResults[1] == PackageManager.PERMISSION_GRANTED) {
                takePictureButton.setEnabled(true);
            }
        }
    }

    /**
     * Called when we have recieved an image capture
     * @param requestCode
     * @param resultCode
     * @param data
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == CAPTURE_REQUEST)
        {
            if (resultCode == RESULT_OK)
            {
                imageView.setImageURI(file);
                Intent intent = new Intent();
                intent.setData(file);
                setResult(RESULT_OK, intent);
                finish();
            } else if (requestCode == RESULT_CANCELED)
            {
                Intent intent = new Intent();
                setResult(RESULT_CANCELED, intent);
                finish();
            } else
            {
                Logger.info("CAPTURE_REQUEST (" + requestCode + ") returned result (" + requestCode + ")");
            }
        }
    }

    /**
     * This will capture the picture
     */
    public void takePicture() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        file = Uri.fromFile(getOutputMediaFile());
        intent.putExtra(MediaStore.EXTRA_OUTPUT, file);

        startActivityForResult(intent, CAPTURE_REQUEST);
    }

    /**
     * This will get the output picture file
     * @return
     */
    @Nullable
    private static File getOutputMediaFile(){
        File mediaStorageDir = new File(Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_PICTURES), "CameraDemo");

        if (!mediaStorageDir.exists()){
            if (!mediaStorageDir.mkdirs()){
                return null;
            }
        }

        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        return new File(mediaStorageDir.getPath() + File.separator +
                "IMG_"+ timeStamp + ".jpg");
    }
}
