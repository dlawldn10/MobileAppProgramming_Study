package com.project.project8_2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Context;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    Button btnPrev, btnNext;

    myPictureView myPicture;
    int curNum=1;
    File[] imageFiles;
    String imageFname;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("간단 이미지 뷰어");

        ActivityCompat.requestPermissions(this, new String[]{
                Manifest.permission.WRITE_EXTERNAL_STORAGE}, MODE_PRIVATE);
        btnPrev = findViewById(R.id.btnPrev);
        btnNext = findViewById(R.id.btnNext);
        myPicture = findViewById(R.id.myPictureView1);

        imageFiles = new File(Environment.getExternalStorageDirectory().getAbsolutePath()
                +"/Pictures").listFiles();

        imageFname = imageFiles[1].toString();
        Log.d("테스트", imageFname);
        myPicture.imagePath = imageFname;

        btnPrev.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                if(curNum <= 1){
                    Toast.makeText(getApplicationContext(), "첫번째 그림입니다", Toast.LENGTH_SHORT).show();;
                }else{
                    curNum--;
                    imageFname = imageFiles[curNum].toString();
                    myPicture.imagePath=imageFname;
                    myPicture.invalidate();
                }
            }
        });

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(curNum >= imageFiles.length-1){
                    Toast.makeText(getApplicationContext(), "마지막 그림입니다", Toast.LENGTH_SHORT).show();
                }else{
                    curNum++;
                }
            }
        });

    }
}