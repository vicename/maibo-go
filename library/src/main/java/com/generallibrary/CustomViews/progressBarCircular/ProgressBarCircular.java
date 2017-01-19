package com.generallibrary.CustomViews.progressBarCircular;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.TypedValue;

/**
 * Created by Li DaChang on 16/9/5.
 * ..-..---.-.--..---.-...-..-....-.
 */
public class ProgressBarCircular extends CustomView {

    private final static String ANDROID_XML = "http://schemas.android.com/apk/res/android";
    private int mBackgroundColor = Color.parseColor("#536dfe");
    private int mArcD = 1;
    private int mArcO = 0;
    private float mRotateAngle = 0;
    private int mLimit = 0;
    private float mRadius1 = 0;
    private float mRadius2 = 0;
    private int mCont = 0;
    private boolean mFirstAnimationOver = false;

    public ProgressBarCircular(Context context, AttributeSet attrs) {
        super(context, attrs);
        setAttributes(attrs);
    }

    // Set atributtes of XML to View
    protected void setAttributes(AttributeSet attrs) {
        setMinimumHeight(dpToPx(32, getResources()));
        setMinimumWidth(dpToPx(32, getResources()));

        //Set background Color
        // Color by resource
        int backgroundColor = attrs.getAttributeResourceValue(ANDROID_XML, "background", -1);
        if (backgroundColor != -1) {
            setBackgroundColor(getResources().getColor(backgroundColor));
        } else {
            // Color by hexadecimal
            int background = attrs.getAttributeIntValue(ANDROID_XML, "background", -1);
            if (background != -1) {
                setBackgroundColor(background);
            } else {
                setBackgroundColor(Color.parseColor("#1E88E5"));
            }
        }

        setMinimumHeight(dpToPx(3, getResources()));
    }

    /**
     * Make a dark color to ripple effect
     *
     * @return press color
     */
    protected int makePressColor() {
        int r = (this.mBackgroundColor >> 16) & 0xFF;
        int g = (this.mBackgroundColor >> 8) & 0xFF;
        int b = (this.mBackgroundColor) & 0xFF;
        return Color.argb(128, r, g, b);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (!mFirstAnimationOver) {
            drawFirstAnimation(canvas);
        }
        if (mCont > 0) {
            drawSecondAnimation(canvas);
        }
        invalidate();
    }


    /**
     * Draw first animation of view
     *
     * @param canvas draw image
     */
    private void drawFirstAnimation(Canvas canvas) {
        if (mRadius1 < getWidth() / 2) {
            Paint paint = new Paint();
            paint.setAntiAlias(true);
            paint.setColor(makePressColor());
            mRadius1 = (mRadius1 >= getWidth() / 2) ? (float) getWidth() / 2 : mRadius1 + 1;
            canvas.drawCircle(getWidth() / 2, getHeight() / 2, mRadius1, paint);
        } else {
            Bitmap bitmap = Bitmap.createBitmap(canvas.getWidth(), canvas.getHeight(), Bitmap.Config.ARGB_8888);
            Canvas temp = new Canvas(bitmap);
            Paint paint = new Paint();
            paint.setAntiAlias(true);
            paint.setColor(makePressColor());
            temp.drawCircle(getWidth() / 2, getHeight() / 2, getHeight() / 2, paint);
            Paint transparentPaint = new Paint();
            transparentPaint.setAntiAlias(true);
            transparentPaint.setColor(getResources().getColor(android.R.color.transparent));
            transparentPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.CLEAR));
            if (mCont >= 50) {
                mRadius2 = (mRadius2 >= getWidth() / 2) ? (float) getWidth() / 2 : mRadius2 + 1;
            } else {
                mRadius2 = (mRadius2 >= getWidth() / 2 - dpToPx(4, getResources())) ? (float) getWidth() / 2 - dpToPx(4, getResources()) : mRadius2 + 1;
            }
            temp.drawCircle(getWidth() / 2, getHeight() / 2, mRadius2, transparentPaint);
            canvas.drawBitmap(bitmap, 0, 0, new Paint());
            if (mRadius2 >= getWidth() / 2 - dpToPx(4, getResources()))
                mCont++;
            if (mRadius2 >= getWidth() / 2)
                mFirstAnimationOver = true;
        }
    }


    /**
     * Draw second animation of view
     *
     * @param canvas draw image
     */
    private void drawSecondAnimation(Canvas canvas) {
        canvas.drawColor(Color.TRANSPARENT);
        Paint p = new Paint();
        p.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.CLEAR));
        canvas.drawPaint(p);

        if (mArcO == mLimit)
            mArcD += 6;
        if (mArcD >= 290 || mArcO > mLimit) {
            mArcO += 6;
            mArcD -= 6;
        }
        if (mArcO > mLimit + 290) {
            mLimit = mArcO;
            mArcO = mLimit;
            mArcD = 1;
        }
        mRotateAngle += 4;
        canvas.rotate(mRotateAngle, getWidth() / 2, getHeight() / 2);

        Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setColor(mBackgroundColor);
        canvas.drawArc(new RectF(0, 0, getWidth(), getHeight()), mArcO, mArcD, true, paint);
        Paint transparentPaint = new Paint();
        transparentPaint.setAntiAlias(true);
        transparentPaint.setColor(getResources().getColor(android.R.color.transparent));
        transparentPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.CLEAR));
        canvas.drawCircle(getWidth() / 2, getHeight() / 2, (getWidth() / 2) - dpToPx(4, getResources()), transparentPaint);
    }


    // Set color of background
    public void setBackgroundColor(int color) {
        super.setBackgroundColor(getResources().getColor(android.R.color.transparent));
        if (isEnabled())
            beforeBackground = mBackgroundColor;
        this.mBackgroundColor = color;
    }

    /**
     * Convert Dp to Pixel
     */
    public int dpToPx(float dp, Resources resources) {
        float px = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, resources.getDisplayMetrics());
        return (int) px;
    }
}

