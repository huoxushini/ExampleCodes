package com.example.examplecodes;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.NonNull;

import java.util.HashMap;
import java.util.LinkedList;

public class L04MyView extends View {

    public LinkedList<LinkedList<HashMap<String,Float>>> lines=new LinkedList<>();
    private Paint paint;
    public L04MyView(Context context, @NonNull AttributeSet attrs) {
        super(context,attrs);
        setBackgroundColor(Color.GREEN);
        paint=new Paint();
        paint.setColor(Color.BLUE);
        paint.setStrokeWidth(4);
        setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.v("liuyu","Click");
            }
        });
    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        //Log.d("liuyu","touch");
        //Toast.makeText(getContext(),"touch",Toast.LENGTH_SHORT).show();
        float ex=event.getX();
        float ey=event.getY();
        //打开相应onClick
        super.onTouchEvent(event);
        Log.v("liuyu",ex+":"+ey);

        if(event.getAction()==MotionEvent.ACTION_DOWN ) {
            Log.v("liuyu","down");
            setFirstPoint(event);
        }
        if(event.getAction()==MotionEvent.ACTION_MOVE ) {
            setMovePoint(event);
            Log.v("liuyu","move");
        }
        if(event.getAction()==MotionEvent.ACTION_UP ) {
            Log.v("liuyu","up");
        }
        return  true;
    }
    public void Clearlines()
    {
        lines.clear();
        invalidate();
    }
    public  void Undo()
    {
        if(lines.size()==0) {
            return;
        }
        lines.removeLast();
        invalidate();
    }
    private void setFirstPoint(MotionEvent event)
    {
        LinkedList<HashMap<String,Float>> line=new LinkedList<>();
        float ex=event.getX();
        float ey=event.getY();
        HashMap<String,Float> point=new HashMap<>();
        point.put("x",ex);
        point.put("y",ey);
        line.add(point);
        lines.add(line);
    }
    private void setMovePoint(MotionEvent event){
        float ex=event.getX();
        float ey=event.getY();
        HashMap<String,Float> point=new HashMap<>();
        point.put("x",ex);
        point.put("y",ey);
        lines.getLast().add(point);
        invalidate();
    }
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        for(LinkedList<HashMap<String,Float>> line:lines) {
            for (int i = 1; i < line.size(); i++) {
                HashMap<String, Float> p0 = line.get(i - 1);
                HashMap<String, Float> p1 = line.get(i);
                canvas.drawLine(p0.get("x"), p0.get("y"), p1.get("x"), p1.get("y"), paint);
            }
        }

    }
}
