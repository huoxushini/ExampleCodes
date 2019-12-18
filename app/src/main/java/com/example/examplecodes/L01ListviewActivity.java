package com.example.examplecodes;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import com.example.examplecodes.databinding.ActivityListviewBinding;

import java.util.HashMap;
import java.util.LinkedList;

public class L01ListviewActivity extends AppCompatActivity implements View.OnClickListener {
    private ActivityListviewBinding binding;
    private String[] from={"title"};
    private int[] to={R.id.item_title};
    private  SimpleAdapter adapter;
    private LinkedList<HashMap<String,String>> data=new LinkedList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listview);
        binding= DataBindingUtil.setContentView(this,R.layout.activity_listview);
        binding.setLifecycleOwner(this);
        binding.btnAdd.setOnClickListener(this);
        binding.btnDel.setOnClickListener(this);
        binding.btnBack.setOnClickListener(this);
        initListview();
    }
    //初始化Listview
    private void initListview() {

        HashMap<String,String> d0=new HashMap<>();
        d0.put(from[0],"Test1");
        HashMap<String,String> d1=new HashMap<>();
        d1.put(from[0],"Test2");
        HashMap<String,String> d2=new HashMap<>();
        d2.put(from[0],"Test3");
        data.add(d0);
        data.add(d1);
        data.add(d2);
        adapter=new SimpleAdapter(this,data,R.layout.item,from,to);
        binding.list.setAdapter(adapter);
    }
    @Override
    public void onClick(View view) {


        if(view.getId()==binding.btnBack.getId())
        {
            finish();
            Log.d("liuyu","back to main");
            Intent intent=new Intent(L01ListviewActivity.this, MainActivity.class);
            startActivity(intent);
            return;
        }
        if(view.getId()==binding.btnAdd.getId()) {
            Log.d("liuyu","Add");
            HashMap<String,String> d=new HashMap<>();
            d.put(from[0],"Test"+(binding.list.getCount()+1));
            data.add(d);
            adapter.notifyDataSetChanged();
        }else if(view.getId()==binding.btnDel.getId()) {
            if(data.size()>0)
                data.removeLast();
            adapter.notifyDataSetChanged();
        }
        binding.list.smoothScrollToPosition(data.size()-1);
    }

    @Override
    public void finish() {
        Toast.makeText(this,"Back",Toast.LENGTH_SHORT).show();
        //super.finish();

    }
}
