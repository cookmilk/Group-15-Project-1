package com.example.tony.spark;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class DiaryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diary);

        View myView = findViewById(R.id.imageButton);

        myView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                startActivity(new Intent(DiaryActivity.this, DiaryEntryEditActivity.class));
            }
        });
        myView.setOnTouchListener(new OnSwipeTouchListener(this) {
            public boolean onSwipeTop() {
                startActivity(new Intent(DiaryActivity.this, DiaryPopupActivity.class));
                return true;
            }

            public boolean onSwipeRight() {
                startActivity(new Intent(DiaryActivity.this, DiaryPopupActivity.class));
                return true;
            }

            public boolean onSwipeLeft() {
                startActivity(new Intent(DiaryActivity.this, DiaryPopupActivity.class));
                return true;
            }

            public boolean onSwipeBottom() {
                return true;
            }

        });
    }

}
