package com.example.finalfunction;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.drawable.Drawable;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.view.View;

/**
 * TODO: document your custom view class.
 */
public class WeatherChartView extends View {
    private String mExampleString; // TODO: use a default from R.string...
    private int mExampleColor1 = Color.BLACK; // TODO: use a default from R.color...
    private int mExampleColor2 = Color.RED; // TODO: use a default from R.color...
    private float mExampleDimension = 0; // TODO: use a default from R.dimen...
    private Drawable mExampleDrawable;
    private TextPaint mTextPaint;
    private float mTextWidth;
    private float mTextHeight;
    private float mXAxis[] = new float[10];
    private float mYAxisDay[] = new float[10];
    private float mYAxisNight[] = new float[10];
    private int LENGTH = 7;
    private int mTempDay[] = new int[10];
    private int mTempNight[] = new int[10];
    private int mHeight;
    private float mTextSize;
    private float mRadius;
    private float mRadiusToday;
    private float mTextSpace;
    private float mStokeWidth;
    private int mColorDay;
    private int mColorNight;
    private int mTextColor;
    private float mDensity;
    private float mSpace;
    public WeatherChartView(Context context) {
        super(context);
    }
    public WeatherChartView(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.WeatherChartView);
        float densityText = getResources().getDisplayMetrics().scaledDensity;
        mTextSize = a.getDimensionPixelSize(R.styleable.WeatherChartView_text_size,(int) (14 * densityText));
        mColorDay = mExampleColor1;
        mColorNight = mExampleColor1;
        mTextColor =  mExampleColor2;
        a.recycle();
        mDensity = getResources().getDisplayMetrics().density;
        mRadius = 3 * mDensity;
        mRadiusToday = 5 * mDensity;
        mSpace = 3 * mDensity;
        mTextSpace = 10 * mDensity;
        mStokeWidth = 2 * mDensity;
    }
    public void setLENGTH(int num){
        this.LENGTH=num;
    }
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        if (mHeight == 0) {
            // 设置控件高度，x轴集合
            setHeightAndXAxis();
        }
        // 计算y轴集合数值
        computeYAxisValues();
        // 画白天折线图
        drawChart(canvas, mColorDay, mTempDay, mYAxisDay, 0);
        // 画夜间折线图
        drawChart(canvas, mColorNight, mTempNight, mYAxisNight, 1);
    }
    private void computeYAxisValues() {
        // 存放白天最低温度
        int minTempDay = mTempDay[0];
        // 存放白天最高温度
        int maxTempDay = mTempDay[0];
        for (int item : mTempDay) {
            if (item < minTempDay) {
                minTempDay = item;
            }
            if (item > maxTempDay) {
                maxTempDay = item;
            }
        }
        // 存放夜间最低温度
        int minTempNight = mTempNight[0];
        // 存放夜间最高温度
        int maxTempNight = mTempNight[0];
        for (int item : mTempNight) {
            if (item < minTempNight) {
                minTempNight = item;
            }
            if (item > maxTempNight) {
                maxTempNight = item;
            }
        }

        // 白天，夜间中的最低温度
        int minTemp = minTempNight < minTempDay ? minTempNight : minTempDay;
        // 白天，夜间中的最高温度
        int maxTemp = maxTempDay > maxTempNight ? maxTempDay : maxTempNight;

        // 份数（白天，夜间综合温差）
        float parts = maxTemp - minTemp;
        // y轴一端到控件一端的距离
        float length = mSpace + mTextSize + mTextSpace + mRadius;
        // y轴高度
        float yAxisHeight = mHeight - length * 2;

        // 当温度都相同时（被除数不能为0）
        if (parts == 0) {
            for (int i = 0; i < LENGTH; i++) {
                mYAxisDay[i] = yAxisHeight / 2 + length;
                mYAxisNight[i] = yAxisHeight / 2 + length;
            }
        } else {
            float partValue = yAxisHeight / parts;
            for (int i = 0; i < LENGTH; i++) {
                mYAxisDay[i] = mHeight - partValue * (mTempDay[i] - minTemp) - length;
                mYAxisNight[i] = mHeight - partValue * (mTempNight[i] - minTemp) - length;
            }
        }
    }
    private void drawChart(Canvas canvas, int color, int temp[], float[] yAxis, int type) {
        // 线画笔
        Paint linePaint = new Paint();
        // 抗锯齿
        linePaint.setAntiAlias(true);
        // 线宽
        linePaint.setStrokeWidth(mStokeWidth);
        linePaint.setColor(color);
        // 空心
        linePaint.setStyle(Paint.Style.STROKE);

        // 点画笔
        Paint pointPaint = new Paint();
        pointPaint.setAntiAlias(true);
        pointPaint.setColor(color);

        // 字体画笔
        Paint textPaint = new Paint();
        textPaint.setAntiAlias(true);
        textPaint.setColor(mTextColor);
        textPaint.setTextSize(mTextSize);
        // 文字居中
        textPaint.setTextAlign(Paint.Align.CENTER);

        int alpha1 = 200;
        int alpha2 = 255;
        for (int i = 0; i < LENGTH; i++) {
            // 画线
            if (i < LENGTH - 1) {
                // 昨天
                if (i == 0) {
                    linePaint.setAlpha(alpha1);
                    // 设置虚线效果
//                    linePaint.setPathEffect(new DashPathEffect(new float[]{2 * mDensity, 2 * mDensity}, 0));
                    // 路径
                    Path path = new Path();
                    // 路径起点
                    path.moveTo(mXAxis[i], yAxis[i]);
                    // 路径连接到
                    path.lineTo(mXAxis[i + 1], yAxis[i + 1]);
                    canvas.drawPath(path, linePaint);
                } else {
                    linePaint.setAlpha(alpha1);
                    linePaint.setPathEffect(null);
                    canvas.drawLine(mXAxis[i], yAxis[i], mXAxis[i + 1], yAxis[i + 1], linePaint);
                }
            }

            // 画点
            pointPaint.setAlpha(alpha2);
            canvas.drawCircle(mXAxis[i], yAxis[i], mRadius, pointPaint);

            // 画字

            textPaint.setAlpha(alpha2);
            drawText(canvas, textPaint, i, temp, yAxis, type);
        }
    }
    private void drawText(Canvas canvas, Paint textPaint, int i, int[] temp, float[] yAxis, int type) {
        switch (type) {
            case 0:
                // 显示白天气温
                canvas.drawText(temp[i] + "°", mXAxis[i], yAxis[i] - mRadius - mTextSpace, textPaint);
                break;
            case 1:
                // 显示夜间气温
                canvas.drawText(temp[i] + "°", mXAxis[i], yAxis[i] + mTextSpace + mTextSize, textPaint);
                break;
        }
    }
    private void setHeightAndXAxis() {
        mHeight = getHeight();
        // 控件宽
        int width = getWidth();
        // 每一份宽
        float w = width / 14;
        mXAxis[0] = w;
        mXAxis[1] = w * 2;
        mXAxis[2] = w * 4;
        mXAxis[3] = w * 7;
        mXAxis[4] = w * 9;
        mXAxis[5] = w * 11;
        mXAxis[6] = w * 13;
    }
    public void setTempDay(int[] tempDay) {
        mTempDay = tempDay;
    }
    public void setTempNight(int[] tempNight) {
        mTempNight = tempNight;
    }
}
