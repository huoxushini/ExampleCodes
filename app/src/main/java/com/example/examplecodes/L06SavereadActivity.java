package com.example.examplecodes;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.examplecodes.databinding.ActivityMyBinding;
import com.example.examplecodes.databinding.ActivitySavereadBinding;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import static android.os.Environment.getExternalStorageDirectory;

public class L06SavereadActivity extends AppCompatActivity {
    ActivitySavereadBinding binding;
    private SharedPreferences sp;
    private SharedPreferences.Editor editor;
    private File sdroot;
    private File approot;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_saveread);
        binding= DataBindingUtil.setContentView(this,R.layout.activity_saveread);
        binding.setLifecycleOwner(this);
        init();
    }

    public  void init(){
        sp=getSharedPreferences("game",MODE_PRIVATE);
        editor=sp.edit();

        sdroot= getExternalStorageDirectory();
        Log.v("liuyu","approot:"+getExternalFilesDir(null));
        Log.v("liuyu","sdroot:"+getExternalStorageDirectory());
        //approot=new File(sdroot,"Android/data/"+getPackageName()+"/");

        approot=getExternalFilesDir(null);
        if(!approot.exists()){
            if(approot.mkdir()){
                Log.v("liuyu","mkdir OK");
            }
            else {
                Log.v("liuyu","mkdir NO OK");
            }
        }
    }
    public void onClick(View view) {
        if(view.getId()==binding.btnsave.getId())
        {
            editor.putString("username","user");
            editor.putBoolean("sound",false);
            editor.putInt("stage",7);
            editor.commit();
            Toast.makeText(this,"Save Ok",Toast.LENGTH_SHORT).show();
            return;
        }
        if(view.getId()==binding.btnload.getId()){
            String username=sp.getString("username","liuyu");
            boolean isSound=sp.getBoolean("sound",true);
            int stage=sp.getInt("stage",4);
            Log.v("liuyu",username+" "+isSound+" "+stage);

            return;
        }
        if(view.getId()==binding.btnsavefile.getId()){
            try {
                FileOutputStream fout = openFileOutput("data1.txt", MODE_APPEND);
                fout.write("Hello,RY\n".getBytes());
                fout.flush();
                fout.close();
                Toast.makeText(this,"Save Ok",Toast.LENGTH_SHORT).show();
            }
            catch (IOException e) {
                Toast.makeText(this,"Save ERROR",Toast.LENGTH_SHORT).show();
            }
            return;
        }
        if(view.getId()==binding.btnloadfile.getId()){
            try {
                FileInputStream fin=openFileInput("data1.txt");
                BufferedReader br=new BufferedReader((new InputStreamReader(fin)));
                String line;
                while ((line=br.readLine())!=null) Log.v("liuyu", line);
                fin.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return;
        }
        if(view.getId()==binding.btntoextern.getId())
        {
            File file1=new File(approot,"test5.txt");
            try {
                FileOutputStream fout=new FileOutputStream(file1);
                fout.write("OK1".getBytes());
                fout.flush();
                fout.close();
                Toast.makeText(this,"Save Ok",Toast.LENGTH_SHORT).show();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return;
        }
        if(view.getId()==binding.btnfromex.getId()){
            File file1=new File(approot,"test5.txt");
            try {
                BufferedReader br=new BufferedReader(new InputStreamReader(new FileInputStream(file1)));
                String line;
                StringBuffer sb=new StringBuffer();
                while ((line=br.readLine())!=null){
                    sb.append(line+"\n");
                }
                binding.txtmsg.setText(sb);
                Toast.makeText(this,"Load Ok",Toast.LENGTH_SHORT).show();
                br.close();
            } catch (IOException e) {
                Toast.makeText(this,"Load No Ok",Toast.LENGTH_SHORT).show();
            }
            return;
        }
    }
}
