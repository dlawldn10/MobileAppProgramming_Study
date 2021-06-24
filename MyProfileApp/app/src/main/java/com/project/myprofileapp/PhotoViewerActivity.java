package com.project.myprofileapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.File;

public class PhotoViewerActivity extends AppCompatActivity {
    Button btnPrev, btnNext, btnSelect;

    myPictureView myPicture;
    int curNum=0;
    File[] imageFiles;
    String imageFname;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo_viewer);

        ActivityCompat.requestPermissions(this, new String[]{
                Manifest.permission.WRITE_EXTERNAL_STORAGE}, MODE_PRIVATE);
        btnPrev = findViewById(R.id.btnPrev);
        btnNext = findViewById(R.id.btnNext);
        btnSelect = findViewById(R.id.SelectPhotoBttn);
        myPicture = findViewById(R.id.myPictureView1);

        imageFiles = new File(Environment.getExternalStorageDirectory().getAbsolutePath()
                +"/Pictures").listFiles();

        imageFname = imageFiles[0].toString();
        myPicture.imagePath = imageFname;

        Intent intent = getIntent();
        String[] dataArray = intent.getStringArrayExtra("dataArray");

        //이전 버튼
        btnPrev.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                if(curNum == 0){
                    curNum=imageFiles.length-2; //imageFiles.length-1 은 .thumbnail 이라는 파일임. 숨김파일인듯.
                    imageFname = imageFiles[curNum].toString();
                    myPicture.imagePath=imageFname;
                    Log.d("테스트2", imageFname);
                    myPicture.invalidate();
                }else{
                    curNum--;
                    imageFname = imageFiles[curNum].toString();
                    myPicture.imagePath=imageFname;
                    myPicture.invalidate();
                }
            }
        });

        //다음 버튼
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(curNum == imageFiles.length-2){
                    curNum=0;
                    imageFname = imageFiles[curNum].toString();
                    myPicture.imagePath=imageFname;
                    myPicture.invalidate();
//                    Log.d("테스트", curNum+" ");
                }else{
                    curNum++;
                    imageFname = imageFiles[curNum].toString();
                    myPicture.imagePath=imageFname;
                    myPicture.invalidate();
//                    Log.d("테스트", curNum+" ");
                }
            }
        });

        //선택 버튼
        btnSelect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imageFname = imageFiles[curNum].toString();
                Intent intent2;
                intent2 = new Intent(getApplicationContext(), MainActivity.class);
                intent2.putExtra("Fname", imageFname);
                intent2.putExtra("dataArray", dataArray);
                startActivity(intent2);
                finish();
            }
        });
    }
}