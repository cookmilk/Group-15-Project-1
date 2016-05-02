package com.example.tony.spark;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    SparkDataBase sdb = new SparkDataBase(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        isFirstTime ift = new isFirstTime();
        setContentView(R.layout.activity_main);
        if (ift.checkIt(this)) {
            super.onCreate(savedInstanceState);

            Button skip_button = (Button) findViewById(R.id.setup_1_button_1);
            Button next_button = (Button) findViewById(R.id.setup_1_button_2);

            /** Uncomment below to test popup window*/
//        Button temp = (Button) findViewById(R.id.temp);
//        temp.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                startActivity(new Intent(MainActivity.this, DiaryActivity.class));
//            }
//        });

            skip_button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    startActivity(new Intent(MainActivity.this, HomeScreenMenuActivity.class));
                }
            });

            next_button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    startActivity(new Intent(MainActivity.this, Setup2Activity.class));
                }
            });
        } else {
            startActivity(new Intent(this, HomeScreenMenuActivity.class));
        }
    }

}
