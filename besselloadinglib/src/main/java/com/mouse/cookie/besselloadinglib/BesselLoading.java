package com.mouse.cookie.besselloadinglib;

import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by cookie on 2016/3/8.
 */
public class BesselLoading extends View {

    private final static int CIRCLE_ONE = 120, CIRCLE_TWO = 240, CIRCLE_THREE = 360;

    private Paint mPaint;

    private int floatY;
    //
    private float mRadius;
    private float mRadiusAnimation;
    private int mColor;
    private long mDuration;
    //
    private float floatX;

    private Path mPath;

    public BesselLoading(Context context) {
        this(context, null);
    }

    public BesselLoading(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public BesselLoading(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        //从xml文件中获取数据
        TypedArray mTypedArray = context.obtainStyledAttributes(attrs, R.styleable.BesselLoading);

        int count = mTypedArray.getIndexCount();
        for (int i = 0; i < count; i++){
            int attr = mTypedArray.getIndex(i);
            if (R.styleable.BesselLoading_radius == attr){
                mRadius = mTypedArray.getDimensionPixelSize(attr, 20);
            }else if (R.styleable.BesselLoading_circlecolor == attr){
                mColor = mTypedArray.getColor(attr, 0xff00dddd);
            }else if (R.styleable.BesselLoading_duration == attr){
                mDuration = mTypedArray.getInt(attr, 2000);
            }
        }

        mTypedArray.recycle();

        initialize();
    }

    //初始化
    private void initialize() {

        mPaint = new Paint();
        mPaint.setColor(mColor);
        mPaint.setAntiAlias(true);

        //路径
        mPath = new Path();

        floatY = 50;
        mRadiusAnimation = mRadius * 0.9f;

        ValueAnimator valueAnimator = ValueAnimator.ofFloat(50, 430);
        valueAnimator.setDuration(mDuration);
        valueAnimator.setRepeatMode(ValueAnimator.REVERSE);
        valueAnimator.setRepeatCount(ValueAnimator.INFINITE);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                floatX = (float) animation.getAnimatedValue();
                postInvalidate();
            }
        });
        valueAnimator.start();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        int mWidth;
        int mHeight;

        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);

        if (widthMode == MeasureSpec.EXACTLY) {
            mWidth = widthSize;
        } else {
            mWidth = getPaddingLeft() + 480 + getPaddingRight();
        }

        if (heightMode == MeasureSpec.EXACTLY) {
            mHeight = heightSize;
        } else {
            mHeight = getPaddingTop() + 100 + getPaddingBottom();
        }

        setMeasuredDimension(mWidth, mHeight);
    }

    @Override
    protected void onDraw(final Canvas canvas) {

        drawCircle(canvas);

        drawAnimationCircle(canvas);

        drawPath(canvas);

        super.onDraw(canvas);
    }

    //画3个圆
    private void drawCircle(Canvas canvas) {
        canvas.drawCircle(CIRCLE_ONE, floatY, mRadius, mPaint);

        canvas.drawCircle(CIRCLE_TWO, floatY, mRadius, mPaint);

        canvas.drawCircle(CIRCLE_THREE, floatY, mRadius, mPaint);
    }

    //画运动点
    private void drawAnimationCircle(Canvas canvas) {
        canvas.drawCircle(floatX, floatY, mRadiusAnimation, mPaint);
    }
    //画贝塞尔曲线
    private void drawPath(Canvas canvas) {

        float y_animation = floatY - mRadiusAnimation + 1;
        float y_point = floatY - mRadius;

        float middleX1 = CIRCLE_ONE + (floatX - CIRCLE_ONE) / 2;
        float middleX2 = CIRCLE_TWO + (floatX - CIRCLE_TWO) / 2;
        float middleX3 = CIRCLE_THREE + (floatX - CIRCLE_THREE) / 2;

        if (floatX < 190) {

            float x = floatX - CIRCLE_ONE;
            if (x < 0) {
                x = -x;
            }
            float y1 = 30 + (x / 3);
            float y2 = 70 - (x / 3);

            //画定点圆
            float radiusX = mRadius + 4 - (x / 17);
            canvas.drawCircle(CIRCLE_ONE, floatY, radiusX, mPaint);

            mPath.moveTo(CIRCLE_ONE, y_point);

            mPath.quadTo(middleX1, y1, floatX, y_animation);

            mPath.lineTo(floatX, floatY + mRadiusAnimation - 1);

            mPath.quadTo(middleX1, y2, CIRCLE_ONE, floatY + mRadius);

            mPath.close();

            canvas.drawPath(mPath, mPaint);
        }
        if (floatX > 170 && floatX < 310) {
            float x = floatX - CIRCLE_TWO;
            if (x < 0) {
                x = -x;
            }
            float y1 = 30 + (x / 3);
            float y2 = 70 - (x / 3);

            //画定点圆
            float radiusX = mRadius + 4 - (x / 17);
            canvas.drawCircle(CIRCLE_TWO, floatY, radiusX, mPaint);

            mPath.moveTo(CIRCLE_TWO, y_point);

            mPath.quadTo(middleX2, y1, floatX, y_animation);

            mPath.lineTo(floatX, floatY + mRadiusAnimation - 1);

            mPath.quadTo(middleX2, y2, CIRCLE_TWO, floatY + mRadius);

            mPath.close();

            canvas.drawPath(mPath, mPaint);
        }

        if (floatX > 290) {
            float x = floatX - CIRCLE_THREE;
            if (x < 0) {
                x = -x;
            }
            float y1 = 30 + (x / 3);
            float y2 = 70 - (x / 3);

            //画定点圆
            float radiusX = mRadius + 4 - (x / 17);
            canvas.drawCircle(CIRCLE_THREE, floatY, radiusX, mPaint);

            mPath.moveTo(CIRCLE_THREE, y_point);

            mPath.quadTo(middleX3, y1, floatX, y_animation);

            mPath.lineTo(floatX, floatY + mRadiusAnimation - 1);

            mPath.quadTo(middleX3, y2, CIRCLE_THREE, floatY + mRadius);

            mPath.close();

            canvas.drawPath(mPath, mPaint);
        }

        mPath.reset();
    }
}
