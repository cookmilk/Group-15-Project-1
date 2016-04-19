package com.example.cookmilk.spark;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.wearable.activity.WearableActivity;
import android.support.wearable.view.BoxInsetLayout;
import android.support.wearable.view.WatchViewStub;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends WearableActivity {

//    private TextView mTextView;
    private BoxInsetLayout mContainerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        LinearLayout dayLog = (LinearLayout) findViewById(R.id.day);

        dayLog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, DayActivity.class);
                startActivity(i);
            }
        });

        LinearLayout monthLog = (LinearLayout) findViewById(R.id.month);

        monthLog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, MonthActivity.class);
                startActivity(i);
            }
        });

        setAmbientEnabled();

        mContainerView = (BoxInsetLayout) findViewById(R.id.container);


        mContainerView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, Notification.class);
                startActivity(i);
            }
        });
    }
}
