package com.example.tony.spark;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class DiaryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diary);

        Button today = (Button) findViewById(R.id.todaybut);
        Button yesterday = (Button) findViewById(R.id.yestbut);
        Button day = (Button) findViewById(R.id.daybut);

//        View myView = findViewById(R.id.imageButton);

        today.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DiaryActivity.this, DiaryPopupActivity.class));
            }
        });

        yesterday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DiaryActivity.this, DiaryPopupActivity.class));
            }
        });

        day.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DiaryActivity.this, DiaryPopupActivity.class));
            }
        });


//        myView.setOnTouchListener(new OnSwipeTouchListener(this) {
//            public boolean onSwipeTop() {
//                startActivity(new Intent(DiaryActivity.this, DiaryPopupActivity.class));
//                return true;
//            }
//
//            public boolean onSwipeRight() {
//                startActivity(new Intent(DiaryActivity.this, DiaryPopupActivity.class));
//                return true;
//            }
//
//            public boolean onSwipeLeft() {
//                startActivity(new Intent(DiaryActivity.this, DiaryPopupActivity.class));
//                return true;
//            }
//
//            public boolean onSwipeBottom() {
//                return true;
//            }
//
//        });
    }

}
