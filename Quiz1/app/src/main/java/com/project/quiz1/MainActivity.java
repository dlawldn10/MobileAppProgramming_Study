package com.project.quiz1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    TextView text1, text2;
    CheckBox chkAgree;
    RadioGroup rGroup1;
    RadioButton picture1, picture2, picture3, picture4, picture5;
    Button btnOK;
    ImageView imgMe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().setTitle("임지우 사진 보기");
        getSupportActionBar().setIcon(R.mipmap.ic_launcher_jw);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        text1 = findViewById(R.id.Text1);
        chkAgree = findViewById(R.id.ChkAgree);
        text2 = findViewById(R.id.Text2);
        rGroup1 = findViewById(R.id.Rgroup1);
        picture1 = findViewById(R.id.Pic1);
        picture2 = findViewById(R.id.Pic2);
        picture3 = findViewById(R.id.Pic3);
        picture4 = findViewById(R.id.Pic4);
        picture5 = findViewById(R.id.Pic5);
        btnOK = findViewById(R.id.BtnOK);
        imgMe = findViewById(R.id.ImgMe);

        //동의 체크하면 발생이벤트
        chkAgree.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(chkAgree.isChecked()){
                    text2.setVisibility(View.VISIBLE);
                    rGroup1.setVisibility(View.VISIBLE);
                    btnOK.setVisibility(View.VISIBLE);
                    imgMe.setVisibility(View.VISIBLE);
                }else{
                    text2.setVisibility(View.INVISIBLE);
                    rGroup1.setVisibility(View.INVISIBLE);
                    btnOK.setVisibility(View.INVISIBLE);
                    imgMe.setVisibility(View.INVISIBLE);
                }
            }
        });

        btnOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (rGroup1.getCheckedRadioButtonId()){
                    case R.id.Pic1:
                        imgMe.setImageResource(R.drawable.pic1);
                        break;
                    case R.id.Pic2:
                        imgMe.setImageResource(R.drawable.pic2);
                        break;
                    case R.id.Pic3:
                        imgMe.setImageResource(R.drawable.pic3);
                        break;
                    case R.id.Pic4:
                        imgMe.setImageResource(R.drawable.pic4);
                        break;
                    case R.id.Pic5:
                        imgMe.setImageResource(R.drawable.pic5);
                        break;
                    default:
                        Toast.makeText(getApplicationContext(), "사진을 먼저 선택하세요", Toast.LENGTH_SHORT)
                                .show();
                }
            }
        });
    }
}