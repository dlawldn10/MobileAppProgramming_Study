package com.project.myalbum;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;

import java.io.File;

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
        myPicture.imagePath = imageFname;

        btnPrev.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                if(curNum <= 1){
                    curNum=imageFiles.length-1;
                    imageFname = imageFiles[curNum].toString();
                    myPicture.imagePath=imageFname;
                    myPicture.invalidate();
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
                    curNum=1;
                    imageFname = imageFiles[curNum].toString();
                    myPicture.imagePath=imageFname;
                    myPicture.invalidate();
                }else{
                    curNum++;
                    imageFname = imageFiles[curNum].toString();
                    myPicture.imagePath=imageFname;
                    myPicture.invalidate();
                }
            }
        });

    }
}