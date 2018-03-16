package com.example.mergeimagetest;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //This is sample picture.
        //Please take picture form gallery or camera.
//        Bitmap pictureBitmap = AssetsUtil.getBitmapFromAsset(this, "picture.jpg");
        Bitmap frameBitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.out_frame);
        Bitmap pictureBitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.picture);

        //This is sample frame.
        // the number of left, top, right, bottom is the area to show picture.
        // last argument is degree of rotation to fit picture and frame.
        Frame frameA = new Frame("frame_a.png", 250, 250, 1250, 1250, 0);
        Bitmap mergedBitmap = frameA.mergeWith(this, frameBitmap, pictureBitmap);

        //showing result bitmap
        ImageView imageView = (ImageView) findViewById(R.id.image);
        imageView.setImageBitmap(mergedBitmap);
    }
}
