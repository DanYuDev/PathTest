package com.example.coderlt.pathtest.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

/**
 * Created by coderlt on 2017/12/16.
 */

public class PathCanvas1 extends View {
    private static final String TAG="PathCanvas";
    private Paint mPaint;
    private int mWidth,mHeight;
    private Path mPath;
    private boolean offset_status=false;
    private Path dst;
    private int count=0;

    public PathCanvas1(Context context){
        this(context,null);
    }

    public PathCanvas1(Context context, AttributeSet attrs){
        super(context,attrs);
        init();
    }

    private void init(){
        mPaint=new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setStrokeWidth(5);
        mPaint.setStyle(Paint.Style.STROKE);
    }

    @Override
    protected void onSizeChanged(int w,int h,int oldW,int oldH){
        mWidth=w;
        mHeight=h;
    }

    @Override
    public void onDraw(Canvas canvas){
        //mPath在这里进行初始化是很有必要的，否则就会每次叠加path，造成路径叠加，反映到这里，可能是曲线运动形成的面积
        mPath=new Path();
        //Log.d(TAG,"Canvas start. and the boolean is "+offset_status);
        canvas.translate(mWidth/2,mHeight/2);
        canvas.scale(1,-1);

        mPath.moveTo(0,0);
        mPath.lineTo(200,200);
        mPath.arcTo(new RectF(0,0,400,400),0,270);
        if(offset_status==true) {
            mPath.offset(50*count, 50*count);
            //Log.e(TAG,"change the path.");
        }
        canvas.drawPath(mPath,mPaint);
        mPaint.setStrokeWidth(2);
        mPaint.setColor(Color.RED);
        //canvas.drawLine(-1000,0,1000,0,mPaint);
        //canvas.drawLine(0,-2000,0,2000,mPaint);
    }

    public void setOffset(){
        Log.d(TAG,"on setoffset");
        offset_status=true;
        count++;
        //不能立即重置 status，因为invalidate()它是异步刷新UI的操作
        invalidate();
        //offset_status=false;
    }
}
