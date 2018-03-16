package com.example.mergeimagetest;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Rect;
import android.util.Log;

/**
 * Created by 易辰 on 2018/3/16.
 */
public class Frame {

    private static final String TAG = "Frame";

    //filename of frame
    private String mFrameName;

    //Rect of picture area in frame
    private final Rect mPictureRect;

    //degree of rotation to fit picture and frame.
    private final float mRorate;

    public Frame(String frameName,int left, int top, int right, int bottom, float rorate) {
        mFrameName = frameName;
        mPictureRect = new Rect(left, top, right, bottom);
        mRorate = rorate;
    }

    public Bitmap mergeWith(Context context,Bitmap frameBitmap , Bitmap pictureBitmap) {
//        Bitmap frameBitmap = AssetsUtil.getBitmapFromAsset(context, mFrameName);

        Bitmap.Config conf = Bitmap.Config.ARGB_8888;
        Bitmap bitmap = Bitmap.createBitmap(frameBitmap.getWidth(), frameBitmap.getHeight(), conf);
        Canvas canvas = new Canvas(bitmap);

        Matrix matrix = getMatrix(pictureBitmap);
        Log.e(TAG, "getMatrix(pictureBitmap): " + getMatrix(pictureBitmap));

        //draw Frame
        canvas.drawBitmap(frameBitmap, 0, 0, null);
        //draw photo to cover frame
        canvas.drawBitmap(pictureBitmap, matrix, null);

        return bitmap;

    }

    Matrix getMatrix(Bitmap pictureBitmap) {
        float widthRatio = mPictureRect.width() /  (float) pictureBitmap.getWidth();
        float heightRatio = mPictureRect.height() / (float) pictureBitmap.getHeight();

        float ratio;

        if (widthRatio > heightRatio) {
            ratio = widthRatio;

        } else {
            ratio = heightRatio;
        }

        float width = pictureBitmap.getWidth() * ratio;
        float height = pictureBitmap.getHeight() * ratio;
        float left = mPictureRect.left - (width - mPictureRect.width()) / 2f;
        float top = mPictureRect.top - (height - mPictureRect.height()) / 2f;

        Matrix matrix = new Matrix();
        matrix.postRotate(mRorate);
        matrix.postScale(ratio, ratio);
        matrix.postTranslate(left, top);

        return matrix;
    }
}
