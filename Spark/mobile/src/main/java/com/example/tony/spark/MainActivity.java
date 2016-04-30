package com.example.tony.spark;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //View myView = findViewById(R.id.imageButton1);


        //Deprecated
        /*
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
        */
    }

}
