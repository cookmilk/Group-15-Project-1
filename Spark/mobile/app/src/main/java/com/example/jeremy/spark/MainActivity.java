package com.example.jeremy.spark;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GestureDetectorCompat;
import android.support.v4.view.MotionEventCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        View myView = findViewById(R.id.imageButton1);

        myView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                //startActivity(new Intent(MainActivity.this, FirstTimeSetup2Activity.class));
            }
        });
        myView.setOnTouchListener(new OnSwipeTouchListener(this) {
            public boolean onSwipeTop() {
                return true;
            }
            public boolean onSwipeRight() {
                startActivity(new Intent(MainActivity.this, FirstTimeSetup2Activity.class));
                return true;
            }
            public boolean onSwipeLeft() {
                startActivity(new Intent(MainActivity.this, HomeScreenActivity.class));
                return true;
            }
            public boolean onSwipeBottom() {
                return true;
            }

        });
    }

}
