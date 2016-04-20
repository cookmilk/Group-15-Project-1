package com.example.jeremy.spark;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.View;

public class MenuPageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_page);

        View myView = findViewById(R.id.imageButton);

        myView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                startActivity(new Intent(MenuPageActivity.this, Settings1Activity.class));
            }
        });
        myView.setOnTouchListener(new OnSwipeTouchListener(this) {
            public boolean onSwipeTop() {
                startActivity(new Intent(MenuPageActivity.this, HomeScreenActivity.class));
                return true;
            }

            public boolean onSwipeRight() {
                startActivity(new Intent(MenuPageActivity.this, AnalyticsActivity.class));
                return true;
            }

            public boolean onSwipeLeft() {
                startActivity(new Intent(MenuPageActivity.this, DiaryActivity.class));
                return true;
            }

            public boolean onSwipeBottom() {
                startActivity(new Intent(MenuPageActivity.this, ChallengePage1Activity.class));
                return true;
            }

        });
    }

}
