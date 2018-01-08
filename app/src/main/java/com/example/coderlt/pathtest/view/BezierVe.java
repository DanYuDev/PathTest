package com.example.coderlt.pathtest.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by coderlt on 2017/12/25.
 */

public class BezierVe extends View {
    private int mWidth,mHeight;
    private Paint pointPaint,linePaint,pathPaint;
    private Path  bezierPath;
    private float mRadius=200f;
    private float mDiff;
    //private final float mDuration=2000;
    private int mCount;
    //当前进行时长
    private float mCurrent;

    private static final float C=0.551915024494f;
    private float[] mDatas=new float[8];
    private float[] mCtrs=new float[16];

    public BezierVe(Context context, AttributeSet attrs){
        super(context,attrs);
        init();
    }

    public BezierVe(Context context){
        this(context,null);
    }

    //init the data points and the control points.
    private void init(){
        // init the data points.
        mDatas[0]=0;
        mDatas[1]=mRadius;

        mDatas[2]=mRadius;
        mDatas[3]=0;

        mDatas[4]=0;
        mDatas[5]=-mRadius;

        mDatas[6]=-mRadius;
        mDatas[7]=0;

        mDiff=C*mRadius;
        //init the ctr points.-------------------------------------
        mCtrs[0]=mDiff;
        mCtrs[1]=mRadius;

        mCtrs[2]=mRadius;
        mCtrs[3]=mDiff;

        mCtrs[4]=mRadius;
        mCtrs[5]=-mDiff;

        mCtrs[6]=mDiff;
        mCtrs[7]=-mRadius;

        mCtrs[8]=-mDiff;
        mCtrs[9]=-mRadius;

        mCtrs[10]=-mRadius;
        mCtrs[11]=-mDiff;

        mCtrs[12]=-mRadius;
        mCtrs[13]=mDiff;

        mCtrs[14]=-mDiff;
        mCtrs[15]=mRadius;

        mCurrent=0;
        mCount=0;
    }

    @Override
    protected void onSizeChanged(int w,int h,int oldW,int oldH){
        mWidth=w;
        mHeight=h;

        pointPaint=new Paint();
        pointPaint.setColor(Color.BLUE);
        pointPaint.setStrokeWidth(16);

        linePaint=new Paint();
        linePaint.setStrokeWidth(10);
        linePaint.setColor(Color.GRAY);
    }

    @Override
    protected void onDraw(Canvas canvas){

        canvas.translate(mWidth/2,mHeight/2);
        canvas.scale(1,-1);
        changeLocation();

        //  Draw...
        //canvas.drawPoints(mDatas,pointPaint);
        //canvas.drawPoints(mCtrs,pointPaint);

        drawBezierPath(canvas);
        //drawLine(canvas);

        //mCurrent+=100;
        if(mCount<=20){
            mCount++;
            postInvalidateDelayed(100);
        }
    }

    private void changeLocation(){
        mDatas[1]-=7;
        mCtrs[4]-=2;
        //mCtrs[5]-=4;
        //mCtrs[6]+=4;
        mCtrs[7]+=4;
        //mCtrs[8]-=4;
        mCtrs[9]+=4;
        mCtrs[10]+=2;
        //mCtrs[11]-=4;
    }

    private void drawLine(Canvas canvas){
        for(int i=0;i<4;i++){
            //  第二行的模是对边界条件的特殊处理
            canvas.drawLine(mDatas[i*2],mDatas[i*2+1],mCtrs[i*4],mCtrs[4*i+1],linePaint);
            canvas.drawLine(mDatas[i*2],mDatas[i*2+1],mCtrs[(i*4-2+16)%16],mCtrs[(4*i-1+16)%16],linePaint);
        }
    }

    private void drawBezierPath(Canvas canvas){
        pathPaint=new Paint();
        pathPaint.setColor(Color.RED);
        pathPaint.setStyle(Paint.Style.STROKE);
        pathPaint.setStrokeWidth(8);
        bezierPath=new Path();

        bezierPath.moveTo(mDatas[0],mDatas[1]);
        bezierPath.cubicTo(mCtrs[0],mCtrs[1],mCtrs[2],mCtrs[3],mDatas[2],mDatas[3]);
        bezierPath.cubicTo(mCtrs[4],mCtrs[5],mCtrs[6],mCtrs[7],mDatas[4],mDatas[5]);
        bezierPath.cubicTo(mCtrs[8],mCtrs[9],mCtrs[10],mCtrs[11],mDatas[6],mDatas[7]);
        bezierPath.cubicTo(mCtrs[12],mCtrs[13],mCtrs[14],mCtrs[15],mDatas[0],mDatas[1]);
        canvas.drawPath(bezierPath,pathPaint);
    }


}
