package com.project.myprofileapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.SubMenu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {

    EditText nameEditText, emailEditText, phoneNumEditText, addressEditText, portfolioEditText;
    setProfileView myPicture;
    Button searchBttn, LinkBttn, gotoPaintBttn;


    TextView title;
    CardView cardView;
    LinearLayout mainlayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("마이페이지");


        title = findViewById(R.id.title_MemberInit);
        cardView = findViewById(R.id.profileCardView);
        mainlayout = findViewById(R.id.mainLayout);

        nameEditText = findViewById(R.id.NameEditText);
        emailEditText = findViewById(R.id.EmailEditText);
        phoneNumEditText = findViewById(R.id.PhoneEditText);
        addressEditText = findViewById(R.id.PostalAddressEditText);
        portfolioEditText = findViewById(R.id.PortfolioEditText);
        myPicture = findViewById(R.id.setProfileView);
        searchBttn = findViewById(R.id.SearchBttn);
        LinkBttn = findViewById(R.id.webLinkBttn);
        gotoPaintBttn = findViewById(R.id.gotoPaintBttn);



        String[] dataArray = {"", "", "", "", ""};

        //다른 액티비티에서 intent가 있다면
        if(getIntent().getStringArrayExtra("dataArray") != null){
            Intent intent = getIntent();
            String[] post_dataArray = intent.getStringArrayExtra("dataArray");
            String imageFname = intent.getStringExtra("Fname");
            
            myPicture.imagePath = imageFname;
            myPicture.invalidate();

            nameEditText.setText(post_dataArray[0]);
            emailEditText.setText(post_dataArray[1]);
            phoneNumEditText.setText(post_dataArray[2]);
            addressEditText.setText(post_dataArray[3]);
            portfolioEditText.setText(post_dataArray[4]);

        }else{
            //다른 액티비티에서 intent가 없다면 = 앱 최초 실행인 경우.
            nameEditText.setText(LoadMyData("name", 50));
            emailEditText.setText(LoadMyData("email", 50));
            phoneNumEditText.setText(LoadMyData("phone", 50));
            portfolioEditText.setText(LoadMyData("url", 50));
            addressEditText.setText(LoadMyData("address", 50));
        }

        //프로필 사진 버튼
        myPicture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dataArray[0] = nameEditText.getText().toString();
                dataArray[1] = emailEditText.getText().toString();
                dataArray[2] = phoneNumEditText.getText().toString();
                dataArray[3] = addressEditText.getText().toString();
                dataArray[4] = portfolioEditText.getText().toString();

                Intent intent;
                intent = new Intent(getApplicationContext(), PhotoViewerActivity.class);
                intent.putExtra("dataArray", dataArray);
                startActivity(intent);
                finish();
            }
        });

        //주소 검색 버튼
        searchBttn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String str= addressEditText.getText().toString();
                Intent intent;
                intent = new Intent(getApplicationContext(), GoogleMapActivity.class);
                intent.putExtra("address", str);
                startActivity(intent);
            }
        });

        //링크 바로가기 버튼
        LinkBttn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent;
                intent = new Intent(getApplicationContext(), WebViewActivity.class);
                intent.putExtra("url", portfolioEditText.getText().toString());
                startActivity(intent);
            }
        });


        //그림으로 나를 표현하기 버튼
        gotoPaintBttn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent;
                intent = new Intent(getApplicationContext(), PaintActivity.class);
                startActivity(intent);
            }
        });

    }

    //앱 최초실행 시 내장메모리에서 기본 정보 불러오는 함수
    private String LoadMyData(String fileName, int fileSize){
        String str = "";
        try {
            FileInputStream inFs = openFileInput(fileName + ".txt");
            byte[] txt = new byte[fileSize];
            inFs.read(txt);
            str = new String(txt);
            Toast.makeText(getApplicationContext(), str, Toast.LENGTH_SHORT).show();
            inFs.close();
        } catch (IOException e) {
            Toast.makeText(getApplicationContext(), "파일 없음" , Toast.LENGTH_SHORT).show();
        }


        return str;
    }


}