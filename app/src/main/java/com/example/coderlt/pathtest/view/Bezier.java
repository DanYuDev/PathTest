package com.example.coderlt.pathtest.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PointF;
import android.support.v4.widget.ContentLoadingProgressBar;
import android.util.AttributeSet;
import android.util.EventLog;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by coderlt on 2017/12/24.
 */

public class Bezier extends View {
    private Paint mPaint,linePaint,pointPaint;
    private static final String TAG="Bezier";
    private int mWidth,mHeight;
    private PointF start,end,control;

    public Bezier(Context context){
        this(context,null);
    }

    public Bezier(Context context, AttributeSet attrs){
        super(context,attrs);
        init();
    }

    @Override
    protected void onSizeChanged(int w,int h,int oldW,int oldH){
        super.onSizeChanged(w,h,oldW,oldH);
        mWidth=w;
        mHeight=h;
        start=new PointF(mWidth/4,mHeight/2);
        end=new PointF(mWidth*3/4,mHeight/2);
        control=new PointF(mWidth/2,mHeight/2);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        Path bezierPath=new Path();
        pointPaint=new Paint();
        pointPaint.setStrokeWidth(12);
        pointPaint.setColor(Color.BLUE);
        linePaint=new Paint();
        linePaint.setColor(Color.GRAY);
        canvas.drawPoints(new float[]{start.x,start.y,end.x,end.y,control.x,control.y},pointPaint);
        canvas.drawLine(start.x,start.y,control.x,control.y,linePaint);
        canvas.drawLine(control.x,control.y,end.x,end.y,linePaint);

        bezierPath.moveTo(start.x,start.y);
        bezierPath.quadTo(control.x,control.y,end.x,end.y);
        canvas.drawPath(bezierPath,mPaint);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event){
        control.set(event.getX(),event.getY());
        invalidate();
        return true;
    }

    private void init(){
        mPaint=new Paint();
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setAntiAlias(true);
        mPaint.setStrokeWidth(5);
        mPaint.setColor(Color.RED);
    }
}
