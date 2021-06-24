package com.project.myprofileapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.SubMenu;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;

public class PaintActivity extends AppCompatActivity {

    final static int LINE = 1, CIRCLE = 2;
    final static int RECT = 3;
    final static int red = 4, blue = 5, green = 6;
    static int shape  = LINE;
    static int color = red;
    MyPaintView paintView;

    Button backBttn4;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_paint);

        RelativeLayout pictureLayout = findViewById(R.id.pictureLayout);
        paintView = new MyPaintView(this);
        pictureLayout.addView(paintView);
        backBttn4 = findViewById(R.id.backBttn4);



        backBttn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);

        menu.add(0,1,0,"선 그리기");
        menu.add(0, 2, 0, "원 그리기");
        menu.add(0, 3, 0, "직사각형 그리기");
        // 그룹id, 메뉴id, 순서, 원하는 텍스트

        SubMenu submenu = menu.addSubMenu("색상 변경 >>");
        submenu.add(0,4,0,"빨강");
        submenu.add(0,5,0,"파랑");
        submenu.add(0,6,0,"초록");
        //색상변경을 도와주는 서브메뉴만들기

        return true;
    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {


        switch (item.getItemId()){
            case LINE:
                shape = LINE;
                return true;
            case CIRCLE:
                shape = CIRCLE;
                return true;
            case RECT:
                shape = RECT;
                return true;
            case red:
                color = red;
                return true;
            case blue:
                color = blue;
                return true;
            case green:
                color = green;
                return true;



        }

        return false;
    }

    public static class MyPaintView extends View {
        ArrayList<MyShape> myShapeArrayList = new ArrayList<>();

        MyShape currentShape;
        int startX= -1;
        int startY= -1;
        int stopX= -1;
        int stopY= -1;

        public MyPaintView(Context context) {
            super(context);
        }

        @Override
        public boolean onTouchEvent(MotionEvent event) {

            switch (event.getAction()){

                case MotionEvent.ACTION_DOWN: //터치할때
                    startX = (int)event.getX();
                    startY = (int)event.getY();
                    break;
                case MotionEvent.ACTION_UP: // 손을 땔때
                    stopX = (int)event.getX();
                    stopY = (int)event.getY();
                    this.invalidate(); // onDraw메소드로 이동
            }
            return true;
        }


        @Override
        protected void onDraw(Canvas canvas) {
            super.onDraw(canvas);

            Paint paint = new Paint();
            paint.setAntiAlias(true);
            paint.setStrokeWidth(5);
            paint.setStyle(Paint.Style.STROKE);

            currentShape = new MyShape(shape, startX, startY, stopX, stopY, paint);
            myShapeArrayList.add(currentShape);

            for(MyShape cshape: myShapeArrayList)
                draw_shape(cshape, canvas);

            if(currentShape != null)
                draw_shape(currentShape, canvas);

        }

        public void draw_shape(MyShape myshape, Canvas canvas) {

            switch (myshape.shape_type) {

                case LINE:
                    canvas.drawLine(myshape.startX, myshape.startY, myshape.stopX, myshape.stopY, myshape.paint);
                    break;
                case CIRCLE:
                    int radius = (int) Math.sqrt(Math.pow(myshape.stopX - myshape.startX, 2) + Math.pow(myshape.stopY - myshape.startY, 2));
                    canvas.drawCircle(myshape.startX, myshape.startY, radius,myshape.paint);
                    break;
                case RECT:
                    Rect rect = new Rect(myshape.startX,myshape.startY, myshape.stopX, myshape.stopY);
                    canvas.drawRect(rect, myshape.paint);
                    break;
            }
        }


        // 그려진 정보를 저장하는 MyShape클래스
        private static class MyShape {

            int shape_type, startX, startY, stopX, stopY;
            Paint paint;

            public MyShape(int shape_type, int startX, int startY, int stopX, int stopY, Paint paint) {

                this.shape_type = shape_type;
                this.startX = startX;
                this.startY = startY;
                this.stopX = stopX;
                this.stopY = stopY;
                this.paint = paint;

                switch (color){

                    case red:
                        paint.setColor(Color.RED);
                        break;
                    case blue:
                        paint.setColor(Color.BLUE);
                        break;
                    case green:
                        paint.setColor(Color.GREEN);
                        break;
                }
            }
        }




    }
}