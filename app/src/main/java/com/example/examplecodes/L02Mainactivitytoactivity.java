package com.example.examplecodes;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.examplecodes.L02Page2Activity;
import com.example.examplecodes.R;
import com.example.examplecodes.databinding.ActivityMainactivitytoactivityBinding;

public class L02Mainactivitytoactivity extends AppCompatActivity implements View.OnClickListener {
     ActivityMainactivitytoactivityBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_mainactivitytoactivity);
        binding= DataBindingUtil.setContentView(this,R.layout.activity_mainactivitytoactivity);
        binding.setLifecycleOwner(this);
        binding.btnToppage2.setOnClickListener(this);
        binding.btnToAnotherWithPara.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        if(view.getId()==binding.btnToppage2.getId()) {
            Intent intent = new Intent(this, L02Page2Activity.class);
            startActivityForResult(intent,475);
            return;
        }
        if(view.getId()==binding.btnToAnotherWithPara.getId()){
            Intent intent=new Intent(this, L02Page2Activity.class);
            intent.putExtra("name",binding.txtname.getText().toString());
            startActivity(intent);
            return;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        int key1= data.getIntExtra("key1",-1);
        String key2=data.getStringExtra("key2");
        Toast.makeText(this,key1+key2+" "+requestCode+":"+resultCode,Toast.LENGTH_SHORT).show();
    }
}
