package com.example.examplecodes;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;
import android.view.View;

import com.example.examplecodes.R;
import com.example.examplecodes.databinding.ActivityMyBinding;

public class L04MyActivity extends AppCompatActivity {
    public ActivityMyBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_my);
        binding= DataBindingUtil.setContentView(this,R.layout.activity_my);
        binding.setLifecycleOwner(this);
    }

    public void onClick(View view) {
        //binding.myview.Clearlines();
        if(view.getId()==binding.btnClear.getId()) {
            binding.myView.Clearlines();
            return;
        }
        if(view.getId()==binding.btnUndo.getId())
        {
            binding.myView.Undo();
            return;
        }

    }
}
