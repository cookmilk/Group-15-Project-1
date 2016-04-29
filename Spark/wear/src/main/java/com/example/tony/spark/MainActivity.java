package com.example.tony.spark;

import android.app.Activity;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.wearable.activity.WearableActivity;
import android.support.wearable.view.BoxInsetLayout;
import android.support.wearable.view.WatchViewStub;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends WearableActivity implements SensorEventListener {

//    private TextView mTextView;
    private BoxInsetLayout mContainerView;
    private TextView text;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        LinearLayout dayLog = (LinearLayout) findViewById(R.id.day);
        text = (TextView) findViewById(R.id.textView2);
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
    private void getStepCount(){
        SensorManager mSensorManager = ((SensorManager) getSystemService(SENSOR_SERVICE));
        Sensor mStepCountSensor = mSensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER);
        mSensorManager.registerListener(this, mStepCountSensor, SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if (event.sensor.getType() == Sensor.TYPE_STEP_COUNTER) {
            Integer value = (int) event.values[0];
            text.setText(value.toString());
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
    }
}
