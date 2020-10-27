package com.sunfb.colortracktextview;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.widget.TextView;

import androidx.annotation.Nullable;

@SuppressLint("AppCompatCustomView")
public class ColorTrackTextView extends TextView {
    private int mOriginColor = Color.BLACK;
    private int mChangeColor = Color.RED;
    private Paint mOriginPaint, mChangePaint;
    private float mProgress = 0.0f;
    private Direction mDirection = Direction.LET_TO_RIGHT;

    public ColorTrackTextView(Context context) {
        this(context, null);
    }

    public ColorTrackTextView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ColorTrackTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.ColorTrackTextView);
        mOriginColor = array.getColor(R.styleable.ColorTrackTextView_originColor, mOriginColor);
        mChangeColor = array.getColor(R.styleable.ColorTrackTextView_changeColor, mChangeColor);
        mOriginPaint = getPaint(mOriginColor);
        mChangePaint = getPaint(mChangeColor);
        array.recycle();

    }

    private Paint getPaint(int color) {
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setColor(color);
        paint.setDither(true);
        paint.setTextSize(getTextSize());
        return paint;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        // 注意：重写onDraw方法必须去掉super.onDraw(canvas) 否则会重绘俩次
        // super.onDraw(canvas);
        int middle = (int) (getWidth() * mProgress);
        if (Direction.LET_TO_RIGHT == mDirection) {
            paintRectText(canvas,  0, middle,mChangePaint);
            paintRectText(canvas,  middle, getWidth(),mOriginPaint);
        } else {
            paintRectText(canvas, getWidth() - middle, getWidth(),mChangePaint);
            paintRectText(canvas, 0, getWidth() - middle,mOriginPaint);

        }



    }

    private void paintRectText(Canvas canvas, int start, int end, Paint paint) {
        canvas.save();
        //裁剪画布区域绘制
        canvas.clipRect(start, 0, end, getHeight());
        String text = getText().toString();
        Rect rectBounds = new Rect();
        //基线和起始位置计算
        paint.getTextBounds(text, 0, text.length(), rectBounds);
        Paint.FontMetricsInt fontMetricsInt = paint.getFontMetricsInt();
        int dx = getWidth() / 2 - rectBounds.width() / 2;
        int dy = (fontMetricsInt.bottom - fontMetricsInt.top) / 2 - fontMetricsInt.bottom;
        int baseLine = getHeight() / 2 + dy;
        canvas.drawText(text, dx, baseLine, paint);
        //释放画布
        canvas.restore();
    }

    public void setProgress(float progress) {
        this.mProgress = progress;
        //刷新
        invalidate();
    }

    public void setDirection(Direction direction) {
        mDirection = direction;
    }

    //方向枚举
    public enum Direction {
        //这里的方式是针对前一个控件，而不是当前控件
        LET_TO_RIGHT, RIGHT_TO_LEFT
    }

}
