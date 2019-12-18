package com.example.examplecodes;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.examplecodes.R;
import com.example.examplecodes.databinding.ActivityPage2Binding;

public class L02Page2Activity extends AppCompatActivity implements View.OnClickListener {
    ActivityPage2Binding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_page2);
        binding= DataBindingUtil.setContentView(this,R.layout.activity_page2);
        binding.setLifecycleOwner(this);
        binding.btnback.setOnClickListener(this);
        Intent intent=getIntent();
        if(intent.hasExtra("name")) {
            Toast.makeText(this,intent.getStringExtra("name"),Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onClick(View view) {
        finish();
    }

    @Override
    public void finish() {
        //设定的值会返回
        Intent intent =new Intent();
        intent.putExtra("key1",123);
        intent.putExtra("key2","brad");
        setResult(327,intent);
        super.finish();
    }
}
