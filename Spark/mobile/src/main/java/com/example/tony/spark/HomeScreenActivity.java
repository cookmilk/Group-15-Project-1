package com.example.tony.spark;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class HomeScreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);

        View myView = findViewById(R.id.imageButton2);

        myView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                startActivity(new Intent(HomeScreenActivity.this, DiaryEntryEditActivity.class));
            }
        });
        myView.setOnTouchListener(new OnSwipeTouchListener(this) {
            public boolean onSwipeTop() {
                startActivity(new Intent(HomeScreenActivity.this, MenuPageActivity.class));
                return true;
            }

            public boolean onSwipeRight() {
                //startActivity(new Intent(HomeScreenActivity.this, HomeScreenActivity.class));
                return true;
            }

            public boolean onSwipeLeft() {
                //startActivity(new Intent(HomeScreenActivity.this, HomeScreenActivity.class));
                return true;
            }

            public boolean onSwipeBottom() {
                return true;
            }

        });
    }

}
