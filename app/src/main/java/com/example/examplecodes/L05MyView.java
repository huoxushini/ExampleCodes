package com.example.examplecodes;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

import java.util.Timer;
import java.util.TimerTask;

public class L05MyView extends View {
    private Bitmap ball;
    private boolean ispostback;
    private  int viewW,viewH;
    private  int ballX,ballY,dx,dy;
    private Timer timer;
    private float ballW,ballH;
    public L05MyView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        ball= BitmapFactory.decodeResource(context.getResources(),R.drawable.volleyball);
        timer=new Timer();
    }
    private void init(){
        viewW=getWidth();
        viewH=getHeight();
        ballW=viewW/ 8f;
        ballH=ballW;
        Matrix matrix=new Matrix();
        matrix.postScale(ballW/ball.getWidth(),ballH/ball.getHeight());
        ball=Bitmap.createBitmap(ball,0,0,ball.getWidth(),ball.getHeight(),matrix,false);
        dx=8;
        dy=8;
        timer.schedule(new BallTask(),1000,10);
    }
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if(!ispostback) {
            init();
            ispostback=true;
        }
        canvas.drawBitmap(ball,ballX,ballY,null);
    }
    private  class BallTask extends TimerTask{

        @Override
        public void run() {
            if(ballX<0||ballX+ballW>viewW) {
                dx*=-1;
            }
            if(ballY<0||ballY+ballH>viewH) {
                dy*=-1;
            }
            ballX+=dx;
            ballY+=dy;

            postInvalidate();
        }
    }
}
