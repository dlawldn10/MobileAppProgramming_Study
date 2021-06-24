package com.project.myprofileapp;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageButton;

import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;

public class setProfileView extends View {
    String imagePath = null;
    CardView cardView;
    public setProfileView(Context context, @Nullable AttributeSet attrs){
        super(context, attrs);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if(imagePath != null) {
            cardView= findViewById(R.id.profileCardView);
            Bitmap bitmap = BitmapFactory.decodeFile(imagePath);
            Bitmap resize_bitmap = Bitmap.createScaledBitmap(bitmap, (int) (bitmap.getWidth()*0.3),
                    (int) (bitmap.getHeight()*0.3), true);
            canvas.drawBitmap(resize_bitmap, 0, 0, null);
            bitmap.recycle();
        }
    }
}
