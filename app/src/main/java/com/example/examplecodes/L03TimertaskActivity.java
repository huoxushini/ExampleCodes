package com.example.examplecodes;

import androidx.annotation.NonNull;
import androidx.annotation.UiThread;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.examplecodes.R;
import com.example.examplecodes.databinding.ActivityTimertaskBinding;

import java.util.Timer;
import java.util.TimerTask;

public class L03TimertaskActivity extends AppCompatActivity {
    private Timer timer;
    ActivityTimertaskBinding binding;
    private Task1 task1;
    private UIhandler uIhandler=new UIhandler();
    private int counter=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_timertask);
        binding= DataBindingUtil.setContentView(this,R.layout.activity_timertask);
        binding.setLifecycleOwner(this);

    }

    @Override
    protected void onStart() {

        super.onStart();
        timer=new Timer();
    }

    @Override
    protected void onPause() {
        super.onPause();
        if(timer!=null){
            timer.cancel();
            timer.purge();
            timer=null;
        }
    }

    public void onClick(View view) {
        if(view.getId()==binding.btnStart.getId()) {
            Toast.makeText(this, "TimerTask", Toast.LENGTH_SHORT).show();
            timer.schedule(new Task1(), 3000, 1000);
            return;
        }
        if(view.getId()==binding.btnbegin.getId()){
            task1=new Task1();
            timer.schedule(task1,0,3000);
            return;
        }
        if(view.getId()==binding.btnstop.getId()){
            task1.cancel();
            task1=null;
            return;
        }


    }

    private class Task1 extends TimerTask{

        @Override
        public void run() {
            Log.v("liuyu","TimerTask");
            counter++;
            //Message message=new Message();
            //Bundle data=new Bundle();
            //data.putInt("counter",counter);
            //message.setData(data);
            //uIhandler.sendMessage(message);
            uIhandler.sendEmptyMessage(0);
        }
    }

    private class UIhandler extends Handler {
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            //int dataInt=msg.getData().getInt("counter");

            //binding.txtResult.setText(""+dataInt);
            if(msg.what==0){
                binding.txtResult.setText(""+counter);
            }
        }
    }
}
